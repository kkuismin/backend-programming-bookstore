package backend.programming.Bookstore;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import backend.programming.Bookstore.domain.Book;
import backend.programming.Bookstore.domain.BookRepository;

@SpringBootApplication
public class BookstoreApplication {

	@Autowired
	private BookRepository bookRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(BookRepository repository) {
		return (args) -> {
			// Your code...add some demo data to db
			System.out.println("creating books"); 
			repository.save(new Book("The Hobbit, or There and Back Again", "J.R.R. Tolkien", "0-618-00221-9", 1937, 22.99));
			repository.save(new Book("The Fellowship of the Ring", "J.R.R. Tolkien", "0-618-00222-7", 1954, 23.00));
			System.out.println("printing books");
			
			for (Book book : bookRepository.findAll()) {
				System.out.println(book);
			}
		};
	}
}
