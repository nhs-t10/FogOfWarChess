import java.util.ArrayList;

public class Knight extends ChessPiece{

    public Knight(Square startingPos, boolean color, char code,Player owner)
    {
        super(startingPos,color,3,code,"kt", owner);
    }

    public ArrayList<int[]> possibleMoves(Square[][]t)
    {
        int row = this.currentPos.row;
        int col = this.currentPos.column;
        int[][] newPos = new int[][]{new int[]{row+2,col+1},new int[]{row+2,col-1},new int[]{row+1,col+2},new int[]{row+1,col-2},new int[]{row-1,col+2},new int[]{row-1,col-2},new int[]{row-2,col+1},new int[]{row-2,col-1}};
        ArrayList<int[]>theMoves = new ArrayList<>();
        for(int[]i:newPos){
            if(i[0]<8&&i[0]>-1&&i[1]>-1&&i[1]<8&&(!t[i[0]][i[1]].hasAPiece()||(t[i[0]][i[1]].hasAPiece()&&t[i[0]][i[1]].pieces[0].pieceColor!=this.pieceColor)))
            {
                theMoves.add(i);
            }
        }
        System.out.println("pos moves "+theMoves.size());
        for(int[]i:theMoves)
        {
            System.out.println(i[0]+", "+i[1]);
        }
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