// package
package board;
/* */
// imports 
import enums.chess_piece_columns;

// LUIS GOMEZ
// class that implements IntChessBoard
public class ChessBoard implements IntChessBoard {
    // method to check if coordinates are within range of the Chess Board
    public Boolean verifyCoordinate(chess_piece_columns X, int Y) {
        // initialise int variables
        int CONSTANT_MAX_ROW_COL = 8;
        int MIN_ROW_COL = 1;
        // convert enum to an int
        int column = X.ordinal() + 1;
        // if the inputs are within range of the chessboard
        if (column <= CONSTANT_MAX_ROW_COL && column >= MIN_ROW_COL) {
            if (Y <= CONSTANT_MAX_ROW_COL && Y >= MIN_ROW_COL) {
                // return true
                return true;
            }
        }
        // otherwise return false
        return false;
    }
    
}