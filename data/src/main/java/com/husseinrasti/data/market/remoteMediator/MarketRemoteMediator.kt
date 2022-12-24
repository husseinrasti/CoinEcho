package com.husseinrasti.data.market.remoteMediator

import android.util.Log
import androidx.paging.*
import androidx.room.RoomDatabase
import androidx.room.withTransaction
import com.husseinrasti.data.bookmarkcoin.datasource.BookMarkCoinDataSource
import com.husseinrasti.data.coin.datasource.CoinDataSource
import com.husseinrasti.data.market.remote.MarketApi
import com.husseinrasti.data.remoteKeys.datasource.RemoteKeysDataSource
import com.husseinrasti.data.remoteKeys.entity.RemoteKeysEntity
import com.husseinrasti.domain.coin.entity.CoinEntity
import com.husseinrasti.domain.market.entity.MarketEntity
import java.io.InvalidObjectException
import java.lang.Exception
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class MarketRemoteMediator @Inject constructor(
    private val coinDAO: CoinDataSource,
    private val remoteKeyDAO: RemoteKeysDataSource,
    private val marketInterface: MarketApi,
    private val db: RoomDatabase,
    private val bookMarkDao:BookMarkCoinDataSource
    ) : RemoteMediator<Int, CoinEntity.Item>() {

    private val initialPage: Int = 1

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, CoinEntity.Item>
    ): MediatorResult {

        return try {

            val currentPage = when (loadType) {
                LoadType.REFRESH -> {
                    val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
                    remoteKeys?.nextKey?.minus(1) ?: initialPage
                }
                LoadType.PREPEND -> {
                    val remoteKeys = getRemoteKeyForFirstItem(state)
                    val prevKey = remoteKeys?.prevKey?:return MediatorResult.Success(endOfPaginationReached = remoteKeys != null)
                    prevKey
                }
                LoadType.APPEND -> {
                    val remoteKeys = getRemoteKeyForLastItem(state)
                    val nextKey = remoteKeys?.nextKey?:return MediatorResult.Success(endOfPaginationReached = remoteKeys != null)
                    nextKey
                }
            }
            val body = MarketEntity.Body(currency = "usd")
            val response = marketInterface.getMarkets(
                category = body.category,
                currency = body.currency,
                order = body.order,
                sparkline = body.sparkline,
                page = currentPage,
                limit = state.config.pageSize
            )

            if (response.isSuccessful) {
               val data = response.body()?.map { it.toDomain() }
                val endOfPaging = data!!.isEmpty()

                Log.e("current Page: ", currentPage.toString())
                Log.e("endOfPagination: ", endOfPaging.toString())

                db.withTransaction {
                    if (loadType == LoadType.REFRESH) {

                        remoteKeyDAO.deleteAllRemoteKeys()
                        coinDAO.deleteAll()
                    }
                    val prevPage = if (currentPage == initialPage) null else currentPage - 1
                    val nextPage = if (endOfPaging) null else currentPage + 1

                    val keys = data.map {
                        RemoteKeysEntity(
                            type = it.id,
                            prevKey = prevPage,
                            nextKey = nextPage
                        )
                    }
                    setBookMarks(data)
                    remoteKeyDAO.insertKeys(keys)
                    coinDAO.insertList(data)
                }
                    MediatorResult.Success(endOfPaging)
            } else {

                MediatorResult.Error(throw InvalidObjectException("invalid"))
            }

        } catch (e: Exception) {
            MediatorResult.Error(e)
        }
    }
    private suspend fun getRemoteKeyClosestToCurrentPosition(
        state: PagingState<Int,CoinEntity.Item>
    ): RemoteKeysEntity? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let { repoId ->
                remoteKeyDAO.getRemoteKey(repoId)
            }
        }
    }
    private suspend fun getRemoteKeyForFirstItem(state: PagingState<Int,CoinEntity.Item>): RemoteKeysEntity? {
        return state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()
            ?.let { repo ->
               remoteKeyDAO.getRemoteKey(repo.id)
            }
    }
    private suspend fun getRemoteKeyForLastItem(state: PagingState<Int,CoinEntity.Item>): RemoteKeysEntity? {
        return state.pages.lastOrNull() { it.data.isNotEmpty() }?.data?.lastOrNull()
            ?.let { repo ->
               remoteKeyDAO.getRemoteKey(repo.id)
            }
    }
    private suspend fun setBookMarks(list:List<CoinEntity.Item>){
        var bookMarkList =bookMarkDao.selectAllBookMarksIds()
        list.map { coinEntity->
            bookMarkList.map {  bookmarkId->

                if(coinEntity.id.equals(bookmarkId)){
                    coinEntity.bookmarked=true
                }
            }
        }
    }



}
