import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
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
			ex.printStackTrace();
            System.err.println("Can't find image: " + representation);
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
        String color = (pieceColor) ? "white" : "black";
        String figureName = figureName(representation);
        String imageFileName = "images/" + color + "_" + figureName + ".png";


        InputStream is = ChessPiece.class.getClassLoader().getResourceAsStream(imageFileName);
        if (is == null)
            throw new IOException("Can't find image " + imageFileName);

        return new Image[] {
            new Image(is, 75, 75, true, true)
        };
    }

    private static String figureName(String representation) {
        switch (representation.toLowerCase()) {
            case "pn": return "pawn";
            case "bs": return "bishop";
            case "kt": return "knight";
            case "rk": return "rook";
            case "qn": return "queen";
            case "kn": return "king";
            default:
                throw(new IllegalArgumentException("i dont know what image sad face"));
        }
    }

}
