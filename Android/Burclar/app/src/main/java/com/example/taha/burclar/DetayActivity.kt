package com.example.taha.burclar

import android.annotation.SuppressLint
import android.graphics.BitmapFactory
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.v7.graphics.Palette
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_detay.*

class DetayActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detay)

        var position = intent.extras.get("position") as Int
        var detay = intent.extras.get("detay") as Burc

        tvBurcOzellikler.text = detay.burcBilgi

        burcHeader.setImageResource(detay.burcBuyukResim)

        /*Bitmap bitmap = BitmapFactory.decodeResource(getResources(),
        R.drawable.header);
        Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
            @Override
            public void onGenerated(Palette palette) {
                mutedColor = palette.getMutedColor(R.attr.colorPrimary);
                collapsingToolbar.setContentScrimColor(mutedColor);
            }
        });*/

        setSupportActionBar(anim_toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        collapsing_toolbar.title = detay.burcAdi

        var bitmap = BitmapFactory.decodeResource(resources,detay.burcBuyukResim)

        Palette.from(bitmap).generate {
            var mutedColor = it.getVibrantColor(R.attr.colorPrimary)
            collapsing_toolbar.setContentScrimColor(mutedColor)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                window.statusBarColor = mutedColor
            }
        }



    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}
