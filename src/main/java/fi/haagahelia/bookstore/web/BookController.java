package fi.haagahelia.bookstore.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import fi.haagahelia.bookstore.domain.Book;
import fi.haagahelia.bookstore.domain.BookRepository;
import fi.haagahelia.bookstore.domain.CategoryRepository;

@Controller
public class BookController {
	@Autowired
	private BookRepository repository;

	@Autowired
    private CategoryRepository categoryRepository;

	@RequestMapping(value="/login")
	public String login() {
		return "login";
	}    

	@GetMapping({ "/", "/booklist" })
	public String bookList(Model model) {
		model.addAttribute("books", repository.findAll());
		return "booklist";
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@GetMapping("/delete/{id}")
	public String deleteStudent(@PathVariable("id") Long id, Model model) {
		repository.deleteById(id);
		return "redirect:../booklist";
	}

	@GetMapping("/addbook")
	public String addBook(Model model) {
		model.addAttribute("book", new Book());
        model.addAttribute("categories", categoryRepository.findAll());
        return "addBook";
	}

	@PostMapping("/addbook")
	public String newBookList(Book book, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			return "addBook";
		}
		repository.save(book);
		model.addAttribute("books", repository.findAll());
		return "booklist";
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@GetMapping("/edit/{id}")
	public String showEditForm(@PathVariable Long id, Model model) {
		Book book = repository.findById(id).orElseThrow(() -> new RuntimeException("Book not found"));
		model.addAttribute("book", book);
		model.addAttribute("categories", categoryRepository.findAll());
		return "editBook"; 
	}

	@PostMapping("/edit/{id}")
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
		book.setCategory(updatedBook.getCategory());
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