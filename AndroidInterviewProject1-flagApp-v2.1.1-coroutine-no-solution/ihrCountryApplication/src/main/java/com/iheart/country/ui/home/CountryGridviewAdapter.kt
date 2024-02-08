package com.iheart.country.ui.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.iheart.country.R
import com.iheart.country.data.Country

class CountryGridviewAdapter(
    context: Context,
    private val data: List<Country>,
) :  BaseAdapter() {
    private val layoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun getCount(): Int {
        return data.size
    }

    override fun getItem(p0: Int): Country {
        return data[p0]
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getView(position: Int, view: View?, parent: ViewGroup?): View {
        var convertView = view

        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.detail_view, null)
        }

        convertView!!.findViewById<TextView>(R.id.title).text = getItem(position).name

        return convertView
    }

}
