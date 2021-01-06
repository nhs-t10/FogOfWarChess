import java.lang.reflect.Array;
import java.util.ArrayList;

public class Rook extends ChessPiece{
    Boolean canCastle;
    public Rook(Square startingPos, boolean color, char code, Player owner)
    {
        super(startingPos,color,5,code,"rk",owner);
        canCastle=true;
    }
    public ArrayList<int[]>possibleMoves(Square[][]t)//also very ugly
    {
        ArrayList<int[]> posMoves = new ArrayList<>();
        int row = this.currentPos.row;
        int col = this.currentPos.column;

        System.out.println(row+", "+col);

        for(int i = 1; i+row<8;i++)
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
        for(int i = -1; i+row>=0;i--)
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
        for(int i = 1; i+col<8;i++)
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
        for(int i = -1; i+col>=0;i--)
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
        System.out.println(posMoves.size()+"posmoves size");
        return posMoves;
    }

    @Override
    public Square[][] move(Square[][] t,int newRow, int newCol,ArrayList<int[]>posMoves)
    {
        for(int i = 0; i<posMoves.size();i++)
        {
            if(posMoves.get(i)[0]==newRow&&posMoves.get(i)[1]==newCol)
            {

                t[newRow][newCol].pieces[0]=this;
                t[this.currentPos.row][this.currentPos.column].pieces=new ChessPiece[2];
                this.currentPos=t[newRow][newCol];
            }
        }
        return t;
    }
}
