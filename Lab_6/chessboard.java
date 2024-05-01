import javax.swing.*;
import chess_pieces.*;
import enums.chess_piece_type;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Objects;

/**
 * This class represents a chessboard which extends JFrame.
 * @author Andre Melendez
 * @author Ivan Armenta
 * @author Luis Gomez
 */
public class chessboard extends JFrame {

    // ArrayList collection that holds the Figure objects
    /**
     * An ArrayList that holds the chess pieces on the board.
     */
    static ArrayList<Object> pieces = new ArrayList<>(); // CHANGED : FROM Figure TO Object

    // static vars for creating a Figure object (or Bishop)
    private static Figure piece;
    private static Bishop bish;
    private static String selectedPieceType = "";
    private static String selectedPieceColor = "";
    private static String selectedPieceRow = "";
    private static String selectedPieceCol = "";

    // icons
    static ImageIcon pawn = new ImageIcon("art\\BP.gif");
    static ImageIcon knight = new ImageIcon("art\\BN.gif");
    static ImageIcon rook = new ImageIcon("art\\BR.gif");
    static ImageIcon bishop = new ImageIcon("art\\BB.gif");
    static ImageIcon queen = new ImageIcon("art\\BQ.gif");
    static ImageIcon king = new ImageIcon("art\\BK.gif");
    static ImageIcon white_pawn = new ImageIcon("art\\WP.gif");
    static ImageIcon white_knight = new ImageIcon("art\\WN.gif");
    static ImageIcon white_rook = new ImageIcon("art\\WR.gif");
    static ImageIcon white_bishop = new ImageIcon("art\\WB.gif");
    static ImageIcon white_queen = new ImageIcon("art\\WQ.gif");
    static ImageIcon white_king = new ImageIcon("art\\WK.gif");

    // initialize the panels
    static JPanel panel = new JPanel();
    static JPanel sidePanel = sidePanel();
    JPanel boardPanel;
    static Tile[][] boardCells;

    /**
     * Constructor for the chessboard class.
     */
    public chessboard() {

        // initialize the window and its title
        super();
        setTitle("Chess Game (Lab 6)");

        // set up the grid for the chessboard
        panel.setLayout(new BorderLayout());
        boardPanel = new JPanel();
        boardPanel.setLayout(new GridLayout(8, 8));

        // method call to initializeBoard
        initializeBoard();

        // set up the panel orientation
        panel.add(boardPanel, BorderLayout.CENTER);
        add(panel);
        setLocationRelativeTo(null);
        setSize(900, 800);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        add(sidePanel, BorderLayout.WEST);
    }

