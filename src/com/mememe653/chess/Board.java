package com.mememe653.chess;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;
import javax.swing.event.MouseInputAdapter;

import com.mememe653.chess.pieces.Bishop;
import com.mememe653.chess.pieces.King;
import com.mememe653.chess.pieces.Knight;
import com.mememe653.chess.pieces.Pawn;
import com.mememe653.chess.pieces.Queen;
import com.mememe653.chess.pieces.Rook;

public class Board extends JPanel {
	
	enum BoardNum {
		One,
		Two
	};
	
	public static final int WIDTH = 480;
	public static final int HEIGHT = 480;
	
	private Board mirrorBoard;
	
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
				squares[1][j].setPiece(new Pawn(Color.black));
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
				squares[6][j].setPiece(new Pawn(Color.white));
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
				this.squares[6][j].setPiece(new Pawn(Color.black));
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
				this.squares[1][j].setPiece(new Pawn(Color.white));
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
	
	private class MouseListener extends MouseInputAdapter {
		
		@Override
		public void mouseClicked(MouseEvent e) {
			super.mouseClicked(e);
			
			int x = e.getX();
			int y = e.getY();
			
			x /= Square.WIDTH;
			y /= Square.WIDTH;
			
			squares[y][x].setSelected();
			mirrorBoard.getSquare(7 - y, 7 - x).setSelected();
			mirrorBoard.repaint();
			repaint();
		}
	}
}
