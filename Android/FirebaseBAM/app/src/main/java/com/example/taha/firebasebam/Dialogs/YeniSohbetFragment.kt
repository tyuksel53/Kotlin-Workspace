package com.example.taha.firebasebam


import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.example.taha.firebasebam.model.Kullanici
import com.example.taha.firebasebam.model.SohbetMesajlar
import com.example.taha.firebasebam.model.SohbetOdası
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import java.text.SimpleDateFormat
import java.util.*

class YeniSohbetFragment : DialogFragment() {


    var mySeekProgress = 0
    var userLevel = 0

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view =  inflater!!.inflate(R.layout.fragment_yeni_sohbet, container, false)

        val btnSohbetOlustur = view.findViewById<Button>(R.id.btnSohbetOlustur)
        val edSohbetAdi = view.findViewById<EditText>(R.id.edSohbetAdi)
        val seviye = view.findViewById<SeekBar>(R.id.seekBarSeviye)
        val tvCurrentSeviye = view.findViewById<TextView>(R.id.tvSohbetSeviye)

        kullaniciSeviyeGetir()



        seviye.setOnSeekBarChangeListener(object:SeekBar.OnSeekBarChangeListener{
            @SuppressLint("SetTextI18n")
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                mySeekProgress = progress
                tvCurrentSeviye.text = "Şuanki Seviye: $mySeekProgress"
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {

            }

        })

        btnSohbetOlustur.setOnClickListener {

            if(edSohbetAdi.text.isNotEmpty())
            {
                if(userLevel >= seviye.progress)
                {
                    val ref = FirebaseDatabase.getInstance().reference

                    val sohbetOdasiId = ref.child("sohbetOdalari").push().key

                    val yeniSohbetOdasi = SohbetOdası()

                    yeniSohbetOdasi.olusturanAdi = FirebaseAuth.getInstance().currentUser?.uid
                    yeniSohbetOdasi.seviye = seviye.progress.toString()
                    yeniSohbetOdasi.sohbetOdasiAdi = edSohbetAdi.text.toString()
                    yeniSohbetOdasi.sohbetOdasi_Id = sohbetOdasiId

                    ref.child("sohbetOdalari").child(sohbetOdasiId).setValue(yeniSohbetOdasi)

                    var hosgeldinMesaj = SohbetMesajlar()
                    val mesajId = ref.child("sohbetOdalari").push().key

                    hosgeldinMesaj.mesaj = "Sohbet Odasına Hoş Geldiniz"
                    hosgeldinMesaj.timestamp = getDate()

                    ref.child("sohbetOdalari").child(sohbetOdasiId).child("sohbetOdasiMesajlari")
                            .child(mesajId).setValue(hosgeldinMesaj)

                    Toast.makeText(activity,"Sohbet Odasi Kaydedildi",Toast.LENGTH_LONG).show()

                    (activity as SohbetActivity).init()

                    dialog.dismiss()


                }else
                {
                    Toast.makeText(activity,"Rütbeniz buna yetmiyor",Toast.LENGTH_LONG).show()
                }


            }else
            {
                Toast.makeText(activity,"Sohbet Adi giriniz",Toast.LENGTH_LONG).show()
            }

        }


        return view
    }

    fun kullaniciSeviyeGetir()
    {

        val currentUser = FirebaseAuth.getInstance().currentUser

        val refrance = FirebaseDatabase.getInstance().reference

        var seviye: Kullanici? = null

        refrance.child("kullanici").orderByKey().equalTo(currentUser?.uid)
                .addListenerForSingleValueEvent(object:ValueEventListener{
                    override fun onCancelled(p0: DatabaseError?) {

                    }

                    override fun onDataChange(p0: DataSnapshot?) {

                        for(singleSnapshot in p0!!.children)
                        {
                            seviye = singleSnapshot.getValue(Kullanici::class.java)
                            userLevel = seviye?.seviye!!
                        }

                    }

                })
    }

    fun getDate():String
    {
        var format = SimpleDateFormat("yyyy-MM-dd HH-mm-ss", Locale("tr"))
        return format.format(Date())
    }

}