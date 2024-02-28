// imports 
import enums.*;
import board.*;

// main class
public class testing {
    // initialize a public Scanner

    // main method
    public static void main(String[]args) {
         // create variables to hold the users inputs to create the object
        chess_piece_type type = chess_piece_type.PAWN;  // Use the enum constant directly
        System.out.println(type);

        chess_piece_color color = chess_piece_color.WHITE;
        enums.chess_piece_columns x_coord = enums.chess_piece_columns.A;
        int y_coord = 1;
         // create the object using the users inputs and store in the pieces array
         Figure obj = Figure.create_chess_piece(type, color, x_coord, y_coord);

         System.out.println(obj.toString()); // testing toString
         if (obj.moveTo(enums.chess_piece_columns.A, 2)) {
            System.out.println("Success");
            System.out.println(obj.toString()); // testing toString
         }
         else {
            System.out.println("Failure");
         }
    }
}