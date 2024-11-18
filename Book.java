package application;

import java.io.Serializable;

public class Book implements Serializable {
    private static final long serialVersionUID = 1L;

    private final String title;
    private final String isbn;
    private final String condition;
    private final double originalPrice;

    public Book(String title, String isbn, String condition, double originalPrice) {
        this.title = title;
        this.isbn = isbn;
        this.condition = condition;
        this.originalPrice = originalPrice;
    }

    public String getTitle() {
        return title;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getCondition() {
        return condition;
    }

    public double getOriginalPrice() {
        return originalPrice;
    }

    @Override
    public String toString() {
        return title + " (ISBN: " + isbn + ") - Condition: " + condition + " - Price: $" + originalPrice;
    }
}