package badIceCream.utils;

import badIceCream.model.Position;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

public class NodeTest {

    private Position mockPosition;
    @BeforeEach
    void setUp() {
        mockPosition = mock(Position.class);
    }
    @Test
    void testCompareToWithEqualCostAndHeuristic() {
        Node node1 = new Node(mockPosition, 5, 3, null);
        Node node2 = new Node(mockPosition, 5, 3, null);

        assertEquals(0, node1.compareTo(node2));

        Node node3 = new Node(mockPosition, 3, 2, null);
        Node node4 = new Node(mockPosition, 5, 3, null);

        assertTrue(node3.compareTo(node4) < 0);

        Node node5 = new Node(mockPosition, 7, 5, null);
        Node node6 = new Node(mockPosition, 5, 3, null);

        assertTrue(node5.compareTo(node6) > 0);
    }
}
