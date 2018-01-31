package com.example.taha.burclar

import java.io.Serializable

/**
 * Created by Taha on 31-Jan-18.
 */
data class Burc(var burcAdi:String,var burcTarihi:String,var burcResmi:Int,
                var burcBilgi:String,var burcBuyukResim:Int):Serializable {

}


/* Serializable , activityler arasında geçiş yaparken veri alışverişi için gerekli */