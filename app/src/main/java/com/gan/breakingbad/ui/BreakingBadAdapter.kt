package com.gan.breakingbad.ui

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.gan.breakingbad.R
import com.gan.breakingbad.model.GetCharacters
import com.gan.breakingbad.utils.GlideApp
import java.util.*

class BreakingBadAdapter (private val context: Context) : RecyclerView.Adapter<BreakingBadAdapter.BreakingBadViewHolder>() {

    private var characterList: List<GetCharacters> = listOf()
    private var characterListFiltered: List<GetCharacters> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BreakingBadViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.character_item, parent, false)

        return BreakingBadViewHolder(itemView)
    }

    override fun onBindViewHolder(characterListViewHolder: BreakingBadViewHolder, position: Int) {
        characterListViewHolder.bind(characterListFiltered[position])

    }


    override fun getItemCount() = characterListFiltered.size

    fun updateCharacters(characterLists: List<GetCharacters>) {
        this.characterList = characterLists
        this.characterListFiltered = characterLists
        notifyDataSetChanged()
    }

    fun getFilter(): Filter? {
        return object : Filter() {
            override fun performFiltering(charSequence: CharSequence): FilterResults {
                val charString = charSequence.toString()
                if (charString.isEmpty()) {
                    characterListFiltered = characterList
                } else {
                    val filteredList: MutableList<GetCharacters> =
                        ArrayList<GetCharacters>()
                    for (row in characterList) {
                        if (row.name.toLowerCase().contains(charString.toLowerCase()) || row.nickname.contains(
                                charSequence
                            )
                        ) {
                            filteredList.add(row)
                        }
                    }
                    characterListFiltered = filteredList
                }
                val filterResults = FilterResults()
                filterResults.values = characterListFiltered
                return filterResults
            }

            override fun publishResults(
                charSequence: CharSequence,
                filterResults: FilterResults
            ) {
                characterListFiltered = filterResults.values as List<GetCharacters>
                notifyDataSetChanged()
            }

        }
    }

    class BreakingBadViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val tvTitle = itemView.findViewById<TextView>(R.id.tvCharacter)
        private val imgPoster = itemView.findViewById<ImageView>(R.id.ivCharacter)

        fun bind(getCharacters: GetCharacters) {
            tvTitle.text = getCharacters.name
            GlideApp.with(itemView.context)
                .load(getCharacters.img)
                .placeholder(itemView.context.resources.getDrawable(R.drawable.ic_launcher_background))
                .centerCrop()
                .into(imgPoster)


            itemView.setOnClickListener {
                    var intent = Intent(itemView.context, CharacterDetails::class.java)
                    intent.putExtra("img",getCharacters.img);
                    intent.putExtra("status",getCharacters.status);
                    intent.putExtra("name",getCharacters.name);
                    intent.putExtra("nickname",getCharacters.nickname);
                    intent.putExtra("nickname",getCharacters.nickname);
                    intent.putExtra("occupation",getCharacters.occupation.toString());
                    intent.putExtra("apperance",getCharacters.apperance.toString());
                    itemView.context.startActivity(intent)

            }


        }
    }
}

