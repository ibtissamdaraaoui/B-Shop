package ma.xproce.bshop.dao.repositories;

import jakarta.transaction.Transactional;
import ma.xproce.bshop.dao.entities.Book;
import ma.xproce.bshop.dao.entities.Categorie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

@Transactional
public interface BookRepository extends JpaRepository<Book,Integer> {
    Page<Book> findByNameContains(String name, Pageable pageable);
}
