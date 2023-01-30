package backend.programming.Bookstore.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import backend.programming.Bookstore.domain.Book;

@Controller
public class BookController {

	@GetMapping("index")
	public String indexPage(Model model) {
		model.addAttribute("book", new Book());
		return "index";
	}
}
