import java.util.ArrayList;
import java.util.Scanner;

public class Player {
    int score;
    boolean color;
    Board board;
    ArrayList<ChessPiece> pieces = new ArrayList<>();
    public Player(boolean color, Board b)//true is white
    {
        score = 0;
        board=b;
        this.color=color;
    }

    public Square[][] placePieces(Square[][] t)
    {
        if(color){
            t[0][0].addPiece(new Rook(t[0][0],color),this);
            t[0][1].addPiece(new Knight(t[0][1],color),this);
            t[0][2].addPiece(new Bishop(t[0][2],color),this);
            t[0][3].addPiece(new Queen(t[0][3],color),this);
            t[0][4].addPiece(new King(t[0][4],color),this);
            t[0][5].addPiece(new Bishop(t[0][5],color),this);
            t[0][6].addPiece(new Knight(t[0][6],color),this);
            t[0][7].addPiece(new Rook(t[0][7],color),this);

            for(int i = 0; i < 8; i++)
            {
                t[1][i].addPiece(new Pawn(t[1][i],color),this);
                pieces.add(t[0][i].pieces[0]);
                pieces.add(t[1][i].pieces[0]);
            }
        }else
        {
            t[8][0].addPiece(new Rook(t[8][0],color),this);
            t[8][1].addPiece(new Knight(t[8][1],color),this);
            t[8][2].addPiece(new Bishop(t[8][2],color),this);
            t[8][3].addPiece(new King(t[8][3],color),this);
            t[8][4].addPiece(new Queen(t[8][4],color),this);
            t[8][5].addPiece(new Bishop(t[8][5],color),this);
            t[8][6].addPiece(new Knight(t[8][6],color),this);
            t[8][7].addPiece(new Rook(t[8][7],color),this);

            for(int i = 0; i < 8; i++)
            {
                t[7][i].addPiece(new Pawn(t[7][i],color),this);
                pieces.add(t[7][i].pieces[0]);
                pieces.add(t[8][i].pieces[0]);
            }
        }
        return t;
    }

    public void calculateVisibleSquares()
    {
        Boolean[][] canSeeSquare = new Boolean[8][8];
        for(ChessPiece p:pieces)
        {
            for(int[] moves: p.possibleMoves(board.getTiles()))
            {
                if(!canSeeSquare[moves[0]][moves[1]])
                {
                    canSeeSquare[moves[0]][moves[1]]=true;
                }
            }
        }
        //well we calculated it but not sure what to do with it now that we calculated it
    }


    //input like this:     (square it is)(square it goes to) ex: rkc3c8
    //pawn -> pn, knight -> kt, bishop -> bs, rook -> rk, queen -> qn, king -> kn
    public void makeMoveText() {//in here it blocks illegal moves :)

        Scanner s = new Scanner(System.in);
        String in = s.nextLine();
        int[]currentPos = new int[]{in.charAt(0)-'a',in.charAt(1)-1};
        int[]futurePos = new int[]{in.charAt(2)-'a',in.charAt(3)-1};
        Square[][]t;
        try{
            t = board.getTiles()[currentPos[0]][currentPos[1]].pieces[0].move(board.getTiles(),futurePos[0],futurePos[1],board.getTiles()[currentPos[0]][currentPos[1]].pieces[0].possibleMoves(board.getTiles()));
            board.tiles=t;
        }catch(IllegalArgumentException ex)
        {
            ex.printStackTrace();
            System.out.println("thats not a move DINGUS DINGUS DINGUS DINGUS DINGUS DINGUS DINGUS");
        }
        System.out.println("that's a correct mvoe but pronbably a bad one AHAHAHAHXH >:D");
    }

}
