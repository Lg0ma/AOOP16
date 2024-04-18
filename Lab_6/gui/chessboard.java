package gui;

import javax.swing.*; // imports classes and components of the Java API that provides components for building graphical user interface (GUI)
import java.awt.*; // imports classes, interfaces, and enumerations from Abstract Window Toolkit (AWT), used for building windows, buttons, text fields, etc.

public class chessboard extends JFrame {

    // a container
    JPanel panel; // a generic, lightweight container, wiil be added to the overarching container to be displayed
    // window that holds the chessboard, will be displayed in the display panel window
    JPanel boardPanel; 
    // a 2D Array of Tile objects
    Tile[][] boardCells;

    JPanel buttonPanel; // EXTRA

    public chessboard() {
        // sets the title of the panel
        super();
        setTitle("Chess Board");
        // initializes a new panel
        panel = new JPanel();

        buttonPanel = new JPanel(); // EXTRA

        JButton addButton = new JButton("Add Task"); // EXTRA

        // sets the panel with a bordered layout
        panel.setLayout(new BorderLayout()); // BorderLayout organizes the panel to have NORTH EAST SOUTH WEST sides

        boardPanel = new JPanel();
        boardPanel.setLayout(new GridLayout(8, 8)); // GridLayout organizes the panel to have grid

        // method call
        initializeBoard();

        // adds the boardPanel to the panel, and centers it
        panel.add(boardPanel, BorderLayout.CENTER);

        buttonPanel.add(addButton); //EXTRA
        panel.add(buttonPanel, BorderLayout.WEST); // EXTRA

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