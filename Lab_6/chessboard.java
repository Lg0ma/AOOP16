import javax.swing.*;
import chess_pieces.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Objects;

public class chessboard extends JFrame {
    
    // ArrayList collection that holds the Figure objects
    static ArrayList<Object> pieces = new ArrayList<Object>(); // CHANGED : FROM Figure TO Object
    
    // static vars for creating a Figure object (or Bishop)
    private static Figure piece;
    private static Bishop bish;
    private static String selectedPieceType = "";
    private static String selectedPieceColor = "";
    private static String selectedPieceRow = "";
    private static String selectedPieceCol = "";
    
    // icons
    static ImageIcon pawn = new ImageIcon("Users/ivan_armenta/Desktop/AOOP16/Lab_6/art/BP.gif");
    static ImageIcon knight = new ImageIcon("/Users/ivan_armenta/Desktop/AOOP16/Lab_6/art/BN.gif");
    static ImageIcon rook = new ImageIcon("/Users/ivan_armenta/Desktop/AOOP16/Lab_6/art/BR.gif");
    static ImageIcon bishop = new ImageIcon("/Users/ivan_armenta/Desktop/AOOP16/Lab_6/art/BB.gif");
    static ImageIcon queen = new ImageIcon("/Users/ivan_armenta/Desktop/AOOP16/Lab_6/art/BQ.gif");
    static ImageIcon king = new ImageIcon("/Users/ivan_armenta/Desktop/AOOP16/Lab_6/art/BK.gif");
    static ImageIcon white_pawn = new ImageIcon("/Users/ivan_armenta/Desktop/AOOP16/Lab_6/art/WP.gif");
    static ImageIcon white_knight = new ImageIcon("/Users/ivan_armenta/Desktop/AOOP16/Lab_6/art/WN.gif");
    static ImageIcon white_rook = new ImageIcon("/Users/ivan_armenta/Desktop/AOOP16/Lab_6/art/WR.gif");
    static ImageIcon white_bishop = new ImageIcon("/Users/ivan_armenta/Desktop/AOOP16/Lab_6/art/WB.gif");
    static ImageIcon white_queen = new ImageIcon("/Users/ivan_armenta/Desktop/AOOP16/Lab_6/art/WQ.gif");
    static ImageIcon white_king = new ImageIcon("/Users/ivan_armenta/Desktop/AOOP16/Lab_6/art/WK.gif");
    
    // initialize the panels
    static JPanel panel = new JPanel();
    static JPanel sidePanel = sidePanel();
    JPanel boardPanel;
    static Tile[][] boardCells;
    JPanel buttonPanel;

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

