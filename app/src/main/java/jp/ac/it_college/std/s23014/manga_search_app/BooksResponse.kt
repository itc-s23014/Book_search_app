package jp.ac.it_college.std.s23014.manga_search_app

data class Book(
    val title: String,
    val authors: List<String>?,
    val imageLinks: ImageLinks?
)

data class ImageLinks(
    val thumbnail: String?
)

data class BooksResponse(
    val items: List<Item>
)

data class Item(
    val volumeInfo: Book
)