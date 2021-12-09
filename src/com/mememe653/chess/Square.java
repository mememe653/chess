package com.mememe653.chess;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import com.mememe653.chess.pieces.Piece;

public class Square extends JPanel {
	
	public static final int WIDTH = Board.WIDTH / 8;
	
	private final Color color;
	private Piece piece;
	private final int row;
	private final int col;

	public Square(Color color, int row, int col) {
		this.color = color;
		this.row = row;
		this.col = col;
	}
	
	public void paint(Graphics g) {
		if (color.equals(Color.white)) {
			g.setColor(Color.lightGray);
		} else if (color.equals(Color.black)) {
			g.setColor(Color.darkGray);
		}
		g.fillRect(col * WIDTH, row * WIDTH, WIDTH, WIDTH);
		
		if (piece != null) {
			Image pieceImage = piece.getImage();
			g.drawImage(pieceImage, col * WIDTH, row * WIDTH, this);
		}
	}
	
	public void setPiece(Piece piece) {
		this.piece = piece;
	}
}
