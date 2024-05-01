/**
 * Package containing classes related to the chess board.
 */
package board;

// Import necessary packages and enums
import enums.*;

/**
 * Implementation of the IntChessBoard interface representing a chess board.
 * @author Luis Gomez
 */
public class ChessBoard implements IntChessBoard {

    /**
     * Verifies if the given coordinates are within the range of the chess board.
     *
     * @param X The column of the chess piece.
     * @param Y The row of the chess piece.
     * @return true if the coordinates are within range, false otherwise.
     */
    public Boolean verifyCoordinate(chess_piece_columns X, int Y) {
        // Constants defining the maximum and minimum rows/columns of the chess board
        final int CONSTANT_MAX_ROW_COL = 8;
        final int MIN_ROW_COL = 1;

        // Convert the enum column to an integer
        int column = X.ordinal() + 1;

        // Check if the inputs are within range of the chess board
        if (column <= CONSTANT_MAX_ROW_COL && column >= MIN_ROW_COL &&
            Y <= CONSTANT_MAX_ROW_COL && Y >= MIN_ROW_COL) {
            // If within range, return true
            return true;
        }
        // Otherwise, return false
        return false;
    }
}
