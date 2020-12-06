import java.util.ArrayList;

abstract class ChessPiece {
    int points;
    boolean pieceColor;
    Square currentPos;
    Player owner;
    char code;
    String representation;

    public ChessPiece()
    {

    }
    public ChessPiece(Square startingPos, boolean color, int value, char code,String representation)
    {
        pieceColor=color;
        currentPos=startingPos;
        points=value;
        this.code=code;
        this.representation=representation;
    }
    Square[][] move(Square[][] t,int newRow, int newCol,ArrayList<int[]>posMoves){
        Square[][] backupT = t;
        try {
            for (int i = 0; i < posMoves.size(); i++) {
                if (posMoves.get(i)[0] == newRow && posMoves.get(i)[1] == newCol) {
                    if (posMoves.get(i)[2] == 1) {
                        takePiece(t[newRow][newCol].pieces[0], newRow, newCol);
                    }
                    t[newRow][newCol].pieces[0] = this;
                    t[this.currentPos.row][this.currentPos.column].pieces[0] = null;
                    currentPos = t[newRow][newCol];
                }
            }
        }catch(Exception e)
        {
            //idk lol but i imagine something will eventually go here lmao
            return backupT;
        }
        return t;
    }
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
