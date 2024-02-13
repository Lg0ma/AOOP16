// Groups 16 - Ivan Armenta, Andre Melendex, Luis Gomez

/*Main task: given a chess piece and its initial position (x, y), verify that you can move it to the new position.
When verifying the move, check that initial and final positions are inside the chess board, 
new position is possible based on the rules for that piece and consider that the chess board only has that piece.x	
You are given a text file with several chess pieces. */

//Imports
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

// ANDRE MELENDEZ
// class object meant to hold the attributes of a chess piece (instance variables) // NO OTHER METHODS AND NO CONSTRUCTOR
class chess_piece {
    // type of chess piece
    public String type;
    // team of chess piece
    public String team;
    // x-coordinate of chess piece position
    public char x_pos;
    // y-coordinate of chess piece position
    public int y_pos;
}

public class Team_16_Lab1 {

    // ANDRE MELENDEZ
    // the main method is where the program will run
    public static void main(String[] args) {
        // File Reader method call
        String[][] array = (file_reader());
        // find the number of chess pieces in the 2D array
        int size = array.length;
        // initialize an array to gold objects
        chess_piece[] pieces = new chess_piece[size];
        // for loop to traverse the object array
        for (int i = 0; i < pieces.length; i++) {
            // create a chess piece object method call
            chess_piece object = create_piece(array[i]);
            // push the new object into the array
            pieces[i] = object;
        }
        // user prompt method call
        int[] input_coord = user_prompt();
        // turn the x-coordinate char into an int and store it in a separate array
        // initialize an empty array
        int[] x_coord = new int[size];
        // for loop to traverse the array of objects
        for (int r = 0; r < pieces.length; r++) {
            // method call to fill the x_coord array
            x_coord[r] = turn_x_to_int(pieces[r].x_pos);
        }
        // for loop to call the validate method for each piece
        for (int r = 0; r < pieces.length; r++) {
            // method call to validate
            validate(pieces[r], input_coord, x_coord[r]);
        }
    }

    // ANDRE MELENDEZ
    // method that reads the file and return a 2D String array holding the inputs
    public static String[][] file_reader() {
        // try/catch block for errors
        try {
            // initialize file
            File input = new File("Lab1_input.txt");
            // initialize a scanner to count the lines and its columns
            Scanner lines = new Scanner(input);
            // initialize a scanner to read the information of the line
            Scanner reader = new Scanner(input);
            // initialize ints for counters
            int c = 0; // counter for columns
            int l = 0; /// counter for lines
            // while loop to count the lines
            while (lines.hasNextLine()) { // while there is a next line in the file
                // add to counter by 1
                l++;
                // find the number of columns by splitting at a whitespace
                c = lines.nextLine().split("\\s+").length;
            }
            // initialize an index to traverse an array
            int i = 0;
            // initialize a String 2D array to put the files information
            String[][] text_info = new String[l][c];
            // while loop to read through the text file
            while (reader.hasNextLine()) { // while the text file has a next line
                // if statement to check if the index is less than the number of lines
                if (i < l) {
                    // set the lines in the text file as a String
                    String line = reader.nextLine();
                    // split the line by a whitespace and push onto a row of the 2D array
                    text_info[i] = line.split(","); // WILL HAVE TO CHANGE T0 COMMA BECAUSE IT INCLUDES THE COMMA AS
                                                    // PART OF AN ATTRIBUTE OF THE OBJECT
                    // increment through the index by +1
                    i++;
                }
                // otherwise break out of the loop
                else {
                    break;
                }
            }
            // close the Scanners
            lines.close();
            reader.close();
            // return the array
            return text_info;
        }
        // catch block
        catch (FileNotFoundException e) {
            // print the error
            System.out.println("An Error Occurred ...");
            e.printStackTrace();
        }
        // return empty array
        return new String[0][0];
    }

    // ANDRE MELENDEZ
    // method that creates the object
    public static chess_piece create_piece(String[] array) {
        // initialize an empty instance object
        chess_piece piece = new chess_piece();
        // initialize the variables
        String type = array[0].trim(); // remove any white-space around the variable
        String team = array[1].trim(); // remove any white-space around the variable
        char x_pos = array[2].trim().charAt(0); // remove any white-space around the variable and parse into a char
        int y_pos = Integer.parseInt(array[3].trim()); // remove any white-space around the variable and parse into an
                                                       // int
        // fil the objects attributes with values
        piece.type = type;
        piece.team = team;
        piece.x_pos = x_pos;
        piece.y_pos = y_pos;
        // return the object
        return piece;

    }

