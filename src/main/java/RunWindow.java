import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;


public class RunWindow extends Application {

    final int X_DIM = 1500;
    final int Y_DIM = 800; //750 is height of stuff in y :)
    int selectedCol=-1;
    int selectedRow=-1;
    ImageView movingPieceImage;

    boolean turn = true;

    private final BoardObj chessBoard = new BoardObj(false);
    private final GridPane board = new GridPane();

    @Override
    public void start(Stage stage) {
        //Creating a Path
        Player me = new Player(true,chessBoard);
        Player notMe = new Player(false,chessBoard);
        chessBoard.setBoard();
        chessBoard.setPlayers(me,notMe);
        chessBoard.changeTiles(me.placePieces(chessBoard.getTiles()));
        chessBoard.changeTiles(notMe.placePieces(chessBoard.getTiles()));

        GridPane chatGrid = new GridPane();
        GridPane boardAreaGrid = new GridPane();//
        GridPane additionalInfoGrid = new GridPane();
        GridPane whitePieces = new GridPane();
        GridPane blackPieces = new GridPane();
        HBox layout = new HBox(75,chatGrid,boardAreaGrid,additionalInfoGrid);
        HBox.setMargin(chatGrid,new Insets(25,25,25,25));
        HBox.setMargin(boardAreaGrid,new Insets(25,25,25,25));
        HBox.setMargin(additionalInfoGrid,new Insets(25,25,25,25));


        boardAreaGrid.add(whitePieces,0,0,1,1);
        boardAreaGrid.add(board,0,1,1,1);
        boardAreaGrid.add(blackPieces,0,2,1,1);
        whitePieces.add(new Rectangle(600,75),0,0,1,1);
        blackPieces.add(new Rectangle(600,75),0,0,1,1);
        boardAreaGrid.setHgap(40);
        chatGrid.add(new Rectangle(300,750),0,0,1,1);
        additionalInfoGrid.add(new Rectangle(300,750),0,0);

        for(int row = 0; row < 8; row++)
        {
            for(int col = 0; col < 8; col++)
            {
                Color c = getColor(row, col);

                Rectangle temp = new Rectangle(75,75, c);
                temp.setOnMouseClicked(this::onMouseClickOnSquare);
                board.add(temp,col,row,1,1);

                if(figureOutWhatPeice(row,col,chessBoard.getTiles())!=null)
                {
                    ChessFigure piece = new ChessFigure(chessBoard.getTiles()[row][col].pieces[0]);
                    board.add(piece,col,row,1,1);
                }
            }
        }

        //Creating a scene object
        Scene scene = new Scene(layout, X_DIM , Y_DIM/*, Color.rgb(215, 245, 198)*/);

        //Setting title to the Stage7
        stage.setTitle("chessboard coming soon");

        //Adding scene to the stage
        stage.setScene(scene);

        //Displaying the contents of the stage
        stage.show();

    }

    private static Color getColor (int row, int col) {
        if((7* row + col)%2==0)
            return Color.rgb(221, 224, 162); // white
        else
            return Color.rgb(13, 97, 35); // black
    }

