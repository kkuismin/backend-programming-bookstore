package backend.programming.Bookstore;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import backend.programming.Bookstore.domain.Book;
import backend.programming.Bookstore.domain.BookRepository;
import backend.programming.Bookstore.domain.Category;

@DataJpaTest
public class BookRepositoryTest {

	@Autowired
	private BookRepository bookrepository;
	
	@Test
	public void findBooksByTitleShouldReturnBook() {
		List<Book> booklist = bookrepository.findByTitle("The Fellowship of the Ring");
		assertThat(booklist).hasSize(1);
		assertThat(booklist.get(0).getAuthor()).isEqualTo("J.R.R. Tolkien");
		
	}
	
	@Test
	public void createNewBook() {
		Book book = new Book("The Two Towers", "J.R.R. Tolkien", "0-618-00223-5", 1954, 23.00, new Category("Fantasy"));
		bookrepository.save(book);
		assertThat(book.getId()).isNotNull();
	}
	
	@Test
	public void deleteNewBook() {
		List<Book> booklist = bookrepository.findByTitle("The Fellowship of the Ring");
		Book book = booklist.get(0);
		bookrepository.delete(book);
		List<Book> newBooklist = bookrepository.findByTitle("The Fellowship of the Ring");
		assertThat(newBooklist).hasSize(0);
	}
}
