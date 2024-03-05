import board.*;
import chess_pieces.*;
import java.util.Scanner;

// main class
public class testing {

    // initialize a private Scanner
    private static Scanner scnr = new Scanner(System.in);
    private static ChessBoard chessBoard = new ChessBoard(); // added attribute

    // everyone used this
    public static void main(String[] args) {
        // method call to prompt user to make chess pieces
        prompt(); // array used to store the chess piece objects array
        // method prompt to move the chess pieces to a new location
        // close the Scanner
        scnr.close();
    }

 // IVAN ARMENTA and ANDRE MELENDEZ
    // method to prompt the user to create chess pieces and store the newly created objects
    public static Figure[] prompt() {
        // initialize an empty chessPiece array to hold the chess piece objects
        Figure pieces[] = new Figure[5];
        // for loop to create the chess pieces and store them in an array
        for (int count = 0; count < 6; count++) { // CHANGE BACK TO 6
            // try catch block for errors
            try {
                // initialize a Boolean and set it to false'
                Boolean exists = false;
                // print to prompt the user to input a chess piece with a position
                System.out.println("Input a chess piece with its initial position. Can only input a chess piece once. E.G. Pawn, White, H, 3. Input 'stop' to end the program ... ");
                System.out.println("");
                // store the users input in a String
                String user_input = scnr.nextLine().toUpperCase();
                // if the user inputted 'stop'
                if (user_input.equals("STOP")) {
                    // end the program
                    System.exit(0);
                }
                // split the users input by any number of nonalphanumeric chars
                String[] user_info = user_input.split("\\W+");
                // for loop to traverse the number of chess pieces created
                for (int idx = 0; idx < count; idx++) {
                    // if the users inputted chess piece already exists in the pieces array
                    if (enums.chess_piece_type.valueOf(user_info[0]) == pieces[idx].getType()) {
                        // change the exists Boolean to true
                        exists = true;
                        // let the user know
                        System.out.println("The inputted chess piece already exists, please input a non-existing chess piece ...");
                        System.out.println("");
                        // decrement count since the chess piece was invalid
                        count--; 
                        // break to loop back up the method
                        break;
                    }
                }
                // if the chess piece does not exist
                if (!exists) {
                    // check if the users inputted initial position is within range of the chessboard
                    if (!chessBoard.verifyCoordinate(enums.chess_piece_columns.valueOf(user_info[2]), Integer.parseInt(user_info[3]))) {
                        // let the user know that the inputted position is not within range
                        System.out.println("User input for starting position is out of range ... ");
                        System.out.println("HERE IS THE ISSUE ");
                        System.out.println("");
                        // decrementcount since the chess piece was invalid
                        count--;
                        // loop back up to prompt the user again
                        continue;
                    }
                    // otherwise, the coordinate is valid
                    else {
                        // create variables to hold the users inputs to create the object
                        enums.chess_piece_type type = enums.chess_piece_type.valueOf(user_info[0]);
                        enums.chess_piece_color color = enums.chess_piece_color.valueOf(user_info[1]);
                        enums.chess_piece_columns x_coord = enums.chess_piece_columns.valueOf(user_info[2]);
                        int y_coord = Integer.parseInt(user_info[3]);
                        if(type == enums.chess_piece_type.BISHOP){
                            bishopPiece =  new Bishop(type,color,x_coord,y_coord);
                        }
                        else{
                        // create the object using the users inputs and store in the pieces array
                        pieces[count] = Figure.create_chess_piece(type, color, x_coord, y_coord);
                        }
                        // let the user know that the chess piece was create
                        System.out.println("The " + type + " chess piece has been successfully created ...");
                        System.out.println("");
                    }
                }
            }
            catch(Exception e) {
                System.out.println("Invalid input, try again ...");
                System.out.println("");
                // decrement count since the users input was invalid
                count--;
            }
        }
        // return the array of pieces
        return pieces;
    }

    // Luis Gomez
    // traverses the array and asks for new position to try to move piece into
    public static void move(Figure[] chessPieces, Bishop bishop) {
        // initialize the index variable, and the variables to store the user input
        int i = 0;
        String[] user_input;
        String newMove;
        // prompt the user to input a location to move all chess pieces to
        System.out.println("Input a new position for the pieces to try to move to. Input 'stop to end program .... ");
        // store the user input
        newMove = scnr.nextLine();
        // if the users input is 'stop' 
         if (newMove.toUpperCase().equals("STOP")) {
              // end the program
            System.exit(0);
        }                    
        // split the users input at the',' and any whitespace                                                                     
        user_input = newMove.split("\\W+");
        // tyr catch blocks to handle any errors
        try {
            //  convert the users input for the x-coordinate into an enum
            enums.chess_piece_columns col = enums.chess_piece_columns.valueOf(user_input[0].toUpperCase());
            // convert the users input for the y-coordinate into an integer
            int row = Integer.parseInt(user_input[1]);
            // while loop to traverse the chessPiece array

            //validation for bishop piece
            //if piece is in board
            if (chessBoard.verifyCoordinate(col, row) == true) {
                // if the move is valid
                if(bishop.moveTo(col, row) == true){
                    System.out.println("Success: " + bishop.toString() + "\n");
                }//if move is invalid
                else if(bishop.moveTo(col, row) == false){
                    System.out.println("Failure: " + bishop.toString() + "\n");
                }
            }
            // otherwise the new position is not within the chess board
            else {
                System.out.println("The user input is not in range of the chess board...");
                // let the user know that the chess piece cannot move to the new location
                System.out.println("Failure: " + bishop.toString() + "\n");
            }
            
            while(i < chessPieces.length) {
                Figure currPiece = chessPieces[i];
                // if the new position is within the chess board
                if (chessBoard.verifyCoordinate(col, row) == true) {
                    // if the move is valid
                    if (chessPieces[i].moveTo(col, row) == true) {
                        // let the user know the chess piece was moved here
                        System.out.println("Success: " + currPiece.toString() + "\n");
                    }
                    // if the move is not valid
                    else if (chessPieces[i].moveTo(col, row) == false) {
                        // let the user know that the chess piece cannot move to the new location
                        System.out.println("Failure: " + currPiece.toString() + "\n");
                    }
                }
                // otherwise the new position is not within the chess board
                else {
                    System.out.println("The user input is not in range of the chess board...");
                    // let the user know that the chess piece cannot move to the new location
                    System.out.println("Failure: " + currPiece.toString() + "\n");
                }
                // increment through the index
                i++;
            }
        }
        catch (Exception e) {
            System.out.println("Invalid input try again!");
            move(chessPieces,bishop);
        }
    }
}