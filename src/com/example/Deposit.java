package com.example;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Scanner;

import javax.swing.*;
import com.example.Display_Acc.variables;

public class Deposit extends JFrame implements ActionListener{
	
	
	variables obj = new variables();
    File myfile = new File("Bank_managemnet.txt");
	JTextField input_accField;
	JButton button,button2;
	JLabel background;
	
	
	
	Deposit(){
	
	this.setSize(800,800);
    this.setTitle("IMPERTIAL CROWN BANK");
    ImageIcon icon = new ImageIcon("C:\\Users\\Hp\\Downloads\\mylogo.jpg");
    this.setIconImage(icon.getImage());
    Image scaled = icon.getImage().getScaledInstance(800, 800, Image.SCALE_SMOOTH);
    background = new JLabel(new ImageIcon(scaled));
    this.setIconImage(icon.getImage());
    background.setBounds(0, 0, 800, 800);
    this.setLocationRelativeTo(null);
    background.setLayout(null);
    
    
    
    JLabel main_label = new JLabel("IMPERTIAL CROWN BANK", SwingConstants.CENTER);
    main_label.setBounds(0, 0, 800, 50);
    main_label.setOpaque(true);
    main_label.setBackground(new Color(218,165,32)); // semi-transparent bar
    main_label.setFont(new Font("Serif", Font.BOLD, 22));
    background.add(main_label);
    
    JLabel nameLabel = new JLabel("Please enter your account number :");
    nameLabel.setBounds(50, 80, 350, 30);
    nameLabel.setForeground(new Color(218,165,32));
    nameLabel.setFont(new Font("Serif", Font.BOLD, 20));
    background.add(nameLabel);
    
    input_accField = new JTextField();
    input_accField.setBounds(400, 80, 200, 30);
    background.add(input_accField);
    
    button = new JButton("Submit");
    button.setBounds(650, 75, 120, 40);
    button.setBackground(new Color(218,165,32));
    button.addActionListener(this);
    background.add(button);
    
    button2 = new JButton("GO to Main Menu");
    button2.setBounds(650, 720, 150, 40);
    button2.setBackground(new Color(218,165,32));
    button.setFocusable(false);
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
		panel.setBounds(40, 300, 700, 400);
		panel.setLayout(null);
		String name_get=null,Father_get=null;
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
            if (acc_num==num)
            {
            
            	
            	
            }
            }
            
            
        }
	catch (Exception e1) {
		// TODO: handle exception
	}
		
	if (count ==0) {
		
		JLabel messageJLabel = new JLabel("SORRY NO ACCOUNT FOUND IN RECORD");
	    messageJLabel.setForeground(Color.RED);
	    messageJLabel.setFont(new Font("Serif", Font.BOLD, 20));
	    messageJLabel.setBounds(150, 150, 700, 50); // Set the position for the error message label
	    
	    // Add the label to the panel
	    panel.add(messageJLabel);
	    
	    // Ensure the panel is updated
	    panel.revalidate(); //  the panel to ensure it adds the new component
	    panel.repaint(); // Repaint the panel to render the new label
		
			
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

