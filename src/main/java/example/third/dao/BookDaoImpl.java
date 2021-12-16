package example.third.dao;

import example.third.model.Book;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Repository
public class BookDaoImpl implements BookDao {

    private static final Logger logger = LoggerFactory.getLogger(BookDaoImpl.class);
// создает сессии для создания соединения с базой данных
    private SessionFactory sessionFactory;

    @Override
    public void addBook(Book book) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(book); // сохранить
        logger.info("Book successfully saved. Book details: " + book);
    }

    @Override
    public void update(Book book) {
        Session session = sessionFactory.getCurrentSession();
        session.update(book); // обновить
        logger.info("Book successfully update. Book details: " + book);
    }

    @Override
    public void removeBook(int id) {
        Session session = sessionFactory.getCurrentSession();
        Book book = getBookById(id); // загрузить
        session.delete(book);
        logger.info("Book successfully removed. Book details: " + book);
    }

    @Override
    public Book getBookById(int id) {
        Session session = sessionFactory.getCurrentSession();
        Book book = session.load(Book.class, id);
        logger.info("Book successfully loaded. Book details: " + book);

        return book;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Book> books() {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Book> criteria = builder.createQuery(Book.class);
        Root<Book> contactRoot = criteria.from(Book.class);
        criteria.select(contactRoot);
        List<Book> books = session.createQuery(criteria).getResultList();

        return books;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    @Transactional
    public Book getSearchBook(String title) {
        for (Book book : books()) {
            if (book.getBookTitle().equals(title)) {
                logger.info("Найдена книга: " + book);
                return book;
            }
        }
        return null;
    }

    @Override
    public List<Book> getSearchBooks(String title) {
        List<Book> searchBooks = new ArrayList<>();
        for (Book book : books()) {
            if (book.getBookTitle().equals(title)) {
                logger.info("Найдена книга: " + book);
                searchBooks.add(book);
            }
        }
        return searchBooks;
    }
}
