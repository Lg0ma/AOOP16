import javax.swing.*; 
import enums.*;
import chess_pieces.*;
import java.awt.*; 
import java.awt.event.ActionEvent; 
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.Flow;

public class chessboard extends JFrame {

    static ArrayList<Figure> pieces = new ArrayList<Figure>();

    private static Figure piece;
    private static String selectedPieceType = "";
    private static String selectedPieceColor = "";
    private static String selectedPieceRow = "";
    private static String selectedPieceCol = "";
    private static String newPieceRow = "";
    private static String newPieceCol = "";
    private static int count = 0;

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

    static JPanel panel = new JPanel(); 
    static private boolean canMove = false;
    static JPanel sidePanel = sidePanel();
    JPanel boardPanel; 
    static Tile[][] boardCells;
    JPanel buttonPanel;

    public chessboard() {

        super();
        setTitle("Chess Game (Lab 6)");
        

        panel.setLayout(new BorderLayout()); 
        boardPanel = new JPanel();
        boardPanel.setLayout(new GridLayout(8, 8));

        initializeBoard();

        panel.add(boardPanel, BorderLayout.CENTER);
        add(panel);
        setLocationRelativeTo(null); 
        setSize(900, 800);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        add(sidePanel, BorderLayout.WEST);
    }

    // Method that creates the board and adds tiles to it.
    private void initializeBoard() {
        
        boardCells = new Tile[8][8];

        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                Color backgroundColor = (row + col) % 2 == 0 ? Color.WHITE : Color.GRAY; 
                Tile tile = new Tile(backgroundColor);
                boardCells[row][col] = tile;
                boardPanel.add(tile);
            }
        }
    }

    // Side panel for user to interact
    private static JPanel sidePanel() {   
        
        // Color gray = new Color(192,192,192);
        JPanel buttonsPanel = new JPanel();
        BoxLayout buttonLayout = new BoxLayout(buttonsPanel, BoxLayout.Y_AXIS);
        buttonsPanel.setLayout(buttonLayout);

        String [] chessPieces = {"Pawn", "Rook", "Knight", "Queen", "King"};
        String [] row = {"-","1", "2", "3", "4", "5", "6", "7", "8"};
        String [] col = {"-","A", "B", "C", "D", "E", "F", "G", "H"};
        String [] colors = {"-", "White", "Black"};

        JLabel chessType = new JLabel("Select a chess piece");
        JLabel inputLabel = new JLabel("Select initial chess pos.");
        JLabel colorLabel = new JLabel("Chess piece color");
        // JLabel finalPosLabel = new JLabel("Select final chess pos."); # MARKED FOR DELETION

        chessType.setAlignmentX(Component.CENTER_ALIGNMENT);
        inputLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        colorLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        // finalPosLabel.setAlignmentX(Component.CENTER_ALIGNMENT); # MARKED FOR DELETION

        buttonsPanel.add(chessType);
        buttonsPanel.add(Box.createVerticalStrut(5));

        JComboBox colDropdown = new JComboBox<>(col);
        JComboBox rowDropdown = new JComboBox<>(row);
        JComboBox colorDropdown = new JComboBox<>(colors);
        // JComboBox finalColDropdown = new JComboBox<>(col); # MARKED FOR DELETION
        // JComboBox finalRowDropdown = new JComboBox<>(row); # MARKED FOR DELETION

        colDropdown.setMaximumSize(colDropdown.getPreferredSize());
        colDropdown.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Creates pop up after selecting initial column
        colDropdown.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                JComboBox<String> comboBox = (JComboBox<String>) e.getSource();
                selectedPieceCol = (String) comboBox.getSelectedItem();
            }
        });

        rowDropdown.setMaximumSize(rowDropdown.getPreferredSize());
        rowDropdown.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Creates pop up after selecting initial row
        rowDropdown.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                JComboBox<String> comboBox = (JComboBox<String>) e.getSource();
                selectedPieceRow = (String) comboBox.getSelectedItem();
            }
        });

        colorDropdown.setAlignmentX(Component.CENTER_ALIGNMENT);
        colorDropdown.setMaximumSize(colorDropdown.getPreferredSize());

        // Creates pop up after selecting piece color
        colorDropdown.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                JComboBox<String> comboBox = (JComboBox<String>) e.getSource();
                selectedPieceColor = (String) comboBox.getSelectedItem();
            }
        });

        // finalColDropdown.setAlignmentX(Component.CENTER_ALIGNMENT); # MARKED FOR DELETION
        // finalColDropdown.setMaximumSize(finalColDropdown.getPreferredSize()); # MARKED FOR DELETION

        // Creates pop up after selecting final column # MARKED FOR DELETION
        // finalColDropdown.addActionListener(new ActionListener() { # MARKED FOR DELETION
            // @Override # MARKED FOR DELETION
            // public void actionPerformed(ActionEvent e) { # MARKED FOR DELETION

                // JComboBox<String> comboBox = (JComboBox<String>) e.getSource(); # MARKED FOR DELETION
                // newPieceCol = (String) comboBox.getSelectedItem(); # MARKED FOR DELETION
            // } # MARKED FOR DELETION
        // }); # MARKED FOR DELETION

        // finalRowDropdown.setAlignmentX(Component.CENTER_ALIGNMENT); # MARKED FOR DELETION
        // finalRowDropdown.setMaximumSize(finalRowDropdown.getPreferredSize()); # MARKED FOR DELETION

        // Creates pop up after selecting final row # MARKED FOR DELETION 
        // finalRowDropdown.addActionListener(new ActionListener() { # MARKED FOR DELETION 
             // @Override # MARKED FOR DELETION 
            // public void actionPerformed(ActionEvent e) { # MARKED FOR DELETION

                // JComboBox<String> comboBox = (JComboBox<String>) e.getSource(); # MARKED FOR DELETION
                // newPieceRow = (String) comboBox.getSelectedItem(); # MARKED FOR DELETION
            // } # MARKED FOR DELETION
        // }); # MARKED FOR DELETION

        JButton createButton = new JButton("Create piece");
        JButton moveButton = new JButton("Move piece");
        ButtonGroup buttonPieces = new ButtonGroup();

        for(String type: chessPieces){
            JRadioButton buttonType = new JRadioButton(type);
            buttonType.setAlignmentX(Component.CENTER_ALIGNMENT);
            buttonPieces.add(buttonType);
            buttonsPanel.add(buttonType);

            buttonType.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    JFrame popUpFrame = new JFrame("Selected Chess Piece Type");
                    JLabel messageLabel = new JLabel("You selected the chess piece: " + type);

                    selectedPieceType = type;
                    messageLabel.setHorizontalAlignment(SwingConstants.CENTER);
                    popUpFrame.add(messageLabel);
                    popUpFrame.setSize(300, 100);
                    popUpFrame.setLocationRelativeTo(null);
                }
            });
        }

        createButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        moveButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        buttonsPanel.add(Box.createVerticalStrut(10));
        buttonsPanel.add(Box.createVerticalStrut(20));
        buttonsPanel.add(colorLabel);
        buttonsPanel.add(Box.createVerticalStrut(5));
        buttonsPanel.add(colorDropdown);
        buttonsPanel.add(Box.createVerticalStrut(20));
        buttonsPanel.add(inputLabel);
        buttonsPanel.add(Box.createVerticalStrut(5));

        JPanel initialPosPanel = new JPanel();
        initialPosPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        initialPosPanel.setLayout(new BoxLayout(initialPosPanel, BoxLayout.X_AXIS));
        initialPosPanel.add(colDropdown);

        initialPosPanel.add(rowDropdown);
        buttonsPanel.add(initialPosPanel);
        buttonsPanel.add(Box.createVerticalStrut(20));
        // buttonsPanel.add(finalPosLabel); # MARKED FOR DELETION
        buttonsPanel.add(Box.createVerticalStrut(5));

        // JPanel finalPosPanel = new JPanel(); # MARKED FOR DELETION
        // finalPosPanel.setAlignmentX(Component.CENTER_ALIGNMENT); # MARKED FOR DELETION
        // finalPosPanel.setLayout(new BoxLayout(finalPosPanel, BoxLayout.X_AXIS)); # MARKED FOR DELETION
        //finalPosPanel.add(finalColDropdown); # MARKED FOR DELETION 
        //finalPosPanel.add(finalRowDropdown); # MARKED FOR DELETION
        //buttonsPanel.add(finalPosPanel); # MARKED FOR DELETION
        buttonsPanel.add(Box.createVerticalStrut(40));

        buttonsPanel.add(createButton);
        
        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) throws NumberFormatException {
                JButton closeGame = new JButton("Exit Game");
                JButton createNew = new JButton("Create New Piece");

                if (selectedPieceType != "" && selectedPieceColor != ""  && selectedPieceCol != "" && selectedPieceRow != "") {

                    JFrame popUpFrame = new JFrame("Created a Chess Piece");
                    popUpFrame.setSize(150, 250);
                    JLabel messageLabel = new JLabel("You created the following chess piece: " + selectedPieceType + " " + selectedPieceColor + " " + selectedPieceCol + ", " + selectedPieceRow);
                    messageLabel.setHorizontalAlignment(SwingConstants.CENTER);
                    
                    JPanel buttonPanel = new JPanel();
                    buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
                    buttonPanel.add(Box.createVerticalStrut(10));
                    buttonPanel.add(createNew);
                    buttonPanel.add(closeGame);

                    popUpFrame.getContentPane().setLayout(new BoxLayout(popUpFrame.getContentPane(), BoxLayout.Y_AXIS));
                    popUpFrame.getContentPane().add(Box.createVerticalStrut(10));          
                    popUpFrame.add(messageLabel, BorderLayout.NORTH);
                    popUpFrame.add(buttonPanel, BorderLayout.CENTER);
                    popUpFrame.getContentPane().add(buttonPanel);
            
                    try {
                        enums.chess_piece_type etype = enums.chess_piece_type.valueOf(selectedPieceType.toUpperCase());
                        enums.chess_piece_color ecolor = enums.chess_piece_color.valueOf(selectedPieceColor.toUpperCase());
                        enums.chess_piece_columns eco = enums.chess_piece_columns.valueOf(selectedPieceCol);

                        int r = Integer.parseInt(selectedPieceRow);
                        
                        piece = chess_pieces.Figure.create_chess_piece(etype, ecolor, eco, r);
                        
                        boolean canCreate = true;

                        for (Figure a_piece : pieces) {
                            enums.chess_piece_type temp_type = a_piece.getType();
                            int temp_row = a_piece.getRow();
                            enums.chess_piece_columns temp_col = a_piece.getColumn();
                            
                            if (temp_row == piece.getRow() && temp_col == piece.getColumn()  && temp_type == piece.getType()) {
                                canCreate = false;
                            } 
                            
                        }

                        if (!pieces.contains(piece) && canCreate == true && count < 6) {
                            pieces.add(piece);
                            System.out.println("Piece added: " + piece);
                            int row = Integer.parseInt(selectedPieceRow) - 1; 
                            int col = eco.ordinal(); 
                            
                            Tile tile = boardCells[7 - row][col];
                            
                            tile.setPieceImage(icon(piece, piece.getColor()));
                            count += 1;

                            int newRow = Integer.parseInt(newPieceRow);
                            System.out.println(newRow);
                            enums.chess_piece_columns newCol = enums.chess_piece_columns.valueOf(newPieceCol);
                            System.out.println(newCol);
                            messageLabel.setHorizontalAlignment(SwingConstants.CENTER);
                            popUpFrame.add(messageLabel);
                            popUpFrame.setSize(400, 100);
                            popUpFrame.setLocationRelativeTo(null);
                            popUpFrame.setVisible(true);

                        }

                        else if (count == 6) {
                            JFrame popUpFrame1 = new JFrame("Invalid input");
                            JLabel messageLabel1 = new JLabel("All chess pieces have already been created");
                            messageLabel1.setHorizontalAlignment(SwingConstants.CENTER);
                            popUpFrame1.add(messageLabel1);
                            popUpFrame1.setSize(400, 100);
                            popUpFrame1.setLocationRelativeTo(null);
                            popUpFrame1.setVisible(true);   
                            return;
                        }
                        else {
                            JFrame popUpFrame1 = new JFrame("Error");
                            JLabel messageLabel1 = new JLabel("Chess Piece already exists on the board or the tile is already in use, try again");
                            messageLabel1.setHorizontalAlignment(SwingConstants.CENTER);
                            popUpFrame1.add(messageLabel1);
                            popUpFrame1.setSize(400, 100);
                            popUpFrame1.setLocationRelativeTo(null);
                            popUpFrame1.setVisible(true);   
                            return;
                        }
                    } 
                    // catch (IllegalArgumentException ex) {
                    //     System.err.println("Invalid enum value: " + ex.getMessage());
                    // } 
                    catch (ArrayIndexOutOfBoundsException ex) {
                        System.err.println("Array index out of bounds: " + ex.getMessage());
                    }
            
                    popUpFrame.setSize(600, 100);
                    popUpFrame.setLocationRelativeTo(null);
                    popUpFrame.setVisible(true);
                }

                else  {
                    JFrame popUpFrame = new JFrame("Invalid Input");
                    JLabel messageLabel = new JLabel("Invalid input, please create a valid chess piece");
                    messageLabel.setHorizontalAlignment(SwingConstants.CENTER);
                    popUpFrame.add(messageLabel);
                    popUpFrame.setSize(400, 100);
                    popUpFrame.setLocationRelativeTo(null);
                    popUpFrame.setVisible(true);
                }

                closeGame.addActionListener(a -> {
                    System.exit(0);
                });

                enums.chess_piece_columns eco = enums.chess_piece_columns.valueOf(selectedPieceCol);
                int oldRow = Integer.parseInt(selectedPieceRow) - 1; 
                int oldCol = eco.ordinal(); 
                Tile tile = boardCells[oldRow][oldCol];

                createNew.addActionListener(n -> {
                    Window window = SwingUtilities.getWindowAncestor(createNew);

                    if(window instanceof JFrame){
                    tile.hidePieceImage(icon(piece, piece.getColor()));
                    ((JFrame) window).dispose();
                    }
                });    
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

                            if (coordinates.length() == 2) {
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

                                if (validX && y > 0 && y < 9  && piece.moveTo(enums.chess_piece_columns.valueOf(x), y)) {
                                    System.out.println(x + " : " + y);
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

                                else{
                                    buttonPanel.add(notMovable);
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