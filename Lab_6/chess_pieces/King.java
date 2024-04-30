/**
 * Package containing classes related to chess pieces.
 */
package chess_pieces;
// Import necessary packages and classes
import enums.*;


/**
 * A class representing a King chess piece, extending the behavior of Queen.
 */
public class King extends Queen {
    /**
     * Constructs a King object with default values.
     */
    public King() {
        super();
    }

    /**
     * Constructs a King object with specified attributes.
     *
     * @param name    The type of the chess piece.
     * @param color   The color of the chess piece.
     * @param x_coord The initial column coordinate of the chess piece.
     * @param y_coord The initial row coordinate of the chess piece.
     */
    public King(chess_piece_type name, chess_piece_color color, chess_piece_columns x_coord, int y_coord) {
        super(name, color, x_coord, y_coord);
    }

    /**
     * Retrieves a string representation of the King's attributes.
     *
     * @return A string representing the King's attributes.
     */
    @Override
    public String toString() {
        return super.toString();
    }

    /**
     * Verifies the move King chess piece would do to the specified column and row coordinates.
     *
     * @param column The column coordinate to move the King to.
     * @param row    The row coordinate to move the King to.
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
        // if the difference between the new and old coordinates is 1
        if ((deltax == 1 && deltay == 1) || (deltax == 0 && deltay == 1) || (deltax == 1 && deltay == 0)) {
            // if the King moves like the Queen
            if (super.moveTo(column, row) == true) {
                // set the valid new coordinates to the object
                ////setColumn(column);
                ////setRow(row);
                // return true if the move is valid
                return true;
            }
        }
        // return false
        return false;
    }
}