import java.util.ArrayList;

public class Bishop extends ChessPiece{
    Player owner;
    public Bishop(Square startingPos, boolean color, char code,Player owner)
    {
        super(startingPos, color,3,code,"bs",owner);


    }
    public ArrayList<int[]> possibleMoves(Square[][]t)//this is very ugly
    {
        ArrayList<int[]> posMoves = new ArrayList<>();
        int row = this.currentPos.row;
        int col = this.currentPos.column;

        for(int i =1; i < 8;i++)
        {
            if(row+i<8&&col+i<8&&!t[row+i][col+i].hasAPiece())
            {
                posMoves.add(new int[]{row+i,col+i});
            }else
            {
                if(row+i<8&&col+i<8&&t[row+i][col+i].hasAPiece()&&t[row+i][col+i].pieces[0].pieceColor!=this.pieceColor)
                {
                    posMoves.add(new int[]{row+i,col+i});
                }
                break;
            }
        }
        for(int i =1; i < 8;i++)
        {
            if(row+i<8&&col-i>-1&&!t[row+i][col-i].hasAPiece())
            {
                posMoves.add(new int[]{row+i,col-i});
            }else
            {
                if(row+i<8&&col-i>-1&&t[row+i][col-i].hasAPiece()&&t[row+i][col-i].pieces[0].pieceColor!=this.pieceColor)
                {
                    posMoves.add(new int[]{row+i,col-i});
                }
                break;
            }
        }
        for(int i =1; i < 8;i++)
        {
            if(row-i>-1&&col+i<8&&!t[row-i][col+i].hasAPiece())
            {
                posMoves.add(new int[]{row-i,col+i});
            }else
            {
                if(row-i>-1&&col+i<8&&t[row-i][col+i].hasAPiece()&&t[row-i][col+i].pieces[0].pieceColor!=this.pieceColor)
                {
                    posMoves.add(new int[]{row-i,col+i});
                }
                break;
            }
        }
        for(int i =1; i < 8;i++)
        {
            if(row-i>-1&&col-i>-1&&!t[row-i][col-i].hasAPiece())
            {
                posMoves.add(new int[]{row-i,col-i});
            }else
            {
                if(row-i>-1&&col-i>-1&&t[row-i][col-i].hasAPiece()&&t[row-i][col-i].pieces[0].pieceColor!=this.pieceColor)
                {
                    posMoves.add(new int[]{row-i,col-i});
                }
                break;
            }
        }

        System.out.println("posmoves"+posMoves.size());
        for(int[]i: posMoves)
        {
            System.out.println(i[0]+", "+i[1]+" <- those are the moves I found omegalul");
        }
        return posMoves;
    }
}
