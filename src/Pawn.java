import java.util.ArrayList;

public class Pawn extends ChessPiece{
    boolean firstMove;
    boolean enPassantAble;
    int direction, changeRow;
    public Pawn(Square startingPos, boolean color)
    {
        super(startingPos,color,1,'¡',"pn");
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
        if(!(this.currentPos.row==7&&direction==1)&&!(this.currentPos.row==0&&direction==-1))
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
        if((this.currentPos.row==4&&direction==1)||(this.currentPos.row==3&&direction==-1))
        {
            if(this.currentPos.column!=0&&!t[this.currentPos.row+direction][this.currentPos.column-1].hasAPiece()&&(t[this.currentPos.row][this.currentPos.column-1].hasAPiece()&&t[this.currentPos.row][this.currentPos.column-1].pieces[0].pieceColor!=this.pieceColor&&(t[this.currentPos.row][this.currentPos.column-1].pieces[0] instanceof Pawn&&((Pawn)t[this.currentPos.row][this.currentPos.column-1].pieces[0]).enPassantAble)))
            {
                posMoves.add(new int[]{this.currentPos.row+direction,this.currentPos.column-1});
            }else if(this.currentPos.column!=7&&!t[this.currentPos.row+direction][this.currentPos.column+1].hasAPiece()&&(t[this.currentPos.row][this.currentPos.column+1].hasAPiece()&&t[this.currentPos.row][this.currentPos.column+1].pieces[0].pieceColor!=this.pieceColor&&(t[this.currentPos.row][this.currentPos.column+1].pieces[0] instanceof Pawn&&((Pawn)t[this.currentPos.row][this.currentPos.column+1].pieces[0]).enPassantAble)))
            {
                posMoves.add(new int[]{this.currentPos.row+direction,this.currentPos.column+1});
            }
        }
        return posMoves;
    }

    public Square[][] changePiece(Square[][]t)
    {
        return t;//change piece here
    }
    @Override
    public Square[][] move(Square[][] t, int newRow, int newCol, ArrayList<int[]>posMoves)//may need some way to claim that move is impossible?
    {

        enPassantAble=false;
        try{
            if(this.currentPos.row==4&&newRow==5&&t[this.currentPos.row][newCol].hasAPiece()&&t[this.currentPos.row][newCol].pieces[0] instanceof Pawn&&((Pawn)t[this.currentPos.row][newCol].pieces[0]).enPassantAble&&!t[newRow][newCol].hasAPiece()){
                //trigger en passant omegalul
            }
            if(this.currentPos.row==3&&direction==1&&newRow==3)
            {

            }

            if(((this.currentPos.row==6&&direction==1)||(this.currentPos.row==0&&direction==-1)))
            {

                return changePiece(t);
            }

        }catch(IllegalArgumentException ex)
        {
            enPassantAble=false;
        }

        return super.move(t,newRow,newCol,posMoves);

    }

}
