package com.example;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Scanner;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class LoginUser extends JFrame implements ActionListener {
    
    JLabel titleLabel, cardLabel, pinLabel;
    JButton atmSignInButton, clearButton, signUpButton, bankOfficerButton;
    JTextField cardField;
    JPasswordField pinField;
    JLabel background;
    File myfile = new File("Bank_managemnet.txt");
    
    // Elite colors
    private final Color GOLD = new Color(212, 175, 55);
    private final Color NAVY = new Color(20, 30, 60);
    private final Color LIGHT_BG = new Color(245, 245, 250);
    
    public LoginUser() {
        
        this.setSize(550, 650);
        ImageIcon icon2 = new ImageIcon("C:\\Users\\Hp\\Downloads\\mylogo.jpg");
        this.setIconImage(icon2.getImage());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("IMPERIAL CROWN BANK - ATM");
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        
        // Background
        
        background = new JLabel();
        background.setBounds(0, 0, 550, 650);
        background.setLayout(null);
        background.setBackground(Color.BLACK);
        background.setOpaque(true);
        
        // Crown Logo
        JLabel crownLabel = new JLabel("♔", SwingConstants.CENTER);
        crownLabel.setBounds(200, 40, 150, 70);
        crownLabel.setFont(new Font("Serif", Font.BOLD, 64));
        crownLabel.setForeground(GOLD);
        background.add(crownLabel);
        
        // Bank Title
        titleLabel = new JLabel("IMPERIAL CROWN BANK", SwingConstants.CENTER);
        titleLabel.setBounds(50, 120, 450, 40);
        titleLabel.setFont(new Font("Serif", Font.BOLD, 28));
        titleLabel.setForeground(GOLD);
        background.add(titleLabel);
        
        // Subtitle
        JLabel subtitleLabel = new JLabel("Private Banking Services", SwingConstants.CENTER);
        subtitleLabel.setBounds(50, 165, 450, 20);
        subtitleLabel.setFont(new Font("Sans-serif", Font.PLAIN, 12));
        subtitleLabel.setForeground(new Color(180, 180, 180));
        background.add(subtitleLabel);
        
        // White Form Panel
        JPanel formPanel = new JPanel();
        formPanel.setBounds(75, 220, 400, 320);
        formPanel.setBackground(Color.WHITE);
        formPanel.setLayout(null);
        formPanel.setBorder(BorderFactory.createLineBorder(GOLD, 3));
        
        // Card Number Label
        cardLabel = new JLabel("CARD NUMBER");
        cardLabel.setBounds(40, 40, 320, 20);
        cardLabel.setFont(new Font("Sans-serif", Font.BOLD, 11));
        cardLabel.setForeground(NAVY);
        formPanel.add(cardLabel);
        
        // Card Number Field
        cardField = new JTextField();
        cardField.setBounds(40, 65, 320, 40);
        cardField.setFont(new Font("Monospaced", Font.PLAIN, 15));
        cardField.setBackground(LIGHT_BG);
        cardField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
            new EmptyBorder(5, 12, 5, 12)
        ));
        formPanel.add(cardField);
        
        // PIN Label
        pinLabel = new JLabel("PIN CODE");
        pinLabel.setBounds(40, 125, 320, 20);
        pinLabel.setFont(new Font("Sans-serif", Font.BOLD, 11));
        pinLabel.setForeground(NAVY);
        formPanel.add(pinLabel);
        
        // PIN Field
        pinField = new JPasswordField();
        pinField.setBounds(40, 150, 320, 40);
        pinField.setFont(new Font("Monospaced", Font.BOLD, 18));
        pinField.setEchoChar('●');
        pinField.setBackground(LIGHT_BG);
        pinField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
            new EmptyBorder(5, 12, 5, 12)
        ));
        formPanel.add(pinField);
        
        // Sign In Button
        atmSignInButton = new JButton("SIGN IN");
        atmSignInButton.setBounds(40, 220, 320, 45);
        atmSignInButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        atmSignInButton.setBackground(GOLD);
        atmSignInButton.setForeground(NAVY);
        atmSignInButton.setFont(new Font("Sans-serif", Font.BOLD, 14));
        atmSignInButton.setFocusable(false);
        atmSignInButton.setBorder(null);
        atmSignInButton.addActionListener(this);
        formPanel.add(atmSignInButton);
        
        background.add(formPanel);
        
        // Clear Button
        clearButton = new JButton("CLEAR");
        clearButton.setBounds(75, 560, 120, 40);
        clearButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        clearButton.setBackground(new Color(70, 70, 70));
        clearButton.setForeground(Color.WHITE);
        clearButton.setFont(new Font("Sans-serif", Font.BOLD, 11));
        clearButton.setFocusable(false);
        clearButton.setBorder(null);
        clearButton.addActionListener(this);
        background.add(clearButton);
        
        // Sign Up Button
        signUpButton = new JButton("CREATE ACCOUNT");
        signUpButton.setBounds(210, 560, 150, 40);
        signUpButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        signUpButton.setBackground(Color.WHITE);
        signUpButton.setForeground(NAVY);
        signUpButton.setFont(new Font("Sans-serif", Font.BOLD, 11));
        signUpButton.setFocusable(false);
        signUpButton.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        signUpButton.addActionListener(this);
        background.add(signUpButton);
        
        // Bank Officer Button
        bankOfficerButton = new JButton("OFFICER");
        bankOfficerButton.setBounds(375, 560, 100, 40);
        bankOfficerButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        bankOfficerButton.setBackground(NAVY);
        bankOfficerButton.setForeground(GOLD);
        bankOfficerButton.setFont(new Font("Sans-serif", Font.BOLD, 10));
        bankOfficerButton.setFocusable(false);
        bankOfficerButton.setBorder(BorderFactory.createLineBorder(GOLD, 1));
        bankOfficerButton.addActionListener(this);
        background.add(bankOfficerButton);
        
        this.setContentPane(background);
        this.setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        if (e.getSource() == atmSignInButton) {
            String cardNumber = cardField.getText().trim().replace("-", "");
            String pin = new String(pinField.getPassword()).trim();
            
            if (cardNumber.isEmpty() || pin.isEmpty()) {
                JOptionPane.showMessageDialog(this, 
                    "Please enter both Card Number and PIN!", 
                    "Missing Information", 
                    JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            if (verifyCardAndPIN(cardNumber, pin)) {
                this.dispose();
                new ATM_deposit();
            } else {
                JOptionPane.showMessageDialog(this, 
                    "Invalid Card Number or PIN!", 
                    "Authentication Failed", 
                    JOptionPane.ERROR_MESSAGE);
                pinField.setText("");
            }
            
        } else if (e.getSource() == clearButton) {
            cardField.setText("");
            pinField.setText("");
            
        } else if (e.getSource() == signUpButton) {
            this.dispose();
            new userinfo();
            
        } else if (e.getSource() == bankOfficerButton) {
            showBankOfficerLogin();
        }
    }
    
    private boolean verifyCardAndPIN(String cardNumber, String pin) {
        try {
            Scanner sc = new Scanner(myfile);
            while (sc.hasNext()) {
                int acc_num = sc.nextInt();
                
                StringBuilder nameBuilder = new StringBuilder();
                while (!sc.hasNextInt() && sc.hasNext()) {
                    nameBuilder.append(sc.next()).append(" ");
                }
                
                int age = sc.nextInt();
                
                StringBuilder fatherBuilder = new StringBuilder();
                while (!sc.hasNextLong() && sc.hasNext()) {
                    fatherBuilder.append(sc.next()).append(" ");
                }
                
                long phone = sc.nextLong();
                long cnic = sc.nextLong();
                int deposit = sc.nextInt();
                String atmCard = sc.next();
                
                String storedPIN = "";
                if (sc.hasNext()) {
                    storedPIN = sc.next();
                }
                
                String storedCardNormalized = atmCard.replace("-", "");
                
                if (storedCardNormalized.equalsIgnoreCase(cardNumber) && 
                    storedPIN.equals(pin)) {
                    sc.close();
                    return true;
                }
            }
            sc.close();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, 
                "Error reading database: " + e.getMessage(), 
                "System Error", 
                JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }
    
    private void showBankOfficerLogin() {
        JTextField usernameField = new JTextField();
        JPasswordField passwordField = new JPasswordField();
        
        Object[] message = {
            "Username:", usernameField,
            "Password:", passwordField
        };
        
        int option = JOptionPane.showConfirmDialog(this, message, 
            "Bank Officer Login", JOptionPane.OK_CANCEL_OPTION);
        
        if (option == JOptionPane.OK_OPTION) {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());
            
            if (username.equals("Ahsan") && password.equals("64164")) {
                this.dispose();
                new Menu();
            } else {
                JOptionPane.showMessageDialog(this, 
                    "Invalid username or password!", 
                    "Login Failed", 
                    JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    public static void main(String[] args) {
        new LoginUser();
    }
}