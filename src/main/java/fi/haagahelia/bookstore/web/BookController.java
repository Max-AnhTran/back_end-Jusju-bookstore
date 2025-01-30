package fi.haagahelia.bookstore.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import fi.haagahelia.bookstore.domain.Book;
import fi.haagahelia.bookstore.domain.BookRepository;

@Controller
public class BookController {
	private BookRepository repository;

	// constructor injection. Can only be one constructor then.
	public BookController(BookRepository repository) {
		this.repository = repository;
	}

	@RequestMapping(value = { "/", "/Booklist" })
	public String bookList(Model model) {
		model.addAttribute("Books", repository.findAll());
		return "bookList";
	}

	@GetMapping("/index")
	public String welcome(Model model) {
		model.addAttribute("book", new Book());
		return "book";
	}
}