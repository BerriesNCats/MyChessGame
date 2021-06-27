package com.game.MyChessGame;

import com.game.MyChessGame.models.board.Board;
import com.game.MyChessGame.models.gui.Table;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.IOException;

@SpringBootApplication
public class MyChessGameApplication {

	public static void main(String[] args) throws IOException {
//		SpringApplication.run(MyChessGameApplication.class, args);
		SpringApplicationBuilder springApplicationBuilder = new SpringApplicationBuilder(MyChessGameApplication.class);
		springApplicationBuilder.headless(false);
		ConfigurableApplicationContext context = springApplicationBuilder.run(args);

		Board board = Board.createStandardBoard();

		System.out.println(board);

		Table table = new Table();
	}

}
