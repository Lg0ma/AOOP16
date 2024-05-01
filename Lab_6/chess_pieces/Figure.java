/**
 * Package containing classes related to chess pieces.
 */
package chess_pieces;
// Import necessary packages and classes
import enums.*;

import java.lang.reflect.Constructor;

import board.*;

/**
 * Abstract class representing a chess piece.
 * @author Andre Melendez
 */
public abstract class Figure implements IntFigure {

    // Attributes representing the chess piece
    protected chess_piece_type piece_name;
    protected chess_piece_color color;
    protected enums.chess_piece_columns column;
    protected int row;
    protected ChessBoard chessBoard = new ChessBoard();

    /**
     * Default constructor initializing the chess piece with default values.
     */    
    protected Figure() {
        // default attributes
        this.piece_name = chess_piece_type.PAWN;
        this.color = chess_piece_color.WHITE;
        this.column = enums.chess_piece_columns.A;
        this.row = 0;
    }
    
    /**
     * Constructor with attributes to initialize the chess piece with specified values.
     *
     * @param name   The type of the chess piece.
     * @param color2 The color of the chess piece.
     * @param column The column coordinate of the chess piece.
     * @param row    The row coordinate of the chess piece.
     */
    protected Figure(chess_piece_type name, chess_piece_color color2, enums.chess_piece_columns column, int row) {
        // set attributes
        this.piece_name = name;
        this.color = color2;
        this.column = column;
        this.row = row;
    }

    /**
     * Gets the type of the chess piece.
     *
     * @return The type of the chess piece.
     */
    public chess_piece_type getType(){
        return piece_name;
    }
    
    /**
     * Gets the color of the chess piece.
     *
     * @return The color of the chess piece.
     */
    public chess_piece_color getColor() {
        return color;
    }

    /**
     * Gets the column coordinate of the chess piece.
     *
     * @return The column coordinate of the chess piece.
     */
    public enums.chess_piece_columns getColumn() {
        return column;
    }

    /**
     * Gets the row coordinate of the chess piece.
     *
     * @return The row coordinate of the chess piece.
     */
    public int getRow() {
        return row;
    }

    /**
     * Sets the column coordinate of the chess piece.
     *
     * @param x_coord The column coordinate to set.
     */
    public void setColumn(enums.chess_piece_columns x_coord) {
        this.column = x_coord;
    }

    /**
     * Sets the row coordinate of the chess piece.
     *
     * @param y_coord The row coordinate to set.
     */
    public void setRow(int y_coord) {
        this.row = y_coord;
    }
    
    /**
     * Method to create a chess piece of a specific type, color, column, and row.
     *
     * @param type   The type of the chess piece.
     * @param color  The color of the chess piece.
     * @param column The column coordinate of the chess piece.
     * @param row    The row coordinate of the chess piece.
     * @return A new instance of the chess piece.
     */
    public static Figure create_chess_piece(chess_piece_type type, chess_piece_color color, enums.chess_piece_columns column, int row) {
        // switch case block to create a specific chess piece
        switch (type) {
            // type king
            case KING:
                return new King(type, color, column, row);
            // type knight
            case KNIGHT:
                return new Knight(type, color, column, row);
            // type pawn
            case PAWN:
                return new Pawn(type, color, column, row);
            // type queen
            case QUEEN:
                return new Queen(type, color, column, row);
            // type rook
            case ROOK:
                return new Rook(type, color, column, row);
            // otherwise the type is invalid
            default:
                throw new IllegalArgumentException("Invalid chess piece type");
        }
    }

    /**
     * Returns a string representation of the current attributes of the chess piece.
     *
     * @return A string representing the type, color, and position of the chess piece.
     */
    public String toString() {
        return getType() + " (" + getColor() + ") current position: " + getColumn() + ", " + getRow();
    }

    /**
     * Moves the chess piece to the specified column and row coordinates.
     *
     * @param X The column coordinate to move the chess piece to.
     * @param Y The row coordinate to move the chess piece to.
     * @return True if the move is valid and within the range of the chess board, otherwise false.
     */
    public Boolean moveTo(enums.chess_piece_columns X, int Y) {
        // check if the input is within range
        return chessBoard.verifyCoordinate(X, Y);
    }

    /**
     * Indicates whether some other object is "equal to" this one.
     *
     * @param obj The object to compare for equality.
     * @return True if the specified object is equal to this one, otherwise false.
     */
    @Override
    public boolean equals(Object obj) {

        // if the objects are the same
        if (this == obj) {
            return true;
        }
        // if the objects are not the same
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        // return the boolean
        Figure other = (Figure) obj;
        return piece_name == other.piece_name && color == other.color && column == other.column && row == other.row;
    }
}