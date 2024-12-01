package nye.eredmeny;
import java.util.Objects;

public final class GameResult {
    private final String winnerName;
    private final char winnerColor;
    private final boolean isDraw;

    public GameResult(String winnerName, char winnerColor, boolean isDraw) {
        this.winnerName = winnerName;
        this.winnerColor = winnerColor;
        this.isDraw = isDraw;
    }

    public String getWinnerName() {
        return winnerName;
    }

    public char getWinnerColor() {
        return winnerColor;
    }

    public boolean isDraw() {
        return isDraw;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GameResult that = (GameResult) o;
        return winnerColor == that.winnerColor && isDraw == that.isDraw && Objects.equals(winnerName, that.winnerName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(winnerName, winnerColor, isDraw);
    }

    @Override
    public String toString() {
        return isDraw
                ? "GameResult[draw=true]"
                : String.format("GameResult[winner=%s, color=%c]", winnerName, winnerColor);
    }
}

