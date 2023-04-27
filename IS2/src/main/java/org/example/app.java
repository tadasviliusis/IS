package org.example;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class app extends JFrame {

    private JPanel panelMain;
    private JTextField message;
    private JButton btn1;
    private JButton btn2;
    private JComboBox modes;
    private JTextField iv;
    private JTextField key;
    private JButton btn3save;
    private JButton btn4open;
    private JTextField message2;

    private JFileChooser fileChooser;

    public app() {
        btn1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String txt;
                    txt = DES.decrypt(message.getText(), key.getText(), modes.getSelectedItem().toString(), iv.getText());
                    message2.setText(txt);
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }

            }
        });
        btn2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String txt;
                    txt = DES.encrypt(message.getText(), key.getText(), modes.getSelectedItem().toString(), iv.getText());
                    message2.setText(txt);
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }


            }
        });

        btn3save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setFileFilter(new FileNameExtensionFilter("Text files", "txt"));
                int returnVal = fileChooser.showSaveDialog(null);
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    try {
                        BufferedWriter writer = new BufferedWriter(new FileWriter(fileChooser.getSelectedFile()));
                        writer.write(message2.getText());
                        writer.close();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
        btn4open.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setFileFilter(new FileNameExtensionFilter("Text files", "txt"));
                int returnVal = fileChooser.showOpenDialog(null);
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    try {
                        BufferedReader reader = new BufferedReader(new FileReader(fileChooser.getSelectedFile()));
                        String line = null;
                        while ((line = reader.readLine()) != null) {
                            message.setText(line + "\n");
                        }
                        reader.close();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });

        message2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });


    }

    public static void main(String[] args) {
        app a = new app();
        JButton btn1 = new JButton();
        btn1.setSize(80, 60);
        a.getContentPane().add(btn1);
        JButton btn2 = new JButton();
        a.getContentPane().add(btn2);
        JButton btn3save = new JButton();
        a.getContentPane().add(btn3save);
        JButton btn4open = new JButton();
        a.getContentPane().add(btn4open);
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new FileNameExtensionFilter("Text files", "txt"));

        JComboBox<String> comboBox1 = new JComboBox<>();
        comboBox1.addItem("ECB");
        comboBox1.addItem("CBC");
        comboBox1.addItem("CFB");
        a.add(comboBox1);


        a.pack();
        a.setContentPane(a.panelMain);
        a.setTitle("algoritmas");
        a.setSize(300, 400);
        a.setVisible(true);
        a.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}



