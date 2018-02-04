package com.example.taha.havadurumu

import android.app.Activity
import android.content.pm.PackageManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.util.Log
import android.view.View
import android.widget.*
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonObjectRequest
import com.example.taha.havadurumu.Library.MySingleton
import im.delight.android.location.SimpleLocation
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject
import java.net.URLEncoder
import java.text.SimpleDateFormat
import java.util.*
import java.util.jar.Manifest

class MainActivity : AppCompatActivity() {

    var formattedAddress: String? = null
    var lat:String? = null
    var lng:String? = null
    var latitude:String? = null
    var longitude:String? = null

    private var location: SimpleLocation? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        pbLoading.visibility = View.INVISIBLE


        var myAdapter = ArrayAdapter.createFromResource(this,R.array.sehirler,android.R.layout.simple_spinner_dropdown_item)
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        spArama.adapter = myAdapter

        spArama.onItemSelectedListener = object: AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                hideAll()
                var array = resources.getStringArray(R.array.sehirler)
                getData(array[position])
            }

        }

        /* var request:StringRequest = StringRequest(Request.Method.GET,"http://www.google.com",
                object: Response.Listener<String>{
                    override fun onResponse(response: String?) {
                        tvHelloWorld.text = response
                    }

                },object : Response.ErrorListener{
                    override fun onErrorResponse(error: VolleyError?) {

                    }

        })

        MySingleton.getInstance(this)?.addToRequestQueue(request)*/
    }

    override fun onResume() {
        super.onResume()
        /*location?.beginUpdates()*/
    }

    override fun onPause() {
        /*location?.endUpdates()*/
        super.onPause()
    }
    fun fahrenheitToCelsius(fahrenheit:Double?):Double
    {
        var celsius = (fahrenheit!! - 32) * 5/9

        return celsius
    }
    fun Double.formatla(kacTaneBasamak:Int) = java.lang.String.format("%.${kacTaneBasamak}f",this)

    fun showAll()
    {
        tvTarih.visibility = View.VISIBLE
        tvAciklama.visibility = View.VISIBLE
        tvSicaklik.visibility = View.VISIBLE
        tvSehir.visibility = View.VISIBLE
        textView5.visibility = View.VISIBLE
        imgWeather.visibility = View.VISIBLE

        pbLoading.visibility = View.INVISIBLE
    }

    fun hideAll()
    {
        tvTarih.visibility = View.INVISIBLE
        tvAciklama.visibility = View.INVISIBLE
        tvSicaklik.visibility = View.INVISIBLE
        tvSehir.visibility = View.INVISIBLE
        textView5.visibility = View.INVISIBLE
        imgWeather.visibility = View.INVISIBLE

        pbLoading.visibility = View.VISIBLE
    }

    fun getData(address:String)
    {
        pbLoading.visibility = View.VISIBLE

        if(address == "Konumum")
        {
            location = SimpleLocation(this)

            if (!location!!.hasLocationEnabled()) {
                // ask the user to enable location access
                Toast.makeText(this,"Konumunuz için GPS açın",Toast.LENGTH_LONG).show()
                SimpleLocation.openSettings(this)
            }else
            {
                if(ContextCompat.checkSelfPermission(this,android.Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED)
                {
                    ActivityCompat.requestPermissions(this,
                            arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),60)

                }else
                {
                    location = SimpleLocation(this)
                    lat = location?.getLatitude()?.formatla(7)?.replace(",",".")
                    lng = location?.getLongitude()?.formatla(7)?.replace(",",".")
                    MySingleton.getInstance(this)?.addToRequestQueue(getCoordinates("$lat,$lng"))
                }
            }



        }else
        {
            MySingleton.getInstance(this)?.addToRequestQueue(getCoordinates(address))
        }


    }

    fun getTemperature():JsonObjectRequest
    {
        val temperatureUrl = "https://api.darksky.net/forecast/fed4be1508483a3f0409b31be96eebbb/$lat,$lng?lang=tr"
        var getTemperature = JsonObjectRequest(Request.Method.GET,temperatureUrl,
                null,object:Response.Listener<JSONObject>{
            override fun onResponse(response: JSONObject?) {
                val timeZone = response?.getString("timezone")
                val currently = response?.getJSONObject("currently")
                val temperature = currently?.getString("temperature")?.toDouble()
                val icon = currently?.getString("icon")
                var iconImg = when(icon)
                {
                    "clear-day" -> R.drawable.icon_01
                    "clear-night" -> R.drawable.icon_01
                    "rain" -> R.drawable.icon_09
                    "snow" -> R.drawable.icon_13
                    "sleet" -> R.drawable.icon_13
                    "strong-wind" -> R.drawable.icon_04
                    "cloudy" -> R.drawable.icon_04
                    "partly-cloudy-day" -> R.drawable.icon_03
                    "partly-cloudy-night" -> R.drawable.icon_03
                    "hail" -> R.drawable.icon_50
                    "fog" -> R.drawable.icon_50
                    else -> R.drawable.icon_01
                }
                tvSicaklik.text = "${fahrenheitToCelsius(temperature).formatla(2)}"
                tvAciklama.text = currently?.getString("summary")

                imgWeather.setImageResource(iconImg)
                var time:Long? = currently?.getLong("time")
                var dateFormat = SimpleDateFormat("EEEE MMMM yyyy HH:mm:ss",Locale("tr"))
                dateFormat.timeZone = TimeZone.getTimeZone(timeZone)
                val formatted_date = dateFormat.format(Date(time!! * 1000L))
                val index = formatted_date.indexOf(Calendar.getInstance().get(Calendar.YEAR).toString()) + 5
                var hour = formatted_date.substring(index,index+2).toInt()
                if(hour > 17 || hour < 6)
                {
                    mainLayout.setBackgroundResource(R.drawable.gece)
                    for(i in 0 until mainLayout.childCount)
                    {
                        var item = mainLayout.getChildAt(i)
                        if(item is TextView )
                        {
                            item.setTextColor(resources.getColor(R.color.colorAccent))
                        }
                    }
                }else
                {
                    mainLayout.setBackgroundResource(R.drawable.bg)
                    for(i in 0 until mainLayout.childCount)
                    {
                        var item = mainLayout.getChildAt(i)
                        if(item is TextView )
                        {
                            item.setTextColor(resources.getColor(android.R.color.black))
                        }
                    }
                }
                tvTarih.text = formatted_date
                showAll()
            }

        },object:Response.ErrorListener{
            override fun onErrorResponse(error: VolleyError?) {
                Toast.makeText(this@MainActivity,"Bir hata oldu",Toast.LENGTH_LONG).show()
            }

        })

        return getTemperature
    }

    fun getCoordinates(address:String):JsonObjectRequest {
        var addressEncoded = URLEncoder.encode(address, "UTF-8")
        val geoUrl = "https://maps.googleapis.com/maps/api/geocode/json?address=$addressEncoded&key=AIzaSyB9iaMf5pc758LI24rj_MoC0PBGjh5khIc"

        val request = JsonObjectRequest(Request.Method.GET, geoUrl, null, object : Response.Listener<JSONObject> {

            override fun onResponse(response: JSONObject?) {

                var result = response?.getJSONArray("results")

                tvSehir.text = result?.getJSONObject(0)?.getString("formatted_address")
                val geometry = result?.getJSONObject(0)?.getJSONObject("geometry")

                val location = geometry?.getJSONObject("location")

                lat = location?.getString("lat")
                lng = location?.getString("lng")

                MySingleton.getInstance(this@MainActivity)?.addToRequestQueue(getTemperature())
            }

        }, object : Response.ErrorListener {
            override fun onErrorResponse(error: VolleyError?) {
                Toast.makeText(this@MainActivity, "Bir hata oldu", Toast.LENGTH_LONG).show()
            }

        })


        return request
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        if(requestCode == 60)
        {
            if(grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
                location = SimpleLocation(this)
                lat = location?.getLatitude()?.formatla(7)?.replace(",",".")
                lng = location?.getLongitude()?.formatla(7)?.replace(",",".")
                MySingleton.getInstance(this)?.addToRequestQueue(getCoordinates("$lat,$lng"))
            }else
            {
                spArama.setSelection(0)
                Toast.makeText(this,"LAN IZINI VERSENE AQ",Toast.LENGTH_LONG).show()
            }
        }
    }
}
