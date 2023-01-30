package JavaChess;
import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.control.Button;

public class ChessController {
	
	@FXML
	Pane pane;
	@FXML
	Label textfield;
	@FXML
	Label errorText;
	@FXML
	VBox VBox1;
	@FXML
	VBox VBox2;
	@FXML
	Button exportGame;

	
	private static final int size = 400;
	private static final int spots = 8;
	private static final int squareSize = size/spots;
	private int alternate=0;
	
	private GameState gameState;
	
	
	//initializes all javafx elements like Rectangles and imageviews. Calls GameStates Initialize GameStatemethod to initialize the model 
	@FXML
	private void initialize() {
		gameState = new GameState(this);
		gameState.InitializeGameState();
		for (int column=0; column<size; column+=squareSize) {
			for (int row=0; row<size; row+=squareSize) {
				Rectangle r = new Rectangle(column,row,squareSize,squareSize);
				if ((alternate/8)%2==0) {
					if (alternate%2==0) {
						r.setFill(Color.BLANCHEDALMOND);
					}
					else {
						r.setFill(Color.PERU);
					}
				}
				else {
					if (alternate%2==0) {
						r.setFill(Color.PERU);
					}
					else {
						r.setFill(Color.BLANCHEDALMOND);
					}
				}
				alternate++;
				r.setStroke(Color.BLACK);
				pane.getChildren().add(r);
			}
		}
		
//setter imageview sine dimensjoner og gjør at de kan trykkes på og beveges
		
		for (Piece piece:gameState.getPieces()) {
			piece.getImageview().setFitHeight(50);
			piece.getImageview().setFitWidth(50);
			piece.getImageview().setOnMousePressed(event-> pressed(event,piece));
			piece.getImageview().setOnMouseDragged(event-> dragged(event,piece));
			piece.getImageview().setOnMouseReleased(event -> released(event,piece));
			pane.getChildren().add(piece.getImageview());
			piece.draw();
		}
	}
	//Used when a new queen is made Makes the queen able to be moved
	protected void instantiatePiece(Piece piece) {
		piece.getImageview().setOnMousePressed(event-> pressed(event,piece));
		piece.getImageview().setOnMouseDragged(event-> dragged(event,piece));
		piece.getImageview().setOnMouseReleased(event -> released(event,piece));
		
	}
	
	protected void UpdateTextWhite(String moveString) {
		textfield.setText("Black to move");
		VBox2.getChildren().add(new Text(moveString));
	}
	
	protected void updateTextBlack(String moveString) {
		textfield.setText("White to move");
		VBox1.getChildren().add(new Text(moveString));
	}
	
	protected void removePiece(Piece p) {
		pane.getChildren().remove(p.getImageview());
	}
	
	protected void addPiece(Piece p) {
		pane.getChildren().add(p.getImageview());
	}
	
	protected void pressed(MouseEvent event, Piece p) {
		gameState.setCurrentX(p.getX());
		gameState.setCurrentY(p.getY());
	}
		
	protected void dragged(MouseEvent event, Piece p) {
		p.setX(p.getX()+event.getX() -25);
		p.setY(p.getY() + event.getY()-25);
		p.draw();
	}
	
	protected void released(MouseEvent event, Piece p) {
		gameState.handlePieceReleased(p);
	}
	
	protected void setErrorText(String newText) {
		if (!(newText.equals("") || newText.equals("That is not a legal move!") || newText.equals("You're in check!"))) {
			throw new IllegalArgumentException("This text is not valid to write to the screen.");
		}
		errorText.setText(newText);
	}
	
	
	//Calls the exportGame method from GameWriter.
	@FXML
	public void writeToFile() {
		try {
			gameState.getGameWriter().exportGame("Chessgame.txt");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public GameState getgameState() {
		return this.gameState;
	}
	
}
