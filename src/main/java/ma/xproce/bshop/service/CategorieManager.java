package ma.xproce.bshop.service;

import ma.xproce.bshop.dao.entities.Book;
import ma.xproce.bshop.dao.entities.Categorie;
import ma.xproce.bshop.dao.entities.Writer;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CategorieManager {

    public Categorie addCategorie(Categorie categorie);
    public List<Categorie> getallCategories();
    public Categorie findById(Integer id);
    public List<Categorie> getCategorieById(List<Integer>categories);
    public Categorie updateCategorie(Categorie categorie);
    public Categorie getCategorieByName(String CategorieName);
    public Categorie getCategorieId(Integer id);





}
