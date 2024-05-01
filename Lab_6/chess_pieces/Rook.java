/**
 * Package containing classes related to chess pieces.
 */
package chess_pieces;
// Import necessary packages and classes

import enums.*;

/**
 * A class representing a Rook chess piece extends from Figure.
 * @author Andre Melendez
 */
class Rook extends Figure {
    /**
     * Constructs a Rook object with default values.
     */
    public Rook() {
        super();
    }

    /**
     * Constructs a Rook object with specified attributes.
     *
     * @param name    The type of the chess piece.
     * @param color   The color of the chess piece.
     * @param x_coord The initial column coordinate of the chess piece.
     * @param y_coord The initial row coordinate of the chess piece.
     */
    public Rook(chess_piece_type name, chess_piece_color color, chess_piece_columns x_coord, int y_coord) {
        super(name, color, x_coord, y_coord);
    }

    /**
     * Retrieves a string representation of the Rook's attributes.
     *
     * @return A string representing the Rook's attributes.
     */
    @Override
    public String toString() {
        return super.toString();
    }
    
    /**
     * Moves the Rook chess piece to the specified column and row coordinates.
     *
     * @param column The target column coordinate.
     * @param row    The target row coordinate.
     * @return True if the move is valid, otherwise false.
     */
    @Override
    public Boolean moveTo(enums.chess_piece_columns column, int row) {
        // turn the enum into an int
        int new_column = column.ordinal() + 1;
        int old_column = getColumn().ordinal() + 1;
        // find the current y position of the rook
        int old_row = getRow();
        // find the absolute difference in the x and y coordinates
        int deltax = Math.abs(old_column - new_column);
        int deltay = Math.abs(old_row - row);
        // check if the move is valid along the y-axis or x-axis
        if (deltax == 0 && deltay != 0) {
            // return true if the move is valid along the y-axis
            setColumn(column);
            setRow(row);
            return true;
        } else if (deltax != 0 && deltay == 0) {
            // set the valid new coordinates to the object
            setColumn(column);
            setRow(row);
            // return true if the move is valid along the x-axis
            return true;
        }
        // return false if the move is not valid
        return false;
    }
}