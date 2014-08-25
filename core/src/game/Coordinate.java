package game;

/**
 * Created 12.03.14 by vlaaad
 */
public class Coordinate {
    public int x;
    public int y;

    public Coordinate() {
    }

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Coordinate(Coordinate point) {
        this.x = point.x;
        this.y = point.y;
    }

    public Coordinate set(Coordinate point) {
        this.x = point.x;
        this.y = point.y;
        return this;
    }

    public Coordinate set(int x, int y) {
        this.x = x;
        this.y = y;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Coordinate that = (Coordinate) o;

        if (x != that.x) return false;
        if (y != that.y) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }

    @Override public String toString() {
        return "{" + x + ", " + y + '}';
    }
}
