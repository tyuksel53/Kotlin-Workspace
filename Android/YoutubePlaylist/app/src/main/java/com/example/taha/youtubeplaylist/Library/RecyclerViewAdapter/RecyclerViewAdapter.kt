package com.example.taha.youtubeplaylist.Library.RecyclerViewAdapter

import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.taha.youtubeplaylist.Library.PlaylistData
import com.example.taha.youtubeplaylist.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.single_line.view.*


class PlayListAdapter(var items:List<PlaylistData.Items>): RecyclerView.Adapter<PlayListAdapter.PlayListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): PlayListViewHolder {

        var inflater = LayoutInflater.from(parent?.context)
        var singleLine = inflater.inflate(R.layout.single_line,parent,false)

        return PlayListViewHolder(singleLine)
    }

    override fun getItemCount(): Int {

        return items.size
    }

    override fun onBindViewHolder(holder: PlayListViewHolder?, position: Int) {
        var current = items.get(position)
        holder?.setData(current)
    }


    inner class PlayListViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {

        var singleLine = itemView as CardView

        var videoResim = singleLine.ivResim
        var videoBaslik = singleLine.tvTitle
        var videoAciklama = singleLine.tvDescription

        fun setData(currentVideo:PlaylistData.Items)
        {
            if(currentVideo.snippet?.description?.length!! > 100)
            {
                videoAciklama.text = currentVideo.snippet?.description?.substring(0,100)
            }else
            {
                videoAciklama.text = currentVideo.snippet?.description
            }
            videoBaslik.text = currentVideo.snippet?.title
            Picasso.with(itemView.context).
                    load(currentVideo.snippet?.thumbnails?.mdefault?.url).into(videoResim)

        }

    }
}