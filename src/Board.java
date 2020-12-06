import java.util.Map;

public class Board {
    boolean usingUI;
    String[] stringBoard;
    public Square[][]tiles;
    Player p1,p2;
    public Board(Boolean ui)//0 is white btw but it doesn't really matter lol
    {
        usingUI=ui;
        p1 = new Player(true,this);
        p2 = new Player(false,this);

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
        String[] chessBoard = new String[]{
                "chess"+"\n",
                "    1     2     3     4     5     6     7     8"+"\n",
                "  _____ _____ _____ _____ _____ _____ _____ _____"+"\n",
                "A|     |     |     |     |     |     |     |     |" +"\n",
                " |_____|_____|_____|_____|_____|_____|_____|_____|" +"\n",
                "B|     |     |     |     |     |     |     |     |" +"\n",
                " |_____|_____|_____|_____|_____|_____|_____|_____|" +"\n",
                "C|     |     |     |     |     |     |     |     |" +"\n",
                " |_____|_____|_____|_____|_____|_____|_____|_____|" +"\n",
                "D|     |     |     |     |     |     |     |     |" +"\n",
                " |_____|_____|_____|_____|_____|_____|_____|_____|" +"\n",
                "E|     |     |     |     |     |     |     |     |" +"\n",
                " |_____|_____|_____|_____|_____|_____|_____|_____|" +"\n",
                "F|     |     |     |     |     |     |     |     |" +"\n",
                " |_____|_____|_____|_____|_____|_____|_____|_____|" +"\n",
                "G|     |     |     |     |     |     |     |     |" +"\n",
                " |_____|_____|_____|_____|_____|_____|_____|_____|" +"\n",
                "H|     |     |     |     |     |     |     |     |" +"\n",
                " |_____|_____|_____|_____|_____|_____|_____|_____|" +"\n",

        };
        //¡ is pawn, █ is rook, i is bishop, Г is knight, K is king, Q is queen
    }
    public Square[][] getTiles()
    {
        return tiles;
    }
    public String sendBoardConsole()
    {


        int[] insertableRows = new int[]{3,5,7,9,11,13,15,17};
        for(int i = 0; i < 8; i++)
        {
            for(int j = 0; j < 8; j++)
            {
                if(tiles[i][j].hasAPiece())
                {//god i hate doing char stuff but here we are... again :(
                    stringBoard[3+i]=stringBoard[3+i].substring(0,4+6*j)+tiles[i][j].pieces[0].code+stringBoard[3+i].substring(5+6*j);
                }
            }
        }
        //¡ is pawn, █ is rook, i is bishop, Г is knight, K is king, Q is queen
        String s= "";
        for(String ss:stringBoard)
        {
            s+=ss;
        }
        return s;
    }

}
