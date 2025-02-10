package fi.haagahelia.bookstore;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import fi.haagahelia.bookstore.domain.Book;
import fi.haagahelia.bookstore.domain.BookRepository;
import fi.haagahelia.bookstore.domain.Category;
import fi.haagahelia.bookstore.domain.CategoryRepository;

@Configuration
public class DatabaseLoader {

    @Bean
    public CommandLineRunner demo(BookRepository bookRepository, CategoryRepository categoryRepository) {
        return (args) -> {
            // Clear existing data
            bookRepository.deleteAll();
            categoryRepository.deleteAll();

            // Create and save categories
            Category category1 = new Category("Fiction");
            categoryRepository.save(category1);

            Category category2 = new Category("Non-Fiction");
            categoryRepository.save(category2);

            Category category3 = new Category("Science Fiction");
            categoryRepository.save(category3);

            // Save a few example books with categories
            bookRepository.save(new Book("The Great Gatsby", "F. Scott Fitzgerald", 1925, "9780743273565", 15, category1));
            bookRepository.save(new Book("To Kill a Mockingbird", "Harper Lee", 1960, "9780061120084", 12, category1));
            bookRepository.save(new Book("1984", "George Orwell", 1949, "9780451524935", 10, category3));
            bookRepository.save(new Book("Pride and Prejudice", "Jane Austen", 1813, "9780141439518", 8, category1));
            bookRepository.save(new Book("The Catcher in the Rye", "J.D. Salinger", 1951, "9780316769488", 9, category1));

            // Fetch all books and log them
            System.out.println("Books found with findAll():");
            System.out.println("-------------------------------");
            for (Book book : bookRepository.findAll()) {
                System.out.println(book.toString());
            }
            System.out.println();
        };
    }
}