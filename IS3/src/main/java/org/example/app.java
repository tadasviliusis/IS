package org.example;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.math.BigInteger;

public class app extends JFrame {
    private JLabel p;
    private JLabel q;
    private JPanel panelMain;
    private JTextField txtField1;
    private JTextField txtField2;
    private JButton btn1generate;
    private JTextField pubKey;
    private JTextField priKey;
    private JLabel generatedValues1;
    private JLabel generatedvalues2;
    private JTextField txtField3;
    private JTextField txtField4;
    private JTextField txtField5;
    private JButton btn2savepubKey;
    private JButton btn3openpubKey;
    private JButton btn4savepriKey;
    private JButton btn5openpriKey;
    private JButton btn6PQfinderButton;
    private JButton btn7encryption;
    private JButton btn8decryption;
    private JButton btn9saveencrypted;
    private JButton btn10openencrypted;

    private JFileChooser fileChooser;

    public app() {
        btn1generate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int keys[] = RSA.key_gen(Integer.valueOf(txtField1.getText()), Integer.valueOf(txtField2.getText()));
                pubKey.setText(keys[0] + " " + keys[2]);
                priKey.setText(keys[1] + " " + keys[2]);

            }
        });
        btn2savepubKey.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                writer("pubKey");
            }
        });
        btn3openpubKey.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                opener("pubKey");
            }
        });
        btn4savepriKey.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                writer("priKey");
            }
        });
        btn5openpriKey.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                opener("priKey");
            }
        });
        btn6PQfinderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String n = pubKey.getText();
                String[] values = n.split(" ");
                int[] pq = RSA.PQfinder(new BigInteger(values[1]));
                txtField1.setText(String.valueOf(pq[0]));
                txtField2.setText(String.valueOf(pq[1]));
                ;
            }
        });
        btn7encryption.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String msg;
                msg = txtField3.getText();
                String n = priKey.getText();
                String[] values = n.split(" ");
                String enMsg = RSA.encrypt(msg, new BigInteger(values[0]), new BigInteger(values[1]));
                txtField4.setText(enMsg);


            }
        });

        btn8decryption.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String msg;
                msg = txtField4.getText();
                String n = pubKey.getText();
                String[] values = n.split(" ");
                String enMsg = RSA.decrypt(msg, new BigInteger(values[0]), new BigInteger(values[1]));
                txtField5.setText(enMsg);
            }
        });
        btn9saveencrypted.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                writer("encrypted");
            }
        });
        btn10openencrypted.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                opener("encrypted");
            }
        });


    }

    public void writer(String chooser) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new FileNameExtensionFilter("Text files", "txt"));
        int returnVal = fileChooser.showSaveDialog(null);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter(fileChooser.getSelectedFile()));
                writer.write(pubKey.getText());
                if (chooser == "pubKey")
                    writer.write(pubKey.getText());
                else if (chooser == "priKey") {
                    writer.write(priKey.getText());
                } else
                    writer.write(txtField4.getText());
                writer.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public void opener(String chooser) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new FileNameExtensionFilter("Text files", "txt"));
        int returnVal = fileChooser.showOpenDialog(null);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            try {
                BufferedReader reader = new BufferedReader(new FileReader(fileChooser.getSelectedFile()));
                String line = null;
                if (chooser == "pubKey") {
                    while ((line = reader.readLine()) != null) {
                        pubKey.setText(line + "\n");
                    }
                } else if (chooser == "priKey") {
                    while ((line = reader.readLine()) != null) {
                        priKey.setText(line + "\n");

                    }
                } else {
                    while ((line = reader.readLine()) != null) {

                        txtField4.setText(line + "\n");
                    }
                }

                reader.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        app a = new app();
        JButton btn1generate = new JButton();
        a.getContentPane().add(btn1generate);

        JButton btn2savepubKey = new JButton();
        a.getContentPane().add(btn2savepubKey);

        JButton btn3openpriKey = new JButton();
        a.getContentPane().add(btn3openpriKey);

        JButton btn4savepriKey = new JButton();
        a.getContentPane().add(btn4savepriKey);

        JButton btn5openpriKey = new JButton();
        a.getContentPane().add(btn5openpriKey);

        JButton btn6PQfinderButton = new JButton();
        a.getContentPane().add(btn6PQfinderButton);

        JButton btn7encryption = new JButton();
        a.getContentPane().add(btn7encryption);

        JButton btn8decryption = new JButton();
        a.getContentPane().add(btn8decryption);

        JButton btn9saveencrypted = new JButton();
        a.getContentPane().add(btn9saveencrypted);

        JButton btn10openencrypted = new JButton();
        a.getContentPane().add(btn10openencrypted);


        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new FileNameExtensionFilter("Text files", "txt"));


        a.pack();
        a.setContentPane(a.panelMain);
        a.setTitle("algoritmas");
        a.setVisible(true);
        a.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}