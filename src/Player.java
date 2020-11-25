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
            t[0][0].addPiece(new Rook(t[0][0],color,5),this);
            t[0][1].addPiece(new Knight(t[0][1],color,3),this);
            t[0][2].addPiece(new Bishop(t[0][2],color,3),this);
            t[0][3].addPiece(new Queen(t[0][3],color,9),this);
            t[0][4].addPiece(new King(t[0][4],color,1000),this);
            t[0][5].addPiece(new Bishop(t[0][5],color,3),this);
            t[0][6].addPiece(new Knight(t[0][6],color,3),this);
            t[0][7].addPiece(new Rook(t[0][7],color,5),this);

            for(int i = 0; i < 8; i++)
            {
                t[1][i].addPiece(new Pawn(t[1][i],color,1),this);
            }
        }else
        {
            t[8][0].addPiece(new Rook(t[8][0],color,5),this);
            t[8][1].addPiece(new Knight(t[8][1],color,3),this);
            t[8][2].addPiece(new Bishop(t[8][2],color,3),this);
            t[8][3].addPiece(new King(t[8][3],color,1000),this);
            t[8][4].addPiece(new Queen(t[8][4],color,9),this);
            t[8][5].addPiece(new Bishop(t[8][5],color,3),this);
            t[8][6].addPiece(new Knight(t[8][6],color,3),this);
            t[8][7].addPiece(new Rook(t[8][7],color,5),this);

            for(int i = 0; i < 8; i++)
            {
                t[7][i].addPiece(new Pawn(t[7][i],color,1),this);
            }
        }
        return t;
    }

    public void calculateVisibleSquares()
    {

    }

}
