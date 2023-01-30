package JavaChess;
import javafx.scene.image.ImageView;

public abstract class Piece {
	
	private double x;
	private double y;
	private int color;
	private Square[][] board;
	private ImageView imageview;
	
	public Piece(double x, double y, int color, ImageView imageview) {
		validateX(x);
		validateY(y);
		this.x=x;
		this.y=y;
		this.color=color;
		this.imageview=imageview;
	}
	
	protected void validateX(double x) {
		if (x < 0 || x > 400) {
			throw new IllegalArgumentException("The piece cannot start outside the board.");
		}
	}
	
	protected void validateY(double y) {
		if (y < 0 || y > 400) {
			throw new IllegalArgumentException("The piece cannot start outside the board.");
		}
	}

	protected ImageView getImageview() {
		return imageview;
	}
	
	protected double getX() {
		return x;
	}
	
	protected void updateBoard(Square[][] newPosition) {
		this.board = newPosition;
	}
	
	protected Square[][] getPosition() {
		return this.board;
	}
	
	protected void setX(double x) {
		//Trying to set X outside the board is handled in the gameState-class, so shouldn't call an exception here
		this.x = x;
	}

	protected double getY() {
		return y;
	}

	protected void setY(double y) {
		//Trying to set Y outside the board is handled in the gameState-class, so shouldn't call an exception here
		this.y = y;
	}
	
	protected int getColor() {
		return this.color;
	}
	
	protected abstract boolean isLegalMove(int prevX, int prevY, int newX, int newY);
	
	protected abstract boolean checkingKing(int currentX, int currentY, int kingX, int kingY);
	
	protected void draw() {
		imageview.setTranslateX(x);
		imageview.setTranslateY(y);
	}
	
	

}
