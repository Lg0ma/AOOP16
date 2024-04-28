/**
 * Package containing classes related to chess pieces.
 */
package chess_pieces;
// Import necessary packages and classes
import enums.chess_piece_columns;

/**
 * An interface for the Bishop class, providing a default method for bishop movement.
 */
public interface IntBishop {
    /**
     * Checks if the move to the specified column and row coordinates is valid for a bishop.
     *
     * @param oldColumn The column of the current position.
     * @param oldRow    The row of the current position.
     * @param column    The column to move to.
     * @param row       The row to move to.
     * @return True if the move is a valid diagonal move for a bishop, otherwise false.
     */
    default Boolean moveToBishop(chess_piece_columns oldColumn, int oldRow, chess_piece_columns column, int row){
        // turn the enum into an int
        int new_column = column.ordinal() + 1;
        int old_column = oldColumn.ordinal() + 1;
        // find the absolute difference in the x and y coordinates
        int deltax = Math.abs(old_column - new_column);
        int deltay = Math.abs(oldRow - row);
        // check if the move is a valid diagonal move
        if ((deltax == deltay) && (deltax > 0 && deltay > 0)) {
            // return true
            return true;
        }
        // return false
        return false;
    }
}