    /**
     * This method initializes the chessboard.
     */
    private void initializeBoard() {

        // initialize the chess board tiles
        boardCells = new Tile[8][8];

        // set the colors for the tiles and add the tiles to the panel
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                Color backgroundColor = (row + col) % 2 == 0 ? Color.WHITE : Color.GRAY;
                Tile tile = new Tile(backgroundColor);
                boardCells[row][col] = tile;
                boardPanel.add(tile);
            }
        }
    }

    /**
     * This method creates a side panel for the chessboard with various controls.
     * @return JPanel The side panel.
     */
    private static Boolean can_create(enums.chess_piece_type type) {

        // for loop to traverse the existing valid Figure objects
        for (Object p : pieces) {
            // if the object is an instance of a Bishop object
            if (p instanceof Bishop temp) { // MAY UPDATE WITH A JUnit Test
                // if the type has already been created, return false
                if (temp.getType() == type) {
                    return false;
                }
            }
            // otherwise the object is a Figure object
            else if (p instanceof Figure temp){
                // if the type has already been created, return false
                if (temp.getType() == type) {
                    return false;
                }
            }
        }
        // return true since the object can be created
        return true;
    }
    /**
     * Method that checks if coordinate on the chessboard is occupied.
     * @param col column coordinate to be checked
     * @param row row coordinate to be checked
     * @return True if the coordinates inputted are not occupied, False otherwise
     */
    private static Boolean not_occupied(enums.chess_piece_columns col, int row) {

        // for loop to traverse the existing valid Figure objects
        for (Object p : pieces) {
            // if the object is an instance of a Bishop object
            if (p instanceof Bishop temp) { // MAY UPDATE WITH A JUnit Test
                // if the coordinates match, return false
                if (col == temp.getColumn() && row == temp.getRow()) {
                    return false;
                }
            }
            // otherwise the object is a Figure object
            else if (p instanceof Figure temp) {
                // if the coordinates match, return false
                if (col == temp.getColumn() && row == temp.getRow()) {
                    return false;
                }
            }
        }
        // return true since the coordinates are not occupied
        return true;
    }

    /**
     * This method creates a side panel for the chessboard with various controls.
     * @return JPanel The side panel.
     */
    private static JPanel sidePanel() {

        // initialize a panel for buttons
        /**
         * A JPanel that holds the buttons for the side panel.
         */
        JPanel buttonsPanel = new JPanel();
        BoxLayout buttonLayout = new BoxLayout(buttonsPanel, BoxLayout.Y_AXIS);
        buttonsPanel.setLayout(buttonLayout);

        // initialize the different options to select from the buttons
        String[] chessPieces = {"Pawn", "Rook", "Knight", "Bishop", "Queen", "King"};
        String[] row = {"-", "1", "2", "3", "4", "5", "6", "7", "8"};
        String[] col = {"-", "A", "B", "C", "D", "E", "F", "G", "H"};
        String[] colors = {"-", "White", "Black"};

        // initialize the labels for each selector/button/dropdown menu
        JLabel chessType = new JLabel("Select a chess piece");
        JLabel inputLabel = new JLabel("Select initial chess pos.");
        JLabel colorLabel = new JLabel("Chess piece color");

        // align the labels
        chessType.setAlignmentX(Component.CENTER_ALIGNMENT);
        inputLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        colorLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // add the chess type radio buttons to the button panel
        buttonsPanel.add(chessType);
        buttonsPanel.add(Box.createVerticalStrut(5));

        // initialize drop down menus
        JComboBox<String> colDropdown = new JComboBox<>(col);
        JComboBox<String> rowDropdown = new JComboBox<>(row);
        JComboBox<String> colorDropdown = new JComboBox<>(colors);

        // set up the dropdown menu for the column coordinate
        colDropdown.setMaximumSize(colDropdown.getPreferredSize());
        colDropdown.setAlignmentX(Component.CENTER_ALIGNMENT);

        // initialize an action listener for the column dropdown menu
        colDropdown.addActionListener(e -> {

            // get the selected item from the dropdown menu
            JComboBox<String> comboBox = (JComboBox<String>) e.getSource();
            selectedPieceCol = (String) comboBox.getSelectedItem();
        });

        // set up the dropdown menu for the row coordinate
        rowDropdown.setMaximumSize(rowDropdown.getPreferredSize());
        rowDropdown.setAlignmentX(Component.CENTER_ALIGNMENT);

        // initialize an action listener for the row dropdown menu
        rowDropdown.addActionListener(e -> {

            // get the selected item from the dropdown menu
            JComboBox<String> comboBox = (JComboBox<String>) e.getSource();
            selectedPieceRow = (String) comboBox.getSelectedItem();
        });

        // set up the dropdown menu for the color
        colorDropdown.setAlignmentX(Component.CENTER_ALIGNMENT);
        colorDropdown.setMaximumSize(colorDropdown.getPreferredSize());

        // initialize an action listener for the color dropdown menu
        colorDropdown.addActionListener(e -> {

            // get the selected item from the dropdown menu
            JComboBox<String> comboBox = (JComboBox<String>) e.getSource();
            selectedPieceColor = (String) comboBox.getSelectedItem();
        });

        // initialize buttons for creating a Figure object and moving existing objects in the chessboard
        JButton createButton = new JButton("Create piece");
        JButton moveButton = new JButton("Move piece");
        ButtonGroup buttonPieces = new ButtonGroup();

        // add radio buttons for selecting a type of chess piece to the button panel
        for (String type : chessPieces) {
            JRadioButton buttonType = new JRadioButton(type);
            buttonType.setAlignmentX(Component.CENTER_ALIGNMENT);
            buttonPieces.add(buttonType);
            buttonsPanel.add(buttonType);

            // initialize an action listener for the type of chess piece to be created
            buttonType.addActionListener(e -> {

                // set up the var
                selectedPieceType = type;
            });
        }

        // set up the buttons
        createButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        moveButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        // set up the buttons panel
        buttonsPanel.add(Box.createVerticalStrut(10));
        buttonsPanel.add(Box.createVerticalStrut(20));
        buttonsPanel.add(colorLabel);
        buttonsPanel.add(Box.createVerticalStrut(5));
        buttonsPanel.add(colorDropdown);
        buttonsPanel.add(Box.createVerticalStrut(20));
        buttonsPanel.add(inputLabel);
        buttonsPanel.add(Box.createVerticalStrut(5));

        // initialize a new panel for the dropdown menus for the column and row
        JPanel initialPosPanel = new JPanel();
        initialPosPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        initialPosPanel.setLayout(new BoxLayout(initialPosPanel, BoxLayout.X_AXIS));
        initialPosPanel.add(colDropdown);
        initialPosPanel.add(rowDropdown);
        // add the dropdown menus to the buttons panel
        buttonsPanel.add(initialPosPanel);
        buttonsPanel.add(Box.createVerticalStrut(20));
        buttonsPanel.add(Box.createVerticalStrut(5));
        buttonsPanel.add(Box.createVerticalStrut(40));

        // add the 'Create piece' button to the buttons panel
        buttonsPanel.add(createButton);

        // initialize an action listener for creating a Figure object
        createButton.addActionListener(e -> {

            // set up the pop up window and its panels, messages, and buttons
            JFrame popUpFrame = new JFrame("Notice");
            JLabel messageLabel = new JLabel();
            JPanel buttonPanel = new JPanel();
            JButton closeGame = new JButton("Exit Game");
            JButton createNew = new JButton("Create New Piece");
            JButton tryAgain = new JButton("Try Again");

            // initialize an action listener for the 'Exit Game' button
            closeGame.addActionListener(a -> {
                // close the program
                System.exit(0);
            });

            // initialize an action listener for the 'Create New Piece' button
            createNew.addActionListener(n -> {
                // close the frame
                popUpFrame.dispose();
            });

            // initialize an action listener for the 'Try Again' button
            tryAgain.addActionListener(n -> {
                // close the frame
                popUpFrame.dispose();
            });

            // if the required inputs are selected
            if (!Objects.equals(selectedPieceType, "") && !Objects.equals(selectedPieceColor, "") && !Objects.equals(selectedPieceCol, "") && !Objects.equals(selectedPieceRow, "")) { // MAY UPDATE WITH A JUnit Test
                // initialize vars that hold the inputs for creating a Figure object
                enums.chess_piece_type etype = enums.chess_piece_type.valueOf(selectedPieceType.toUpperCase());
                enums.chess_piece_color ecolor = enums.chess_piece_color.valueOf(selectedPieceColor.toUpperCase());
                enums.chess_piece_columns eco = enums.chess_piece_columns.valueOf(selectedPieceCol);
                int r = Integer.parseInt(selectedPieceRow);

                // if the selected type is a Bishop
                if (Objects.equals(selectedPieceType, "Bishop")) {
                    // try
                    try {

                        // create the Bishop object
                        bish = new Bishop(etype, ecolor, eco, r);

                        // if the object can be created and the coordinates are not occupied
                        if (can_create(etype) && not_occupied(eco, r)) { // MAY UPDATE WITH A JUnit Test

                            // set up the buttons
                            buttonPanel.add(createNew);
                            buttonPanel.add(closeGame);

                            // initialize and set up the success message
                            messageLabel.setText("You created the following chess piece: " + etype + " " + ecolor + " " + eco + ", " + r);
                            messageLabel.setHorizontalAlignment(SwingConstants.CENTER);
                            buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

                            // initialize, set up, and display the pop-up frame
                            pop_up_frame(popUpFrame, messageLabel, buttonPanel);

                            // add the created Figure object to the collection of pieces
                            pieces.add(bish);

                            System.out.println("The following object was created and added to the ArrayList: " + bish); // FOR TERMINAL USE

                            // set up the vars for updating the tile
                            int row1 = Integer.parseInt(selectedPieceRow) - 1;
                            int col1 = eco.ordinal();

                            // get the tile to place the icon for the Figure object
                            Tile tile = boardCells[7 - row1][col1];

                            // update the tile
                            tile.setPieceImageBish(bish_icon(bish.getColor()));
                            return;
                        }

                        // if the object already exists in the chessboard
                        if (!can_create(etype)) { // MAY UPDATE WITH A JUnit Test
                            // set up the buttons
                            buttonPanel.add(tryAgain);
                            buttonPanel.add(closeGame);
                            // initialize, set up, and display the pop-up frame
                            messageLabel.setText("Chess Piece already exists on the board, try another piece");
                            messageLabel.setHorizontalAlignment(SwingConstants.CENTER);
                            buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

                            // initialize, set up, and display the pop-up frame
                            pop_up_frame(popUpFrame, messageLabel, buttonPanel);
                            return;
                        }

                        // if the tile is occupied by another object
                        if (!not_occupied(eco, r)) { // MAY UPDATE WITH A JUnit Test
                            // set up the buttons
                            buttonPanel.add(tryAgain);
                            buttonPanel.add(closeGame);
                            // initialize, set up, and display the pop-up frame
                            messageLabel.setText("The tile is in use, try another tile");
                            messageLabel.setHorizontalAlignment(SwingConstants.CENTER);
                            buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

                            // initialize, set up, and display the pop-up frame
                            pop_up_frame(popUpFrame, messageLabel, buttonPanel);
                        }
                    }

                    // catch any index out of bounds errors
                    catch (ArrayIndexOutOfBoundsException ex) {
                        System.err.println("Array index out of bounds: " + ex.getMessage());
                    }
                }

                // otherwise it is  a  Figure object
                else {
                    // try
                    try {

                        // initialize a boolean to make sure that messages are not mixed

                        // create the Figure object
                        piece = Figure.create_chess_piece(etype, ecolor, eco, r);

                        // if the new  object does not exist in the collection and can be created
                        if (can_create(etype) && not_occupied(eco, r)) { // MAY UPDATE WITH A JUnit Test

                            // initialize and set up the success message
                            messageLabel.setText("You created the following chess piece: " + etype + " " + ecolor + " " + eco + ", " + r);
                            messageLabel.setHorizontalAlignment(SwingConstants.CENTER);

                            // set up the panel with the buttons
                            buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
                            buttonPanel.add(createNew);
                            buttonPanel.add(closeGame);

                            // initialize, set up, and display the pop-up frame
                            popUpFrame.getContentPane().setLayout(new BorderLayout());
                            popUpFrame.getContentPane().add(messageLabel, BorderLayout.NORTH);
                            popUpFrame.getContentPane().add(buttonPanel, BorderLayout.SOUTH);
                            popUpFrame.setSize(400, 100);
                            popUpFrame.setLocationRelativeTo(null);
                            popUpFrame.setVisible(true);

                            // add the created Figure object to the collection of pieces
                            pieces.add(piece);

                            System.out.println("The following object was created and added to the ArrayList: " + piece); // FOR TERMINAL USE

                            // set up the vars for updating the tile
                            int row1 = Integer.parseInt(selectedPieceRow) - 1;
                            int col1 = eco.ordinal();

                            // get the tile to place the icon for the Figure object
                            Tile tile = boardCells[7 - row1][col1];

                            // update the tile
                            tile.setPieceImage(icon(piece, piece.getColor()));

                            return;
                        }

                        // if the object already exists in the chessboard
                        if (!can_create(etype)) { // MAY UPDATE WITH A JUnit Test
                            // set up the buttons
                            buttonPanel.add(tryAgain);
                            buttonPanel.add(closeGame);
                            // initialize, set up, and display the pop-up frame
                            messageLabel.setText("Chess Piece already exists on the board, try another piece");
                            messageLabel.setHorizontalAlignment(SwingConstants.CENTER);
                            buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

                            // initialize, set up, and display the pop-up frame
                            pop_up_frame(popUpFrame, messageLabel, buttonPanel);
                            return;
                        }

                        // if the tile is occupied by another object
                        if (!not_occupied(eco, r)) { // MAY UPDATE WITH A JUnit Test
                            // set up the buttons
                            buttonPanel.add(tryAgain);
                            buttonPanel.add(closeGame);
                            // initialize, set up, and display the pop-up frame
                            messageLabel.setText("The tile is in use, try another tile");
                            messageLabel.setHorizontalAlignment(SwingConstants.CENTER);
                            buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

                            // initialize, set up, and display the pop-up frame
                            pop_up_frame(popUpFrame, messageLabel, buttonPanel);
                        }
                    }

                    // catch any index out of bounds errors
                    catch (ArrayIndexOutOfBoundsException ex) {
                        System.err.println("Array index out of bounds: " + ex.getMessage());
                    }
                }
            }

            // otherwise, the user input is invalid
            else {
                // set up the buttons
                buttonPanel.add(tryAgain);
                buttonPanel.add(closeGame);
                // initialize, set up, and display the pop-up frame
                messageLabel.setText("Invalid input, please create a valid chess piece");
                messageLabel.setHorizontalAlignment(SwingConstants.CENTER);
                buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

                // initialize, set up, and display the pop-up frame
                pop_up_frame(popUpFrame, messageLabel, buttonPanel);

            }
        });

        // add the 'Move piece' button to the buttons panel
        buttonsPanel.add(Box.createVerticalStrut(10));
        buttonsPanel.add(moveButton);

        // initialize an action listener to move the Figure objects on the chessboard
        moveButton.addActionListener(e -> {

            // initialize a new pop-up Frame
            JFrame new_coord = new JFrame("New Coordinates");
            JPanel coordPanel = new JPanel();

            // initialize label, text field, and a 'Submit' button
            JLabel coordLabel = new JLabel("Enter coordinates (e.g., A1): ");
            JTextField coordTextField = new JTextField(5);
            JButton submitButton = new JButton("Submit");

            // add the label, text field, and button to the pop-up frame
            coordPanel.add(coordLabel);
            coordPanel.add(coordTextField);
            coordPanel.add(submitButton);
            new_coord.add(coordPanel);

            // set up the Frame
            new_coord.setSize(500, 100);
            new_coord.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            new_coord.setLocationRelativeTo(null);
            new_coord.setVisible(true);

            // initialize an action listener to change the positions of the Figure objects
            submitButton.addActionListener(e1 -> {

                // initialize a new pop up frame, button panel, and an 'Exit Game' button
                JFrame popUpFrame = new JFrame("Notice");
                JPanel messagePanel = new JPanel();
                JPanel buttonPanel = new JPanel();
                JButton closeGame = new JButton("Exit Game");
                JButton tryAgain = new JButton("Try Again");
                JLabel outOfBounds = new JLabel("Input is out of bounds of the chessboard");

                // initialize an action listener for the 'Exit Game' button
                closeGame.addActionListener(a -> System.exit(0));

                // initialize an action listener for the 'Try Again' button
                tryAgain.addActionListener(n -> {
                    // close the frame
                    popUpFrame.dispose();
                });

                // initialize vars to hold the new coordinates
                String x;
                int y;

                // remove any unwanted symbols and spaces from the user input
                String coordinates = coordTextField.getText().replaceAll("[,\\s/]", "");

                // if the user input is valid (length 2)
                if (coordinates.length() == 2) { // MAY UPDATE WITH A JUnit Test
                    // try
                    try {
                        // split the user input
                        String[] coord = coordinates.split("");
                        // update the Strings to their appropriate values
                        x = coord[0].toUpperCase();
                        y = Integer.parseInt(coord[1]);

                        // traverse the collection of Figure objects
                        for (Object curr : pieces) {
                            // get the objects attributes
                            enums.chess_piece_type type;
                            enums.chess_piece_color color;
                            enums.chess_piece_columns col12;
                            int row12;

                            boolean test = false; // ADDED

                            // if the Object is an instance of a Figure Object
                            if (curr instanceof Figure piece) {
                                type = piece.getType();
                                color = piece.getColor();
                                col12 = piece.getColumn();
                                row12 = piece.getRow();

                                System.out.println("Current piece trying to move: " + piece); // FOR TERMINAL USE

                                // initialize labels
                                JLabel wasMovable = new JLabel(type + " " + color + " can move from: " + col12 + ", " + row12 + " to " + x + ", " + y);
                                JLabel notMovable = new JLabel(type + " " + color + " cannot move from: " + col12 + ", " + row12 + " to " + x + ", " + y);
                                JLabel messageLabel = new JLabel("You tried to move " + type + " " + color + " " + col12 + " " + row12 + " to: " + x + y + " but the location was occupied.");
                                // set up, and display the pop-up frame
                                wasMovable.setHorizontalAlignment(SwingConstants.CENTER);
                                notMovable.setHorizontalAlignment(SwingConstants.CENTER);
                                messageLabel.setHorizontalAlignment(SwingConstants.CENTER);

                                // initialize a boolean to check if the user input is valid for the column # UPDATE
                                boolean validX = false;

                                // for loop to traverse the enums for columns
                                for (enums.chess_piece_columns column : enums.chess_piece_columns.values()) {
                                    // if the user input is a valid column
                                    if (column.name().equals(x)) {
                                        validX = true;
                                        break;
                                    }
                                }

                                // if the user input is valid
                                if (validX && y > 0 && y < 9) {
                                    // initialize the new coordinates as usable variables
                                    enums.chess_piece_columns newColumn = enums.chess_piece_columns.valueOf(x);

                                    // if the piece can move to the new location
                                    if (piece.moveTo(newColumn, y)) {
                                        // otherwise the tile is not occupied
                                        if (!isTileOccupied(newColumn, y, pieces)) {

                                            // add the appropriate label to the panel
                                            messagePanel.add(wasMovable);

                                            // initialize the vars for updating the tile
                                            int oldRow = row12 - 1;
                                            int oldCol = col12.ordinal();

                                            // update the Figure objects attributes
                                            piece.setColumn(newColumn);
                                            piece.setRow(y);

                                            System.out.print("Piece has been moved: " + piece); // FOR TERMINAL USE


                                            // get the old tile and the new tile
                                            Tile tile = boardCells[7 - oldRow][oldCol];
                                            Tile newTile = boardCells[8 - y][newColumn.ordinal()];

                                            // reset the old tile and update the new tile icon
                                            tile.hidePieceImage();
                                            newTile.setPieceImage(icon(piece, piece.getColor()));
                                            continue;
                                        }

                                        // else if the tile is occupied
                                        else if (isTileOccupied(newColumn, y, pieces) && !test) {
                                            System.out.print("Piece could not be  due to tile being in use: " + piece); // FOR TERMINAL USE
                                            // set the appropriate label
                                            messageLabel.setHorizontalAlignment(SwingConstants.CENTER);
                                            messagePanel.add(messageLabel);
                                            continue;
                                        }
                                    }

                                    // otherwise the piece is not movable
                                    else {
                                        System.out.print("Piece could not be moved: " + piece); // FOR TERMINAL USE
                                        // set the appropriate label
                                        messagePanel.add(notMovable);
                                        continue;
                                    }
                                }
                            }

                            // if the Object is an instance of a Figure Object
                            if (curr instanceof Bishop bish) {
                                type = bish.getType();
                                color = bish.getColor();
                                col12 = bish.getColumn();
                                row12 = bish.getRow();

                                System.out.println("Current piece trying to move: " + bish); // FOR TERMINAL USE

                                // initialize labels
                                JLabel wasMovable = new JLabel(type + " " + color + " can move from: " + col12 + ", " + row12 + " to " + x + ", " + y);
                                JLabel notMovable = new JLabel(type + " " + color + " cannot move from: " + col12 + ", " + row12 + " to " + x + ", " + y);
                                JLabel messageLabel = new JLabel("You tried to move " + type + " " + color + " " + col12 + " " + row12 + " to: " + x + y + " but the location was occupied.");
                                // set up, and display the pop-up frame
                                messageLabel.setHorizontalAlignment(SwingConstants.CENTER);
                                wasMovable.setHorizontalAlignment(SwingConstants.CENTER);
                                notMovable.setHorizontalAlignment(SwingConstants.CENTER);

                                // initialize a boolean to check if the user input is valid for the column # UPDATE
                                boolean validX = false;

                                // for loop to traverse the enums for columns
                                for (enums.chess_piece_columns column : enums.chess_piece_columns.values()) {
                                    // if the user input is a valid column
                                    if (column.name().equals(x)) {
                                        validX = true;
                                        break;
                                    }
                                }

                                // if the user input is valid
                                if (validX && y > 0 && y < 9) {
                                    // initialize the new coordinates as usable variables
                                    enums.chess_piece_columns newColumn = enums.chess_piece_columns.valueOf(x);

                                    // if the piece can move to the new location
                                    if (bish.moveToBishop(col12, row12, newColumn, y)) {
                                        // otherwise the tile is not occupied
                                        if (!isTileOccupied(newColumn, y, pieces)) {

                                            // add the appropriate label to the panel
                                            wasMovable.setHorizontalAlignment(SwingConstants.CENTER);
                                            messagePanel.add(wasMovable);

                                            // initialize the vars for updating the tile
                                            int oldRow = row12 - 1;
                                            int oldCol = col12.ordinal();

                                            // update the Figure objects attributes
                                            bish.setColumn(newColumn);
                                            bish.setRow(y);

                                            System.out.print("Piece has been moved: " + bish); // FOR TERMINAL USE


                                            // get the old tile and the new tile
                                            Tile tile = boardCells[7 - oldRow][oldCol];
                                            Tile newTile = boardCells[8 - y][newColumn.ordinal()];

                                            // reset the old tile and update the new tile icon
                                            tile.hidePieceImage();
                                            newTile.setPieceImageBish(bish_icon(bish.getColor()));
                                        }

                                        // else if the tile is occupied
                                        else if (isTileOccupied(newColumn, y, pieces)) {
                                            System.out.print("Piece could not be  due to tile being in use: " + piece); // FOR TERMINAL USE
                                            // set the appropriate label
                                            messageLabel.setHorizontalAlignment(SwingConstants.CENTER);
                                            messagePanel.add(messageLabel);
                                        }
                                    }

                                    // otherwise the piece is not movable
                                    else {
                                        System.out.print("Piece could not be moved: " + piece); // FOR TERMINAL USE
                                        // set the appropriate label
                                        notMovable.setHorizontalAlignment(SwingConstants.CENTER);
                                        messagePanel.add(notMovable);
                                    }
                                }
                            }
                        }
                        // set up the buttons
                        buttonPanel.add(tryAgain);
                        buttonPanel.add(closeGame);
                        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
                        // set the appropriate label
                        messagePanel.setLayout(new FlowLayout(FlowLayout.CENTER));
                        // initialize, set up, and display the pop-up frame
                        popUpFrame.getContentPane().setLayout(new BorderLayout());
                        popUpFrame.getContentPane().add(messagePanel, BorderLayout.CENTER);
                        popUpFrame.getContentPane().add(buttonPanel, BorderLayout.NORTH);
                        popUpFrame.setSize(500, 200);
                        popUpFrame.setLocationRelativeTo(null);
                        popUpFrame.setVisible(true);
                    }
                    catch (Exception out) {
// set up the buttons
                        messagePanel.add(tryAgain);
                        messagePanel.add(closeGame);

                        // set the appropriate label
                        JLabel error = new JLabel("Invalid Input");

                        error.setHorizontalAlignment(SwingConstants.CENTER);
                        messagePanel.setLayout(new FlowLayout(FlowLayout.CENTER));
                        // initialize, set up, and display the pop-up frame
                        pop_up_frame(popUpFrame, error, messagePanel);
                    }
                }
                // otherwise the input is out of bounds/invalid
                else {
                    // set up the buttons
                    messagePanel.add(tryAgain);
                    messagePanel.add(closeGame);

                    // set the appropriate label
                    outOfBounds.setHorizontalAlignment(SwingConstants.CENTER);
                    messagePanel.setLayout(new FlowLayout(FlowLayout.CENTER));
                    // initialize, set up, and display the pop-up frame
                    pop_up_frame(popUpFrame, outOfBounds, messagePanel);
                }


            });
        });
        // return the button panel
        return buttonsPanel;
    }

    private static void pop_up_frame(JFrame popUpFrame, JLabel messageLabel, JPanel buttonPanel) {

        // initialize and set up a pop up frame
        popUpFrame.getContentPane().setLayout(new BorderLayout());
        popUpFrame.getContentPane().add(messageLabel, BorderLayout.NORTH);
        popUpFrame.getContentPane().add(buttonPanel, BorderLayout.CENTER);
        popUpFrame.setSize(500, 100);
        popUpFrame.setLocationRelativeTo(null);
        popUpFrame.setVisible(true);
    }

    /**
     * Method to check if a tile is occupied by a chess piece.
     * @return TRUE is tile is occupied FALSE otherwise
     * @param column the pieces col value in the columns enum
     * @param row an integer value for the row
     * @param currentPiece the current chesspiece that is being checked
     */
    private static boolean isTileOccupied(enums.chess_piece_columns column, int row, ArrayList<Object> pieces) {

        // Loop through pieces to check for occupancy
        for (Object piece : pieces) {
            // Check if the piece is of a different type and occupies the same tile (excluding currentPiece)
            if (piece instanceof Figure && ((Figure) piece).getColumn() == column && ((Figure) piece).getRow() == row) {
                return true;
            } else if (piece instanceof Bishop && ((Bishop) piece).getColumn() == column && ((Bishop) piece).getRow() == row) {
                return true;
            }
        }

        // Tile is not occupied
        return false;
    }

    /**
     * Method to get the icon for a chess piece.
     * @param piece the piece whos image we want to get
     * @param color part of the color enum for pieces
     * @return ImageIcon the corresponding piece image
     */
    public static ImageIcon icon(Figure piece, enums.chess_piece_color color) {

        // get the type of Figure object
        enums.chess_piece_type type = piece.getType();

        // if the color of the Figure object is black
        if(color.equals(enums.chess_piece_color.BLACK)) {
            // find and return the appropriate icon
            return getImageIcon(type, king, knight, pawn, bishop, queen, rook);
        }

        // if the color of the Figure object is white
        else {
            // find and return the appropriate icon
            return getImageIcon(type, white_king, white_knight, white_pawn, white_bishop, white_queen, white_rook);
        }
    }

    /**
     * Returns Image icon depending on what type is needed.
     * @param type chess piece type whos image we need
     * @param king ImageIcon
     * @param knight ImageIcon
     * @param pawn ImageIcon
     * @param bishop ImageIcon
     * @param queen ImageIcon
     * @param rook ImageIcon
     * @return an ImageIcon for the specified piece
     */
    private static ImageIcon getImageIcon(chess_piece_type type, ImageIcon king, ImageIcon knight, ImageIcon pawn, ImageIcon bishop, ImageIcon queen, ImageIcon rook) {

        // return the appropriate icon
        return switch (type) {
            case KING -> king;
            case KNIGHT -> knight;
            case PAWN -> pawn;
            case BISHOP -> bishop;
            case QUEEN -> queen;
            case ROOK -> rook;
        };
    }

    /**
     * Method to get the icon for a Bishop chess piece.
     * @param color the pieces color from the colors Enum
     * @return ImageIcon for the bishop piece
     */
    public static ImageIcon bish_icon(enums.chess_piece_color color) {

        // if the color is black return the black icon
        if (color.equals(enums.chess_piece_color.BLACK)) {
            return bishop;
        }

        // otherwise, return the white icon
        return white_bishop;

    }

    /**
     * Main method for initializing the chessboard
     * @param args
    */
    public static void main(String[] args) {

        // call in the chessboard
        new chessboard();
    }
}

