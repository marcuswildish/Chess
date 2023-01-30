package JavaChess;
import javafx.scene.image.ImageView; 

public class Bishop extends Piece {

	public Bishop(double x, double y, int color, ImageView imageview) {
		super(x, y, color, imageview);
	}

	@Override
	protected boolean isLegalMove(int prevX, int prevY, int newX, int newY) {
		if (isBishopMoveBlocked(prevX, prevY, newX, newY)) {
			return false;
		}
		if (prevX == newX) {
			return false;
		}
		if (newY - prevY == newX - prevX || newX + newY == prevX + prevY) {
			return true;
		}
		return false;
	}
	
	protected boolean isBishopMoveBlocked(int prevX, int prevY, int newX,int newY) {
		Square[][] squares = this.getPosition();
		int dirX = newX > prevX ? 1 : -1;
		int dirY = newY > prevY ? 1 : -1;
		  for (int i=1; i < Math.abs(newX-prevX); ++i) {
		    if (squares[prevX+i*dirX][prevY+i*dirY].isOccupied()) {
		      return true;
		    }
		  }
		  return false;
		}
	
	@Override
	protected boolean checkingKing(int currentX, int currentY, int kingX, int kingY) {
		if (currentX == kingX) {
			return false;
		}
		if (kingY - currentY == kingX - currentX || kingX + kingY == currentX + currentY) {
			if (isBishopMoveBlocked(currentX, currentY, kingX, kingY)) {
				return false;
			}
			return true;
		}
		return false;
	}
	

}
