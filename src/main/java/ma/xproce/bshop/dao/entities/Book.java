package ma.xproce.bshop.dao.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String description;
    @Lob
    @Column(columnDefinition = "MEDIUMBLOB")
    private String imgP;



    @ManyToMany(mappedBy = "books", cascade = CascadeType.MERGE)
    private Collection<Categorie> categories=new ArrayList<>();
    @ManyToOne
    private Writer writer ;






}
