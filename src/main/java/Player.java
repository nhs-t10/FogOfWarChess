import java.util.ArrayList;
import java.util.Scanner;

public class Player {
    int score;
    boolean color;
    BoardObj board;
    ArrayList<ChessPiece> pieces = new ArrayList<>();
    public Player(boolean color, BoardObj b)//true is white
    {
        score = 0;
        board=b;
        this.color=color;
    }

    public void removePiece(int deadRow, int deadCol)
    {
        for(ChessPiece p: pieces)
        {
            if(deadRow==p.currentPos.row&&deadCol==p.currentPos.column)
            {
                pieces.remove(p);
            }
        }
    }

    public Square[][] placePieces(Square[][] t)
    {
        if(color){
            t[0][0].addPiece(new Rook(t[0][0],color,'█',this),this);
            t[0][1].addPiece(new Knight(t[0][1],color, 'Г',this),this);
            t[0][2].addPiece(new Bishop(t[0][2],color, 'i',this),this);
            t[0][3].addPiece(new King(t[0][3],color, 'K',this),this);
            t[0][4].addPiece(new Queen(t[0][4],color,'Q',this),this);
            t[0][5].addPiece(new Bishop(t[0][5],color, 'i',this),this);
            t[0][6].addPiece(new Knight(t[0][6],color, 'Г',this),this);
            t[0][7].addPiece(new Rook(t[0][7],color,'█',this),this);

            for(int i = 0; i < 8; i++)
            {
                t[1][i].addPiece(new Pawn(t[1][i],color, '¡',this),this);
                pieces.add(t[0][i].pieces[0]);
                pieces.add(t[1][i].pieces[0]);
            }
        }else
        {
            t[7][0].addPiece(new Rook(t[7][0],color,'█',this),this);
            t[7][1].addPiece(new Knight(t[7][1],color,'Г',this),this);
            t[7][2].addPiece(new Bishop(t[7][2],color,'i',this),this);
            t[7][3].addPiece(new King(t[7][3],color,'K',this),this);
            t[7][4].addPiece(new Queen(t[7][4],color,'Q',this),this);
            t[7][5].addPiece(new Bishop(t[7][5],color,'i',this),this);
            t[7][6].addPiece(new Knight(t[7][6],color,'Г',this),this);
            t[7][7].addPiece(new Rook(t[7][7],color,'█',this),this);

            for(int i = 0; i < 8; i++)
            {
                t[6][i].addPiece(new Pawn(t[6][i],color,'¡',this),this);
                pieces.add(t[6][i].pieces[0]);
                pieces.add(t[7][i].pieces[0]);
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
    public Square[][] makeMoveText() {//in here it blocks illegal moves :)

        Scanner s = new Scanner(System.in);
        String in = s.nextLine();
//        System.out.println();
//        System.out.println(in);
//        System.out.println(in.charAt(1));
        int[]currentPos = new int[]{(int)(in.charAt(0))-(int)('a'),(int)(in.charAt(1)-'1')};
        int[]futurePos = new int[]{(int)(in.charAt(2))-(int)('a'),(int)(in.charAt(3)-'1')};
        System.out.println(currentPos[0]+", "+(int)currentPos[1]);
//        System.out.println(board.getTiles()[currentPos[0]][currentPos[1]].pieces[0].currentPos.column);
        Square[][]t=board.getTiles();
        if(!(board.getTiles()[currentPos[0]][currentPos[1]].hasAPiece()&&board.getTiles()[currentPos[0]][currentPos[1]].pieces[0].pieceColor==color))
        {
            throw new IllegalArgumentException("you either don't own this piece or there is no piece here. bub.bububububububub.");
        }

        try{
            t = (board.getTiles()[currentPos[0]][currentPos[1]].pieces[0]).move(board.getTiles(),futurePos[0],futurePos[1],board.getTiles()[currentPos[0]][currentPos[1]].pieces[0].possibleMoves(board.getTiles()));
            board.tiles=t;
//            System.out.println("updated t?");
        }catch(IllegalArgumentException ex)
        {
            ex.printStackTrace();
            System.out.println("thats not a move DINGUS DINGUS DINGUS DINGUS DINGUS DINGUS DINGUS");
        }
        System.out.println("that's a correct mvoe but pronbably a bad one AHAHAHAHXH >:D");
        return t;
    }


}
