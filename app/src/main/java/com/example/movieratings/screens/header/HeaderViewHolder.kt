package com.example.movieratings.screens.header

import android.util.TypedValue
import androidx.recyclerview.widget.RecyclerView
import com.example.movieratings.databinding.ItemHeaderBinding
import com.example.movieratings.models.Header
import com.example.movieratings.util.invisibleOnCondition

/**
 * Created by Ankita
 */
class HeaderViewHolder(private val viewBinding: ItemHeaderBinding) :
    RecyclerView.ViewHolder(viewBinding.root) {

    lateinit var listener: HeaderItemClickListener
    private val SELECTED_FONT_SIZE_SP = 32
    private val UNSELECTED_FONT_SIZE_SP = 20

    fun bind(header: Header, pos: Int) {
        viewBinding.apply {
            title.text = header.title
            title.setTextSize(
                TypedValue.COMPLEX_UNIT_SP,
                if (header.isSelected) SELECTED_FONT_SIZE_SP.toFloat() else UNSELECTED_FONT_SIZE_SP.toFloat()
            )
            selection.invisibleOnCondition(!header.isSelected)
            headerContainer.setOnClickListener {
                listener.onHeaderClicked(pos)
            }
        }
    }
}

interface HeaderItemClickListener {
    fun onHeaderClicked(pos: Int)
}