package com.mememe653.chess;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.event.MouseInputAdapter;

import com.mememe653.chess.pieces.Bishop;
import com.mememe653.chess.pieces.King;
import com.mememe653.chess.pieces.Knight;
import com.mememe653.chess.pieces.Pawn;
import com.mememe653.chess.pieces.Queen;
import com.mememe653.chess.pieces.Rook;
import com.mememe653.chess.pieces.Pawn.Direction;
import com.mememe653.chess.pieces.Piece;

public class Board extends JPanel {
	
	enum BoardNum {
		One,
		Two
	};
	
	public static final int WIDTH = 480;
	public static final int HEIGHT = 480;
	
	private Square selectedSquare;
	private Board mirrorBoard;
	private Color turn = Color.white;
	
	private Square[][] squares = new Square[8][8];
	private MouseListener mouseListener = new MouseListener();

	public Board(BoardNum boardNum) {
		initBoard();
		initSquares();
		
		if (boardNum.equals(BoardNum.One)) {
			// Setting black pieces
			squares[0][0].setPiece(new Rook(Color.black));
			squares[0][1].setPiece(new Knight(Color.black));
			squares[0][2].setPiece(new Bishop(Color.black));
			squares[0][3].setPiece(new Queen(Color.black));
			squares[0][4].setPiece(new King(Color.black));
			squares[0][5].setPiece(new Bishop(Color.black));
			squares[0][6].setPiece(new Knight(Color.black));
			squares[0][7].setPiece(new Rook(Color.black));
			for (int j = 0; j < 8; j++) {
				squares[1][j].setPiece(new Pawn(Color.black, Direction.Down));
			}
			
			// Setting white pieces
			squares[7][0].setPiece(new Rook(Color.white));
			squares[7][1].setPiece(new Knight(Color.white));
			squares[7][2].setPiece(new Bishop(Color.white));
			squares[7][3].setPiece(new Queen(Color.white));
			squares[7][4].setPiece(new King(Color.white));
			squares[7][5].setPiece(new Bishop(Color.white));
			squares[7][6].setPiece(new Knight(Color.white));
			squares[7][7].setPiece(new Rook(Color.white));
			for (int j = 0; j < 8; j++) {
				squares[6][j].setPiece(new Pawn(Color.white, Direction.Up));
			}
		} else if (boardNum.equals(BoardNum.Two)) {
			// Setting black pieces
			this.squares[7][0].setPiece(new Rook(Color.black));
			this.squares[7][1].setPiece(new Knight(Color.black));
			this.squares[7][2].setPiece(new Bishop(Color.black));
			this.squares[7][3].setPiece(new King(Color.black));
			this.squares[7][4].setPiece(new Queen(Color.black));
			this.squares[7][5].setPiece(new Bishop(Color.black));
			this.squares[7][6].setPiece(new Knight(Color.black));
			this.squares[7][7].setPiece(new Rook(Color.black));
			for (int j = 0; j < 8; j++) {
				this.squares[6][j].setPiece(new Pawn(Color.black, Direction.Up));
			}
			
			// Setting white pieces
			this.squares[0][0].setPiece(new Rook(Color.white));
			this.squares[0][1].setPiece(new Knight(Color.white));
			this.squares[0][2].setPiece(new Bishop(Color.white));
			this.squares[0][3].setPiece(new King(Color.white));
			this.squares[0][4].setPiece(new Queen(Color.white));
			this.squares[0][5].setPiece(new Bishop(Color.white));
			this.squares[0][6].setPiece(new Knight(Color.white));
			this.squares[0][7].setPiece(new Rook(Color.white));
			for (int j = 0; j < 8; j++) {
				this.squares[1][j].setPiece(new Pawn(Color.white, Direction.Down));
			}
		}
	}
	
	private void initBoard() {
		setFocusable(true);
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		setLayout(new GridLayout(8, 8));
		addMouseListener(mouseListener);
	}
	
