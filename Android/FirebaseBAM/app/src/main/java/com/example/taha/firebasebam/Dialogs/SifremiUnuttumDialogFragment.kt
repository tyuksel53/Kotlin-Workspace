package com.example.taha.firebasebam.Dialogs


import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v4.app.FragmentActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.taha.firebasebam.R
import com.google.firebase.auth.FirebaseAuth

class SifremiUnuttumDialogFragment : DialogFragment() {

    lateinit var mContext:FragmentActivity
    lateinit var edEmail:EditText
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        mContext = activity
        val view =  inflater!!.inflate(R.layout.fragment_sifremi_unuttum_dialog, container, false)
        val btnIptal = view.findViewById<Button>(R.id.btnSifreTekrarIptal)
        val btnSifreGonder = view.findViewById<Button>(R.id.btnSifreTekrarGonder)
        edEmail = view.findViewById<EditText>(R.id.edSifreTekrarGonder)
        btnSifreGonder.setOnClickListener {
            if( edEmail.text.toString().isNotEmpty() )
            {
                FirebaseAuth.getInstance().sendPasswordResetEmail(edEmail.text.toString())
                        .addOnCompleteListener {
                            if(it.isSuccessful)
                            {
                                Toast.makeText(mContext,"Şifre sıfırlama maili göderildi",Toast.LENGTH_LONG).show()
                                dialog.dismiss()
                            }else
                            {
                                Toast.makeText(mContext,"Sıçtı",Toast.LENGTH_LONG).show()
                            }
                        }
            }
        }
        btnIptal.setOnClickListener {
            dialog.dismiss()
        }



        return view
    }

}
