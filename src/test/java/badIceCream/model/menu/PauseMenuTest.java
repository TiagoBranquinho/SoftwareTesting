package badIceCream.model.menu;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PauseMenuTest {

    private SelectLevelMenu menu;

    @BeforeEach
    void setUp() {
        menu = new SelectLevelMenu();
    }

    @Test
    void testStates() {
        Assertions.assertTrue(menu.isSelectedLevel1());
        Assertions.assertFalse(menu.isSelectedLevel2());
        Assertions.assertFalse(menu.isSelectedLevel3());
        Assertions.assertFalse(menu.isSelectedLevel4());
        Assertions.assertFalse(menu.isSelectedLevel5());

        menu.nextEntry();

        Assertions.assertFalse(menu.isSelectedLevel1());
        Assertions.assertTrue(menu.isSelectedLevel2());
        Assertions.assertFalse(menu.isSelectedLevel3());
        Assertions.assertFalse(menu.isSelectedLevel4());
        Assertions.assertFalse(menu.isSelectedLevel5());

        menu.nextEntry();

        Assertions.assertFalse(menu.isSelectedLevel1());
        Assertions.assertFalse(menu.isSelectedLevel2());
        Assertions.assertTrue(menu.isSelectedLevel3());
        Assertions.assertFalse(menu.isSelectedLevel4());
        Assertions.assertFalse(menu.isSelectedLevel5());


        menu.nextEntry();

        Assertions.assertFalse(menu.isSelectedLevel1());
        Assertions.assertFalse(menu.isSelectedLevel2());
        Assertions.assertFalse(menu.isSelectedLevel3());
        Assertions.assertTrue(menu.isSelectedLevel4());
        Assertions.assertFalse(menu.isSelectedLevel5());


        menu.nextEntry();

        Assertions.assertFalse(menu.isSelectedLevel1());
        Assertions.assertFalse(menu.isSelectedLevel2());
        Assertions.assertFalse(menu.isSelectedLevel3());
        Assertions.assertFalse(menu.isSelectedLevel4());
        Assertions.assertTrue(menu.isSelectedLevel5());
    }
}
