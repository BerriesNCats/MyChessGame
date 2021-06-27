package com.game.MyChessGame;

import com.game.MyChessGame.models.board.Board;
import com.game.MyChessGame.models.gui.Table;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class MyChessGameApplication {

	public static void main(String[] args) {
//		SpringApplication.run(MyChessGameApplication.class, args);
		SpringApplicationBuilder builder = new SpringApplicationBuilder(MyChessGameApplication.class);
		builder.headless(false);
		ConfigurableApplicationContext context = builder.run(args);

		Board board = Board.createStandardBoard();

		System.out.println(board);

		Table table = new Table();
	}

}
