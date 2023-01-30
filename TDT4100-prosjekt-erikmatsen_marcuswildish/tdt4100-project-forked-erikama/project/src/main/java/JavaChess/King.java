package JavaChess;
import javafx.scene.image.ImageView;

public class King extends Piece {

	public King(double x, double y, int color, ImageView imageview) {
		super(x, y, color, imageview);
	}

	@Override
	protected boolean isLegalMove(int prevX, int prevY, int newX, int newY) {
		if (prevX == newX && prevY == newY) {
			return false;
		}
		if (newX > prevX + 1 || newX < prevX - 1 || newY > prevY + 1 || newY < prevY - 1 ) {
			return false;
		}
		return true;
	}
	
	@Override
	protected boolean checkingKing(int currentX, int currentY, int kingX, int kingY) {
		if ((currentX == kingX -1 || currentX == kingX + 1 || currentX == kingX) && (currentY == kingY - 1 || currentY == kingY + 1 || currentY == kingY)) {
			return true;
		}
		return false;
	}

}
