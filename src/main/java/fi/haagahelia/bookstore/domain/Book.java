package fi.haagahelia.bookstore.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private String author;
    private int publicationYear;
    private String isbn;
    private int price;

    @ManyToOne
    @JoinColumn(name = "categoryid")
    private Category category;

    public Book() {};

    public Book(String title, String author, int publicationYear, String isbn, int price, Category category) {
        this.title = title;
        this.author = author;
        this.publicationYear = publicationYear;
        this.isbn = isbn;
        this.price = price;
        this.category = category;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return this.author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getPublicationYear() {
        return this.publicationYear;
    }

    public void setPublicationYear(int publicationYear) {
        this.publicationYear = publicationYear;
    }

    public String getIsbn() {
        return this.isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public int getPrice() {
        return this.price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Category getCategory() {
        return this.category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        if (this.category != null) {
            return "Book [id=" + this.id + ", title=" + this.title + ", author=" + this.author + ", publicationYear=" + this.publicationYear + ", isbn=" + this.isbn + ", price=" + this.price + ", category=" + this.category + "]";
        } else {
            return "Book [id=" + this.id + ", title=" + this.title + ", author=" + this.author + ", publicationYear=" + this.publicationYear + ", isbn=" + this.isbn + ", price=" + this.price + "]";
        }
    }
}
