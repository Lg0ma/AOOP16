/**
 * Package containing classes related to chess pieces.
 */
package chess_pieces;
// Import necessary packages and classes

import enums.*;

/**
 * A class representing a Queen chess piece extends from Bishop's Interface.
 */
class Queen extends Rook implements IntBishop {
    /**
     * Constructs a Queen object with default values.
     */
    public Queen() {
        super();
    }

     /**
     * Constructs a Queen object with specified attributes.
     *
     * @param name    The type of the chess piece.
     * @param color   The color of the chess piece.
     * @param x_coord The initial column coordinate of the chess piece.
     * @param y_coord The initial row coordinate of the chess piece.
     */
    public Queen(chess_piece_type name, chess_piece_color color, chess_piece_columns x_coord, int y_coord) {
        super(name, color, x_coord, y_coord);
    }

    /**
     * Retrieves a string representation of the Queen's attributes.
     *
     * @return A string representing the Queen's attributes.
     */
    @Override
    public String toString() {
        return super.toString();
    }

    /**
     * Moves the Queen chess piece to the specified column and row coordinates, considering Bishop movement rules.
     *
     * @param column The target column coordinate.
     * @param row    The target row coordinate.
     * @return True if the move is valid, otherwise false.
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
            // set the valid new coordinates to the object
            setColumn(column);
            setRow(row);
            // return true
            return true;
        }
        // return false
        return false;
    }

    /**
     * Moves the Queen chess piece to the specified column and row coordinates, considering Rook movement rules.
     *
     * @param column The target column coordinate.
     * @param row    The target row coordinate.
     * @return True if the move is valid, otherwise false.
     */
    @Override
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
        // If moving like a Bishop fails, try moving like a Rook
        return super.moveTo(column, row);
    }
}