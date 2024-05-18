package ma.xproce.bshop;

import ma.xproce.bshop.dao.entities.Book;
import ma.xproce.bshop.dao.entities.Categorie;
import ma.xproce.bshop.dao.entities.Writer;
import ma.xproce.bshop.dao.repositories.BookRepository;
import ma.xproce.bshop.dao.repositories.WriterRepository;
import ma.xproce.bshop.service.BookManager;
import ma.xproce.bshop.service.CategorieManager;
import ma.xproce.bshop.service.WriterManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@SpringBootApplication
public class BshopApplication {
	@Autowired
	public BookRepository bookRepository;
	@Autowired
	public WriterRepository writerRepository;

	@Autowired
	public WriterManager writerManager;
	@Autowired
	public BookManager bookManager;
	@Autowired
	public CategorieManager categorieManager;


	public static void main(String[] args) {
		SpringApplication.run(BshopApplication.class, args);
	}
	@Bean
	CommandLineRunner start() {
		return args -> {



/*
			Book b1=new Book();
			Categorie categorie1 =new Categorie();
			Categorie categorie2=new Categorie();

			b1.setName("Nos etoiles contraires");
			b1.setType("romance");
			b1.setDescription("hsxjhjqshxiq");
			bookManager.addBook(b1);
			Collection<Book> books = new ArrayList<>();
			books.add(b1);


			categorie1.setName("Sport");
			categorie2.setName("Aventure");
			categorie2.setBooks(List.of(b1));
			categorie1.setBooks(List.of(b1));

			categorieManager.addCategorie(categorie1);
			categorieManager.addCategorie(categorie2);
			b1.setCategories(List.of(categorie1,categorie2));

			bookManager.addBook(b1);

			Writer w1=new Writer();
			w1.setName("John Green");
			writerManager.addWriter(w1);






			w1.setBooks(books);
			writerManager.addWriter(w1);
			b1.setWriter(w1);
			bookManager.addBook(b1);


*/









		};
	}
}
