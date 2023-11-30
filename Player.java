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
            maxY = 4;
            minY = 1;
        }else{
            maxY = 10;
            minY = 7;
        }
        boolean confirmedDone = false;
        while (piecesLeft != 0 && confirmedDone == false){
            b.print(color);
            System.out.println("What piece would you like to place?");
            System.out.println("f: flag");
            System.out.println("b: bomb");
            System.out.println("s: spy");
            System.out.println("1-9: numbered piece");
            System.out.println("r: randomize remaining pieces");
            System.out.println("p: remove a piece");
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
                            b.removePiece(x, y, true);
                            String oldPieceName = pieceSelector.get(Character.toLowerCase(current.getVal()));
                            availablePieces.put(pieceName, availablePieces.get(pieceName) - 1);
                            availablePieces.put(oldPieceName, availablePieces.get(oldPieceName) + 1);
                            b.addPiece(piece, x, y);
                        }else{
                            System.out.println("You have not placed your " + pieceName + " yet.");
                        }
                    }
                }else{
                    System.out.println("You have no more " + pieceName + "s left.");
                }
            }else{
                if (piece == 'r'){
                    System.out.println("Are you sure you want to randomize your remaining pieces? (y/n)");
                    char randomize = input.next().charAt(0);
                    if (randomize == 'y'){
                        randomPlace(b, availablePieces);
                        piecesLeft = 0;
                    }else{
                        System.out.println("Returning to piece selection.");
                    }
                }else if (piece == 'p'){
                    if (piecesLeft == 40){
                        System.out.println("You have not placed any pieces yet.");
                        continue;
                    }
                    System.out.println("Where would you like to remove a piece?");
                    int x = input.nextInt();
                    int y = input.nextInt();
                    while (x < 1 || x > 10 || y < minY || y > maxY){
                        System.out.println("Invalid location. Please try again.");
                        x = input.nextInt();
                        y = input.nextInt();
                    }
                    Piece current = b.at(x, y);
                    if (current instanceof GenericPiece){
                        System.out.println("There is no piece there.");
                    }else{
                        String oldPieceName = pieceSelector.get(Character.toLowerCase(current.getVal()));
                        availablePieces.put(oldPieceName, availablePieces.get(oldPieceName) + 1);
                        b.removePiece(x, y, true);
                        piecesLeft++;
                    }
                }
                piecesLeft = 0;
                for (String pieceName : availablePieces.keySet()){
                    piecesLeft += availablePieces.get(pieceName);
                }
            }
            if (piecesLeft == 0){
                b.print(color);
                System.out.println("Are you sure you are done placing your pieces? (y/n)");
                char done = input.next().charAt(0);
                if (done == 'y'){
                    confirmedDone = true;
                }else{
                    piecesLeft = -1;
                }
            }
        }
        
    }

    public void randomPlace(Board b, HashMap<String, Integer> availablePieces){
        if (availablePieces == null){
            availablePieces = new HashMap<String, Integer>();
            availablePieces.put("flag", 1);
            availablePieces.put("bomb", 6);
            availablePieces.put("spy", 1);
            availablePieces.put("scout", 8);
            availablePieces.put("miner", 5);
            availablePieces.put("sergeant", 4);
            availablePieces.put("lieutenant", 4);
            availablePieces.put("captain", 4);
            availablePieces.put("major", 3);
            availablePieces.put("colonel", 2);
            availablePieces.put("general", 1);
            availablePieces.put("marshal", 1);
        }
        //HashMap<String, Integer> availablePieces = new HashMap<String, Integer>();
        HashMap<Character, String> pieceSelector = new HashMap<Character, String>();
        pieceSelector.put('f', "flag");
        pieceSelector.put('b', "bomb");
        pieceSelector.put('s', "spy");
        pieceSelector.put('9', "scout");
        pieceSelector.put('8', "miner");
        pieceSelector.put('7', "sergeant");
        pieceSelector.put('6', "lieutenant");
        pieceSelector.put('5', "captain");
        pieceSelector.put('4', "major");
        pieceSelector.put('3', "colonel");
        pieceSelector.put('2', "general");
        pieceSelector.put('1', "marshal");

        int piecesLeft = 0;
        for (String pieceName : availablePieces.keySet()){
            piecesLeft += availablePieces.get(pieceName);
        }
        int maxY = 10;
        int minY = 1;
        if (color == 'r'){
            maxY = 4;
            minY = 1;
        }else{
            maxY = 10;
            minY = 7;
        }
        int currentX = 1;
        int currentY = minY;
        while (piecesLeft != 0){
            char piece = '0';
            while (piece == '0'){
                int rand = (int)(Math.random() * 40);
                if (rand == 0){
                    piece = 'f';
                }else if (rand >= 1 && rand <= 6){
                    piece = 'b';
                }else if (rand == 7){
                    piece = 's';
                }else if (rand >= 8 && rand <= 15){
                    piece = '9';
                }else if (rand >= 16 && rand <= 20){
                    piece = '8';
                }else if (rand >= 21 && rand <= 24){
                    piece = '7';
                }else if (rand >= 25 && rand <= 28){
                    piece = '6';
                }else if (rand >= 29 && rand <= 32){
                    piece = '5';
                }else if (rand >= 33 && rand <= 35){
                    piece = '4';
                }else if (rand >= 36 && rand <= 37){
                    piece = '3';
                }else if (rand == 38){
                    piece = '2';
                }else if (rand == 39){
                    piece = '1';
                }
                String pieceName = pieceSelector.get(piece);
                if (availablePieces.get(pieceName) == 0){
                    piece = '0';
                }
            }
            if (b.at(currentX, currentY) instanceof GenericPiece){
                piecesLeft--;
                b.addPiece(piece, currentX, currentY);
                availablePieces.put(pieceSelector.get(piece), availablePieces.get(pieceSelector.get(piece)) - 1);
            }
            currentX++;
            if (currentX == 11){
                currentX = 1;
                currentY++;
            }
        }
    }

    public String turn(Board b){
        String event = color + ": ";

        Scanner input = new Scanner(System.in);

        int x = 0;
        int y = 0;
        
        Piece pickedPiece = null;
        while(pickedPiece == null){
            System.out.println("Which piece would u like to move? (Enter location)");
            x = input.nextInt();
            y = input.nextInt();
    
            pickedPiece = b.at(x, y);
            b.removePiece(x, y, false);

            if(pickedPiece.getColor() != color){
                System.out.println("You must pick your own piece.");
                pickedPiece = null;
            }
        }

        event += "Moved " + pickedPiece.getVal();
        
        Piece attackPiece = pickedPiece;
        while(attackPiece != null && attackPiece.getColor() == color){
            System.out.println("Where would u like to move it?");
            x = input.nextInt();
            y = input.nextInt();

            attackPiece = b.at(x, y);
            if(attackPiece == null) break;

            if(attackPiece.getColor() == color){
                System.out.println("You cannot attack your own pieces.");
                attackPiece = null;
            }
        }

        if(attackPiece == null) {
            event += " to the pos " + x + "," + y;
            b.setPiece(x, y, pickedPiece);
        } else {
            event += " and attacked " + attackPiece.getVal();
        }

        return event;
    }
}
