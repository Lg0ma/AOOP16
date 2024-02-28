// package 
package chess_pieces;
// import packages
import enums.*; // needed import despite extend

// ANDRE MELENDEZ
// Queen class that extends to Queen
class Bishop extends Queen {
    // empty constructor
    public Bishop() {
        // default values
        super();
    }

    // constructor with parameters for each class field
    public Bishop(chess_piece_type name, chess_piece_color color, chess_piece_columns x_coord, int y_coord) {
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
    public Boolean moveToBishop(enums.chess_piece_columns column, int row) {
        return super.moveToBishop(column, row);
    }
}