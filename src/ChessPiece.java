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
    public ChessPiece(Square startingPos, boolean color, int value, char code,String representation, Player owner)
    {
        pieceColor=color;
        currentPos=startingPos;
        points=value;
        this.code=code;
        this.representation=representation;
        this.owner = owner;
    }
    Square[][] move(Square[][] t,int newRow, int newCol,ArrayList<int[]>posMoves){
        System.out.println("moving");
        Square[][] backupT = t;
        try {
            for (int i = 0; i < posMoves.size(); i++) {
                if (posMoves.get(i)[0] == newRow && posMoves.get(i)[1] == newCol) {
                    System.out.println("found it");
                    t[newRow][newCol].pieces[0] = this;
                    t[this.currentPos.row][this.currentPos.column].pieces = new ChessPiece[2];
                    currentPos = t[newRow][newCol];
                }
            }
        }catch(Exception e)
        {
            //idk lol but i imagine something will eventually go here lmao
            return backupT;
        }
        for(ChessPiece p: owner.pieces)
        {
            if(p instanceof Pawn)
            {
                ((Pawn) p).enPassantAble = false;
            }
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
