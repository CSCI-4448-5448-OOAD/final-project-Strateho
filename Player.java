import java.util.*;
import java.io.*;

public class Player {
    private Piece[] pieces;
    private char color;

    public Player(char color){
        this.color = color;
    }

    public void givePieces(Piece[] pieces){
        this.pieces = pieces;
    }

    public void playerPlace(Board b){
        HashMap<String, Integer> availablePieces = new HashMap<String, Integer>();
        HashMap<Character, String> pieceSelector = new HashMap<Character, String>();
        availablePieces.put("flag", 1);
        pieceSelector.put('f', "flag");
        availablePieces.put("bomb", 6);
        pieceSelector.put('b', "bomb");
        availablePieces.put("spy", 1);
        pieceSelector.put('s', "spy");
        availablePieces.put("scout", 8);
        pieceSelector.put('9', "scout");
        availablePieces.put("miner", 5);
        pieceSelector.put('8', "miner");
        availablePieces.put("sergeant", 4);
        pieceSelector.put('7', "sergeant");
        availablePieces.put("lieutenant", 4);
        pieceSelector.put('6', "lieutenant");
        availablePieces.put("captain", 4);
        pieceSelector.put('5', "captain");
        availablePieces.put("major", 3);
        pieceSelector.put('4', "major");
        availablePieces.put("colonel", 2);
        pieceSelector.put('3', "colonel");
        availablePieces.put("general", 1);
        pieceSelector.put('2', "general");
        availablePieces.put("marshal", 1);
        pieceSelector.put('1', "marshal");

        int piecesLeft = 40;
        Scanner input = new Scanner(System.in);
        int maxY = 10;
        int minY = 1;
        if (color == 'r'){
            maxY = 10;
            minY = 7;
        }else{
            maxY = 4;
            minY = 1;
        }
        while (piecesLeft != 0){
            System.out.println("What piece would you like to place? (f, b, s, 1-9)");
            char piece = input.next().charAt(0);
            if (pieceSelector.containsKey(piece)){
                String pieceName = pieceSelector.get(piece);
                if (availablePieces.get(pieceName) != 0){
                    System.out.println("Where would you like to place your " + pieceName + "?");
                    int x = input.nextInt();
                    int y = input.nextInt();
                    while (x < 1 || x > 10 || y < minY || y > maxY){
                        System.out.println("Invalid location. Please try again.");
                        x = input.nextInt();
                        y = input.nextInt();
                    }
                    Piece current = b.at(x, y);
                    if (current instanceof GenericPiece){
                        b.addPiece(piece, x, y);
                        availablePieces.put(pieceName, availablePieces.get(pieceName) - 1);
                        piecesLeft--;
                    }else{
                        System.out.println("There is already a piece there. Would you like to remove it? (y/n)");
                        char remove = input.next().charAt(0);
                        if (remove == 'y'){
                            b.removePiece(x, y);
                            String oldPieceName = pieceSelector.get(current.getVal());
                            availablePieces.put(pieceName, availablePieces.get(pieceName) - 1);
                            availablePieces.put(oldPieceName, availablePieces.get(oldPieceName) + 1);
                        }else{
                            System.out.println("You have not placed your " + pieceName + " yet.");
                        }
                    }
                }else{
                    System.out.println("You have no more " + pieceName + "s left.");
                }
            }
        }
    }

    public String turn(Board b){
        String event = "add functionality";
        return event;
    }
}
