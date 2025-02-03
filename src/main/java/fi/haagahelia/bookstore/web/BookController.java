package fi.haagahelia.bookstore.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import fi.haagahelia.bookstore.domain.Book;
import fi.haagahelia.bookstore.domain.BookRepository;

@Controller
public class BookController {
	private BookRepository repository;

	// constructor injection. Can only be one constructor then.
	public BookController(BookRepository repository) {
		this.repository = repository;
	}

	@RequestMapping(value = { "/", "/booklist" })
	public String bookList(Model model) {
		model.addAttribute("books", repository.findAll());
		return "booklist";
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String deleteStudent(@PathVariable("id") Long id, Model model) {
		repository.deleteById(id);
		return "redirect:../booklist";
	}

	@RequestMapping(value = "/addbook", method = RequestMethod.GET)
	public String addBook(Model model) {
		model.addAttribute("book", new Book());
		return "addBook";
	}

	@RequestMapping(value = "/addbook", method = RequestMethod.POST)
	public String newBookList(Book book, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			return "addBook";
		}

		repository.save(book);
		model.addAttribute("books", repository.findAll());
		return "booklist";
	}

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String showEditForm(@PathVariable Long id, Model model) {
		Book book = repository.findById(id).orElseThrow(() -> new RuntimeException("Book not found"));
		model.addAttribute("book", book);
		return "editBook"; 
	}

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
	public String updateBook(@PathVariable Long id, @ModelAttribute Book updatedBook, BindingResult bindingResult,
			Model model) {
		if (bindingResult.hasErrors()) {
			return "editBook";
		}

		Book book = repository.findById(id).orElseThrow(() -> new RuntimeException("Book not found"));
		book.setTitle(updatedBook.getTitle());
		book.setAuthor(updatedBook.getAuthor());
		book.setIsbn(updatedBook.getIsbn());
		book.setPublicationYear(updatedBook.getPublicationYear());
		book.setPrice(updatedBook.getPrice());
		repository.save(book);

		model.addAttribute("books", repository.findAll());
		return "booklist";
	}

	@GetMapping("/index")
	public String welcome(Model model) {
		model.addAttribute("book", new Book());
		return "book";
	}
}