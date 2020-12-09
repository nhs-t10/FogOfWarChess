public class RunGame {

    public static void main(String[]args)
    {
        Board chessBoard = new Board(false);
        Player me = new Player(true,chessBoard);
        Player notMe = new Player(false,chessBoard);
        chessBoard.setBoard();
        chessBoard.setPlayers(me,notMe);
        chessBoard.changeTiles(me.placePieces(chessBoard.getTiles()));
        chessBoard.changeTiles(notMe.placePieces(chessBoard.getTiles()));

//        chessBoard.testingPurposes();
//        for(Square[] s: chessBoard.getTiles())
//        {
//            for(Square ss: s)
//            {
//                System.out.print(ss.hasAPiece());
//            }
//            System.out.println();
//        }
        while(isGameOver(chessBoard.getTiles()))
        {
            chessBoard.changeTiles(recursiveMove(me,chessBoard));
            chessBoard.changeTiles(recursiveMove(notMe,chessBoard));
        }
        
    }
    public static boolean isGameOver(Square t[][])
    {
        int i = 0;
        for(int j = 0; j < 8; j++)
        {
            for(int k = 0; k < 8; k++)
            {
                if(t[j][k].hasAPiece()&&t[j][k].pieces[0] instanceof King)
                {
                    i++;
                }
            }
        }
        return i==2;
    }
    public static Square[][] recursiveMove(Player p, Board b) {
        Board backup = b;
        b.sendBoardConsole();
        try {
            b.changeTiles(p.makeMoveText());
        } catch (Exception ex) {
            System.out.println("not a legal move");
            recursiveMove(p,backup);
        }
        return b.getTiles();
    }
}
