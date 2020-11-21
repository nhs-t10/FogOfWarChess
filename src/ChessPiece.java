import java.util.ArrayList;

abstract class ChessPiece {
    int points;
    boolean pieceColor;
    Square currentPos;
    Player owner;
    public ChessPiece()
    {

    }
    public ChessPiece(Square startingPos, boolean color, int value)
    {
        pieceColor=color;
        currentPos=startingPos;
        points=value;
    }
    abstract Square[][] move(Square[][] t,int newRow, int newCol,ArrayList<int[]>posMoves);
    abstract ArrayList<int[]> possibleMoves(Square[][] t);
    public void takePiece(ChessPiece theDeadOne, int row, int col){
        owner.score+=theDeadOne.points;
    }

    public int destroy(Player attacker)
    {
        return 0; //change this
    }
    public void changeCurrentPosSquare(Square s)
    {
        currentPos = s;
    }





}
