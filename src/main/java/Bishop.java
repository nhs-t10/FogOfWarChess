import java.util.ArrayList;
import java.util.List;

public class Bishop extends ChessPiece {

    public Bishop(Square startingPos, boolean color, char code, Player owner) {
        super(startingPos, color, 3, code, "bs", owner);
    }

    public List<Coordinate> possibleMoves(Square[][] t)//this is very ugly
    {
        List<Coordinate> posMoves = new ArrayList<>();
        int row = this.currentPos.row;
        int col = this.currentPos.column;

        for (int i = 1; i < 8; i++) {
            if (row + i < 8 && col + i < 8 && !t[row + i][col + i].hasAPiece()) {
                posMoves.add(new Coordinate(row + i, col + i));
            } else {
                if (row + i < 8 && col + i < 8 && t[row + i][col + i].hasAPiece() && t[row + i][col + i].pieces[0].pieceColor != this.pieceColor) {
                    posMoves.add(new Coordinate(row + i, col + i));
                }
                break;
            }
        }
        for (int i = 1; i < 8; i++) {
            if (row + i < 8 && col - i > -1 && !t[row + i][col - i].hasAPiece()) {
                posMoves.add(new Coordinate(row + i, col - i));
            } else {
                if (row + i < 8 && col - i > -1 && t[row + i][col - i].hasAPiece() && t[row + i][col - i].pieces[0].pieceColor != this.pieceColor) {
                    posMoves.add(new Coordinate(row + i, col - i));
                }
                break;
            }
        }
        for (int i = 1; i < 8; i++) {
            if (row - i > -1 && col + i < 8 && !t[row - i][col + i].hasAPiece()) {
                posMoves.add(new Coordinate(row - i, col + i));
            } else {
                if (row - i > -1 && col + i < 8 && t[row - i][col + i].hasAPiece() && t[row - i][col + i].pieces[0].pieceColor != this.pieceColor) {
                    posMoves.add(new Coordinate(row - i, col + i));
                }
                break;
            }
        }
        for (int i = 1; i < 8; i++) {
            if (row - i > -1 && col - i > -1 && !t[row - i][col - i].hasAPiece()) {
                posMoves.add(new Coordinate(row - i, col - i));
            } else {
                if (row - i > -1 && col - i > -1 && t[row - i][col - i].hasAPiece() && t[row - i][col - i].pieces[0].pieceColor != this.pieceColor) {
                    posMoves.add(new Coordinate(row - i, col - i));
                }
                break;
            }
        }

        System.out.println("posmoves" + posMoves.size());
        for (Coordinate c : posMoves) {
            System.out.println(c + " <- those are the moves I found omegalul");
        }
        return posMoves;
    }
}
