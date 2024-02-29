// package 
package chess_pieces;
// import packages
import enums.*; // needed import despite extend


// initialize a King class that extends to Queen
public class King extends Queen {
 // empty constructor
    public King() {
        // default values
        super();
    }

    // constructor with parameters for each class field
    public King(chess_piece_type name, chess_piece_color color, chess_piece_columns x_coord, int y_coord) {
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
        // if statement to check if the user input is within the chessboard range
        if (super.moveTo(column, row) == false) {
            // print statement and return
            System.out.println("Input is out of range ... ");
            return false;
        }
        // find the absolute difference in the x and y coordinates
        int deltax = Math.abs(old_column - new_column);
        int deltay = Math.abs(old_row - row);
        // initialize a Boolean for queen validation
        Boolean queen_movement = super.moveTo(column, row);
        // if the queen movement is true and the difference between the new and old coordinates is 1
        if (queen_movement == true && (deltax == 1 || deltay == 1)) {
            // set the valid new coordinates to the object
            setColumn(column);
            setRow(row);
            // return true if the move is valid
            return true;
        }
        // return false
        return false;
    }
}