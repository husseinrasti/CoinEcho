package com.husseinrasti.detail_page

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.IAxisValueFormatter
import com.github.mikephil.charting.formatter.ValueFormatter
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import com.husseinrasti.detail_page.databinding.FragmentDetailBinding
import com.husseinrasti.domain.coin.entity.CoinEntity
import com.husseinrasti.domain.detail.entity.DetailEntity
import com.husseinrasti.domain.market.entity.MarketEntity
import dagger.hilt.android.AndroidEntryPoint
import java.sql.Timestamp
import java.text.DateFormat
import java.text.FieldPosition
import java.text.Format
import java.text.ParsePosition
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

/**
 * Fragment Created by Sarah
 */

@AndroidEntryPoint
class DetailFragment : Fragment() {

    private val viewModel: DetailViewModel by viewModels()

    private val args: DetailFragmentArgs by navArgs()

    private val date: Date = Date()
    private val dateFormat: SimpleDateFormat = SimpleDateFormat("hh:mm")

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
      /*  lifecycleScope.launchWhenStarted {

            binding.buttonOneDay.setOnClickListener {
                viewModel.getPriceList( 1)
            }

            binding.buttonSevenDay.setOnClickListener {
                viewModel.getPriceList(7)
            }

        }*/
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val nameOFCoin = args.nameOfCoin
        binding.name0fCoin.text = nameOFCoin

        binding.currentPrice.text = arguments?.getSerializable("currentPrice").toString()

        binding.marketCap2.text = arguments?.getSerializable("marketCap").toString()

        binding.percentSecond.text = arguments?.getSerializable("percent").toString()

        onSetUpViewModel()

        dateFormat.format(date)

        lifecycleScope.launchWhenStarted {
            binding.buttonOneDay.setOnClickListener {
                viewModel.getPriceList( 1)
                Log.d("Listener works","woooork")
                setLineChartData()
            }

            binding.buttonSevenDay.setOnClickListener {
                viewModel.getPriceList(7)
                Log.d("Listener works","woooork")
                setLineChartData()
            }
        }

      /*  binding.buttonOneDay.setBackgroundColor(R.color.sara_blue)
        binding.buttonSevenDay.setBackgroundColor(R.color.sara_blue)*/

    }

    private fun onSetUpViewModel(){
        viewModel.data.observe(viewLifecycleOwner){
            Log.d("Show the Price","price : ${
                it.prices}")
        }
    }

    private fun setLineChartData(){

        viewModel.data.observe(viewLifecycleOwner) {

            var all = it.prices
            val allPrices = it.prices.size

            for (i in 0 until allPrices) {
                if (allPrices / 2 == 0)
                {
                     all = all.filterIndexed { index, doubles -> index / 2 == 0  }
                }
            }

            val myCurrency = ArrayList<Entry>()

            all.forEach {

                binding.lineChart.xAxis.valueFormatter = FormatterClass()

                val entry = Entry(it[0].toFloat(), it[1].toFloat())
                myCurrency.add(entry)

                Log.d("Getting Entry", "Entry is $entry")

            }

            val lineDataSet = LineDataSet(myCurrency, "first")

            lineDataSet.valueFormatter = FormatterClass()
            lineDataSet.axisDependency = YAxis.AxisDependency.LEFT
            lineDataSet.color = R.color.red
            lineDataSet.setDrawCircles(false)

            val dataSets = ArrayList<ILineDataSet>()
            dataSets.add(lineDataSet)

            val data = LineData(dataSets)
            binding.lineChart.data = data
            binding.lineChart.invalidate()

            binding.lineChart.setBackgroundColor(R.color.white)
            binding.lineChart.setBackgroundColor(getResources().getColor(R.color.white))
        }

    }
}