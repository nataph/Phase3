package application;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class BuyerView extends Application {
    @SuppressWarnings("unchecked")
    
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Buyer View - Bookstore 52");

        BorderPane root = new BorderPane();
        root.setPadding(new Insets(20));
        root.setStyle("-fx-background-color: #AD7979;");
        
        // logo
        ImageView logo = new ImageView(new String("file:///Users/nataliepham/Desktop/logo.png")); 
        logo.setFitWidth(50); 
        logo.setFitHeight(50);

        MenuBar menuBar = new MenuBar();
        Menu menu = new Menu("My Account");
        menuBar.getMenus().add(menu);
        root.setTop(menuBar);

        VBox sidebar = new VBox(10);
        sidebar.setPadding(new Insets(10));
        Label searchLabel = new Label("Enter keyword to search:");
        TextField searchField = new TextField();
        ListView<String> categoryList = new ListView<>();
        categoryList.getItems().addAll("Natural Science", "Computer", "Math", "Language", "Novels", 
                "Classic", "History", "Fantasy", "Fiction");

        sidebar.getChildren().addAll(searchLabel, searchField, categoryList);
        root.setLeft(sidebar);

        // Center Table
        TableView<String> tableView = new TableView<>();
        TableColumn<String, String> bookInfoCol = new TableColumn<>("Book's Information");
        TableColumn<String, String> statusCol = new TableColumn<>("Status");
        TableColumn<String, String> priceCol = new TableColumn<>("Price");

        tableView.getColumns().addAll(bookInfoCol, statusCol, priceCol);
        root.setCenter(tableView);

        // Bottom Checkout Section
        HBox bottomSection = new HBox(10);
        bottomSection.setPadding(new Insets(10));
        bottomSection.setAlignment(Pos.CENTER_RIGHT);

        Label totalLabel = new Label("Total:");
        TextField totalField = new TextField();
        totalField.setEditable(false);
        Button checkoutButton = new Button("Check Out");

        bottomSection.getChildren().addAll(totalLabel, totalField, checkoutButton);
        root.setBottom(bottomSection);

        // Scene setup
        Scene scene = new Scene(root, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}