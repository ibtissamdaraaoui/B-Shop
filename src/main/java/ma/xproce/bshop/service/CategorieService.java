package ma.xproce.bshop.service;

import ma.xproce.bshop.dao.entities.Book;
import ma.xproce.bshop.dao.entities.Categorie;
import ma.xproce.bshop.dao.repositories.BookRepository;
import ma.xproce.bshop.dao.repositories.CategorieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class CategorieService implements CategorieManager{
    @Autowired
    public CategorieRepository categorieRepository;
    @Override
    public Categorie addCategorie(Categorie categorie) {
        return categorieRepository.save(categorie);
    }

    @Override
    public List<Categorie> getallCategories() {
        return categorieRepository.findAll();
    }

    @Override
    public Categorie findById(Integer id) {
        return categorieRepository.findById(id).get();
    }

    @Override
    public List<Categorie> getCategorieById(List<Integer> categories) {
        List<Categorie> categories1 = new ArrayList<>();
        for (Integer id : categories) {
            Categorie categorie = this.findById(id);
            if (categorie != null) {
                categories1.add(categorie);
            }
        }
        return categories1;

    }

    @Override
    public Categorie updateCategorie(Categorie categorie) {
        if (categorieRepository.existsById(categorie.getId())) {
            return categorieRepository.save(categorie);
        } else {
            System.out.println("The categorie doesnt exist");
            return null;
        }
    }
    @Override
    public Categorie getCategorieByName(String CategorieName){
        return categorieRepository.findByName(CategorieName);
    }
    @Override
    public Categorie getCategorieId(Integer id){
        return categorieRepository.findById(id).orElse(null);
    }


}
