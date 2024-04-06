package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChessGameFrame2 extends JFrame implements ActionListener {

    JPanel panel;
    JLabel labelPosX, labelPosY;
    JTextField texfieldPosX, textfieldPosY;

    JButton OKButton;

    public ChessGameFrame2() {
        super();
        setTitle("Chess Game");

        panel = new JPanel();
        panel.setLayout(new FlowLayout());

        labelPosX = new JLabel("Column:");
        texfieldPosX = new JTextField();
        texfieldPosX.setPreferredSize(new Dimension(40,40));

        labelPosY = new JLabel("Row:");
        textfieldPosY = new JTextField();
        textfieldPosY.setPreferredSize(new Dimension(40,40));

        OKButton = new JButton();
        OKButton.setText("move");
        OKButton.addActionListener(this);

        panel.add(labelPosX);
        panel.add(texfieldPosX);
        panel.add(labelPosY);
        panel.add(textfieldPosY);
        panel.add(OKButton);

        add(panel);
        setLocationRelativeTo(null);
        setSize(300, 300);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //this.setExtendedState(JFrame.MAXIMIZED_BOTH);

    }



    public static void main(String[] args) {
        ChessGameFrame2 cgMainFrame = new ChessGameFrame2();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //call your verify move method
        JOptionPane.showMessageDialog(new JFrame(),"You Clicked Move Button");
    }
}
