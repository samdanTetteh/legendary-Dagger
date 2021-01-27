package com.ijikod.di.home.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.ijikod.di.home.R
import com.ijikod.poweradapter.ItemRenderer
import javax.inject.Inject

class NumberItemRenderer @Inject constructor(): ItemRenderer<NumberItem> {


    override fun layoutRes(): Int = R.layout.number_item

    override fun createView(parent: ViewGroup): View {
        return LayoutInflater.from(parent.context).inflate(layoutRes(), parent, false)
    }

    override fun render(itemView: View, item: NumberItem) {
        (itemView as TextView).text = item.number.toString()
    }
}