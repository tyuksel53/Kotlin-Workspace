package com.example.taha.candostlar

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.taha.candostlar.Library.Dostlar
import kotlinx.android.synthetic.main.activity_detay.*
import kotlinx.android.synthetic.main.tek_dost.*

class DetayActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detay)

        var getInfo = intent.getSerializableExtra("bilgi") as Dostlar

        tvDetayIsim.text = getInfo.isim
        ivDetayResim.setImageResource(getInfo.resim)


    }
}
