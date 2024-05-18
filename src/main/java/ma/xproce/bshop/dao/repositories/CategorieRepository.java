package ma.xproce.bshop.dao.repositories;

import ma.xproce.bshop.dao.entities.Book;
import ma.xproce.bshop.dao.entities.Categorie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategorieRepository extends JpaRepository<Categorie,Integer> {

}
