package application;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;

public class Transaction {
	//datamembers
	private int transactionID;
	
	//Constructor
	public Transaction() {
		Random rand = new Random();
		boolean check;
		do {//Creates random ID that is Unique
			transactionID = rand.nextInt(9000000) + 1000000;
			Path filePath = Paths.get("src/BookList/", transactionID + ".txt");
			check = Files.exists(filePath);
		}while(check == true);
			
	}
	
	public int getTransactionID() {
		return transactionID;
	}
	
	//TODO
	public void processTransaction(int transactionID) {
		
	}
}
