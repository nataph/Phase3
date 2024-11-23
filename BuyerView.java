package application;

import javafx.application.Application;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class BuyerView extends Application {
    //Data Members
    private TableView<BookInfo> bookTable;
    private TextField totalBox; 
    private TextField searchBar;
    private double totalPrice = 0;
    private ObservableList<BookInfo> books = FXCollections.observableArrayList();
    private ObservableList<BookInfo> allBooks = FXCollections.observableArrayList(); // The list

    public void start(Stage primaryStage) {
    	primaryStage.setTitle("Bookstore 52");
    	Text title = new Text("Bookstore 52");
    	title.setStyle("-fx-font-weight: bold;");
        
        // Left: Category List
        ListView<String> categoryList = new ListView<>();
        categoryList.setItems(FXCollections.observableArrayList(
                "Natural Science", "Computer", "Math", "Language",
                "Novel", "Classic", "History", "Fantasy", "Fiction"));
        categoryList.setPrefWidth(100);
        
        // listen (or check) for changes in the selected item in category list
        categoryList.getSelectionModel().selectedItemProperty().
        addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
            	updateBookTable(newValue);
            }
        });

        // Search Bar
        searchBar = new TextField();
        searchBar.setPromptText("Search for a book");
        searchBar.setPrefWidth(50);
        Button searchButton = new Button("Search");
        searchButton.setOnAction(e -> filterBooks());
        VBox searchContainer = new VBox(10, searchBar, searchButton);
        searchContainer.setStyle("-fx-padding: 10px; -fx-alignment: center;");

        // Book Table
        bookTable = new TableView<>();
        bookTable.setPrefWidth(600);

        TableColumn<BookInfo, String> titleColumn = new TableColumn<>("Book's Information");
        titleColumn.setCellValueFactory(data -> data.getValue().titleProperty());
        titleColumn.setPrefWidth(300);

        TableColumn<BookInfo, String> statusColumn = new TableColumn<>("Status");
        statusColumn.setCellValueFactory(data -> data.getValue().statusProperty());
        statusColumn.setPrefWidth(150);

        TableColumn<BookInfo, Double> priceColumn = new TableColumn<>("Price");
        priceColumn.setCellValueFactory(data -> data.getValue().priceProperty().asObject()); // makes DoubleProperty usable in TableView
        priceColumn.setPrefWidth(150);
        
        // define columns ( display the title, status, price)
        bookTable.getColumns().addAll(titleColumn, statusColumn, priceColumn);

        // Total price 
        bookTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                totalPrice += newValue.getPrice();
                totalBox.setText(String.format("%.2f", totalPrice));
            }
        });

        // Bottom - Total and Checkout
        Label totalLabel = new Label("Total:");
        totalBox = new TextField();
        totalBox.setEditable(false);

        Button checkoutButton = new Button("Check out");
        checkoutButton.setOnAction(e -> showCheckoutAlert());

        HBox totalBoxContainer = new HBox(10, totalLabel, totalBox, checkoutButton);
        totalBoxContainer.setStyle("-fx-alignment: center-right; -fx-padding: 50px;");

        // Layout
        BorderPane root = new BorderPane();
        root.setTop(searchContainer); 				// set the search bar at the top
        root.setLeft(categoryList);   				// set the category list to the left
        root.setCenter(bookTable);   				// set the book table to the center
        root.setBottom(totalBoxContainer); 			// set the total section to the bottom
        root.setStyle("-fx-background-color: #AD7979;");

        // Load all books into the list
        initializeAllBooks();

        // Scene
        Scene scene = new Scene(root, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Filter books based on search book
    private void filterBooks() {
        String searchText = searchBar.getText().toLowerCase();
        ObservableList<BookInfo> filteredBooks = FXCollections.observableArrayList();
        // Search book in the list
        for (BookInfo book : allBooks) { 
            if (book.getTitle().toLowerCase().contains(searchText) ||
                book.getStatus().toLowerCase().contains(searchText)) {
                filteredBooks.add(book);
            }
        }
        bookTable.setItems(filteredBooks);
    }

    // Update book table 
    private void updateBookTable(String category) {
        books.clear();
        for (BookInfo book : allBooks) { 
            if (getCategory(book).equals(category)) {
                books.add(book);
            }
        }
        bookTable.setItems(books);
    }

    // The list of all books
    private void initializeAllBooks() {
        allBooks.addAll(new BookInfo("A Brief History of Time", "Used Like New", 15.99, "Natural Science"),
                		new BookInfo("The Selfish Gene", "Moderately Used", 10.99, "Natural Science"),
                		new BookInfo("Clean Code", "Used Like New", 25.99, "Computer"),
                		new BookInfo("The Pragmatic Programmer", "Heavily Used", 9.99, "Computer"),
                		new BookInfo("Introduction to Linear Algebra", "Used Like New", 5.99, "Math"),
                		new BookInfo("Introduction to Algebra", "Moderately Used", 7.99, "Math"),
                		new BookInfo("Practice Makes Perfect: Complete Spanish All-in-One", "Used Like New", 11.99, "Language"),
                		new BookInfo("Practice Makes Perfect: Basic French", "Moderately Used", 10.99, "Language"),
                		new BookInfo("Little Women", "Moderately Used", 12.59, "Novel"),
                		new BookInfo("The Great Gatsby", "Moderately Used", 8.99, "Novel"),
                		new BookInfo("Pride & Prejudice", "Used Like New", 7.59, "Classic"),
                		new BookInfo("The Alchemist", "Heavily Used", 12.59, "Classic"),
                		new BookInfo("American History", "Moderately Used", 12.99, "History"),
                		new BookInfo("Timeline of World History", "Used Like New", 10.59, "History"),
                		new BookInfo("A Game of Thrones", "Used Like New", 14.59, "Fantasy"),
                		new BookInfo("The Lord of the Rings", "Heavily Used", 6.99, "Fantasy"),
                		new BookInfo("Harry Potter and the Chamber of Secrets", "Heavily Used", 8.69, "Fiction"),
                		new BookInfo("The Book Thief", "Used Like New", 9.59, "Fiction"));
    }

    // Get category 
    private String getCategory(BookInfo book) {
        return book.getCategory();
    }

    // Show checkout
    private void showCheckoutAlert() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Checkout");
        alert.setHeaderText(null);
        alert.setContentText("Total Price: $" + String.format("%.2f", totalPrice));
        alert.showAndWait();
    }

    // Book class
    public static class BookInfo {
        //Data Members
        private final StringProperty title;
        private final StringProperty status;
        private final DoubleProperty price;
        private final String category; 

        //Constructor
        public BookInfo(String title, String status, double price, String category) {
            this.title = new SimpleStringProperty(title);
            this.status = new SimpleStringProperty(status);
            this.price = new SimpleDoubleProperty(price);
            this.category = category;
        }

        //Get Methods
        public String getTitle() {
            return title.get();
        }

        public String getStatus() {
            return status.get();
        }

        public double getPrice() {
            return price.get();
        }

        public String getCategory() {
            return category;
        }

        public StringProperty titleProperty() {
            return title;
        }

        public StringProperty statusProperty() {
            return status;
        }

        public DoubleProperty priceProperty() {
            return price;
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
