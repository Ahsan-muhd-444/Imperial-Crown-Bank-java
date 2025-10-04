package com.example;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Menu extends JFrame implements ActionListener{
	JButton creatButton, SearchButton, displayButton, updateButton, 
	        deleteButton, withdrawlButton, depositButton;

    public Menu() {
        this.setSize(900, 700);
        ImageIcon icon2 = new ImageIcon("C:\\Users\\Hp\\Downloads\\mylogo.jpg");
        this.setIconImage(icon2.getImage());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("IMPERTIAL CROWN BANK");
        this.setResizable(false);
        this.setLocationRelativeTo(null);

        // Load and scale background image
        ImageIcon bgIcon = new ImageIcon("C:\\Users\\Hp\\Downloads\\mylogo.jpg");
        Image bgImg = bgIcon.getImage();
        Image scaledBgImg = bgImg.getScaledInstance(900, 700, Image.SCALE_SMOOTH);
        ImageIcon scaledBgIcon = new ImageIcon(scaledBgImg);

        // Create main panel with background image and overlay
        JPanel mainPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
                
                // Draw background image
                g2d.drawImage(scaledBgImg, 0, 0, null);
                
                // Add semi-transparent dark overlay for better text visibility
                g2d.setColor(new Color(0, 0, 0, 150)); // Adjust transparency (0-255)
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        mainPanel.setLayout(null);
        
        // Header with bank name
        JPanel headerPanel = new JPanel();
        headerPanel.setBounds(0, 0, 900, 80);
        headerPanel.setLayout(null);
        headerPanel.setBackground(new Color(184, 134, 11));
        
        JLabel bankName = new JLabel("IMPERTIAL CROWN BANK", SwingConstants.CENTER);
        bankName.setBounds(0, 0, 900, 80);
        bankName.setFont(new Font("Monospaced", Font.BOLD, 36));
        bankName.setForeground(Color.WHITE);
        headerPanel.add(bankName);
        
        JLabel tagline = new JLabel("Banking Excellence Since 1920", SwingConstants.CENTER);
        tagline.setBounds(0, 50, 900, 25);
        tagline.setFont(new Font("Monospaced", Font.ITALIC, 14));
        tagline.setForeground(new Color(255, 255, 255, 200));
        headerPanel.add(tagline);
        
        mainPanel.add(headerPanel);
        
        // Subtitle
        JLabel subtitle = new JLabel("MANAGEMENT CONSOLE", SwingConstants.CENTER);
        subtitle.setBounds(0, 100, 900, 40);
        subtitle.setFont(new Font("Monospaced", Font.BOLD, 20));
        subtitle.setForeground(new Color(218, 165, 32));
        mainPanel.add(subtitle);
        
        // Create styled buttons
        int buttonWidth = 350;
        int buttonHeight = 60;
        int leftX = 80;
        int rightX = 470;
        int startY = 180;
        int spacing = 85;
        
        creatButton = createStyledButton("CREATE ACCOUNT", leftX, startY);
        updateButton = createStyledButton("UPDATE ACCOUNTS", rightX, startY);
        
        SearchButton = createStyledButton("SEARCH ACCOUNTS", leftX, startY + spacing);
        deleteButton = createStyledButton("DELETE ACCOUNTS", rightX, startY + spacing);
        
        displayButton = createStyledButton("DISPLAY ACCOUNTS", leftX, startY + spacing * 2);
        withdrawlButton = createStyledButton("WITHDRAW MONEY", rightX, startY + spacing * 2);
        
        depositButton = createStyledButton("DEPOSIT MONEY", 275, startY + spacing * 3);
        depositButton.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
        
        // Add action listeners
        creatButton.addActionListener(this);
        SearchButton.addActionListener(this);
        displayButton.addActionListener(this);
        updateButton.addActionListener(this);
        deleteButton.addActionListener(this);
        withdrawlButton.addActionListener(this);
        depositButton.addActionListener(this);
        
        // Add buttons to panel
        mainPanel.add(creatButton);
        mainPanel.add(SearchButton);
        mainPanel.add(displayButton);
        mainPanel.add(updateButton);
        mainPanel.add(deleteButton);
        mainPanel.add(withdrawlButton);
        mainPanel.add(depositButton);
        
        // Footer
        JLabel footer = new JLabel("Secure • Trusted • Professional", SwingConstants.CENTER);
        footer.setBounds(0, 630, 900, 30);
        footer.setFont(new Font("Monospaced", Font.PLAIN, 12));
        footer.setForeground(new Color(150, 150, 150));
        mainPanel.add(footer);
        
        this.setContentPane(mainPanel);
        this.setVisible(true);
    }
    
    private JButton createStyledButton(String text, int x, int y) {
        JButton button = new JButton(text);
        button.setBounds(x, y, 350, 60);
        button.setFont(new Font("Monospaced", Font.BOLD, 16));
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setOpaque(true);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        // Gradient background
        button.setBackground(new Color(184, 134, 11));
        button.setForeground(Color.WHITE);
        button.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(218, 165, 32), 2),
            BorderFactory.createEmptyBorder(10, 20, 10, 20)
        ));
        
        // Hover effect
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(218, 165, 32));
                button.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(new Color(255, 215, 0), 2),
                    BorderFactory.createEmptyBorder(10, 20, 10, 20)
                ));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(184, 134, 11));
                button.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(new Color(218, 165, 32), 2),
                    BorderFactory.createEmptyBorder(10, 20, 10, 20)
                ));
            }
        });
        
        return button;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        if (e.getSource() == creatButton) {
            this.dispose();
            userinfo creatacc = new userinfo();
        }
        
        else if (e.getSource() == displayButton) {
            this.dispose();
            Display_Acc displayacc = new Display_Acc();
        }
        
        else if (e.getSource() == SearchButton) {
            this.dispose();
            Search_acc search_acc = new Search_acc();
        } 
        
        else if (e.getSource() == updateButton) {
            showAuthDialog("UPDATE");
        }
        
        else if (e.getSource() == deleteButton) {
            showAuthDialog("DELETE");
        }
        
        else if (e.getSource() == withdrawlButton) {
        	this.dispose();
            ATM_withdraw withdraw_accAtm_withdraw = new ATM_withdraw();
        }
        
        else if (e.getSource() == depositButton) {
            this.dispose();
            ATM_deposit deposit_accAtm_deposit =new ATM_deposit();
        }
    }
    
    private void showAuthDialog(String action) {
        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        JLabel userLabel = new JLabel("Username:");
        userLabel.setFont(new Font("Monospaced", Font.BOLD, 14));
        JTextField userField = new JTextField();
        userField.setFont(new Font("Monospaced", Font.PLAIN, 14));
        
        JLabel passLabel = new JLabel("Password:");
        passLabel.setFont(new Font("Monospaced", Font.BOLD, 14));
        JPasswordField passField = new JPasswordField();
        passField.setFont(new Font("Monospaced", Font.PLAIN, 14));
        
        panel.add(userLabel);
        panel.add(userField);
        panel.add(passLabel);
        panel.add(passField);
        
        int option = JOptionPane.showConfirmDialog(
            this,
            panel,
            "Authentication Required - " + action + " Operation",
            JOptionPane.OK_CANCEL_OPTION,
            JOptionPane.PLAIN_MESSAGE
        );
        
        if (option == JOptionPane.OK_OPTION) {
            String username = userField.getText();
            String password = new String(passField.getPassword());
            
            if (username.equals("Ahsan") && password.equals("64164")) {
                JOptionPane.showMessageDialog(this, 
                    "Authentication Successful", 
                    "Access Granted", 
                    JOptionPane.INFORMATION_MESSAGE);
                
        
                if (action.equals("UPDATE")) {
                    Update_acc obj_Update_acc =new Update_acc();
                } else if (action.equals("DELETE")) {
                    Delete_acc obj_delAcc=new Delete_acc();
                }
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(this, 
                    "Invalid username or password!", 
                    "Authentication Failed", 
                    JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}