package com.amrit.niumassignment

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment(), CountryAdapter.SelectCountryListener {

    private val mMainViewModel: MainViewModel by activityViewModels()
    private lateinit var mContext: Context

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUp()
        setListener()
        setViewObserver()
    }

    private fun setListener() {
        country_search.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(
                s: CharSequence, start: Int, before: Int,
                count: Int
            ) {
            }

            override fun beforeTextChanged(
                s: CharSequence, start: Int, count: Int,
                after: Int
            ) {
            }

            override fun afterTextChanged(theWatchedText: Editable) {
                val filterList = ArrayList<Country>()
                val text = theWatchedText.toString()
                mMainViewModel.getCountryLiveData().value?.let {
                    for (i in it.indices) {
                        if (it[i].name.toLowerCase().contains(text.toLowerCase())) {
                            filterList.add(it[i])
                        }
                    }
                    if (!filterList.isNullOrEmpty()){
                        updateUserInterface(filterList)
                    }else{
                        updateUserInterface(it)
                    }
                }
            }
        })
        sort_natural.setOnClickListener {
            mMainViewModel.mIsNatural = true
            mMainViewModel.getCountryLiveData().value?.let { updateUserInterface(it) }
        }
        sort_reverse.setOnClickListener {
            mMainViewModel.mIsNatural = false
            mMainViewModel.getCountryLiveData().value?.let { updateUserInterface(it) }
        }
    }

    private fun setUp() {
        mMainViewModel.getCountryList()
        country_list.layoutManager = LinearLayoutManager(mContext)
    }

    private fun setViewObserver() {
        mMainViewModel.getCountryLiveData()
            .observe(viewLifecycleOwner, { data -> updateUserInterface(data) })
    }

    private fun updateUserInterface(countryList: List<Country>) {
        var list = countryList
        if (!mMainViewModel.mIsNatural) {
            list = countryList.asReversed()
        }
        val adapter = country_list.adapter as CountryAdapter?
        if (adapter == null) {
            country_list.adapter = CountryAdapter(list, this)
        } else {
            adapter.updateData(list)
            adapter.notifyDataSetChanged()
        }
    }

    override fun selectedCountry(country: Country) {
        mMainViewModel.setSelectedCountry(country)
        val action = HomeFragmentDirections.actionHomeFragmentToDetailFragment()
        this.findNavController().navigate(action)
    }

}