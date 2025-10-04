package com.example;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import java.util.*;
public class Account_number extends JFrame{
	
	
	
	
	static int  get_Account_number() {
		int acc_nm = 10010; // starting account number if file is empty
        File myfile = new File("Bank_managemnet.txt");

        try {
            if (!myfile.exists() || myfile.length() == 0) {
                return acc_nm; // file not found or empty → return 101
            }

            Scanner sc = new Scanner(myfile);

            while (sc.hasNext()) {
                acc_nm = sc.nextInt();  // read the first int (account number)
                sc.nextLine(); // skip rest of the line
            }
            sc.close();

            return acc_nm + 1; // next available account number

        } catch (Exception e) {
            System.out.println("Error while getting latest account: " + e);
        }

        return acc_nm; // fallback
        
		
	}
	
	static String generateATMCardNumber() {
        Random random = new Random();
        StringBuilder cardNumber = new StringBuilder();
        
        // Generate 16 random digits
        for (int i = 0; i < 16; i++) {
            cardNumber.append(random.nextInt(10));
            
            // Add hyphen after every 4 digits for readability (file-safe)
            if ((i + 1) % 4 == 0 && i != 15) {
                cardNumber.append("-");
            }
        }
        
        return cardNumber.toString();
    }
	
	Account_number() {
		
		int acc_num = get_Account_number()-1;
		String atmCardNumber = generateATMCardNumber();
		
		String message = String.format(
	            "Account Created Successfully!\n\n" +
	            "Account Number: %d\n" +
	            "ATM Card Number: %s\n\n" +
	            "Please keep this information safe.",
	            acc_num,
	            atmCardNumber
	        );
	        
	        // Display the information
	        JOptionPane.showMessageDialog(
	            null, 
	            message, 
	            "ACCOUNT CREATION SUCCESS", 
	            JOptionPane.INFORMATION_MESSAGE
	        );
	        
	        this.dispose();
	        Menu display_menu1 = new Menu();
	    }
	}