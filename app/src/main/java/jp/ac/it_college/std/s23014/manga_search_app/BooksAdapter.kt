package jp.ac.it_college.std.s23014.manga_search_app


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import jp.ac.it_college.std.s23014.manga_search_app.databinding.ItemBookBinding


class BooksAdapter(private var books: List<Book>) :
    RecyclerView.Adapter<BooksAdapter.BookViewHolder>() {

    inner class BookViewHolder(val binding: ItemBookBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val binding = ItemBookBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BookViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        val book = books[position]
        holder.binding.bookTitle.text = book.title
        holder.binding.bookAuthor.text = book.authors?.joinToString(", ") ?: "Unknown Author"

        // Glideを使用してサムネイル画像をロードするやつ
        Glide.with(holder.itemView.context)
            .load(book.imageLinks?.thumbnail)
            .into(holder.binding.bookImage)
    }


    override fun getItemCount() = books.size

    fun updateBooks(newBooks: List<Book>) {
        books = newBooks
        notifyDataSetChanged()
    }
}
