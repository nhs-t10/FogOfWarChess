import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

//IMPORTANT: COL THEN ROW FOR GUI STUFF, ROW THEN COL FOR NON GUI STUFF
public class RunWindow extends Application {

    public static final int CELL_SIZE = 75;
    public static final int LABEL_SIZE = 20;
    public static final int BOARD_SIZE = 8*CELL_SIZE + 2*LABEL_SIZE;
    final int X_DIM = 1500;
    final int Y_DIM = 800; //750 is height of stuff in y :)
    int selectedCol=-1;
    int selectedRow=-1;
    ImageView movingPieceImage;

    boolean turn = true;
    static boolean applyFog = true;

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
        HBox layout = new HBox(CELL_SIZE,chatGrid,boardAreaGrid,additionalInfoGrid);
        HBox.setMargin(chatGrid,new Insets(25,25,25,25));
        HBox.setMargin(boardAreaGrid,new Insets(25,25,25,25));
        HBox.setMargin(additionalInfoGrid,new Insets(25,25,25,25));

        BorderPane pane = new BorderPane();
        GridPane boardWithLabels = new GridPane();
        for (int i = 0; i < 8; i++) {
            boardWithLabels.add(newRowLabel(i), 0, i + 1, 1, 1);
            boardWithLabels.add(newRowLabel(i), 9, i + 1, 1, 1);
            boardWithLabels.add(newColLabel(i), i + 1, 0, 1, 1);
            boardWithLabels.add(newColLabel(i), i + 1, 9, 1, 1);
        }
        boardWithLabels.add(board, 1, 1, 8, 8);
        boardWithLabels.setAlignment(Pos.CENTER);
        pane.setCenter(boardWithLabels);


        boardAreaGrid.add(whitePieces,0,0,1,1);
        boardAreaGrid.add(pane,0,1,1,1);
        boardAreaGrid.add(blackPieces,0,2,1,1);
        whitePieces.add(new Rectangle(BOARD_SIZE,CELL_SIZE),0,0,1,1);
        blackPieces.add(new Rectangle(BOARD_SIZE,CELL_SIZE),0,0,1,1);
        boardAreaGrid.setHgap(40);
        chatGrid.add(new Rectangle(300,10*CELL_SIZE),0,0,1,1);
        additionalInfoGrid.add(new Rectangle(300,10*CELL_SIZE),0,0);

        for(int row = 0; row < 8; row++)
        {
            for(int col = 0; col < 8; col++)
            {
                Color c = getColor(row, col);

                Rectangle temp = new Rectangle(CELL_SIZE,CELL_SIZE, c);
                temp.setOnMouseClicked(this::onMouseClickOnSquare);
                board.add(temp,col,row,1,1);

                if(figureOutWhatPeice(row,col,chessBoard.getTiles())!=null)
                {
                    ChessFigure piece = new ChessFigure(chessBoard.getTiles()[row][col].pieces[0]);
                    board.add(piece,col,row,1,1);
                }
            }
        }
        if(applyFog)
        {
            boolean[][] fogSquares = chessBoard.getPlayer(turn).calculateVisibleSquares();
            for(int row = 0; row < 8; row++)
            {
                for(int col = 0; col<8;col++)
                {
                    if(!fogSquares[row][col])
                    {
                        board.add(new FogRect(col,row),col,row);
                    }
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

    private void onMouseClickOnSquare(MouseEvent e) {
        int newCol = (int)((e.getSceneX()-450-LABEL_SIZE)/CELL_SIZE);
        int newRow = (int)((e.getSceneY()-100-LABEL_SIZE)/CELL_SIZE);
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
                    board.add(new Rectangle(CELL_SIZE,CELL_SIZE, getColor(newCol, newRow)),newCol,newRow);
                    board.add(movingPieceImage,newCol,newRow);
                    movingPieceImage.setEffect(null);

                    turn = !turn;



                    if(applyFog)
                    {
                        System.err.println("WE REMOVIN");
                        board.getChildren().removeIf(node -> node instanceof FogRect && GridPane.getRowIndex(node) == newRow && GridPane.getColumnIndex(node) == newCol);



//                        boolean[][] fogSquares = chessBoard.getPlayer(turn).calculateVisibleSquares();
//                        for(int row = 0; row < 8; row++)
//                        {
//                            for(int col = 0; col<8;col++)
//                            {
//                                if(!fogSquares[row][col])
//                                {
//                                    board.add(new FogRect(row,col),col,row);
//                                }
//                            }
//                        }
                    }
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
    class FogRect extends Rectangle {

        FogRect(int row, int col)
        {
            super(CELL_SIZE,CELL_SIZE,getFogColor(col,row));
        }
        FogRect()
        {
            super();
        }
    }

    class ChessFigure extends ImageView {
        final ChessPiece chessPiece;

        ChessFigure(ChessPiece chessPiece) {
            super(chessPiece.images[0]);
            this.chessPiece = chessPiece;

            setOnMouseClicked(this::onMouseClickOnPiece);
        }

        private void onMouseClickOnPiece(MouseEvent e) {
            int newCol = (int)((e.getSceneX()-450-LABEL_SIZE)/CELL_SIZE);
            int newRow = (int)((e.getSceneY()-100-LABEL_SIZE)/CELL_SIZE);
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
                        board.add(new Rectangle(CELL_SIZE,CELL_SIZE, getColor(newCol, newRow)),newCol,newRow);
                        board.add(movingPieceImage,newCol,newRow);
                        movingPieceImage.setEffect(null);

                        turn = !turn;
                        if(applyFog)
                        {
                            System.err.println("WE REMOVIN");
                            board.getChildren().removeIf(node -> node instanceof FogRect && GridPane.getRowIndex(node) == newRow && GridPane.getColumnIndex(node) == newCol);


//                            boolean[][] fogSquares = chessBoard.getPlayer(turn).calculateVisibleSquares();
//                            for(int row = 0; row < 8; row++)
//                            {
//                                for(int col = 0; col<8;col++)
//                                {
//                                    if(!fogSquares[row][col])
//                                    {
//                                        board.add(new FogRect(col,row),col,row);
//                                    }
//                                }
//                            }
                        }

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

            this.setEffect( DS );
        }

        @Override
        public String toString() {
            return chessPiece.toString();
        }
    }

    private static Label newRowLabel(int i) {
        Label l = new Label(Integer.toString(8 - i));
        l.setMinSize(LABEL_SIZE, CELL_SIZE);
        l.setAlignment(Pos.CENTER);
        return l;
    }

    private static Label newColLabel(int i) {
        Label l = new Label(Character.toString('A' + i));
        l.setMinSize(CELL_SIZE, LABEL_SIZE);
        l.setAlignment(Pos.CENTER);
        return l;
    }

    private static Color getColor (int row, int col) {
        if((7* row + col)%2==0)
            return Color.rgb(221, 224, 162); // white
        else
            return Color.rgb(13, 97, 35); // black
    }

    private static Color getFogColor(int row, int col)
    {
        if((7* row + col)%2==0)
            return Color.rgb(
                    125, 125, 121); // white
        else
            return Color.rgb(63, 69, 65); // black
    }
}
