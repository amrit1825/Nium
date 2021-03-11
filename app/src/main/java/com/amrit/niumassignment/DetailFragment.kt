package com.amrit.niumassignment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import kotlinx.android.synthetic.main.fragment_detail.*

class DetailFragment : Fragment() {

    private val mMainViewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setViewObserver()
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    private fun setViewObserver() {
        mMainViewModel.getSelectedCountryLiveData()
            .observe(viewLifecycleOwner, { data -> updateUserInterface(data) })
    }

    private fun updateUserInterface(country: Country) {
        country_value.text = country.name
        capital_value.text = country.capital
        region_value.text = country.region
        numeric_code_value.text = country.numericCode
    }

}