    // IVAN ARMENTA
    // method that prompts user an input and quits the program
    public static int[] user_prompt() {
        // initialize the Scanner, and vars
        Scanner scnr = new Scanner(System.in);
        String temp = "";
        int[] user_input = new int[2];
        String x = "";
        // print statement
        System.out.println(
                "Where do you want to move the chess piece? Ex: E,8. Anything else will result in an error (including anything out of range).\nIf you want to exit the program type 'Close'");
        // push the input to the temp String var
        temp = scnr.nextLine();
        // trim the input
        temp = temp.replace(" ", "");
        // if the input is 'close'
        if (temp.equalsIgnoreCase("close")) {
            // close the program
            System.exit(0);
        }
        // if the length does not equal to three or does not contain a comma
        if (temp.length() != 3 || temp.contains(",") == false) {
            // print statement
            System.out.println("Invalid input");
            // close scanner
            scnr.close();
            // return empty input
            return user_input;
        }
        // initialize a temp array and split the user input at the ','
        String[] temp_arr = temp.split(",");
        // push the first element ot the x var
        x = temp_arr[0];
        // turn the x var into an int by a method call and send it to the first element
        // of the user_input array
        user_input[0] = turn_x_to_int(x.charAt(0));
        // turn the other element into an int and send it to the last element of the
        // user_input array
        user_input[1] = Integer.parseInt(temp_arr[1]);
        // clos ethe scanner
        scnr.close();
        // return the array
        return user_input;
    }

    // ANDRE MELENDEZ
    // method that turns the x_coordinate char into an int
    public static int turn_x_to_int(char x) {
        // convert the character into an uppercase for consistent results
        char upper_x = Character.toUpperCase(x);
        // initialize the variable to store the coordinate
        int x_coord = 0;
        // switch case statements to check the value of the x_pos variable
        switch (upper_x) {
            case 'A':
                x_coord = 1;
                break;
            case 'B':
                x_coord = 2;
                break;
            case 'C':
                x_coord = 3;
                break;
            case 'D':
                x_coord = 4;
                break;
            case 'E':
                x_coord = 5;
                break;
            case 'F':
                x_coord = 6;
                break;
            case 'G':
                x_coord = 7;
                break;
            case 'H':
                x_coord = 8;
                break;
            // default case for out of range inputs
            default:
                System.out.println("Input is out of range...");
                break;
        }
        // return the coordinate
        return x_coord;
    }

    // ANDRE MELENDEZ
    // method that turns the x_coordinate int into a char (WILL BE USED FOR OUTPUT,
    // SINCE WE NEVER STORE AND RETURN THE CHAR VERSION OF THE X-COORDINATE)
    public static char turn_x_to_char(int x) {
        // initialize the variable to store the coordinate
        char x_coord = 0;
        // switch case statements to check the value of the x_pos variable
        switch (x) {
            case 1:
                x_coord = 'a';
                break;
            case 2:
                x_coord = 'b';
                break;
            case 3:
                x_coord = 'v';
                break;
            case 4:
                x_coord = 'd';
                break;
            case 5:
                x_coord = 'e';
                break;
            case 6:
                x_coord = 'f';
                break;
            case 7:
                x_coord = 'g';
                break;
            case 8:
                x_coord = 'h';
                break;
            // default case for out of range inputs
            default:
                System.out.println("Input is out of range...");
                break;
        }
        // return the coordinate
        return x_coord;
    }

