package ca.cu_dev;

import sun.awt.image.ImageWatched;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
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
                assertTrue(list.getSize() == 0);
            }
        });

        it("doesn't add an element if position is less than 0", () -> {
            LinkedList<String> list = new LinkedList<>();
            try {
                list.addAfter("str1", -1);
                assertTrue(false, "Added an element for an invalid position.");
            } catch (NoSuchElementException e) {
                assertTrue(true);
                assertTrue(list.getSize() == 0);
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
                assertTrue(list.getSize() == 0);
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
            LinkedList<String> list = new LinkedList<>();
            try {
                list.addAfter("str1", "str0");
                assertTrue(false, "added an element after an element that doesn't exist.");
            } catch (NoSuchElementException e) {
                assertTrue(true);
                assertTrue(list.getSize() == 0);
            }
        });

        it("updates the tail for 1 element lists", () -> {
            LinkedList<String> list = new LinkedList<>();
            list.add(str1);
            boolean success = list.addAfter(str2, str1);
            assertTrue(success);
            assertTrue(list.getLast() == str2, "last item wasn't added");
            assertTrue(list.getSize() == 2, "size is incorrect");
        });

        it("adds to the middle of a list", () -> {
            LinkedList<String> list = new LinkedList<>();
            list.add(str3);
            list.add(str1);
            list.addAfter(str2, str1);
            assertTrue(list.get(1) == str2);
            assertTrue(list.getSize() == 3);
        });
    }

    @Test
    public void addBeforePosition() {
        it("doesn't add data if position is less than 0", () -> {
            LinkedList<String> list = new LinkedList<>();
            try {
                list.addBefore(str1, -1);
                assertTrue(false, "added an item before a negative index");
            } catch (NoSuchElementException e) {
                assertTrue(true);
                assertTrue(list.getSize() == 0);
            }
        });

        it("doesn't add data to an index that is too large", () -> {
            LinkedList<String> list = new LinkedList<>();
            try {
                list.addBefore(str1, 5);
                assertTrue(false, "added an item to an invalid index");
            } catch (NoSuchElementException e) {
                assertTrue(true);
                assertTrue(list.getSize() == 0);
            }
        });

        it("doesn't add an element at index 0 if list is empty", () -> {
            LinkedList<String> list = new LinkedList<>();
            try {
                list.addBefore(str1, 0);
                assertTrue(false, "added an item at 0 to an empty list");
            } catch (NoSuchElementException e) {
                assertTrue(true);
                assertTrue(list.getSize() == 0);
            }
        });

        it("adds an element before head of index 1 list", () -> {
            LinkedList<String> list = new LinkedList<>();
            list.add(str2);
            list.addBefore(str1, str2);
            assertTrue(list.getFirst() == str1);
            assertTrue(list.getLast() == str2);
            assertTrue(list.getSize() == 2);
        });

        it("adds an element in the middle of a list", () -> {
            LinkedList<String> list = new LinkedList<>();
            list.add(str3);
            list.add(str1);
            list.addBefore(str2, 1);
            assertTrue(list.get(1) == str2);
            assertTrue(list.getSize() == 3);
        });
    }

    @Test
    public void addBeforeData() {
        it("doesn't add data if data doesn't exist", () -> {
            LinkedList<String> list = new LinkedList<>();
            try {
                list.addBefore("str1", "str0");
                assertTrue(false, "added element before a non-existent element");
            } catch (NoSuchElementException e) {
                assertTrue(true);
                assertTrue(list.getSize() == 0);
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

    @Test
    public void addFirst() {
        it("adds to an empty list", () -> {
            LinkedList<String> list = new LinkedList<>();
            boolean success = list.addFirst(str1);
            assertTrue(success);
            assertTrue(list.getFirst() == str1);
            assertTrue(list.getLast() == str1);
            assertTrue(list.getSize() == 1);
        });

        it("adds to a list with elements", () -> {
            LinkedList<String> list = new LinkedList<>();
            list.add(str2);
            list.addFirst(str1);
            assertTrue(list.getFirst() == str1);
            assertTrue(list.getLast() == str2);
            assertTrue(list.getSize() == 2);
        });
    }

    @Test
    public void addLast() {
        it("adds to an empty list", () -> {
            LinkedList<String> list = new LinkedList<>();
            list.addLast(str1);
            assertTrue(list.getFirst() == str1);
            assertTrue(list.getLast() == str1);
            assertTrue(list.getSize() == 1);
        });

        it("adds to list with elements", () -> {
            LinkedList<String> list = new LinkedList<>();
            list.add(str1);
            list.addLast(str2);
            assertTrue(list.getFirst() == str1);
            assertTrue(list.getLast() == str2);
            assertTrue(list.getSize() == 2);
        });
    }

    @Test
    public void clear() {
        it("clears an empty list", () -> {
            LinkedList<String> list = new LinkedList<>();
            list.clear();
            assertThrows(() -> list.getFirst(), NoSuchElementException.class);
            assertThrows(() -> list.getLast(), NoSuchElementException.class);
            assertTrue(list.getSize() == 0);
        });

        it("clears a list with elements", () -> {
            LinkedList<String> list = new LinkedList<>();
            list.add(str1);
            list.add(str2);
            list.clear();
            assertThrows(() -> list.getFirst(), NoSuchElementException.class);
            assertThrows(() -> list.getLast(), NoSuchElementException.class);
            assertTrue(list.getSize() == 0);
        });
    }

    @Test
    public void isEmpty() {
        it("tests an empty list", () -> {
            LinkedList<String> list = new LinkedList<>();
            assertTrue(list.isEmpty());
        });

        it("tests a list with elements", () -> {
            LinkedList<String> list = new LinkedList<>();
            list.add(str1);
            assertTrue(!list.isEmpty());
        });
    }

    @Test
    public void getNoArgument() {
        it("throws an exception when list is empty", () -> {
            LinkedList<String> list = new LinkedList<>();
            assertThrows(() -> list.get(), NoSuchElementException.class);
        });

        it("returns the head on empty list", () -> {
            LinkedList<String> list = new LinkedList<>();
            list.add(str1);
            assertTrue(list.get() == str1);
        });

        it("returns the head on containing data", () -> {
            LinkedList<String> list = new LinkedList<>();
            list.add(str2);
            list.add(str1);
            assertTrue(list.get() == str1);
        });
    }

    @Test
    public void getData() {
        it("doesn't get an element that doesn't exist in list", () -> {
            LinkedList<String> list = new LinkedList<>();
            try {
                list.get(str1);
                assertTrue(false, "didn't throw an exception");
            } catch (NoSuchElementException e) {
                assertTrue(true);
            }
        });

        it("gets an element from 1 element list", () -> {
            LinkedList<String> list = new LinkedList<>();
            list.add(str1);
            assertTrue(list.get("str1") == str1);
        });

        it("gets an element from a multi-element list", () -> {
            LinkedList<String> list = new LinkedList<>();
            list.add(str3);
            list.add(str2);
            list.add(str1);
            assertTrue(list.get("str2") == str2);
        });
    }

    @Test
    public void getPosition() {
        it("throws exception negative position", () -> {
            LinkedList<String> list = new LinkedList<>();
            assertThrows(() -> list.get(-1), NoSuchElementException.class);
        });

        it("throws exception for position greater than size", () -> {
            LinkedList<String> list = new LinkedList<>();
            assertThrows(() -> list.get(1), NoSuchElementException.class);
        });

        it("gets an element from a 1 element list", () -> {
            LinkedList<String> list = new LinkedList<>();
            list.add(str1);
            assertTrue(list.get(0) == str1);
        });

        it("gets an element from a multi-element list", () -> {
            LinkedList<String> list = new LinkedList<>();
            list.add(str3);
            list.add(str2);
            list.add(str1);
            assertTrue(list.get(1) == str2);
        });
    }

    @Test
    public void getFirst() {
        it("throws exception for empty list", () -> {
            LinkedList<String> list = new LinkedList<>();
            assertThrows(() -> list.getFirst(), NoSuchElementException.class);
        });

        it("gets the head for a single element list", () -> {
            LinkedList<String> list = new LinkedList<>();
            list.add(str1);
            assertTrue(list.getFirst() == str1);
        });

        it("gets an element from a multi-element list", () -> {
            LinkedList<String> list = new LinkedList<>();
            list.add(str3);
            list.add(str2);
            list.add(str1);
            assertTrue(list.getFirst() == str1);
        });
    }

    @Test
    public void getLast() {
        it("throws exception for empty list", () -> {
            LinkedList<String> list = new LinkedList<>();
            assertThrows(() -> list.getLast(), NoSuchElementException.class);
        });

        it("gets the tail for a single element list", () -> {
            LinkedList<String> list = new LinkedList<>();
            list.add(str1);
            assertTrue(list.getLast() == str1);
        });

        it("gets an element from a multi-element list", () -> {
            LinkedList<String> list = new LinkedList<>();
            list.add(str3);
            list.add(str2);
            list.add(str1);
            assertTrue(list.getLast() == str3);
        });
    }

    @Test
    public void getSize() {
        it("gets size for an empty list", () -> {
            LinkedList<String> list = new LinkedList<>();
            assertTrue(list.getSize() == 0);
        });
        it("gets size for an multi-element list", () -> {
            LinkedList<String> list = new LinkedList<>();
            list.add(str3);
            list.add(str2);
            list.add(str1);
            assertTrue(list.getSize() == 3);
        });
    }

    @Test
    public void insert() {
        it("inserts into an empty list", () -> {
            LinkedList<String> list = new LinkedList<>();
            list.insert(str1);
            assertTrue(list.getFirst() == str1);
            assertTrue(list.getSize() == 1);
        });

        it("inserts into a properly sorted list", () -> {
            LinkedList<String> list = new LinkedList<>();
            list.insert(str1);
            list.insert(str3);
            list.insert(str2);
            assertTrue(list.get(1) == str2);
            assertTrue(list.get(2) == str3);
            assertTrue(list.getSize() == 3);
        });

        it("inserts into a unsorted list", () -> {
            LinkedList<String> list = new LinkedList<>();
            list.add(str1);
            list.add(str2);
            list.insert(str3);
            assertTrue(list.get(1) == str3);
            assertTrue(list.get(0) == str2);
            assertTrue(list.get(2) == str1);
        });
    }






}