    private static Boolean can_create(enums.chess_piece_type type, enums.chess_piece_columns col, int row) {

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

    private static Boolean not_occupied(enums.chess_piece_type type, enums.chess_piece_columns col, int row) {

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

    private static JPanel sidePanel() {

        // initialize a panel for buttons
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
        colDropdown.addActionListener(new ActionListener() {
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
            @Override
            public void actionPerformed(ActionEvent e) {

                // get the selected item from the dropdown menu
                JComboBox<String> comboBox = (JComboBox<String>) e.getSource();
                selectedPieceColor = (String) comboBox.getSelectedItem();
            }
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
            buttonType.addActionListener(new ActionListener() {
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
            @Override
            public void actionPerformed(ActionEvent e) {

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

                // initialize an action listener for the 'Create New Piece' buttom
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
                            if (can_create(etype, eco, r) && not_occupied(etype, eco, r)) { // MAY UPDATE WITH A JUnit Test

                                // set up the buttons
                                buttonPanel.add(createNew);
                                buttonPanel.add(closeGame);

                                // initialize and set up the success message
                                messageLabel.setText("You created the following chess piece: " + etype + " " + ecolor + " " + eco + ", " + r);
                                messageLabel.setHorizontalAlignment(SwingConstants.CENTER);
                                buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

                                // initialize, set up, and display the pop-up frame
                                popUpFrame.getContentPane().setLayout(new BorderLayout());
                                popUpFrame.getContentPane().add(messageLabel, BorderLayout.NORTH);
                                popUpFrame.getContentPane().add(buttonPanel, BorderLayout.CENTER);
                                popUpFrame.setSize(500, 100);
                                popUpFrame.setLocationRelativeTo(null);
                                popUpFrame.setVisible(true);

                                // add the created Figure object to the collection of pieces
                                pieces.add(bish);

                                System.out.println("The following object was created and added to the ArrayList: " + bish); // FOR TERMINAL USE

                                // set up the vars for updating the tile
                                int row = Integer.parseInt(selectedPieceRow) - 1;
                                int col = eco.ordinal();

                                // get the tile to place the icon for the Figure object
                                Tile tile = boardCells[7 - row][col];

                                // update the tile
                                tile.setPieceImageBish(bish_icon(bish.getColor()));
                                return;
                            }

                            // if the object already exists in the chessboard
                            if (!can_create(etype, eco, r)) { // MAY UPDATE WITH A JUnit Test
                                // set up the buttons
                                buttonPanel.add(tryAgain);
                                buttonPanel.add(closeGame);
                                // initialize, set up, and display the pop-up frame
                                messageLabel.setText("Chess Piece already exists on the board, try another piece");
                                messageLabel.setHorizontalAlignment(SwingConstants.CENTER);
                                buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

                                // initialize, set up, and display the pop-up frame
                                popUpFrame.getContentPane().setLayout(new BorderLayout());
                                popUpFrame.getContentPane().add(messageLabel, BorderLayout.NORTH);
                                popUpFrame.getContentPane().add(buttonPanel, BorderLayout.CENTER);
                                popUpFrame.setSize(500, 100);
                                popUpFrame.setLocationRelativeTo(null);
                                popUpFrame.setVisible(true);
                                return;
                            }

                            // if the tile is occupied by another object
                            if (!not_occupied(etype, eco, r)) { // MAY UPDATE WITH A JUnit Test
                                // set up the buttons
                                buttonPanel.add(tryAgain);
                                buttonPanel.add(closeGame);
                                // initialize, set up, and display the pop-up frame
                                messageLabel.setText("The tile is in use, try another tile");
                                messageLabel.setHorizontalAlignment(SwingConstants.CENTER);
                                buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

                                // initialize, set up, and display the pop-up frame
                                popUpFrame.getContentPane().setLayout(new BorderLayout());
                                popUpFrame.getContentPane().add(messageLabel, BorderLayout.NORTH);
                                popUpFrame.getContentPane().add(buttonPanel, BorderLayout.CENTER);
                                popUpFrame.setSize(500, 100);
                                popUpFrame.setLocationRelativeTo(null);
                                popUpFrame.setVisible(true);
                            }
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
                            boolean verify = false;

                            // create the Figure object
                            piece = chess_pieces.Figure.create_chess_piece(etype, ecolor, eco, r);

                            // if the new  object does not exist in the collection and can be created
                            if (can_create(etype, eco, r) && not_occupied(etype, eco, r)) { // MAY UPDATE WITH A JUnit Test

                                // initialize and set up the success message
                                messageLabel.setText("You created the following chess piece: " + etype + " " + ecolor + " " + eco + ", " + r);
                                messageLabel.setHorizontalAlignment(SwingConstants.CENTER);

                                // set up the panel with the buttons
                                buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
                                buttonPanel.add(createNew);
                                buttonPanel.add(closeGame);

                                // initialize, set up, and display the pop up frame
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
                                int row = Integer.parseInt(selectedPieceRow) - 1;
                                int col = eco.ordinal();

                                // get the tile to place the icon for the Figure object
                                Tile tile = boardCells[7 - row][col];

                                // update the tile
                                tile.setPieceImage(icon(piece, piece.getColor()));

                                return;
                            }

                            // if the object already exists in the chessboard
                            if (!can_create(etype, eco, r)) { // MAY UPDATE WITH A JUnit Test
                                // set up the buttons
                                buttonPanel.add(tryAgain);
                                buttonPanel.add(closeGame);
                                // initialize, set up, and display the pop-up frame
                                messageLabel.setText("Chess Piece already exists on the board, try another piece");
                                messageLabel.setHorizontalAlignment(SwingConstants.CENTER);
                                buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

                                // initialize, set up, and display the pop-up frame
                                popUpFrame.getContentPane().setLayout(new BorderLayout());
                                popUpFrame.getContentPane().add(messageLabel, BorderLayout.NORTH);
                                popUpFrame.getContentPane().add(buttonPanel, BorderLayout.CENTER);
                                popUpFrame.setSize(500, 100);
                                popUpFrame.setLocationRelativeTo(null);
                                popUpFrame.setVisible(true);
                                return;
                            }

                            // if the tile is occupied by another object
                            if (!not_occupied(etype, eco, r)) { // MAY UPDATE WITH A JUnit Test
                                // set up the buttons
                                buttonPanel.add(tryAgain);
                                buttonPanel.add(closeGame);
                                // initialize, set up, and display the pop-up frame
                                messageLabel.setText("The tile is in use, try another tile");
                                messageLabel.setHorizontalAlignment(SwingConstants.CENTER);
                                buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

                                // initialize, set up, and display the pop-up frame
                                popUpFrame.getContentPane().setLayout(new BorderLayout());
                                popUpFrame.getContentPane().add(messageLabel, BorderLayout.NORTH);
                                popUpFrame.getContentPane().add(buttonPanel, BorderLayout.CENTER);
                                popUpFrame.setSize(500, 100);
                                popUpFrame.setLocationRelativeTo(null);
                                popUpFrame.setVisible(true);
                            }
                        }

                        // catch any index out of bounds erros
                        catch (ArrayIndexOutOfBoundsException ex) {
                            System.err.println("Array index out of bounds: " + ex.getMessage());
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
                    popUpFrame.getContentPane().setLayout(new BorderLayout());
                    popUpFrame.getContentPane().add(messageLabel, BorderLayout.NORTH);
                    popUpFrame.getContentPane().add(buttonPanel, BorderLayout.CENTER);
                    popUpFrame.setSize(500, 100);
                    popUpFrame.setLocationRelativeTo(null);
                    popUpFrame.setVisible(true);

                }
            }
        });

        // add the 'Move piece' button to the buttons panel
        buttonsPanel.add(Box.createVerticalStrut(10));
        buttonsPanel.add(moveButton);

        // initialize an action listener to move the Figure objects on the chessboard
        moveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

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
                submitButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {

                        // initialize a new pop up frame, button panel, and an 'Exit Game' button
                        JFrame popUpFrame = new JFrame("Notice");
                        JPanel buttonPanel = new JPanel();
                        JButton closeGame = new JButton("Exit Game");
                        JButton tryAgain = new JButton("Try Again");
                        JLabel outOfBounds = new JLabel("Input is out of bounds of the chessboard");

                        // initialize an action listener for the 'Exit Game' button
                        closeGame.addActionListener(a -> {
                            System.exit(0);
                        });

                        // initialize an action listener for the 'Try Again' button
                        tryAgain.addActionListener(n -> {
                            // close the frame
                            popUpFrame.dispose();
                        });

                        // initialize vars to hold the new coordinates
                        String x = "";
                        int y = 0;

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
                                    enums.chess_piece_type type = null;
                                    enums.chess_piece_color color = null;
                                    enums.chess_piece_columns col = null;
                                    int row = 0;

                                    boolean test = false; // ADDED

                                    // if the Object is an isntance of a Figure Object
                                    if (curr instanceof Figure piece) {
                                        type = piece.getType();
                                        color = piece.getColor();
                                        col = piece.getColumn();
                                        row = piece.getRow();

                                        System.out.println("Current piece trying to move: " + piece); // FOR TERMINAL USE

                                        // initialize labels
                                        JLabel wasMovable = new JLabel(type + " " + color + " can move from: " + col + ", " + row + " to " + x + ", " + y);
                                        JLabel notMovable = new JLabel(type + " " + color + " cannot move from: " + col + ", " + row + " to " + x + ", " + y);
                                        JLabel messageLabel = new JLabel("You tried to move " + type + " " + color + " " + col + " " + row + " to: " + x + y + " but the location was occupied.");
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
                                            int newRow = y;

                                            // if the piece can move to the new location
                                            if (piece != null && piece.moveTo(newColumn, newRow)) {
                                                // otherwise the tile is not occupied
                                                if (!isTileOccupied(newColumn, newRow, pieces)) {

                                                    // add the appropriate label to the panel
                                                    buttonPanel.add(wasMovable);

                                                    // initialize the vars for updating the tile
                                                    int oldRow = row - 1;
                                                    int oldCol = col.ordinal();

                                                    // update the Figure objects attributes
                                                    piece.setColumn(newColumn);
                                                    piece.setRow(newRow);

                                                    System.out.print("Piece has been moved: " + piece); // FOR TERMINAL USE


                                                    // get the old tile and the new tile
                                                    Tile tile = boardCells[7 - oldRow][oldCol];
                                                    Tile newTile = boardCells[8 - newRow][newColumn.ordinal()];

                                                    // reset the old tile and update the new tile icon
                                                    tile.hidePieceImage();
                                                    newTile.setPieceImage(icon(piece, piece.getColor()));
                                                    test = true;
                                                    continue;
                                                }

                                                // else if the tile is occupied
                                                else if (isTileOccupied(newColumn, newRow, pieces) && !test) {
                                                    System.out.print("Piece could not be  due to tile being in use: " + piece); // FOR TERMINAL USE
                                                    // set the appropriate label
                                                    messageLabel.setHorizontalAlignment(SwingConstants.CENTER);
                                                    buttonPanel.add(messageLabel);
                                                    continue;
                                                }
                                            }

                                            // otherwise the piece is not movable
                                            else {
                                                System.out.print("Piece could not be moved: " + piece); // FOR TERMINAL USE
                                                // set the appropriate label
                                                buttonPanel.add(notMovable);
                                                continue;
                                            }
                                        }
                                    }

                                    // if the Object is an isntance of a Figure Object
                                    if (curr instanceof Bishop bish) {
                                        type = bish.getType();
                                        color = bish.getColor();
                                        col = bish.getColumn();
                                        row = bish.getRow();

                                        System.out.println("Current piece trying to move: " + bish); // FOR TERMINAL USE

                                        // initialize labels
                                        JLabel wasMovable = new JLabel(type + " " + color + " can move from: " + col + ", " + row + " to " + x + ", " + y);
                                        JLabel notMovable = new JLabel(type + " " + color + " cannot move from: " + col + ", " + row + " to " + x + ", " + y);
                                        JLabel messageLabel = new JLabel("You tried to move " + type + " " + color + " " + col + " " + row + " to: " + x + y + " but the location was occupied.");
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
                                            int newRow = y;

                                            // if the piece can move to the new location
                                            if (piece != null && piece.moveTo(newColumn, newRow)) {
                                                // otherwise the tile is not occupied
                                                if (!isTileOccupied(newColumn, newRow, pieces)) {

                                                    // add the appropriate label to the panel
                                                    buttonPanel.add(wasMovable);

                                                    // initialize the vars for updating the tile
                                                    int oldRow = row - 1;
                                                    int oldCol = col.ordinal();

                                                    // update the Figure objects attributes
                                                    bish.setColumn(newColumn);
                                                    bish.setRow(newRow);

                                                    System.out.print("Piece has been moved: " + bish); // FOR TERMINAL USE


                                                    // get the old tile and the new tile
                                                    Tile tile = boardCells[7 - oldRow][oldCol];
                                                    Tile newTile = boardCells[8 - newRow][newColumn.ordinal()];

                                                    // reset the old tile and update the new tile icon
                                                    tile.hidePieceImage();
                                                    newTile.setPieceImageBish(bish_icon(bish.getColor()));
                                                }

                                                // else if the tile is occupied
                                                else if (isTileOccupied(newColumn, newRow, pieces)) {
                                                    System.out.print("Piece could not be  due to tile being in use: " + piece); // FOR TERMINAL USE
                                                    // set the appropriate label
                                                    messageLabel.setHorizontalAlignment(SwingConstants.CENTER);
                                                    buttonPanel.add(messageLabel);
                                                }
                                            }

                                            // otherwise the piece is not movable
                                            else {
                                                System.out.print("Piece could not be moved: " + piece); // FOR TERMINAL USE
                                                // set the appropriate label
                                                buttonPanel.add(notMovable);
                                            }
                                        }
                                    }
                                }
                                // set up the buttons
                                buttonPanel.add(tryAgain);
                                buttonPanel.add(closeGame);

                                // set the appropriate label
                                buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
                                // initialize, set up, and display the pop-up frame
                                popUpFrame.getContentPane().setLayout(new BorderLayout());
                                popUpFrame.getContentPane().add(buttonPanel, BorderLayout.CENTER);
                                popUpFrame.setSize(500, 600);
                                popUpFrame.setLocationRelativeTo(null);
                                popUpFrame.setVisible(true);
                            }
                            catch (Exception out) {
// set up the buttons
                                buttonPanel.add(tryAgain);
                                buttonPanel.add(closeGame);

                                // set the appropriate label
                                JLabel error = new JLabel("Invalid Input");

                                error.setHorizontalAlignment(SwingConstants.CENTER);
                                buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
                                // initialize, set up, and display the pop-up frame
                                popUpFrame.getContentPane().setLayout(new BorderLayout());
                                popUpFrame.getContentPane().add(error, BorderLayout.NORTH);
                                popUpFrame.getContentPane().add(buttonPanel, BorderLayout.CENTER);
                                popUpFrame.setSize(500, 100);
                                popUpFrame.setLocationRelativeTo(null);
                                popUpFrame.setVisible(true);
                            }
                        }
                        // otherwise the input is out of bounds/invalid
                        else {
                            // set up the buttons
                            buttonPanel.add(tryAgain);
                            buttonPanel.add(closeGame);

                            // set the appropriate label
                            outOfBounds.setHorizontalAlignment(SwingConstants.CENTER);
                            buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
                            // initialize, set up, and display the pop-up frame
                            popUpFrame.getContentPane().setLayout(new BorderLayout());
                            popUpFrame.getContentPane().add(outOfBounds, BorderLayout.NORTH);
                            popUpFrame.getContentPane().add(buttonPanel, BorderLayout.CENTER);
                            popUpFrame.setSize(500, 100);
                            popUpFrame.setLocationRelativeTo(null);
                            popUpFrame.setVisible(true);
                        }


                    }
                });
            }
        });
        // return the button panel
        return buttonsPanel;
    }

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

    public static ImageIcon icon(Figure piece, enums.chess_piece_color color) {

        // get the type of Figure object
        enums.chess_piece_type type = piece.getType();

        // if the color of the Figure object is black
        if(color.equals(enums.chess_piece_color.BLACK)) {
            // find and return the appropriate icon
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

        // if the color of the Figure object is white
        else {
            // find and return the appropriate icon
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

    public static ImageIcon bish_icon(enums.chess_piece_color color) {

        // if the color is black return the black icon
        if (color.equals(enums.chess_piece_color.BLACK)) {
            return bishop;
        }

        // otherwise, return the white icon
        return white_bishop;

    }

    public static void main(String[] args) {

        // call in the chessboard
        new chessboard();
    }
}

class Tile extends JPanel {

    // initialize a label
    private JLabel pieceLabel;

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

    public void setPieceImage(ImageIcon icon) {

        // set the icon of the tile
        pieceLabel.setIcon(icon);
        // update the tile
        repaint();
    }

    public void setPieceImageBish(ImageIcon bish_icon) {

        // set the icon of the tile
        pieceLabel.setIcon(bish_icon);
        // update the tile
        repaint();
    }

    public void hidePieceImage() {
        // remove the icon of the tile
        pieceLabel.setIcon(null);
        repaint();
    }

    @Override
    public Dimension getPreferredSize() {

        // initialize the dimension of the component and return it
        Dimension size = super.getPreferredSize();
        int maxSize = Math.max(size.width, size.height);
        return new Dimension(maxSize, maxSize);
    }
}