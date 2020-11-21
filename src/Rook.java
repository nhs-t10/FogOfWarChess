//public class Rook extends ChessPiece{
//    Player owner;
//    Boolean canCastle;
//    public Rook()
//    {
//        canCastle=true;
//    }
//    public Square[][] move(Square[][] t,int newRow, int newCol)
//    {
//        Square[][] backup = t;
//        if(this.currentPos.row==newRow||this.currentPos.column==newCol)
//        {
//            if(newRow<=8&&newRow>=0&&newCol>=0&&newCol<=8)
//            {
//                try{
//                    t[newRow][newCol].addPiece(this,this.owner);
//                    t[this.currentPos.row][this.currentPos.column].removePiece();
//                    this.changeCurrentPosSquare(t[newRow][newCol]);
//                    canCastle=false;
//                    return t;
//                }catch(Exception e)
//                {
//                    return backup;
//                }
//            }
//        }
//        return backup;
//    }
//}
