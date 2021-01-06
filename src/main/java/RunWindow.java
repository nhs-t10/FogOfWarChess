import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.image.Image;

import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class RunWindow extends Application {

    final int X_DIM = 1500;
    final int Y_DIM = 800; //750 is height of stuff in y :)
    int selectedCol=-1;
    int selectedRow=-1;
    ImageView imageSave;
    Color c;
    BoardObj chessBoard = new BoardObj(false);
    @Override
    public void start(Stage stage) throws IOException {
        //Creating a Path
        Player me = new Player(true,chessBoard);
        Player notMe = new Player(false,chessBoard);
        chessBoard.setBoard();
        for(int i = 0; i < 8; i++)
        {
            for(int j = 0; j < 8; j++)
            {
//                System.out.println(chessBoard.getTiles()[i][j].hasAPiece()+", "+i+", "+j);
            }
        }
        int lastRow = 0;
        int lastCol = 0;
        chessBoard.setPlayers(me,notMe);
        chessBoard.changeTiles(me.placePieces(chessBoard.getTiles()));
        chessBoard.changeTiles(notMe.placePieces(chessBoard.getTiles()));

        GridPane chatGrid = new GridPane();
        GridPane boardAreaGrid = new GridPane();//
        GridPane additionalInfoGrid = new GridPane();
        GridPane whitePieces = new GridPane();
        GridPane blackPieces = new GridPane();
        GridPane board = new GridPane();
        HBox layout = new HBox(75,chatGrid,boardAreaGrid,additionalInfoGrid);
        layout.setMargin(chatGrid,new Insets(25,25,25,25));
        layout.setMargin(boardAreaGrid,new Insets(25,25,25,25));
        layout.setMargin(additionalInfoGrid,new Insets(25,25,25,25));


        boardAreaGrid.add(whitePieces,0,0,1,1);
        boardAreaGrid.add(board,0,1,1,1);
        boardAreaGrid.add(blackPieces,0,2,1,1);
        whitePieces.add(new Rectangle(600,75),0,0,1,1);
        blackPieces.add(new Rectangle(600,75),0,0,1,1);
        boardAreaGrid.setHgap(40);
        chatGrid.add(new Rectangle(300,750),0,0,1,1);
        additionalInfoGrid.add(new Rectangle(300,750),0,0);

        for(int i = 0; i < 8; i++)
        {
            for(int j = 0; j<8; j++)
            {

                c=Color.rgb(13, 97, 35);
                if((7*i+j)%2==0)
                    c=Color.rgb(221, 224, 162);
                if(figureOutWhatPeice(i,j,chessBoard.getTiles())!=null)
                {
//                    System.out.println("we made it here");
                    ImageView temp = new ImageView(chessBoard.getTiles()[i][j].pieces[0].images[0]);
                    temp.setOnMouseClicked(e ->{
                        int newRow = (int)((e.getSceneX()-450)/75);
                        int newCol = (int)((e.getSceneY()-100)/75);
                        if(newRow == selectedRow && newCol==selectedCol)
                        {
                            selectedRow=-1;
                            selectedCol=-1;
                            System.out.println("unselected");
                        } else if(selectedRow!=-1 && selectedCol != -1)
                        {
//                            System.out.println("trying to move");
                            try
                            {
                                System.out.println("in the try");
                                if(chessBoard.getTiles()[selectedRow][selectedCol].hasAPiece())
                                {
                                    System.out.println("peice exists");
                                    System.out.println(chessBoard.getTiles()[selectedRow][selectedCol].pieces[0].representation);
                                    chessBoard.tiles=chessBoard.getTiles()[selectedRow][selectedCol].pieces[0].move(chessBoard.getTiles(),(int)((e.getSceneX()-450)/75),(int)(1+(e.getSceneY()-100)/75),chessBoard.getTiles()[selectedRow][selectedCol].pieces[0].possibleMoves(chessBoard.getTiles()));
                                    System.out.println("move works?");
                                    board.getChildren().remove(newRow,newCol);
                                    board.getChildren().remove(temp);
                                    board.add(temp,newRow,newCol);
                                    board.add(new Rectangle(75,75,c),newRow,newCol);
                                }else
                                {
                                    System.out.println("no piece here");
                                }
                                selectedRow=-1;
                                selectedCol=-1;

                            }catch(Exception ex){
                                ex.printStackTrace();
                                System.out.println("error found in the image lambda for figure out what peice not being null :)");

                            }
                        } else
                        {
                            selectedRow = newRow; selectedCol = newCol;
                            imageSave=temp;
                            System.out.println("selected");
                        }



//                        System.out.println("hi 1");
                    });

                    board.add(temp,j,8-i,1,1);
                }else
                {////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                    Rectangle temp = new Rectangle(75,75,c);
                    temp.setOnMouseClicked(e -> {
                        int newRow = (int)((e.getSceneX()-450)/75);
                        int newCol = (int)((e.getSceneY()-100)/75);
                        if(newRow == selectedRow && newCol==selectedCol)
                        {
                            selectedRow=-1;
                            selectedCol=-1;
                            System.out.println("unselected");
                        } else if(selectedRow!=-1 && selectedCol != -1)
                        {
                            System.out.println("trying to move");
                            try
                            {
                                System.out.println(selectedRow+"|"+selectedCol);
                                if(chessBoard.getTiles()[selectedRow][selectedCol].hasAPiece())
                                {
                                    if(imageSave==null)
                                    {
                                        System.out.println("image save is null");
                                    }
                                    chessBoard.tiles=chessBoard.getTiles()[selectedRow][selectedCol].pieces[0].move(chessBoard.getTiles(),(int)((e.getSceneX()-450)/75),(int)(1+(e.getSceneY()-100)/75),chessBoard.getTiles()[selectedRow][selectedCol].pieces[0].possibleMoves(chessBoard.getTiles()));
                                    System.out.println("move works?");

                                    board.getChildren().remove(imageSave);


                                    ObservableList<Node> childrens = board.getChildren();

//                                    for(Node node : childrens) {
//                                        if(node instanceof ImageView && board.getRowIndex(node) == newRow && board.getColumnIndex(node) == newCol) {
//
//                                            board.getChildren().remove(node);
//                                            break;
//                                        }
//                                    }
//                                    board.add(new Rectangle(75,75,c),newRow,newCol+1);
                                    board.add(imageSave,newRow,newCol+1);


                                }else
                                {
                                    System.out.println("no peice here");

                                }

                            }catch(Exception ex){
                                System.out.println("error found in the rect lambda for figure out what peice not being null :)");
                                ex.printStackTrace();
                            }
                            selectedRow=-1;
                            selectedCol=-1;
                        } else
                        {
                            selectedRow = newRow; selectedCol = newCol;
                            System.out.println("selected");
                        }
                    });
                    board.add(temp,j,8-i,1,1);
                }


            }
        }

        //Creating a Group object
        Group root = new Group();

        //Creating a scene object
        Scene scene = new Scene(layout, X_DIM , Y_DIM/*, Color.rgb(215, 245, 198)*/);

        //Setting title to the Stage7
        stage.setTitle("chessboard coming soon");

        scene.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
//                System.out.println("mouse click detected! "+event.getSceneX()+"|"+event.getSceneY());
                if(event.getSceneX()>=450&&event.getSceneX()<=1050&&event.getSceneY()>=100&&event.getSceneY()<=700)
                {
                    System.out.println("the cords are: "+(int)(event.getSceneX()-450)/75+","+(int)(event.getSceneY()-100)/75);

                }
            }
        });



        //Adding scene to the stage
        stage.setScene(scene);

        //Displaying the contents of the stage
        stage.show();



    }
    public static void main(String args[]){
        launch(args);
    }

    public static Image figureOutWhatPeice(int i, int j, Square[][] t)
    {
//        System.out.println("i am being useful");
        if(t[i][j].hasAPiece())
        {
//            System.out.println("image?");
            return t[i][j].pieces[0].images[0];
        }
        return null;
    }




}
