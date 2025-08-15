package badIceCream.model.menu;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MainMenuTest {

    private MainMenu menu;

    @BeforeEach
    void setUp() {
        menu = new MainMenu();
    }

    @Test
    void testStates() {
        Assertions.assertTrue(menu.isSelectedStart());
        Assertions.assertFalse(menu.isSelectedInstructions());
        Assertions.assertFalse(menu.isSelectedExit());

        menu.nextEntry();

        Assertions.assertFalse(menu.isSelectedStart());
        Assertions.assertTrue(menu.isSelectedInstructions());
        Assertions.assertFalse(menu.isSelectedExit());

        menu.nextEntry();

        Assertions.assertFalse(menu.isSelectedStart());
        Assertions.assertFalse(menu.isSelectedInstructions());
        Assertions.assertTrue(menu.isSelectedExit());
    }
}
