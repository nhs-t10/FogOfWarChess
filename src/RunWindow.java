import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Rectangle;

public class RunWindow extends Application {

    final int X_DIM = 1500;
    final int Y_DIM = 800; //750 is height of stuff in y :)
    @Override
    public void start(Stage stage) {
        //Creating a Path


        GridPane chatGrid = new GridPane();
        GridPane boardAreaGrid = new GridPane();
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
                Color c=Color.rgb(13, 97, 35);
                if((7*i+j)%2==0)
                    c=Color.rgb(221, 224, 162);


                board.add(new Rectangle(75,75,c),i,j,1,1);
            }
        }

        //Creating a Group object
        Group root = new Group();

        //Creating a scene object
        Scene scene = new Scene(layout, X_DIM , Y_DIM, Color.rgb(215, 245, 198));

        //Setting title to the Stage
        stage.setTitle("chessboard coming soon");

        //Adding scene to the stage
        stage.setScene(scene);

        //Displaying the contents of the stage
        stage.show();
    }
    public static void main(String args[]){
        launch(args);
    }

}       