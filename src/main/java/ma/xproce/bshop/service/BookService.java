package ma.xproce.bshop.service;

import ma.xproce.bshop.dao.entities.Book;
import ma.xproce.bshop.dao.entities.Categorie;
import ma.xproce.bshop.dao.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookService implements BookManager{
    @Autowired
    public BookRepository bookRepository;
    @Override
    public Book addBook(Book book ) {
        return bookRepository.save(book);

    }

    @Override
    public List<Book> getallBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Book findById(Integer id) {
        return bookRepository.findById(id).get();
    }
    @Override
    public Book getBookById(Integer id) {
        return bookRepository.findById(id).get();
    }

    @Override
    public Boolean deleteBook(Integer id) {
        Optional<Book> optionalBook = bookRepository.findById(id);
        if (optionalBook.isPresent()) {
            bookRepository.deleteById(id);
            return true;
        } else {
            System.out.println("video not found");
            return false;
        }
    }

    @Override
    public Page<Book> getAllBooks(int page, int taille) {
        return bookRepository.findAll(PageRequest.of(page, taille));
    }

    @Override
    public Page<Book> searchBooks(String keyword,int page, int taille) {
        return bookRepository.findByNameContains(keyword,PageRequest.of(page, taille));
    }
    @Override
    public Book updateBook(Book book){
        if(book.getId() == null){
            return null;
        }
        else {
             return bookRepository.save(book);
        }
    }
    @Override
    public List<Book> getBooksById(List<Integer> books) {
        List<Book> books1 = new ArrayList<>();
        for (Integer id : books) {
            Book b = this.findById(id);
            if (b != null) {
                books1.add(b);
            }
        }
        return books1;

    }

}
