import javax.swing.*; // imports classes and components of the Java API that provides components for building graphical user interface (GUI)
import java.awt.*; // imports classes, interfaces, and enumerations from Abstract Window Toolkit (AWT), used for building windows, buttons, text fields, etc.

public class chessboard extends JFrame {

    // a container
    static JPanel panel = new JPanel(); // a generic, lightweight container, wiil be added to the overarching container to be displayed
    // window that holds the chessboard, will be displayed in the display panel window
    static JPanel sidePanel = sidePanel();
    JPanel boardPanel; 
    // a 2D Array of Tile objects
    Tile[][] boardCells;

    JPanel buttonPanel; // EXTRA

    public chessboard(){
        // sets the title of the panel
        super();
        setTitle("Chess Board");
        
        // sets the panel with a bordered layout
        panel.setLayout(new BorderLayout()); // BorderLayout organizes the panel to have NORTH EAST SOUTH WEST sides

        boardPanel = new JPanel();
        boardPanel.setLayout(new GridLayout(8, 8)); // GridLayout organizes the panel to have grid

        // method call
        initializeBoard();

        // adds the boardPanel to the panel, and centers it
        panel.add(boardPanel, BorderLayout.CENTER);

        // adds the panel to the container to be displayed
        add(panel);
        // displays the container panel to the user at the center of the screen 
        setLocationRelativeTo(null); // null sets the container in the center of the screen
        // set the size of the panel
        setSize(900, 800);
        // make the panel visible
        setVisible(true);
        // add the close window operation (close button)
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        // sidePanel
        add(sidePanel, BorderLayout.WEST);
        
    }

    // method to initialize the chessboard
    private void initializeBoard() {
        // create an array of Tiles
        boardCells = new Tile[8][8];
        // traverse the boardCells
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                // sets the backgroundColor to white if the tile is at an even position, or gray if its at an odd position
                Color backgroundColor = (row + col) % 2 == 0 ? Color.WHITE : Color.GRAY; // serves as an if statement, ? operator makes a Boolean, it its true then WHITE, : sets else, if its false, then GRAY
                // initialize a new Tile and set its backgroundColor
                Tile tile = new Tile(backgroundColor);
                // place the tile in the current position
                boardCells[row][col] = tile;
                // add the tile to the boardPanel to be displayed on the panel
                boardPanel.add(tile);
            }
        }
    }

    // Side Panel code
    public static JPanel sidePanel(){   
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
        // panel.add(bottonsPanel, BorderLayout.WEST);
        // panel.setVisible(true);
        return bottonsPanel;
    }

    

    // main method
    public static void main(String[] args) {
        new chessboard();
    }
}

// object class for the tiles in the chessboard
class Tile extends JPanel { // extends to JPanel so that we can make each tile a component that can be added to containers, tiles serve as separate containers
    // tile constructor method, takes color as a parameter
    public Tile(Color color) {
        // set the background color of the tile
        setBackground(color);
    }

    // method that adjusts the size of the tiles, is overridden
    @Override
    public Dimension getPreferredSize() { // getPrefferedSize() is a built in function for .swing
        // gets the preferred size of the tiles using the super class, is determined by the size of the panel where the tiles will be contained
        Dimension size = super.getPreferredSize();
        // calculates the size for the tiles
        int maxSize = Math.max(size.width, size.height);
        // return the Dimension object for the tiles where the width and height are equal in length
        return new Dimension(maxSize, maxSize);
    }
}


