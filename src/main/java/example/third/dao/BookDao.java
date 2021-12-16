package example.third.dao;

import example.third.model.Book;

import java.util.List;

public interface BookDao {

    void addBook(Book book);
    void update(Book book);
    void removeBook(int id);
    Book getBookById(int id);
    List<Book> books();
    Book getSearchBook(String title);
    List<Book> getSearchBooks(String title);
}
