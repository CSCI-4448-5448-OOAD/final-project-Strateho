import java.util.Arrays;

public class NumberedPiece extends DecoratedPiece{
    int level;
    int[][] moves = new int[18][2];
    boolean updatedMoves = false;

    public NumberedPiece(Piece p, int level){
        super(p);
        this.level = level;
    }

    @Override
    public char getVal(){
        return (char)(level + '0');
    }

    @Override
    public void move(Board board){
        //piece.move(board);

        Arrays.fill(moves, null);
    }

    @Override
    public boolean canMove(Board board, int curX, int curY){
        boolean canMove = false;
        updatedMoves = true;
        int moveNum = 0;
        if (this.level != 9){
            if (board.at(curX+1, curY) == null || board.at(curX+1, curY).getColor() != this.getColor()){
                moves[moveNum][0] = curX+1;
                moves[moveNum][1] = curY;
                moveNum++;
                canMove = true;
            }
            if (board.at(curX-1, curY) == null || board.at(curX-1, curY).getColor() != this.getColor()){
                moves[moveNum][0] = curX-1;
                moves[moveNum][1] = curY;
                moveNum++;
                canMove = true;
            }
            if (board.at(curX, curY+1) == null || board.at(curX, curY+1).getColor() != this.getColor()){
                moves[moveNum][0] = curX;
                moves[moveNum][1] = curY+1;
                moveNum++;
                canMove =  true;
            }
            if (board.at(curX, curY-1) == null || board.at(curX, curY-1).getColor() != this.getColor()){
                moves[moveNum][0] = curX;
                moves[moveNum][1] = curY-1;
                moveNum++;
                canMove = true;
            }
        }else{
            int i = 0;
            while (curX+i < 10 && (board.at(curX+i, curY) == null || board.at(curX+i, curY).getColor() != this.getColor())){
                moves[moveNum][0] = curX+i;
                moves[moveNum][1] = curY;
                moveNum++;
                canMove = true;
                i++;
            }
            i = 0;
            while (curX-i > 0 && (board.at(curX-i, curY) == null || board.at(curX-i, curY).getColor() != this.getColor())){
                moves[moveNum][0] = curX-i;
                moves[moveNum][1] = curY;
                moveNum++;
                canMove = true;
                i++;
            }
            i = 0;
            while (curY+i < 10 && (board.at(curX, curY+i) == null || board.at(curX, curY+i).getColor() != this.getColor())){
                moves[moveNum][0] = curX;
                moves[moveNum][1] = curY+i;
                moveNum++;
                canMove = true;
                i++;
            }
            i = 0;
            while (curY-i > 0 && (board.at(curX, curY-i) == null || board.at(curX, curY-i).getColor() != this.getColor())){
                moves[moveNum][0] = curX;
                moves[moveNum][1] = curY-i;
                moveNum++;
                canMove = true;
                i++;
            }
        }
        if (!canMove){
            System.out.println("There is no where for this piece to move!");
        }
        return canMove;
    }
}
