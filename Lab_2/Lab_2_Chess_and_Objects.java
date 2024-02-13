//Andre Melendez, Luis Gomez, Ivan Armenta

// package
package Lab_2;

// imports
import java.util.Scanner;
import java.util.function.Supplier;

// main class
public class Lab_2_Chess_and_Objects {

    // create a single instance of Scanner for the entire program
    private static Scanner scnr = new Scanner(System.in);

    // everyone used this
    public static void main(String[] args) {

        // try catch block
        try {
            // method call to get the users input
            initialuserPrompt();
        }
        catch (Exception e) {
            System.out.println("Invalid input or an error occured : " + e.getMessage());
        }
        finally {
            // close the Scanner
            scnr.close();
        }
    }

    // IVAN ARMENTA and ANDRE MELENDEZ
    // method to prompt user and to return the object
    public static void initialuserPrompt() {

        // this array is where input will be separated and stored
        String[] user_input = new String[4];
        // Initial string to get user input
        String piece_info = "";
        String piece_type = "";
        // initialize an unknown object
        Object chess_piece = new Object();
        // while loop to keep the program running if the user inputs something incorrect, the program doesn't close
        while (true) {
            // try catch block
            try {
                // user prompt prints
                System.out.println("Choose one chesss piece? PAWN/ROOK/KNIGHT/BISHOP/QUEEN/KING &");
                System.out.println("Enter color piece and initial position. Ex: Pawn, White, H, 3");
                // getting user input for chess piece attributes
                piece_info = scnr.nextLine();
                // if statement to exit the program
                if (piece_info.equalsIgnoreCase("close")) {
                    System.out.println("Exiting...");
                    System.exit(0);
                }
                // making the String into array
                // split at the (",' '");
                user_input = piece_info.split(",\\s*");
                // getting the first input from the user_input array, is the type of chess piece
                piece_type = user_input[0];
                // if statement to check if the user inputs are not valid
                if (!chessboard.withinChessboard(turn_x_to_int(user_input[2].charAt(0)), Integer.parseInt(user_input[3].trim()))) {
                    System.out.println("Input is out of range ... ");
                }
                // otherwise its a valid input
                else if (chessboard.withinChessboard(turn_x_to_int(user_input[2].charAt(0)), Integer.parseInt(user_input[3].trim()))) {
                    // push the return of the create_chess_piece method to an unknown object type
                    chess_piece = create_chess_piece(piece_type, user_input);
                }
            }
            catch (Exception e) {
                System.out.println("Invalid input or an error occured : " + e.getMessage());
            }  
            // while the users input is not close
            while(!piece_info.equalsIgnoreCase("close")) {
                // method call to the second prompt
                second_Prompt(chess_piece);
            }
        }
    }

    // IVAN ARMENTA
    // method to check if the user wants to move an existing chess piece, or make a new one
    public static void second_Prompt(Object chess_piece){

        // try catch block
        try {
            // print user prompt string
            System.out.println("Do you want to move the same piece again or move a different piece?\n1: Move again\t2:Choose a different piece or type close to exit\n Or type \"close\" to exit the program");
            // store the users input
            String user_prompt_select = scnr.nextLine();
            // if the user wants to close the program
            if (user_prompt_select.equalsIgnoreCase("close")) {
                System.out.println("Exiting...");
                System.exit(0);
            }

            // if the users input is 2
            if (user_prompt_select.equalsIgnoreCase("2")) {
                // inform user of their choice
                System.out.println("Choosing another piece...\n");
                // method call to the initial user prompt to make a new piece
                initialuserPrompt();
            } 
            // otherwise move the current piece
            else if (user_prompt_select.equalsIgnoreCase("1")){
                // find the objects class
                Class<?> obj_class = chess_piece.getClass();
                // method call to the move again method
                moveAgain(obj_class, chess_piece);
            }
        }
        catch (Exception e) {
            System.out.println("Invalid input or an error occured : " + e.getMessage());
        }
    }

