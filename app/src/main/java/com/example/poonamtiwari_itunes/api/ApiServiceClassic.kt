package com.example.poonamtiwari_itunes.api


import com.example.poonamtiwari_itunes.model.MusicModel

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiServiceClassic {
   // https://itunes.apple.com/search?term=pop&amp;media=music&amp;entity=song&amp;limit=50
    @GET("search")
    fun getClassicMusic(
       @Query("term") term:String ="classic",
       @Query(";media") media:String ="music",
       @Query(";entity") entity:String ="song",
       @Query(";limit") limit:Int =50):Call<MusicModel>

    @GET("search")
    fun getPopMusic(
        @Query("term") term:String ="pop",
        @Query(";media") media:String ="music",
        @Query(";entity") entity:String ="song",
        @Query(";limit") limit:Int =50):Call<MusicModel>

    @GET("search")
    fun getRockMusic(
        @Query("term") term:String ="rock",
        @Query(";media") media:String ="music",
        @Query(";entity") entity:String ="song",
        @Query(";limit") limit:Int =50):Call<MusicModel>

//    @GET("search?term=classic&amp;media=music&entity=song&limit=50")
//    fun getClassicSongs(
//    ): retrofit2.Call<ITunesResponse>
//
//    @GET("search?term=rock&amp;media=music&entity=song&limit=50")
//    fun getRockSongs(
//    ): retrofit2.Call<ITunesResponse>
//
//    @GET("search?term=pop&amp;media=music&entity=song&limit=50")
//    fun getPopSongs(


    companion object{
        private var instance : Retrofit?=null
        fun createRetrofit() :Retrofit{
            if(instance==null){
                instance=Retrofit.Builder()
                    .baseUrl("https://itunes.apple.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return instance!!
        }
    }
}