// package 
package chess_pieces;
// import packages
import enums.*;

// ANDRE MELENDEZ
// knight class
public class Knight extends Figure {
    // empty constructor
    public Knight() {
        // default values
        super();
    }

    // constructor with parameters for each class field
    public Knight(chess_piece_type name, chess_piece_color color, chess_piece_columns x_coord, int y_coord) {
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
            // set the valid new coordinates to the object
            setColumn(column);
            setRow(row);
            // set valid as false if the move is valid
            return true;
        }
        // return false
        return false;
    }
}