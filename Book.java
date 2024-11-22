package application;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javafx.scene.control.Alert;

public class Book {//Start of Class
	//Data Members
	private String bookTitle;
	private String isbn;
	private String condition;
	private String filePath;
	private String originalPrice;
	private String buyingPrice;
	
	//Constructor
	public Book(String bookTitle, String isbn, String condition, String filePath, String originalPrice, String buyingPrice) {
		this.bookTitle = bookTitle;
		this.isbn = isbn;
		this.filePath = filePath;
		this.condition = condition;
		this.originalPrice = originalPrice;
		this.buyingPrice = buyingPrice;
		
		//Create TransactionID
		Transaction newTransaction = new Transaction();
        int fileName = newTransaction.getTransactionID();
		File bookFile = new File("src/BookList/" + fileName);
        
        // Save data to a file
        try (FileWriter writer = new FileWriter(bookFile, true)) {
            writer.write("Book Title: " + bookTitle + "\n");
            writer.write("ISBN: " + isbn + "\n");
            writer.write("Condition: " + condition + "\n");
            writer.write("Image Path: " + filePath + "\n");
            writer.write("Original Price: " + originalPrice + "\n");
            writer.write("Buying Price: " + buyingPrice + "\n");
            writer.write("------------------------------\n");
            writer.close();
            
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText(null);
            alert.setContentText("Book information saved successfully!");
            alert.showAndWait();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
	}
	
}//End of Class