// package 
package chess_pieces;
// import packages
import enums.*;

// ANDRE MELENDEZ
// Pawn class that extends to Figure
public class Pawn extends Figure {
    // empty constructor
    public Pawn() {
        // default values
        super();
    }

    // constructor with parameters for each class field
    public Pawn(chess_piece_type name, chess_piece_color color, enums.chess_piece_columns x_coord, int y_coord) {
        // set values
        super(name, color, x_coord, y_coord);
    }

    // method that prints the current attributes of the Chess Piece
    @Override
    public String toString() {
        return super.toString();
    }
    
    // method to verify its piece movement
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
            setColumn(column);
            setRow(row);
        }
        // return the validity of the move
        return valid;
    }
}