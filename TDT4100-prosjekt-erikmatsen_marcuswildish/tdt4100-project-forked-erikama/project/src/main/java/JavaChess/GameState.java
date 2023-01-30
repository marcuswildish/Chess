package JavaChess;

import java.util.ArrayList;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class GameState {
	
	private ArrayList<Piece>pieces;
	private Square[][] squares;
	private Piece pawnToQueen = null;
	private Piece newQueen = null;
	
	private double currentX;
	private double currentY;
	private int movecount = 0;
	private GameWriter gameWriter = new GameWriter();
	private ChessController controller;
	

	private static final int squareSize = 400/8;
	private static final int size=400;
	
	public GameState(ChessController controller) {
		this.pieces = new ArrayList<Piece>();
		this.squares=new Square[8][8];
		this.controller=controller;
		
	}
	
	private ChessController getController() {
		return this.controller;
	}
	
	protected GameWriter getGameWriter() {
		return this.gameWriter;
	}
	
	
	protected double getCurrentX() {
		return currentX;
	}

	protected void setCurrentX(double currentX) {
		if (currentX < 0 || currentX > 400) {
			throw new IllegalArgumentException("This move is outside the board.");
		}
		this.currentX = currentX;
	}

	protected double getCurrentY() {
		return currentY;
	}
	
	protected void setCurrentY(double currentY) {
		if (currentY < 0 || currentY > 400) {
			throw new IllegalArgumentException("This move is outside the board.");
		}
		this.currentY = currentY;
	}
	
	protected ArrayList<Piece> getPieces() {
		if (this.pieces.size() < 2) {
			throw new IllegalStateException("There must be at least two pieces in play.");
		}
		return pieces;
	}
	
	protected void addPiece(Piece piece) {
		if (this.pieces.contains(piece)) {
			throw new IllegalArgumentException("Cannot add already existing piece to list.");
		}
		pieces.add(piece);
	}
	
	protected void removePiece(Piece piece) {
		if (!this.pieces.contains(piece)) {
			throw new IllegalArgumentException("Cannot remove piece that is not in play.");
		}
		this.pieces.remove(piece);
	}
	
	protected Square getSquare(int x, int y) {
		if (x < 0 || x > 7 || y < 0 || y > 7) {
			throw new IllegalArgumentException("These coordinates do not exist on the board.");
		}
		return squares[x][y];
	}
	
	protected void setSquare(int x, int y, Square square) {
		if (x < 0 || x > 7 || y < 0 || y > 7) {
			throw new IllegalArgumentException("These coordinates do not exist on the board.");
		}
		squares[x][y]=square;
	}
	
	protected Square[][] getSquares() {
		return squares;
	}
	
	protected Piece getPawnToQueen() {
		return pawnToQueen;
	}
	 
	protected Piece getNewQueen() {
		return newQueen;
	}
	
	protected void InitializeGameState() {
		if (!this.pieces.isEmpty()) {
			throw new IllegalStateException("must be empty board to initialize state");
		}
		
		for (int column=0; column<size; column+=squareSize) {
			for (int row=0; row<size; row+=squareSize) {
				Square s = new Square(column/squareSize, row/squareSize, null, false);
				setSquare(column/squareSize,row/squareSize, s);
			}
		}
		
		for (int i=0; i<8; i++) {//Initialize all pawns
			Piece blackpawn=new Pawn(squareSize*i, squareSize, 1, new ImageView(new Image(getClass().getResourceAsStream("Chess_pdt60.png")))); 
			addPiece(blackpawn);
			getSquare(i,1).setPiece(blackpawn);
		}
		
		for (int i=0;i<8;i++) {//Initialize all pawns
			Piece whitepawn=new Pawn(squareSize*i, 6*squareSize, 0, new ImageView(new Image(getClass().getResourceAsStream("Chess_plt60.png"))));
			addPiece(whitepawn);
			getSquare(i,6).setPiece(whitepawn);
		}
		
		
		//Initialize all pieces
		Piece whiteRook1 = new Rook(0, squareSize*7, 0, new ImageView(new Image(getClass().getResourceAsStream("Chess_rlt60.png"))));
		Piece whiteRook2 = new Rook(7*squareSize, squareSize*7, 0, new ImageView(new Image(getClass().getResourceAsStream("Chess_rlt60.png"))));
		Piece blackRook1 = new Rook(0, 0, 1, new ImageView(new Image(getClass().getResourceAsStream("Chess_rdt60.png"))));
		Piece blackRook2 = new Rook(7*squareSize, 0, 1, new ImageView(new Image(getClass().getResourceAsStream("Chess_rdt60.png"))));
		Piece whiteKnight1 = new Knight(squareSize, squareSize*7, 0, new ImageView(new Image(getClass().getResourceAsStream("Chess_nlt60.png"))));
		Piece whiteKnight2 = new Knight(6*squareSize, squareSize*7, 0, new ImageView(new Image(getClass().getResourceAsStream("Chess_nlt60.png"))));
		Piece blackKnight1 = new Knight(squareSize, 0, 1, new ImageView(new Image(getClass().getResourceAsStream("Chess_ndt60.png"))));
		Piece blackKnight2 = new Knight(6*squareSize, 0, 1, new ImageView(new Image(getClass().getResourceAsStream("Chess_ndt60.png"))));
		Piece whiteBishop1 = new Bishop(2*squareSize, squareSize*7, 0, new ImageView(new Image(getClass().getResourceAsStream("Chess_blt60.png"))));
		Piece whiteBishop2 = new Bishop(5*squareSize, squareSize*7, 0, new ImageView(new Image(getClass().getResourceAsStream("Chess_blt60.png"))));
		Piece blackBishop1 = new Bishop(2*squareSize, 0, 1, new ImageView(new Image(getClass().getResourceAsStream("Chess_bdt60.png"))));
		Piece blackBishop2 = new Bishop(5*squareSize, 0, 1, new ImageView(new Image(getClass().getResourceAsStream("Chess_bdt60.png"))));
		Piece whiteQueen = new Queen(3*squareSize, 7*squareSize, 0, new ImageView(new Image(getClass().getResourceAsStream("Chess_qlt60.png"))));
		Piece blackQueen = new Queen(3*squareSize, 0, 1, new ImageView(new Image(getClass().getResourceAsStream("Chess_qdt60.png"))));
		Piece whiteKing = new King(4*squareSize, 7*squareSize, 0, new ImageView(new Image(getClass().getResourceAsStream("Chess_klt60.png"))));
		Piece blackKing = new King(4*squareSize, 0, 1, new ImageView(new Image(getClass().getResourceAsStream("Chess_kdt60.png"))));
		
		//Add pieces to the game state
		addPiece(whiteRook1);
		addPiece(whiteRook2);
		addPiece(blackRook1);
		addPiece(blackRook2);
		addPiece(whiteKnight1);
		addPiece(whiteKnight2);
		addPiece(blackKnight1);
		addPiece(blackKnight2);
		addPiece(whiteBishop1);
		addPiece(whiteBishop2);
		addPiece(blackBishop1);
		addPiece(blackBishop2);
		addPiece(whiteKing);
		addPiece(blackKing);
		addPiece(whiteQueen);
		addPiece(blackQueen);
		getSquare(0, 0).setPiece(blackRook1);
		getSquare(0, 7).setPiece(whiteRook1);
		getSquare(1, 0).setPiece(blackKnight1);
		getSquare(1, 7).setPiece(whiteKnight1);
		getSquare(2, 0).setPiece(blackBishop1);
		getSquare(2, 7).setPiece(whiteBishop1);
		getSquare(3, 0).setPiece(blackQueen);
		getSquare(3, 7).setPiece(whiteQueen);
		getSquare(4, 0).setPiece(blackKing);
		getSquare(4, 7).setPiece(whiteKing);
		getSquare(5, 0).setPiece(blackBishop2);
		getSquare(5, 7).setPiece(whiteBishop2);
		getSquare(6, 0).setPiece(blackKnight2);
		getSquare(6, 7).setPiece(whiteKnight2);
		getSquare(7, 0).setPiece(blackRook2);
		getSquare(7, 7).setPiece(whiteRook2);
	}
	
	protected boolean isCheck(int color) {//Checks if king is in check
		if (color < 0 || color > 1) { //0 means white, 1 means black
			throw new IllegalArgumentException("Invalid color");
		}
		int kingX = 0;
		int kingY = 0;
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (squares[i][j].isOccupied()) {
					if (squares[i][j].getPiece().getClass().getName().equals("JavaChess.King") && squares[i][j].getPiece().getColor() == color) {
						kingX = i;
						kingY = j;	
					}
				}
			}
		}
		for (Piece p: pieces) {
			if (p.getColor() != color) {
				double newXDouble = p.getX();
				double newYDouble = p.getY();
				int newX = (int)(newXDouble+25) / 50;
				int newY = (int)(newYDouble+25) / 50;
				if (p.checkingKing(newX, newY, kingX, kingY)) {
					return true;
				}
			}
		}
		return false;
	}
	
	
	protected boolean isPawnQueening(Piece pawn) { //Checks if there is a pawn on the 1st or 8th rank. If so, it should turn into a queen!
		boolean queening = false;
		int row = 0;
		int color = 0;
		String pieceType = "";
		String imagefile = "Chess_qlt60.png";
		for (int i = 0; i < 8; i++) {
			try {
				pieceType=squares[i][0].getPiece().getClass().getName();
			}
			catch(NullPointerException e){//There could be no piece on the given square, this must be handled
				
			}
			if (squares[i][0].isOccupied() && pieceType.equals("JavaChess.Pawn") ) {
				 queening = true;
			}
			try {
				pieceType=squares[i][7].getPiece().getClass().getName();
			}
			catch(NullPointerException e){//There could be no piece on the given square, this must be handled
				
			}
			if (squares[i][7].isOccupied() && pieceType.equals("JavaChess.Pawn"))  {
				 color=1;
				 imagefile="Chess_qdt60.png";
				 row =7;
				 queening = true;
			}
			if (queening == true) {
				pawnToQueen = pawn;
				newQueen = new Queen(i*squareSize,row*squareSize,color,new ImageView(new Image(getClass().getResourceAsStream(imagefile))));
				newQueen.getImageview().setFitHeight(50);
				newQueen.getImageview().setFitWidth(50);
				removePiece(pawnToQueen);
				pieces.add(newQueen);
				squares[i][row].setPiece(newQueen);	
				return true;
			}
		}
		return false;
	}
	
	
	protected void handlePieceReleased(Piece p) {
		if (!getPieces().contains(p)) {
			throw new IllegalArgumentException("Where did this piece come from?");
		}
		for (Piece piece: getPieces()) {
			piece.updateBoard(getSquares());
		}
		int newRow = (int)(p.getX()+25) / squareSize;
		int newColumn = (int)(p.getY()+25) / squareSize;
		int oldRow = (int)(currentX+25) / squareSize;
		int oldColumn = (int)(currentY+25) / squareSize;
		
		if (newRow < 8 && newColumn < 8 && p.getColor() == movecount%2 && p.isLegalMove(oldRow, oldColumn, newRow, newColumn)) { //Making sure move is valid
			try {
				if (getSquares()[newRow][newColumn].getPiece().getColor() != p.getColor()) {
					Piece captured = getSquares()[newRow][newColumn].getPiece();
					getController().removePiece(captured);
					Piece freed = getSquares()[oldRow][oldColumn].freeSquare();
					getSquares()[newRow][newColumn].setPiece(p);
					removePiece(captured);
					if (!isCheck(movecount%2)) {
						if(isPawnQueening(p)) {
							getController().removePiece(getPawnToQueen());
							getController().instantiatePiece(getNewQueen());
							getController().addPiece(getNewQueen());
							getNewQueen().draw();
						}
						p.setX(squareSize*newRow);
						p.setY(squareSize*newColumn);
						p.draw();
						String moveString = getGameWriter().getMoveString(newRow, newColumn, p, true);
						if (movecount%2==0) {
							getController().UpdateTextWhite(moveString);
						}
						else {
							getController().updateTextBlack(moveString);
						}
						movecount++;
						getGameWriter().updateGame(moveString);
						getController().setErrorText("");
					}
					else {
						getController().addPiece(captured);
						getSquares()[oldRow][oldColumn].setPiece(freed);
						getSquares()[newRow][newColumn].setPiece(captured);
						getPieces().add(captured);
						p.setX(currentX);
						p.setY(currentY);
						p.draw();
						getController().setErrorText("You're in check!");
					}
				}
				else {
					p.setX(currentX);
					p.setY(currentY);
					p.draw();
					getController().setErrorText("That is not a legal move!");
				}
				
			}
			catch (NullPointerException N) { //Possible nullpointer in getPiece(), if square is not occupied
				Piece freed = getSquares()[oldRow][oldColumn].freeSquare();
				getSquares()[newRow][newColumn].setPiece(p);
				if (!isCheck(movecount%2)) {
					p.setX(squareSize*newRow);
					p.setY(squareSize*newColumn);
					p.draw();
					if(isPawnQueening(p)) {
						getController().removePiece(getPawnToQueen());
						getController().instantiatePiece(getNewQueen());
						getController().addPiece(getNewQueen());
						getNewQueen().draw();
					}
					String moveString = getGameWriter().getMoveString(newRow, newColumn, p, false);
					
					if (movecount%2==0) {
						getController().UpdateTextWhite(moveString);
					}
					else {
						getController().updateTextBlack(moveString);
					}
					movecount++;	
					getGameWriter().updateGame(moveString);
					getController().setErrorText("");
				}
				else {
					getSquares()[oldRow][oldColumn].setPiece(freed);
					getSquares()[newRow][newColumn].freeSquare();
					p.setX(currentX);
					p.setY(currentY);
					p.draw();
					getController().setErrorText("You're in check!");
				}
			}
		}		
		else { //Invalid move attempted
			p.setX(currentX);
			p.setY(currentY);
			p.draw();
			getController().setErrorText("That is not a legal move!");
		}	
	}






}
