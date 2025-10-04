package com.example;

import javax.swing.*;

import javax.swing.*;

public class twoinput {
   
	public twoinput() {
		JTextField firstField = new JTextField();
        JTextField secondField = new JTextField();

        Object[] message = {
            "Enter Username:", firstField,
            "Enter Password:", secondField
        };

       
        String[] options = {"Verify", "Close"};

        int option = JOptionPane.showOptionDialog(
                null,
                message,
                "Login Form",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.PLAIN_MESSAGE,
                null,
                options,
                options[0]   // default selected button
        );

        if (option == 0) { // "Confirm"
            String username = firstField.getText();
            String password = secondField.getText();
            JOptionPane.showMessageDialog(null, 
                "You entered:\nUsername: " + username + "\nPassword: " + password);
        } else {
            JOptionPane.showMessageDialog(null, "User cancelled.");
        }
    }
	}
       