    // Luis Gomez
    // method for changing the position of an existing chess piece
    public static <T> Object moveAgain( T type, Object chess_piece) {

        // try catch block
        try {
            // initialize a new String array and String variable
            String [] new_move = new String[2];
            String move;
            // prompt user to input
            System.out.println("Where would you like to move the piece Ex: H, 3 or \"close\" to ");
            // store user input
            move = scnr.nextLine();
            // if user want to close the program
            if (move.equalsIgnoreCase("close")) {
                System.out.println("Exiting...");
                System.exit(0);
            }
            // split the input at ",' '" and store in an array
            new_move = move.split(",\\s*");
            // find the class of the object
            Class<?> objClass = chess_piece.getClass();
            // if the object is a pawn
            if(objClass == pawn.class){
                if( ((pawn) chess_piece).verifyTarget(new_move[0].charAt(0), Integer.parseInt(new_move[1].trim())) == false){
                    System.out.println("Move is invalid try again");
                }else{
                    System.out.println("Move was successful");
                }
            }
            // if the object is a bishop
            else if(objClass == bishop.class){
                if( ((bishop) chess_piece).verifyTarget(new_move[0].charAt(0), Integer.parseInt(new_move[1].trim())) == false){
                    System.out.println("Move is invalid try again");
                }else{
                    System.out.println("Move was successful");
                }            
            }
            // if the object is a king
            else if(objClass == king.class){
                if( ((king) chess_piece).verifyTarget(new_move[0].charAt(0), Integer.parseInt(new_move[1].trim())) == false){
                    System.out.println("Move is invalid try again");
                }else{
                    System.out.println("Move was successful");
                }
            }
            // if the object is a queen
            else if(objClass == queen.class){
                if( ((queen) chess_piece).verifyTarget(new_move[0].charAt(0), Integer.parseInt(new_move[1].trim())) == false){
                    System.out.println("Move is invalid try again");
                }else{
                    System.out.println("Move was successful");
                }
            }
            // if the object is a rook
            else if(objClass == rook.class){
                if( ((rook) chess_piece).verifyTarget(new_move[0].charAt(0), Integer.parseInt(new_move[1].trim())) == false){
                    System.out.println("Move is invalid try again");
                }else{
                    System.out.println("Move was successful");
                }
            }
            // if the object is a knight
            else if(objClass == knight.class){
                if( ((knight) chess_piece).verifyTarget(new_move[0].charAt(0), Integer.parseInt(new_move[1].trim())) == false){
                    System.out.println("Move is invalid try again");
                }else{
                    System.out.println("Move was successful");
                }
            }
            return chess_piece;
        }
        catch (Exception e) {
            System.out.println("Invalid input or an error occured : " + e.getMessage());
            return chess_piece;
        }
    }

    // ANDRE MELENDEZ
    // method to create the chess piece object
    public  static Object create_chess_piece(String piece_type, String[] user_input) {

        // initialize an empty chess piece object of unknown type
        Object chess_piece = null;
        // switch case block to create specific chess piece type objects and push them to the chess_piece variable
        switch (piece_type.toLowerCase()) {
            // format for creating chess piece
            // new obj(chess_piece_type.NAME, color, x-coordinate, y-coordinate)
            // if the user input is pawn
            case "pawn":
                // create a pawn object and associate it with the unknown object type chess_piece, set the appropriate chess_piece_type as the enum type name
                chess_piece = create_object(() -> new pawn(chess_piece_type.PAWN, user_input[1], user_input[2].charAt(0), Integer.parseInt(user_input[3].trim())));
                break;
            // if the user input is rook
            case "rook":
                // create a rook object and associate it with the unknown object type chess_piece, set the appropriate chess_piece_type as the enum type name
                chess_piece = create_object(() -> new rook(chess_piece_type.ROOK, user_input[1], user_input[2].charAt(0),
                        Integer.parseInt(user_input[3].trim())));
                break;
            // if the user input is knight
            case "knight":
                // create a knight object and associate it with the unknown object type chess_piece, set the appropriate chess_piece_type as the enum type name
                chess_piece = create_object(() -> new knight(chess_piece_type.KNIGHT, user_input[1],
                        user_input[2].charAt(0), Integer.parseInt(user_input[3].trim())));
                break;
            // if the user input is bishop
            case "bishop":
                // create a bishop object and associate it with the unknown object type chess_piece, set the appropriate chess_piece_type as the enum type name
                chess_piece = create_object(() -> new bishop(chess_piece_type.BISHOP, user_input[1],
                        user_input[2].charAt(0), Integer.parseInt(user_input[3].trim())));
                break;
            // if the user input is queen
            case "queen":
                // create a queen object and associate it with the unknown object type chess_piece, set the appropriate chess_piece_type as the enum type name
                chess_piece = create_object(() -> new queen(chess_piece_type.QUEEN, user_input[1],
                        user_input[2].charAt(0), Integer.parseInt(user_input[3].trim())));
                break;
            // if the user input is king
            case "king":
                // create a king object and associate it with the unknown object type chess_piece, set the appropriate chess_piece_type as the enum type name
                chess_piece = create_object(() -> new king(chess_piece_type.KING, user_input[1], user_input[2].charAt(0),
                        Integer.parseInt(user_input[3].trim())));
                break;
            // as a default, the user input is incorrect
            default:
                System.out.println("Wrong input, please select a correct chess piece");
                break;
        }
        // return the created object
        return chess_piece;
    }

    // IVAN ARMENTA and ANDRE MELENDEZ
    public static int turn_x_to_int(char x) {
        // convert the char to uppercase to handle both upper and lower case inputs
        char upperCaseX = Character.toUpperCase(x);
        // initialize an int
        int x_value = -1;

        // check if the character is not a valid column letter (between 'A' and 'H')
        if (upperCaseX < 'A' || upperCaseX > 'H') {
            System.out.println("Invalid x-coordinate input: " + upperCaseX);
        } 
        // otherwise the input is valid
        else {
            // find the value of the char
            x_value = upperCaseX - 'A' + 1;
        }
        // return the int value
        return x_value;
    }
    // ANDRE MELENDEZ
    // method to create an object of unknown type, the <T> is meant to represent the object type, so ths can be 'pawn', 'rook', etc.
    public  static <T> T create_object(Supplier<T> supplier) {
        // return the type of object that is being associated with create_object
        return supplier.get();
    }


}
