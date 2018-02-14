package com.example.taha.firebasebam.Dialogs


import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.support.v4.app.DialogFragment
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.taha.firebasebam.R


/**
 * A simple [Fragment] subclass.
 */
class ProfilResimFragment : DialogFragment() {


    interface onProfilResimListener{
        fun getResimYol(resimPath: Uri?)
        fun resimBitmap(bitmap:Bitmap?)

    }

    lateinit var myProfilResimListener: onProfilResimListener

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view =  inflater!!.inflate(R.layout.fragment_profil_resim, container, false)

        var tvTakePhoto = view.findViewById<TextView>(R.id.tvNewPhoto)
        var tvPickPhoto = view.findViewById<TextView>(R.id.tvPickImg)

        tvTakePhoto.setOnClickListener {

            var intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(intent,200)

        }

        tvPickPhoto.setOnClickListener {

            var intent = Intent(Intent.ACTION_GET_CONTENT)

            intent.type = "image/*"
            startActivityForResult(intent,100)


        }

        return view
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)


        //galeriden
        if(requestCode == 100 && resultCode == Activity.RESULT_OK && data != null)
        {
            var resim = data.data
            Log.e("zundi","$resim")
            myProfilResimListener.getResimYol(resim)
            dismiss()

        } // kameradan
        else if(requestCode == 200 && resultCode == Activity.RESULT_OK && data != null)
        {
            var resim:Bitmap
            resim = data.extras.get("data") as Bitmap
            myProfilResimListener.resimBitmap(resim)
            dismiss()
        }

    }

    override fun onAttach(context: Context?) {

        myProfilResimListener = activity as onProfilResimListener
        super.onAttach(context)

    }

}// Required empty public constructor
