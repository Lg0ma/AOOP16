// imports 
import enums.chess_piece_columns;
import enums.chess_piece_type;
import enums.chess_piece_color;
import board.ChessBoard;
import board.IntChessBoard;

// main class
public class testing {
    // initialize a public Scanner

    // main method
    public static void main(String[]args) {
         // create variables to hold the users inputs to create the object
        chess_piece_type type = chess_piece_type.PAWN;  // Use the enum constant directly
        System.out.println(type);

        chess_piece_color color = chess_piece_color.WHITE;
        chess_piece_columns x_coord = chess_piece_columns.A;
        int y_coord = 1;
         // create the object using the users inputs and store in the pieces array
         Figure obj = Figure.create_chess_piece(type, color, x_coord, y_coord);

         System.out.println(obj.getColumn() + ", " + obj.getRow());
         if (obj.moveTo(chess_piece_columns.A, 2)) {
            System.out.println("Success");
         }
         else {
            System.out.println("Failure");
         }
    }
}