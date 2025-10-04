package com.example;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.util.*;
import javax.swing.*;

public class Delete_acc extends JFrame implements ActionListener {
    
    JButton deleteButton;
    variables obj = new variables();
    File myfile = new File("Bank_managemnet.txt");
    JTextField input_accField;
    JButton button, button2;
    JPanel mainPanel;
    JPanel displayPanel;
    
    public Delete_acc() {
        this.setSize(1000, 800);
        this.setTitle("IMPERTIAL CROWN BANK - Account Deletion");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        
        ImageIcon icon = new ImageIcon("C:\\Users\\Hp\\Downloads\\mylogo.jpg");
        this.setIconImage(icon.getImage());
        
        // Load background image
        ImageIcon bgIcon = new ImageIcon("C:\\Users\\Hp\\Downloads\\mylogo.jpg");
        Image bgImg = bgIcon.getImage();
        Image scaledBgImg = bgImg.getScaledInstance(1000, 800, Image.SCALE_SMOOTH);
        
        // Main panel with background image and overlay
        mainPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.drawImage(scaledBgImg, 0, 0, getWidth(), getHeight(), this);
                g2d.setColor(new Color(0, 0, 0, 160));
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        mainPanel.setLayout(null);
        
        // Header
        JPanel headerPanel = new JPanel();
        headerPanel.setBounds(0, 0, 1000, 80);
        headerPanel.setBackground(new Color(139, 0, 0)); // Dark red for deletion
        headerPanel.setLayout(null);
        
        JLabel bankLabel = new JLabel("IMPERTIAL CROWN BANK", SwingConstants.CENTER);
        bankLabel.setBounds(0, 10, 1000, 40);
        bankLabel.setFont(new Font("Monospaced", Font.BOLD, 32));
        bankLabel.setForeground(Color.WHITE);
        headerPanel.add(bankLabel);
        
        JLabel subtitleLabel = new JLabel("Account Deletion Service - Handle with Care", SwingConstants.CENTER);
        subtitleLabel.setBounds(0, 50, 1000, 25);
        subtitleLabel.setFont(new Font("Monospaced", Font.ITALIC, 14));
        subtitleLabel.setForeground(new Color(255, 255, 255, 200));
        headerPanel.add(subtitleLabel);
        
        mainPanel.add(headerPanel);
        
        // Warning icon
        JLabel warningLabel = new JLabel("⚠", SwingConstants.CENTER);
        warningLabel.setBounds(450, 95, 100, 40);
        warningLabel.setFont(new Font("Dialog", Font.PLAIN, 40));
        warningLabel.setForeground(new Color(255, 193, 7));
        mainPanel.add(warningLabel);
        
        // Search section
        JLabel searchLabel = new JLabel("Enter Account Number:");
        searchLabel.setBounds(200, 150, 250, 30);
        searchLabel.setForeground(new Color(255, 193, 7));
        searchLabel.setFont(new Font("Monospaced", Font.BOLD, 18));
        mainPanel.add(searchLabel);
        
        input_accField = new JTextField();
        input_accField.setBounds(450, 150, 200, 35);
        input_accField.setFont(new Font("Monospaced", Font.PLAIN, 16));
        mainPanel.add(input_accField);
        
        button = new JButton("SEARCH");
        button.setBounds(670, 147, 130, 40);
        button.setBackground(new Color(255, 193, 7));
        button.setForeground(Color.BLACK);
        button.setFont(new Font("Monospaced", Font.BOLD, 14));
        button.setFocusable(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.addActionListener(this);
        mainPanel.add(button);
        
        // Main menu button
        button2 = new JButton("RETURN TO MAIN MENU");
        button2.setBounds(730, 720, 220, 45);
        button2.setBackground(new Color(184, 134, 11));
        button2.setForeground(Color.WHITE);
        button2.setFont(new Font("Monospaced", Font.BOLD, 13));
        button2.setFocusable(false);
        button2.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button2.addActionListener(this);
        mainPanel.add(button2);
        
        this.setContentPane(mainPanel);
        this.revalidate();
        this.repaint();
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == button) {
            button.setEnabled(false);
            int num = Integer.parseInt(input_accField.getText());
            int count = 0;
            
            if (displayPanel != null) {
                mainPanel.remove(displayPanel);
            }
            
            displayPanel = new JPanel();
            displayPanel.setBounds(100, 230, 800, 470);
            displayPanel.setLayout(null);
            displayPanel.setBackground(new Color(245, 245, 245));
            displayPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(220, 53, 69), 4),
                BorderFactory.createEmptyBorder(20, 30, 20, 30)
            ));

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
                    }

                    if (acc_num == num) {
                        count = 1;
                        createDeletionConfirmation(displayPanel, num);
                        break;
                    }
                }
                sc.close();

                if (count == 0) {
                    JLabel messageLabel = new JLabel("Account Not Found", SwingConstants.CENTER);
                    messageLabel.setForeground(new Color(220, 53, 69));
                    messageLabel.setFont(new Font("Monospaced", Font.BOLD, 24));
                    messageLabel.setBounds(0, 180, 800, 40);
                    displayPanel.add(messageLabel);
                }
            } catch (Exception e1) {
                e1.printStackTrace();
            }

            mainPanel.add(displayPanel);
            mainPanel.revalidate();
            mainPanel.repaint();
        }
        
        if (e.getSource() == button2) {
            this.dispose();
            new Menu();
        }
    }
    
    private void createDeletionConfirmation(JPanel panel, int accountNum) {
        // Warning message
        JLabel warningTitle = new JLabel("⚠ ACCOUNT DELETION WARNING", SwingConstants.CENTER);
        warningTitle.setBounds(0, 10, 740, 35);
        warningTitle.setFont(new Font("Monospaced", Font.BOLD, 20));
        warningTitle.setForeground(new Color(220, 53, 69));
        panel.add(warningTitle);
        
        JLabel warningMsg = new JLabel("This action cannot be undone. Please review carefully.", SwingConstants.CENTER);
        warningMsg.setBounds(0, 45, 740, 25);
        warningMsg.setFont(new Font("Monospaced", Font.ITALIC, 13));
        warningMsg.setForeground(new Color(100, 100, 100));
        panel.add(warningMsg);
        
        int yPos = 90;
        int spacing = 60;
        
        // Account Holder Name
        addInfoRow(panel, "ACCOUNT HOLDER NAME", obj.name, yPos);
        yPos += spacing;
        
        // Father's Name
        addInfoRow(panel, "FATHER'S NAME", obj.Father_name, yPos);
        yPos += spacing;
        
        // Age
        addInfoRow(panel, "AGE", String.valueOf(obj.age), yPos);
        yPos += spacing;
        
        // Phone Number
        addInfoRow(panel, "PHONE NUMBER", String.valueOf(obj.Phone_number), yPos);
        yPos += spacing;
        
        // CNIC
        addInfoRow(panel, "CNIC NUMBER", String.valueOf(obj.CNIC), yPos);
        
        // Delete button
        deleteButton = new JButton("CONFIRM DELETION");
        deleteButton.setBounds(220, 370, 300, 50);
        deleteButton.setBackground(new Color(220, 53, 69));
        deleteButton.setForeground(Color.WHITE);
        deleteButton.setFont(new Font("Monospaced", Font.BOLD, 16));
        deleteButton.setFocusable(false);
        deleteButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        deleteButton.addActionListener(ae -> {
            int confirm = JOptionPane.showConfirmDialog(this,
                "Are you absolutely sure you want to delete this account?\nThis action is PERMANENT and IRREVERSIBLE!",
                "Final Confirmation Required",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE);
            
            if (confirm == JOptionPane.YES_OPTION) {
                deleteAccount(accountNum);
            }
        });
        panel.add(deleteButton);
    }
    
    private void addInfoRow(JPanel panel, String label, String value, int y) {
        JLabel fieldLabel = new JLabel(label);
        fieldLabel.setBounds(30, y, 300, 20);
        fieldLabel.setFont(new Font("Monospaced", Font.BOLD, 12));
        fieldLabel.setForeground(new Color(70, 70, 70));
        panel.add(fieldLabel);
        
        JLabel valueLabel = new JLabel(value);
        valueLabel.setBounds(30, y + 20, 680, 28);
        valueLabel.setFont(new Font("Monospaced", Font.BOLD, 16));
        valueLabel.setForeground(new Color(220, 53, 69));
        panel.add(valueLabel);
    }

    private void deleteAccount(int num) {
        try {
            deleteButton.setEnabled(false);
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
                String father = fatherBuilder.toString().trim();
                long phone = sc.nextLong();
                long cnic = sc.nextLong();
                int deposit = sc.nextInt();
                String cardNum = sc.next();
                String pin = sc.hasNext() ? sc.next() : "";

                if (acc_num != num) {
                    fw.write(acc_num + " " + name + " " + age + " " + father + " " + 
                             phone + " " + cnic + " " + deposit + " " + cardNum);
                    if (!pin.isEmpty()) {
                        fw.write(" " + pin);
                    }
                    fw.write("\n");
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
            
            JOptionPane.showMessageDialog(this,
                "Account has been permanently deleted from the system.",
                "Deletion Complete",
                JOptionPane.INFORMATION_MESSAGE);
            
            mainPanel.remove(displayPanel);
            mainPanel.revalidate();
            mainPanel.repaint();
            button.setEnabled(true);
            input_accField.setText("");

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this,
                "Error deleting account: " + e.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE);
        }
    }
}
