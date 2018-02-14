package com.example.taha.firebasebam

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.view.View
import android.widget.Toast
import com.example.taha.firebasebam.Dialogs.ProfilResimFragment
import com.example.taha.firebasebam.model.Kullanici
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.database.*
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.UploadTask
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_kullanici_detay.*
import java.io.ByteArrayOutputStream

class KullaniciDetayActivity : AppCompatActivity() , ProfilResimFragment.onProfilResimListener {


    var galeridenGelenUri:Uri? = null
    var kameradanGelenBitmap:Bitmap? = null
    var isPermissionGived = false


    override fun getResimYol(resimPath: Uri?) {
        galeridenGelenUri = resimPath
        Picasso.with(this).load(galeridenGelenUri).into(ivUpdateResim)
    }

    override fun resimBitmap(bitmap: Bitmap?) {
        ivUpdateResim.setImageBitmap(bitmap)
    }


    private fun fotografCompress(gelenUri:Uri?) {

        var compress = BackgroundImgCompress()
        compress.execute(gelenUri)

    }

    private fun fotografCompress(gelenBitmap:Bitmap?)
    {
        var compress = BackgroundImgCompress(gelenBitmap)
        var uri:Uri? = null
        compress.execute(null)
    }

    inner class BackgroundImgCompress:AsyncTask<Uri,Double,ByteArray?>
    {

        var myBitmap:Bitmap? = null

        constructor()

        constructor(kameradanGelenBitmap:Bitmap?)
        {
            this.myBitmap = kameradanGelenBitmap
        }

        override fun doInBackground(vararg params: Uri?): ByteArray? {

            if(myBitmap == null) /* galeriden resim seçilmekte */
            {
                myBitmap = MediaStore.Images.Media
                        .getBitmap(this@KullaniciDetayActivity.contentResolver,params[0])
              /*  Log.e("MUNDI","OrjinalResim Boyutu: ${myBitmap!!.byteCount.toDouble() / 1000000.toDouble() }")*/
            }

            var resimBytes:ByteArray? = null

            for(i in 1..5)
            {
                resimBytes = convertBitmapToByte(100/i,myBitmap)
                publishProgress(resimBytes!!.size.toDouble())
            }

            return resimBytes
        }

        private fun convertBitmapToByte(i: Int,bitmap:Bitmap?): ByteArray? {

            var stream = ByteArrayOutputStream()
            myBitmap?.compress(Bitmap.CompressFormat.JPEG,i,stream)
            return stream.toByteArray()
        }

        override fun onPreExecute() {
            super.onPreExecute()

        }

        override fun onPostExecute(result: ByteArray?) {
            super.onPostExecute(result)
            uploadResimToFirebase(result)

        }


        override fun onProgressUpdate(vararg values: Double?) {
            super.onProgressUpdate(*values)

            /*Toast.makeText(this@KullaniciDetayActivity,"Suanki Byte:${values[0]!! / 1000000}",Toast.LENGTH_LONG).show()*/

        }

    }

    private fun uploadResimToFirebase(result: ByteArray?) {

        pbSaveChanges.visibility  = View.VISIBLE
        var reffence = FirebaseStorage.getInstance().reference

        var yuklenecek_yer = reffence?.child("images/users/${FirebaseAuth.getInstance().currentUser?.uid}/profil_resim")

        var uploadGorevi = yuklenecek_yer.putBytes(result!!)

        uploadGorevi.addOnSuccessListener(object: OnSuccessListener<UploadTask.TaskSnapshot>{
            override fun onSuccess(p0: UploadTask.TaskSnapshot?) {
                var yol = p0?.downloadUrl
                Toast.makeText(this@KullaniciDetayActivity,"Yol: $yol",Toast.LENGTH_LONG).show()

                var ref = FirebaseDatabase.getInstance().reference
                var veri = ref.child("kullanici")
                        .child(FirebaseAuth.getInstance().currentUser?.uid)
                        .child("profil_resim").setValue(yol.toString())
                        .addOnCompleteListener {

                            pbSaveChanges.visibility = View.INVISIBLE
                        }

            }

        })

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kullanici_detay)

        var currentUser = FirebaseAuth.getInstance().currentUser!!
        var db = FirebaseDatabase.getInstance().reference
        edUpdateCurrentPass.setText("")

        db.child("kullanici").orderByKey().equalTo(currentUser.uid)
                .addValueEventListener(object :ValueEventListener{
                    override fun onCancelled(p0: DatabaseError?) {

                    }
                    override fun onDataChange(p0: DataSnapshot?) {

                        for(singleSnapshot in p0!!.children)
                        {
                            var kullanici = singleSnapshot.getValue(Kullanici::class.java)
                            edUpdateKullaniciAdi.setText(kullanici?.isim.toString())
                            edUpdatePhone.setText(kullanici?.telefon.toString())
                            Picasso.with(this@KullaniciDetayActivity)
                                    .load(kullanici?.profil_resim).into(ivUpdateResim)

                        }
                    }

                })

        tvUpdateMail.text = currentUser.email.toString()

        tvUpdateForgetPass.setOnClickListener {

            if(currentUser != null)
            {
                FirebaseAuth.getInstance()
                        .sendPasswordResetEmail(FirebaseAuth.getInstance().currentUser?.email.toString())
                        .addOnCompleteListener {
                            if(it.isSuccessful)
                            {
                                Toast.makeText(this@KullaniciDetayActivity,"Şifre sıfırlama maili göderildi", Toast.LENGTH_LONG).show()
                            }else
                            {
                                Toast.makeText(this@KullaniciDetayActivity,"Sıçtı", Toast.LENGTH_LONG).show()
                            }
                        }

            }

        }

