package application;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;



public class SellerView extends Application {
   
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Seller View - Bookstore 52");

        VBox root = new VBox(10);
        root.setPadding(new Insets(20));
        root.setStyle("-fx-background-color: #AD7979;");

        MenuBar menuBar = new MenuBar();
        Menu menu = new Menu("Select Book Category");
        menuBar.getMenus().add(menu);
        root.getChildren().add(menuBar);

        GridPane form = new GridPane();
        form.setHgap(10);
        form.setVgap(10);
        form.setAlignment(Pos.TOP_LEFT);

        Label bookTitleLabel = new Label("Book Title:");
        TextField bookTitleField = new TextField();
        Label isbnLabel = new Label("ISBN:");
        TextField isbnField = new TextField();

        Label conditionLabel = new Label("Condition:");
        CheckBox likeNew = new CheckBox("Used Like New");
        CheckBox moderatelyUsed = new CheckBox("Moderately Used");
        CheckBox heavilyUsed = new CheckBox("Heavily Used");

        Label uploadLabel = new Label("Please attach a picture of the book here:");
        Button uploadButton = new Button("Upload File");

        Label originalPriceLabel = new Label("Original Price:");
        TextField originalPriceField = new TextField();
        Button confirmButton = new Button("Confirm");

        Label buyingPriceLabel = new Label("Buying Price:");
        TextField buyingPriceField = new TextField();

        Button listBookButton = new Button("List My Book");

        form.add(bookTitleLabel, 0, 0);
        form.add(bookTitleField, 1, 0);
        form.add(isbnLabel, 0, 1);
        form.add(isbnField, 1, 1);
        form.add(conditionLabel, 0, 2);
        form.add(likeNew, 1, 2);
        form.add(moderatelyUsed, 1, 3);
        form.add(heavilyUsed, 1, 4);
        form.add(uploadLabel, 0, 5);
        form.add(uploadButton, 1, 5);
        form.add(originalPriceLabel, 0, 6);
        form.add(originalPriceField, 1, 6);
        form.add(confirmButton, 2, 6);
        form.add(buyingPriceLabel, 0, 7);
        form.add(buyingPriceField, 1, 7);
        form.add(listBookButton, 1, 8);

        root.getChildren().add(form);

        //Scene setup
        Scene scene = new Scene(root, 600, 500);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}


