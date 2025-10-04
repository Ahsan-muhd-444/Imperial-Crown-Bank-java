package com.example;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ATM_deposit extends JFrame implements ActionListener {
    
    variables obj = new variables();
    File myfile = new File("Bank_managemnet.txt");
    JTextField input_accField;
    JButton button;
    JLabel background;
    JPanel currentPanel;
    
    public ATM_deposit() {
        
        this.setSize(800, 800);
        this.setTitle("IMPERTIAL CROWN BANK");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ImageIcon icon = new ImageIcon("C:\\Users\\Hp\\Downloads\\ATM.jpg");
        this.setIconImage(icon.getImage());
        Image scaled = icon.getImage().getScaledInstance(800, 800, Image.SCALE_SMOOTH);
        background = new JLabel(new ImageIcon(scaled));
        this.setIconImage(icon.getImage());
        background.setBounds(0, 0, 800, 800);
        this.setLocationRelativeTo(null);
        background.setLayout(null);
        
        JLabel cashJLabel = new JLabel("Enter your card number :");
        cashJLabel.setBounds(120, 80, 350, 30);
        cashJLabel.setForeground(new Color(25, 50, 130)); // Dark blue matching ATM panels
        cashJLabel.setFont(new Font("Monospaced", Font.BOLD, 18));
        background.add(cashJLabel);
        
        input_accField = new JTextField();
        input_accField.setBounds(400, 80, 200, 30);
        input_accField.setBackground(Color.WHITE);
        input_accField.setFont(new Font("Monospaced", Font.PLAIN, 14));
        background.add(input_accField);
        
        button = new JButton("VERIFY");
        button.setBounds(600, 80, 100, 30);
        button.setFocusable(false);
        button.setBackground(new Color(220, 180, 50)); // Bright gold like keypad
        button.setForeground(new Color(40, 40, 40));
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.addActionListener(this);
        background.add(button);
        
        this.setContentPane(background);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        if (e.getSource() == button) {
            button.setEnabled(false);
            String card_nm = input_accField.getText().trim().replace("-", ""); // Remove hyphens
            int count = 0;
            
            try {
                Scanner sc = new Scanner(myfile);
                while (sc.hasNext()) {
                    int acc_num = sc.nextInt();
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
                        obj.pin = "0000"; // Default PIN for old accounts
                    }

                    // Remove hyphens from stored card number for comparison
                    String storedCardNormalized = obj.atm_card.replace("-", "");
                    
                    if (storedCardNormalized.equalsIgnoreCase(card_nm)) {
                        count = 1;
                        sc.close();
                        
                        // Ask for PIN
                        String enteredPIN = JOptionPane.showInputDialog(this, 
                            "Enter your 4-digit PIN:", 
                            "PIN Verification", 
                            JOptionPane.PLAIN_MESSAGE);
                        
                        if (enteredPIN == null) {
                            // User cancelled
                            button.setEnabled(true);
                            return;
                        }
                        
                        if (enteredPIN.equals(obj.pin)) {
                            // PIN correct - show account panel
                            showCurrentAccountPanel(acc_num, card_nm);
                        } else {
                            // PIN incorrect
                            JOptionPane.showMessageDialog(this, 
                                "Incorrect PIN! Access Denied.", 
                                "Authentication Failed", 
                                JOptionPane.ERROR_MESSAGE);
                            button.setEnabled(true);
                        }
                        break;
                    }
                }
                
                if (count == 0) {
                    JOptionPane.showMessageDialog(this, "Card number not found!", "Error", JOptionPane.ERROR_MESSAGE);
                    button.setEnabled(true);
                }
                
            } catch (Exception e1) {
                e1.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error reading file: " + e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                button.setEnabled(true);
            }
        }
    }
    
    private void showCurrentAccountPanel(int accountNumber, String cardNumber) {
        // Remove existing panel if any
        if (currentPanel != null) {
            background.remove(currentPanel);
        }
        
        JPanel panel1 = new JPanel();
        panel1.setBounds(125, 160, 380, 310);
        panel1.setLayout(null);
        panel1.setBackground(new Color(230, 230, 230)); // Light gray like ATM screen
        panel1.setBorder(javax.swing.BorderFactory.createLineBorder(new Color(100, 100, 100), 2));
        currentPanel = panel1;
        
        JLabel titleLabel = new JLabel("Account Verified Successfully!");
        titleLabel.setBounds(50, 20, 300, 30);
        titleLabel.setFont(new Font("Monospaced", Font.BOLD, 16));
        titleLabel.setForeground(new Color(25, 50, 130)); // Dark blue
        panel1.add(titleLabel);
        
        JButton currentAccButton = new JButton("Current Account");
        currentAccButton.setBounds(90, 70, 200, 50);
        currentAccButton.setBackground(new Color(220, 180, 50)); // Bright gold
        currentAccButton.setForeground(new Color(40, 40, 40));
        currentAccButton.setFont(new Font("Arial", Font.BOLD, 14));
        currentAccButton.setFocusable(false);
        currentAccButton.addActionListener(ae -> {
            showDepositPanel(accountNumber, cardNumber);
        });
        panel1.add(currentAccButton);
        
        JButton savingAccButton = new JButton("Saving Account");
        savingAccButton.setBounds(90, 130, 200, 50);
        savingAccButton.setBackground(new Color(220, 180, 50)); // Bright gold
        savingAccButton.setForeground(new Color(40, 40, 40));
        savingAccButton.setFont(new Font("Arial", Font.BOLD, 14));
        savingAccButton.setFocusable(false);
        savingAccButton.addActionListener(ae -> {
            JOptionPane.showMessageDialog(
                this, 
                "We are working on this section.\nIt will be ready soon!", 
                "Coming Soon", 
                JOptionPane.INFORMATION_MESSAGE
            );
        });
        panel1.add(savingAccButton);
        
        JButton mainMenuButton = new JButton("Main Menu");
        mainMenuButton.setBounds(90, 190, 200, 50);
        mainMenuButton.setBackground(new Color(220, 180, 50)); // Bright gold
        mainMenuButton.setForeground(new Color(40, 40, 40));
        mainMenuButton.setFont(new Font("Arial", Font.BOLD, 14));
        mainMenuButton.setFocusable(false);
        mainMenuButton.addActionListener(ae -> {
            this.dispose();
            new Menu();
        });
        panel1.add(mainMenuButton);
        
        background.add(panel1);
        background.revalidate();
        background.repaint();
    }
    
    private void showDepositPanel(int accountNumber, String cardNumber) {
        // Remove existing panel
        if (currentPanel != null) {
            background.remove(currentPanel);
        }
        
        JPanel depositPanel = new JPanel();
        depositPanel.setBounds(125, 160, 380, 310);
        depositPanel.setLayout(null);
        depositPanel.setBackground(new Color(230, 230, 230)); // Light gray like ATM screen
        depositPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new Color(100, 100, 100), 2));
        currentPanel = depositPanel;
        
        JLabel balanceLabel = new JLabel("Current Balance: Rs. " + obj.Deposit_money);
        balanceLabel.setBounds(30, 20, 320, 30);
        balanceLabel.setFont(new Font("Monospaced", Font.BOLD, 17));
        balanceLabel.setForeground(new Color(0, 120, 0)); // Professional green
        depositPanel.add(balanceLabel);
        
        JLabel depositLabel = new JLabel("Enter Amount to Deposit:");
        depositLabel.setBounds(30, 70, 300, 25);
        depositLabel.setFont(new Font("Monospaced", Font.BOLD, 14));
        depositLabel.setForeground(new Color(25, 50, 130)); // Dark blue
        depositPanel.add(depositLabel);
        
        JTextField depositField = new JTextField();
        depositField.setBounds(30, 105, 200, 35);
        depositField.setFont(new Font("Monospaced", Font.PLAIN, 16));
        depositField.setBackground(Color.WHITE);
        depositPanel.add(depositField);
        
        JButton depositButton = new JButton("DEPOSIT");
        depositButton.setBounds(245, 105, 110, 35);
        depositButton.setFocusable(false);
        depositButton.setBackground(new Color(40, 160, 40)); // Green like ENTER button
        depositButton.setForeground(Color.WHITE);
        depositButton.setFont(new Font("Arial", Font.BOLD, 14));
        depositButton.addActionListener(ae -> {
            try {
                int depositAmount = Integer.parseInt(depositField.getText());
                if (depositAmount <= 0) {
                    JOptionPane.showMessageDialog(this, "Please enter a valid amount!", "Invalid Amount", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                
                int newBalance = obj.Deposit_money + depositAmount;
                updateBankFile(accountNumber, cardNumber, newBalance);
                
                JOptionPane.showMessageDialog(this, 
                    "Deposit Successful!\nAmount Deposited: Rs. " + depositAmount + 
                    "\nNew Balance: Rs. " + newBalance, 
                    "Success", JOptionPane.INFORMATION_MESSAGE);
                
                // Update the balance label
                balanceLabel.setText("Current Balance: Rs. " + newBalance);
                obj.Deposit_money = newBalance;
                depositField.setText("");
                
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Please enter a valid number!", "Invalid Input", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error processing deposit: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
            }
        });
        depositPanel.add(depositButton);
        
        JButton backButton = new JButton("BACK");
        backButton.setBounds(30, 250, 110, 40);
        backButton.setBackground(new Color(220, 180, 50)); // Bright gold
        backButton.setForeground(new Color(40, 40, 40));
        backButton.setFont(new Font("Arial", Font.BOLD, 14));
        backButton.setFocusable(false);
        backButton.addActionListener(ae -> {
            showCurrentAccountPanel(accountNumber, cardNumber);
        });
        depositPanel.add(backButton);
        
        JButton mainMenuButton = new JButton("Main Menu");
        mainMenuButton.setBounds(180, 250, 130, 40);
        mainMenuButton.setBackground(new Color(220, 180, 50)); // Bright gold
        mainMenuButton.setForeground(new Color(40, 40, 40));
        mainMenuButton.setFont(new Font("Arial", Font.BOLD, 14));
        mainMenuButton.setFocusable(false);
        mainMenuButton.addActionListener(ae -> {
            this.dispose();
            new Menu();
        });
        depositPanel.add(mainMenuButton);
        
        background.add(depositPanel);
        background.revalidate();
        background.repaint();
    }
    
    private void updateBankFile(int accountNumber, String cardNumber, int newBalance) throws Exception {
        // Step 1: Read from original file and write to temp file with updated balance
        Scanner sc = new Scanner(myfile);
        FileWriter fw = new FileWriter("temp.txt", false);
        
        while (sc.hasNext()) {
            int acc_num = sc.nextInt();
            StringBuilder nameBuilder = new StringBuilder();
            while (!sc.hasNextInt() && sc.hasNext()) {
                nameBuilder.append(sc.next()).append(" ");
            }
            String name = nameBuilder.toString().trim();
            int age = sc.nextInt();
            StringBuilder fatherBuilder = new StringBuilder();
            while (!sc.hasNextLong() && sc.hasNext()) {
                fatherBuilder.append(sc.next()).append(" ");
            }
            String fatherName = fatherBuilder.toString().trim();
            long phoneNumber = sc.nextLong();
            long cnic = sc.nextLong();
            int depositMoney = sc.nextInt();
            String atmCard = sc.next();
            
            // Read PIN if it exists
            String pin = "";
            if (sc.hasNext()) {
                pin = sc.next();
            }
            
            // Normalize card numbers for comparison
            String storedCardNormalized = atmCard.replace("-", "");
            String inputCardNormalized = cardNumber.replace("-", "");
            
            // Update balance if this is the matching account
            if (storedCardNormalized.equalsIgnoreCase(inputCardNormalized)) {
                depositMoney = newBalance;
            }
            
            // Write to temporary file (include PIN if it exists)
            if (!pin.isEmpty()) {
                fw.write(acc_num + " " + name + " " + age + " " + fatherName + " " + 
                        phoneNumber + " " + cnic + " " + depositMoney + " " + atmCard + " " + pin + "\n");
            } else {
                fw.write(acc_num + " " + name + " " + age + " " + fatherName + " " + 
                        phoneNumber + " " + cnic + " " + depositMoney + " " + atmCard + "\n");
            }
        }
        
        fw.close();
        sc.close();
        
        // Step 2: Read from temp file and overwrite original file
        Scanner sc1 = new Scanner(new File("temp.txt"));
        FileWriter fw1 = new FileWriter(myfile, false);
        
        while (sc1.hasNextLine()) {
            fw1.write(sc1.nextLine() + "\n");
        }
        
        fw1.close();
        sc1.close();
    }
}