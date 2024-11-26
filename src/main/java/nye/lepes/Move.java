package nye.lepes;
import java.util.Objects;

public final class Move {
    private final String playerName;
    private final char color;
    private final String column;

    public Move(String playerName, char color, String column) {
        this.playerName = playerName; //betölti a játékos nevét
        this.color = color;   //játékos szine
        this.column = column;   //lépés oszlopa
    }

    public String getPlayerName() {
        return playerName;
    }

    public char getColor() {
        return color;
    }

    public String getColumn() {
        return column;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Move move = (Move) o;
        return color == move.color && Objects.equals(playerName, move.playerName) && Objects.equals(column, move.column);
    }         // ellenőrzi, hogy a szinek, a játékos neve és az oszlop egyenlőek-e.

    @Override
    public int hashCode() {
        return Objects.hash(playerName, color, column);
    }

    @Override
    public String toString() {
        return String.format("Move[player=%s, color=%c, column=%s]", playerName, color, column);
    }
}

