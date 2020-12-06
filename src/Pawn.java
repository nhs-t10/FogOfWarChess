import java.util.ArrayList;

public class Pawn extends ChessPiece{
    boolean firstMove;
    boolean enPassantAble;
    int direction, changeRow;
    public Pawn(Square startingPos, boolean color, char code)
    {
        super(startingPos,color,1,code,"pn");
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
        if(!(this.currentPos.row==7)&&!(this.currentPos.row==0))
        {
            if(!t[this.currentPos.row+direction][this.currentPos.column].hasAPiece()){
                posMoves.add(new int[]{this.currentPos.row+direction,this.currentPos.column,0});
            }
            if((this.currentPos.row==6&&direction==-1)||(this.currentPos.row==1&&this.direction==1))
            {
                posMoves.add(new int[]{this.currentPos.row+2*direction,this.currentPos.column,0});
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

        }//en passant section run while you still can :)
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
        Square[][] backupT = t;

        try{
            if(t[this.currentPos.row][newCol].hasAPiece()&&t[this.currentPos.row][newCol].pieces[0] instanceof Pawn&&((Pawn)t[this.currentPos.row][newCol].pieces[0]).enPassantAble&&!t[newRow][newCol].hasAPiece()&&t[this.currentPos.row][newCol].pieces[0].pieceColor!=this.pieceColor&&(((this.currentPos.row==4&&newRow==5))||(this.currentPos.row==3&&newRow==2))){//just an apology for anyone who has to read this :)
                t[this.currentPos.row][newCol].pieces[0].destroy(owner);
                t[newRow][newCol].pieces[0]=this;
                t[this.currentPos.row][this.currentPos.column].pieces[0]=null;
                this.currentPos=t[newRow][newCol];
            } else if(((this.currentPos.row==6&&direction==1)||(this.currentPos.row==0&&direction==-1)))
            {

                return changePiece(t);
            } else
            {
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
            }
        }catch(IllegalArgumentException ex)
        {
            enPassantAble=false;
            return backupT;
            //illegal move here????????????????????????
        }


        //move function


        for(ChessPiece p: owner.pieces)
        {
            if(p instanceof Pawn)
            {
                ((Pawn) p).enPassantAble = false;
            }
        }
        if(Math.abs(this.currentPos.row-newRow)==2)
        {
            enPassantAble=true;
        }
        return t;



    }

}
