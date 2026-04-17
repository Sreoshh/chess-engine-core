import core.*;
import game.*;
import engine.*;
import java.util.*;
import ui.console.ConsoleUI;

public class Main {
    public static void main(String[] args) {

        Game game = new Game();
        Engine engine = new Engine();
        Scanner sc = new Scanner(System.in);

        ConsoleUI ui = new ConsoleUI();
        ui.start();

        int moveCount = 0;

        while (!game.isCheckmate() && !game.isStalemate()) {
            {

                moveCount++;
                System.out.println("Move #" + moveCount);
                if (moveCount > 200) {
                    System.out.println("Game stopped (move limit reached)");
                    break;
                }

            game.getBoard().printBoard();

            if (game.isCheckmate()) {
                System.out.println("Checkmate! Winner: " + game.getCurrentTurn().opposite());
                break;
            }

            if (game.isStalemate()) {
                System.out.println("Stalemate!");
                break;
            }

            if (false) {
                // Player move
                System.out.println("Enter move (fromRow fromCol toRow toCol): ");
                int fr = sc.nextInt();
                int fc = sc.nextInt();
                int tr = sc.nextInt();
                int tc = sc.nextInt();

                Move move = new Move(new Position(fr, fc), new Position(tr, tc));

                if (!game.makeMove(move)) {
                    System.out.println("Invalid move! Try again.");
                }

            } else {
                // AI move
                System.out.println("AI thinking...");
                Move bestMove = engine.findBestMove(game.getBoard(), Color.BLACK);

                System.out.println("AI plays: " + bestMove);
                game.makeMove(bestMove);
            }
        }
    }

        try {
            Thread.sleep(500); // 0.5 seconds
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}