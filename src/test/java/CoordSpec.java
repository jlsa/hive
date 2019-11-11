import nl.josaho.*;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class CoordSpec {
    @Test
    void ACoordHasTwoIntegerCoordinates() {
        Coord coord = new Coord(0, 0);
        assertTrue(coord.q instanceof Integer);
        assertTrue(coord.r instanceof Integer);
    }
}