/**
 *  Class to represent a tile on the chessboard.
 */
class Tile extends JPanel {

    // initialize a label
    private final JLabel pieceLabel;

    /**
     * Constructor to initialize the tile with a specific color.
     * @param color
     */
    public Tile(Color color) {

        // set the background color fo the tile
        setBackground(color);
        // initialize a new label
        pieceLabel = new JLabel();
        // set up the grid for the tile
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        // add the label
        add(pieceLabel, gbc);
    }

    /**
     * Method to set the image of the chess piece on the tile.
     * @param icon the image that will be set for the piece on the board
     */
    public void setPieceImage(ImageIcon icon) {

        // set the icon of the tile
        pieceLabel.setIcon(icon);
        // update the tile
        repaint();
    }

    /**
     * Method to set the image of the Bishop chess piece on the tile.
     * @param bish_icon the image that will be set for the piece on the board
     */
    public void setPieceImageBish(ImageIcon bish_icon) {

        // set the icon of the tile
        pieceLabel.setIcon(bish_icon);
        // update the tile
        repaint();
    }

      /**
     *  Method to hide the image of the chess piece on the tile.
     */
    public void hidePieceImage() {
        // remove the icon of the tile
        pieceLabel.setIcon(null);
        repaint();
    }

    /**
     * Method to get the preferred size of the tile.
     */
    @Override
    public Dimension getPreferredSize() {

        // initialize the dimension of the component and return it
        Dimension size = super.getPreferredSize();
        int maxSize = Math.max(size.width, size.height);
        return new Dimension(maxSize, maxSize);
    }
}