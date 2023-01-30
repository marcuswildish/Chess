package JavaChess;

import javafx.scene.image.ImageView;

public class Queen extends Piece {

	public Queen(double x, double y, int color, ImageView imageview) {
		super(x, y, color, imageview);
	}

	@Override
	protected boolean isLegalMove(int prevX, int prevY, int newX, int newY) {
		if (prevX != newX && prevY != newY) {
			if (isBishopMoveBlocked(prevX, prevY, newX, newY)) {
				return false;
			}
		}
		else {
			if (isRookMoveBlocked(prevX, prevY, newX, newY)) {
				return false;
			}
		}
		if (newX == prevX && newY == prevY) {
			return false;
		}
		if (newY - prevY == newX - prevX || newX + newY == prevX + prevY) {
			return true;
		}
		if ((prevX != newX && prevY == newY) || (prevX == newX && prevY != newY)) {
			return true;
		}
		return false;
	}
	
	protected boolean isBishopMoveBlocked(int prevX, int prevY, int newX,int newY) {
		Square[][] squares = this.getPosition();
		int dirX = newX > prevX ? 1 : -1;
		int dirY = newY > prevY ? 1 : -1;
		  for (int i=1; i<Math.abs(newX-prevX); ++i) {
		    if (squares[prevX+i*dirX][prevY+i*dirY].isOccupied()) {
		      return true;
		    }
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
	
	@Override
	protected boolean checkingKing(int currentX, int currentY, int kingX, int kingY) {
		if (currentX != kingX && currentY != kingY) {
			if (isBishopMoveBlocked(currentX, currentY, kingX, kingY)) {
				return false;
			}
		}
		else {
			if (isRookMoveBlocked(currentX, currentY, kingX, kingY)) {
				return false;
			}
		}
		if (kingX == currentX && kingY == currentY) {
			return false;
		}
		if (kingY - currentY == kingX - currentX || kingX + kingY == currentX + currentY) {
			return true;
		}
		if ((currentX != kingX && currentY == kingY) || (currentX == kingX && currentY != kingY)) {
			return true;
		}
		return false;
	}

}
