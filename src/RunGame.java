public class RunGame {

    public static void main(String[]args)
    {
        Board chessBoard = new Board(false);
        Player me = new Player(true,chessBoard);
        Player notMe = new Player(false,chessBoard);
        chessBoard.setBoard();
        chessBoard.setPlayers(me,notMe);
        me.placePieces(chessBoard.getTiles());
        notMe.placePieces(chessBoard.getTiles());
        chessBoard.sendBoardConsole();
    }
}
