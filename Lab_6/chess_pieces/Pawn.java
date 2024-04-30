/**
 * Package containing classes related to chess pieces.
 */
package chess_pieces;
// Import necessary packages and classes

import enums.*;

/**
 * A class representing a Pawn chess piece extends from Figure.
 */
public class Pawn extends Figure {
    /**
     * Constructs a Pawn object with default values.
     */
    public Pawn() {
        super();
    }

    /**
     * Constructs a Pawn object with specified attributes.
     *
     * @param name    The type of the chess piece.
     * @param color   The color of the chess piece.
     * @param x_coord The initial column coordinate of the chess piece.
     * @param y_coord The initial row coordinate of the chess piece.
     */
    public Pawn(chess_piece_type name, chess_piece_color color, enums.chess_piece_columns x_coord, int y_coord) {
        super(name, color, x_coord, y_coord);
    }

    /**
     * Retrieves a string representation of the Pawn's attributes.
     *
     * @return A string representing the Pawn's attributes.
     */
    @Override
    public String toString() {
        return super.toString();
    }
    
    /**
     * Moves the Pawn chess piece to the specified column and row coordinates.
     *
     * @param column The column coordinate to move the Pawn to.
     * @param row    The row coordinate to move the Pawn to.
     * @return True if the move is valid, otherwise false.
     */
    @Override
    public Boolean moveTo(enums.chess_piece_columns column, int row) {
        // initialize a boolean
        boolean valid = true;
        // turn the enum into an int
        int new_column = column.ordinal() + 1;
        int old_column = getColumn().ordinal() + 1;
        // if the team of the pawn is white
        if (getColor().ordinal() == 1) {
            // check if the x-coordinate or y-coordinate doesn't match the expected move
            if ((old_column != new_column) || ((getRow() + 1) != row)) {
                // set valid to false if the move is not valid'
                valid = false;
            }
        }
        // if the team of the pawn is black
        if (getColor().ordinal() == 0) {
            // check if the x-coordinate or y-coordinate doesn't match the expected move
            if (old_column != new_column || (getRow() - 1) != row) {
                // set valid to false if the move is not valid
                valid = false;
            }
        }
        // if valid is true up to this point
        if (valid == true) {
            // set the valid new coordinates to the object
            ////setColumn(column);
            ////setRow(row);
        }
        // return the validity of the move
        return valid;
    }
}