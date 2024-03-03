// package 
package chess_pieces;
import board.ChessBoard;
// import packages
import enums.*;

// IVAN ARMENTA & ANDRE MELENDEZ & Luis Gomez
//Bishop impements bihop interface
class Bishop implements IntBishop {

    protected chess_piece_type piece_name;
    protected chess_piece_color color;
    protected enums.chess_piece_columns column;
    protected int row;
    protected ChessBoard chessBoard = new ChessBoard();

    // empty constructor
    public Bishop() {
        this.piece_name = chess_piece_type.BISHOP;
        this.color = chess_piece_color.WHITE;
        this.column = enums.chess_piece_columns.A;
        this.row = 0;
    }

    // constructor with parameters for each class field
    public Bishop(chess_piece_type name, chess_piece_color color, chess_piece_columns x_coord, int y_coord) {
        // set values
        this.piece_name = name;
        this.color = color;
        this.column = x_coord;
        this.row = y_coord;
    }

    // method to get the type of object
    public chess_piece_type getType(){
        return piece_name;
    }

    // method to get color of object
    public chess_piece_color getColor() {
        return color;
    }

    // method to get column of object
    public enums.chess_piece_columns getColumn() {
        return column;
    }

    // method to get row of object
    public int getRow() {
        return row;
    }

    // setter method for column
    public void setColumn(enums.chess_piece_columns x_coord) {
        this.column = x_coord;
    }

    // setter method for row
    public void setRow(int y_coord) {
        this.row = y_coord;
    }


    // method that prints the current attributes of the Chess Piece
    public String toString() {
        return getType() + " (" + getColor() + ") current position: " + getColumn() + ", " + getRow();
    }

    // method to verify its piece movement (Bishop)
    public Boolean moveToBishop(chess_piece_columns column, int row) {
        // turn the enum into an int
        int new_column = column.ordinal() + 1;
        int old_column = getColumn().ordinal() + 1;
        // find the current y position of the rook
        int old_row = getRow();
        // find the absolute difference in the x and y coordinates
        int deltax = Math.abs(old_column - new_column);
        int deltay = Math.abs(old_row - row);
        // check if the move is a valid diagonal move
        if ((deltax == deltay) && (deltax > 0 && deltay >0)) {
            // set the valid new coordinates to the object
            setColumn(column);
            setRow(row);
            // return true
            return true;
        }
        // return false
        return false;
    }

    // method to verify its piece movement
    public Boolean moveTo(enums.chess_piece_columns column, int row) {
        // check if the piece can move like a Bishop
        if (moveToBishop(column, row) == true) {
            return true;
        }
        // otherwise, return false
        return false;
    }
}