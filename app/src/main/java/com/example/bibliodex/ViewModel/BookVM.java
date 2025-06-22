package com.example.bibliodex.ViewModel;

    import android.content.Context;

    import com.example.bibliodex.Model.Book;
    import com.example.bibliodex.Storage.BookDAO;

    import java.io.Serializable;
    import java.util.ArrayList;

    /**
     * ViewModel class for managing a Book object and interacting with the BookDAO.
     * Provides getter and setter methods for Book properties and methods to perform CRUD operations.
     */
    public class BookVM implements Serializable {
        /** The Book instance managed by this ViewModel */
        private Book book;
        /** Data Access Object for Book persistence operations */
        private BookDAO bookDAO;

        /**
         * Constructor for BookVM.
         * Initializes the BookDAO and creates a new Book instance.
         * @param context Android context used for DAO initialization
         */
        public BookVM(Context context) {
            this.bookDAO = new BookDAO(context);
            this.book = new Book();
        }

        /**
         * Gets the book associated with this ViewModel.
         * @return the book
         */
        public Book getBook() {
            return book;
        }

        /**
         * Sets the book for this ViewModel.
         * @param book the book to set
         */
        public void setBook(Book book) {
            this.book = book;
        }

        /**
         * Gets the title of the book.
         * @return the book title
         */
        public String getTitle() {
            return book.getTitle();
        }

        /**
         * Sets the title of the book.
         * @param title the title to set
         */
        public void setTitle(String title) {
            book.setTitle(title);
        }

        /**
         * Gets the author of the book.
         * @return the book author
         */
        public String getAuthor() {
            return book.getAuthor();
        }

        /**
         * Sets the author of the book.
         * @param author the author to set
         */
        public void setAuthor(String author) {
            book.setAuthor(author);
        }

        /**
         * Gets the publication year of the book.
         * @return the publication year
         */
        public int getPublicationYear(){
            return book.getPublicationYear();
        }

        /**
         * Sets the publication year of the book.
         * @param publicationYear the year to set
         */
        public void setPublicationYear(int publicationYear) {
            book.setPublicationYear(publicationYear);
        }

        /**
         * Gets the rating of the book.
         * @return the rating
         */
        public int getRating() {
            return book.getRating();
        }

        /**
         * Sets the rating of the book.
         * @param rating the rating to set
         */
        public void setRating(int rating) {
            book.setRating(rating);
        }

        /**
         * Checks if the book is marked as read.
         * @return true if read, false otherwise
         */
        public boolean isRead() {
            return book.isRead();
        }

        /**
         * Sets the read status of the book.
         * @param read true if the book is read, false otherwise
         */
        public void setRead(boolean read) {
            book.setRead(read);
        }

        /**
         * Checks if the book is marked as favorite.
         * @return true if favorite, false otherwise
         */
        public boolean isFavorite() {
            return book.isFavorite();
        }

        /**
         * Sets the favorite status of the book.
         * @param favorite true if the book is favorite, false otherwise
         */
        public void setFavorite(boolean favorite) {
            book.setFavorite(favorite);
        }

        /**
         * Gets the current page of the book.
         * @return the current page
         */
        public int getPageActual() {
            return book.getPageActual();
        }

        /**
         * Sets the current page of the book.
         * @param pageActual the page to set
         */
        public void setPageActual(int pageActual) {
            book.setPageActual(pageActual);
        }

        /**
         * Gets the URI of the book cover.
         * @return the cover URI
         */
        public String getCoverUri(){
            return book.getCoverUri();
        }

        /**
         * Sets the URI of the book cover.
         * @param uri the URI to set
         */
        public void setCoverUri(String uri){
            book.setCoverUri(uri);
        }

        /**
         * Add the book using the Data Access Object.
         */
        public void addBook() {
            bookDAO.addBook(book);
        }

        /**
         * Updates the book in the Data Access Object.
         * @param index the index of the book to update
         */
        public void updateBook(int index) {
            bookDAO.updateBook(index, book);
        }

        /**
         * Removes the book from the Data Access Object.
         */
        public void removeBook() {
            bookDAO.removeBook(book);
        }

        /**
         * Gets the book at a specific index from the Data Access Object.
         * @param index the index of the book
         * @return the book at the specified index
         */
        public Book getBookAtIndex(int index) {
            return bookDAO.getBook(index);
        }

        /**
         * Gets all books from the Data Access Object.
         * @return an array of all books
         */
        public ArrayList<Book> getAllBooks() {
            return bookDAO.getAllBooks();
        }

        /**
         * Gets all favorite books from the Data Access Object.
         * @return an array of all favorite books
         */
        public ArrayList<Book> getAllFavoriteBooks() {
            return bookDAO.getAllFavorite();
        }

        /**
         * Gets all unread books from the Data Access Object.
         * @return an array of all unread books
         */
        public ArrayList<Book> getAllUnreadBooks() {
            return bookDAO.getAllInProgress();
        }

    }