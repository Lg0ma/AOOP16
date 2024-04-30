import javax.swing.*; 
import enums.*;
import chess_pieces.*;
import java.awt.*; 
import java.awt.event.ActionEvent; 
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * This class represents a chessboard which extends JFrame.
 */
public class chessboard extends JFrame {

    // ArrayList collection that holds the Figure objects
    /**
     * An ArrayList that holds the chess pieces on the board.
     */
    static ArrayList<Object> pieces = new ArrayList<Object>(); 

    // static vars for creating a Figure object (or Bishop)
    private static Figure piece;
    private static Bishop bish;
    private static String selectedPieceType = "";
    private static String selectedPieceColor = "";
    private static String selectedPieceRow = "";
    private static String selectedPieceCol = "";

    // counter to count the number of Figure objects on the chessboard
    /**
     * A counter to keep track of the number of pieces on the board.
     */
    private static int count = 0;

    // icons 
    static ImageIcon pawn = new ImageIcon("C:\\Users\\luisg\\AOOP16\\Lab_6\\art\\BP.gif");
    static ImageIcon knight = new ImageIcon("C:\\Users\\luisg\\AOOP16\\Lab_6\\art\\BN.gif");
    static ImageIcon rook = new ImageIcon("C:\\Users\\luisg\\AOOP16\\Lab_6\\art\\BR.gif");
    static ImageIcon bishop = new ImageIcon("C:\\Users\\luisg\\AOOP16\\Lab_6\\art\\BB.gif");
    static ImageIcon queen = new ImageIcon("C:\\Users\\luisg\\AOOP16\\Lab_6\\art\\BQ.gif");
    static ImageIcon king = new ImageIcon("C:\\Users\\luisg\\AOOP16\\Lab_6\\art\\BK.gif");
    static ImageIcon white_pawn = new ImageIcon("C:\\Users\\luisg\\AOOP16\\Lab_6\\art\\WP.gif");
    static ImageIcon white_knight = new ImageIcon("C:\\Users\\luisg\\AOOP16\\Lab_6\\art\\WN.gif");
    static ImageIcon white_rook = new ImageIcon("C:\\Users\\luisg\\AOOP16\\Lab_6\\art\\WR.gif");
    static ImageIcon white_bishop = new ImageIcon("C:\\Users\\luisg\\AOOP16\\Lab_6\\art\\WB.gif");
    static ImageIcon white_queen = new ImageIcon("C:\\Users\\luisg\\AOOP16\\Lab_6\\art\\WQ.gif");
    static ImageIcon white_king = new ImageIcon("C:\\Users\\luisg\\AOOP16\\Lab_6\\art\\WK.gif");

