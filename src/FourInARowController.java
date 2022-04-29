import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;


public class FourInARowController {

    @FXML
    private GridPane btnsGrid;

    @FXML
    private Button clearBtn;

    @FXML
    private GridPane gameBoard;

    
    private final int COLUMNS = 7, ROWS = 6;
    
    private Button[] btnsArrayGrid;
    private Pane[][] board;
    
    
    public void initialize() {
	
    	btnsArrayGrid = new Button[COLUMNS];
    	board = new Pane[ROWS][COLUMNS];
    	
    	
    	// Add the first row of the buttons + their text
    	for (int i = 0; i< COLUMNS; i++) {
    		
    		btnsArrayGrid[i] = new Button(""+(i+1));	
    		btnsArrayGrid[i].setPrefSize(btnsGrid.getPrefWidth() / COLUMNS, btnsGrid.getPrefHeight());
    		btnsGrid.add( btnsArrayGrid[i], i, 0);
    	}
    	
    	for (int i=0;i<ROWS; i++) {
    		for( int j=0;j<COLUMNS; j++) {
    			
    			board[i][j] = new Pane();
    			board[i][j].setStyle("-fx-border-color: black");
    			//board[i][j].setBorder(new Border(new BorderStroke(Color.BLACK, 
    					//BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
    			board[i][j].setPrefSize((gameBoard.getPrefWidth() / COLUMNS), (gameBoard.getPrefWidth() / ROWS));
    			gameBoard.add(board[i][j], j, i);
    		}
    	}
    	
    	
    	
    	
    	
    	
    	
    }

    
    @FXML
    void clearGame(ActionEvent event) {

    }

}
