package ma.xproce.bshop.service;

import ma.xproce.bshop.dao.entities.Book;
import ma.xproce.bshop.dao.entities.Categorie;
import org.springframework.data.domain.Page;

import java.util.List;

public interface BookManager {
    public Book addBook(Book book);
    public List<Book> getallBooks();
    public Boolean deleteBook(Integer id);
    public Book findById(Integer id);
    public Book getBookById(Integer id);

    public Page<Book> getAllBooks(int page, int taille);
    public Page<Book> searchBooks(String keyword,int page, int taille);
    public Book updateBook(Book book);
    public List<Book> getBooksById(List<Integer> books);

}
