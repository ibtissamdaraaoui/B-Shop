package ma.xproce.bshop.web;

import ma.xproce.bshop.dao.entities.Book;
import ma.xproce.bshop.dao.entities.Categorie;
import ma.xproce.bshop.dao.entities.Writer;
import ma.xproce.bshop.dao.repositories.BookRepository;
import ma.xproce.bshop.service.BookManager;
import ma.xproce.bshop.service.CategorieManager;
import ma.xproce.bshop.service.WriterManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Arrays;
import java.util.List;

@Controller

public class HomeController {

    @Autowired
    private BookManager bookManager;

    @Autowired
    private WriterManager writerManager;
    @Autowired
    private CategorieManager categorieManager;

    @GetMapping("/")
    public String index(Model model) {
        List<Writer> writers =writerManager.getallWriters();
        model.addAttribute("Writers",writers);
        List<Book> books =bookManager.getBooksById(List.of(59,61,63));
        model.addAttribute("Books",books);
        List<Categorie> categories = categorieManager.getCategorieById(List.of(25,29,32));
        model.addAttribute("Categ",categories);
        return "index";
    }

}
