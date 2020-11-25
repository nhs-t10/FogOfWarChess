import javax.management.remote.SubjectDelegationPermission;
import java.util.ArrayList;

public class King extends ChessPiece {
    public Boolean canCastle = true;
    public King()
    {

    }
    public ArrayList<int[]> possibleMoves(Square[][]t){//why is this so ugly why did I do this oh the humanity oh the pain
        ArrayList<int[]> posMoves = new ArrayList<>();
        int row = this.currentPos.row;
        int col = this.currentPos.column;

        if(row+1<8&&t[row+1][col].hasAPiece())
        {
            if(t[row+1][col].pieces[0].pieceColor!=this.pieceColor)
            {
                posMoves.add(new int[]{row+1,col});
            }
        }else{
            posMoves.add(new int[]{row+1,col});
        }

        if(row+1<8&&col+1<8&&t[row+1][col+1].hasAPiece())
        {
            if(t[row+1][col+1].pieces[0].pieceColor!=this.pieceColor)
            {
                posMoves.add(new int[]{row+1,col+1});
            }
        }else{
            posMoves.add(new int[]{row+1,col+1});
        }

        if(col+1<8&&t[row][col+1].hasAPiece())
        {
            if(t[row][col+1].pieces[0].pieceColor!=this.pieceColor)
            {
                posMoves.add(new int[]{row,col+1});
            }
        }else{
            posMoves.add(new int[]{row,col+1});
        }

        if(row!=0&&col+1<8&&t[row-1][col+1].hasAPiece())
        {
            if(t[row-1][col+1].pieces[0].pieceColor!=this.pieceColor)
            {
                posMoves.add(new int[]{row-1,col+1});
            }
        }else{
            posMoves.add(new int[]{row-1,col+1});
        }

        if(row!=0&&t[row-1][col].hasAPiece())
        {
            if(t[row-1][col].pieces[0].pieceColor!=this.pieceColor)
            {
                posMoves.add(new int[]{row-1,col});
            }
        }else{
            posMoves.add(new int[]{row-1,col});
        }

        if(row!=0&&col!=0&&t[row-1][col-1].hasAPiece())
        {
            if(t[row-1][col-1].pieces[0].pieceColor!=this.pieceColor)
            {
                posMoves.add(new int[]{row-1,col-1});
            }
        }else{
            posMoves.add(new int[]{row-1,col-1});
        }

        if(col!=0&&t[row][col-1].hasAPiece())
        {
            if(t[row][col-1].pieces[0].pieceColor!=this.pieceColor)
            {
                posMoves.add(new int[]{row,col-1});
            }
        }else{
            posMoves.add(new int[]{row,col-1});
        }

        if(col!=0&&row+1<8&&t[row+1][col-1].hasAPiece())
        {
            if(t[row+1][col-1].pieces[0].pieceColor!=this.pieceColor)
            {
                posMoves.add(new int[]{row+1,col-1});
            }
        }else{
            posMoves.add(new int[]{row+1,col-1});
        }

        return posMoves;
    }
    public Square[][] move(Square[][] t, int newRow, int newCol, ArrayList<int[]>posMoves)
    {
        return null;
    }

}
