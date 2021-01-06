import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

abstract class ChessPiece {
    int points;
    boolean pieceColor;
    Square currentPos;
    Player owner;
    char code;
    String representation;
    Image[] images;

    public ChessPiece()
    {

    }
    public ChessPiece(Square startingPos, boolean color, int value, char code,String representation, Player owner)
    {
        pieceColor=color;
        currentPos=startingPos;
        points=value;
        this.code=code;
        this.representation=representation;
        this.owner = owner;
        try
        {
            images=determineImage(representation,color);
        }catch(Exception ex)
        {

        }
    }
    Square[][] move(Square[][] t,int newRow, int newCol,ArrayList<int[]>posMoves){
//        System.out.println("moving");
        Square[][] backupT = t;
        try {
            for (int i = 0; i < posMoves.size(); i++) {
                if (posMoves.get(i)[0] == newRow && posMoves.get(i)[1] == newCol) {
//                    System.out.println("found it");
                    if(t[newRow][newCol].hasAPiece())
                    {
                        t[newRow][newCol].pieces[0].destroy(owner);
                    }
                    t[newRow][newCol].pieces[0] = this;
                    t[this.currentPos.row][this.currentPos.column].pieces = new ChessPiece[2];
                    currentPos = t[newRow][newCol];
                }
            }
        }catch(Exception e)
        {
            //idk lol but i imagine something will eventually go here lmao
            System.out.println("exception in move");
            return backupT;
        }
        for(ChessPiece p: owner.pieces)
        {
            if(p instanceof Pawn)
            {
                ((Pawn) p).enPassantAble = false;
            }
        }
        return t;
    }
    abstract ArrayList<int[]> possibleMoves(Square[][] t);
    public void takePiece(ChessPiece theDeadOne, int row, int col){
        owner.score+=theDeadOne.points;
    }

    public int destroy(Player attacker)
    {

        owner.removePiece(this.currentPos.row,this.currentPos.column);
        return 0; //change this

    }
    public void changeCurrentPosSquare(Square s)
    {
        currentPos = s;
    }

    public Image[] determineImage(String representation, boolean pieceColor) throws IOException
    {
        FileInputStream[] file=new FileInputStream[4];
        if(pieceColor)
        {
            switch (representation.toLowerCase())
            {
                case "pn":
                     file[0]=new FileInputStream("C:\\Users\\alex\\Documents\\GitHub\\FogOfWarChess\\src\\chessImages\\badPawnWhite.jpg");
                    break;
                case "bs":
                    file[0]=new FileInputStream("C:\\Users\\alex\\Documents\\GitHub\\FogOfWarChess\\src\\chessImages\\badBishopWhite.jpg");
                    break;
                case "kt":
                    file[0]=new FileInputStream("C:\\Users\\alex\\Documents\\GitHub\\FogOfWarChess\\src\\chessImages\\badKnightWhite.jpg");
                    break;
                case "rk":
                    file[0]=new FileInputStream("C:\\Users\\alex\\Documents\\GitHub\\FogOfWarChess\\src\\chessImages\\badRookWhite.jpg");
                    break;
                case "qn":
                    file[0]=new FileInputStream("C:\\Users\\alex\\Documents\\GitHub\\FogOfWarChess\\src\\chessImages\\badQueenWhite.jpg");
                    break;
                case "kn":
                    file[0]=new FileInputStream("C:\\Users\\alex\\Documents\\GitHub\\FogOfWarChess\\src\\chessImages\\badKingWhite.jpg");
                    break;
                default:
                    throw(new IllegalArgumentException("i dont know what image sad face"));

            }
        }else
        {
            switch (representation.toLowerCase())
            {
                case "pn":
                    file[0]=new FileInputStream("C:\\Users\\alex\\Documents\\GitHub\\FogOfWarChess\\src\\chessImages\\badPawnBlack.jpg");
                    break;
                case "bs":
                    file[0]=new FileInputStream("C:\\Users\\alex\\Documents\\GitHub\\FogOfWarChess\\src\\chessImages\\badBishopBlack.jpg");
                    break;
                case "kt":
                    file[0]=new FileInputStream("C:\\Users\\alex\\Documents\\GitHub\\FogOfWarChess\\src\\chessImages\\badKnightBlack.jpg");
                    break;
                case "rk":
                    file[0]=new FileInputStream("C:\\Users\\alex\\Documents\\GitHub\\FogOfWarChess\\src\\chessImages\\badRookBlack.jpg");
                    break;
                case "qn":
                    file[0]=new FileInputStream("C:\\Users\\alex\\Documents\\GitHub\\FogOfWarChess\\src\\chessImages\\badQueenBlack.jpg");
                    break;
                case "kn":
                    file[0]=new FileInputStream("C:\\Users\\alex\\Documents\\GitHub\\FogOfWarChess\\src\\chessImages\\badKingBlack.jpg");
                    break;
                default:
                    throw(new IllegalArgumentException("i dont know what image sad face"));
            }
        }
        return new Image[]{new Image(file[0])};
    }




}
