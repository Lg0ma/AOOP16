// package
/* */
// imports 
import enums.chess_piece_columns;
import enums.chess_piece_type;
import enums.chess_piece_color;
import board.ChessBoard;
import board.IntChessBoard;

// ANDRE MELENDEZ
// initialize an abstract class
public abstract class Figure implements IntFigure {

    // initialize protected attributes
    protected chess_piece_type piece_name;
    protected chess_piece_color color;
    protected chess_piece_columns column;
    protected int row;

    // constructor with no attributes
    protected Figure() {
        // default attributes
        this.piece_name = chess_piece_type.PAWN;
        this.color = chess_piece_color.WHITE;
        this.column = chess_piece_columns.A;
        this.row = 0;
    }
    
    // constructor with attributes
    protected Figure(enums.chess_piece_type name, enums.chess_piece_color color2, chess_piece_columns column, int row) {
        // set attributes
        this.piece_name = name;
        this.color = color2;
        this.column = column;
        this.row = row;
    }

    // method to get the type of object
    public chess_piece_type getType(){
        return piece_name;
    }
    
    // method to get color of object
    protected chess_piece_color getColor() {
        return color;
    }

    // method to get column of object
    protected chess_piece_columns getColumn() {
        return column;
    }

    // method to get row of object
    protected int getRow() {
        return row;
    }

    // setter method for column
    public void setColumn(chess_piece_columns x_coord) {
        this.column = x_coord;
    }

    // setter method for row
    public void setRow(int y_coord) {
        this.row = y_coord;
    }
    
    // method to create a chess piece
    protected static Figure create_chess_piece(chess_piece_type type, chess_piece_color color, chess_piece_columns column, int row) {
        // switch case block to create a specific chess piece
        switch (type) {
            // type bishop
            case BISHOP:
                //return new Bishop(type, color, column, row);
            // type king
            case KING:
                //return new King(type, color, column, row);
            // type knight
            case KNIGHT:
                //return new Knight(type, color, column, row);
            // type pawn
            case PAWN:
                return new Pawn(type, color, column, row);
            // type queen
            case QUEEN:
                //return new Queen(type, color, column, row);
            // type rook
            case ROOK:
                //return new Rook(type, color, column, row);
            // otherwise the type is invalid
            default:
                throw new IllegalArgumentException("Invalid chess piece type");
        }
    }

    // partially implement the moveTo method
    public Boolean moveTo(chess_piece_columns X, int Y) {
        // check if the target position is within the Chess Board boundaries
        IntChessBoard chessBoard = new ChessBoard(); // create an instance of ChessBoard
        // method call from ChessBoard
        return chessBoard.verifyCoordinate(X, Y);
    }
}