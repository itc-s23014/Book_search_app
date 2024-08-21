package jp.ac.it_college.std.s23014.manga_search_app


import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.Call

interface GoogleBooksApiService {
    @GET("volumes")
    fun searchBooks(@Query("q") query: String): Call<BooksResponse>
}