package com.example;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import javax.swing.*;

public class userinfo extends JFrame implements ActionListener {
    // Page 1 fields
    JTextField nameField, fatherField, dobField, emailField, addressField, cityField, pinCodeField, stateField;
    ButtonGroup genderGroup, maritalGroup;
    JRadioButton maleRadio, femaleRadio, marriedRadio, unmarriedRadio, otherRadio;
    
    // Page 2 fields
    JComboBox<String> religionCombo, categoryCombo, incomeCombo, educationCombo, occupationCombo;
    JTextField panField, aadharField;
    ButtonGroup seniorGroup, existingGroup;
    JRadioButton seniorYes, seniorNo, existingYes, existingNo;
    
    // Page 3 fields
    ButtonGroup accountTypeGroup;
    JRadioButton savingAccount, currentAccount, fixedAccount, recurringAccount;
    JCheckBox atmCardCheck, internetBankingCheck, mobileBankingCheck, emailAlertsCheck, chequeBookCheck, eStatementCheck;
    JCheckBox declarationCheck;
    
    // Common
    JButton nextButton, submitButton, cancelButton;
    JLabel background;
    JPanel currentPanel;
    File myfile = new File("Bank_managemnet.txt");
    
    // Store data
    String formNumber;
    String generatedCardNumber;
    String generatedPIN;
    
    public userinfo() {
        formNumber = String.valueOf(6000 + new Random().nextInt(4000)); // Random form number
        
        this.setTitle("NEW ACCOUNT APPLICATION FORM");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1000, 700);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        
        ImageIcon icon = new ImageIcon("C:\\Users\\Hp\\Downloads\\mylogo.jpg");
        this.setIconImage(icon.getImage());
        
        ImageIcon bgIcon = new ImageIcon("C:\\Users\\Hp\\Downloads\\creat_acc_user.jpg");
        Image scaled = bgIcon.getImage().getScaledInstance(1000, 700, Image.SCALE_SMOOTH);
        background = new JLabel(new ImageIcon(scaled));
        background.setBounds(0, 0, 1000, 700);
        background.setLayout(null);
        
        showPage1();
        
