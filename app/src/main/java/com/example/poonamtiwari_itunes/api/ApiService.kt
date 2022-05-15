package com.example.poonamtiwari_itunes.api




import com.example.poonamtiwari_itunes.model.RockMusicModel
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    //https://itunes.apple.com/search?term=rock&amp;media=music&amp;entity=song&amp;limit=50
//https://itunes.apple.com/search?term=rock&amp;media=music&amp;entity=song&amp;limit=50
    // https://randomuser.me/api/
    // https://randomuser.me/api/?result='results parameter'&gender=
    @GET("search")
    fun getAllSongs(
       // https://itunes.apple.com/search?term=pop&amp;media=music&amp;entity=song&amp;limit=50
       // https://itunes.apple.com/search?term=rock&amp;media=music&amp;entity=song&amp;limit=50
        @Query("term") term: String = "pop",
        @Query("media") media: String ="music",
        @Query("entity") entity: String = "song",
        @Query("limit") limit: Int = 50
    ): Call<RockMusicModel>

    // create our instance of Retrofit
    // static
    companion object {
        private var instance: Retrofit? = null

        // https://randomuser.me/api/
        // https://randomuser.me/json/
        // https://randomuser.me/user/
        // GraphQL
        fun createRetrofit(): Retrofit {
            if (instance == null) {
                instance =
                    Retrofit.Builder()
                        .baseUrl("https://itunes.apple.com/")
                        .addConverterFactory(GsonConverterFactory.create())

                        .build()
            }

            return instance!! // !! -> this will NOT be null here
        }
    }
}