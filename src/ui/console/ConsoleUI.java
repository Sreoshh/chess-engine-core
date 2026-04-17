package ui.console;

import core.*;
import game.*;
import engine.*;
import java.util.*;

public class ConsoleUI {

    private Game game;
    private Engine engine;
    private Scanner sc;

    public ConsoleUI() {
        game = new Game();
        engine = new Engine();
        sc = new Scanner(System.in);
    }

    public void start() {

        while (!game.isCheckmate() && !game.isStalemate()) {

            game.getBoard().printBoard();

            if (game.getCurrentTurn() == Color.WHITE) {
                playerMove();
            } else {
                aiMove();
            }

            pause();
        }

        endGame();
    }

    private void playerMove() {
        System.out.println("Enter move (fromRow fromCol toRow toCol): ");

        int fr = sc.nextInt();
        int fc = sc.nextInt();
        int tr = sc.nextInt();
        int tc = sc.nextInt();

        Move move = new Move(new Position(fr, fc), new Position(tr, tc));

        if (!game.makeMove(move)) {
            System.out.println("Invalid move! Try again.");
            playerMove();
        }
    }

    private void aiMove() {
        System.out.println("AI thinking...");

        Move bestMove = engine.findBestMove(game.getBoard(), Color.BLACK);

        System.out.println("AI plays: " + bestMove);
        game.makeMove(bestMove);
    }

    private void pause() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void endGame() {
        game.getBoard().printBoard();

        if (game.isCheckmate()) {
            System.out.println("Checkmate! Winner: " + game.getCurrentTurn().opposite());
        } else {
            System.out.println("Stalemate!");
        }
    }
}