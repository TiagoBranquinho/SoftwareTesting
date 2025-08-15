package badIceCream.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PositionTest {

    private Position position;
    @BeforeEach
    void setUp() {
        position = new Position(2, 3);
    }

    @Test
    void testGetters() {
        Assertions.assertEquals(2, position.getX());
        Assertions.assertEquals(3, position.getY());
    }

    @Test
    void testSets() {
        position.setX(7);
        position.setY(10);
        Assertions.assertEquals(new Position(7,10), position);
    }

    @Test
    void testGetLeft() {
        Position left = position.getLeft();
        Position newPos = new Position(1, 3);
        Assertions.assertEquals(newPos, left);
    }

    @Test
    void testGetRight() {
        Position right = position.getRight();
        Position newPos = new Position(3, 3);
        Assertions.assertEquals(newPos, right);
    }

    @Test
    void testGetUp() {
        Position up = position.getUp();
        Position newPos = new Position(2, 2);
        Assertions.assertEquals(newPos, up);
    }

    @Test
    void testGetDown() {
        Position down = position.getDown();
        Position newPos = new Position(2, 4);
        Assertions.assertEquals(newPos, down);
    }

    @Test
    void testEquals() {
        Position position2 = new Position(2, 4);
        String position3 = "position";
        Assertions.assertTrue(position.equals(position));
        Assertions.assertFalse(position.equals(position3));
        Assertions.assertFalse(position.equals(position2));
    }

    @Test
    void testHashCode() {
        Position position1 = new Position(5, 5);
        Position position2 = new Position(5, 5);
        Position position3 = new Position(5, 6);
        Assertions.assertEquals(position1.hashCode(), position2.hashCode());
        Assertions.assertNotEquals(position1.hashCode(), position3.hashCode());
    }
}