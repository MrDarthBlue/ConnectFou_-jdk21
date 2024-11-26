package nye.jatekosok;
import java.util.Objects;

public class Player {
    private final String name; //játékos neve
    private final char color; //játékos szine

    public Player(String name, char color) {
        this.name = name; // A megadott nevet beállítja.
        this.color = color;// A megadott szimbólumot beállítja.
    }

    public String getName() {
        return name;
    }

    public char getColor() {
        return color;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return color == player.color && Objects.equals(name, player.name);  // Ellenőrzi, hogy a szimbólumok és a nevek egyenlőek-e.
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, color);
    }

    @Override
    public String toString() {
        return String.format("Player[name=%s, color=%c]", name, color);
    }
}
