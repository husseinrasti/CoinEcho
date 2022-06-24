package com.husseinrasti.core.network.callback

import android.content.res.Resources
import androidx.annotation.StringRes
import androidx.paging.PagingSource
import com.husseinrasti.core.network.R
import com.husseinrasti.core.model.ResponseObject
import com.husseinrasti.core.model.NETWORK_PAGE_SIZE
import com.husseinrasti.core.model.STARTING_PAGE_INDEX
import com.husseinrasti.core.model.toFailure
import retrofit2.Response


suspend inline fun <reified RequestType : ResponseObject<Entity>, reified Entity : Any> fetchPage(
    resources: Resources,
    position: Int,
    loadSize: Int,
    @StringRes errorMessage: Int = R.string.msg_error,
    crossinline fetch: suspend () -> Response<List<RequestType>>
): PagingSource.LoadResult<Int, Entity> {
    return try {
        val response = fetch.invoke()
        if (response.isSuccessful) PagingSource.LoadResult.Page(
            data = response.body()!!.map { item -> item.toDomain() },
            prevKey = if (position == STARTING_PAGE_INDEX) null else position - 1,
            nextKey = if (response.body()
                    .isNullOrEmpty()
            ) null else (position + (loadSize / NETWORK_PAGE_SIZE)),
            itemsBefore = PagingSource.LoadResult.Page.COUNT_UNDEFINED,
            itemsAfter = PagingSource.LoadResult.Page.COUNT_UNDEFINED
        )
        else PagingSource.LoadResult.Error(response.errorResponse(resources.getString(errorMessage)))
    } catch (e: Exception) {
        e.printStackTrace()
        PagingSource.LoadResult.Error(e.toFailure())
    }
}