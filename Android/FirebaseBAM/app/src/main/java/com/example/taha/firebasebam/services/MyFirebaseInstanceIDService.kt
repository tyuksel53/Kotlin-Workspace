package com.example.taha.firebasebam.services

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.iid.FirebaseInstanceId
import com.google.firebase.iid.FirebaseInstanceIdService


class MyFirebaseInstanceIDService: FirebaseInstanceIdService() {

    override fun onTokenRefresh() {

        var refleshedToken:String? = FirebaseInstanceId.getInstance().token

        tokenKaydet(refleshedToken)
    }

    private fun tokenKaydet(refleshedToken: String?) {

        var ref = FirebaseDatabase.getInstance().reference
                .child("kullanici")
                .child(FirebaseAuth.getInstance().uid)
                .child("mesaj_token")
                .setValue(refleshedToken)


    }

}