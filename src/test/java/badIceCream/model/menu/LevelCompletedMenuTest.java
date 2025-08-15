package badIceCream.model.menu;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LevelCompletedMenuTest {

    private LevelCompletedMenu menu;

    @BeforeEach
    void setUp() {
        menu = new LevelCompletedMenu();
    }

    @Test
    void testStates() {
        Assertions.assertTrue(menu.isSelectedNextLevel());
        Assertions.assertFalse(menu.isSelectedQuitToMainMenu());

        menu.nextEntry();

        Assertions.assertFalse(menu.isSelectedNextLevel());
        Assertions.assertTrue(menu.isSelectedQuitToMainMenu());
    }
}
