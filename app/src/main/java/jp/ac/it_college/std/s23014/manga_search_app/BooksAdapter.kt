package jp.ac.it_college.std.s23014.manga_search_app


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
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
        // 画像の表示処理をここに追加
    }

    override fun getItemCount() = books.size

    fun updateBooks(newBooks: List<Book>) {
        books = newBooks
        notifyDataSetChanged()
    }
}
