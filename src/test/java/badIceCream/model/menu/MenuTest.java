package badIceCream.model.menu;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class MenuTest {

    private Menu menu;

    @BeforeEach
    void setUp() {
        menu = new Menu(Arrays.asList("Option 1", "Option 2", "Option 3"));
    }

    @Test
    void testNextEntry() {
        Assertions.assertTrue(menu.isSelected(0));
        Assertions.assertFalse(menu.isSelected(1));
        Assertions.assertFalse(menu.isSelected(2));

        menu.nextEntry();
        Assertions.assertFalse(menu.isSelected(0));
        Assertions.assertTrue(menu.isSelected(1));
        Assertions.assertFalse(menu.isSelected(2));

        menu.nextEntry();
        Assertions.assertFalse(menu.isSelected(0));
        Assertions.assertFalse(menu.isSelected(1));
        Assertions.assertTrue(menu.isSelected(2));

        menu.nextEntry();
        Assertions.assertTrue(menu.isSelected(0));
        Assertions.assertFalse(menu.isSelected(1));
        Assertions.assertFalse(menu.isSelected(2));
    }

    @Test
    void testPreviousEntry() {
        Assertions.assertTrue(menu.isSelected(0));
        Assertions.assertFalse(menu.isSelected(1));
        Assertions.assertFalse(menu.isSelected(2));

        menu.previousEntry();
        Assertions.assertFalse(menu.isSelected(0));
        Assertions.assertFalse(menu.isSelected(1));
        Assertions.assertTrue(menu.isSelected(2));

        menu.previousEntry();
        Assertions.assertFalse(menu.isSelected(0));
        Assertions.assertTrue(menu.isSelected(1));
        Assertions.assertFalse(menu.isSelected(2));

        menu.previousEntry();
        Assertions.assertTrue(menu.isSelected(0));
        Assertions.assertFalse(menu.isSelected(1));
        Assertions.assertFalse(menu.isSelected(2));
    }

    @Test
    void testGetEntry() {
        Assertions.assertEquals("Option 1", menu.getEntry(0));
        Assertions.assertEquals("Option 2", menu.getEntry(1));
        Assertions.assertEquals("Option 3", menu.getEntry(2));
    }

    @Test
    void testGetNumberEntries() {
        Assertions.assertEquals(3, menu.getNumberEntries());
    }
}
