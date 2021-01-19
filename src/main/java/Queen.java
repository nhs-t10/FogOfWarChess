import java.util.ArrayList;
import java.util.List;

public class Queen extends ChessPiece {

    public Queen(Square startingPos, boolean color, char code, Player owner) {
        super(startingPos, color, 9, code, "qn", owner);
    }

    public Square[][] move(Square[][] t, int newRow, int newCol) {
        return null;
    }


    public List<Coordinate> possibleMoves(Square[][] t)//this one is like way uglier than the others
    {
        List<Coordinate> posMoves = new ArrayList<>();
        int row = this.currentPos.row;
        int col = this.currentPos.column;

        for (int i = 1; i + row < 8; i++) {
            if (t[row + i][col].hasAPiece()) {
                if (t[row + i][col].pieces[0].pieceColor != this.pieceColor) {
                    posMoves.add(new Coordinate(row + i, col));
                }
                break;
            }
            posMoves.add(new Coordinate(row + i, col));
        }
        for (int i = -1; i + row >= 0; i--) {
            if (t[row + i][col].hasAPiece()) {
                if (t[row + i][col].pieces[0].pieceColor != this.pieceColor) {
                    posMoves.add(new Coordinate(row + i, col));
                }
                break;
            }
            posMoves.add(new Coordinate(row + i, col));
        }
        for (int i = 1; i + col < 8; i++) {
            if (t[row][col + i].hasAPiece()) {
                if (t[row][col + i].pieces[0].pieceColor != this.pieceColor) {
                    posMoves.add(new Coordinate(row, col + i));
                }
                break;
            }
            posMoves.add(new Coordinate(row, col + i));
        }
        for (int i = -1; i + col >= 0; i--) {
            if (t[row][col + i].hasAPiece()) {
                if (t[row][col + i].pieces[0].pieceColor != this.pieceColor) {
                    posMoves.add(new Coordinate(row, col + i));
                }
                break;
            }
            posMoves.add(new Coordinate(row, col + i));
        }
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
        return posMoves;
    }
}
