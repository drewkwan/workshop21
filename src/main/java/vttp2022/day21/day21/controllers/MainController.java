package vttp2022.day21.day21.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonArrayBuilder;
import vttp2022.day21.day21.models.Book;
import vttp2022.day21.day21.models.Query;
import vttp2022.day21.day21.repositories.BookRepository;

@Controller
public class MainController {

    @Autowired
    private BookRepository bookRepo;

    private static final Logger logger = LoggerFactory.getLogger(MainController.class);

    @GetMapping("/") 
    public String home(Model model) {
        model.addAttribute("query", new Query());
        return "index";
    }

    @PostMapping("/searchResults")
    public String searchResults(@ModelAttribute Query query, Model model, @RequestParam String title, @RequestParam int limit) {
        
        logger.info(query.getTitle());
        System.out.println(query.getLimit());
         //Query the db for books
         title =query.getTitle();
         limit = query.getLimit();
         title = '%' + title + '%';
         List<Book> books = bookRepo.getBooksByTitle(title, limit);
        
         // Build the result
         JsonArrayBuilder arrBuilder = Json.createArrayBuilder();
         for (Book b: books) {
             arrBuilder.add(b.toJSON());
         }
         JsonArray result = arrBuilder.build();

         model.addAttribute("books", books);
        return "result";

    }

}