    // initialize the panels
    static JPanel panel = new JPanel(); 
    static JPanel sidePanel = sidePanel();
    JPanel boardPanel; 
    static Tile[][] boardCells;
    JPanel buttonPanel;

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
    private static JPanel sidePanel() {   

        // initialize a panel for buttons
        /**
         * A JPanel that holds the buttons for the side panel.
         */
        JPanel buttonsPanel = new JPanel();
        BoxLayout buttonLayout = new BoxLayout(buttonsPanel, BoxLayout.Y_AXIS);
        buttonsPanel.setLayout(buttonLayout);

        // initialize the different options to select from the buttons
        String [] chessPieces = {"Pawn", "Rook", "Knight", "Bishop", "Queen", "King"};
        String [] row = {"-","1", "2", "3", "4", "5", "6", "7", "8"};
        String [] col = {"-","A", "B", "C", "D", "E", "F", "G", "H"};
        String [] colors = {"-", "White", "Black"};

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
        JComboBox colDropdown = new JComboBox<>(col);
        JComboBox rowDropdown = new JComboBox<>(row);
        JComboBox colorDropdown = new JComboBox<>(colors);

        // set up the drop down menu for the column coordinate
        colDropdown.setMaximumSize(colDropdown.getPreferredSize());
        colDropdown.setAlignmentX(Component.CENTER_ALIGNMENT);

        // initialize an action listener for the column dropdown menu
        colDropdown.addActionListener(new ActionListener() {
            /**
             * This method is called when an action is performed on the column dropdown menu.
             * It sets the selected column for the piece.
             * @param e The action event.
             */
            @Override
            public void actionPerformed(ActionEvent e) {

                // get the selected item from the dropdown menu
                JComboBox<String> comboBox = (JComboBox<String>) e.getSource();
                selectedPieceCol = (String) comboBox.getSelectedItem();
            }
        });

        // set up the drop down menu for the row coordinate
        rowDropdown.setMaximumSize(rowDropdown.getPreferredSize());
        rowDropdown.setAlignmentX(Component.CENTER_ALIGNMENT);

        // initialize an action listener for the row dropdown menu
        rowDropdown.addActionListener(new ActionListener() {
            /**
             * This method is called when an action is performed on the row dropdown menu.
             * It sets the selected row for the piece.
             * @param e The action event.
             */
            @Override
            public void actionPerformed(ActionEvent e) {

                // get the selected item from the dropdown menu
                JComboBox<String> comboBox = (JComboBox<String>) e.getSource();
                selectedPieceRow = (String) comboBox.getSelectedItem();
            }
        });

        // set up the dropdown menu for the color
        colorDropdown.setAlignmentX(Component.CENTER_ALIGNMENT);
        colorDropdown.setMaximumSize(colorDropdown.getPreferredSize());

        // initialize an action listener for the color dropdown menu
        colorDropdown.addActionListener(new ActionListener() {
            /**
             * This method is called when an action is performed on the color dropdown menu.
             * It sets the selected color for the piece.
             * @param e The action event.
             */
            @Override
            public void actionPerformed(ActionEvent e) {

                // get the selected item from the dropdown menu
                JComboBox<String> comboBox = (JComboBox<String>) e.getSource();
                selectedPieceColor = (String) comboBox.getSelectedItem();
            }
        });


                // initialize buttons for creating a Figure object and moving existing objects in the chessboard
        /**
         * A button for creating a new chess piece.
         */
        JButton createButton = new JButton("Create piece");
        /**
         * A button for moving an existing chess piece.
         */
        JButton moveButton = new JButton("Move piece");
        /**
         * A group of radio buttons for selecting a type of chess piece.
         */
        ButtonGroup buttonPieces = new ButtonGroup();

        // add radio buttons for selecting a type of chess piece to the button panel
        for(String type: chessPieces) {
            /**
             * A radio button for selecting a type of chess piece.
             */
            JRadioButton buttonType = new JRadioButton(type);
            buttonType.setAlignmentX(Component.CENTER_ALIGNMENT);
            buttonPieces.add(buttonType);
            buttonsPanel.add(buttonType);

            // initialize an action listener for the type of chess piece to be created
            buttonType.addActionListener(new ActionListener() {
                /**
                 * This method is called when an action is performed on the type radio button.
                 * It sets the selected type for the piece.
                 * @param e The action event.
                 */
                @Override
                public void actionPerformed(ActionEvent e) {
                    
                    // set up the var
                    selectedPieceType = type;
                }
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
        /**
         * A panel for the dropdown menus for the column and row.
         */
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
        createButton.addActionListener(new ActionListener() {
            /**
             * This method is called when an action is performed on the create button.
             * It creates a new chess piece based on the selected options.
             * @param e The action event.
             */
            @Override
            public void actionPerformed(ActionEvent e) {

                // set up the pop up window and its panels, messages, and buttons
                JFrame popUpFrame = new JFrame("Created a Chess Piece");
                JLabel messageLabel = new JLabel();
                JPanel buttonPanel = new JPanel();
                JButton closeGame = new JButton("Exit Game");
                JButton createNew = new JButton("Create New Piece");

                // if the required inputs are selected
                if (selectedPieceType != "" && selectedPieceColor != "" && selectedPieceCol != "" && selectedPieceRow != "") { // MAY UPDATE WITH A JUnit Test
                    // if the selected type is a Bishop
                    if (selectedPieceType == "Bishop") {
                        // try
                        try {
                            // initialize vars that hold the inputs for creating a Figure object
                            enums.chess_piece_type etype = enums.chess_piece_type.valueOf(selectedPieceType.toUpperCase());
                            enums.chess_piece_color ecolor = enums.chess_piece_color.valueOf(selectedPieceColor.toUpperCase());
                            enums.chess_piece_columns eco = enums.chess_piece_columns.valueOf(selectedPieceCol);
                            int r = Integer.parseInt(selectedPieceRow);

                            // create the Bishop object
                            bish = new Bishop(etype, ecolor, eco, r);

                            // initialize booleans to check if the Figure can be created
                            /**
                             * A boolean to check if a new chess piece can be created.
                             */
                            boolean canCreate = true;
                            /**
                             * A boolean to check if a tile is occupied by a chess piece.
                             */
                            boolean occupied = false;

                            // for loop to traverse the existing valid Figure objects
                            for (Object p : pieces) {
                                // if the object is an instance of a Bishop object
                                if (p instanceof Bishop) { 
                                    // set the boolean as false
                                    canCreate = false;
                                    break; 

                                }
                                // otherwise the object is an instance of a Figure object
                                else {
                                    // cast Figure to p
                                    Figure temp = (Figure)p;
                                    // if the coordinates are occupied, set the boolean to true
                                    if (bish.getColumn() == temp.getColumn() && bish.getRow() == temp.getRow()) { 
                                        occupied = true;
                                    }
                                }
                            }

                            // if the new Bishop object does not exist in the collection and can be created
                            if (canCreate && count < 6 && !occupied) { 

                                // initialize an action listener for the 'Exit Game' button
                                closeGame.addActionListener(a -> {
                                    // close the program
                                    System.exit(0);
                                });

                                // initialize an action listener for the 'Create New Piece' buttpm
                                createNew.addActionListener(n -> {
                                    // close the frame
                                    popUpFrame.dispose();
                                });

                                // initialize and set up the success message
                                messageLabel.setText("You created the following chess piece: " + selectedPieceType + " " + selectedPieceColor + " " + selectedPieceCol + ", " + selectedPieceRow);
                                messageLabel.setHorizontalAlignment(SwingConstants.CENTER);

                                // set up the panel with the buttons
                                buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
                                buttonPanel.add(createNew);
                                buttonPanel.add(closeGame);

                                // initialize, set up, and display the pop up frame
                                popUpFrame.getContentPane().setLayout(new BorderLayout());
                                popUpFrame.getContentPane().add(messageLabel, BorderLayout.NORTH);
                                popUpFrame.getContentPane().add(buttonPanel, BorderLayout.CENTER);
                                popUpFrame.setSize(400, 100);
                                popUpFrame.setLocationRelativeTo(null);
                                popUpFrame.setVisible(true);

                                // add the created Figure object to the collection of pieces
                                pieces.add(bish);

                                System.out.println("Piece added: " + bish); // FOR TERMINAL USE

                                // set up the vars for updating the tile
                                int row = Integer.parseInt(selectedPieceRow) - 1;
                                int col = eco.ordinal();

                                System.out.println(col + " " + row); // FOR TERMINAL USE



                                // get the tile to place the icon for the Figure object
                                Tile tile = boardCells[7 - row][col]; // Get the tile on the chessboard where the icon for the chess piece will be placed.

                                // update the tile
                                tile.setPieceImageBish(bish_icon(bish.getColor())); // Update the tile with the icon for the Bishop chess piece.

                                // increment through the counter
                                count += 1; // Increment the counter to keep track of the number of chess pieces on the board.
                            }

                            // if the Figure object already exists in the chessboard
                            if (canCreate == false) { 
                                // initialize, set up, and display the pop up frame
                                messageLabel.setText("Chess Piece already exists on the board, try another piece");
                                messageLabel.setHorizontalAlignment(SwingConstants.CENTER);
                                popUpFrame.add(messageLabel);
                                popUpFrame.setSize(500, 100);
                                popUpFrame.setLocationRelativeTo(null);
                                popUpFrame.setVisible(true);
                                return;
                            }

                            // if the tile is occupied by another Figure object
                            if (occupied == true) { 
                                // initialize, set up, and display the pop up frame
                                messageLabel.setText("The tile is in use, try another tile");
                                messageLabel.setHorizontalAlignment(SwingConstants.CENTER);
                                popUpFrame.add(messageLabel);
                                popUpFrame.setSize(500, 100);
                                popUpFrame.setLocationRelativeTo(null);
                                popUpFrame.setVisible(true);
                                return;
                            }

                            // else if all chess pieces have been created
                            else if (count == 6) { 
                                // initialize, set up, and display the pop up frame
                                messageLabel.setText("All chess pieces have already been created");
                                popUpFrame.add(messageLabel);
                                popUpFrame.setSize(400, 100);
                                popUpFrame.setLocationRelativeTo(null);
                                popUpFrame.setVisible(true);
                                return;
                            }
                        }

                        // catch any index out of bounds erros
                        catch (ArrayIndexOutOfBoundsException ex) {
                            System.err.println("Array index out of bounds: " + ex.getMessage()); // Catch any index out of bounds errors.
                        }
                    }

                    // otherwise its a normal Figure object
                    else {
                        // try
                        try {
                            // initialize vars that hold the inputs for creating a Figure object
                            enums.chess_piece_type etype = enums.chess_piece_type.valueOf(selectedPieceType.toUpperCase());
                            enums.chess_piece_color ecolor = enums.chess_piece_color.valueOf(selectedPieceColor.toUpperCase());
                            enums.chess_piece_columns eco = enums.chess_piece_columns.valueOf(selectedPieceCol);
                            int r = Integer.parseInt(selectedPieceRow);

                            // create the Figure object
                            piece = chess_pieces.Figure.create_chess_piece(etype, ecolor, eco, r); // Create a new chess piece based on the selected options.

                            // initialize booleans to check if the Figure can be created
                            boolean canCreate = true;
                            boolean occupied = false;


                            // for loop to traverse the existing valid Figure objects
                            for (Object p : pieces) {
                                // if the object is an instance of a Figure object
                                if (p instanceof Figure) {
                                    // cast Figure to p
                                    Figure p_iece = (Figure) p;
                                    // if the Figure object already exists, set the boolean to false
                                    if (piece.getType() == p_iece.getType()) { 
                                        canCreate = false;
                                    }

                                    // if the coordinates are occupied, set the boolean to true
                                    if (piece.getColumn() == p_iece.getColumn() && piece.getRow() == p_iece.getRow()) { 
                                        occupied = true;
                                    }
                                }
                                // if the object is an instance of a Bishop object
                                if (p instanceof Bishop) {
                                    // cast Bishop to p
                                    Bishop p_iece = (Bishop) p;
                                    // if the coordinates are occupied, set the boolean to true
                                    if (piece.getColumn() == p_iece.getColumn() && piece.getRow() == p_iece.getRow()) { 
                                        occupied = true;
                                    }
                                }
                            }

                            // if the new Figure object does not exist in the collection and can be created
                            if (!pieces.contains(piece) && canCreate && count < 6 && occupied == false) { 

                                // initialize an action listener for the 'Exit Game' button
                                closeGame.addActionListener(a -> {
                                    // close the program
                                    System.exit(0);
                                });

                                // initialize an action listener for the 'Create New Piece' buttpm
                                createNew.addActionListener(n -> {
                                    // close the frame
                                    popUpFrame.dispose();
                                });

                                // initialize and set up the success message
                                messageLabel.setText("You created the following chess piece: " + selectedPieceType + " " + selectedPieceColor + " " + selectedPieceCol + ", " + selectedPieceRow);
                                messageLabel.setHorizontalAlignment(SwingConstants.CENTER);

                                // set up the panel with the buttons
                                buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
                                buttonPanel.add(createNew);
                                buttonPanel.add(closeGame);

                                // initialize, set up, and display the pop up frame
                                popUpFrame.getContentPane().setLayout(new BorderLayout());
                                popUpFrame.getContentPane().add(messageLabel, BorderLayout.NORTH);
                                popUpFrame.getContentPane().add(buttonPanel, BorderLayout.CENTER);
                                popUpFrame.setSize(400, 100);
                                popUpFrame.setLocationRelativeTo(null);
                                popUpFrame.setVisible(true);

                                // add the created Figure object to the collection of pieces
                                pieces.add(piece);
                            }


                                // Print a message to the console indicating that the piece has been added.
                                System.out.println("Piece added: " + piece); 
                                
                                // Set up the variables for updating the tile.
                                int row = Integer.parseInt(selectedPieceRow) - 1;
                                int col = eco.ordinal();

                                // Get the tile on the chessboard where the icon for the chess piece will be placed.
                                Tile tile = boardCells[7 - row][col];

                                // Update the tile with the icon for the chess piece.
                                tile.setPieceImage(icon(piece, piece.getColor()));

                                // Increment the counter to keep track of the number of chess pieces on the board.
                                count += 1;

                            // If the Figure object already exists in the chessboard, display a message to the user.
                            if (canCreate == false) { 
                                messageLabel.setText("Chess Piece already exists on the board, try another piece");
                                messageLabel.setHorizontalAlignment(SwingConstants.CENTER);
                                popUpFrame.add(messageLabel);
                                popUpFrame.setSize(500, 100);
                                popUpFrame.setLocationRelativeTo(null);
                                popUpFrame.setVisible(true);
                                return;
                            }

                            // If the tile is occupied by another Figure object, display a message to the user.
                            if (occupied == true) { 
                                messageLabel.setText("The tile is in use, try another tile");
                                messageLabel.setHorizontalAlignment(SwingConstants.CENTER);
                                popUpFrame.add(messageLabel);
                                popUpFrame.setSize(500, 100);
                                popUpFrame.setLocationRelativeTo(null);
                                popUpFrame.setVisible(true);
                                return;
                            }

                            // If all chess pieces have been created, display a message to the user.
                            else if (count == 6) { 
                                messageLabel.setText("All chess pieces have already been created");
                                popUpFrame.add(messageLabel);
                                popUpFrame.setSize(400, 100);
                                popUpFrame.setLocationRelativeTo(null);
                                popUpFrame.setVisible(true);
                                return;
                            }
                        }

                        // Catch any index out of bounds errors.
                        catch (ArrayIndexOutOfBoundsException ex) {
                            System.err.println("Array index out of bounds: " + ex.getMessage());
                        }
                    }
                }

                // If the user input is invalid, display a message to the user.
                else {
                    JFrame invalidInputFrame = new JFrame("Invalid Input");
                    JLabel invalidInputLabel = new JLabel("Invalid input, please create a valid chess piece");
                    invalidInputLabel.setHorizontalAlignment(SwingConstants.CENTER);
                    invalidInputFrame.add(invalidInputLabel);
                    invalidInputFrame.setSize(400, 100);
                    invalidInputFrame.setLocationRelativeTo(null);
                    invalidInputFrame.setVisible(true);
                }
            }
        });

        // Add a vertical strut to the buttons panel for spacing.
        buttonsPanel.add(Box.createVerticalStrut(10));
        // Add the 'Move piece' button to the buttons panel.
        buttonsPanel.add(moveButton);

        // Initialize an action listener to move the Figure objects on the chessboard.
        moveButton.addActionListener(new ActionListener() {
            /**
             * This method is called when an action is performed on the move button.
             * It opens a new frame for the user to enter the new coordinates for the piece to be moved.
             * @param e The action event.
             */
            @Override
            public void actionPerformed(ActionEvent e) {

                // Initialize a new pop up Frame for entering new coordinates.
                JFrame new_coord = new JFrame("New Coordinates");
                // Initialize a panel for the coordinate input components.
                JPanel coordPanel = new JPanel();

                // Initialize label, text field, and a 'Submit' button for entering new coordinates.
                JLabel coordLabel = new JLabel("Enter coordinates (e.g., A1): ");
                JTextField coordTextField = new JTextField(5); 
                JButton submitButton = new JButton("Submit");

                // Add the label, text field, and button to the pop up frame.
                coordPanel.add(coordLabel);
                coordPanel.add(coordTextField);
                coordPanel.add(submitButton);
                new_coord.add(coordPanel);

                // Set up the Frame.
                new_coord.setSize(400, 100);
                new_coord.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                new_coord.setLocationRelativeTo(null);
                new_coord.setVisible(true);

                // Initialize an action listener to change the positions of the Figure objects.
                submitButton.addActionListener(new ActionListener() {
                    /**
                     * This method is called when an action is performed on the submit button.
                     * It moves the selected chess piece to the entered coordinates.
                     * @param e The action event.
                     */
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        // Initialize a boolean to check if the user input is valid for the column.
                        boolean validX = false;


                        // Traverse the collection of Figure objects.
                        for (Object curr : pieces) {
                            // Get the objects attributes.
                            enums.chess_piece_type type = null;
                            enums.chess_piece_color color = null;
                            enums.chess_piece_columns col = null;
                            int row = 0;

                            // If the Object is an instance of a Figure Object.
                            if (curr instanceof Figure) {
                                piece = (Figure) curr;
                                type = piece.getType();
                                color = piece.getColor();
                                col = piece.getColumn();
                                row = piece.getRow();
                                System.out.println("Current piece in move : " + type + " " + col + " " + row); // FOR TERMINAL USE

                                // Initialize vars to hold the new coordinates.
                                String x = "";
                                int y = 0;

                                // Initialize a new pop up frame, button panel, and an 'Exit Game' button.
                                JFrame popUpFrame = new JFrame("New Chess Piece Location");
                                JPanel buttonPanel = new JPanel();
                                JButton closeGame = new JButton("Exit Game");

                                // Remove any unwanted symbols and spaces from the user input.
                                String coordinates = coordTextField.getText().replaceAll("[,\\s/]", "");

                                // Initialize an action listener for the 'Exit Game' button.
                                closeGame.addActionListener(a -> {
                                    System.exit(0);
                                });


                                // If the user input is valid (length 2).
                                if (coordinates.length() == 2) { 
                                    // Split the user input into an array of Strings.
                                    String [] coord = coordinates.split("");
                                    // Update the Strings to their appropriate values.
                                    x = coord[0].toUpperCase();
                                    y = Integer.parseInt(coord[1]);


                                    // For loop to traverse the enums for columns.
                                    for (enums.chess_piece_columns column : enums.chess_piece_columns.values()) {
                                        // If the user input is a valid column.
                                        if (column.name().equals(x)) {
                                            validX = true;
                                            break;
                                        }
                                    }

                                    // Initialize labels for different messages.
                                    JLabel wasMovable = new JLabel(type + " " + color + " can move from: " + col + ", " + row + " to " + x + ", " + y);
                                    JLabel notMovable = new JLabel(type + " " + color + " cannot move from: " + col + ", " + row  + " to " + x + ", " + y);
                                    JLabel messageLabel = new JLabel("You tried to move " + type + " " + color + " " + col + " " + row + " to: " + x + y + " but the location was occupied.");
                                    JLabel outOfBounds = new JLabel("Input is out of bounds of the chessboard");

                                    // If the user input is valid.
                                    if (validX && y > 0 && y < 9) {
                                        // Initialize the new coordinates as usable variables.
                                        enums.chess_piece_columns newColumn = enums.chess_piece_columns.valueOf(x);
                                        int newRow = y;

                                        // If the tile is occupied and the piece can move to that location.
                                        if (isTileOccupied(newColumn, newRow, curr) && piece.moveTo(newColumn, newRow)) {
                                            // Set the appropriate label.
                                            messageLabel.setHorizontalAlignment(SwingConstants.CENTER);
                                            buttonPanel.add(messageLabel);
                                        }

                                        // Else if the piece can move to the new location.
                                        else if (!isTileOccupied(newColumn, newRow, curr) && piece != null && piece.moveTo(newColumn, newRow)) {
                                            // Add the appropriate label to the panel.
                                            buttonPanel.add(wasMovable);

                                            // Initialize the vars for updating the tile.
                                            int oldRow = row - 1;
                                            int oldCol =  col.ordinal();

                                            // Print the new coordinates to the console for testing.
                                            System.out.println(newColumn + " " + newRow); 

                                            // Update the Figure objects attributes.
                                            piece.setColumn(newColumn);
                                            piece.setRow(newRow);

                                            // Get the old tile and the new tile.
                                            Tile tile = boardCells[6 - oldRow][oldCol];
                                            Tile newTile = boardCells[8 - newRow ][newColumn.ordinal()];

                                            // Reset the old tile and update the new tile icon.
                                            tile.hidePieceImage();
                                            newTile.setPieceImage(icon(piece, piece.getColor()));
                                        }
                                        
                                        // If the piece is not movable, set the appropriate label.
                                        else {
                                            buttonPanel.add(notMovable);
                                        }
                                    }
                                    
                                    // If the input is out of bounds/invalid, set the appropriate label.
                                    else {
                                        buttonPanel.add(outOfBounds);
                                    }
                                    
                                    // Set up, and display the pop up frame.
                                    messageLabel.setHorizontalAlignment(SwingConstants.CENTER);
                                    buttonPanel.add(closeGame);
                                    buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 5));
                                    popUpFrame.getContentPane().setLayout(new BoxLayout(popUpFrame.getContentPane(), BoxLayout.Y_AXIS));
                                    popUpFrame.getContentPane().add(Box.createVerticalStrut(10));
                                    popUpFrame.getContentPane().add(buttonPanel);
                                    
                                    popUpFrame.setSize(600, 100);
                                    popUpFrame.setLocationRelativeTo(null);
                                    popUpFrame.setVisible(true);
                                }
                            }
                            
                            // If the Object is an instance of a Figure Object.
                            if (curr instanceof Bishop) {
                                bish = (Bishop) curr;
                                type = bish.getType();
                                color = bish.getColor();
                                col = bish.getColumn();
                                row = bish.getRow();
                                System.out.println("Current piece in move : " + type + " " + col + " " + row); // FOR TERMINAL USE
                            
                                // Initialize vars to hold the new coordinates.
                                String x = "";
                                int y = 0;
                            
                                // Initialize a new pop up frame, button panel, and an 'Exit Game' button.
                                JFrame popUpFrame = new JFrame("New Chess Piece Location");
                                JPanel buttonPanel = new JPanel();
                                JButton closeGame = new JButton("Exit Game");
                            
                                // Remove any unwanted symbols and spaces from the user input.
                                String coordinates = coordTextField.getText().replaceAll("[,\\s/]", "");
                            
                                // Initialize an action listener for the 'Exit Game' button.
                                closeGame.addActionListener(a -> {
                                    System.exit(0);
                                });
                            
                                // If the user input is valid (length 2).
                                if (coordinates.length() == 2) { 
                                    // Split the user input.
                                    String [] coord = coordinates.split("");
                                    // Update the Strings to their appropriate values.
                                    x = coord[0].toUpperCase();
                                    y = Integer.parseInt(coord[1]);



                                    // For loop to traverse the enums for columns.
                                    for (enums.chess_piece_columns column : enums.chess_piece_columns.values()) {
                                        // If the user input is a valid column.
                                        if (column.name().equals(x)) {
                                            validX = true;
                                            break;
                                        }
                                    }

                                    // Initialize labels for different messages.
                                    JLabel wasMovable = new JLabel(type + " " + color + " can move from: " + col + ", " + row + " to " + x + ", " + y);
                                    JLabel notMovable = new JLabel(type + " " + color + " cannot move from: " + col + ", " + row  + " to " + x + ", " + y);
                                    JLabel messageLabel = new JLabel("You tried to move " + type + " " + color + " " + col + " " + row + " to: " + x + y + " but the location was occupied.");
                                    JLabel outOfBounds = new JLabel("Input is out of bounds of the chessboard");

                                    // If the user input is valid.
                                    if (validX && y > 0 && y < 9) {
                                        // Initialize the new coordinates as usable variables.
                                        enums.chess_piece_columns newColumn = enums.chess_piece_columns.valueOf(x);
                                        int newRow = y;

                                        // If the tile is occupied and the piece can move to that location.
                                        if (isTileOccupied(newColumn, newRow, curr) && bish.moveTo(newColumn, newRow)) {
                                            // Set the appropriate label.
                                            messageLabel.setHorizontalAlignment(SwingConstants.CENTER);
                                            buttonPanel.add(messageLabel);
                                        }

                                        // Else if the piece can move to the new location.
                                        else if (! isTileOccupied(newColumn, newRow, curr) && bish != null && bish.moveTo(newColumn, newRow)) {
                                            // Add the appropriate label to the panel.
                                            buttonPanel.add(wasMovable);

                                            // Initialize the vars for updating the tile.
                                            int oldRow = row - 1;
                                            int oldCol =  col.ordinal();

                                            System.out.println(newColumn + " " + newRow); // FOR TERMINAL TESTING

                                            // Update the Figure objects attributes.
                                            bish.setColumn(newColumn);
                                            bish.setRow(newRow);

                                            // Get the old tile and the new tile.
                                            Tile tile = boardCells[6 - oldRow][oldCol];
                                            Tile newTile = boardCells[8 - newRow ][newColumn.ordinal()];

                                            // Reset the old tile and update the new tile icon.
                                            tile.hidePieceImageBish();
                                            newTile.setPieceImageBish(bish_icon(bish.getColor()));
                                        }

                                        // Otherwise the piece is not movable.
                                        else {
                                            // Set the appropriate label.
                                            buttonPanel.add(notMovable);
                                        }
                                    }

                                    // If the input is out of bounds/invalid, set the appropriate label.
                                    else {
                                        buttonPanel.add(outOfBounds);
                                    }

                                    // Set up, and display the pop up frame.
                                    messageLabel.setHorizontalAlignment(SwingConstants.CENTER);
                                    buttonPanel.add(closeGame);
                                    buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 5));
                                    popUpFrame.getContentPane().setLayout(new BoxLayout(popUpFrame.getContentPane(), BoxLayout.Y_AXIS));
                                    popUpFrame.getContentPane().add(Box.createVerticalStrut(10));
                                    popUpFrame.getContentPane().add(buttonPanel);

                                    popUpFrame.setSize(600, 100);
                                    popUpFrame.setLocationRelativeTo(null);
                                    popUpFrame.setVisible(true);
                                }
                            }
                        }
                    }
                });
            }
        });
        // Return the button panel.
        return buttonsPanel;
    }

    /**
     * Method to check if a tile is occupied by a chess piece.
     * @return TRUE is tile is occupied FALSE otherwise
     * @param column the pieces col value in the columns enum
     * @param row an integer value for the row
     * @param currentPiece the current chesspiece that is being checked
     */ 
    private static boolean isTileOccupied(enums.chess_piece_columns column, int row, Object currentPiece) {

        // Cast currentPiece to the appropriate type based on the initial check.
        if (currentPiece instanceof Figure) {
            Figure curr_fig = (Figure) currentPiece;
        } else {
            Bishop curr_bish = (Bishop) currentPiece;
        }

        // Loop through pieces to check for occupancy.
        for (Object p : pieces) {
            // Check if the piece is of a different type and occupies the same tile.
            if (p instanceof Figure && p != currentPiece &&
                    ((Figure) p).getColumn() == column && ((Figure) p).getRow() == row) {
                return true;
            } else if (p instanceof Bishop && p != currentPiece &&
                    ((Bishop) p).getColumn() == column && ((Bishop) p).getRow() == row) {
                return true;
            }
        }

        // Tile is not occupied.
        return false;
    }

    /**
     * Method to get the icon for a chess piece.
     * @param piece the piece whos image we want to get
     * @param color part of the color enum for pieces
     * @return ImageIcon the corresponding piece image
     */
    public static ImageIcon icon(Figure piece, enums.chess_piece_color color) {
        
        // Get the type of Figure object.
        enums.chess_piece_type type = piece.getType();

        // If the color of the Figure object is black.
        if(color.equals(enums.chess_piece_color.BLACK)) {
            // Find and return the appropriate icon.
            switch (type) {
                case KING:
                    return king;
                case KNIGHT:
                    return knight;
                case PAWN:
                    return pawn;
                case BISHOP:
                    return bishop;
                case QUEEN:
                    return queen;
                case ROOK:
                    return rook;
                default:
                    throw new IllegalArgumentException("Invalid chess piece type");
            }
        }

                
        // If the color of the Figure object is white.
        else {
            // Find and return the appropriate icon.
            switch (type) {
                case KING:
                    return white_king;
                case KNIGHT:
                    return white_knight;
                case PAWN:
                    return white_pawn;
                case BISHOP:
                    return white_bishop;
                case QUEEN:
                    return white_queen;
                case ROOK:
                    return white_rook;
                default:
                    throw new IllegalArgumentException("Invalid chess piece type");
            }
        }
    }
    /**
     * Method to get the icon for a Bishop chess piece.
     * @param color the pieces color from the colors Enum
     * @return ImageIcon for the bishop piece
     */
    public static ImageIcon bish_icon(enums.chess_piece_color color) {
        // If the color of the Bishop object is black.
        if (color.equals(enums.chess_piece_color.BLACK)) {
            return bishop;
        }

        // If the color of the Bishop object is white.
        return white_bishop;
    }

    /**
     * Main method for initializing the chessboard
     * @param args
    */
    // Main method to start the chessboard.
    public static void main(String[] args) {
        new chessboard();
    }
}
/**
 *  Class to represent a tile on the chessboard.

 */
