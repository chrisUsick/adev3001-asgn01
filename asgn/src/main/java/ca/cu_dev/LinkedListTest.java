package ca.cu_dev;

import sun.awt.image.ImageWatched;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 * Created by chris on 26-Sep-16.
 */
public class LinkedListTest extends TestSuite {
    public LinkedListTest() {
        super(new Logger());
    }
    public static void main(String[] args) throws Exception{
        LinkedListTest testSuite = new LinkedListTest();
        testSuite.run();
    }
    private final String str1 = "str1";
    private final String str2 = "str2";
    private final String str3 = "str3";

    @Test
    public void constructor() {
        it("inits correctly", () -> {
            LinkedList<String> list = new LinkedList<>();
            assertTrue(list.getSize() == 0);
        });
    }

    @Test
    public void add() {
        it("adds an element to the head of empty list", () -> {
            LinkedList<String> list = new LinkedList<>();
            String str = "Foo";
            list.add(str);
            assertTrue(list.getFirst() == str, "head isn't Set");
            assertTrue(list.getLast() == str, "tail isn't set");
            assertTrue(list.getSize() == 1, "size isn't correct");
        });

        it("adds an element to the head of list with data", () -> {
            LinkedList<String> list = new LinkedList<>();
            String str1 = "str1";
            String str2 = "str2";
            list.add(str1);
            list.add(str2);

            assertTrue(list.getFirst() == str2);
            assertTrue(list.getLast() == str1);
            assertTrue(list.getSize() == 2);
        });
    }

    @Test
    public void addAfterPosition() {
        it("doesn't add a element if position greater than size", () -> {
            LinkedList<String> list = new LinkedList<>();
            try {
                list.addAfter("str1", 1);
            } catch (NoSuchElementException e) {
                assertTrue(true);
            }
        });

        it("doesn't add an element if position is less than 0", () -> {
            LinkedList<String> list = new LinkedList<>();
            try {
                list.addAfter("str1", -1);
                assertTrue(false, "Added an element for an invalid position.");
            } catch (NoSuchElementException e) {
                assertTrue(true);
            }
        });

        it("doesn't add an element if list is empty", () -> {
            LinkedList<String> list = new LinkedList<>();
            try {
                list.addAfter("str1", 0);
                // if we get here, then the test failed
                assertTrue(false, "addAfter added an element to an empty list");
            } catch (NoSuchElementException e) {
                assertTrue(true);
            }
        });

        it("adds after to index 0", () -> {
            LinkedList<String> list = new LinkedList<>();
            String str0 = "str0";
            String str1 = "str1";
            list.add(str0);
            boolean success = list.addAfter(str1, 0);
            assertTrue(success);
            assertTrue(list.getFirst() == str0);
            assertTrue(list.getLast() == str1);
            assertTrue(list.getSize() == 2);
        });

        it("adds after to the last element in the list", () -> {
            LinkedList<String> list = new LinkedList<>();
            String str0 = "str0";
            String str2 = "str2";
            list.add("str1");
            list.add(str0);
            list.addAfter(str2, 1);
            assertTrue(list.getFirst() == str0);
            assertTrue(list.getLast() == str2);
            assertTrue(list.getSize() == 3);
        });
    }

    @Test
    public void addAfterData() {
        it("doesn't add an element if the data doesn't exist", () -> {
            try {
                LinkedList<String> list = new LinkedList<>();
                list.addAfter("str1", "str0");
                assertTrue(false, "added an element after an element that doesn't exist.");
            } catch (NoSuchElementException e) {
                assertTrue(true);
            }
        });

        it("updates the tail for 1 element lists", () -> {
            LinkedList<String> list = new LinkedList<>();
            list.add(str1);
            boolean success = list.addAfter(str2, str1);
            assertTrue(success);
            assertTrue(list.getLast() == str2);
            assertTrue(list.getSize() == 2);
        });

        it("adds to the middle of a list", () -> {
            LinkedList<String> list = new LinkedList<>();
            list.add(str3);
            list.add(str1);
            list.addAfter(str2, str1);
            assertTrue(list.get(1) == str2);
            assertTrue(list.getSize() == 2);
        });
    }

    @Test
    public void addBeforePosition() {
        it("doesn't add data if position is less than 0", () -> {
            try {
                LinkedList<String> list = new LinkedList<>();
                list.addBefore(str1, -1);
                assertTrue(false, "added an item before a negative index");
            } catch (NoSuchElementException e) {
                assertTrue(true);
            }
        });

        it("doesn't add data to an index that is too large", () -> {
            try {
                LinkedList<String> list = new LinkedList<>();
                list.addBefore(str1, 5);
                assertTrue(false, "added an item to an invalid index");
            } catch (NoSuchElementException e) {
                assertTrue(true);
            }
        });

        it("doesn't add an element at index 0 if list is empty", () -> {
            try {
                LinkedList<String> list = new LinkedList<>();
                list.addBefore(str1, 0);
                assertTrue(false, "added an item at 0 to an empty list");
            } catch (NoSuchElementException e) {
                assertTrue(true);
            }
        });

        it("adds an element before head of index 1 list", () -> {
            LinkedList<String> list = new LinkedList<>();
            list.add(str2);
            list.addBefore(str1, str2);
            assertTrue(list.getFirst() == str1);
            assertTrue(list.getLast() == str2);
        });
    }

    @Test
    public void addBeforeData() {
        it("doesn't add data if data doesn't exist", () -> {
            try {
                LinkedList<String> list = new LinkedList<>();
                list.addBefore("str1", "str0");
                assertTrue(false, "added element before a non-existent element");
            } catch (NoSuchElementException e) {
                assertTrue(true);
            }
        });

        it("adds before the head", () -> {
            LinkedList<String> list = new LinkedList<>();
            list.add(str2);
            boolean success = list.addBefore(str1, str2);
            assertTrue(success);
            assertTrue(list.getFirst() == str1);
            assertTrue(list.getSize() == 2);
        });

        it("adds to the middle of the list", () -> {
            LinkedList<String> list = new LinkedList<>();
            list.add(str3);
            list.add(str1);
            list.addBefore(str2, str3);
            assertTrue(list.get(1) == str2);
            assertTrue(list.getSize() == 3);
        });
    }




}
