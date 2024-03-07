// package 
package chess_pieces;
// import packages
import enums.*;

// ANDRE MELENDEZ
// Queen class that extends to Rook and implementes IntBishop
public class Queen extends Rook implements IntBishop {
    // empty constructor
    public Queen() {
        // default values
        super();
    }

    // constructor with parameters for each class field
    public Queen(chess_piece_type name, chess_piece_color color, chess_piece_columns x_coord, int y_coord) {
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
        // check if the piece can move like a Bishop        
        chess_piece_columns old_column = getColumn();
        int old_row = getRow();
        if(moveToBishop( old_column, old_row, column, row) == true){
            setColumn(column);
            setRow(row);
            return true;
        }
        // If moving like a Bishop fails, try moving like a Rook
        return super.moveTo(column, row);
    }
}