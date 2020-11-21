import java.util.ArrayList;

public class Bishop extends ChessPiece{
    Player owner;
    public Bishop()
    {
        super();
    }
    public ArrayList<int[]> possibleMoves(Square[][]t)
    {
            ArrayList<int[]> posMoves = new ArrayList<>();
            return posMoves;
    }

    public Square[][] move(Square[][] t, int newRow, int newCol, ArrayList<int[]> posMoves)
    {
        Square[][] backup = t;
        if(Math.abs(newRow-this.currentPos.row)==Math.abs(newCol -this.currentPos.column))
        {
            for(int i = 1; i < Math.abs(this.currentPos.row-newRow);i++)
            {
                if(t[this.currentPos.row][this.currentPos.column].hasAPiece())
                {
                    return backup;//illegal move!
                }
            }
            try{
                t[newRow][newCol].addPiece(this,this.owner);
                t[this.currentPos.row][this.currentPos.column].removePiece();
                this.changeCurrentPosSquare(t[newRow][newCol]);
                return t;
            }catch(Exception e)
            {
                return backup;
            }
        }
        //illegal move!

        return backup;

    }
}
