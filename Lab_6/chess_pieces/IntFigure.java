/**
 * Package containing classes related to chess pieces.
 */
package chess_pieces;
// Import necessary packages and classes
import enums.chess_piece_columns;

/**
 * An interface for chess pieces, providing a method for movement.
 */
public interface IntFigure {
    
    /**
     * Moves the chess piece to the specified column and row coordinates.
     *
     * @param X The column coordinate to move the chess piece to.
     * @param Y The row coordinate to move the chess piece to.
     * @return True if the move is valid, otherwise false.
     */
    Boolean moveTo(chess_piece_columns X, int Y);
}

