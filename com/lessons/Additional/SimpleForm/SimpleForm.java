package com.lessons.Additional.SimpleForm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SimpleForm extends JFrame {

    JTextField name_field, email_field;
    JRadioButton male, female;
    JCheckBox agreement;

    public SimpleForm() {
        super("Simple Form");
        this.setBounds(600, 300, 360, 200);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container container = super.getContentPane();
        container.setLayout(new GridLayout(5, 1, 23, 4));

        JLabel name = new JLabel("Your name: ");
        name_field = new JTextField("", 1);
        container.add(name);
        container.add(name_field);

        JLabel email = new JLabel("Your email:");
        email_field = new JTextField("", 1);
        container.add(email);
        container.add(email_field);

        male = new JRadioButton("Male");
        female = new JRadioButton("Female");
        container.add(male);
        container.add(female);

        agreement = new JCheckBox("Agreement", false);
        container.add(agreement);

        JButton apply = new JButton("Apply");
        container.add(apply);

        apply.addActionListener(new ButtonEvent());
    }

    class ButtonEvent implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String name = name_field.getText();
            String email = email_field.getText();
            String gender = "Male";

            if (!male.isSelected())
                gender = "Female";

            boolean checkbox = agreement.isSelected();

            JOptionPane.showMessageDialog(null, "Name: "
                    + name + "\nEmail: " + email + "\nAgreement: " + checkbox + "\nGender: " +
                    gender, "Result", JOptionPane.PLAIN_MESSAGE);
        }
    }
}
