package fi.haagahelia.bookstore;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import fi.haagahelia.bookstore.domain.Book;
import fi.haagahelia.bookstore.domain.BookRepository;

@Configuration
public class DatabaseLoader {

    @Bean
    public CommandLineRunner demo(BookRepository repository) {
        return (args) -> {
            // Clear existing data
            repository.deleteAll();

            // Save a few example books
            repository.save(new Book("The Great Gatsby", "F. Scott Fitzgerald", 1925, "9780743273565", 15));
            repository.save(new Book("To Kill a Mockingbird", "Harper Lee", 1960, "9780061120084", 12));
            repository.save(new Book("1984", "George Orwell", 1949, "9780451524935", 10));
            repository.save(new Book("Pride and Prejudice", "Jane Austen", 1813, "9780141439518", 8));
            repository.save(new Book("The Catcher in the Rye", "J.D. Salinger", 1951, "9780316769488", 9));

            // Fetch all books and log them
            System.out.println("Books found with findAll():");
            System.out.println("-------------------------------");
            for (Book book : repository.findAll()) {
                System.out.println(book.toString());
            }
            System.out.println();
        };
    }
}
