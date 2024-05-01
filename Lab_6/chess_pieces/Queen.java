// package
package chess_pieces;
// import packages
import enums.*;

/**
 * Queen class that extends to Rook and implementes IntBishop
 * @author Andre Melendez
 */
class Queen extends Rook implements IntBishop {
    /**
     * empty constructor for Queen
     */
    public Queen() {
        // default values
        super();
    }

    /**
     * Constructor with parameters for each class field
     * @param name Name of the chess piece in the enum for pieces name
     * @param color Color of the chess piece in the enum for pieces Colors
     * @param x_coord X pos of the chess piece in the enum for pieces columns
     * @param y_coord int value to represent the row of the piece
     */
    public Queen(chess_piece_type name, chess_piece_color color, chess_piece_columns x_coord, int y_coord) {
        // set values
        super(name, color, x_coord, y_coord);
    }

    /** 
     * Method that prints the current attributes of the Chess Piece
     * @return String
     */
    @Override
    public String toString() {
        return super.toString();
    }

    /** 
     * method to verify its piece movement (Bishop)
     * @param column column that the piece will try to move to 
     * @param row row that the piece will try to move to
     * @return Boolean if piece is able to move to a spot.
     */
    public Boolean moveToBishop(chess_piece_columns column, int row) {
        // turn the enum into an int
        int new_column = column.ordinal() + 1;
        int old_column = getColumn().ordinal() + 1;
        // find the current y position of the rook
        int old_row = getRow();
        // find the absolute difference in the x and y coordinates
        int deltax = Math.abs(old_column - new_column);
        int deltay = Math.abs(old_row - row);
        // check if the move is a valid diagonal move
        if ((deltax == deltay) && (deltax > 0 && deltay >0)) {
            // return true
            return true;
        }
        // return false
        return false;
    }

    /** 
     * method to verify its piece movement (Bishop)
     * @param column column that the piece will try to move to 
     * @param row row that the piece will try to move to
     * @return Boolean if piece is able to move to a spot.
     */
    @Override
    public Boolean moveTo(enums.chess_piece_columns column, int row) {
        //get current piece position
        chess_piece_columns old_column = getColumn();
        int old_row = getRow();
        // if the default method return true
        if(moveToBishop( old_column, old_row, column, row)) {
            return true;
        }
        // If moving like a Bishop fails, try moving like a Rook
        return super.moveTo(column, row);
    }
}