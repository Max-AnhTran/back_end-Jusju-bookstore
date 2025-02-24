package fi.haagahelia.bookstore.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;

@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long categoryid;
    private String name;

    @JsonIgnoreProperties("category")  // ignoring 'department' attribute for all students
	//@JsonIgnore   // ignoring students

	// Thêm @IgnoreProperties("department") để tránh lỗi vòng lặp vô hạn
	// Còn thêm @JsonIgnore thì sẽ không hiển thị thông tin students
	// @JsonIgnoreProperties("department")  // ignoring 'department' attribute for all students

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "category")
    private List<Book> books;

    public Category() {
    }

    public Category(String name) {
        super();
        this.name = name;
    }

    public Long getCategoryid() {
        return categoryid;
    }

    public void setCategoryid(Long categoryid) {
        this.categoryid = categoryid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    @Override
    public String toString() {
        return "Category [categoryid=" + categoryid + ", name=" + name + "]";
    }
}
