import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GUIATM extends JFrame {
    private double balance = 1000.00;
    private final int CORRECT_PIN = 1234;
    private int attempts = 0;
    private final int MAX_ATTEMPTS = 3;

    private CardLayout cardLayout;
    private JPanel mainPanel;

    public GUIATM() {
        setTitle("Java ATM");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        mainPanel.add(createLoginPanel(), "login");
        mainPanel.add(createMenuPanel(), "menu");

        add(mainPanel);
        cardLayout.show(mainPanel, "login");
    }

    private JPanel createLoginPanel() {
        JPanel panel = new JPanel(new GridLayout(4, 1, 10, 10));
        JLabel label = new JLabel("Enter your PIN:", SwingConstants.CENTER);
        JPasswordField pinField = new JPasswordField();
        JButton loginButton = new JButton("Login");
        JLabel message = new JLabel("", SwingConstants.CENTER);

        loginButton.addActionListener(e -> {
            String pinText = new String(pinField.getPassword());
            try {
                int pin = Integer.parseInt(pinText);
                if (pin == CORRECT_PIN) {
                    message.setText("✅ Access Granted!");
                    cardLayout.show(mainPanel, "menu");
                } else {
                    attempts++;
                    if (attempts >= MAX_ATTEMPTS) {
                        JOptionPane.showMessageDialog(this,
                                "Too many incorrect attempts.\nCard blocked!",
                                "Access Denied",
                                JOptionPane.ERROR_MESSAGE);
                        System.exit(0);
                    } else {
                        message.setText("❌ Incorrect PIN (" + (MAX_ATTEMPTS - attempts) + " left)");
                    }
                }
            } catch (NumberFormatException ex) {
                message.setText("⚠ Please enter a valid PIN.");
            }
        });

        panel.setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));
        panel.add(label);
        panel.add(pinField);
        panel.add(loginButton);
        panel.add(message);
        return panel;
    }

    private JPanel createMenuPanel() {
        JPanel panel = new JPanel(new GridLayout(5, 1, 10, 10));
        JLabel label = new JLabel("Welcome to Java ATM", SwingConstants.CENTER);

        JButton checkBtn = new JButton("Check Balance");
        JButton depositBtn = new JButton("Deposit");
        JButton withdrawBtn = new JButton("Withdraw");
        JButton exitBtn = new JButton("Exit");

        checkBtn.addActionListener(e ->
                JOptionPane.showMessageDialog(this,
                        "Your balance is: ₱" + String.format("%.2f", balance)));

        depositBtn.addActionListener(e -> {
            String input = JOptionPane.showInputDialog(this, "Enter deposit amount:");
            try {
                double amount = Double.parseDouble(input);
                if (amount > 0) {
                    balance += amount;
                    JOptionPane.showMessageDialog(this, "✅ Deposit successful!");
                } else {
                    JOptionPane.showMessageDialog(this, "⚠ Invalid amount.");
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "⚠ Invalid input.");
            }
        });

        withdrawBtn.addActionListener(e -> {
            String input = JOptionPane.showInputDialog(this, "Enter withdrawal amount:");
            try {
                double amount = Double.parseDouble(input);
                if (amount > 0 && amount <= balance) {
                    balance -= amount;
                    JOptionPane.showMessageDialog(this, "💸 Please take your cash.");
                } else if (amount > balance) {
                    JOptionPane.showMessageDialog(this, "⚠ Insufficient balance!");
                } else {
                    JOptionPane.showMessageDialog(this, "⚠ Invalid amount.");
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "⚠ Invalid input.");
            }
        });

        exitBtn.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "👋 Thank you for using Java ATM!");
            System.exit(0);
        });

        panel.setBorder(BorderFactory.createEmptyBorder(30, 60, 30, 60));
        panel.add(label);
        panel.add(checkBtn);
        panel.add(depositBtn);
        panel.add(withdrawBtn);
        panel.add(exitBtn);
        return panel;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new GUIATM().setVisible(true));
    }
}
