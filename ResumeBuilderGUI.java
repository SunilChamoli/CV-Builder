package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileOutputStream;
public class ResumeBuilderGUI extends JFrame {

    private JTextField firstNameField, lastNameField, emailField, phoneField,
            companyField, positionField, startDateField, endDateField,
            schoolField, degreeField, majorField;
    private JTextArea skillsArea, experienceArea, educationArea;
    private JButton submitButton;

    public ResumeBuilderGUI() {
        super("Resume Builder");

        // Create the main panel
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Create the input panel
        JPanel inputPanel = new JPanel(new GridLayout(0, 2, 10, 10));
        inputPanel.setBorder(BorderFactory.createTitledBorder("Personal Information"));

        firstNameField = new JTextField(20);
        lastNameField = new JTextField(20);
        emailField = new JTextField(20);
        phoneField = new JTextField(20);
        companyField = new JTextField(20);
        positionField = new JTextField(20);
        startDateField = new JTextField(20);
        endDateField = new JTextField(20);
        schoolField = new JTextField(20);
        degreeField = new JTextField(20);
        majorField = new JTextField(20);

        inputPanel.add(new JLabel("First Name: "));
        inputPanel.add(firstNameField);
        inputPanel.add(new JLabel("Last Name: "));
        inputPanel.add(lastNameField);
        inputPanel.add(new JLabel("Email: "));
        inputPanel.add(emailField);
        inputPanel.add(new JLabel("Phone: "));
        inputPanel.add(phoneField);
        inputPanel.add(new JLabel("Company: "));
        inputPanel.add(companyField);
        inputPanel.add(new JLabel("Position: "));
        inputPanel.add(positionField);
        inputPanel.add(new JLabel("Start Date: "));
        inputPanel.add(startDateField);
        inputPanel.add(new JLabel("End Date: "));
        inputPanel.add(endDateField);
        inputPanel.add(new JLabel("School: "));
        inputPanel.add(schoolField);
        inputPanel.add(new JLabel("Degree: "));
        inputPanel.add(degreeField);
        inputPanel.add(new JLabel("Major: "));
        inputPanel.add(majorField);

        // Create the skills panel
        JPanel skillsPanel = new JPanel(new BorderLayout());
        skillsPanel.setBorder(BorderFactory.createTitledBorder("Skills"));

        skillsArea = new JTextArea(10, 40);
        JScrollPane skillsScrollPane = new JScrollPane(skillsArea);
        skillsPanel.add(skillsScrollPane, BorderLayout.CENTER);

        // Create the experience panel
        JPanel experiencePanel = new JPanel(new BorderLayout());
        experiencePanel.setBorder(BorderFactory.createTitledBorder("Work Experience"));

        experienceArea = new JTextArea(10, 40);
        JScrollPane experienceScrollPane = new JScrollPane(experienceArea);
        experiencePanel.add(experienceScrollPane, BorderLayout.CENTER);

        // Create the education panel
        JPanel educationPanel = new JPanel(new BorderLayout());
        educationPanel.setBorder(BorderFactory.createTitledBorder("Education"));

        educationArea = new JTextArea(10, 40);
        JScrollPane educationScrollPane = new JScrollPane(educationArea);
        educationPanel.add(educationScrollPane, BorderLayout.CENTER);

        // Create the button panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        submitButton = new JButton("Submit");
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                submitResume();
            }
        });
        buttonPanel.add(submitButton);

        // Add everything to the main panel
        mainPanel.add(inputPanel, BorderLayout.NORTH);
        mainPanel.add(skillsPanel, BorderLayout.WEST);
        mainPanel.add(experiencePanel, BorderLayout.CENTER);
        mainPanel.add(educationPanel, BorderLayout.EAST);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Add the main panel to the frame
        add(mainPanel);

        // Set the size of the frame and center it on the screen
        setSize(800, 600);
        setLocationRelativeTo(null);

        // Show the frame
        setVisible(true);
    }

    private void submitResume() {
        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();
        String email = emailField.getText();
        String phone = phoneField.getText();

        String company = companyField.getText();
        String position = positionField.getText();
        String startDate = startDateField.getText();
        String endDate = endDateField.getText();

        String school = schoolField.getText();
        String degree = degreeField.getText();
        String major = majorField.getText();

        String skills = skillsArea.getText();
        String experience = experienceArea.getText();
        String education = educationArea.getText();

        // Validate input
        if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() || phone.isEmpty() ||
                company.isEmpty() || position.isEmpty() || startDate.isEmpty() || endDate.isEmpty() ||
                school.isEmpty() || degree.isEmpty() || major.isEmpty() ||
                skills.isEmpty() || experience.isEmpty() || education.isEmpty()) {
            JOptionPane.showMessageDialog(this, "All fields are required.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        Document document = new Document();
        try {
            // Create a PdfWriter to write the document to a file
            PdfWriter.getInstance(document, new FileOutputStream("resume.pdf"));

            // Open the document
            document.open();

            // Add the resume data to the document
            document.add(new Paragraph("Resume"));
            document.add(new Paragraph("Name: " + firstName + " " + lastName));
            document.add(new Paragraph("Email: " + email));
            document.add(new Paragraph("Phone: " + phone));
            document.add(new Paragraph("Work Experience: " + company + ", " + position + ", " + startDate + " - " + endDate));
            document.add(new Paragraph("Education: " + degree + " in " + major + " from " + school));
            document.add(new Paragraph("Skills: " + skills));
            document.add(new Paragraph("Experience: " + experience));
            document.add(new Paragraph("Education: " + education));

            // Close the document
            document.close();

            // Show a success message
            JOptionPane.showMessageDialog(this, "Resume saved to resume.pdf", "Success", JOptionPane.INFORMATION_MESSAGE);

        } catch (Exception ex) {
            // Show an error message if something went wrong
            JOptionPane.showMessageDialog(this, "Error generating PDF: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        ResumeBuilderGUI resumeBuilderGUI = new ResumeBuilderGUI();
    }
}
