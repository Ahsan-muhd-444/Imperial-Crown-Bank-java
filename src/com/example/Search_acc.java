package com.example;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class Search_acc extends JFrame implements ActionListener{
	
	
	variables obj = new variables();
    File myfile = new File("Bank_managemnet.txt");
	JTextField input_accField;
	JButton button,button2;
	JLabel background;
	
	public Search_acc() {
		
        
        this.setSize(1000,800);
        this.setTitle("IMPERTIAL CROWN BANK");
        ImageIcon icon = new ImageIcon("C:\\Users\\Hp\\Downloads\\mylogo.jpg");
        this.setIconImage(icon.getImage());
        Image scaled = icon.getImage().getScaledInstance(1000, 800, Image.SCALE_SMOOTH);
        background = new JLabel(new ImageIcon(scaled));
        this.setIconImage(icon.getImage());
        background.setBounds(0, 0, 1000, 800);
        this.setLocationRelativeTo(null);
        background.setLayout(null);
        
        
        
        
        JLabel main_label = new JLabel("IMPERTIAL CROWN BANK", SwingConstants.CENTER);
        main_label.setBounds(0, 0, 1000, 60);
        main_label.setOpaque(true);
        main_label.setBackground(new Color(184, 134, 11)); // Dark golden
        main_label.setForeground(Color.WHITE);
        main_label.setFont(new Font("Monospaced", Font.BOLD, 28));
        background.add(main_label);
        
        JLabel nameLabel = new JLabel("Please enter your account number :");
        nameLabel.setBounds(100, 90, 440
        		, 35);
        nameLabel.setOpaque(true);
        nameLabel.setBackground(new Color(0, 0, 0, 180)); // Semi-transparent black background
        nameLabel.setForeground(new Color(218, 165, 32));
        nameLabel.setFont(new Font("Monospaced", Font.BOLD, 20));
        nameLabel.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 10, 5, 5)); // Padding
        background.add(nameLabel);
        
        input_accField = new JTextField();
        input_accField.setBounds(540, 90, 220, 35);
        input_accField.setFont(new Font("Monospaced", Font.PLAIN, 16));
        background.add(input_accField);
        
        button = new JButton("Submit");
        button.setBounds(780, 85, 130, 45);
        button.setBackground(new Color(218, 165, 32));
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Monospaced", Font.BOLD, 16));
        button.setFocusable(false);
        button.addActionListener(this);
        background.add(button);
        
        button2 = new JButton("GO to Main Menu");
        button2.setBounds(780, 710, 180, 50);
        button2.setBackground(new Color(184, 134, 11));
        button2.setForeground(Color.WHITE);
        button2.setFont(new Font("Monospaced", Font.BOLD, 14));
        button2.setFocusable(false);
        button2.addActionListener(this);
        background.add(button2);
        
        
        this.setContentPane(background);
        this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()==button) {
			button.setEnabled(false);
			int num = Integer.parseInt(input_accField.getText());
			int count =0;
			JPanel panel = new JPanel();
			panel.setBounds(80, 200, 840, 480);
			panel.setLayout(null);
			panel.setBackground(new Color(245, 245, 245));
			panel.setBorder(javax.swing.BorderFactory.createCompoundBorder(
				javax.swing.BorderFactory.createLineBorder(new Color(184, 134, 11), 3),
				javax.swing.BorderFactory.createEmptyBorder(20, 20, 20, 20)
			));
			String name_get=null,Father_get=null,ATM_get=null,PIN_get=null;
			long CNIC_get=0,phone_get=0;
			int age_get=0;
			
		
		
		try {
			Scanner sc = new Scanner(myfile);
			while(sc.hasNext())
            {
				
                int acc_num=sc.nextInt();
                StringBuilder nameBuilder = new StringBuilder();
                while (!sc.hasNextInt() && sc.hasNext()) {
                    nameBuilder.append(sc.next()).append(" ");
                }
                obj.name = nameBuilder.toString().trim();
                obj.age = sc.nextInt();
                StringBuilder fatherBuilder = new StringBuilder();
                while (!sc.hasNextLong() && sc.hasNext()) {
                    fatherBuilder.append(sc.next()).append(" ");
                }
                obj.Father_name = fatherBuilder.toString().trim();
                obj.Phone_number = sc.nextLong();
                obj.CNIC = sc.nextLong();
                obj.Deposit_money = sc.nextInt();
                obj.atm_card = sc.next();
                
                // Read PIN if it exists
                if (sc.hasNext()) {
                    obj.pin = sc.next();
                } else {
                    obj.pin = "N/A"; // For old accounts without PIN
                }
                
                if (acc_num==num)
                {
                	count = 1;
                	name_get = obj.name;
                	age_get = obj.age;
                	Father_get = obj.Father_name;
                	phone_get = obj.Phone_number;
                	CNIC_get = obj.CNIC;
                	ATM_get = obj.atm_card;
                	PIN_get = obj.pin;
                	
                	
                	int labelYPosition = 30;
                	int labelHeight = 50;
                	int spacing = 55;
                    
                    // Account Holder Name
                    JLabel nameLabel = new JLabel("ACCOUNT HOLDER NAME");
                    nameLabel.setForeground(new Color(70, 70, 70));
                    nameLabel.setFont(new Font("Monospaced", Font.BOLD, 14));
                    nameLabel.setBounds(40, labelYPosition, 350, 25);
                    panel.add(nameLabel);
                    
                    JLabel nameValue = new JLabel(name_get);
                    nameValue.setForeground(new Color(184, 134, 11));
                    nameValue.setFont(new Font("Monospaced", Font.BOLD, 18));
                    nameValue.setBounds(40, labelYPosition + 25, 750, 30);
                    panel.add(nameValue);
                    
                    labelYPosition += spacing;
                    
                    // Father's Name
                    JLabel fatherLabel = new JLabel("FATHER'S NAME");
                    fatherLabel.setForeground(new Color(70, 70, 70));
                    fatherLabel.setFont(new Font("Monospaced", Font.BOLD, 14));
                    fatherLabel.setBounds(40, labelYPosition, 350, 25);
                    panel.add(fatherLabel);
                    
                    JLabel fatherValue = new JLabel(Father_get);
                    fatherValue.setForeground(new Color(184, 134, 11));
                    fatherValue.setFont(new Font("Monospaced", Font.BOLD, 18));
                    fatherValue.setBounds(40, labelYPosition + 25, 750, 30);
                    panel.add(fatherValue);
                    
                    labelYPosition += spacing;
                    
                    // Age and Phone in same row
                    JLabel ageLabel = new JLabel("AGE");
                    ageLabel.setForeground(new Color(70, 70, 70));
                    ageLabel.setFont(new Font("Monospaced", Font.BOLD, 14));
                    ageLabel.setBounds(40, labelYPosition, 150, 25);
                    panel.add(ageLabel);
                    
                    JLabel ageValue = new JLabel(String.valueOf(age_get));
                    ageValue.setForeground(new Color(184, 134, 11));
                    ageValue.setFont(new Font("Monospaced", Font.BOLD, 18));
                    ageValue.setBounds(40, labelYPosition + 25, 150, 30);
                    panel.add(ageValue);
                    
                    JLabel phoneLabel = new JLabel("PHONE NUMBER");
                    phoneLabel.setForeground(new Color(70, 70, 70));
                    phoneLabel.setFont(new Font("Monospaced", Font.BOLD, 14));
                    phoneLabel.setBounds(450, labelYPosition, 350, 25);
                    panel.add(phoneLabel);
                    
                    JLabel phoneValue = new JLabel(String.valueOf(phone_get));
                    phoneValue.setForeground(new Color(184, 134, 11));
                    phoneValue.setFont(new Font("Monospaced", Font.BOLD, 18));
                    phoneValue.setBounds(450, labelYPosition + 25, 350, 30);
                    panel.add(phoneValue);

                    labelYPosition += spacing;
                    
                    // CNIC
                    JLabel cnicLabel = new JLabel("CNIC NUMBER");
                    cnicLabel.setForeground(new Color(70, 70, 70));
                    cnicLabel.setFont(new Font("Monospaced", Font.BOLD, 14));
                    cnicLabel.setBounds(40, labelYPosition, 350, 25);
                    panel.add(cnicLabel);
                    
                    JLabel cnicValue = new JLabel(String.valueOf(CNIC_get));
                    cnicValue.setForeground(new Color(184, 134, 11));
                    cnicValue.setFont(new Font("Monospaced", Font.BOLD, 18));
                    cnicValue.setBounds(40, labelYPosition + 25, 750, 30);
                    panel.add(cnicValue);
                    
                    labelYPosition += spacing;
                    
                    // ATM Card and PIN in same row
                    JLabel ATMlabel = new JLabel("ATM CARD NUMBER");
                    ATMlabel.setForeground(new Color(70, 70, 70));
                    ATMlabel.setFont(new Font("Monospaced", Font.BOLD, 14));
                    ATMlabel.setBounds(40, labelYPosition, 350, 25);
                    panel.add(ATMlabel);
                    
                    JLabel ATMvalue = new JLabel(ATM_get);
                    ATMvalue.setForeground(new Color(184, 134, 11));
                    ATMvalue.setFont(new Font("Monospaced", Font.BOLD, 18));
                    ATMvalue.setBounds(40, labelYPosition + 25, 350, 30);
                    panel.add(ATMvalue);
                    
                    // Display PIN (masked for security)
                    String maskedPIN = PIN_get.equals("N/A") ? "N/A" : "••••";
                    JLabel PINlabel = new JLabel("ATM PIN");
                    PINlabel.setForeground(new Color(70, 70, 70));
                    PINlabel.setFont(new Font("Monospaced", Font.BOLD, 14));
                    PINlabel.setBounds(450, labelYPosition, 200, 25);
                    panel.add(PINlabel);
                    
                    JLabel PINvalue = new JLabel(maskedPIN);
                    PINvalue.setForeground(new Color(184, 134, 11));
                    PINvalue.setFont(new Font("Monospaced", Font.BOLD, 24));
                    PINvalue.setBounds(450, labelYPosition + 20, 200, 35);
                    panel.add(PINvalue);
                    
                }
            }
			if (count ==0) {
				
				JLabel messageJLabel = new JLabel("ACCOUNT NOT FOUND");
			    messageJLabel.setForeground(new Color(184, 134, 11));
			    messageJLabel.setFont(new Font("Monospaced", Font.BOLD, 24));
			    messageJLabel.setBounds(280, 180, 400, 50);
			    panel.add(messageJLabel);
			    
			    JLabel subMessage = new JLabel("No record exists for this account number");
			    subMessage.setForeground(new Color(120, 120, 120));
			    subMessage.setFont(new Font("Monospaced", Font.PLAIN, 16));
			    subMessage.setBounds(210, 230, 500, 30);
			    panel.add(subMessage);
			    
			    panel.revalidate();
			    panel.repaint();
				
			}
      

        }
		
            
        catch(Exception e1) {
            System.out.println("ERROR");
        }
		
			
			background.add(panel);
			this.setContentPane(background);
			
			
			
		}
		else if (e.getSource()==button2) {
			
			this.dispose();
			Menu obj3 = new Menu();
			
		}
		
		
	}

}