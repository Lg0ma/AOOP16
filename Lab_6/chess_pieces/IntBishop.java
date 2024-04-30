package chess_pieces;
// initialize an interface for the Bishop class

import enums.chess_piece_columns;

public interface IntBishop {
    // initialize a default method to be implemented
    default Boolean moveToBishop(chess_piece_columns oldColumn, int oldRow, chess_piece_columns column, int row){
        // turn the enum into an int
        int new_column = column.ordinal() + 1;
        int old_column = oldColumn.ordinal() + 1;
        // find the absolute difference in the x and y coordinates
        int deltax = Math.abs(old_column - new_column);
        int deltay = Math.abs(oldRow - row);
        // check if the move is a valid diagonal move
        if ((deltax == deltay) && (deltax > 0 && deltay >0)) {
            // return true
            return true;
        }
        // return false
        return false;
    }
}