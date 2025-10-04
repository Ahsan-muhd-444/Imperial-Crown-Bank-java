package com.example;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;
import javax.swing.*;

public class ATM_withdraw extends JFrame implements ActionListener {
    
    variables obj = new variables();
    File myfile = new File("Bank_managemnet.txt");
    JTextField input_accField;
    JButton button;
    JLabel background;
    JPanel currentPanel;
    int currentAccountNumber;
    String currentCardNumber;
    
    public ATM_withdraw() {
        this.setSize(800, 800);
        ImageIcon icon2 = new ImageIcon("C:\\Users\\Hp\\Downloads\\mylogo.jpg");
        this.setIconImage(icon2.getImage());
        this.setTitle("IMPERTIAL CROWN BANK - ATM Services");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        
        ImageIcon icon = new ImageIcon("C:\\Users\\Hp\\Downloads\\ATM.jpg");
        this.setIconImage(icon.getImage());
        Image scaled = icon.getImage().getScaledInstance(800, 800, Image.SCALE_SMOOTH);
        background = new JLabel(new ImageIcon(scaled));
        background.setBounds(0, 0, 800, 800);
        background.setLayout(null);
        
        JLabel cashJLabel = new JLabel("Enter your card number:");
        cashJLabel.setBounds(120, 80, 350, 30);
        cashJLabel.setOpaque(true);
        cashJLabel.setBackground(new Color(0, 0, 0, 180));
        cashJLabel.setForeground(new Color(218, 165, 32));
        cashJLabel.setFont(new Font("Monospaced", Font.BOLD, 18));
        cashJLabel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        background.add(cashJLabel);
        
        input_accField = new JTextField();
        input_accField.setBounds(450, 80, 200, 30);
        input_accField.setBackground(Color.WHITE);
        input_accField.setFont(new Font("Monospaced", Font.PLAIN, 14));
        background.add(input_accField);
        
        button = new JButton("VERIFY");
        button.setBounds(660, 80, 100, 30);
        button.setFocusable(false);
        button.setBackground(new Color(220, 180, 50));
        button.setForeground(new Color(40, 40, 40));
        button.setFont(new Font("Monospaced", Font.BOLD, 14));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.addActionListener(this);
        background.add(button);
        
        this.setContentPane(background);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == button) {
            button.setEnabled(false);
            String card_nm = input_accField.getText().trim().replace("-", "");
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
                    
                    if (sc.hasNext()) {
                        obj.pin = sc.next();
                    } else {
                        obj.pin = "0000";
                    }

                    String storedCardNormalized = obj.atm_card.replace("-", "");
                    
                    if (storedCardNormalized.equalsIgnoreCase(card_nm)) {
                        count = 1;
                        sc.close();
                        
                        String enteredPIN = JOptionPane.showInputDialog(this, 
                            "Enter your 4-digit PIN:", 
                            "PIN Verification", 
                            JOptionPane.PLAIN_MESSAGE);
                        
                        if (enteredPIN == null) {
                            button.setEnabled(true);
                            return;
                        }
                        
                        if (enteredPIN.equals(obj.pin)) {
                            currentAccountNumber = acc_num;
                            currentCardNumber = card_nm;
                            showMainMenu();
                        } else {
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
    
    private void showMainMenu() {
        if (currentPanel != null) {
            background.remove(currentPanel);
        }
        
        JPanel panel = new JPanel();
        panel.setBounds(125, 140, 550, 520);
        panel.setLayout(null);
        panel.setBackground(new Color(245, 245, 245));
        panel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(184, 134, 11), 3),
            BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));
        currentPanel = panel;
        
        JLabel titleLabel = new JLabel("ATM SERVICES", SwingConstants.CENTER);
        titleLabel.setBounds(0, 10, 510, 35);
        titleLabel.setFont(new Font("Monospaced", Font.BOLD, 24));
        titleLabel.setForeground(new Color(184, 134, 11));
        panel.add(titleLabel);
        
        JLabel welcomeLabel = new JLabel("Welcome, " + obj.name, SwingConstants.CENTER);
        welcomeLabel.setBounds(0, 45, 510, 25);
        welcomeLabel.setFont(new Font("Monospaced", Font.ITALIC, 14));
        welcomeLabel.setForeground(new Color(100, 100, 100));
        panel.add(welcomeLabel);
        
        int y = 100;
        int spacing = 70;
        
        // Balance Inquiry
        JButton balanceButton = createMenuButton("BALANCE INQUIRY", 80, y);
        balanceButton.addActionListener(ae -> showBalanceInquiry());
        panel.add(balanceButton);
        y += spacing;
        
        // Withdraw Cash
        JButton withdrawButton = createMenuButton("WITHDRAW CASH", 80, y);
        withdrawButton.addActionListener(ae -> showWithdrawPanel());
        panel.add(withdrawButton);
        y += spacing;
        
        // Deposit Cash
        JButton depositButton = createMenuButton("DEPOSIT CASH", 80, y);
        depositButton.addActionListener(ae -> showDepositPanel());
        panel.add(depositButton);
        y += spacing;
        
        // Change PIN
        JButton changePinButton = createMenuButton("CHANGE PIN", 80, y);
        changePinButton.addActionListener(ae -> showChangePinPanel());
        panel.add(changePinButton);
        y += spacing;
        
        // Exit
        JButton exitButton = createMenuButton("EXIT", 80, y);
        exitButton.setBackground(new Color(220, 53, 69));
        exitButton.addActionListener(ae -> {
            this.dispose();
            new LoginUser();
        });
        panel.add(exitButton);
        
        background.add(panel);
        background.revalidate();
        background.repaint();
    }
    
