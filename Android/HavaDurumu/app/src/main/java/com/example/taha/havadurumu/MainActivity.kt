package com.example.taha.havadurumu

import android.opengl.Visibility
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.example.taha.havadurumu.Library.MySingleton
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject
import java.net.URLEncoder
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.*

class MainActivity : AppCompatActivity() {

    var formattedAddress: String? = null
    var lat:String? = null
    var lng:String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var address = "New York"
        var addressEncoded = URLEncoder.encode(address,"UTF-8")
        val geoUrl = "https://maps.googleapis.com/maps/api/geocode/json?address=$addressEncoded&key=AIzaSyB9iaMf5pc758LI24rj_MoC0PBGjh5khIc"

        val request = JsonObjectRequest(Request.Method.GET,geoUrl,null,object:Response.Listener<JSONObject>{

            override fun onResponse(response: JSONObject?) {

                var result = response?.getJSONArray("results")

                tvSehir.text = result?.getJSONObject(0)?.getString("formatted_address")
                val geometry = result?.getJSONObject(0)?.getJSONObject("geometry")

                val location = geometry?.getJSONObject("location")

                lat = location?.getString("lat")
                lng = location?.getString("lng")

                val temperatureUrl = "https://api.darksky.net/forecast/fed4be1508483a3f0409b31be96eebbb/$lat,$lng?lang=tr"

                var getTemperature = JsonObjectRequest(Request.Method.GET,temperatureUrl,
                        null,object:Response.Listener<JSONObject>{
                    override fun onResponse(response: JSONObject?) {
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
                        tvAciklama.text = currently?.getString("summary") +  "\n $icon"

                        imgWeather.setImageResource(iconImg)

                        var dateFormat = SimpleDateFormat("EEEE MMMM yyyy HH:mm:ss",Locale("tr"))
                        tvTarih.text = dateFormat.format(Calendar.getInstance().time)

                    }

                },object:Response.ErrorListener{
                    override fun onErrorResponse(error: VolleyError?) {

                    }

                })


                MySingleton.getInstance(this@MainActivity)?.addToRequestQueue(getTemperature)
            }

        },object:Response.ErrorListener{
            override fun onErrorResponse(error: VolleyError?) {

            }

        })

        MySingleton.getInstance(this)?.addToRequestQueue(request)





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
    fun fahrenheitToCelsius(fahrenheit:Double?):Double
    {
        var celsius = (fahrenheit!! - 32) * 5/9

        return celsius
    }
    fun Double.formatla(kacTaneBasamak:Int) = java.lang.String.format("%.${kacTaneBasamak}f",this)
}
