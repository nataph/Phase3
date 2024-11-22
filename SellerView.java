package application;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;



public class SellerView extends Application {
   
    public void start(Stage primaryStage) {
    	// Create grid layout
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20));
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setStyle("-fx-background-color: #AD7979;");

        // logo 
        
        // Add UI components
        Label titleLabel = new Label("Book Title:");
        TextField titleField = new TextField();
        grid.add(titleLabel, 0, 0);
        grid.add(titleField, 1, 0);

        Label isbnLabel = new Label("ISBN:");
        TextField isbnField = new TextField();
        grid.add(isbnLabel, 0, 1);
        grid.add(isbnField, 1, 1);

        Label conditionLabel = new Label("Condition:");
        RadioButton usedLikeNew = new RadioButton("Used Like New");
        RadioButton moderatelyUsed = new RadioButton("Moderately Used");
        RadioButton heavilyUsed = new RadioButton("Heavily Used");

        ToggleGroup conditionGroup = new ToggleGroup();
        usedLikeNew.setToggleGroup(conditionGroup);
        moderatelyUsed.setToggleGroup(conditionGroup);
        heavilyUsed.setToggleGroup(conditionGroup);

        HBox conditionBox = new HBox(10, usedLikeNew, moderatelyUsed, heavilyUsed);
        grid.add(conditionLabel, 0, 2);
        grid.add(conditionBox, 1, 2);

        Label uploadLabel = new Label("Please attach a picture of the book here:");
        Button uploadButton = new Button("Upload file");
        FileChooser fileChooser = new FileChooser();
        Label filePathLabel = new Label();
        uploadButton.setOnAction(e -> {
            File file = fileChooser.showOpenDialog(primaryStage);
            if (file != null) {
                filePathLabel.setText(file.getAbsolutePath());
            }
        });
        HBox uploadBox = new HBox(10, uploadButton, filePathLabel);
        grid.add(uploadLabel, 0, 3);
        grid.add(uploadBox, 1, 3);

        Label originalPriceLabel = new Label("Original Price:");
        TextField originalPriceField = new TextField();
        grid.add(originalPriceLabel, 0, 4);
        grid.add(originalPriceField, 1, 4);

        Label buyingPriceLabel = new Label("Buying Price:");
        TextField buyingPriceField = new TextField();
        grid.add(buyingPriceLabel, 0, 5);
        grid.add(buyingPriceField, 1, 5);

        Button saveButton = new Button("List My Book");
        saveButton.setOnAction(e -> {
            String bookTitle = titleField.getText();
            String isbn = isbnField.getText();
            String condition = (usedLikeNew.isSelected() ? "Used Like New" : "") +
                               (moderatelyUsed.isSelected() ? " Moderately Used" : "") +
                               (heavilyUsed.isSelected() ? " Heavily Used" : "");
            String filePath = filePathLabel.getText();
            String originalPrice = originalPriceField.getText();
            String buyingPrice = buyingPriceField.getText();

            // Save data to a file
            try (FileWriter writer = new FileWriter("book_info.txt", true)) {
                writer.write("Book Title: " + bookTitle + "\n");
                writer.write("ISBN: " + isbn + "\n");
                writer.write("Condition: " + condition + "\n");
                writer.write("Image Path: " + filePath + "\n");
                writer.write("Original Price: " + originalPrice + "\n");
                writer.write("Buying Price: " + buyingPrice + "\n");
                writer.write("------------------------------\n");
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setHeaderText(null);
                alert.setContentText("Book information saved successfully!");
                alert.showAndWait();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        grid.add(saveButton, 1, 6);

        // Set the scene
        Scene scene = new Scene(grid, 600, 400);
        primaryStage.setTitle("Bookstore 52");
        primaryStage.setScene(scene);
        primaryStage.show();
        }

    public static void main(String[] args) {
        launch(args);
    }
}