class Tile extends JPanel {
    
    // Label to display the chess piece.
    private JLabel pieceLabel; 

    /**
     * Constructor to initialize the tile with a specific color.
     * @param color
     */
    public Tile(Color color) {
        setBackground(color);
        pieceLabel = new JLabel();
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(pieceLabel, gbc);
    }

    /**
     * Method to set the image of the chess piece on the tile.
     * @param icon the image that will be set for the piece on the board
     */
    public void setPieceImage(ImageIcon icon) {
        pieceLabel.setIcon(icon);
        repaint(); 
    }
/**
 * Method to set the image of the Bishop chess piece on the tile.
 * @param bish_icon the image that will be set for the piece on the board
 */
    public void setPieceImageBish(ImageIcon bish_icon) {
        pieceLabel.setIcon(bish_icon);
        repaint();
    }

    /**
     *  Method to hide the image of the chess piece on the tile.
     */
    public void hidePieceImage() {
        pieceLabel.setIcon(null);
        repaint();
    }

    /**
     *  Method to hide the image of the Bishop chess piece on the tile.
     */
    public void hidePieceImageBish() {
        pieceLabel.setIcon(null);
        repaint();
    }

    /**
     * Method to get the preferred size of the tile.
     */
    @Override
    public Dimension getPreferredSize() { 
        Dimension size = super.getPreferredSize();
        int maxSize = Math.max(size.width, size.height);
        return new Dimension(maxSize, maxSize);
    }
}