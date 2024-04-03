package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChessGameMainFrame extends JFrame implements ActionListener {


    String[] petStrings = {"--", "Pawn", "Rook", "Bishop", "Knight", "Queen", "King" };
    JPanel panel;
    JLabel labelWelcome, labelPieceName, labelPosX, labelPosY;
    JTextArea textareaPosX, textareaPosY;
    JComboBox pieceList = new JComboBox(petStrings);


    JButton OKButton;

    public ChessGameMainFrame() {
        super();
        setTitle("Chess Game");
        panel = new JPanel();
        panel.setLayout(new FlowLayout());

        labelPieceName = new JLabel("Select Piece:");
        pieceList.setSelectedIndex(0);

        labelPosX = new JLabel("Column:");
        textareaPosX = new JTextArea();
        textareaPosY = new JTextArea();
        labelPosY = new JLabel("Row:");


        OKButton = new JButton(); //Cerating a button
        OKButton.setText("Select");

        OKButton.addActionListener(this);// register an action listener

        panel.add(labelPieceName);
        panel.add(pieceList);

        panel.add(OKButton);

        add(panel);
        setLocationRelativeTo(null);
        setSize(300, 300);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    }



    public static void main(String[] args) {
        ChessGameMainFrame cgMainFrame = new ChessGameMainFrame();
    }

    @Override
    public void actionPerformed(ActionEvent e) { //Handle the event
        //call your verify move method
        JOptionPane.showMessageDialog(new JFrame(),
                "You have selected a piece");
    }
}
