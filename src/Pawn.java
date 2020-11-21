import java.util.ArrayList;

public class Pawn extends ChessPiece{
    int direction, changeRow;
    public Pawn()
    {
        super();
        if(super.pieceColor)
        {
            direction=1;
            changeRow=8;

        }else
        {
            direction=-1;
            changeRow=0;
        }
    }
    public ArrayList<int[]> possibleMoves(Square[][] t)
    {
        ArrayList<int[]> posMoves = new ArrayList<>();
        if((this.currentPos.row==7&&direction==1)||(this.currentPos.row==0&&direction==-1))
        {
            if(!t[this.currentPos.row+direction][this.currentPos.column].hasAPiece()){
                posMoves.add(new int[]{this.currentPos.row+direction,this.currentPos.column,0});
            }
            if(this.currentPos.column!=7)
            {
                if(t[this.currentPos.row+direction][this.currentPos.column+1].pieces[0]!=null&&t[this.currentPos.row+direction][this.currentPos.column+1].pieces[0].pieceColor!=this.pieceColor)
                {
                    posMoves.add(new int[]{this.currentPos.row+direction,this.currentPos.column+1,1});
                }
            }
            if(this.currentPos.column!=0)
            {
                if(t[this.currentPos.row+direction][this.currentPos.column-1].pieces[0]!=null&&t[this.currentPos.row+direction][this.currentPos.column-1].pieces[0].pieceColor!=this.pieceColor)
                {
                    posMoves.add(new int[]{this.currentPos.row+direction,this.currentPos.column-1,1});
                }
            }

        }
        return posMoves;

    }

    public Square[][] changePiece(Square[][]t)
    {
        return t;//change piece here
    }
    public Square[][] move(Square[][] t, int newRow, int newCol, ArrayList<int[]>posMoves)//may need some way to claim that move is impossible?
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
                currentPos=t[newRow][newCol];
            }
        }
        if((this.currentPos.row==7&&direction==1)||(this.currentPos.row==0&&direction==-1))
        {
            t=changePiece(t);
        }
        return t;
    }

}
