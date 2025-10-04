package com.example;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Display_Acc extends JFrame implements ActionListener {

    JButton button;

    private static class variables {
        int acc_nm;
        String name;
        int age;
        String Father_name;
        long Phone_number;
        long CNIC;
        int Deposit;
        String ATM_card, pin;
    }

    public Display_Acc() {
        setTitle("IMPERTIAL CROWN BANK - Account Registry");
        setSize(1400, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        
        ImageIcon icon = new ImageIcon("C:\\Users\\Hp\\Downloads\\mylogo.jpg");
        setIconImage(icon.getImage());

        // Main panel with gradient
        JPanel mainPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
                Color color1 = new Color(20, 20, 25);
                Color color2 = new Color(45, 45, 55);
                GradientPaint gp = new GradientPaint(0, 0, color1, 0, getHeight(), color2);
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        mainPanel.setLayout(new BorderLayout(0, 20));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));

        // Header Panel
        JPanel headerPanel = new JPanel();
        headerPanel.setOpaque(false);
        headerPanel.setLayout(new BorderLayout());
        
        JLabel titleLabel = new JLabel("ACCOUNT REGISTRY", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Monospaced", Font.BOLD, 32));
        titleLabel.setForeground(new Color(218, 165, 32));
        headerPanel.add(titleLabel, BorderLayout.CENTER);
        
        JLabel subtitleLabel = new JLabel("Confidential Banking Records", SwingConstants.CENTER);
        subtitleLabel.setFont(new Font("Monospaced", Font.ITALIC, 14));
        subtitleLabel.setForeground(new Color(180, 180, 180));
        headerPanel.add(subtitleLabel, BorderLayout.SOUTH);

        mainPanel.add(headerPanel, BorderLayout.NORTH);

        // Read data
        ArrayList<variables> accountList = new ArrayList<>();
        File file = new File("Bank_managemnet.txt");

        try {
            Scanner sc = new Scanner(file);
            while (sc.hasNext()) {
                variables obj = new variables();
                obj.acc_nm = sc.nextInt();

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
                obj.Deposit = sc.nextInt();
                obj.ATM_card = sc.next();
                
                if (sc.hasNext()) {
                    obj.pin = sc.next();
                } else {
                    obj.pin = "****";
                }

                accountList.add(obj);
            }
            sc.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        // Table columns
        String[] columns = {"Account #", "Account Holder", "Father's Name", "Age", 
                           "Phone Number", "CNIC", "Balance (Rs.)", "ATM Card", "PIN"};

        // Populate data
        Object[][] data = new Object[accountList.size()][9];
        for (int i = 0; i < accountList.size(); i++) {
            variables acc = accountList.get(i);
            data[i][0] = acc.acc_nm;
            data[i][1] = acc.name;
            data[i][2] = acc.Father_name;
            data[i][3] = acc.age;
            data[i][4] = formatPhone(acc.Phone_number);
            data[i][5] = acc.CNIC;
            data[i][6] = String.format("%,d", acc.Deposit);
            data[i][7] = acc.ATM_card;
            data[i][8] = "••••";
        }

        // Create table
        DefaultTableModel model = new DefaultTableModel(data, columns) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        JTable table = new JTable(model);
        table.setFont(new Font("Monospaced", Font.PLAIN, 13));
        table.setRowHeight(35);
        table.setBackground(new Color(250, 250, 250));
        table.setForeground(new Color(40, 40, 40));
        table.setSelectionBackground(new Color(218, 165, 32, 100));
        table.setSelectionForeground(new Color(40, 40, 40));
        table.setGridColor(new Color(200, 200, 200));
        table.setShowGrid(true);
        table.setIntercellSpacing(new Dimension(1, 1));

        // Header styling
        JTableHeader header = table.getTableHeader();
        header.setBackground(new Color(184, 134, 11));
        header.setForeground(Color.WHITE);
        header.setFont(new Font("Monospaced", Font.BOLD, 13));
        header.setPreferredSize(new Dimension(header.getWidth(), 40));
        header.setBorder(BorderFactory.createLineBorder(new Color(218, 165, 32), 2));

        // Center align numeric columns
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        table.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        table.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
        table.getColumnModel().getColumn(8).setCellRenderer(centerRenderer);

        // Right align balance
        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(JLabel.RIGHT);
        table.getColumnModel().getColumn(6).setCellRenderer(rightRenderer);

        // Set column widths
        table.getColumnModel().getColumn(0).setPreferredWidth(80);
        table.getColumnModel().getColumn(1).setPreferredWidth(180);
        table.getColumnModel().getColumn(2).setPreferredWidth(180);
        table.getColumnModel().getColumn(3).setPreferredWidth(50);
        table.getColumnModel().getColumn(4).setPreferredWidth(120);
        table.getColumnModel().getColumn(5).setPreferredWidth(130);
        table.getColumnModel().getColumn(6).setPreferredWidth(120);
        table.getColumnModel().getColumn(7).setPreferredWidth(150);
        table.getColumnModel().getColumn(8).setPreferredWidth(60);

        // Scroll pane with styling
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(184, 134, 11), 3),
            BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));
        scrollPane.getViewport().setBackground(new Color(250, 250, 250));

        mainPanel.add(scrollPane, BorderLayout.CENTER);

        // Bottom panel with info and button
        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setOpaque(false);
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));

        JLabel recordCount = new JLabel("Total Accounts: " + accountList.size());
        recordCount.setFont(new Font("Monospaced", Font.PLAIN, 14));
        recordCount.setForeground(new Color(180, 180, 180));
        bottomPanel.add(recordCount, BorderLayout.WEST);

        button = new JButton("RETURN TO MAIN MENU");
        button.setPreferredSize(new Dimension(220, 45));
        button.setFont(new Font("Monospaced", Font.BOLD, 14));
        button.setBackground(new Color(184, 134, 11));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.addActionListener(this);
        
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(218, 165, 32));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(184, 134, 11));
            }
        });

        bottomPanel.add(button, BorderLayout.EAST);

        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        setContentPane(mainPanel);
        setVisible(true);
    }
    
    private String formatPhone(long phone) {
        String phoneStr = String.valueOf(phone);
        if (phoneStr.length() == 10) {
            return phoneStr.substring(0, 4) + "-" + phoneStr.substring(4, 7) + "-" + phoneStr.substring(7);
        }
        return phoneStr;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == button) {
            this.dispose();
            new Menu();
        }
    }
}