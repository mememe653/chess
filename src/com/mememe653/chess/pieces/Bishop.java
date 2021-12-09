package com.mememe653.chess.pieces;

import java.awt.Color;
import java.awt.Image;

import javax.swing.ImageIcon;

public class Bishop implements Piece {
	
	private final Color color;
	private final ImageIcon ii;
	
	public Bishop(Color color) {
		this.color = color;
		if (color.equals(Color.white)) {
			ii = new ImageIcon("src/resources/White Bishop.png");
		} else {
			ii = new ImageIcon("src/resources/Black Bishop.png");
		}
	}

	@Override
	public int[][][] getThreatenedSquares(int row, int col) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int[][][] getCandidateMoves(int row, int col) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Image getImage() {
		return ii.getImage();
	}

}
