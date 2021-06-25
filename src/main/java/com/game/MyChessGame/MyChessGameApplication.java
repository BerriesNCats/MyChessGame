package com.game.MyChessGame;

import com.game.MyChessGame.models.board.Board;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MyChessGameApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyChessGameApplication.class, args);

//		Board board = Board.createStandardBoard();
//
//		System.out.println(board);
	}

}
