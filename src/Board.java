public class Board {
    public Square[][]tiles;
    Player p1,p2;
    public Board()//0 is white btw but it doesn't really matter lol
    {
        p1 = new Player(true);
        p2 = new Player(false);

        Square[][] tiles = new Square[8][8];
        for(int i = 0; i < 8; i++)
        {
            for(int j = 0; j<8; j++)
            {
                tiles[i][j]=new Square(8-i,j+'A',((i*8+j)%2)==0,new ChessPiece[]{});
            }
        }
        this.tiles = tiles;
    }
    public void setBoard()
    {
          tiles = p1.placePieces(tiles);
          tiles = p2.placePieces(tiles);
    }
    public Square[][] getTiles()
    {
        return tiles;
    }

}
