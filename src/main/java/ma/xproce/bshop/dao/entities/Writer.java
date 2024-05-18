package ma.xproce.bshop.dao.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Writer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    @Lob
    @Column(columnDefinition = "MEDIUMBLOB")
    private String imgP;
    @OneToMany(mappedBy = "writer" , cascade = CascadeType.MERGE)
    private Collection<Book> books;

    @Override
    public  String toString(){
        return " name"+name;
    }
}
