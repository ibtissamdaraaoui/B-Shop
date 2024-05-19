package ma.xproce.bshop.web;

import jdk.jfr.Category;
import lombok.Data;
import ma.xproce.bshop.dao.entities.Book;
import ma.xproce.bshop.dao.entities.Categorie;
import ma.xproce.bshop.dao.entities.Writer;
import ma.xproce.bshop.dao.repositories.BookRepository;
import ma.xproce.bshop.service.BookManager;
import ma.xproce.bshop.service.CategorieManager;
import ma.xproce.bshop.service.WriterManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

@Controller
public class BookController {
    @Autowired
    private BookManager bookManager;
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private WriterManager writerManager;
    @Autowired
    private CategorieManager categorieManager;





    @GetMapping("/BookList")
    public String listProduits(Model model,
                               @RequestParam(name = "page", defaultValue = "0") int page,
                               @RequestParam(name = "taille", defaultValue = "6") int taille,
                               @RequestParam(name = "search", defaultValue = "") String keyword
    ) {

        Page<Book> books = bookManager.searchBooks(keyword,page, taille);
        model.addAttribute("BookList",books.getContent());
        int[] pages = new int[books.getTotalPages()];
        for (int i = 0; i < pages.length; i++) {
            pages[i] = i;
        }
        model.addAttribute("pages", pages);
        model.addAttribute("currentPage", page);
        model.addAttribute("keyword", keyword);
        return "BookList";
    }
    @GetMapping("/detailBook")
    public  String getDetailBook (@RequestParam(name="id") Integer id ,Model model){
        Book book= bookManager.findById(id);
        model.addAttribute("detail",book);
        return "detailBook";
    }

    @GetMapping("/addBook")
    public String addBook(Model model){
        List<Categorie> categories = categorieManager.getallCategories();
        model.addAttribute("categories", categories);
        return "AddBook";
    }


    @PostMapping("addBook")
    public String addBookPost(Model model , @RequestParam("file") MultipartFile file
            ,@RequestParam(name="name") String name
            ,@RequestParam(name="description") String description
            , @RequestParam(name="writer") String writer
            ,@RequestParam("file1") MultipartFile file1
            ,@RequestParam(name="categories")List<Integer>categories)
    {
        Writer existingWriter = writerManager.getWriterByName(writer);
        if (existingWriter != null) {
            Book book = new Book();
            book.setName(name);
            book.setDescription(description);
            String fileName = StringUtils.cleanPath(file.getOriginalFilename());
            if (fileName.contains("..")) {
                System.out.println("not a a valid file");
            }
            try {
                book.setImgP(Base64.getEncoder().encodeToString(file.getBytes()));
            } catch (IOException e) {
                e.printStackTrace();
            }



            List<Categorie> selectedCategories = categorieManager.getCategorieById(categories);
            book.setCategories(selectedCategories);

            existingWriter.setBooks(List.of(book));
            book.setWriter(existingWriter);

            bookManager.addBook(book);


            for (Categorie category : selectedCategories) {
                category.getBooks().add(book);
                categorieManager.addCategorie(category);
            }

            return "redirect:/BookList?success";
            //model.addAttribute("errorMessage", "L'écrivain existe déjà dans la base de données.");
           // return "redirect:/addBook?error"; // Remplacer par le nom de la vue de la page d'erreur
        }
        else {
            Book book = new Book();
            book.setName(name);
            book.setDescription(description);
            String fileName = StringUtils.cleanPath(file.getOriginalFilename());
            if (fileName.contains("..")) {
                System.out.println("not a a valid file");
            }
            try {
                book.setImgP(Base64.getEncoder().encodeToString(file.getBytes()));
            } catch (IOException e) {
                e.printStackTrace();
            }


            Writer writer1 = new Writer();
            writer1.setName(writer);
            String fileName1 = StringUtils.cleanPath(file1.getOriginalFilename());
            if (fileName1.contains("..")) {
                System.out.println("not a a valid file");
            }
            try {
               writer1.setImgP(Base64.getEncoder().encodeToString(file1.getBytes()));
            } catch (IOException e) {
                e.printStackTrace();
            }

            List<Categorie> selectedCategories = categorieManager.getCategorieById(categories);
            book.setCategories(selectedCategories);

            writer1.setBooks(List.of(book));
            book.setWriter(writer1);

            writerManager.addWriter(writer1);
            bookManager.addBook(book);


            for (Categorie category : selectedCategories) {
                category.getBooks().add(book);
                categorieManager.addCategorie(category);
            }

            return "redirect:/BookList?success";
        }

    }

    @GetMapping("/deleteBook")
    public String deleteBookAction(@RequestParam(name = "id") Integer id) {
        if (bookManager.deleteBook(id)) {
            return "redirect:/BookList";
        } else {
            return "error";
        }
    }

    @GetMapping("/editBook")
    public String editBookAction(Model model, @RequestParam(name = "id") Integer id) {
        Book book= bookManager.getBookById(id);
        if (book!=null) {
            List<Categorie> allCategories = categorieManager.getallCategories();
            model.addAttribute("BookTobeUpdated",  book);
            model.addAttribute("allCategories", allCategories);
            return "EditBook";
        } else {
            return "error";
        }
    }
    @PostMapping("editBook")
    public String editVBookPost(Model model ,@RequestParam(name="id") Integer id, @RequestParam(name="name") String name
            ,@RequestParam(name="description") String description
            , @RequestParam(name="writer") String writer
            ,@RequestParam(name="categories")List<Integer> categories) {

        Book book = bookManager.getBookById(id);
        book.setId(id);
        book.setName(name);
        book.setDescription(description);
        List<Categorie> currentCategories = (List<Categorie>) book.getCategories();

        List<Categorie> selectedCategories = categorieManager.getCategorieById(categories);

        if (!book.getCategories().containsAll(selectedCategories)){
            book.setCategories(selectedCategories);
            for (Categorie category : selectedCategories) {
                category.getBooks().add(book);
                categorieManager.updateCategorie(category);
            }
        }
        Writer existingWriter = writerManager.getWriterByName(writer);
        if(existingWriter!=null){
        existingWriter.setId(existingWriter.getId());
        existingWriter.setName(writer);
        writerManager.addWriter(existingWriter);
            book.setWriter(existingWriter);
            bookManager.addBook(book);}
        else{
            Writer writer1 =new Writer();
            writer1.setName(writer);
            writerManager.addWriter(writer1);
            book.setWriter(writer1);
            bookManager.addBook(book);
        }


        return "redirect:/BookList";
    }



}
