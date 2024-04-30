/**
 * Package containing classes related to chess pieces.
 */
package chess_pieces;
// Import necessary packages and classes
import enums.*;

/**
 * A class representing a Knight chess piece extends from Figure.
 */
class Knight extends Figure {
    /**
     * Constructs a Knight object with default values.
     */
    public Knight() {
        super();
    }

    /**
     * Constructs a Knight object with specified attributes.
     *
     * @param name    The type of the chess piece.
     * @param color   The color of the chess piece.
     * @param x_coord The initial column coordinate of the chess piece.
     * @param y_coord The initial row coordinate of the chess piece.
     */
    public Knight(chess_piece_type name, chess_piece_color color, chess_piece_columns x_coord, int y_coord) {
        super(name, color, x_coord, y_coord);
    }
    
    /**
     * Retrieves a string representation of the Knight's attributes.
     *
     * @return A string representing the Knight's attributes.
     */
    @Override
    public String toString() {
        return super.toString();
    }
    
    
    /**
     * Verifies the move Knight chess piece would do to the specified column and row coordinates.
     *
     * @param column The column coordinate to move the Knight to.
     * @param row    The row coordinate to move the Knight to.
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
        // check if the move is a valid L-shape for a knight
        if ((deltax == 1 && deltay == 2) || (deltax == 2 && deltay == 1)) {
            // set valid as false if the move is valid
            return true;
        }
        // return false
        return false;
    }
}