package ma.xproce.bshop.web;

import ma.xproce.bshop.dao.entities.Book;
import ma.xproce.bshop.dao.entities.Categorie;
import ma.xproce.bshop.dao.entities.Writer;
import ma.xproce.bshop.dao.repositories.CategorieRepository;
import ma.xproce.bshop.service.CategorieManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Base64;
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
    @GetMapping("/addCategorie")
    public String addCategorie() {
        return "AddCategorie";
    }
    @PostMapping("addCategorie")
    public String addCategoriePost(@RequestParam(name="name")String name
            ,@RequestParam("file") MultipartFile file,Model model){

        Categorie existingCategorie =categorieManager.getCategorieByName(name);
        if(existingCategorie==null){
            Categorie categorie=new Categorie();
            categorie.setName(name);
            String fileName = StringUtils.cleanPath(file.getOriginalFilename());
            if (fileName.contains("..")) {
                System.out.println("not a a valid file");
            }
            try {
                categorie.setImgP(Base64.getEncoder().encodeToString(file.getBytes()));
            } catch (IOException e) {
                e.printStackTrace();
            }
            categorieManager.addCategorie(categorie);

       }
        return "redirect:/Categories";
    }
    @GetMapping("/editCategorie")
    public String editCategorieAction(Model model, @RequestParam(name = "id") Integer id) {
        Categorie categorie= categorieManager.getCategorieId(id);
        if (categorie!=null) {
            model.addAttribute("CategorieTobeUpdated",  categorie);

            return "EditCategorie";
        } else {
            return "error";
        }
    }
    @PostMapping("editCategorie")
    public String editVCategorieePost(Model model ,@RequestParam(name="id") Integer id, @RequestParam(name="name") String name
            ,@RequestParam("file") MultipartFile file ) {

        Categorie categorie = categorieManager.getCategorieId(id);
        categorie.setId(id);
        categorie.setName(name);
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        if (fileName.contains("..")) {
            System.out.println("not a a valid file");
        }
        try {
            categorie.setImgP(Base64.getEncoder().encodeToString(file.getBytes()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        categorieManager.addCategorie(categorie);



        return "redirect:/Categories";
    }




}
