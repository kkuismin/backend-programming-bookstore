package backend.programming.Bookstore.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import backend.programming.Bookstore.domain.Book;
import backend.programming.Bookstore.domain.BookRepository;
import backend.programming.Bookstore.domain.CategoryRepository;

@Controller
public class BookController {
	
	@Autowired
	private BookRepository bookRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;

	@GetMapping("/index")
	public String indexPage() {
		return "index";
	}
	
	@RequestMapping(value = { "/booklist", "/" } )
	public String showBooklist(Model model) {
		System.out.println("getting books from db");
		model.addAttribute("books", bookRepository.findAll());
		return "booklist";
	}
	
	//RESTful service to get all books
	@GetMapping("/books")
	public @ResponseBody List<Book> bookListRest() {
		return (List<Book>) bookRepository.findAll();
	}
	
	//RESTful service to get book by id
	@GetMapping("/book/{id}")
	public @ResponseBody Optional<Book> findBookRest(@PathVariable("id") Long id) {
		return bookRepository.findById(id);
	}
	
	@PreAuthorize("hasAuthority('ADMIN')")
	@GetMapping("/addBook")
	public String addNewBook(Model model) {
		model.addAttribute("book", new Book());
		model.addAttribute("categories", categoryRepository.findAll());
		return "newBook";
	}
	
	@PreAuthorize("hasAuthority('ADMIN')")
	@PostMapping("saveBook")
	public String saveBook(Book book, Model model) {
		bookRepository.save(book);
		return "redirect:/booklist";
	}
	
	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String deleteBook(@PathVariable("id") Long id, Model model) {
	 bookRepository.deleteById(id);
	 return "redirect:../booklist";
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String editBook(@PathVariable("id") Long id, Model model) {
		model.addAttribute("book", bookRepository.findById(id));
		model.addAttribute("categories", categoryRepository.findAll());
		return "editpage";
	}
}
