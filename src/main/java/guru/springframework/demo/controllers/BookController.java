package guru.springframework.demo.controllers;

import guru.springframework.demo.repositories.BookRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BookController  {

    private BookRepository bookrepository;

    public BookController(BookRepository bookrepository) {
        this.bookrepository = bookrepository;
    }

    @RequestMapping("/books")
    public String getBooks(Model model){

        model.addAttribute("books", bookrepository.findAll());
        return "books";
    }
}
