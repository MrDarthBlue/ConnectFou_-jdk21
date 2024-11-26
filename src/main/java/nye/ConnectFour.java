package nye;

import nye.adatbazis.Database;
import nye.jatekosok.AIPlayer;
import nye.jatekosok.Player;
import nye.tabla.Board;

import java.io.IOException;
import java.util.Scanner;

public class ConnectFour {
    public static void main(String[] args) {
        //betöltjük az adatbázist
        Database.initializeDatabase();

        Scanner scanner = new Scanner(System.in);
        String filePath = "game_state.txt"; // a játékállást tartalmazó fájl elérési útja, neve
        Board board = null;

        // felhasználó felé kérdés hogy betöltjük vagy nem
        System.out.println("Szeretnéd betölteni az előző játék állását? (y/n):");
        String loadResponse = scanner.nextLine().trim().toLowerCase();

        if (loadResponse.equals("y")) {
            try {
                board = Board.loadFromFile(filePath); // játékállás betöltése
            } catch (IOException e) {
            }
        } else {
            // ha nem töltjük be akkor egy új üreset hoz létre
            board = new Board(6, 7); // alap tábla méret 6x7
        }

        // játékos nevének bekérése
        System.out.println("Neve a Sárga (Y) Játékosnak:");
        String playerName = scanner.nextLine();
        Player human = new Player(playerName, 'Y');

        AIPlayer ai = new AIPlayer("Gép", 'R');

        System.out.println("Üdvözöllek a Connect Four játékban, " + human.getName() + "!");
        board.display();

        boolean gameOver = false;
        //a játék fő menete: a humán és AI lépéseit váltakozva kezeli.
        while (!gameOver) {
            // humán játékos lépése
            System.out.println("Te lépésed (válassz egy oszlopot: a, b, c, ...):");
            String move = scanner.nextLine();
            while (!board.makeMove(human, move)) { // ellenőrzi hogy érvényes
                System.out.println("Érvénytelen lépés! Próbáld újra:");
                move = scanner.nextLine();
            }
            board.display();

            if (board.checkWin(human.getColor())) {
                System.out.println("Győztél!");
                // játékos "nyereményének száma" frissítése az adatbázisban
                Database.updatePlayerWins(human.getName());
                gameOver = true;
                break;
            }

            if (board.isFull()) {
                System.out.println("Döntetlen!");
                break;
            }

            // ai lépése
            System.out.println("A gép lép...");
            String aiMove = ai.chooseMove(board);
            board.makeMove(ai, aiMove);
            board.display();

            if (board.checkWin(ai.getColor())) {
                System.out.println("Gép győzőtt!");
                gameOver = true;
                break;
            }

            if (board.isFull()) {
                System.out.println("Döntetlen!");
                break;
            }
        }

        // játék állásának mentése a fájlba
        try {
            board.saveToFile(filePath);
        } catch (IOException e) {
        }
        // high score táblázat megjelenítése
        Database.displayHighScores();
        scanner.close();
    }
}



