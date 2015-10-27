package org.ramon.dao;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

import org.ramon.model.Book;
import org.springframework.stereotype.Service;
@Service
public class BooksDaoImpl implements BooksDao {
    @Getter
    @Setter
    private List<Book> library;

    public BooksDaoImpl() {
        library = new ArrayList<Book>();
    }

    @Override
    public List<Book> getListByAuthor(String authorName) {

        // Get List of books with the name of the author
        List<Book> booksByAuthor = new ArrayList<Book>();
        for (int i = 0; i < library.size(); i++) {
            if (library.get(i).getAuthor().getName().equals(authorName)) {
                booksByAuthor.add(library.get(i));
            }
        }
        return booksByAuthor;
    }

    @Override
    public Book deleteBook(String idBook) {
        Book bookToDelete=null;
        for (int i = 0; i < library.size(); i++) {
            if (library.get(i).getId().equals(idBook)) {
                bookToDelete=library.get(i);
                library.remove(i);
            }
        }
        return bookToDelete;
    }

    @Override
    public Book getBook(String idBook) {
        Book findBook = null;
        for (int i = 0; i < library.size(); i++) {
            if (library.get(i).getId().equals(idBook)) {
                findBook = library.get(i);
            }
        }
        return findBook;
    }

    @Override
    public void updateBook(Book book) {
        if (exist(book.getId())) {
            Book bookToFind = getBook(book.getId());
            deleteBook(bookToFind.getId());
            library.add(book);
        }
    }

    @Override
    public void addBook(Book b) {
        if (!exist(b.getId())) {
            library.add(b);
        }
    }

    @Override
    public List<Book> getAllBooks() {
        return library;
    }

    @Override
    public boolean exist(String idBook) {
        boolean exist = false;
        for (int i = 0; i < library.size(); i++) {
            if (library.get(i).getId().equals(idBook)) {
                exist = true;
            }
        }
        return exist;
    }

}
