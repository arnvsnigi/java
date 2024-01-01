import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class pgm4 extends JFrame {
    private Map<String, String> userCredentials;
    private Map<String, Customer> customers;
    private JTextField usernameField, passwordField, mobileNumberField, itemIdField, quantityField;
    private JTextArea displayArea;
    private JComboBox<String> discountComboBox;
    private JButton loginButton, enterCustomerIdButton, purchaseButton, printButton;

    public pgm4() {
        userCredentials = new HashMap<>();
        customers = new HashMap<>();

        // Dummy user credentials (you can replace this with your authentication logic)
        userCredentials.put("abc", "qwerty");

        // Create and set up the JFrame
        setTitle("Shopping App");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 400);
        setLocationRelativeTo(null);

        // Create components
        usernameField = new JTextField(20);
        passwordField = new JPasswordField(20);
        mobileNumberField = new JTextField(10);
        itemIdField = new JTextField(10);
        quantityField = new JTextField(5);
        displayArea = new JTextArea(10, 30);
        displayArea.setEditable(false);

        discountComboBox = new JComboBox<>(new String[]{"No Discount", "Percentage Discount", "Fixed Discount"});
        discountComboBox.setSelectedIndex(0);

        loginButton = new JButton("Login");
        enterCustomerIdButton = new JButton("Enter Customer ID");
        purchaseButton = new JButton("Purchase");
        printButton = new JButton("Print");

        // Set layout manager
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        // Create panels
        JPanel loginPanel = new JPanel();
        JPanel customerPanel = new JPanel();
        JPanel purchasePanel = new JPanel();
        JPanel printPanel = new JPanel();
        JScrollPane scrollPane = new JScrollPane(displayArea);

        // Add components to loginPanel
        loginPanel.add(new JLabel("Username:"));
        loginPanel.add(usernameField);
        loginPanel.add(new JLabel("Password:"));
        loginPanel.add(passwordField);
        loginPanel.add(loginButton);

        // Add components to customerPanel
        customerPanel.add(new JLabel("Mobile Number:"));
        customerPanel.add(mobileNumberField);
        customerPanel.add(enterCustomerIdButton);

        // Add components to purchasePanel
        purchasePanel.add(new JLabel("Item ID:"));
        purchasePanel.add(itemIdField);
        purchasePanel.add(new JLabel("Quantity:"));
        purchasePanel.add(quantityField);
        purchasePanel.add(new JLabel("Discount Type:"));
        purchasePanel.add(discountComboBox);
        purchasePanel.add(purchaseButton);

        // Add components to printPanel
        printPanel.add(printButton);

        // Add action listeners
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                validateLogin();
            }
        });

        enterCustomerIdButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                enterCustomerId();
            }
        });

        purchaseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                purchaseItem();
            }
        });

        printButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                printDetails();
            }
        });

        // Add components to the frame
        add(loginPanel);
        add(customerPanel);
        add(purchasePanel);
        add(printPanel);
        add(scrollPane);

        // Set visibility
        setVisible(true);
    }

    private void validateLogin() {
        String username = usernameField.getText();
        String password = new String(((JPasswordField) passwordField).getPassword());

        if (userCredentials.containsKey(username) && userCredentials.get(username).equals(password)) {
            JOptionPane.showMessageDialog(this, "Login successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Invalid credentials. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void enterCustomerId() {
        String mobileNumber = mobileNumberField.getText();
        if (customers.containsKey(mobileNumber)) {
            JOptionPane.showMessageDialog(this, "Customer ID: " + customers.get(mobileNumber).getCustomerId(), "Customer ID", JOptionPane.INFORMATION_MESSAGE);
        } else {
            String customerId = JOptionPane.showInputDialog(this, "New customer! Enter customer ID:");
            if (customerId != null && !customerId.isEmpty()) {
                customers.put(mobileNumber, new Customer(customerId));
                JOptionPane.showMessageDialog(this, "Customer ID set successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Invalid customer ID. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void purchaseItem() {
        String itemId = itemIdField.getText();
        int quantity = Integer.parseInt(quantityField.getText());
        String discountType = (String) discountComboBox.getSelectedItem();

        // Assume that the items and their prices are stored in a map (replace it with your item logic)
        Map<String, Double> itemPrices = new HashMap<>();
        itemPrices.put("item1", 10.0);
        itemPrices.put("item2", 20.0);
        itemPrices.put("item3", 30.0);

        if (itemPrices.containsKey(itemId)) {
            double itemPrice = itemPrices.get(itemId);
            double totalCost = calculateTotalCost(itemPrice, quantity, discountType);
            displayArea.append("Item: " + itemId + ", Total Cost: " + totalCost + "\n");
        } else {
            JOptionPane.showMessageDialog(this, "Invalid item ID. Please enter a valid item ID.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private double calculateTotalCost(double itemPrice, int quantity, String discountType) {
        double discount = 0.0;

        switch (discountType) {
            case "Percentage Discount":
                discount = 0.1; // 10% discount
                break;
            case "Fixed Discount":
                discount = 5.0; // $5 fixed discount
                break;
            // No discount for "No Discount" option
        }

        return itemPrice * quantity * (1 - discount);
    }

    private void printDetails() {
        String discountType = (String) discountComboBox.getSelectedItem();
        JOptionPane.showMessageDialog(this, "Discount Type: " + discountType + "\n" + displayArea.getText(), "Print Details", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new pgm4();
            }
        });
    }
}

class Customer {
    private String customerId;

    public Customer(String customerId) {
        this.customerId = customerId;
    }

    public String getCustomerId() {
        return customerId;
    }
}
