import java.lang.reflect.Array;
import java.util.ArrayList;

public class Rook extends ChessPiece{
    Boolean canCastle;
    public Rook(Square startingPos, boolean color, int value)
    {
        super(startingPos,color,value);
        canCastle=true;
    }
    public ArrayList<int[]>possibleMoves(Square[][]t)//also very ugly
    {
        ArrayList<int[]> posMoves = new ArrayList<>();
        int row = this.currentPos.row;
        int col = this.currentPos.column;

        for(int i = 0; i+row<8;i++)
        {
            if(t[row+i][col].hasAPiece())
            {
                if(t[row+i][col].pieces[0].pieceColor!=this.pieceColor)
                {
                    posMoves.add(new int[]{row+i,col});
                }
                break;
            }
            posMoves.add(new int[]{row+i,col});
        }
        for(int i = 0; i+row>=0;i--)
        {
            if(t[row+i][col].hasAPiece())
            {
                if(t[row+i][col].pieces[0].pieceColor!=this.pieceColor)
                {
                    posMoves.add(new int[]{row+i,col});
                }
                break;
            }
            posMoves.add(new int[]{row+i,col});
        }
        for(int i = 0; i+col<8;i++)
        {
            if(t[row][col+i].hasAPiece())
            {
                if(t[row][col+i].pieces[0].pieceColor!=this.pieceColor)
                {
                    posMoves.add(new int[]{row,col+i});
                }
                break;
            }
            posMoves.add(new int[]{row,col+i});
        }
        for(int i = 0; i+col>=0;i--)
        {
            if(t[row][col+i].hasAPiece())
            {
                if(t[row][col+i].pieces[0].pieceColor!=this.pieceColor)
                {
                    posMoves.add(new int[]{row,col+i});
                }
                break;
            }
            posMoves.add(new int[]{row,col+i});
        }
        return posMoves;
    }

    public Square[][] move(Square[][] t,int newRow, int newCol,ArrayList<int[]>posMoves)
    {
        for(int i = 0; i<posMoves.size();i++)
        {
            if(posMoves.get(i)[0]==newRow&&posMoves.get(i)[1]==newCol)
            {
                if(posMoves.get(i)[2]==1)
                {
                    takePiece(t[newRow][newCol].pieces[0],newRow,newCol);
                }
                t[newRow][newCol].pieces[0]=this;
                t[this.currentPos.row][this.currentPos.column].pieces[0]=null;
            }
        }
        return t;
    }
}
