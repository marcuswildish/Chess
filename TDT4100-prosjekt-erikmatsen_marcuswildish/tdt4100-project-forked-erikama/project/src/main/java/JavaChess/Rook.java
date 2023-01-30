package JavaChess;
import javafx.scene.image.ImageView;

public class Rook extends Piece {

	public Rook(double x, double y, int color, ImageView imageview) {
		super(x, y, color, imageview);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected boolean isLegalMove(int prevX, int prevY, int newX, int newY) {
		if (this.isRookMoveBlocked(prevX, prevY, newX, newY)) {
			return false;
		}
		if ((prevX != newX && prevY == newY) || (prevX == newX && prevY != newY)) {
			return true;
		}
		return false;
	}
	
	protected boolean isRookMoveBlocked(int prevX, int prevY, int newX, int newY) {
		Square[][] squares = this.getPosition();
		int dirX = newX > prevX? 1 : -1;
		int dirY = newY > prevY? 1 : -1;
		if (newX != prevX) {
			for (int i=1; i < Math.abs(newX - prevX); ++i) {
			    if (squares[prevX+i*dirX][prevY].isOccupied()) {
			      return true;
			    }
			  }
			  return false;
			}
		else {
			for (int i=1; i < Math.abs(newY - prevY); ++i) {
			    if (squares[prevX][prevY+i*dirY].isOccupied()) {
			      return true;
			    }
			  }
			  return false;
			}
			
		}
	
	protected boolean checkingKing(int currentX, int currentY, int kingX, int kingY) {
		if (this.isRookMoveBlocked(currentX, currentY, kingX, kingY)) {
			return false;
		}
		if ((currentX != kingX && currentY == kingY) || (currentX == kingX && currentY != kingY)) {
			return true;
		}
		return false;
	}
	
}
