package fi.haagahelia.bookstore;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import fi.haagahelia.bookstore.domain.Book;
import fi.haagahelia.bookstore.domain.BookRepository;
import fi.haagahelia.bookstore.domain.Category;
import fi.haagahelia.bookstore.domain.CategoryRepository;
import fi.haagahelia.bookstore.domain.AppUserRepository;
import fi.haagahelia.bookstore.domain.AppUser;

@Configuration
public class DatabaseLoader {

    @Bean
    public CommandLineRunner demo(BookRepository bookRepository, CategoryRepository categoryRepository,
            AppUserRepository appUserRepository) {
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
            bookRepository
                    .save(new Book("The Bad Homie Guy", "F. Scott Fitzgerald", 1925, "9780743273565", 15, category1));
            bookRepository.save(new Book("To Kill a Heart Blue", "Harper Lee", 1960, "9780061120084", 12, category2));
            bookRepository.save(new Book("Born in 1919", "George Orwell", 1949, "9780451524935", 10, category3));
            bookRepository.save(new Book("Black and White", "Jane Austen", 1813, "9780141439518", 8, category1));
            bookRepository
                    .save(new Book("The Catcher in the Rome", "J.D. Salinger", 1951, "9780316769488", 9, category3));

            // Fetch all books and log them
            System.out.println("Books found with findAll():");
            System.out.println("-------------------------------");
            for (Book book : bookRepository.findAll()) {
                System.out.println(book.toString());
            }
            System.out.println();

            // Create users: admin/admin user/user
            AppUser user1 = new AppUser("user", "$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6", "user@haagahelia.fi", "USER");
            AppUser user2 = new AppUser("admin", "$2a$10$0MMwY.IQqpsVc1jC8u7IJ.2rT8b0Cd3b3sfIBGV2zfgnPGtT4r0.C", "admin@haagahelia.fi", "ADMIN");
            AppUser user3 = new AppUser("anhtran", "$2a$10$q8zB.XUSujJ4NvulUJFSm.OZCCX5EKaSyg2M0Ogc9R8jz29LtnbT.", "anhtran@haagahelia.fi", "ADMIN");
            appUserRepository.save(user1);
            appUserRepository.save(user2);
            appUserRepository.save(user3);
        };
    }
}