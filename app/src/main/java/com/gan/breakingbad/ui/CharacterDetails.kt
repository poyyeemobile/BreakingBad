package com.gan.breakingbad.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.gan.breakingbad.R
import com.gan.breakingbad.service.BreakingBadDataService
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.character_details.*


class CharacterDetails : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.character_details)
        val intent = intent
        val getCharacters: Bundle? = intent.extras
        if (getCharacters != null) {
            val img = getCharacters.getString("img")
            val name = getCharacters.getString("name")
            val status = getCharacters.getString("status")
            val apperance = getCharacters.getString("apperance")
            val occupation = getCharacters.getString("occupation")
            val nickname = getCharacters.getString("nickname")

            tvName.text = name
            tvStatus.text = status
            if (apperance != null) {
                tvSeasonAppearance.text = apperance.substring(1, apperance.length - 1)
            }

            if (occupation != null) {
                tvOccupation.text = occupation.substring(1, occupation.length - 1)
            }
            tvNickName.text = nickname
            Glide.with(this).load(img).into(ivCharacterName)


            ivClose.setOnClickListener {
                finish()
            }
        }
    }
}