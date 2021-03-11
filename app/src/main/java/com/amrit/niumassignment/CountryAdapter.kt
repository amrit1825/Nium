package com.amrit.niumassignment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.country_item.view.*

class CountryAdapter(
    private var mCountryList: List<Country>,
    private val mSelectCountryListener: SelectCountryListener
) :
    RecyclerView.Adapter<CountryAdapter.ViewHolder>() {

    interface SelectCountryListener {
        fun selectedCountry(country: Country)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(country: Country, selectCountryListener: SelectCountryListener) = with(itemView) {
            country_name.text = country.name
            itemView.setOnClickListener { selectCountryListener.selectedCountry(country) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.country_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(
            mCountryList[position], mSelectCountryListener
        )
    }

    override fun getItemCount(): Int {
        return mCountryList.size
    }

    fun updateData(countryList: List<Country>) {
        mCountryList = countryList
    }

}