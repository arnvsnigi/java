import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class pgm3 extends JFrame {
    private JTextField nameField, usnField, ageField, addressField, sgpaField1, sgpaField2, sgpaField3, sgpaField4, categoryField;
     private JTextField cgpaField;
    private JTextArea displayArea;
    private ArrayList<Student> studentList;

    public pgm3() {
        studentList = new ArrayList<>();

        // Create and set up the JFrame
        setTitle("Student Information App");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 400);
       

        // Create components
        nameField = new JTextField(20);
        usnField = new JTextField(20);
        ageField = new JTextField(3);
        addressField = new JTextField(20);
        sgpaField1 = new JTextField(4);
        sgpaField2 = new JTextField(4);
        sgpaField3 = new JTextField(4);
        sgpaField4 = new JTextField(4);
        categoryField = new JTextField(10);
        cgpaField = new JTextField(4);
        cgpaField.setEditable(false);
        displayArea = new JTextArea(10, 30);
        displayArea.setEditable(false);

        JButton computeButton = new JButton("Compute CGPA");
        JButton doneButton = new JButton("Done");
        JButton displayButton = new JButton("Display");

        // Set layout manager
        setLayout(new BorderLayout());

        // Create panels
        JPanel inputPanel = new JPanel(new GridLayout(12, 4));
        JPanel buttonPanel = new JPanel(new FlowLayout());
        JScrollPane scrollPane = new JScrollPane(displayArea);

        // Add components to inputPanel
        inputPanel.add(new JLabel("Name:"));
        inputPanel.add(nameField);
        inputPanel.add(new JLabel("USN:"));
        inputPanel.add(usnField);
        inputPanel.add(new JLabel("Age:"));
        inputPanel.add(ageField);
        inputPanel.add(new JLabel("Address:"));
        inputPanel.add(addressField);
        inputPanel.add(new JLabel("SGPA Sem1:"));
        inputPanel.add(sgpaField1);
        inputPanel.add(new JLabel("SGPA Sem2:"));
        inputPanel.add(sgpaField2);
        inputPanel.add(new JLabel("SGPA Sem3:"));
        inputPanel.add(sgpaField3);
        inputPanel.add(new JLabel("SGPA Sem4:"));
        inputPanel.add(sgpaField4);
        inputPanel.add(new JLabel("CGPA:"));
        inputPanel.add(cgpaField);
        inputPanel.add(new JLabel("Category:"));
        inputPanel.add(categoryField);

        inputPanel.add(computeButton);
        inputPanel.add(doneButton);
        inputPanel.add(displayButton);

        // Add action listeners
        computeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                computeCGPA();
            }
        });

        doneButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addStudent();
            }
        });

        displayButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayStudents();
            }
        });

        // Add components to buttonPanel
        buttonPanel.add(scrollPane);

        // Add panels to the frame
        add(inputPanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);

        // Set visibility
        setVisible(true);
    }

    private void computeCGPA() {
        try {
            double sgpa1 = Double.parseDouble(sgpaField1.getText());
            double sgpa2 = Double.parseDouble(sgpaField2.getText());
            double sgpa3 = Double.parseDouble(sgpaField3.getText());
            double sgpa4 = Double.parseDouble(sgpaField4.getText());

            // Perform CGPA computation logic here
            double cgpa = (sgpa1 + sgpa2 + sgpa3 + sgpa4) / 4;

            // Display CGPA in the CGPA text field
            cgpaField.setText(String.format("%.2f", cgpa));

        } catch (NumberFormatException e) {
            // Handle the case where SGPA fields contain non-numeric values
            JOptionPane.showMessageDialog(this, "Invalid SGPA values. Please enter numeric values.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void addStudent() {
        // Perform input validation here
        // You can check if the age and SGPA values are valid, and show appropriate messages in pop-up boxes

        // If everything is valid, add the student to the collection
        Student student = new Student(
                nameField.getText(),
                usnField.getText(),
                Integer.parseInt(ageField.getText()),
                addressField.getText(),
                Double.parseDouble(sgpaField1.getText()),
                Double.parseDouble(sgpaField2.getText()),
                Double.parseDouble(sgpaField3.getText()),
                Double.parseDouble(sgpaField4.getText()),
                Double.parseDouble(cgpaField.getText()),
                
                categoryField.getText()
        );
        // displayArea.append("CGPA: " + cgpaField.getText() + "\n");
        studentList.add(student);

        // Clear input fields
        clearFields();
    }

    private void displayStudents() {
        // Display the collection in the JTextArea
        displayArea.setText("");
        for (Student student : studentList) {
            displayArea.append(student.toString() + "\n");
            // displayArea.append(student.toString() + ", CGPA: " + student.getCGPA() + "\n");
        }
    }

    private void clearFields() {
        // Clear input fields
        nameField.setText("");
        usnField.setText("");
        ageField.setText("");
        addressField.setText("");
        sgpaField1.setText("");
        sgpaField2.setText("");
        sgpaField3.setText("");
        sgpaField4.setText("");
        cgpaField.setText("");
        categoryField.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new pgm3();
            }
        });
    }
}

class Student {
    private String name;
    private String usn;
    private int age;
    private String address;
    private double sgpa1;
    private double sgpa2;
    private double sgpa3;
    private double sgpa4;
     private double cgpa;
    private String category;

    public Student(String name, String usn, int age, String address, double sgpa1, double sgpa2, double sgpa3, double sgpa4,double cgpa, String category) {
        this.name = name;
        this.usn = usn;
        this.age = age;
        this.address = address;
        this.sgpa1 = sgpa1;
        this.sgpa2 = sgpa2;
        this.sgpa3 = sgpa3;
        this.sgpa4 = sgpa4;
        this.category = category;
        this.cgpa = (sgpa1 + sgpa2 + sgpa3 + sgpa4) / 4;
    }
    public double getCGPA() {
        return cgpa;
    }


    @Override
    public String toString() {
        return "Name: " + name +
                ", USN: " + usn +
                ", Age: " + age +
                ", Address: " + address +
                ", SGPA Sem1: " + sgpa1 +
                ", SGPA Sem2: " + sgpa2 +
                ", SGPA Sem3: " + sgpa3 +
                ", SGPA Sem4: " + sgpa4 +
                ", Category: " + category;
    }
}
