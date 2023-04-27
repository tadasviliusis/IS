package org.example;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigInteger;

public class app extends JFrame {
    private JPanel panelMain;
    private JTextField txtField1;
    private JTextField txtField2;
    private JTextField pubKey;
    private JTextField priKey;
    private JLabel generatedValues1;
    private JTextField txtField3;
    private JTextField txtField4;

    private JButton btn1receive;
    private JButton btn2validate;


    public app() {

        btn1receive.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String message = Client.receiveMessage();
                String[] values = message.split("!NEWLINE!");
                pubKey.setText(values[0]);
                txtField3.setText(values[1]);
                txtField4.setText(values[2]);

            }
        });
        btn2validate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[] keys = pubKey.getText().split(" ");
                String decryptedMessage = RSA.decrypt(txtField4.getText(), new BigInteger(keys[0]), new BigInteger(keys[1]));

                if(decryptedMessage.equals(txtField3.getText()))
                    JOptionPane.showMessageDialog(null,"SIGNATURE IS VALID");
                else
                    JOptionPane.showMessageDialog(null,"SIGNATURE IS NOT VALID");
            }
        });


    }



    public static void main(String[] args) {
        app a = new app();
        JButton btn1receive = new JButton();
        a.getContentPane().add(btn1receive);


        JButton btn2validate = new JButton();
        a.getContentPane().add(btn2validate);




        a.pack();
        a.setContentPane(a.panelMain);
        a.setTitle("algoritmas");
        a.setVisible(true);
        a.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}