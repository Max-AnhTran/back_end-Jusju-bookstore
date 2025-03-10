package fi.haagahelia.bookstore;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import fi.haagahelia.bookstore.domain.BookRepository;
import fi.haagahelia.bookstore.domain.Book;
import fi.haagahelia.bookstore.domain.Category;
import fi.haagahelia.bookstore.domain.CategoryRepository;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class BookRepositoryTest {
    @Autowired
    private BookRepository repository;

    @Autowired
    private CategoryRepository categoryRepository;

    @BeforeEach
    public void setUp() {
        // Set up test data
        Category category = new Category("Fiction");
        categoryRepository.save(category);
        Book book = new Book("The Bad Homie Guy", "F. Scott Fitzgerald", 1925, "9780743273565", 15, category);
        repository.save(book);
    }

    @Test
    public void findByIsbnShouldReturnBook() {
        List<Book> books = repository.findByIsbn("9780743273565");

        assertThat(books).hasSize(1);
        assertThat(books.get(0).getTitle()).isEqualTo("The Bad Homie Guy");
    }

    @Test
    public void findByTitleShouldReturnBook() {
        List<Book> books = repository.findByTitle("The Bad Homie Guy");

        assertThat(books).hasSize(1);
        assertThat(books.get(0).getIsbn()).isEqualTo("9780743273565");
    }

    @Test
    public void createNewStudent() {
    	Category category = new Category("Non-Fiction");
        categoryRepository.save(category);
        Book book = new Book("The Gold Guy", "F. Dan Steel", 1975, "9780654373565", 15, category);
        repository.save(book);
        List<Book> newBooks = repository.findByTitle("The Bad Homie Guy");
        assertThat(newBooks).hasSize(1);
    }    
    @Test
    public void deleteNewStudent() {
    	List<Book> newBooks = repository.findByTitle("The Bad Homie Guy");
    	assertThat(newBooks).hasSize(1);
    	repository.delete(newBooks.get(0));
    	newBooks = repository.findByTitle("The Bad Homie Guy");
    	assertThat(newBooks).hasSize(0);
     }
}