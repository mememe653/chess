package com.mememe653.chess.pieces;

import java.awt.Color;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

public interface Piece {

	Image getImage();
	Color getColor();
	List<ArrayList<int[]>> getThreatenedSquares(int row, int col);
	List<ArrayList<int[]>> getCandidateMoves(int row, int col);
}