    // ANDRE MELENDEZ and LUIS GOMEZ
    // helper method to call upon the appropriate validation methods
    public static void validate(chess_piece piece, int[] input_coord, int x_coord) {
        boolean validation = false;
        // if the chess piece is a pawn
        if ("Pawn".equals(piece.type)) {
            validation = pawn_Validation(piece, input_coord, x_coord);
        }
        // if the chess piece is a knight
        if ("Knight".equals(piece.type)) {
            validation = knight_Validation(piece, input_coord, x_coord);
        }
        // if the chess piece is a bishop
        if ("Bishop".equals(piece.type)) {
            validation = bishop_Validation(piece, input_coord, x_coord);
        }
        // if the chess piece is a rook
        if ("Rook".equals(piece.type)) {
            validation = rook_Validation(piece, input_coord, x_coord);
        }
        // if the chess piece is a queen
        if ("Queen".equals(piece.type)) {
            boolean val1 = bishop_Validation(piece, input_coord, x_coord);
            boolean val2 = rook_Validation(piece, input_coord, x_coord);
            if (val1 == true || val2 == true) {
                validation = true;
            }
        }
        // if the chess piece is a king
        if ("King".equals(piece.type)) {
            validation = king_Validation(piece, input_coord, x_coord);
        }
        // if the validation variable is True
        if (validation == true) {
            System.out.println(piece.type + " in team " + piece.team + " at " + piece.x_pos + "," + piece.y_pos
                    + " can move to " + turn_x_to_char(input_coord[0]) + "," + input_coord[1] + ".");
        }
        // otherwise
        else {
            System.out.println(piece.type + " in team " + piece.team + " at " + piece.x_pos + "," + piece.y_pos
                    + " can NOT move to " + turn_x_to_char(input_coord[0]) + "," + input_coord[1] + ".");
        }
        return;
    }

    // IVAN ARMENTA
    // method that validates the new position for the pawn
    public static boolean pawn_Validation(chess_piece piece, int[] coord, int x_coord) {
        // initialize a boolean
        boolean valid = true;
        // if the team of the pawn is white
        if (piece.team.equalsIgnoreCase("white")) {
            // check if the x-coordinate or y-coordinate doesn't match the expected move
            if (x_coord != coord[0] || (piece.y_pos + 1) != coord[1]) {
                valid = false; // set valid to false if the move is not valid
            }
        }
        // if the team of the pawn is black
        if (piece.team.equalsIgnoreCase("black")) {
            // check if the x-coordinate or y-coordinate doesn't match the expected move
            if (piece.x_pos != coord[0] || (piece.y_pos - 1) != coord[1]) {
                // set valid to false if the move is not valid
                valid = false;
            }
        }
        // return the validity of the move
        return valid;
    }

    // Luis Gomez
    // method that validates the new position for the knight
    public static boolean knight_Validation(chess_piece piece, int[] coord, int x_coord) {
        // calculate the absolute differences in x and y coordinates
        int deltax = Math.abs(x_coord - coord[0]);
        int deltay = Math.abs(piece.y_pos - coord[1]);
        // check if the move is a valid L-shape for a knight
        if ((deltax == 1 && deltay == 2) || (deltax == 2 && deltay == 1)) {
            // return true if the move is valid
            return true;
        }
        // return false if the move is not valid
        return false;
    }

    // Luis Gomez
    // method that validates the new position for the bishop
    public static boolean bishop_Validation(chess_piece piece, int[] coord, int x_coord) {
        // calculate the absolute differences in x and y coordinates
        int deltax = Math.abs(x_coord - coord[0]);
        int deltay = Math.abs(piece.y_pos - coord[1]);
        // check if the move is a valid diagonal
        if ((deltax == deltay) && (deltax > 0)) {
            // return true if the move is valid
            return true;
        }
        // return false if the move is not valid
        return false;
    }

    // Luis Gomez
    // method that validates the new position for the rook
    public static boolean rook_Validation(chess_piece piece, int[] coord, int x_coord) {
        // calculate the absolute differences in x and y coordinates
        int deltax = Math.abs(x_coord - coord[0]);
        int deltay = Math.abs(piece.y_pos - coord[1]);
        // check if the move is valid along the y-axis or x-axis
        if (deltax == 0 && deltay != 0) {
            // return true if the move is valid along the y-axis
            return true;
        } else if (deltax != 0 && deltay == 0) {
            // return true if the move is valid along the x-axis
            return true;
        }
        // return false if the move is not valid
        return false;
    }

    // Luis Gomez
    // method that validates the new position for the king
    public static boolean king_Validation(chess_piece piece, int[] coord, int x_coord) {
        // calculate the absolute differences in x and y coordinates
        int deltax = Math.abs(x_coord - coord[0]);
        int deltay = Math.abs(piece.y_pos - coord[1]);
        // check if the move is valid for a king (1 square in any direction)
        if ((deltax == 1) && (deltay == 0) || (deltax == 0) && (deltay == 1) || (deltax == 1) && (deltay == 1)) {
            // return true if the move is valid
            return true;
        }
        // return false if the move is not valid
        return false;
    }

}

