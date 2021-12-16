package example.third.controller;

import example.third.model.Book;
import example.third.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class BookController {

    private BookService bookService;

    @Autowired(required = true)
    @Qualifier(value = "bookService")
    public void setBookService(BookService bookService) {
        this.bookService = bookService;
    }

    @RequestMapping(value = "books", method = RequestMethod.GET)
    public String books(Model model) {
        model.addAttribute("book", new Book());
        model.addAttribute("books", this.bookService.books());

        return "books";
    }

    @RequestMapping(value = "books/add", method = RequestMethod.POST)
    public String addBook(@ModelAttribute("book") Book book) {

        if(book.getId() == 0) {
            bookService.addBook(book);
        }
        else {
            bookService.update(book);
        }

        return "redirect:/books";
    }

    @RequestMapping("remove/{id}")
    public String removeBook(@PathVariable("id") int id) {
        bookService.removeBook(id);

        return "redirect:/books";
    }

    @RequestMapping("edit/{id}")
    public String editBook(@PathVariable("id") int id, Model model) {
        model.addAttribute("book", bookService.getBookById(id));
        model.addAttribute("books", bookService.books());

        return "books";
    }

    @RequestMapping("bookData/{id}")
    public String bookData(@PathVariable("id") int id, Model model) {
        model.addAttribute("book", bookService.getBookById(id));

        return "bookdata";
    }

    @PostMapping(value = "bookSearch")
    public String bookSearch(Model model, @RequestParam("description") String sourceText) {
        model.addAttribute("bookSearch", bookService.getSearchBook(sourceText));
        return "search";
    }

    @PostMapping("booksSearch")
    public String booksSearch(Model model, @RequestParam("titleBook") String titleBook) {
        model.addAttribute("booksSearch", bookService.getSearchBooks(titleBook));
        return "searchBooks";
    }
}
