import javafx.scene.image.Image;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

abstract class ChessPiece {
    protected final int points;
    protected final boolean pieceColor;
    protected final Player owner;
    protected final char code;
    protected final String representation;
    protected final Image[] images;

    protected Square currentPos;

    public ChessPiece(Square startingPos, boolean color, int value, char code, String representation, Player owner) {
        this.representation = representation;
        this.currentPos = startingPos;
        this.pieceColor = color;
        this.points = value;
        this.code = code;
        this.owner = owner;
        this.images = getImages(color, representation);
    }

    Square[][] move(Square[][] t, int newRow, int newCol, List<Coordinate> posMoves) {
        Square[][] backupT = t;
        try {
            for (int i = 0; i < posMoves.size(); i++) {
                if (posMoves.get(i).row == newRow && posMoves.get(i).col == newCol) {
                    if (t[newRow][newCol].hasAPiece()) {
                        t[newRow][newCol].pieces[0].destroy(owner);
                    }
                    t[newRow][newCol].pieces[0] = this;
                    t[this.currentPos.row][this.currentPos.column].pieces = new ChessPiece[2];
                    currentPos = t[newRow][newCol];
                }
            }
        } catch (Exception e) {
            //idk lol but i imagine something will eventually go here lmao
            System.out.println("exception in move");
            e.printStackTrace();
            return backupT;
        }
        for (ChessPiece p : owner.pieces) {
            if (p instanceof Pawn) {
                ((Pawn) p).enPassantAble = false;
            }
        }
        return t;
    }

    abstract List<Coordinate> possibleMoves(Square[][] t);

    public void takePiece(ChessPiece theDeadOne, int row, int col) {
        owner.score += theDeadOne.points;
    }

    public int destroy(Player attacker) {
        owner.removePiece(this.currentPos.row, this.currentPos.column);
        return 0; //change this
    }

    public static Image[] determineImage(String representation, boolean pieceColor) throws IOException {
        String color = (pieceColor) ? "white" : "black";
        String figureName = figureName(representation);
        String imageFileName = "images/" + color + "_" + figureName + ".png";


        InputStream is = ChessPiece.class.getClassLoader().getResourceAsStream(imageFileName);
        if (is == null)
            throw new IOException("Can't find image " + imageFileName);

        return new Image[]{
                new Image(is, 75, 75, true, true)
        };
    }

    private static String figureName(String representation) {
        switch (representation.toLowerCase()) {
            case "pn":
                return "pawn";
            case "bs":
                return "bishop";
            case "kt":
                return "knight";
            case "rk":
                return "rook";
            case "qn":
                return "queen";
            case "kn":
                return "king";
            default:
                throw (new IllegalArgumentException("i don't know what image sad face"));
        }
    }

    public String getColor() {
        return (pieceColor) ? "WHITE" : "BLACK";
    }

    public String toString() {
        return getColor() + " " + representation.toUpperCase();
    }


    private static Image[] getImages(boolean color, String representation) {
        try {
            return determineImage(representation, color);
        } catch (Exception ex) {
            ex.printStackTrace();
            System.err.println("Can't find image: " + representation);
            return null;
        }
    }

}
