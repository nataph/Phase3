package application;

import javafx.application.Application;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class BuyerView extends Application {
	 private TableView<Book> bookTable;
	    private TextField totalBox;
	    private double totalPrice = 0;

	    public void start(Stage primaryStage) {
	        // Left - Category List
	        ListView<String> categoryList = new ListView<>();
	        categoryList.setItems(FXCollections.observableArrayList(
	                "Natural Science", "Computer", "Math", "Language", 
	                "Novels", "Classic", "History", "Fantasy", "Fiction"
	        ));
	        categoryList.setPrefWidth(200);

	        // Center
	        bookTable = new TableView<>();
	        bookTable.setPrefWidth(600);

	        // Column-TableView
	        TableColumn<Book, String> titleColumn = new TableColumn<>("Book's Information");
	        titleColumn.setCellValueFactory(data -> data.getValue().titleProperty());
	        titleColumn.setPrefWidth(300);

	        TableColumn<Book, String> statusColumn = new TableColumn<>("Status");
	        statusColumn.setCellValueFactory(data -> data.getValue().statusProperty());
	        statusColumn.setPrefWidth(150);

	        TableColumn<Book, Double> priceColumn = new TableColumn<>("Price");
	        priceColumn.setCellValueFactory(data -> data.getValue().priceProperty().asObject());
	        priceColumn.setPrefWidth(100);

	        bookTable.getColumns().addAll(titleColumn, statusColumn, priceColumn);

	        // Add books when a category is clicked
	        categoryList.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
	            if (newValue != null) {
	                updateBookTable(newValue);
	            }
	        });

	        // Right
	        Label totalLabel = new Label("Total:");
	        totalBox = new TextField();
	        totalBox.setEditable(false);

	        Button checkoutButton = new Button("Check out");
	        checkoutButton.setOnAction(e -> {
	            Alert alert = new Alert(Alert.AlertType.INFORMATION);
	            alert.setTitle("Checkout");
	            alert.setHeaderText(null);
	            alert.setContentText("Total Price: $" + String.format("%.2f", totalPrice));
	            alert.showAndWait();
	        });

	        HBox totalBoxContainer = new HBox(10, totalLabel, totalBox, checkoutButton);
	        totalBoxContainer.setStyle("-fx-alignment: center-right;");

	        //update the total 
	        bookTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
	            if (newValue != null) {
	                totalPrice += newValue.getPrice();
	                totalBox.setText(String.format("%.2f", totalPrice));
	            }
	        });

	        // Layout
	        BorderPane root = new BorderPane();
	        root.setLeft(categoryList);
	        root.setCenter(bookTable);
	        root.setBottom(totalBoxContainer);
	        root.setStyle("-fx-background-color: #AD7979;");

	        //Scene
	        Scene scene = new Scene(root, 800, 600);
	        primaryStage.setTitle("Bookstore 52");
	        primaryStage.setScene(scene);
	        primaryStage.show();
	    }

	    private void updateBookTable(String category) {
	        //Each category
	        ObservableList<Book> books = FXCollections.observableArrayList();
	        switch (category) {
	            case "Natural Science":
	                books.add(new Book("A Brief History of Time", "Used Like New", 15.99));
	                books.add(new Book("The Selfish Gene", "Moderately Used", 10.99));
	                break;
	            case "Computer":
	                books.add(new Book("Clean Code", "Used Like New", 25.99));
	                books.add(new Book("The Pragmatic Programmer", "Heavily Used", 9.99));
	                break;
	            case "Math":
	                books.add(new Book("Introduction to Linear Algebra", "Used Like New", 5.99));
	                books.add(new Book("Introduction to Algebra", "Moderately Used", 7.99));
			break;
	            case "Language":
	                books.add(new Book("Practice Makes Perfect: Complete Spanish All-in-One", "Used Like New", 11.99));
	                books.add(new Book("Practice Makes Perfect: Basic French", "Moderately Used", 10.99));
	                break;
	            case "Novel":
	                books.add(new Book("Little Women", "Moderately Used", 12.59));
	                books.add(new Book("The Great Gatsby", "Moderately Used", 8.99));
	                break;
	            case "Classic":
	                books.add(new Book("Pride & Prejudice", "Used Like New", 7.59));
	                books.add(new Book("The Alchemist", "Heavily Used", 12.59));
	                break;
	            case "History":
	                books.add(new Book("American History", "Moderately Used", 12.99));
	                books.add(new Book("Timeline of World History", "Used Like New", 10.59));
	                break;
	            case "Fantasy":
	                books.add(new Book("A Game of Thrones", "Used Like New", 14.59));
	                books.add(new Book("The Lord of the Rings", "Heavily Used", 6.99));
	                break;
	            case "Fiction":
	                books.add(new Book("Harry Potter and the Chamber of Secrets", "Heavily Used", 8.69));
	                books.add(new Book("The Book Thief", "Used Like New", 9.59));
	                break;
	            default:
	            	books.add(new Book("Blank","Blank", 0.00));
	            	break;
	        }
	        bookTable.setItems(books);
	    }

	   
	    // Book class 
	    public static class Book {
	        private final StringProperty title;
	        private final StringProperty status;
	        private final DoubleProperty price;
	        public Book(String title, String status, double price) {
	            this.title = new SimpleStringProperty(title);
	            this.status = new SimpleStringProperty(status);
	            this.price = new SimpleDoubleProperty(price);
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
	        public double getPrice() {
	            return price.get();
	        }
	    }
	    public static void main(String[] args) {
	        launch(args);
	    }
}