    private JButton createMenuButton(String text, int x, int y) {
        JButton btn = new JButton(text);
        btn.setBounds(x, y, 350, 50);
        btn.setBackground(new Color(184, 134, 11));
        btn.setForeground(Color.WHITE);
        btn.setFont(new Font("Monospaced", Font.BOLD, 14));
        btn.setFocusable(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                if (btn.getBackground().getRGB() != new Color(220, 53, 69).getRGB()) {
                    btn.setBackground(new Color(218, 165, 32));
                }
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                if (btn.getBackground().getRGB() != new Color(220, 53, 69).getRGB()) {
                    btn.setBackground(new Color(184, 134, 11));
                }
            }
        });
        return btn;
    }
    
    private void showBalanceInquiry() {
        JOptionPane.showMessageDialog(this,
            String.format("Account Number: %d\n\nCurrent Balance: Rs. %,d\n\nAccount Holder: %s",
                currentAccountNumber, obj.Deposit_money, obj.name),
            "Balance Inquiry",
            JOptionPane.INFORMATION_MESSAGE);
    }
    
    private void showWithdrawPanel() {
        if (currentPanel != null) {
            background.remove(currentPanel);
        }
        
        JPanel panel = new JPanel();
        panel.setBounds(125, 140, 550, 520);
        panel.setLayout(null);
        panel.setBackground(new Color(245, 245, 245));
        panel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(184, 134, 11), 3),
            BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));
        currentPanel = panel;
        
        JLabel titleLabel = new JLabel("CASH WITHDRAWAL", SwingConstants.CENTER);
        titleLabel.setBounds(0, 20, 510, 35);
        titleLabel.setFont(new Font("Monospaced", Font.BOLD, 22));
        titleLabel.setForeground(new Color(184, 134, 11));
        panel.add(titleLabel);
        
        JLabel balanceLabel = new JLabel(String.format("Available Balance: Rs. %,d", obj.Deposit_money));
        balanceLabel.setBounds(80, 80, 350, 30);
        balanceLabel.setFont(new Font("Monospaced", Font.BOLD, 16));
        balanceLabel.setForeground(new Color(0, 120, 0));
        panel.add(balanceLabel);
        
        JLabel amountLabel = new JLabel("Enter Amount:");
        amountLabel.setBounds(80, 140, 200, 25);
        amountLabel.setFont(new Font("Monospaced", Font.BOLD, 14));
        panel.add(amountLabel);
        
        JTextField amountField = new JTextField();
        amountField.setBounds(80, 170, 250, 35);
        amountField.setFont(new Font("Monospaced", Font.PLAIN, 16));
        panel.add(amountField);
        
        JButton withdrawBtn = new JButton("WITHDRAW");
        withdrawBtn.setBounds(350, 170, 140, 35);
        withdrawBtn.setBackground(new Color(220, 53, 69));
        withdrawBtn.setForeground(Color.WHITE);
        withdrawBtn.setFont(new Font("Monospaced", Font.BOLD, 14));
        withdrawBtn.setFocusable(false);
        withdrawBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        withdrawBtn.addActionListener(ae -> {
            try {
                int amount = Integer.parseInt(amountField.getText());
                if (amount <= 0) {
                    JOptionPane.showMessageDialog(this, "Please enter a valid amount!", "Invalid Amount", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                if (amount > obj.Deposit_money) {
                    JOptionPane.showMessageDialog(this, "Insufficient balance!", "Transaction Failed", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                int newBalance = obj.Deposit_money - amount;
                updateBankFile(currentAccountNumber, currentCardNumber, newBalance);
                
                JOptionPane.showMessageDialog(this,
                    String.format("Withdrawal Successful!\n\nAmount Withdrawn: Rs. %,d\nNew Balance: Rs. %,d",
                        amount, newBalance),
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE);
                
                obj.Deposit_money = newBalance;
                showMainMenu();
                
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Please enter a valid number!", "Invalid Input", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        panel.add(withdrawBtn);
        
        JButton backBtn = new JButton("BACK TO MENU");
        backBtn.setBounds(150, 420, 200, 45);
        backBtn.setBackground(new Color(184, 134, 11));
        backBtn.setForeground(Color.WHITE);
        backBtn.setFont(new Font("Monospaced", Font.BOLD, 14));
        backBtn.setFocusable(false);
        backBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        backBtn.addActionListener(ae -> showMainMenu());
        panel.add(backBtn);
        
        background.add(panel);
        background.revalidate();
        background.repaint();
    }
    
    private void showDepositPanel() {
        if (currentPanel != null) {
            background.remove(currentPanel);
        }
        
        JPanel panel = new JPanel();
        panel.setBounds(125, 140, 550, 520);
        panel.setLayout(null);
        panel.setBackground(new Color(245, 245, 245));
        panel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(184, 134, 11), 3),
            BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));
        currentPanel = panel;
        
        JLabel titleLabel = new JLabel("CASH DEPOSIT", SwingConstants.CENTER);
        titleLabel.setBounds(0, 20, 510, 35);
        titleLabel.setFont(new Font("Monospaced", Font.BOLD, 22));
        titleLabel.setForeground(new Color(184, 134, 11));
        panel.add(titleLabel);
        
        JLabel balanceLabel = new JLabel(String.format("Current Balance: Rs. %,d", obj.Deposit_money));
        balanceLabel.setBounds(80, 80, 350, 30);
        balanceLabel.setFont(new Font("Monospaced", Font.BOLD, 16));
        balanceLabel.setForeground(new Color(0, 120, 0));
        panel.add(balanceLabel);
        
        JLabel amountLabel = new JLabel("Enter Amount:");
        amountLabel.setBounds(80, 140, 200, 25);
        amountLabel.setFont(new Font("Monospaced", Font.BOLD, 14));
        panel.add(amountLabel);
        
        JTextField amountField = new JTextField();
        amountField.setBounds(80, 170, 250, 35);
        amountField.setFont(new Font("Monospaced", Font.PLAIN, 16));
        panel.add(amountField);
        
        JButton depositBtn = new JButton("DEPOSIT");
        depositBtn.setBounds(350, 170, 140, 35);
        depositBtn.setBackground(new Color(40, 160, 40));
        depositBtn.setForeground(Color.WHITE);
        depositBtn.setFont(new Font("Monospaced", Font.BOLD, 14));
        depositBtn.setFocusable(false);
        depositBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        depositBtn.addActionListener(ae -> {
            try {
                int amount = Integer.parseInt(amountField.getText());
                if (amount <= 0) {
                    JOptionPane.showMessageDialog(this, "Please enter a valid amount!", "Invalid Amount", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                
                int newBalance = obj.Deposit_money + amount;
                updateBankFile(currentAccountNumber, currentCardNumber, newBalance);
                
                JOptionPane.showMessageDialog(this,
                    String.format("Deposit Successful!\n\nAmount Deposited: Rs. %,d\nNew Balance: Rs. %,d",
                        amount, newBalance),
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE);
                
                obj.Deposit_money = newBalance;
                showMainMenu();
                
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Please enter a valid number!", "Invalid Input", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        panel.add(depositBtn);
        
        JButton backBtn = new JButton("BACK TO MENU");
        backBtn.setBounds(150, 420, 200, 45);
        backBtn.setBackground(new Color(184, 134, 11));
        backBtn.setForeground(Color.WHITE);
        backBtn.setFont(new Font("Monospaced", Font.BOLD, 14));
        backBtn.setFocusable(false);
        backBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        backBtn.addActionListener(ae -> showMainMenu());
        panel.add(backBtn);
        
        background.add(panel);
        background.revalidate();
        background.repaint();
    }
    
    private void showChangePinPanel() {
        JPasswordField oldPinField = new JPasswordField();
        JPasswordField newPinField = new JPasswordField();
        JPasswordField confirmPinField = new JPasswordField();
        
        Object[] message = {
            "Enter Current PIN:", oldPinField,
            "Enter New PIN:", newPinField,
            "Confirm New PIN:", confirmPinField
        };
        
        int option = JOptionPane.showConfirmDialog(this, message, 
            "Change PIN", JOptionPane.OK_CANCEL_OPTION);
        
        if (option == JOptionPane.OK_OPTION) {
            String oldPin = new String(oldPinField.getPassword());
            String newPin = new String(newPinField.getPassword());
            String confirmPin = new String(confirmPinField.getPassword());
            
            if (!oldPin.equals(obj.pin)) {
                JOptionPane.showMessageDialog(this, "Current PIN is incorrect!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            if (newPin.length() != 4 || !newPin.matches("\\d{4}")) {
                JOptionPane.showMessageDialog(this, "New PIN must be 4 digits!", "Invalid PIN", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            if (!newPin.equals(confirmPin)) {
                JOptionPane.showMessageDialog(this, "New PIN and Confirm PIN do not match!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            try {
                changePIN(currentCardNumber, newPin);
                obj.pin = newPin;
                JOptionPane.showMessageDialog(this, "PIN changed successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error changing PIN: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    private void changePIN(String cardNumber, String newPIN) throws Exception {
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
            String pin = sc.hasNext() ? sc.next() : "";
            
            String storedCardNormalized = atmCard.replace("-", "");
            String inputCardNormalized = cardNumber.replace("-", "");
            
            if (storedCardNormalized.equalsIgnoreCase(inputCardNormalized)) {
                pin = newPIN;
            }
            
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
        
        Scanner sc1 = new Scanner(new File("temp.txt"));
        FileWriter fw1 = new FileWriter(myfile, false);
        while (sc1.hasNextLine()) {
            fw1.write(sc1.nextLine() + "\n");
        }
        fw1.close();
        sc1.close();
    }
    
    private void updateBankFile(int accountNumber, String cardNumber, int newBalance) throws Exception {
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
            String pin = sc.hasNext() ? sc.next() : "";
            
            String storedCardNormalized = atmCard.replace("-", "");
            String inputCardNormalized = cardNumber.replace("-", "");
            
            if (storedCardNormalized.equalsIgnoreCase(inputCardNormalized)) {
                depositMoney = newBalance;
            }
            
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
        
        Scanner sc1 = new Scanner(new File("temp.txt"));
        FileWriter fw1 = new FileWriter(myfile, false);
        while (sc1.hasNextLine()) {
            fw1.write(sc1.nextLine() + "\n");
        }
        fw1.close();
        sc1.close();
    }
}