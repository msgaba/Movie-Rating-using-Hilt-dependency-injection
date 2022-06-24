package com.example.movieratings.screens.header

import android.app.Activity
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.movieratings.databinding.ItemHeaderBinding
import com.example.movieratings.models.Header

/**
 * Created by Ankita
 */
class HeaderAdapter(private val mContext: Activity) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var mHeaderList: MutableList<Header> = arrayListOf()
    private lateinit var mListener: HeaderItemClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = ItemHeaderBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return HeaderViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val viewHolder: HeaderViewHolder = holder as HeaderViewHolder
        viewHolder.listener = mListener
        viewHolder.bind(mHeaderList[position], position)
    }

    override fun getItemCount(): Int = mHeaderList.size

    fun list(list: MutableList<Header>) {
        mHeaderList = list
        notifyDataSetChanged()
    }

    fun list(): MutableList<Header> {
        return mHeaderList
    }

    fun listener(listener: HeaderItemClickListener) {
        mListener = listener
    }
}