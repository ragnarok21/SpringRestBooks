package org.ramon.controller;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NOT_ACCEPTABLE;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

import org.ramon.model.Author;
import org.ramon.model.Book;
import org.ramon.services.BooksServicesImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

@RestController
@RequestMapping("/BooksRestAnnotations")
public class BooksController {
    @Getter
    @Setter
    //@Autowired
    private BooksServicesImpl services=new BooksServicesImpl();
    
    Gson gson = new Gson();
    Book book = new Book("1","asd","sdas", new Author("Asd","asdasd"));

    @RequestMapping(value = "/get/{idBook}", method = GET, produces = { APPLICATION_JSON_VALUE })
    @ResponseBody
    public ResponseEntity<Book> getBook(@PathVariable("idBook") String idBook) {
        if (!this.services.getAllBooks().isEmpty()) {
            if (services.exist(idBook)) {
                return new ResponseEntity<Book>(this.services.getBook(idBook),
                        HttpStatus.OK);
            } else {
                return new ResponseEntity<Book>(NOT_FOUND);
            }

        } else {
            return new ResponseEntity<Book>(NOT_FOUND);
        }
    }

    @RequestMapping(value = "/listbyauthor/{authorName}", method = GET, produces = { APPLICATION_JSON_VALUE })
    @ResponseBody
    public List<Book> getListByAuthor(
            @PathVariable("authorName") String authorName) {
        return services.getListByAuthor(authorName);

    }

    @RequestMapping(value = "/list", method = GET, produces = { APPLICATION_JSON_VALUE })
    @ResponseBody
    public List<Book> getAllBooks() {
        return services.getAllBooks();
    }

    @RequestMapping(value = "/delete/{idBook}", method = DELETE, produces = { APPLICATION_JSON_VALUE })
    @ResponseBody
    public ResponseEntity<Book> deleteBook(@PathVariable("idBook") String idBook) {
            if (services.exist(idBook)) {
                Book book = services.getBook(idBook);
                services.deleteBook(idBook);
                return new ResponseEntity<Book>(book, OK);
            } else {
                return new ResponseEntity<Book>(NOT_FOUND);
            }

    }

    @RequestMapping(value = "/create", method = POST, produces = "application/json", consumes = "application/json")
    @ResponseBody
    public ResponseEntity<Book> createBook(@RequestBody Book book) {
        if (!services.exist(book.getId())) {
            this.services.addBook(book);
            return new ResponseEntity<Book>(book, CREATED);
        } else {
            return new ResponseEntity<Book>(NOT_ACCEPTABLE);
        }
    }

    @RequestMapping(value = "/update", method = PUT, produces = "application/json", consumes = "application/json")
    @ResponseBody
    public ResponseEntity<Book> updateComputer(@RequestBody Book book) {
        if (services.exist(book.getId())) {
            this.services.updateBook(book);
            return new ResponseEntity<Book>(book, OK);
        } else {
            return new ResponseEntity<Book>(NOT_FOUND);
        }
    }
    @RequestMapping(value = "/greeting", method = GET, produces = "application/json")
    @ResponseBody
    public Book greeting(@RequestParam(value="name", defaultValue="World") String name) {
        return book;
    }
}