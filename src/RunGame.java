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
        while(!isGameOver(me)&&!isGameOver(notMe))
        {
            chessBoard.changeTiles(recursiveMove(me,chessBoard));
            chessBoard.changeTiles(recursiveMove(notMe,chessBoard));
        }

        chessBoard.sendBoardConsole();
    }
    public static boolean isGameOver(Player p)
    {
        for(ChessPiece pee: p.pieces)
        {
            if(pee instanceof King)
            {
                System.out.println(p.color +" still has a king?");
                return false;
            }
        }
        System.out.println("----------no king found---------------");
        return true;
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
