import java.util.Scanner;
import java.util.Random;
import java.util.ArrayList;

public class TicTacToe {

    static ArrayList<String> validMoves = new ArrayList<String>();
    static char[] board = {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '};
    static boolean didSomeoneWin = false;
    static String winnerWinnerChickenDinner = "no one";
    static char userChar = ' ';
    static char compChar = ' ';

    public static void main (String args[]) {

        System.out.println("Let's play tic-tac-toe");
        System.out.println("Do you wanna be X or O? (Valid inputs: x, o, random)");

        Scanner sc = new Scanner(System.in);
        String userChoice = sc.next();
        sc.nextLine();
        String userNextMove;
        String compNextMove;

        validMoves.add("top left");
        validMoves.add("top center");
        validMoves.add("top right");
        validMoves.add("left center");
        validMoves.add("center");
        validMoves.add("right center");
        validMoves.add("bottom left");
        validMoves.add("bottom center");
        validMoves.add("bottom right");


        Random rand = new Random();
        if (userChoice.toLowerCase().equals("x")) {
            System.out.println("Ok, you're X then");
            userChar = 'X';
            compChar = 'O';
        } else if (userChoice.toLowerCase().equals("o")) {
            System.out.println("Ok, you're O then");
            userChar = 'O';
            compChar = 'X';
        } else if (userChoice.toLowerCase().equals("random")) {
            System.out.println("Ok, picking randomly...");
            switch (rand.nextInt(0, 2)) {
                case 0:
                System.out.println("The fates have assigned you: X");
                userChar = 'X';
                compChar = 'O';
                break;
                default:
                System.out.println("The fates have assigned you: O");
                userChar = 'O';
                compChar = 'X';
                
            }
        } else {
            System.out.println("You didn't enter a valid option");
            System.exit(0);
        }

            //uh oh...
        sc.useDelimiter("\n");
        if (userChar == 'X') {

            while (!didSomeoneWin) {

                guideForUser();
    
                userNextMove = sc.nextLine().trim();
                addAndRemove(userNextMove, userChar);
                renderBoard();
    
                    //check if someone won or draw
                winLogic(board);
                ifBoardFull();
    
                System.out.println("Ok... my turn!");
                compNextMove = validMoves.get(rand.nextInt(0, validMoves.size()));
                addAndRemove(compNextMove, compChar);
                renderBoard();
    
                    //check if someone won or draw
                winLogic(board);
                ifBoardFull();
    
            }

        } else {

            while (!didSomeoneWin) {

                System.out.println("Here's my move!");
                compNextMove = validMoves.get(rand.nextInt(0, validMoves.size()));
                addAndRemove(compNextMove, compChar);
                renderBoard();

                    //check if someone won or draw
                winLogic(board);
                ifBoardFull();

                guideForUser();

                userNextMove = sc.nextLine().trim();
                addAndRemove(userNextMove, userChar);
                renderBoard();
    
                    //check if someone won or draw
                winLogic(board);
                ifBoardFull();
    
            }

        }

        sc.close();


    }

    private static void addAndRemove(String move, char whoseChar) {

        if (!(validMoves.contains(move))) {
            System.out.println("That move isn't available.");
            return;
        }

        switch (move) {
            case "top left":
            board[0] = whoseChar;
            validMoves.remove("top left");
            break;
            case "top center":
            board[1] = whoseChar;
            validMoves.remove("top center");
            break;
            case "top right":
            board[2] = whoseChar;
            validMoves.remove("top right");
            break;
            case "left center":
            board[3] = whoseChar;
            validMoves.remove("left center");
            break;
            case "center":
            board[4] = whoseChar;
            validMoves.remove("center");
            break;
            case "right center":
            board[5] = whoseChar;
            validMoves.remove("right center");
            break;
            case "bottom left":
            board[6] = whoseChar;
            validMoves.remove("bottom left");
            break;
            case "bottom center":
            board[7] = whoseChar;
            validMoves.remove("bottom center");
            break;
            case "bottom right":
            board[8] = whoseChar;
            validMoves.remove("bottom right");
            break;
            default:
            System.out.println("Something went wrong, check boardRenderer()");

        }
    }

    private static void renderBoard() {
        System.out.println(board[0] + " | " + board[1] + " | " + board[2]);
        System.out.println("---------");
        System.out.println(board[3] + " | " + board[4] + " | " + board[5]);
        System.out.println("---------");
        System.out.println(board[6] + " | " + board[7] + " | " + board[8]);
        System.out.println();
    }

    private static void winLogic(char[] currentBoard) {
        for (int i = 0; i < 7; i+=3) {
            if (board[i] == board[i + 1] && board[i + 1] == board[i + 2] && Character.isLetter(board[i])) {
                whenSomeoneWins(board[i]);
            }
        }
        for (int i = 0; i < 3; ++i) {
            if (board[i] == board[i + 3] && board[i + 3] == board[i + 6] && Character.isLetter(board[i])) {
                whenSomeoneWins(board[i]);
            }
        }

        if (board[0] == board[4] && board[4] == board[8] && Character.isLetter(board[4])) {
            whenSomeoneWins(board[4]);
        } else if (board[2] == board[4] && board[4] == board[6] && Character.isLetter(board[4])) {
            whenSomeoneWins(board[4]);
        } else {
            return;
        }

        
    }
        // helper for whenSomeoneWins() ...use to find winner
    private static void whoTheFuckWon(char chara) {
        if (chara == userChar) {
            winnerWinnerChickenDinner = "You";
        } else {
            winnerWinnerChickenDinner = "I";
        }
    }

        // helper for winLogic() ... use when theres a winner 
    private static void whenSomeoneWins(char chara) {
        didSomeoneWin = true;
        whoTheFuckWon(chara);
        System.out.println(winnerWinnerChickenDinner + " won!!!");
        if (winnerWinnerChickenDinner.equals("You")) {
            renderBoard();
        }
        System.exit(0);
    }

        //check for draw
    private static void ifBoardFull() {
        boolean itsFull = true;

        for (char c : board) {
            if (c == ' ') {
                itsFull = false;
                break;
            }
        }

        if (itsFull && !didSomeoneWin) {
            System.out.println("So it's a draw then... Good game.");
            System.exit(0);
        }
        
    }

    private static void guideForUser() {

        System.out.println("Your turn!");
        System.out.print("What's your move? (Valid inputs: " + validMoves.get(0));

        for (int i = 1; i < validMoves.size(); ++i) {
            System.out.print(", " + validMoves.get(i));
        }
        System.out.println();
        
    }

}
