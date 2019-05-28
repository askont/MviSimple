package com.yakow.weber.mvisimple.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.yakow.weber.mvisimple.R
import com.yakow.weber.mvisimple.model.data.models.LanguageModel
import kotlinx.android.synthetic.main.item_language_holder.view.*

/**
 * Created on 29.05.19
 * @author YWeber */

class LanguageAdapter(listLanguageModel: List<LanguageModel>) :
    RecyclerView.Adapter<LanguageAdapter.SearchViewHolder>() {
    private val mutableList = mutableListOf<LanguageModel>()
    private var clickItem: () -> Unit = {}

    init {
        mutableList.addAll(listLanguageModel)
    }

    fun addClickListener(click: () -> Unit) {
        clickItem = click
    }

    fun refreshData(listLanguageModel: List<LanguageModel>) {
        mutableList.clear()
        mutableList.addAll(listLanguageModel)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_language_holder, parent, false)
        return SearchViewHolder(view, clickItem)
    }

    override fun getItemCount(): Int = mutableList.size

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        holder.bind(mutableList[position])
    }


    class SearchViewHolder(itemView: View, val clickItemView: () -> Unit) :
        RecyclerView.ViewHolder(itemView) {
        init {
            itemView.setOnClickListener {
                clickItemView()
            }
        }

        fun bind(language: LanguageModel) {
            itemView.nameLanguage.text = language.name
            itemView.descriptionLanguage.text = language.description
        }
    }
}