    private void onMouseClickOnSquare(MouseEvent e) {
        int newCol = (int)((e.getSceneX()-450)/75);
        int newRow = (int)((e.getSceneY()-100)/75);
        if(newRow == selectedRow && newCol==selectedCol)
        {
            selectedRow=-1;
            selectedCol=-1;
            movingPieceImage=null;
            System.out.println("unselected");
        } else if(selectedRow!=-1 && selectedCol != -1)
        {
            System.out.println("Trying to move: {"+selectedRow+","+selectedCol+"} to {" + newRow+","+newCol +'}');
            try
            {
                if(chessBoard.getTiles()[selectedRow][selectedCol].hasAPiece()&&chessBoard.getTiles()[selectedRow][selectedCol].pieces[0].pieceColor==turn&&movingPieceImage!=null)
                {


                    // modifying the model
                    chessBoard.dump();
                    chessBoard.tiles=chessBoard.getTiles()[selectedRow][selectedCol].pieces[0].move(chessBoard.getTiles(),newRow,newCol,chessBoard.getTiles()[selectedRow][selectedCol].pieces[0].possibleMoves(chessBoard.getTiles()));
                    System.out.println("Before move works?");
                    chessBoard.dump();

                    board.getChildren().remove(movingPieceImage);

                    for(Node node : board.getChildren()) {
                        if(node instanceof ChessFigure && GridPane.getRowIndex(node) == newRow && GridPane.getColumnIndex(node) == newCol) {
                            board.getChildren().remove(node);
                            break;
                        }
                    }
                    board.add(new Rectangle(75,75, getColor(newCol, newRow)),newCol,newRow);
                    board.add(movingPieceImage,newCol,newRow);

                    turn = !turn;
                }else
                {
                    System.out.println("Square has no piece - nothing to do.");
                }

            }catch(Exception ex){
                System.out.println("error found in the rect lambda for figure out what peice not being null :)");
                ex.printStackTrace();
            }
            selectedRow=-1;
            selectedCol=-1;
            movingPieceImage=null;
        } else
        {
            selectedRow = newRow; selectedCol = newCol;
            System.out.println("selected");
        }
    }


    public static void main(String [] args){
        launch(args);
    }

    public static Image figureOutWhatPeice(int row, int col, Square[][] t)
    {
        if(t[row][col].hasAPiece())
        {
            return t[row][col].pieces[0].images[0];
        }
        return null;
    }


    class ChessFigure extends ImageView {
        final ChessPiece chessPiece;

        ChessFigure(ChessPiece chessPiece) {
            super(chessPiece.images[0]);
            this.chessPiece = chessPiece;

            setOnMouseClicked(this::onMouseClickOnPiece);
        }

        private void onMouseClickOnPiece(MouseEvent e) {
            int newCol = (int)((e.getSceneX()-450)/75);
            int newRow = (int)((e.getSceneY()-100)/75);
            System.out.println("Clicked on row=" + newRow + ", col=" + newCol);

            if(newRow == selectedRow && newCol==selectedCol)
            {
                selectedRow=-1;
                selectedCol=-1;
                movingPieceImage=null;
            } else if(selectedRow!=-1 && selectedCol != -1)
            {
                System.out.println("Trying to move: {"+selectedRow+","+selectedCol+"} to {" + newRow+","+newCol +'}');
                try
                {
                    System.out.println(selectedRow+"|"+selectedCol);
                    if(chessBoard.getTiles()[selectedRow][selectedCol].hasAPiece()&&chessBoard.getTiles()[selectedRow][selectedCol].pieces[0].pieceColor==turn&&movingPieceImage!=null)
                    {

                        chessBoard.tiles=chessBoard.getTiles()[selectedRow][selectedCol].pieces[0].move(
                                chessBoard.getTiles(),newRow,newCol,chessBoard.getTiles()[selectedRow][selectedCol].pieces[0].possibleMoves(chessBoard.getTiles()) //TODO: refactor
                        );
                        System.out.println("After move works?");
                        chessBoard.dump();

                        board.getChildren().remove(movingPieceImage);


                        for(Node node : board.getChildren()) {
                            if(node instanceof ChessFigure && GridPane.getRowIndex(node) == newRow && GridPane.getColumnIndex(node) == newCol) {
                                board.getChildren().remove(node);
                                break;
                            }
                        }
                        board.add(new Rectangle(75,75, getColor(newCol, newRow)),newCol,newRow);
                        board.add(movingPieceImage,newCol,newRow);

                        turn = !turn;
                    }else
                    {
                        System.out.println("no peice here");

                    }

                }catch(Exception ex){
                    System.out.println("error found in the image lambda for figure out what peice not being null :)");
                    ex.printStackTrace();
                }
                selectedRow=-1;
                selectedCol=-1;
                movingPieceImage=null;
            } else
            {
                System.out.println("Preparing to move a piece " + this);
                selectedRow = newRow; selectedCol = newCol;
                movingPieceImage = this;
            }
        }

        @Override
        public String toString() {
            return chessPiece.toString();
        }
    }


}