        this.setContentPane(background);
        this.setVisible(true);
    }
    
    private void showPage1() {
        if (currentPanel != null) {
            background.remove(currentPanel);
        }
        
        JPanel panel = new JPanel();
        panel.setBounds(50, 50, 900, 600);
        panel.setLayout(null);
        panel.setBackground(new Color(255, 255, 255, 240));
        panel.setBorder(BorderFactory.createLineBorder(new Color(220, 180, 50), 3));
        currentPanel = panel;
        
        // Bank icon and title
        JLabel titleLabel = new JLabel("APPLICATION FORM NO. " + formNumber);
        titleLabel.setBounds(200, 20, 600, 40);
        titleLabel.setFont(new Font("Monospaced", Font.BOLD, 28));
        titleLabel.setForeground(new Color(25, 50, 130));
        panel.add(titleLabel);
        
        JLabel pageLabel = new JLabel("Page 1: Personal Details");
        pageLabel.setBounds(350, 65, 300, 30);
        pageLabel.setFont(new Font("Monospaced", Font.BOLD, 20));
        panel.add(pageLabel);
        
        int y = 120;
        int labelX = 80;
        int fieldX = 350;
        int spacing = 50;
        
        // Name
        addLabel(panel, "Name:", labelX, y);
        nameField = addTextField(panel, fieldX, y, 400);
        y += spacing;
        
        // Father's Name
        addLabel(panel, "Father's Name:", labelX, y);
        fatherField = addTextField(panel, fieldX, y, 400);
        y += spacing;
        
        // Date of Birth
        addLabel(panel, "Date of Birth:", labelX, y);
        dobField = addTextField(panel, fieldX, y, 250);
        JLabel dobHint = new JLabel("(DD/MM/YYYY)");
        dobHint.setBounds(fieldX + 260, y, 100, 30);
        dobHint.setFont(new Font("Arial", Font.ITALIC, 12));
        panel.add(dobHint);
        y += spacing;
        
        // Gender
        addLabel(panel, "Gender:", labelX, y);
        genderGroup = new ButtonGroup();
        maleRadio = new JRadioButton("Male");
        maleRadio.setBounds(fieldX, y, 100, 30);
        maleRadio.setBackground(new Color(255, 255, 255, 240));
        femaleRadio = new JRadioButton("Female");
        femaleRadio.setBounds(fieldX + 150, y, 100, 30);
        femaleRadio.setBackground(new Color(255, 255, 255, 240));
        genderGroup.add(maleRadio);
        genderGroup.add(femaleRadio);
        panel.add(maleRadio);
        panel.add(femaleRadio);
        y += spacing;
        
        // Email
        addLabel(panel, "Email Address:", labelX, y);
        emailField = addTextField(panel, fieldX, y, 400);
        y += spacing;
        
        // Marital Status
        addLabel(panel, "Marital Status:", labelX, y);
        maritalGroup = new ButtonGroup();
        marriedRadio = new JRadioButton("Married");
        marriedRadio.setBounds(fieldX, y, 100, 30);
        marriedRadio.setBackground(new Color(255, 255, 255, 240));
        unmarriedRadio = new JRadioButton("Unmarried");
        unmarriedRadio.setBounds(fieldX + 120, y, 120, 30);
        unmarriedRadio.setBackground(new Color(255, 255, 255, 240));
        otherRadio = new JRadioButton("Other");
        otherRadio.setBounds(fieldX + 260, y, 100, 30);
        otherRadio.setBackground(new Color(255, 255, 255, 240));
        maritalGroup.add(marriedRadio);
        maritalGroup.add(unmarriedRadio);
        maritalGroup.add(otherRadio);
        panel.add(marriedRadio);
        panel.add(unmarriedRadio);
        panel.add(otherRadio);
        y += spacing;
        
        // Address
        addLabel(panel, "Address:", labelX, y);
        addressField = addTextField(panel, fieldX, y, 400);
        y += spacing;
        
        // City
        addLabel(panel, "City:", labelX, y);
        cityField = addTextField(panel, fieldX, y, 250);
        y += spacing;
        
        // Pin Code
        addLabel(panel, "Postal Code:", labelX, y);
        pinCodeField = addTextField(panel, fieldX, y, 150);
        y += spacing;
        
        
        // Next Button
        nextButton = new JButton("Next");
        nextButton.setBounds(750, 540, 100, 40);
        nextButton.setBackground(Color.BLACK);
        nextButton.setForeground(Color.WHITE);
        nextButton.setFont(new Font("Monospaced", Font.BOLD, 14));
        nextButton.setFocusable(false);
        nextButton.addActionListener(this);
        panel.add(nextButton);
        
        background.add(panel);
        background.revalidate();
        background.repaint();
    }
    
    private void showPage2() {
        if (currentPanel != null) {
            background.remove(currentPanel);
        }
        
        JPanel panel = new JPanel();
        panel.setBounds(50, 50, 900, 600);
        panel.setLayout(null);
        panel.setBackground(new Color(255, 255, 255, 240));
        panel.setBorder(BorderFactory.createLineBorder(new Color(220, 180, 50), 3));
        currentPanel = panel;
        
        JLabel formNoLabel = new JLabel("Form No: " + formNumber);
        formNoLabel.setBounds(750, 20, 150, 30);
        formNoLabel.setFont(new Font("Monospaced", Font.PLAIN, 14));
        panel.add(formNoLabel);
        
        JLabel pageLabel = new JLabel("Page 2: Additional Details");
        pageLabel.setBounds(280, 30, 400, 30);
        pageLabel.setFont(new Font("Monospaced", Font.BOLD, 20));
        panel.add(pageLabel);
        
        int y = 100;
        int labelX = 80;
        int fieldX = 350;
        int spacing = 50;
        
        // Religion
        addLabel(panel, "Religion:", labelX, y);
        String[] religions = {"Muslim", "Sikh", "Christian", "Other"};
        religionCombo = new JComboBox<>(religions);
        religionCombo.setBounds(fieldX, y, 200, 30);
        panel.add(religionCombo);
        y += spacing;
        
        // Category
        addLabel(panel, "Province:", labelX, y);
        String[] provinces = {"Punjab", "Sindh", "Khyber Pakhtunkhwa", "Balochistan", "Gilgit-Baltistan", "Azad Kashmir"};
        categoryCombo = new JComboBox<>(provinces);
        categoryCombo.setBounds(fieldX, y, 200, 30);
        panel.add(categoryCombo);
        y += spacing;
        
        // Income
        addLabel(panel, "Income:", labelX, y);
        String[] incomes = {"Null", "< 1,50,000", "< 2,50,000", "< 5,00,000", "Upto 10,00,000", "> 10,00,000"};
        incomeCombo = new JComboBox<>(incomes);
        incomeCombo.setBounds(fieldX, y, 200, 30);
        panel.add(incomeCombo);
        y += spacing;
        
        // Educational Qualification
        addLabel(panel, "Educational Qualification:", labelX, y);
        String[] education = {"Non-Graduate", "Graduate", "Post-Graduate", "Doctorate", "Other"};
        educationCombo = new JComboBox<>(education);
        educationCombo.setBounds(fieldX, y, 200, 30);
        panel.add(educationCombo);
        y += spacing;
        
        // Occupation
        addLabel(panel, "Occupation:", labelX, y);
        String[] occupations = {"Salaried", "Self-Employed", "Business", "Student", "Retired", "Other"};
        occupationCombo = new JComboBox<>(occupations);
        occupationCombo.setBounds(fieldX, y, 200, 30);
        panel.add(occupationCombo);
        y += spacing;
        
        // NTN Number
        addLabel(panel, "NTN Number:", labelX, y);
        panField = addTextField(panel, fieldX, y, 200);
        y += spacing;
        
        // CNIC Number
        addLabel(panel, "CNIC Number:", labelX, y);
        aadharField = addTextField(panel, fieldX, y, 200);
        y += spacing;
        
        // Senior Citizen
        addLabel(panel, "Senior Citizen:", labelX, y);
        seniorGroup = new ButtonGroup();
        seniorYes = new JRadioButton("Yes");
        seniorYes.setBounds(fieldX, y, 80, 30);
        seniorYes.setBackground(new Color(255, 255, 255, 240));
        seniorNo = new JRadioButton("No");
        seniorNo.setBounds(fieldX + 100, y, 80, 30);
        seniorNo.setBackground(new Color(255, 255, 255, 240));
        seniorGroup.add(seniorYes);
        seniorGroup.add(seniorNo);
        panel.add(seniorYes);
        panel.add(seniorNo);
        y += spacing;
        
        // Existing Account
        addLabel(panel, "Existing Account:", labelX, y);
        existingGroup = new ButtonGroup();
        existingYes = new JRadioButton("Yes");
        existingYes.setBounds(fieldX, y, 80, 30);
        existingYes.setBackground(new Color(255, 255, 255, 240));
        existingNo = new JRadioButton("No");
        existingNo.setBounds(fieldX + 100, y, 80, 30);
        existingNo.setBackground(new Color(255, 255, 255, 240));
        existingGroup.add(existingYes);
        existingGroup.add(existingNo);
        panel.add(existingYes);
        panel.add(existingNo);
        
        // Next Button
        nextButton = new JButton("Next");
        nextButton.setBounds(750, 540, 100, 40);
        nextButton.setBackground(Color.BLACK);
        nextButton.setForeground(Color.WHITE);
        nextButton.setFont(new Font("Monospaced", Font.BOLD, 14));
        nextButton.setFocusable(false);
        nextButton.addActionListener(e -> showPage3());
        panel.add(nextButton);
        
        background.add(panel);
        background.revalidate();
        background.repaint();
    }
    
    private void showPage3() {
        // Generate card and PIN
        generatedCardNumber = Account_number.generateATMCardNumber();
        generatedPIN = generatePIN();
        
        if (currentPanel != null) {
            background.remove(currentPanel);
        }
        
        JPanel panel = new JPanel();
        panel.setBounds(50, 50, 900, 600);
        panel.setLayout(null);
        panel.setBackground(new Color(255, 255, 255, 240));
        panel.setBorder(BorderFactory.createLineBorder(new Color(220, 180, 50), 3));
        currentPanel = panel;
        
        JLabel formNoLabel = new JLabel("Form No: " + formNumber);
        formNoLabel.setBounds(750, 20, 150, 30);
        formNoLabel.setFont(new Font("Monospaced", Font.PLAIN, 14));
        panel.add(formNoLabel);
        
        JLabel pageLabel = new JLabel("Page 3: Account Details");
        pageLabel.setBounds(300, 30, 400, 30);
        pageLabel.setFont(new Font("Monospaced", Font.BOLD, 20));
        panel.add(pageLabel);
        
        int y = 100;
        int labelX = 80;
        
        // Account Type
        JLabel accountTypeLabel = new JLabel("Account Type:");
        accountTypeLabel.setBounds(labelX, y, 200, 30);
        accountTypeLabel.setFont(new Font("Monospaced", Font.BOLD, 16));
        panel.add(accountTypeLabel);
        y += 40;
        
        accountTypeGroup = new ButtonGroup();
        savingAccount = new JRadioButton("Saving Account");
        savingAccount.setBounds(labelX + 20, y, 200, 30);
        savingAccount.setBackground(new Color(255, 255, 255, 240));
        currentAccount = new JRadioButton("Current Account");
        currentAccount.setBounds(labelX + 20, y + 35, 200, 30);
        currentAccount.setBackground(new Color(255, 255, 255, 240));
        fixedAccount = new JRadioButton("Fixed Deposit Account");
        fixedAccount.setBounds(labelX + 300, y, 250, 30);
        fixedAccount.setBackground(new Color(255, 255, 255, 240));
        recurringAccount = new JRadioButton("Recurring Deposit Account");
        recurringAccount.setBounds(labelX + 300, y + 35, 250, 30);
        recurringAccount.setBackground(new Color(255, 255, 255, 240));
        
        accountTypeGroup.add(savingAccount);
        accountTypeGroup.add(currentAccount);
        accountTypeGroup.add(fixedAccount);
        accountTypeGroup.add(recurringAccount);
        panel.add(savingAccount);
        panel.add(currentAccount);
        panel.add(fixedAccount);
        panel.add(recurringAccount);
        y += 80;
        
        // Card Number
        JLabel cardLabel = new JLabel("Card Number:");
        cardLabel.setBounds(labelX, y, 200, 30);
        cardLabel.setFont(new Font("Monospaced", Font.BOLD, 16));
        panel.add(cardLabel);
        
        JLabel cardValue = new JLabel("XXXX-XXXX-XXXX-XXXX");
        cardValue.setBounds(400, y, 300, 30);
        cardValue.setFont(new Font("Monospaced", Font.BOLD, 16));
        cardValue.setForeground(new Color(120, 120, 120));
        panel.add(cardValue);
        
        JLabel cardNote = new JLabel("(Your 16-digit Card number)");
        cardNote.setBounds(labelX, y + 25, 250, 20);
        cardNote.setFont(new Font("Arial", Font.ITALIC, 11));
        panel.add(cardNote);
        
        JLabel cardNote2 = new JLabel("It would appear on ATM Card/Cheque Book and Statements");
        cardNote2.setBounds(400, y + 25, 450, 20);
        cardNote2.setFont(new Font("Arial", Font.ITALIC, 11));
        panel.add(cardNote2);
        y += 60;
        
        // PIN
        JLabel pinLabel = new JLabel("PIN:");
        pinLabel.setBounds(labelX, y, 200, 30);
        pinLabel.setFont(new Font("Monospaced", Font.BOLD, 16));
        panel.add(pinLabel);
        
        JLabel pinValue = new JLabel("XXXX");
        pinValue.setBounds(400, y, 100, 30);
        pinValue.setFont(new Font("Monospaced", Font.BOLD, 16));
        pinValue.setForeground(new Color(0, 120, 0));
        panel.add(pinValue);
        
        JLabel pinNote = new JLabel("(4-digit password)");
        pinNote.setBounds(labelX, y + 25, 250, 20);
        pinNote.setFont(new Font("Arial", Font.ITALIC, 11));
        panel.add(pinNote);
        y += 60;
        
        // Services Required
        JLabel servicesLabel = new JLabel("Services Required:");
        servicesLabel.setBounds(labelX, y, 300, 30);
        servicesLabel.setFont(new Font("Monospaced", Font.BOLD, 16));
        panel.add(servicesLabel);
        y += 40;
        
        atmCardCheck = new JCheckBox("ATM CARD");
        atmCardCheck.setBounds(labelX + 20, y, 200, 30);
        atmCardCheck.setBackground(new Color(255, 255, 255, 240));
        atmCardCheck.setSelected(true);
        panel.add(atmCardCheck);
        
        internetBankingCheck = new JCheckBox("Internet Banking");
        internetBankingCheck.setBounds(labelX + 300, y, 200, 30);
        internetBankingCheck.setBackground(new Color(255, 255, 255, 240));
        panel.add(internetBankingCheck);
        y += 35;
        
        mobileBankingCheck = new JCheckBox("Mobile Banking");
        mobileBankingCheck.setBounds(labelX + 20, y, 200, 30);
        mobileBankingCheck.setBackground(new Color(255, 255, 255, 240));
        panel.add(mobileBankingCheck);
        
        emailAlertsCheck = new JCheckBox("EMAIL Alerts");
        emailAlertsCheck.setBounds(labelX + 300, y, 200, 30);
        emailAlertsCheck.setBackground(new Color(255, 255, 255, 240));
        panel.add(emailAlertsCheck);
        y += 35;
        
        chequeBookCheck = new JCheckBox("Cheque Book");
        chequeBookCheck.setBounds(labelX + 20, y, 200, 30);
        chequeBookCheck.setBackground(new Color(255, 255, 255, 240));
        panel.add(chequeBookCheck);
        
        eStatementCheck = new JCheckBox("E-Statement");
        eStatementCheck.setBounds(labelX + 300, y, 200, 30);
        eStatementCheck.setBackground(new Color(255, 255, 255, 240));
        panel.add(eStatementCheck);
        y += 50;
        
        // Declaration
        declarationCheck = new JCheckBox("I hereby declares that the above entered details correct to th best of my knowledge.");
        declarationCheck.setBounds(80, y, 700, 30);
        declarationCheck.setBackground(new Color(255, 255, 255, 240));
        declarationCheck.setFont(new Font("Arial", Font.PLAIN, 12));
        panel.add(declarationCheck);
        
        // Submit and Cancel Buttons
        submitButton = new JButton("Submit");
        submitButton.setBounds(300, 540, 150, 40);
        submitButton.setBackground(Color.BLACK);
        submitButton.setForeground(Color.WHITE);
        submitButton.setFont(new Font("Monospaced", Font.BOLD, 14));
        submitButton.setFocusable(false);
        submitButton.addActionListener(this);
        panel.add(submitButton);
        
        cancelButton = new JButton("Cancel");
        cancelButton.setBounds(500, 540, 150, 40);
        cancelButton.setBackground(Color.BLACK);
        cancelButton.setForeground(Color.WHITE);
        cancelButton.setFont(new Font("Monospaced", Font.BOLD, 14));
        cancelButton.setFocusable(false);
        cancelButton.addActionListener(e -> {
            this.dispose();
            new LoginUser();
        });
        panel.add(cancelButton);
        
        background.add(panel);
        background.revalidate();
        background.repaint();
    }
    
    private String generatePIN() {
        Random random = new Random();
        return String.format("%04d", random.nextInt(10000));
    }
    
    private String maskCardNumber(String cardNumber) {
        if (cardNumber.length() == 16) {
            return "XXXX-XXXX-XXXX-" + cardNumber.substring(12);
        }
        return cardNumber;
    }
    
    private void addLabel(JPanel panel, String text, int x, int y) {
        JLabel label = new JLabel(text);
        label.setBounds(x, y, 250, 30);
        label.setFont(new Font("Monospaced", Font.BOLD, 14));
        panel.add(label);
    }
    
    private JTextField addTextField(JPanel panel, int x, int y, int width) {
        JTextField field = new JTextField();
        field.setBounds(x, y, width, 30);
        field.setFont(new Font("Arial", Font.PLAIN, 14));
        panel.add(field);
        return field;
    }
    
    private int calculateAge(String dobString) {
        try {
            // Parse DOB string (DD/MM/YYYY format)
            String[] parts = dobString.trim().split("/");
            if (parts.length != 3) {
                return 0; // Invalid format
            }
            
            int day = Integer.parseInt(parts[0]);
            int month = Integer.parseInt(parts[1]);
            int year = Integer.parseInt(parts[2]);
            
            // Get current date
            java.util.Calendar today = java.util.Calendar.getInstance();
            int currentYear = today.get(java.util.Calendar.YEAR);
            int currentMonth = today.get(java.util.Calendar.MONTH) + 1; // Calendar.MONTH is 0-based
            int currentDay = today.get(java.util.Calendar.DAY_OF_MONTH);
            
            // Calculate age
            int age = currentYear - year;
            
            // Adjust if birthday hasn't occurred this year
            if (currentMonth < month || (currentMonth == month && currentDay < day)) {
                age--;
            }
            
            return age;
            
        } catch (NumberFormatException e) {
            return 0; // Return 0 if parsing fails
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == nextButton) {
            // Validate Page 1
            if (nameField.getText().trim().isEmpty() || fatherField.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill all required fields!", "Missing Information", JOptionPane.WARNING_MESSAGE);
                return;
            }
            showPage2();
        } else if (e.getSource() == submitButton) {
            // Validate Page 3
            if (!declarationCheck.isSelected()) {
                JOptionPane.showMessageDialog(this, "Please accept the declaration!", "Declaration Required", JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            if (!savingAccount.isSelected() && !currentAccount.isSelected() && 
                !fixedAccount.isSelected() && !recurringAccount.isSelected()) {
                JOptionPane.showMessageDialog(this, "Please select an account type!", "Account Type Required", JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            // Validate CNIC from Page 2
            if (aadharField.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "CNIC Number is required! Please go back and fill it.", "Missing CNIC", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            // Save essential data to file
            saveToFile();
        }
    }
    
    private void saveToFile() {
        try {
            int acc_nm = Account_number.get_Account_number();
            String name = nameField.getText();
            String father = fatherField.getText();
            
            // Calculate age from DOB field
            String dobString = dobField.getText().trim();
            int age = calculateAge(dobString);
            
            // Validate age
            if (age <= 0 || age > 120) {
                JOptionPane.showMessageDialog(this, 
                    "Invalid Date of Birth! Please check the format (DD/MM/YYYY)", 
                    "Invalid DOB", 
                    JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            // Handle empty CNIC field properly
            long cnic = 0;
            String cnicText = aadharField.getText().replaceAll("[^0-9]", "");
            if (!cnicText.isEmpty()) {
                cnic = Long.parseLong(cnicText);
            }
            
            // Use a default phone or collect it separately
            long phone = 1234567890L; // You might want to add phone field
            
            int initialDeposit = 1000; // Default initial deposit
            
            FileWriter fw = new FileWriter(myfile, true);
            fw.write("\n" + acc_nm + " " + name + " " + age + " " + father + " " + 
                     phone + " " + cnic + " " + initialDeposit + " " + 
                     generatedCardNumber + " " + generatedPIN);
            fw.close();
            
            // Show success message with card and PIN details
            String message = String.format(
                "Account Created Successfully!\n\n" +
                "Account Number: %d\n" +
                "Name: %s\n" +
                "Age: %d\n" +
                "Card Number: %s\n" +
                "PIN: %s\n\n" +
                "Please save these details securely!",
                acc_nm, name, age, generatedCardNumber, generatedPIN
            );
            
            JOptionPane.showMessageDialog(this, message, "Success", JOptionPane.INFORMATION_MESSAGE);
            
            this.dispose();
            new LoginUser();
            
        } catch (IOException e1) {
            e1.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error creating account: " + e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}