package badIceCream.model.menu;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class GameOverMenuTest {

    private GameOverMenu menu;

    @BeforeEach
    void setUp() {
        menu = new GameOverMenu();
    }

    @Test
    void testStates() {
        Assertions.assertTrue(menu.isSelectedPlayAgain());
        Assertions.assertFalse(menu.isSelectedQuitToMainMenu());

        menu.nextEntry();

        Assertions.assertFalse(menu.isSelectedPlayAgain());
        Assertions.assertTrue(menu.isSelectedQuitToMainMenu());
    }
}
