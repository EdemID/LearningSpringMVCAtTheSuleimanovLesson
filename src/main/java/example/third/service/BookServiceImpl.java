package example.third.service;

import example.third.dao.BookDao;
import example.third.model.Book;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    private BookDao bookDao;

    public void setBookDao(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    @Override
    @Transactional
    public void addBook(Book book) {
        bookDao.addBook(book);
    }

    @Override
    @Transactional
    public void update(Book book) {
        bookDao.update(book);
    }

    @Override
    @Transactional
    public void removeBook(int id) {
        bookDao.removeBook(id);
    }

    @Override
    @Transactional
    public Book getBookById(int id) {
        return bookDao.getBookById(id);
    }

    @Override
    @Transactional
    public List<Book> books() {
        return bookDao.books();
    }

    @Override
    @Transactional
    public Book getSearchBook(String title) {
        return bookDao.getSearchBook(title);
    }

    @Override
    @Transactional
    public List<Book> getSearchBooks(String title) {
        return bookDao.getSearchBooks(title);
    }
}
