package backend.programming.Bookstore;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import backend.programming.Bookstore.domain.AppUser;
import backend.programming.Bookstore.domain.AppUserRepository;
import backend.programming.Bookstore.domain.Book;
import backend.programming.Bookstore.domain.BookRepository;
import backend.programming.Bookstore.domain.Category;
import backend.programming.Bookstore.domain.CategoryRepository;

@SpringBootApplication
public class BookstoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(BookRepository repository, CategoryRepository crepository, AppUserRepository urepository) {
		return (args) -> {
			
			System.out.println("creating categories");
			crepository.save(new Category("Fantasy"));
			crepository.save(new Category("Romance"));
			crepository.save(new Category("Horror"));
			crepository.save(new Category("Crime"));
			
			System.out.println("creating books"); 
			repository.save(new Book("The Hobbit, or There and Back Again", "J.R.R. Tolkien", "0-618-00221-9", 1937, 22.99, crepository.findByName("Fantasy").get(0)));
			repository.save(new Book("The Fellowship of the Ring", "J.R.R. Tolkien", "0-618-00222-7", 1954, 23.00, crepository.findByName("Fantasy").get(0)));
			
			AppUser user1 = new AppUser("user", "$2a$10$rnG6I/vhAj5g6XU2XsqXw.i.GdCvelQNvoQ4.CQUVDs1/x28wSIMm", "USER");
			AppUser user2 = new AppUser("admin", "$2a$10$Sfm4PTBde25h5JUXQaWNHuiLKebrndSePJoeE1xIz5ozD5KBttw5i", "ADMIN");
			urepository.save(user1);
			urepository.save(user2);
			
			System.out.println("printing books");
			for (Book book : repository.findAll()) {
				System.out.println(book);
			}
		};
	}
}
