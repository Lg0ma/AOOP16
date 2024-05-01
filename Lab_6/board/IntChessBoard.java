/**
 * Package containing classes related to the chess board.
 */package board;

// Import necessary packages and enums
import enums.*;;

/**
 * Interface representing a chess board.
 * @author Luis Gomez
 */
public interface IntChessBoard {
    /**
     * Verifies if the given coordinates are within the range of the chess board.
     *
     * @param x The column of the chess piece.
     * @param Y The row of the chess piece.
     * @return true if the coordinates are within range, false otherwise.
     */
    Boolean verifyCoordinate(chess_piece_columns x, int Y);
}
