package org.example;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigInteger;

public class app extends JFrame {
    private JPanel panelMain;
    private JTextField txtField3;
    private JTextField txtField4;
    private JButton btn1generate;
    private JTextField pubKey;
    private JLabel generatedValues1;

    private JButton btn3Send;
    private JButton btn4Receive;


    public app() {


        btn3Send.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String n = pubKey.getText();
                String[] keys = n.split(" ");
                String sendMessage = keys[0] + " " + keys[1] + "!NEWLINE!" +
                        txtField3.getText() + "!NEWLINE!" + txtField4.getText();
                Server.sendMessage(sendMessage);
            }
        });


        btn4Receive.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String message = Server.receiveMessage();
                String[] values = message.split("!NEWLINE!");
                pubKey.setText(values[0]);
                txtField3.setText(values[1]);
                txtField4.setText(values[2]);
            }
        });
    }


    public static void main(String[] args) {
        app a = new app();


        JButton btn3Send = new JButton();
        a.getContentPane().add(btn3Send);

        JButton btn4Receive = new JButton();
        a.getContentPane().add(btn4Receive);


        a.pack();
        a.setContentPane(a.panelMain);
        a.setTitle("algoritmas");
        a.setVisible(true);
        a.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}
