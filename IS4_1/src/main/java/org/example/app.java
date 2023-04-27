package org.example;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

    private JButton btn3Send;
    private JButton btn2encryption;


    public app() {
        btn1generate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int keys[] = RSA.key_gen(Integer.valueOf(txtField1.getText()), Integer.valueOf(txtField2.getText()));
                pubKey.setText(keys[0] + " " + keys[2]);
                priKey.setText(keys[1] + " " + keys[2]);

            }
        });

        btn3Send.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String n = pubKey.getText();
                String[] keys = n.split(" ");
                String sendMessage = keys[0] + " " + keys[1] + "!NEWLINE!" +
                        txtField3.getText() + "!NEWLINE!" + txtField4.getText();
                Client.sendMessage(sendMessage);
            }
        });
        btn2encryption.addActionListener(new ActionListener() {
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


    }



    public static void main(String[] args) {
        app a = new app();
        JButton btn1generate = new JButton();
        a.getContentPane().add(btn1generate);

        JButton btn3Send = new JButton();
        a.getContentPane().add(btn3Send);


        JButton btn2encryption = new JButton();
        a.getContentPane().add(btn2encryption);





        a.pack();
        a.setContentPane(a.panelMain);
        a.setTitle("algoritmas");
        a.setVisible(true);
        a.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}