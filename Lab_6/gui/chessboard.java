import javax.swing.*; // imports classes and components of the Java API that provides components for building graphical user interface (GUI)
import java.awt.*; // imports classes, interfaces, and enumerations from Abstract Window Toolkit (AWT), used for building windows, buttons, text fields, etc.

// class for creating the chessboard window with its operations
public class chessboard extends JFrame {

    // a container
    static JPanel panel = new JPanel(); // a generic, lightweight container, wil be added to the overarching container to be displayed
    // window that holds the chessboard, will be displayed in the display panel window
    static JPanel sidePanel = sidePanel();
    JPanel boardPanel; 
    // a 2D Array of Tile objects
    Tile[][] boardCells;
    // a button side panel
    JPanel buttonPanel;

    // the chessboard function
    public chessboard(){
        // sets the title of the panel
        super();
        setTitle("Chess Board");
        // sets the panel with a bordered layout
        panel.setLayout(new BorderLayout()); // BorderLayout organizes the panel to have NORTH EAST SOUTH WEST sides
        // initialize a panel for the chessboard and its grid layout
        boardPanel = new JPanel();
        boardPanel.setLayout(new GridLayout(8, 8)); // GridLayout organizes the panel to have grid
        // method call to fill the panel
        initializeBoard();
        // adds the boardPanel to the panel, and centers it in the window
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
        // add the sidePanel
        add(sidePanel, BorderLayout.WEST);
    }

    // function to initialize the chessboard
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

// function to set the components for the sidePanel
public static JPanel sidePanel(){   
    // initialize the grey color
    Color gray = new Color(192,192,192);
    // panel where controls are going to live and set its background as grey
    JPanel buttonsPanel = new JPanel();
    buttonsPanel.setBackground(gray);
    // making layout for side panel
    BoxLayout buttonLayout = new BoxLayout(buttonsPanel, BoxLayout.Y_AXIS);
    buttonsPanel.setLayout(buttonLayout);
    // lists used for dropdowns options
    String [] chessPieces = {"Pawn", "Rook", "Knight", "Queen", "King"};
    String [] row = {"1", "2", "3", "4", "5", "6", "7", "8"};
    String [] col = {"A", "B", "C", "D", "E", "F", "G", "H"};
    String [] colors = {"White", "Black"};
    // components that will go on side panel
    JLabel chessType = new JLabel("Select a chess piece");
    JLabel inputLabel = new JLabel("Select initial chess pos.");
    JLabel colorLabel = new JLabel("Chess piece color");
    JLabel finalPosLabel = new JLabel("Select final chess pos.");
    // align the components in the center of the side panel
    chessType.setAlignmentX(Component.CENTER_ALIGNMENT);
    inputLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
    colorLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
    finalPosLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
    // add the chessType component first
    buttonsPanel.add(chessType);
    buttonsPanel.add(Box.createVerticalStrut(5));
    // dropdown and button creation
    JComboBox colDropdown = new JComboBox<>(col);
    JComboBox rowDropdown = new JComboBox<>(row);
    JComboBox colorDropdown = new JComboBox<>(colors);
    JComboBox finalColDropdown = new JComboBox<>(col);
    JComboBox finalRowDropdown = new JComboBox<>(row);
    // alignments for dropdowns and buttons
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
    // other buttons
    JButton createButton = new JButton("Create piece");
    JButton moveButton = new JButton("Move piece");
    // button group to be able to click only one button at a time
    ButtonGroup buttonPieces = new ButtonGroup();
    // radio buttons
    for(String type: chessPieces){
        JRadioButton buttonType = new JRadioButton(type);
        buttonType.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonPieces.add(buttonType);
        buttonsPanel.add(buttonType);
        // Removing all space around button panel so they look closer
    }
    // buttons alignment
    createButton.setAlignmentX(Component.CENTER_ALIGNMENT);
    moveButton.setAlignmentX(Component.CENTER_ALIGNMENT);
    // add spacing between the buttons
    buttonsPanel.add(Box.createVerticalStrut(10));
    // add components to side panel
    buttonsPanel.add(Box.createVerticalStrut(20));
    buttonsPanel.add(colorLabel);
    buttonsPanel.add(Box.createVerticalStrut(5));
    buttonsPanel.add(colorDropdown);
    buttonsPanel.add(Box.createVerticalStrut(20));
    buttonsPanel.add(inputLabel);
    buttonsPanel.add(Box.createVerticalStrut(5));
    // create a panel for the initial position selectors
    JPanel initialPosPanel = new JPanel();
    initialPosPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
    initialPosPanel.setLayout(new BoxLayout(initialPosPanel, BoxLayout.X_AXIS));
    initialPosPanel.add(colDropdown);
    initialPosPanel.add(rowDropdown);
    buttonsPanel.add(initialPosPanel);
    buttonsPanel.add(Box.createVerticalStrut(20));
    buttonsPanel.add(finalPosLabel);
    buttonsPanel.add(Box.createVerticalStrut(5));
    // create a panel for the final position selectors
    JPanel finalPosPanel = new JPanel();
    finalPosPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
    finalPosPanel.setLayout(new BoxLayout(finalPosPanel, BoxLayout.X_AXIS));
    finalPosPanel.add(finalColDropdown);
    finalPosPanel.add(finalRowDropdown);
    buttonsPanel.add(finalPosPanel);
    buttonsPanel.add(Box.createVerticalStrut(40));
    // add create and move buttons
    buttonsPanel.add(createButton);
    buttonsPanel.add(Box.createVerticalStrut(5)); // add space between buttons
    buttonsPanel.add(moveButton);
    // return the panel
    return buttonsPanel;
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


