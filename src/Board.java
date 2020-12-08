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
                tiles[i][j]=new Square(i,j,((i*8+j)%2)==0);
            }
        }
        this.tiles = tiles;
    }
    public void changeTiles(Square[][]t)
    {
        tiles=t;
    }
    public void testingPurposes()
    {
        for(int i = 0; i < 8;i++)
        {
            tiles[0][i].pieces=new ChessPiece[2];
        }
    }
    public void setPlayers(Player white, Player black)
    {
        p1=white;
        p2=black;
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
        stringBoard=chessBoard;
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
                char addMe;
                if(tiles[i][j].hasAPiece())
                {
                    addMe= tiles[i][j].pieces[0].code;
                }else
                {
                    addMe=' ';
                }
                stringBoard[3+2*i]=stringBoard[3+2*i].substring(0,4+6*j)+addMe+stringBoard[3+2*i].substring(5+6*j);

            }
        }
        //¡ is pawn, █ is rook, i is bishop, Г is knight, K is king, Q is queen
        String s= "";
        for(String ss:stringBoard)
        {
            s+=ss;
        }
        System.out.println(s);
        return s;
    }

}