        btnUpdateSaveChanges.setOnClickListener {

            if(edUpdateKullaniciAdi.text.isNotEmpty() && edUpdatePhone.text.isNotEmpty())
            {
                var bilgileriGuncelle = UserProfileChangeRequest.Builder()
                        .setDisplayName(edUpdateKullaniciAdi.text.toString())
                        .build()

                currentUser.updateProfile(bilgileriGuncelle)
                        .addOnCompleteListener {
                            if(it.isSuccessful)
                            {
                                Toast.makeText(this@KullaniciDetayActivity,"Bilgiler Güncellendi",Toast.LENGTH_LONG).show()
                                FirebaseDatabase.getInstance().reference
                                        .child("kullanici")
                                        .child(currentUser.uid.toString())
                                        .child("isim").setValue(edUpdateKullaniciAdi.text.toString())

                                FirebaseDatabase.getInstance().reference
                                        .child("kullanici")
                                        .child(currentUser.uid)
                                        .child("telefon").setValue(edUpdatePhone.text.toString())
                            }else
                            {
                                Toast.makeText(this@KullaniciDetayActivity,"Sıçtı",Toast.LENGTH_LONG).show()
                            }
                        }

            }else
            {
                Toast.makeText(this@KullaniciDetayActivity,"WOWOWOWOWWO bütün alanları doldur",Toast.LENGTH_LONG).show()
            }


            if(galeridenGelenUri != null)
            {
                fotografCompress(galeridenGelenUri)
            }else if(kameradanGelenBitmap != null)
            {
                fotografCompress(kameradanGelenBitmap)
            }


        }

        tvUpdatePassAndMail.setOnClickListener {

            if(edUpdateCurrentPass.text.isNotEmpty())
            {

                var credential = EmailAuthProvider.getCredential(currentUser.email.toString(),edUpdateCurrentPass.text.toString())
                currentUser.reauthenticate(credential)
                        .addOnCompleteListener {
                            if(it.isSuccessful)
                            {
                                clUpdateArea.visibility = View.VISIBLE

                                btnUpdateMail.setOnClickListener {
                                    mailGuncelle()
                                }

                                btnUpdatePass.setOnClickListener {
                                    sifreGuncelle()
                                }

                            }else
                            {
                                Toast.makeText(this@KullaniciDetayActivity,"Suanki şifreniz yanlıştır",Toast.LENGTH_LONG).show()
                                clUpdateArea.visibility = View.INVISIBLE
                            }
                        }

            }else
            {
                Toast.makeText(this,"Şuanki şifreni girin",Toast.LENGTH_LONG).show()
            }

        }

        ivUpdateResim.setOnClickListener {

            if(isPermissionGived)
            {
                var fragment = ProfilResimFragment()
                fragment.show(supportFragmentManager,"fotoseç")
            }else
            {
                izinleriIste()
            }

        }
    }

    private fun izinleriIste() {

        var izinler = arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE
        ,android.Manifest.permission.WRITE_EXTERNAL_STORAGE,android.Manifest.permission.CAMERA)

        if(ContextCompat.checkSelfPermission(this,izinler[0]) == PackageManager.PERMISSION_GRANTED
            && ContextCompat.checkSelfPermission(this,izinler[1]) == PackageManager.PERMISSION_GRANTED
            && ContextCompat.checkSelfPermission(this,izinler[2]) == PackageManager.PERMISSION_GRANTED)
        {
            isPermissionGived = true
        }else
        {
            ActivityCompat.requestPermissions(this,izinler,150)
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {

        if(requestCode == 150)
        {
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED
                && grantResults[1] == PackageManager.PERMISSION_GRANTED
                && grantResults[2] == PackageManager.PERMISSION_GRANTED)
            {
                var fragment = ProfilResimFragment()
                fragment.show(supportFragmentManager,"fotoseç")
            }else
            {
                Toast.makeText(this,"Tüm izinleri ver lan",Toast.LENGTH_LONG).show()
            }
        }

    }

    private fun mailGuncelle() {

        val kullanici = FirebaseAuth.getInstance().currentUser

        if(kullanici != null)
        {
            FirebaseAuth.getInstance().fetchProvidersForEmail(edUpdateMail.text.toString())
                    .addOnCompleteListener{

                        if(it.isSuccessful)
                        {
                            if(it.result.providers?.size == 1)
                            {
                                mesajGoster(this@KullaniciDetayActivity,"Var olan email girdiniz")
                            }else
                            {
                                kullanici.updateEmail(edUpdateMail.text.toString())
                                        .addOnCompleteListener {
                                            if(it.isSuccessful)
                                            {
                                                mesajGoster(this@KullaniciDetayActivity,"Mail değiştirildi")
                                                var intent = Intent(this@KullaniciDetayActivity,LoginActivity::class.java)
                                                FirebaseAuth.getInstance().signOut()
                                                startActivity(intent)
                                                finish()
                                            }else
                                            {
                                                mesajGoster(this@KullaniciDetayActivity,"Sıçtı")
                                            }
                                        }
                            }
                        }

                    }
        }

    }

    private fun sifreGuncelle() {

        val kullanici = FirebaseAuth.getInstance().currentUser

        if(kullanici != null)
        {
            kullanici.updatePassword(edUpdatePass.text.toString())
                    .addOnCompleteListener {
                        if(it.isSuccessful)
                        {
                            mesajGoster(this@KullaniciDetayActivity,"Şifre Güncellendi")
                        }
                    }
        }

    }

    private fun mesajGoster(context:Context,mesaj:String,isDurationLong:Boolean = true)
    {
        if(isDurationLong)
        {
            Toast.makeText(context,mesaj,Toast.LENGTH_LONG).show()
        }else
        {
            Toast.makeText(context,mesaj,Toast.LENGTH_SHORT).show()
        }

    }
}
