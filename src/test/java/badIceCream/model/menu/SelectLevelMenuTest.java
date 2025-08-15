package badIceCream.model.menu;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SelectLevelMenuTest {

    private PauseMenu menu;

    @BeforeEach
    void setUp() {
        menu = new PauseMenu();
    }

    @Test
    void testStates() {
        Assertions.assertTrue(menu.isSelectedResume());
        Assertions.assertFalse(menu.isSelectedMenu());

        menu.nextEntry();

        Assertions.assertFalse(menu.isSelectedResume());
        Assertions.assertTrue(menu.isSelectedMenu());
    }
}
