import java.util.Arrays;
import java.util.Scanner;

public class NumberedPiece extends DecoratedPiece{
    //Class for all the normal pieces

    int level;
    int[][] moves = new int[18][2];
    boolean updatedMoves = false;

    public NumberedPiece(Piece p, int level){
        super(p);
        this.level = level;
    }

    @Override
    public char getVal(){
        //Display value
        return (char)(level + '0');
    }

    @Override
    public String move(Board board){
        String event = "Moved " + this.getVal() + " to ";
        Scanner input = new Scanner(System.in);
        if (updatedMoves == true){
            System.out.println("Where would you like to move this piece?");
            int moveNum = 0;
            
            while (moves[moveNum][0] != 0 && moves[moveNum][1] != 0){
                System.out.println((moveNum+1) + ": " + moves[moveNum][0] + ", " + moves[moveNum][1]);
                moveNum++;
            }

            int moveChoice = input.nextInt();
            //Pick a valid move
            while (moveChoice < 1 || moveChoice > moveNum){
                System.out.println("Please enter a valid move number.");
                moveChoice = input.nextInt();
            }
            int newX = moves[moveChoice-1][0];
            int newY = moves[moveChoice-1][1];
            event += newX + ", " + newY;
            //Move the piece on the board
            event += board.move(this, newX, newY);
        }
        
        //Reset the moves
        for (int i = 0; i < 18; i++){
            Arrays.fill(moves[i], 0);
        }
        updatedMoves = false;
        return event;
    }

    @Override
    public boolean canMove(Board board, int curX, int curY){
        //Check if the piece can move and update the moves array

        boolean canMove = false;
        updatedMoves = true;
        int moveNum = 0;
        if (this.level != 9){
            //For each direction check that the location is valid, the location is not a lake, and the location is empty or has an enemy piece
            if (curX+1 < 10 && !board.isLakeLoc(curX+1, curY) && (board.at(curX+1, curY) == null || board.at(curX+1, curY).getColor() != this.getColor())){
                moves[moveNum][0] = curX+1;
                moves[moveNum][1] = curY;
                moveNum++;
                canMove = true;
            }
            if (curX-1 > 0 && !board.isLakeLoc(curX-1, curY) && (board.at(curX-1, curY) == null || board.at(curX-1, curY).getColor() != this.getColor())){
                moves[moveNum][0] = curX-1;
                moves[moveNum][1] = curY;
                moveNum++;
                canMove = true;
            }
            if (curY+1 < 10 && !board.isLakeLoc(curX, curY+1) && (board.at(curX, curY+1) == null || board.at(curX, curY+1).getColor() != this.getColor())){
                moves[moveNum][0] = curX;
                moves[moveNum][1] = curY+1;
                moveNum++;
                canMove = true;
            }
            if (curY-1 > 0 && !board.isLakeLoc(curX, curY-1) && (board.at(curX, curY-1) == null || board.at(curX, curY-1).getColor() != this.getColor())){
                moves[moveNum][0] = curX;
                moves[moveNum][1] = curY-1;
                moveNum++;
                canMove = true;
            }
        }else{
            //The 9 can move any number of spaces in any direction
            //For each direction check that the location is valid, the location is not a lake, and the location is empty or has an enemy piece
            int i = 1;
            while (curX+i < 10 && !board.isLakeLoc(curX+i, curY) && ((board.at(curX+i, curY) == null || board.at(curX+i, curY).getColor() != this.getColor()))){
                moves[moveNum][0] = curX+i;
                moves[moveNum][1] = curY;
                moveNum++;
                canMove = true;
                if (board.at(curX+i, curY) != null){
                    break;
                }
                i++;
            }
            i = 1;
            while (curX-i > 0 && !board.isLakeLoc(curX-i, curY) && ((board.at(curX-i, curY) == null || board.at(curX-i, curY).getColor() != this.getColor()))){
                moves[moveNum][0] = curX-i;
                moves[moveNum][1] = curY;
                moveNum++;
                canMove = true;
                if (board.at(curX-i, curY) != null){
                    break;
                }
                i++;
            }
            i = 1;
            while (curY+i < 10 && !board.isLakeLoc(curX, curY+i) && ((board.at(curX, curY+i) == null || board.at(curX, curY+i).getColor() != this.getColor()))){
                moves[moveNum][0] = curX;
                moves[moveNum][1] = curY+i;
                moveNum++;
                canMove = true;
                if (board.at(curX, curY+i) != null){
                    break;
                }
                i++;
            }
            i = 1;
            while (curY-i > 0 && !board.isLakeLoc(curX, curY-i) && ((board.at(curX, curY-i) == null || board.at(curX, curY-i).getColor() != this.getColor()))){
                moves[moveNum][0] = curX;
                moves[moveNum][1] = curY-i;
                moveNum++;
                canMove = true;
                if (board.at(curX, curY-i) != null){
                    break;
                }
                i++;
            }
        }
        return canMove;
    }

    @Override
    public int attack(Piece other){
        char otherVal = other.getVal();
        //Allow players to see pieces that have been in combat
        other.discover();
        this.discover();
        if (other instanceof NumberedPiece){
            int otherLevel = other.getVal() - '0';
            if (this.level == otherLevel){
                return 0;
            }else if (this.level < otherLevel){
                return 1;
            }else{
                return -1;
            }
        }
        if (otherVal == 'F'){
            return 2;
        }else if (otherVal == 'B'){
            if (this.level == 8){
                return 1;
            }
            return -1;
        }else if (otherVal == 'S'){
            return 1;
        }else{
            System.out.println("How did you even get here?");
            return -3;
        }
    }

    public int getX(){
        return piece.getX();
    }

    public int getY(){
        return piece.getY();
    }

    public void setPos(int x, int y){
        piece.setPos(x, y);
    }
}
