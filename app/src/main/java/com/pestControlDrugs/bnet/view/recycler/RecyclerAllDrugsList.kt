package com.pestControlDrugs.bnet.view.recycler

import android.content.SharedPreferences
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.pestControlDrugs.bnet.R
import com.pestControlDrugs.bnet.constant.BASE_URL
import com.pestControlDrugs.bnet.constant.CURRENT_DRUGS_FRAGMENT
import com.pestControlDrugs.bnet.constant.DRUGS_NAME
import com.pestControlDrugs.bnet.model.dataclass.AllDrugs
import com.pestControlDrugs.bnet.tools.picassoLoad
import com.pestControlDrugs.bnet.tools.showToast
import com.pestControlDrugs.bnet.view.activities.MainActivity
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso

class RecyclerAllDrugsList(
    private val allDrugs: AllDrugs,
    private val mainActivity: MainActivity,
    private val sharedPreferencesEditor: SharedPreferences.Editor
) :
    RecyclerView.Adapter<RecyclerAllDrugsList.MyViewHolder>() {


    class MyViewHolder(itemView: View) : ViewHolder(itemView) {
        val card: CardView = itemView.findViewById(R.id.cardView)
        val drugsPhoto: ImageView = itemView.findViewById(R.id.drugsPhoto)
        val drugsName: TextView = itemView.findViewById(R.id.drugsName)
        val drugsDescription: TextView = itemView.findViewById(R.id.drugsDescription)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_drugs_recycler, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount() = allDrugs.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val drugsExample = allDrugs[position]
        holder.drugsName.text = drugsExample.name
        holder.drugsDescription.text = drugsExample.description
        setDrugsPhoto(drugsExample.image, holder.drugsPhoto)
        holder.card.setOnClickListener {
            sharedPreferencesEditor.putString(DRUGS_NAME, drugsExample.name).commit()
            mainActivity.changeFragment(CURRENT_DRUGS_FRAGMENT)
        }
    }
    private fun setDrugsPhoto(image: String, drugsPhoto: ImageView) {
        val url = BASE_URL + image
        picassoLoad(url, drugsPhoto, drugsPhoto.context)
    }


}