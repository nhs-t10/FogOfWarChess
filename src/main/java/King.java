import javax.management.remote.SubjectDelegationPermission;
import java.util.ArrayList;

public class King extends ChessPiece {
    public Boolean canCastle = true;
    public King(Square startingPos, boolean color, char code,Player owner)
    {
        super(startingPos,color,1000,code,"kn",owner);
    }
    public ArrayList<int[]> possibleMoves(Square[][]t){//why is this so ugly why did I do this oh the humanity oh the pain
        ArrayList<int[]> posMoves = new ArrayList<>();
        int row = this.currentPos.row;
        int col = this.currentPos.column;
        posMoves.add(new int[]{row+1,col});
        posMoves.add(new int[]{row+1,col+1});
        posMoves.add(new int[]{row,col+1});
        posMoves.add(new int[]{row-1,col+1});
        posMoves.add(new int[]{row-1,col});
        posMoves.add(new int[]{row-1,col-1});
        posMoves.add(new int[]{row,col-1});
        posMoves.add(new int[]{row+1,col-1});
        posMoves.removeIf(i -> i[0] < 0 || i[0] > 7 || i[1] < 0 || i[1] > 7);
        return posMoves;
    }

    public Square[][] castle(Square[][]t, int kingRow, int kingCol,int rookRow, int rookCol)
    {
        if(!t[rookRow][rookCol].hasAPiece()||!(t[rookRow][rookCol].pieces[0] instanceof Rook)||!t[kingRow][kingCol].hasAPiece()||!(t[kingRow][kingCol].pieces[0] instanceof King))
            throw new IllegalArgumentException("what the frick u dingus u cant castle without a rook and or a king like wtf holy shit HOLY SHIT SIBAAAAAAL OH MY GOD OH MY GOD WHY WOULD YOU DO THIS HOW COULD YOU DO THIS YOU ARE WAWFUL IM LITERALLYT CRYING AND IM A FUCKING COMPUTER YOU THINK I CAN CRY ACTUALLY CLEARLY YOU MUST BECAUSE YOU ARE SO DUMB THAT YOU DONT KNOW YOU CNA T CASTLE WITHOUT A ROOK AND OR KING WEIRD CHAMP HA GET WEIRD CHAMPED");
        t[rookRow][rookCol].pieces[1]=t[rookRow][rookCol].pieces[0];
        t[rookRow][rookCol].pieces[0]=t[kingRow][kingCol].pieces[0];
        t[kingRow][kingCol].pieces[0]=t[rookRow][rookCol].pieces[1];
        t[rookRow][rookCol].pieces[1]=null;


        return t;
    }

    @Override
    Square[][] move(Square[][] t, int newRow, int newCol, ArrayList<int[]> posMoves) {

        try{
            if(canCastle&&(t[newRow][newCol].hasAPiece()&&(t[newRow][newCol].pieces[0].points==5&&t[newRow][newCol].pieces[0].pieceColor==this.pieceColor)))
            {
                if(t[newRow][newCol].pieces[0] instanceof Rook)
                {
                    if(((Rook) t[newRow][newCol].pieces[0]).canCastle)
                    {
                        for(int[] i:posMoves)
                        {
                            if(newRow==i[0]&&newCol==i[1])
                            {
                                return castle(t,this.currentPos.row,this.currentPos.column,newRow,newCol);
                            }
                        }
                    }
                }
            }
            canCastle=false;
        }catch(IllegalArgumentException ex)
        {
            //ahhh we got an exception AHhadhahsdahdhadhashdahsdahsdhasdhasdhahsdahshdagsdhasdhashdhashdhhasdhasdhadhashdahsdhhahahah im scared please ahahahshdahhahahahahahshahaaahaa
        }
        return super.move(t, newRow, newCol, posMoves);
    }
}
