import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Rectangle;

public class RunWindow extends Application {
    @Override
    public void start(Stage stage) {
        //Creating a Path
        GridPane layout = new GridPane();
        GridPane chatGrid = new GridPane();
        GridPane boardAreaGrid = new GridPane();
        GridPane additionalInfoGrid = new GridPane();
        GridPane whitePieces = new GridPane();
        GridPane blackPieces = new GridPane();
        GridPane board = new GridPane();

        layout.add(chatGrid,0,0,1,1);
        layout.add(boardAreaGrid,0,1,1,1);
        layout.add(additionalInfoGrid,0,2,1,1);
        boardAreaGrid.add(whitePieces,0,1,1,1);
        boardAreaGrid.add(board,0,1,1,1);
        boardAreaGrid.add(blackPieces,0,1,1,1);
        board.setHgap(10);
        board.setVgap(10);
        for(int i = 0; i < 8; i++)
        {
            for(int j = 0; j<8; j++)
            {

                board.add(new Rectangle(10,10),i,j,1,1);
            }
        }

        //Creating a Group object
        Group root = new Group();

        //Creating a scene object
        Scene scene = new Scene(layout, 1500, 800);

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