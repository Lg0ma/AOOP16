 // Import necessary Swing and AWT classes for GUI components
import javax.swing.*;
import java.awt.*;
import java.util.Vector;


public class chessboard_picture{
    public static void main(String[] args) {
        // Create a DefaultListModel and a JList for managing and displaying to-do list items
        DefaultListModel<String> toDoListModel = new DefaultListModel<>();
        JList<String> toDoList = new JList<>(toDoListModel);
        SwingUtilities.invokeLater(() -> {
            // Where you call the methods you create to display/use? your GUI
            sidePanel();
        });
    }

    public static void sidePanel(){   
        JFrame frame = new JFrame("Chess Game");
        frame.setSize(900, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Color macro
        Color gray = new Color(192,192,192);

        // Panels where controls are going to live
        JPanel bottonsPanel = new JPanel();
        bottonsPanel.setBackground(gray);

        //Making layout for side Panel
        BoxLayout buttonLayout = new BoxLayout(bottonsPanel, BoxLayout.Y_AXIS);
        bottonsPanel.setLayout(buttonLayout);

        // Lists used for dropdowns
        String [] chessPieces = {"Pawn", "Rook", "Knight", "Queen", "King"};
        String [] row = {"1", "2", "3", "4", "5", "6", "7", "8"};
        String [] col = {"A", "B", "C", "D", "E", "F", "G", "H"};
        String [] colors = {"White", "Black"};

        // Components that will go on side panel
        JLabel chessType = new JLabel("Select a chess piece");
        JLabel inputLabel = new JLabel("Select inital chess pos.");
        JLabel colorLabel = new JLabel("Chess piece color");
        JLabel finalPosLabel = new JLabel("Select final chess pos.");

        chessType.setAlignmentX(Component.CENTER_ALIGNMENT);
        inputLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        colorLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        finalPosLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Addign these first to be able to add RadioButtons in a loop
        bottonsPanel.add(chessType);
        bottonsPanel.add(Box.createVerticalStrut(5));
        
        // Dropdown and button creation
        JComboBox colDropdown = new JComboBox<>(col);
        JComboBox rowDropdown = new JComboBox<>(row);
        JComboBox colorDropdown = new JComboBox<>(colors);
        JComboBox finalColDropdown = new JComboBox<>(col);
        JComboBox finalRowDropdown = new JComboBox<>(row);

        // Alignments for dropdowns and buttons
        colDropdown.setMaximumSize(colDropdown.getPreferredSize());
        colDropdown.setAlignmentX(Component.CENTER_ALIGNMENT);
        rowDropdown.setMaximumSize(rowDropdown.getPreferredSize());
        rowDropdown.setAlignmentX(Component.CENTER_ALIGNMENT);
        colorDropdown.setAlignmentX(Component.CENTER_ALIGNMENT);
        colorDropdown.setMaximumSize(colorDropdown.getPreferredSize());
        finalColDropdown.setAlignmentX(Component.CENTER_ALIGNMENT);
        finalColDropdown.setMaximumSize(finalColDropdown.getPreferredSize());
        finalRowDropdown.setAlignmentX(Component.CENTER_ALIGNMENT);
        finalRowDropdown.setMaximumSize(finalRowDropdown.getPreferredSize());

        // Other buttons
        JButton createButton = new JButton("Create piece");
        JButton moveButton = new JButton("Move piece");

        // Button group to be able to click only one button at a time
        ButtonGroup buttonPieces = new ButtonGroup();

        // Radio buttons
        for(String type: chessPieces){
            JRadioButton buttonType = new JRadioButton(type);
            buttonType.setAlignmentX(Component.CENTER_ALIGNMENT);
            buttonPieces.add(buttonType);
            bottonsPanel.add(buttonType);
            // Removing all space around button panel so they look closer
        }
        // Buttons alignment
        createButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        moveButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        
        // Adding components to side 
        bottonsPanel.add(Box.createVerticalStrut(20));
        bottonsPanel.add(colorLabel);
        bottonsPanel.add(Box.createVerticalStrut(5));
        bottonsPanel.add(colorDropdown);
        bottonsPanel.add(Box.createVerticalStrut(20));
        bottonsPanel.add(inputLabel);
        bottonsPanel.add(Box.createVerticalStrut(5));
        bottonsPanel.add(rowDropdown);
        bottonsPanel.add(colDropdown);
        bottonsPanel.add(Box.createVerticalStrut(20));
        bottonsPanel.add(finalPosLabel);
        bottonsPanel.add(Box.createVerticalStrut(5));
        bottonsPanel.add(finalColDropdown);
        bottonsPanel.add(finalRowDropdown);
        bottonsPanel.add(Box.createVerticalStrut(40));
        bottonsPanel.add(createButton);
        bottonsPanel.add(moveButton);


        // Lines that modfiy frame
        // frame.add(bottonsPanel, BorderLayout.WEST);
        frame.add(bottonsPanel, BorderLayout.WEST);
        frame.setVisible(true);
    }
}