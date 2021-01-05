import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
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
import java.io.FileInputStream;
import java.io.IOException;

public class RunWindow extends Application {

    final int X_DIM = 1500;
    final int Y_DIM = 800; //750 is height of stuff in y :)
    @Override
    public void start(Stage stage) throws IOException {
        //Creating a Path
        BoardObj chessBoard = new BoardObj(false);
        Player me = new Player(true,chessBoard);
        Player notMe = new Player(false,chessBoard);
        chessBoard.setBoard();
        for(int i = 0; i < 8; i++)
        {
            for(int j = 0; j < 8; j++)
            {
                System.out.println(chessBoard.getTiles()[i][j].hasAPiece()+", "+i+", "+j);
            }
        }
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
        board = updateBoard(chessBoard);

        //Creating a Group object
        Group root = new Group();

        //Creating a scene object
        Scene scene = new Scene(layout, X_DIM , Y_DIM/*, Color.rgb(215, 245, 198)*/);

        //Setting title to the Stage7
        stage.setTitle("chessboard coming soon");

        scene.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                System.out.println("mouse click detected! "+event.getSceneX()+"|"+event.getSceneY());
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
        System.out.println("i am being useful");
        if(t[i][j].hasAPiece())
        {
            System.out.println("image?");
            return t[i][j].pieces[0].images[0];
        }
        return null;
    }
    public static GridPane updateBoard(BoardObj chessBoard)
    {
        GridPane board = new GridPane();
        for(int i = 0; i < 8; i++)
        {
            for(int j = 0; j<8; j++)
            {
                Color c=Color.rgb(13, 97, 35);
                if((7*i+j)%2==0)
                    c=Color.rgb(221, 224, 162);
                if(figureOutWhatPeice(i,j,chessBoard.getTiles())!=null)
                {
                    System.out.println("we made it here");
                    board.add(new ImageView(chessBoard.getTiles()[i][j].pieces[0].images[0]),j,8-i,1,1);
                }else
                {
                    board.add(new Rectangle(75,75,c),j,8-i,1,1);
                }


            }
        }
        return board;

    }

}
