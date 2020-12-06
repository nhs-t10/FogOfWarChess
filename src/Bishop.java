import java.util.ArrayList;

public class Bishop extends ChessPiece{
    Player owner;
    public Bishop(Square startingPos, boolean color, char code)
    {
        super(startingPos, color,3,code,"bs");


    }
    public ArrayList<int[]> possibleMoves(Square[][]t)//this is very ugly
    {
        ArrayList<int[]> posMoves = new ArrayList<>();
        int row = this.currentPos.row;
        int col = this.currentPos.column;
        for(int i = 0; row+i < 8; i++)
        {
            for(int j = 0; col+j<8;j++)
            {
                if(t[row+i][col+j].hasAPiece())
                {
                    if(t[row+i][col+j].pieces[0].pieceColor!=this.pieceColor)
                    {
                        posMoves.add(new int[]{row+i,col+j});
                    }
                    break;
                }
                posMoves.add(new int[]{row+i,col+j});
            }
            for(int j = 0; col+j >=0;j--)
            {
                if(t[row+i][col+j].hasAPiece())
                {
                    if(t[row+i][col+j].pieces[0].pieceColor!=this.pieceColor)
                    {
                        posMoves.add(new int[]{row+i,col+j});
                    }
                    break;
                }
                posMoves.add(new int[]{row+i,col+j});
            }
        }
        for(int i = 0; row+i >= 0; i--)
        {
            for(int j = 0; col+j<8;j++)
            {
                if(t[row+i][col+j].hasAPiece())
                {
                    if(t[row+i][col+j].pieces[0].pieceColor!=this.pieceColor)
                    {
                        posMoves.add(new int[]{row+i,col+j});
                    }
                    break;
                }
                posMoves.add(new int[]{row+i,col+j});
            }
            for(int j = 0; col+j >=0;j--)
            {
                if(t[row+i][col+j].hasAPiece())
                {
                    if(t[row+i][col+j].pieces[0].pieceColor!=this.pieceColor)
                    {
                        posMoves.add(new int[]{row+i,col+j});
                    }
                    break;
                }
                posMoves.add(new int[]{row+i,col+j});
            }
        }
        return posMoves;
    }
}
