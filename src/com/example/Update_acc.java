package com.example;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;
import javax.swing.*;

public class Update_acc extends JFrame implements ActionListener {
    
    JButton updateNameBtn, updateFatherBtn, updateAgeBtn, updatePhoneBtn, updateCnicBtn, saveButton;
    variables obj = new variables();
    File myfile = new File("Bank_managemnet.txt");
    JTextField input_accField;
    JButton button, button2;
    JPanel mainPanel;
    JPanel displayPanel;
    
    public Update_acc() {
        this.setSize(1000, 800);
        this.setTitle("IMPERTIAL CROWN BANK - Account Update");
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
        mainPanel = new JPanel(true) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                
                // Draw background image (with ImageObserver)
                g2d.drawImage(scaledBgImg, 0, 0, getWidth(), getHeight(), this);
                
                // Add semi-transparent dark overlay
                g2d.setColor(new Color(0, 0, 0, 150));
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        mainPanel.setLayout(null);
        
        // Header
        JPanel headerPanel = new JPanel();
        headerPanel.setBounds(0, 0, 1000, 80);
        headerPanel.setBackground(new Color(184, 134, 11));
        headerPanel.setLayout(null);
        
        JLabel bankLabel = new JLabel("IMPERTIAL CROWN BANK", SwingConstants.CENTER);
        bankLabel.setBounds(0, 10, 1000, 40);
        bankLabel.setFont(new Font("Monospaced", Font.BOLD, 32));
        bankLabel.setForeground(Color.WHITE);
        headerPanel.add(bankLabel);
        
        JLabel subtitleLabel = new JLabel("Account Modification Service", SwingConstants.CENTER);
        subtitleLabel.setBounds(0, 50, 1000, 25);
        subtitleLabel.setFont(new Font("Monospaced", Font.ITALIC, 14));
        subtitleLabel.setForeground(new Color(255, 255, 255, 200));
        headerPanel.add(subtitleLabel);
        
        mainPanel.add(headerPanel);
        
        // Search section
        JLabel searchLabel = new JLabel("Enter Account Number:");
        searchLabel.setBounds(200, 120, 250, 30);
        searchLabel.setForeground(new Color(218, 165, 32));
        searchLabel.setFont(new Font("Monospaced", Font.BOLD, 18));
        mainPanel.add(searchLabel);
        
        input_accField = new JTextField();
        input_accField.setBounds(450, 120, 200, 35);
        input_accField.setFont(new Font("Monospaced", Font.PLAIN, 16));
        mainPanel.add(input_accField);
        
        button = new JButton("SEARCH");
        button.setBounds(670, 117, 130, 40);
        button.setBackground(new Color(218, 165, 32));
        button.setForeground(Color.WHITE);
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
            displayPanel.setBounds(100, 200, 800, 500);
            displayPanel.setLayout(null);
            displayPanel.setBackground(new Color(245, 245, 245));
            displayPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(184, 134, 11), 3),
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
                        createEditableFields(displayPanel, num);
                        break;
                    }
                }
                sc.close();

                if (count == 0) {
                    JLabel messageLabel = new JLabel("Account Not Found", SwingConstants.CENTER);
                    messageLabel.setForeground(new Color(184, 134, 11));
                    messageLabel.setFont(new Font("Monospaced", Font.BOLD, 24));
                    messageLabel.setBounds(0, 200, 800, 40);
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
    
    private void createEditableFields(JPanel panel, int accountNum) {
        int yPos = 20;
        int spacing = 70;
        
        // Account Holder Name
        addFieldRow(panel, "ACCOUNT HOLDER NAME", obj.name, yPos, 
            () -> updateNameBtn, 
            btn -> updateNameBtn = btn,
            () -> {
                String newName = JOptionPane.showInputDialog(this, "Enter new name:", obj.name);
                if (newName != null && !newName.trim().isEmpty()) {
                    obj.name = newName;
                }
            });
        yPos += spacing;
        
        // Father's Name
        addFieldRow(panel, "FATHER'S NAME", obj.Father_name, yPos,
            () -> updateFatherBtn,
            btn -> updateFatherBtn = btn,
            () -> {
                String newFather = JOptionPane.showInputDialog(this, "Enter father's name:", obj.Father_name);
                if (newFather != null && !newFather.trim().isEmpty()) {
                    obj.Father_name = newFather;
                }
            });
        yPos += spacing;
        
        // Age
        addFieldRow(panel, "AGE", String.valueOf(obj.age), yPos,
            () -> updateAgeBtn,
            btn -> updateAgeBtn = btn,
            () -> {
                String newAge = JOptionPane.showInputDialog(this, "Enter new age:", obj.age);
                if (newAge != null && !newAge.trim().isEmpty()) {
                    try {
                        obj.age = Integer.parseInt(newAge);
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(this, "Invalid age!");
                    }
                }
            });
        yPos += spacing;
        
        // Phone Number
        addFieldRow(panel, "PHONE NUMBER", String.valueOf(obj.Phone_number), yPos,
            () -> updatePhoneBtn,
            btn -> updatePhoneBtn = btn,
            () -> {
                String newPhone = JOptionPane.showInputDialog(this, "Enter phone number:", obj.Phone_number);
                if (newPhone != null && !newPhone.trim().isEmpty()) {
                    try {
                        obj.Phone_number = Long.parseLong(newPhone);
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(this, "Invalid phone number!");
                    }
                }
            });
        yPos += spacing;
        
        // CNIC
        addFieldRow(panel, "CNIC NUMBER", String.valueOf(obj.CNIC), yPos,
            () -> updateCnicBtn,
            btn -> updateCnicBtn = btn,
            () -> {
                String newCnic = JOptionPane.showInputDialog(this, "Enter CNIC:", obj.CNIC);
                if (newCnic != null && !newCnic.trim().isEmpty()) {
                    try {
                        obj.CNIC = Long.parseLong(newCnic);
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(this, "Invalid CNIC!");
                    }
                }
            });
        
        // Save button
        saveButton = new JButton("SAVE CHANGES");
        saveButton.setBounds(260, 420, 250, 45);
        saveButton.setBackground(new Color(184, 134, 11));
        saveButton.setForeground(Color.WHITE);
        saveButton.setFont(new Font("Monospaced", Font.BOLD, 14));
        saveButton.setFocusable(false);
        saveButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        saveButton.addActionListener(ae -> saveChanges(accountNum, panel));
        panel.add(saveButton);
    }
    
    private void addFieldRow(JPanel panel, String label, String value, int y,
                            java.util.function.Supplier<JButton> getter,
                            java.util.function.Consumer<JButton> setter,
                            Runnable editAction) {
        JLabel fieldLabel = new JLabel(label);
        fieldLabel.setBounds(30, y, 250, 25);
        fieldLabel.setFont(new Font("Monospaced", Font.BOLD, 13));
        fieldLabel.setForeground(new Color(70, 70, 70));
        panel.add(fieldLabel);
        
        JLabel valueLabel = new JLabel(value);
        valueLabel.setBounds(30, y + 25, 600, 30);
        valueLabel.setFont(new Font("Monospaced", Font.BOLD, 16));
        valueLabel.setForeground(new Color(184, 134, 11));
        panel.add(valueLabel);
        
        JButton editBtn = new JButton("EDIT");
        editBtn.setBounds(650, y + 18, 100, 35);
        editBtn.setBackground(new Color(218, 165, 32));
        editBtn.setForeground(Color.WHITE);
        editBtn.setFont(new Font("Monospaced", Font.BOLD, 12));
        editBtn.setFocusable(false);
        editBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        editBtn.addActionListener(e -> editAction.run());
        panel.add(editBtn);
        
        setter.accept(editBtn);
    }

    private void saveChanges(int num, JPanel panel) {
    	
        try {
            updateNameBtn.setEnabled(false);
            updateFatherBtn.setEnabled(false);
            updateAgeBtn.setEnabled(false);
            updatePhoneBtn.setEnabled(false);
            updateCnicBtn.setEnabled(false);
            saveButton.setEnabled(false);
            
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
                int depositMoney = sc.nextInt();
                String cardNum = sc.next();
                String pin = sc.hasNext() ? sc.next() : "";

                if (acc_num == num) {
                    fw.write(acc_num + " " + obj.name + " " + obj.age + " " +
                             obj.Father_name + " " + obj.Phone_number + " " + obj.CNIC + 
                             " " + obj.Deposit_money + " " + obj.atm_card);
                    if (!pin.isEmpty()) {
                        fw.write(" " + pin);
                    }
                    fw.write("\n");
                } else {
                    fw.write(acc_num + " " + name + " " + age + " " + father + " " + 
                             phone + " " + cnic + " " + depositMoney + " " + cardNum);
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
                "Account information updated successfully!",
                "Success",
                JOptionPane.INFORMATION_MESSAGE);
            
            button.setEnabled(true);

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this,
                "Error saving changes: " + e.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE);
        }
    }
}