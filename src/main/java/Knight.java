import java.util.ArrayList;
import java.util.List;

public class Knight extends ChessPiece {

    public Knight(Square startingPos, boolean color, char code, Player owner) {
        super(startingPos, color, 3, code, "kt", owner);
    }

    public List<Coordinate> possibleMoves(Square[][] t) {
        int row = this.currentPos.row;
        int col = this.currentPos.column;


        Coordinate[] newPos = new Coordinate[] {
                new Coordinate(row + 2, col + 1),
                new Coordinate(row + 2, col - 1),
                new Coordinate(row + 1, col + 2),
                new Coordinate(row + 1, col - 2),
                new Coordinate(row - 1, col + 2), 
                new Coordinate(row - 1, col - 2), 
                new Coordinate(row - 2, col + 1), 
                new Coordinate(row - 2, col - 1)};
        

        List<Coordinate> theMoves = new ArrayList<>();
        for (Coordinate coordinate : newPos) {
            if (coordinate.row < 8 && coordinate.row > -1 &&
                coordinate.col > -1 && coordinate.col < 8 &&
                    ( !t[coordinate.row][coordinate.col].hasAPiece() ||
                      t[coordinate.row][coordinate.col].hasAPiece() &&
                      t[coordinate.row][coordinate.col].pieces[0].pieceColor != this.pieceColor)) {
                theMoves.add(coordinate);
            }
        }
//        System.out.println("pos moves "+theMoves.size());
//        for(int[]i:theMoves)
//        {
////            System.out.println(i[0]+", "+i[1]);
//        }
        return theMoves;

    }//so we have 3 cases: 6 6 2 2,   7 7 1 1, the cases between
}



/*
 _ _ _ _ _ _ _ _
|_|_|L|_|L|_|_|_|
|_|L|_|_|_|L|_|_|
|_|_|_|H|_|_|_|_|
|_|L|_|_|_|L|_|_|
|_|_|L|_|L|_|_|_|
|_|_|_|_|_|_|_|_|
 */