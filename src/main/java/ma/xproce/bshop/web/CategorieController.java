package ma.xproce.bshop.web;

import ma.xproce.bshop.dao.entities.Book;
import ma.xproce.bshop.dao.entities.Categorie;
import ma.xproce.bshop.dao.repositories.CategorieRepository;
import ma.xproce.bshop.service.CategorieManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class CategorieController {
    @Autowired
    private CategorieManager categorieManager;
    @Autowired
    private CategorieRepository categorieRepository;

    @GetMapping("/Categories")
    public String getCategories(Model model){
        List<Categorie> categories =categorieManager.getallCategories();
        model.addAttribute("listeCategories",categories);
        return "Categories";
    }
    @GetMapping("/detailCategorie")
    public  String getDetailBook (@RequestParam(name="id") Integer id , Model model){
        Categorie categorie= categorieManager.findById(id);
        model.addAttribute("detailcategorie",categorie);
        return "detailCategorie";
    }


}
