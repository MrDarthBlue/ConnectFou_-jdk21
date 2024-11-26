package nye.jatekosok;

import nye.tabla.Board;

import java.util.Random;

public class AIPlayer extends Player {
    private final Random random = new Random();

    public AIPlayer(String name, char color) {
        super(name, color);
    } //ai játékos létrehozása névvel és "jellel"

    public String chooseMove(Board board) {
        int colIndex;
        do {
            colIndex = random.nextInt(7); // 0-6 között választ egy véletlen oszlopindexet
        } while (!board.isColumnAvailable(indexToColumn(colIndex))); // addig ismétli, amíg nem talál üres oszlopot.

        return indexToColumn(colIndex);
    }

    private String indexToColumn(int index) {
        return String.valueOf((char) ('a' + index)); } // az indexet oszlopbetűvé alakítja (0='a',1='b',stb.)
}
