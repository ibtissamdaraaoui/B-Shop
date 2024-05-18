package ma.xproce.bshop.dao.repositories;

import ma.xproce.bshop.dao.entities.Writer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WriterRepository extends JpaRepository<Writer,Integer> {
    Writer findByName(String name);
    List<Writer> findAllById(Iterable<Integer> list);
}
