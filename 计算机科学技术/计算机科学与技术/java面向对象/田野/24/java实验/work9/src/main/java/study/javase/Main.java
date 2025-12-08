package study.javase;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Swing Layout Demo");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLayout(new BorderLayout(8, 8));

            JPanel northPanel = new JPanel(new BorderLayout(5, 5));
            JTextField textField = new JTextField();
            JButton submitButton = new JButton("Submit");
            northPanel.add(textField, BorderLayout.CENTER);
            northPanel.add(submitButton, BorderLayout.EAST);

            JTextArea textArea = new JTextArea();
            textArea.setEditable(false);
            JScrollPane scrollPane = new JScrollPane(textArea);

            String[] items = {"Option A", "Option B", "Option C", "Hello", "World"};
            JComboBox<String> comboBox = new JComboBox<>(items);

            frame.add(northPanel, BorderLayout.NORTH);
            frame.add(scrollPane, BorderLayout.CENTER);
            frame.add(comboBox, BorderLayout.SOUTH);

            ActionListener submitAction = e -> {
                String input = textField.getText().trim();
                if (input.isEmpty()) {
                    return;
                }
                if ("clear".equalsIgnoreCase(input)) {
                    textArea.setText("");
                } else {
                    textArea.append(input + System.lineSeparator());
                }
                textField.setText("");
            };

            textField.addActionListener(submitAction);
            submitButton.addActionListener(submitAction);

            comboBox.addActionListener(new ActionListener() {
                boolean initialized = false;

                @Override
                public void actionPerformed(ActionEvent e) {
                    if (!initialized) {
                        initialized = true;
                        return;
                    }
                    Object sel = comboBox.getSelectedItem();
                    if (sel != null) {
                        textArea.append(sel + System.lineSeparator());
                    }
                }
            });

            frame.setSize(600, 400);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