	private void initSquares() {
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if ((i % 2 == 0) && (j % 2 == 0)) {
					Square square = new Square(Color.white, i, j);
					squares[i][j] = square;
					add(square);
				} else if ((i % 2 == 1) && (j % 2 == 1)) {
					Square square = new Square(Color.white, i, j);
					squares[i][j] = square;
					add(square);
				} else {
					Square square = new Square(Color.black, i, j);
					squares[i][j] = square;
					add(square);
				}
			}
		}
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		for (int row = 0; row < 8; row++) {
			for (int col = 0; col < 8; col++) {
				squares[row][col].paint(g);
			}
		}
	}
	
	public void setMirrorBoard(Board mirrorBoard) {
		this.mirrorBoard = mirrorBoard;
	}
	
	public Square getSquare(int row, int col) {
		return squares[row][col];
	}
	
	public void setSelectedSquare(int row, int col) {
		if (selectedSquare != null) {
			selectedSquare.setSelected(false);
		}
		selectedSquare = squares[row][col];
		selectedSquare.setSelected(true);
	}
	
	public void setSelectedSquare(Object obj) {
		if (obj == null) {
			selectedSquare.setSelected(false);
			selectedSquare = null;
		}
	}
	
	public Square getSelectedSquare() {
		return selectedSquare;
	}
	
	public void changeTurn() {
		if (turn.equals(Color.white)) {
			turn = Color.black;
		} else {
			turn = Color.white;
		}
	}
	
	private class MouseListener extends MouseInputAdapter {
		
		@Override
		public void mouseClicked(MouseEvent e) {
			super.mouseClicked(e);
			
			int x = e.getX();
			int y = e.getY();
			
			x /= Square.WIDTH;
			y /= Square.WIDTH;
			
			if (squares[y][x].getPiece() != null && squares[y][x].getPiece().getColor().equals(turn)) {
				setSelectedSquare(y, x);
				mirrorBoard.setSelectedSquare(7 - y, 7 - x);
			} else if (selectedSquare != null && canCastle(y, x)) {
				switch (y) {
				case 0:
					switch (x) {
					case 1:
						squares[0][1].setPiece(selectedSquare.getPiece());
						squares[0][1].getPiece().move();
						squares[0][3].setPiece(null);
						squares[0][2].setPiece(squares[0][0].getPiece());
						squares[0][2].getPiece().move();
						squares[0][0].setPiece(null);
						mirrorBoard.getSquare(7, 6).setPiece(mirrorBoard.getSelectedSquare().getPiece());
						mirrorBoard.getSquare(7, 6).getPiece().move();
						mirrorBoard.getSquare(7, 4).setPiece(null);
						mirrorBoard.getSquare(7, 5).setPiece(mirrorBoard.getSquare(7, 7).getPiece());
						mirrorBoard.getSquare(7, 5).getPiece().move();
						mirrorBoard.getSquare(7, 7).setPiece(null);
						break;
					case 2:
						squares[0][2].setPiece(selectedSquare.getPiece());
						squares[0][2].getPiece().move();
						squares[0][4].setPiece(null);
						squares[0][3].setPiece(squares[0][0].getPiece());
						squares[0][3].getPiece().move();
						squares[0][0].setPiece(null);
						mirrorBoard.getSquare(7, 5).setPiece(mirrorBoard.getSelectedSquare().getPiece());
						mirrorBoard.getSquare(7, 5).getPiece().move();
						mirrorBoard.getSquare(7, 3).setPiece(null);
						mirrorBoard.getSquare(7, 4).setPiece(mirrorBoard.getSquare(7, 7).getPiece());
						mirrorBoard.getSquare(7, 4).getPiece().move();
						mirrorBoard.getSquare(7, 7).setPiece(null);
						break;
					case 5:
						squares[0][5].setPiece(selectedSquare.getPiece());
						squares[0][5].getPiece().move();
						squares[0][3].setPiece(null);
						squares[0][4].setPiece(squares[0][7].getPiece());
						squares[0][4].getPiece().move();
						squares[0][7].setPiece(null);
						mirrorBoard.getSquare(7, 2).setPiece(mirrorBoard.getSelectedSquare().getPiece());
						mirrorBoard.getSquare(7, 2).getPiece().move();
						mirrorBoard.getSquare(7, 4).setPiece(null);
						mirrorBoard.getSquare(7, 3).setPiece(mirrorBoard.getSquare(7, 0).getPiece());
						mirrorBoard.getSquare(7, 3).getPiece().move();
						mirrorBoard.getSquare(7, 0).setPiece(null);
						break;
					case 6:
						squares[0][6].setPiece(selectedSquare.getPiece());
						squares[0][6].getPiece().move();
						squares[0][4].setPiece(null);
						squares[0][5].setPiece(squares[0][7].getPiece());
						squares[0][5].getPiece().move();
						squares[0][7].setPiece(null);
						mirrorBoard.getSquare(7, 1).setPiece(mirrorBoard.getSelectedSquare().getPiece());
						mirrorBoard.getSquare(7, 1).getPiece().move();
						mirrorBoard.getSquare(7, 3).setPiece(null);
						mirrorBoard.getSquare(7, 2).setPiece(mirrorBoard.getSquare(7, 0).getPiece());
						mirrorBoard.getSquare(7, 2).getPiece().move();
						mirrorBoard.getSquare(7, 0).setPiece(null);
						break;
					}
					break;
				case 7:
					switch (x) {
					case 1:
						squares[7][1].setPiece(selectedSquare.getPiece());
						squares[7][1].getPiece().move();
						squares[7][3].setPiece(null);
						squares[7][2].setPiece(squares[7][0].getPiece());
						squares[7][2].getPiece().move();
						squares[7][0].setPiece(null);
						mirrorBoard.getSquare(0, 6).setPiece(mirrorBoard.getSelectedSquare().getPiece());
						mirrorBoard.getSquare(0, 6).getPiece().move();
						mirrorBoard.getSquare(0, 4).setPiece(null);
						mirrorBoard.getSquare(0, 5).setPiece(mirrorBoard.getSquare(0, 7).getPiece());
						mirrorBoard.getSquare(0, 5).getPiece().move();
						mirrorBoard.getSquare(0, 7).setPiece(null);
						break;
					case 2:
						squares[7][2].setPiece(selectedSquare.getPiece());
						squares[7][2].getPiece().move();
						squares[7][4].setPiece(null);
						squares[7][3].setPiece(squares[7][0].getPiece());
						squares[7][3].getPiece().move();
						squares[7][0].setPiece(null);
						mirrorBoard.getSquare(0, 5).setPiece(mirrorBoard.getSelectedSquare().getPiece());
						mirrorBoard.getSquare(0, 5).getPiece().move();
						mirrorBoard.getSquare(0, 3).setPiece(null);
						mirrorBoard.getSquare(0, 4).setPiece(mirrorBoard.getSquare(0, 7).getPiece());
						mirrorBoard.getSquare(0, 4).getPiece().move();
						mirrorBoard.getSquare(0, 7).setPiece(null);
						break;
					case 5:
						squares[7][5].setPiece(selectedSquare.getPiece());
						squares[7][5].getPiece().move();
						squares[7][3].setPiece(null);
						squares[7][4].setPiece(squares[7][7].getPiece());
						squares[7][4].getPiece().move();
						squares[7][7].setPiece(null);
						mirrorBoard.getSquare(0, 2).setPiece(mirrorBoard.getSelectedSquare().getPiece());
						mirrorBoard.getSquare(0, 2).getPiece().move();
						mirrorBoard.getSquare(0, 4).setPiece(null);
						mirrorBoard.getSquare(0, 3).setPiece(mirrorBoard.getSquare(0, 0).getPiece());
						mirrorBoard.getSquare(0, 3).getPiece().move();
						mirrorBoard.getSquare(0, 0).setPiece(null);
						break;
					case 6:
						squares[7][6].setPiece(selectedSquare.getPiece());
						squares[7][6].getPiece().move();
						squares[7][4].setPiece(null);
						squares[7][5].setPiece(squares[7][7].getPiece());
						squares[7][5].getPiece().move();
						squares[7][7].setPiece(null);
						mirrorBoard.getSquare(0, 1).setPiece(mirrorBoard.getSelectedSquare().getPiece());
						mirrorBoard.getSquare(0, 1).getPiece().move();
						mirrorBoard.getSquare(0, 3).setPiece(null);
						mirrorBoard.getSquare(0, 2).setPiece(mirrorBoard.getSquare(0, 0).getPiece());
						mirrorBoard.getSquare(0, 2).getPiece().move();
						mirrorBoard.getSquare(0, 0).setPiece(null);
						break;
					}
					break;
				}
				changeTurn();
				mirrorBoard.changeTurn();
				setSelectedSquare(null);
				mirrorBoard.setSelectedSquare(null);
			} else if (selectedSquare != null && squares[y][x].getPiece() == null) {
				List<int[]> candidateMoves = clarifyCandidateMoves(selectedSquare.getPiece().getCandidateMoves(selectedSquare.getRow(), selectedSquare.getCol()));
				for (int candidateMove[] : candidateMoves) {
					if (Arrays.equals(candidateMove, new int[] { y, x })) {
						squares[y][x].setPiece(selectedSquare.getPiece());
						selectedSquare.setPiece(null);
						changeTurn();
						if (testForCheck()) {
							// Revert
							selectedSquare.setPiece(squares[y][x].getPiece());
							squares[y][x].setPiece(null);
							changeTurn();
							break;
						}
						mirrorBoard.getSquare(7 - y, 7 - x).setPiece(mirrorBoard.getSelectedSquare().getPiece());
						mirrorBoard.getSelectedSquare().setPiece(null);
						mirrorBoard.changeTurn();
						if ((squares[y][x].getPiece() instanceof Pawn)) {
							if (y == 0 || y == 7) {
								squares[y][x].setPiece(new Queen(squares[y][x].getPiece().getColor()));
								mirrorBoard.getSquare(7 - y, 7 - x).setPiece(new Queen(mirrorBoard.getSquare(7 - y, 7 - x).getPiece().getColor()));
							}
						}
						break;
					}
				}
				setSelectedSquare(null);
				mirrorBoard.setSelectedSquare(null);
			} else if (selectedSquare != null && !squares[y][x].getPiece().getColor().equals(turn)) {
				List<int[]> threatenedSquares = clarifyThreatenedSquares(selectedSquare.getPiece().getThreatenedSquares(selectedSquare.getRow(), selectedSquare.getCol()));
				for (int threatenedSquare[] : threatenedSquares) {
					if (Arrays.equals(threatenedSquare, new int[] { y, x })) {
						Piece takenPiece = squares[y][x].getPiece();
						squares[y][x].setPiece(selectedSquare.getPiece());
						selectedSquare.setPiece(null);
						changeTurn();
						if (testForCheck()) {
							// Revert
							selectedSquare.setPiece(squares[y][x].getPiece());
							squares[y][x].setPiece(takenPiece);
							changeTurn();
							break;
						}
						mirrorBoard.getSquare(7 - y, 7 - x).setPiece(mirrorBoard.getSelectedSquare().getPiece());
						mirrorBoard.getSelectedSquare().setPiece(null);
						mirrorBoard.changeTurn();
						if ((squares[y][x].getPiece() instanceof Pawn)) {
							if (y == 0 || y == 7) {
								squares[y][x].setPiece(new Queen(squares[y][x].getPiece().getColor()));
								mirrorBoard.getSquare(7 - y, 7 - x).setPiece(new Queen(mirrorBoard.getSquare(7 - y, 7 - x).getPiece().getColor()));
							}
						}
						break;
					}
				}
				setSelectedSquare(null);
				mirrorBoard.setSelectedSquare(null);
			}
			
			mirrorBoard.repaint();
			repaint();
		}
		
		private List<int[]> clarifyThreatenedSquares(List<ArrayList<int[]>> unboundedThreatenedSquares) {
			List<int[]> boundedThreatenedSquares = new ArrayList<>();
			for (int i = 0; i < unboundedThreatenedSquares.size(); i++) {
				for (int coords[] : unboundedThreatenedSquares.get(i)) {
					if (squares[coords[0]][coords[1]].getPiece() == null) {
						boundedThreatenedSquares.add(coords);
					} else if (squares[coords[0]][coords[1]].getPiece().getColor().equals(turn)) {
						break;
					} else if (!squares[coords[0]][coords[1]].getPiece().getColor().equals(turn)) {
						boundedThreatenedSquares.add(coords);
						break;
					}
				}
			}
			return boundedThreatenedSquares;
		}
		
		private List<int[]> clarifyCandidateMoves(List<ArrayList<int[]>> unboundedCandidateMoves) {
			List<int[]> boundedCandidateMoves = new ArrayList<>();
			for (int i = 0; i < unboundedCandidateMoves.size(); i++) {
				for (int coords[] : unboundedCandidateMoves.get(i)) {
					if (squares[coords[0]][coords[1]].getPiece() == null) {
						boundedCandidateMoves.add(coords);
					}
				}
			}
			return boundedCandidateMoves;
		}
		
		private boolean testForCheck() {
			Color opponentColor = turn.equals(Color.white) ? Color.black : Color.white;
			
			List<int[]> threatenedSquares = new ArrayList<>();
			int[] kingCoords = new int[2];
			for (int i = 0; i < 8; i++) {
				for (int j = 0; j < 8; j++) {
					if (squares[i][j].getPiece() != null && squares[i][j].getPiece().getColor().equals(turn)) {
						threatenedSquares.addAll(clarifyThreatenedSquares(squares[i][j].getPiece().getThreatenedSquares(i, j)));
					} else if (squares[i][j].getPiece() != null && 
							squares[i][j].getPiece().getColor().equals(opponentColor) &&
							squares[i][j].getPiece() instanceof King) {
						kingCoords = new int[] { i, j };
					}
				}
			}
			
			for (int threatenedSquare[] : threatenedSquares) {
				if (Arrays.equals(threatenedSquare, kingCoords)) {
					return true;
				}
			}
			return false;
		}
		
		private boolean canCastle(int row, int col) {
			boolean canCastle = true;
			if (selectedSquare.getPiece() instanceof King) {
				King king = (King) selectedSquare.getPiece();
				
				if (selectedSquare.getRow() == 7 && selectedSquare.getCol() == 4) {
					if (row == 7 && col == 2) {
						if (!(squares[7][0].getPiece() instanceof Rook)) {
							return false;
						}
						Rook rook = (Rook) (squares[7][0].getPiece());
						canCastle = canCastle && !king.hasMoved();
						canCastle = canCastle && !rook.hasMoved();
						canCastle = canCastle && squares[7][1].getPiece() == null;
						canCastle = canCastle && squares[7][2].getPiece() == null;
						canCastle = canCastle && squares[7][3].getPiece() == null;
						if (canCastle) {
							changeTurn();
							for (int i = 3; i > 1; i--) {
								squares[7][i].setPiece(king);
								squares[7][i+1].setPiece(null);
								if (testForCheck()) {
									selectedSquare.setPiece(king);
									squares[7][i] = null;
									changeTurn();
									return false;
								}
							}
							changeTurn();
							selectedSquare.setPiece(king);
							squares[7][2].setPiece(null);
							return true;
						}
					} else if (row == 7 && col == 6) {
						if (!(squares[7][7].getPiece() instanceof Rook)) {
							return false;
						}
						Rook rook = (Rook) (squares[7][7].getPiece());
						canCastle = canCastle && !king.hasMoved();
						canCastle = canCastle && !rook.hasMoved();
						canCastle = canCastle && squares[7][5].getPiece() == null;
						canCastle = canCastle && squares[7][6].getPiece() == null;
						if (canCastle) {
							changeTurn();
							for (int i = 5; i < 7; i++) {
								squares[7][i].setPiece(king);
								squares[7][i-1].setPiece(null);
								if (testForCheck()) {
									selectedSquare.setPiece(king);
									squares[7][i] = null;
									changeTurn();
									return false;
								}
							}
							changeTurn();
							selectedSquare.setPiece(king);
							squares[7][6].setPiece(null);
							return true;
						}
					}
				} else if (selectedSquare.getRow() == 7 && selectedSquare.getCol() == 3) {
					if (row == 7 && col == 1) {
						if (!(squares[7][0].getPiece() instanceof Rook)) {
							return false;
						}
						Rook rook = (Rook) (squares[7][0].getPiece());
						canCastle = canCastle && !king.hasMoved();
						canCastle = canCastle && !rook.hasMoved();
						canCastle = canCastle && squares[7][1].getPiece() == null;
						canCastle = canCastle && squares[7][2].getPiece() == null;
						if (canCastle) {
							changeTurn();
							for (int i = 2; i > 0; i--) {
								squares[7][i].setPiece(king);
								squares[7][i+1].setPiece(null);
								if (testForCheck()) {
									selectedSquare.setPiece(king);
									squares[7][i] = null;
									changeTurn();
									return false;
								}
							}
						}
						changeTurn();
						selectedSquare.setPiece(king);
						squares[7][1].setPiece(null);
						return true;
					} else if (row == 7 && col == 5) {
						if (!(squares[7][7].getPiece() instanceof Rook)) {
							return false;
						}
						Rook rook = (Rook) (squares[7][7].getPiece());
						canCastle = canCastle && !king.hasMoved();
						canCastle = canCastle && !rook.hasMoved();
						canCastle = canCastle && squares[7][4].getPiece() == null;
						canCastle = canCastle && squares[7][5].getPiece() == null;
						canCastle = canCastle && squares[7][6].getPiece() == null;
						if (canCastle) {
							changeTurn();
							for (int i = 4; i < 7; i++) {
								squares[7][i].setPiece(king);
								squares[7][i-1].setPiece(null);
								if (testForCheck()) {
									selectedSquare.setPiece(king);
									squares[7][i] = null;
									changeTurn();
									return false;
								}
							}
						}
						changeTurn();
						selectedSquare.setPiece(king);
						squares[7][6].setPiece(null);
						return true;
					}
				} else if (selectedSquare.getRow() == 0 && selectedSquare.getCol() == 4) {
					if (row == 0 && col == 2) {
						if (!(squares[0][0].getPiece() instanceof Rook)) {
							return false;
						}
						Rook rook = (Rook) (squares[0][0].getPiece());
						canCastle = canCastle && !king.hasMoved();
						canCastle = canCastle && !rook.hasMoved();
						canCastle = canCastle && squares[0][1].getPiece() == null;
						canCastle = canCastle && squares[0][2].getPiece() == null;
						canCastle = canCastle && squares[0][3].getPiece() == null;
						if (canCastle) {
							changeTurn();
							for (int i = 3; i > 1; i--) {
								squares[0][i].setPiece(king);
								squares[0][i+1].setPiece(null);
								if (testForCheck()) {
									selectedSquare.setPiece(king);
									squares[0][i] = null;
									changeTurn();
									return false;
								}
							}
						}
						changeTurn();
						selectedSquare.setPiece(king);
						squares[0][2].setPiece(null);
						return true;
					} else if (row == 0 && col == 6) {
						if (!(squares[0][7].getPiece() instanceof Rook)) {
							return false;
						}
						Rook rook = (Rook) (squares[0][7].getPiece());
						canCastle = canCastle && !king.hasMoved();
						canCastle = canCastle && !rook.hasMoved();
						canCastle = canCastle && squares[0][5].getPiece() == null;
						canCastle = canCastle && squares[0][6].getPiece() == null;
						if (canCastle) {
							changeTurn();
							for (int i = 5; i < 7; i++) {
								squares[0][i].setPiece(king);
								squares[0][i-1].setPiece(null);
								if (testForCheck()) {
									selectedSquare.setPiece(king);
									squares[0][i] = null;
									changeTurn();
									return false;
								}
							}
						}
						changeTurn();
						selectedSquare.setPiece(king);
						squares[0][6].setPiece(null);
						return true;
					}
				} else if (selectedSquare.getRow() == 0 && selectedSquare.getCol() == 3) {
					if (row == 0 && col == 1) {
						if (!(squares[0][0].getPiece() instanceof Rook)) {
							return false;
						}
						Rook rook = (Rook) (squares[0][0].getPiece());
						canCastle = canCastle && !king.hasMoved();
						canCastle = canCastle && !rook.hasMoved();
						canCastle = canCastle && squares[0][1].getPiece() == null;
						canCastle = canCastle && squares[0][2].getPiece() == null;
						if (canCastle) {
							changeTurn();
							for (int i = 2; i > 0; i--) {
								squares[0][i].setPiece(king);
								squares[0][i+1].setPiece(null);
								if (testForCheck()) {
									selectedSquare.setPiece(king);
									squares[0][i] = null;
									changeTurn();
									return false;
								}
							}
						}
						changeTurn();
						selectedSquare.setPiece(king);
						squares[0][1].setPiece(null);
						return true;
					} else if (row == 0 && col == 5) {
						if (!(squares[0][7].getPiece() instanceof Rook)) {
							return false;
						}
						Rook rook = (Rook) (squares[0][7].getPiece());
						canCastle = canCastle && !king.hasMoved();
						canCastle = canCastle && !rook.hasMoved();
						canCastle = canCastle && squares[0][4].getPiece() == null;
						canCastle = canCastle && squares[0][5].getPiece() == null;
						canCastle = canCastle && squares[0][6].getPiece() == null;
						if (canCastle) {
							changeTurn();
							for (int i = 4; i < 7; i++) {
								squares[0][i].setPiece(king);
								squares[0][i-1].setPiece(null);
								if (testForCheck()) {
									selectedSquare.setPiece(king);
									squares[0][i] = null;
									changeTurn();
									return false;
								}
							}
						}
						changeTurn();
						selectedSquare.setPiece(king);
						squares[0][6].setPiece(null);
						return true;
					}
				}
			}
			return false;
		}
	}
}
