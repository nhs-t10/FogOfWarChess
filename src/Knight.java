import java.util.ArrayList;

public class Knight extends ChessPiece{

    public Knight()
    {
        super();
    }
    public ArrayList<int[]> possibleMoves(Square[][]t)
    {
        int row = this.currentPos.row;
        int col = this.currentPos.column;
        int[][] newPos = new int[][]{new int[]{row+2,col+1},new int[]{row+2,col-1},new int[]{row+1,col+2},new int[]{row+1,col-2},new int[]{row-1,col+2},new int[]{row-1,col-2},new int[]{row-2,col+1},new int[]{row-2,col-1}};
        ArrayList<int[]>theMoves = new ArrayList<>();
        for(int[]i:newPos){
            if(i[0]<8&&i[0]>-1&&i[1]>-1&&i[1]<8)
            {
                theMoves.add(i);
            }
        }
        return theMoves;

    }//so we have 3 cases: 6 6 2 2,   7 7 1 1, the cases between


    public Square[][] move(Square[][] t,int newRow, int newCol,ArrayList<int[]>posMoves)
    {
        for(int i = 0; i<posMoves.size();i++)
        {
            if(posMoves.get(i)[0]==newRow&&posMoves.get(i)[1]==newCol)
            {
                if(posMoves.get(i)[2]==1)
                {
                    takePiece(t[newRow][newCol].pieces[0],newRow,newCol);
                }
                t[newRow][newCol].pieces[0]=this;
                t[this.currentPos.row][this.currentPos.column].pieces[0]=null;
            }
        }
        return t;
    }


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