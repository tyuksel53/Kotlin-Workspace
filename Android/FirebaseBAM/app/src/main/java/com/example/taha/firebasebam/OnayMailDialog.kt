package com.example.taha.firebasebam


import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v4.app.FragmentActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseAuth


class OnayMailDialog : DialogFragment() {

    lateinit var edOnayMail:EditText
    lateinit var edOnayPassword:EditText
    lateinit var mContext: FragmentActivity
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.fragment_dialog, container, false)
        val btnIptal = view.findViewById<Button>(R.id.btnOnayIptal)
        val btnOnayMailGonder = view.findViewById<Button>(R.id.btnOnayGonder)

        edOnayPassword = view.findViewById(R.id.edOnayMailPassword)
        edOnayMail = view.findViewById(R.id.edOnayMailAdres)
        mContext = activity
        btnIptal.setOnClickListener {
            dialog.dismiss()
        }
        btnOnayMailGonder.setOnClickListener {
            Toast.makeText(mContext,"Gonder tıklandı",Toast.LENGTH_LONG).show()
            if(edOnayMail.text.toString().isNotEmpty() && edOnayPassword.text.toString().isNotEmpty())
            {
                onayMailGonder(edOnayMail.text.toString(),edOnayPassword.text.toString())
            }else
            {
                Toast.makeText(mContext,"Lütfen bütün alanları doldurun",Toast.LENGTH_LONG).show()
            }
        }


        return view
    }

    private fun onayMailGonder(mail: String, pass: String) {
        var credential = EmailAuthProvider.getCredential(mail,pass)
        FirebaseAuth.getInstance().signInWithCredential(credential)
                .addOnCompleteListener {
                    if(it.isSuccessful)
                    {
                        var currentUser = FirebaseAuth.getInstance().currentUser
                        if(currentUser != null)
                        {
                            currentUser.sendEmailVerification().addOnCompleteListener {

                                if(it.isSuccessful)
                                {
                                    Toast.makeText(mContext,"Onay maili tekrar göderildi",Toast.LENGTH_LONG).show()

                                    dialog.dismiss()
                                }else
                                {
                                    Toast.makeText(mContext,"Something went wrong :(",Toast.LENGTH_LONG).show()
                                }
                                FirebaseAuth.getInstance().signOut()
                            }
                        }
                    }else
                    {
                        Toast.makeText(activity,"Email veya şifre hatalı",Toast.LENGTH_LONG).show()
                    }
                }
    }

}
