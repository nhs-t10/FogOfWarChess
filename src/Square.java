import java.util.ArrayList;

public class Square {
    int row, column;
    boolean color,isVisible;
    ChessPiece[] pieces;
    public Square(int row, int column, boolean color)
    {
        this.pieces= new ChessPiece[2];
        this.row = row;
        this.column=column;
        this.color=color;
    }
    public void addPiece(ChessPiece p,Player player)
    {
        if(!hasAPiece())
        {
            pieces[0]=p;
        }else
        {
            if(pieces[0].pieceColor==player.color)
            {
                //restart turn here
            }else
            {
                //take here
            }

        }
    }
    public void removePiece()
    {
        if(!hasAPiece())
        {
            throw new IllegalArgumentException("there are no pieces on this square: "+row+","+(char)column);
        }else
        {
            pieces[0]=null;
        }
    }

    public boolean hasAPiece()
    {
        return this.pieces.length>0&&this.pieces[0]!=null;
    }
}
