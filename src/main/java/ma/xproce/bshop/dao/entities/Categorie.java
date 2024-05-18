package ma.xproce.bshop.dao.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Collection;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Categorie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    @Lob
    @Column(columnDefinition = "MEDIUMBLOB")
    private String imgP;

    @ManyToMany(fetch = FetchType.EAGER)
    private Collection<Book> books =new ArrayList<>();

}
