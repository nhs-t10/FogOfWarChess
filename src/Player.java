public class Player {
    int score;
    boolean color;
    public Player(boolean color)//true is white
    {
        score = 0;
        this.color=color;
    }

    public Square[][] placePieces(Square[][] t)
    {
        if(color){
            t[0][0].addPiece(new Rook(),this);
            t[0][1].addPiece(new Knight(),this);
            t[0][2].addPiece(new Bishop(),this);
            t[0][3].addPiece(new Queen(),this);
            t[0][4].addPiece(new King(),this);
            t[0][5].addPiece(new Bishop(),this);
            t[0][6].addPiece(new Knight(),this);
            t[0][7].addPiece(new Rook(),this);

            for(int i = 0; i < 8; i++)
            {
                t[1][i].addPiece(new Pawn(),this);
            }
        }else
        {
            t[8][0].addPiece(new Rook(),this);
            t[8][1].addPiece(new Knight(),this);
            t[8][2].addPiece(new Bishop(),this);
            t[8][3].addPiece(new King(),this);
            t[8][4].addPiece(new Queen(),this);
            t[8][5].addPiece(new Bishop(),this);
            t[8][6].addPiece(new Knight(),this);
            t[8][7].addPiece(new Rook(),this);

            for(int i = 0; i < 8; i++)
            {
                t[7][i].addPiece(new Pawn(),this);
            }
        }
        return t;
    }

}
