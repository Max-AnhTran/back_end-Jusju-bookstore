package fi.haagahelia.bookstore.web;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import fi.haagahelia.bookstore.domain.BookRepository;
import fi.haagahelia.bookstore.domain.Book;
// import org.springframework.web.bind.annotation.RequestParam;


@Controller
// Nếu thay @Controller bằng @RestController thì sẽ không cần phải viết @ResponseBody ở mỗi hàm
public class BookRestController {
    private BookRepository bookRepository; 
		
    // Constructor Injection 
    public BookRestController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }
    
    // RESTful service to get all books
    @RequestMapping(value="/books", method = RequestMethod.GET)
    public @ResponseBody List<Book> bookListRest() {	
        return (List<Book>) bookRepository.findAll();
    }    

    // RESTful service to get book by id
    @RequestMapping(value="/books/{id}", method = RequestMethod.GET)
    public @ResponseBody Optional<Book> findBookRest(@PathVariable("id") Long bookId) {	
        return bookRepository.findById(bookId);
    }       
    
   // RESTful service to save new book
    @RequestMapping(value="/books", method = RequestMethod.POST)
    public @ResponseBody Book saveNewBookRest(@RequestBody Book book) {
        book.setId(null);
        return bookRepository.save(book);
    }    

    
    // TODO RESTful service to delete book etc
    @RequestMapping(value = "/books/{id}", method = RequestMethod.DELETE)
    public @ResponseBody String deleteBookRest(@PathVariable("id") Long bookId) {
        bookRepository.deleteById(bookId);
        return "Deleted book with id: " + bookId;
    }

    // Update book
    @RequestMapping(value = "/books/{id}", method = RequestMethod.PUT)  
    public @ResponseBody Book updateBookRest(@PathVariable("id") Long bookId, @RequestBody Book book) {
        return bookRepository.save(book);
    }
}
