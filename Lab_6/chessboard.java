import javax.swing.*; 
import enums.*;
import chess_pieces.*;
import java.awt.*; 
import java.awt.event.ActionEvent; 
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class chessboard extends JFrame {

    // ArrayList collection that holds the Figure objects
    static ArrayList<Figure> pieces = new ArrayList<Figure>();

    // static vars for creating a Figure object
    private static Figure piece;
    private static String selectedPieceType = "";
    private static String selectedPieceColor = "";
    private static String selectedPieceRow = "";
    private static String selectedPieceCol = "";

    // counter to count the number of Figure objects on the chessboard
    private static int count = 0;

    // icons
    static ImageIcon pawn = new ImageIcon("C:\\Users\\andre\\Code\\OOP\\AOOP16\\Lab_6\\art\\BP.gif");
    static ImageIcon knight = new ImageIcon("C:\\Users\\andre\\Code\\OOP\\AOOP16\\Lab_6\\art\\BN.gif");
    static ImageIcon rook = new ImageIcon("C:\\Users\\andre\\Code\\OOP\\AOOP16\\Lab_6\\art\\BR.gif");
    static ImageIcon bishop = new ImageIcon("C:\\Users\\andre\\Code\\OOP\\AOOP16\\Lab_6\\art\\BB.gif");
    static ImageIcon queen = new ImageIcon("C:\\Users\\andre\\Code\\OOP\\AOOP16\\Lab_6\\art\\BQ.gif");
    static ImageIcon king = new ImageIcon("C:\\Users\\andre\\Code\\OOP\\AOOP16\\Lab_6\\art\\BK.gif");
    static ImageIcon white_pawn = new ImageIcon("C:\\Users\\andre\\Code\\OOP\\AOOP16\\Lab_6\\art\\WP.gif");
    static ImageIcon white_knight = new ImageIcon("C:\\Users\\andre\\Code\\OOP\\AOOP16\\Lab_6\\art\\WN.gif");
    static ImageIcon white_rook = new ImageIcon("C:\\Users\\andre\\Code\\OOP\\AOOP16\\Lab_6\\art\\WR.gif");
    static ImageIcon white_bishop = new ImageIcon("C:\\Users\\andre\\Code\\OOP\\AOOP16\\Lab_6\\art\\WB.gif");
    static ImageIcon white_queen = new ImageIcon("C:\\Users\\andre\\Code\\OOP\\AOOP16\\Lab_6\\art\\WQ.gif");
    static ImageIcon white_king = new ImageIcon("C:\\Users\\andre\\Code\\OOP\\AOOP16\\Lab_6\\art\\WK.gif");

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

    private static JPanel sidePanel() {   

        // initialize a panel for buttons
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
        for(String type: chessPieces){
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
                JFrame popUpFrame = new JFrame("Created a Chess Piece");
                JLabel messageLabel = new JLabel();
                JPanel buttonPanel = new JPanel();
                JButton closeGame = new JButton("Exit Game");
                JButton createNew = new JButton("Create New Piece");
        
                // if the required inputs are selected
                if (selectedPieceType != "" && selectedPieceColor != "" && selectedPieceCol != "" && selectedPieceRow != "") { // MAY UPDATE WITH A JUnit Test

                    // try
                    try {
                        // initialize vars that hold the inputs for creating a Figure object
                        enums.chess_piece_type etype = enums.chess_piece_type.valueOf(selectedPieceType.toUpperCase());
                        enums.chess_piece_color ecolor = enums.chess_piece_color.valueOf(selectedPieceColor.toUpperCase());
                        enums.chess_piece_columns eco = enums.chess_piece_columns.valueOf(selectedPieceCol);
                        int r = Integer.parseInt(selectedPieceRow);
        
                        // create the Figure object
                        piece = chess_pieces.Figure.create_chess_piece(etype, ecolor, eco, r);
                        
                        // initialize booleans to check if the Figure can be created
                        boolean canCreate = true;
                        boolean occupied = false;
                        
                        // for loop to traverse the existing valid Figure objects
                        for (Figure p : pieces) {
                            // if the Figure object already exists, set the boolean to false
                            if (piece.getType() == p.getType()) { // MAY UPDATE WITH A JUnit Test
                                canCreate = false;
                            }
                            
                            // if the coordinates are occupied, set the boolean to true
                            if (piece.getColumn() == p.getColumn() && piece.getRow() == p.getRow()) { // MAY UPDATE WITH A JUnit Test
                                occupied = true;
                            }

                        }

                        // if the new Figure object does not exist in the collection and can be created 
                        if (!pieces.contains(piece) && canCreate && count < 6 && occupied == false && canCreate) { // MAY UPDATE WITH A JUnit Test

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

                            System.out.println("Piece added: " + piece); // FOR TERMINAL USE

                            // set up the vars for updating the tile
                            int row = Integer.parseInt(selectedPieceRow) - 1;
                            int col = eco.ordinal();
        
                            // get the tile to place the icon for the Figure object
                            Tile tile = boardCells[7 - row][col];
    
                            // update the tile
                            tile.setPieceImage(icon(piece, piece.getColor()));

                            // increment through the counter
                            count += 1;
                        }
                        
                        // if the Figure object already exists in the chessboard
                        if (canCreate == false) { // MAY UPDATE WITH A JUnit Test
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
                        if (occupied == true) { // MAY UPDATE WITH A JUnit Test
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
                        else if (count == 6) { // MAY UPDATE WITH A JUnit Test
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
                        System.err.println("Array index out of bounds: " + ex.getMessage());
                    }
                } 
            
                // otherwise, the user input is invalid
                else {
                    // initialize, set up, and display the pop up frame
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
        


        buttonsPanel.add(Box.createVerticalStrut(10));
        buttonsPanel.add(moveButton);
        moveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                JFrame new_coord = new JFrame("New Coordinates");

                JPanel coordPanel = new JPanel();
                JLabel coordLabel = new JLabel("Enter coordinates (e.g., A1): ");
                JTextField coordTextField = new JTextField(5); 
                JButton submitButton = new JButton("Submit");

                coordPanel.add(coordLabel);
                coordPanel.add(coordTextField);
                coordPanel.add(submitButton);

                new_coord.add(coordPanel);

                new_coord.setSize(400, 100);
                new_coord.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                new_coord.setLocationRelativeTo(null);
                new_coord.setVisible(true);

                submitButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {

                        for (Figure piece : pieces) {
                            enums.chess_piece_type type = piece.getType();
                            enums.chess_piece_color color = piece.getColor();
                            enums.chess_piece_columns col = piece.getColumn();
                            int row = piece.getRow();
                            String x = "";
                            int y = 0;

                            JFrame popUpFrame = new JFrame("New Chess Piece Location");
                            JPanel buttonPanel = new JPanel();
                            JButton closeGame = new JButton("Exit Game");


                            String coordinates = coordTextField.getText().replaceAll("[,\\s/]", "");

                            closeGame.addActionListener(a -> {
                                System.exit(0);
                            });

                            if (coordinates.length() == 2) { // MAY UPDATE WITH A JUnit Test
                                String [] coord = coordinates.split("");
                                x = coord[0].toUpperCase();
                                y = Integer.parseInt(coord[1]);

                                boolean validX = false;
                                for (enums.chess_piece_columns column : enums.chess_piece_columns.values()) {
                                    if (column.name().equals(x)) {
                                        validX = true;
                                        break;
                                    }
                                }
                                
                                JLabel wasMovable = new JLabel(type + " " + color + " can move from: " + col + ", " + row + " to " + x + ", " + y);
                                JLabel notMovable = new JLabel(type + " " + color + " cannot move from: " + col + ", " + row  + " to " + x + ", " + y);
                                JLabel messageLabel = new JLabel("You tried to move " + type + " " + color + " " + col + " " + row + " to: " + x + y);
                                JLabel outOfBounds = new JLabel("Input is out of bounds of the chessboard");

                                if (validX && y > 0 && y < 9  && piece.moveTo(enums.chess_piece_columns.valueOf(x), y)) { // MAY UPDATE WITH A JUnit Test
                                    System.out.println(x + " : " + y); // FOR TERMINAL USE
                                    buttonPanel.add(wasMovable);
                                    int oldRow = row - 1; 
                                    int oldCol =  col.ordinal(); 
                                    enums.chess_piece_columns newEco = enums.chess_piece_columns.valueOf(x);
                                    int newRow = y - 1; 
                                    int newCol = newEco.ordinal();                     
                
                                    Tile tile = boardCells[7 - oldRow][oldCol];
                                    Tile newTile = boardCells[7 - newRow][newCol];
                
                                    tile.hidePieceImage(icon(piece, piece.getColor()));
                                    newTile.setPieceImage(icon(piece, piece.getColor()));
                                }

                                else if (validX && y > 0 && y < 9  && piece.moveTo(enums.chess_piece_columns.valueOf(x), y) == false){ // MAY UPDATE WITH A JUnit Test
                                    buttonPanel.add(notMovable);
                                }

                                else if (!validX || y <= 0 || y >= 9 ) { // MAY UPDATE WITH A JUnit Test
                                    buttonPanel.add(outOfBounds);
                                }

                                messageLabel.setHorizontalAlignment(SwingConstants.CENTER);
                                buttonPanel.add(closeGame);
                                buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 5));
                                popUpFrame.getContentPane().setLayout(new BoxLayout(popUpFrame.getContentPane(), BoxLayout.Y_AXIS));
                                popUpFrame.getContentPane().add(Box.createVerticalStrut(10));          
                                popUpFrame.getContentPane().add(buttonPanel);
                
                                popUpFrame.setSize(400, 100);
                                popUpFrame.setLocationRelativeTo(null);
                                popUpFrame.setVisible(true);
                            }
                        }

                        

                    }
                });
            }
        });

        return buttonsPanel;
    }

    public static ImageIcon icon(Figure piece, enums.chess_piece_color color) {

        enums.chess_piece_type type = piece.getType();

        if(color.equals(enums.chess_piece_color.BLACK)){
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
        else{
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

    public static void main(String[] args) {

        new chessboard();
    }
}

class Tile extends JPanel {
    
    private JLabel pieceLabel; 

    public Tile(Color color) {

        setBackground(color);
        pieceLabel = new JLabel();
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(pieceLabel, gbc);
    }

    public void setPieceImage(ImageIcon icon) {

        pieceLabel.setIcon(icon);
        repaint(); 
    }

    public void hidePieceImage(ImageIcon icon){
        pieceLabel.setVisible(false);
        repaint();
    }

    @Override
    public Dimension getPreferredSize() { 

        Dimension size = super.getPreferredSize();
        int maxSize = Math.max(size.width, size.height);
        return new Dimension(maxSize, maxSize);
    }
}