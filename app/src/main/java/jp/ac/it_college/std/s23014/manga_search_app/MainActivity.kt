package jp.ac.it_college.std.s23014.manga_search_app

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import jp.ac.it_college.std.s23014.manga_search_app.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val booksAdapter = BooksAdapter(emptyList())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.booksRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.booksRecyclerView.adapter = booksAdapter

        binding.searchButton.setOnClickListener {
            val searchTerm = binding.searchInput.text.toString()
            searchBooks(searchTerm)
        }
    }

    private fun searchBooks(query: String) {
        RetrofitClient.apiService.searchBooks(query).enqueue(object : Callback<BooksResponse> {
            override fun onResponse(call: Call<BooksResponse>, response: Response<BooksResponse>) {
                if (response.isSuccessful) {
                    val books = response.body()?.items?.map {
                        Book(
                            title = it.volumeInfo.title,
                            authors = it.volumeInfo.authors,
                            imageLinks = it.volumeInfo.imageLinks
                        )
                    } ?: emptyList()
                    booksAdapter.updateBooks(books)
                } else {
                    Toast.makeText(this@MainActivity, "Error: ${response.code()}", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<BooksResponse>, t: Throwable) {
                Toast.makeText(this@MainActivity, "Failure: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}