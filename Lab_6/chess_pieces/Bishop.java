// package
package chess_pieces;
import board.ChessBoard;
// import packages
import enums.*;

// IVAN ARMENTA & ANDRE MELENDEZ & Luis Gomez
//Bishop impements bihop interface
public class Bishop implements IntBishop {

    protected chess_piece_type piece_name;
    protected chess_piece_color color;
    protected enums.chess_piece_columns column;
    protected int row;
    protected ChessBoard chessBoard = new ChessBoard();

    /**
     * empty constructor for Bishop
     */
        public Bishop() {
        this.piece_name = chess_piece_type.BISHOP;
        this.color = chess_piece_color.WHITE;
        this.column = enums.chess_piece_columns.A;
        this.row = 0;
    }

    /**
     * Constructor with parameters for each class field
     * @param name Name of the chess piece in the enum for pieces name
     * @param color Color of the chess piece in the enum for pieces Colors
     * @param x_coord X pos of the chess piece in the enum for pieces columns
     * @param y_coord int value to represent the row of the piece
     */
        public Bishop(chess_piece_type name, chess_piece_color color, chess_piece_columns x_coord, int y_coord) {
        // set values
        this.piece_name = name;
        this.color = color;
        this.column = x_coord;
        this.row = y_coord;
    }

    
    /** 
     * getter Method to get the pieces type
     * @return chess_piece_type chess piece type
     */
    public chess_piece_type getType(){
        return piece_name;
    }

    
    /** 
     * method to get color of object
     * @return chess_piece_color
     */
    public chess_piece_color getColor() {
        return color;
    }

    /**
     * method to get column of object
     * @return chess_piece_columns pieces colums value
     */
    public enums.chess_piece_columns getColumn() {
        return column;
    }

    /**
     * method to get row of object
     * @return row row values for pieces
     */    
    public int getRow() {
        return row;
    }

    /**
     * setter method for columns
     * @param y_coord int coord value for the piece
     */
    public void setColumn(enums.chess_piece_columns x_coord) {
        this.column = x_coord;
    }
    /**
     * setter method for row
     * @param y_coord int coord value for the piece
     */
    public void setRow(int y_coord) {
        this.row = y_coord;
    }


    /** 
     * Method that prints the current attributes of the Chess Piece
     * @return String
     */
    public String toString() {
        return getType() + " (" + getColor() + ") current position: " + getColumn() + ", " + getRow();
    }
    /** 
     * method to verify its piece movement
     * @param column column that the piece will try to move to 
     * @param row row that the piece will try to move to
     * @return Boolean if piece is able to move to a spot.
     */    
    public Boolean moveTo(enums.chess_piece_columns column, int row) {
        //get current piece position
        chess_piece_columns old_column = getColumn();
        int old_row = getRow();
        // if the default method return true
        if(moveToBishop( old_column, old_row, column, row) == true) {
            // update the attributes
            setColumn(column);
            setRow(row);
            return true;
        }
        return false;
    }
}