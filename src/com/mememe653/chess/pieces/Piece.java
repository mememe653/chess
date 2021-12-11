package com.mememe653.chess.pieces;

import java.awt.Color;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

public interface Piece {

	public Image getImage();
	public Color getColor();
	public List<ArrayList<int[]>> getThreatenedSquares(int row, int col);
	public List<ArrayList<int[]>> getCandidateMoves(int row, int col);
	public void move();
	
}
