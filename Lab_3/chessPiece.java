/** Class for the chesspiece
 *  @author Andre Melendez
 *  @version 1.0 (04/06/24)
 */
public class chessPiece {

    /** Chesspiece name/type that is in the chess_piece_type
     */
    private chess_piece_type piece_name;

    /** Chesspiece color that is in the chess_piece_color enum
     */
    private chess_piece_color color;

    /** Chesspiece column that is in the chess_piece_columns enum
     */
    private chess_piece_columns column;

    /** Chesspiece row that is an integer
     */
    private int row;

    /**
     * A default constructor for the chessPiece object
     * sets the piece to a white pawn in coords A,0
     */
    public chessPiece() {
        // default attributes
        this.piece_name = chess_piece_type.PAWN;
        this.color = chess_piece_color.WHITE;
        this.column = chess_piece_columns.A;
        this.row = 0;
    }

    /**
     * A constructor for the chessPiece object that sets the
     * initial piece info like name , color, x_coord, and y_coord
     * @param name a chess piece name that is in the chess_piece_type enum
     * @param color a chess piece color that is in the chess_piece_color enum
     * @param x_coord the initial x coordinate for the piece that is in the chess_piece_columns enum
     * @param y_coord the initial y coordinate for the piece that is an int
     */    public chessPiece(chess_piece_type name, chess_piece_color color, chess_piece_columns column, int row) {
        // set attributes
        this.piece_name = name;
        this.color = color;
        this.column = column;
        this.row = row;
    }


    /**
     * getter method that returns the chess piece type
     * @return piece_name a chess piece type that is in the chess_piece_type enum
     */
    public chess_piece_type getType(){
        return piece_name;
    }

    /**
     * getter method that returns the chess piece color
     * @return color a chess piece color that is in the chess_piece_color enum
     */
    public chess_piece_color getColor() {
        return color;
    }

    /**
     * getter method that returns the chess piece column
     * @return column a column that is in the chess_piece_columns enum
     */
    public chess_piece_columns getColumn() {
        return column;
    }

    /**
     * getter method that returns the chess piece row
     * @return row an int variable that represents the chess piece row
     */
    public int getRow() {
        return row;
    }

    
    /** method to create a chesspiece of any specified
     *  type that is passed in the parameters
     * @param type a chess piece type that is in the chess_piece_type enum
     * @param color a chess piece color that is in the chess_piece_color enum
     * @param column the initial column value for the piece that is in the chess_piece_columns enum
     * @param row the initial row value for the piece that is an int
     * @return chessPiece a chesspiece of the specified type that contains all the values passed in the parameters
     * @throws IllegalArgumentException if a value passed in the parameters does not exist in the enums for the value
     */
    // method to create a chess piece
    public static chessPiece create_chess_piece(chess_piece_type type, chess_piece_color color, chess_piece_columns column, int row) {
        // switch case block to create a specific chess piece
        switch (type) {
            // type bishop
            case BISHOP:
                return new bishop(type, color, column, row);
            // type king
            case KING:
                return new king(type, color, column, row);
            // type knight
            case KNIGHT:
                return new knight(type, color, column, row);
            // type pawn
            case PAWN:
                return new pawn(type, color, column, row);
            // type queen
            case QUEEN:
                return new queen(type, color, column, row);
            // type rook
            case ROOK:
                return new rook(type, color, column, row);
            // otherwise the type is invalid
            default:
                throw new IllegalArgumentException("Invalid chess piece type");
        }
    }

/**
 * Method that will return a boolean saying if the piece move passed in is within the chessboard
 * THIS METHOD IS ALWAYS OVERRIDDEN IN ALL CLASSES THAT USE IT
 * @param column a new column which the piece will try to move to
 * @param row a new row which the piece will try to move to
 * @return true if the move is within the chessboard bounds
 * */
    /
    public Boolean verifyTarget(chess_piece_columns column, int row) {
        // check if the target position is within the chessboard boundaries
        return chessboard.withinChessboard(column, row);
    }
    
}
