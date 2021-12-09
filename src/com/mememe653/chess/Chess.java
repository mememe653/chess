package com.mememe653.chess;

import java.awt.EventQueue;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.mememe653.chess.Board.BoardNum;

public class Chess extends JFrame {
	
	private final Board board1 = new Board(BoardNum.One);
	private final Board board2 = new Board(BoardNum.Two);

	public Chess() {
		initUI();
	}
	
	private void initUI() {
		JPanel container = new JPanel();
		
		container.setLayout(new GridLayout(1, 2, 20, 20));
		container.add(board1);
		container.add(board2);
		add(container);
		
		board1.setMirrorBoard(board2);
		board2.setMirrorBoard(board1);
		
		setResizable(false);
		pack();
		setTitle("Chess");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public static void main(String args[]) {
		EventQueue.invokeLater(() -> {
			JFrame frame = new Chess();
			frame.setVisible(true);
		});
	}
}
