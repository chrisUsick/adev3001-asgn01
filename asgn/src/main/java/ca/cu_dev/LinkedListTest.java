package ca.cu_dev;

import sun.awt.image.ImageWatched;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Created by chris on 26-Sep-16.
 */
public class LinkedListTest extends TestSuite {
    public LinkedListTest() {
        super(new Logger());
        setName("Linked Lists");
    }
    public static void main(String[] args) throws Exception{
        LinkedListTest testSuite = new LinkedListTest();
        testSuite.run();
    }
    private final String str1 = "str1";
    private final String str2 = "str2";
    private final String str3 = "str3";

    public void assertListEmpty(LinkedList<?> list) {
        assertThrows(() -> list.getFirst(), NoSuchElementException.class, "Head isn't null");
        assertThrows(() -> list.getLast(), NoSuchElementException.class, "Tail isn't null");
        assertTrue(list.getSize() == 0, "List size is not 0");
    }

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
                list.addAfter("str1", 2);
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
            boolean success = list.addAfter(str1, 1);
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
            list.addAfter(str2, 2);
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
            assertTrue(list.get(2) == str2);
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
            list.addBefore(str2, 2);
            assertTrue(list.get(2) == str2);
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
            assertTrue(list.get(2) == str2);
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
            assertListEmpty(list);
        });

        it("clears a list with elements", () -> {
            LinkedList<String> list = new LinkedList<>();
            list.add(str1);
            list.add(str2);
            list.clear();
            assertListEmpty(list);
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
            assertThrows(() -> list.get(2), NoSuchElementException.class);
        });

        it("gets an element from a 1 element list", () -> {
            LinkedList<String> list = new LinkedList<>();
            list.add(str1);
            assertTrue(list.get(1) == str1);
        });

        it("gets an element from a multi-element list", () -> {
            LinkedList<String> list = new LinkedList<>();
            list.add(str3);
            list.add(str2);
            list.add(str1);
            assertTrue(list.get(2) == str2);
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
            assertTrue(list.get(2) == str2);
            assertTrue(list.get(3) == str3);
            assertTrue(list.getSize() == 3);
        });

        it("inserts into a unsorted list", () -> {
            LinkedList<String> list = new LinkedList<>();
            list.add(str1);
            list.add(str2);
            list.insert(str3);
            assertTrue(list.get(3) == str3);
            assertTrue(list.get(1) == str2);
            assertTrue(list.get(2) == str1);
        });

        it("inserts to the end of a list", () -> {
            LinkedList<String> list = new LinkedList<>();
            list.insert(str2);
            list.insert(str1);
            list.insert(str3);
            assertTrue(list.getSize() == 3);
            assertTrue(list.get(2) == str2);
            assertTrue(list.get(3) == str3);
        });

    }

    @Test
    public void removeHead() {
        it("throws exception for empty list", () -> {
            LinkedList<String> list = new LinkedList<>();
            assertThrows(() -> list.remove(), NoSuchElementException.class);
        });

        it("removes element on single element list", () -> {
            LinkedList<String> list = new LinkedList<>();
            list.add(str1);
            list.remove();
            assertTrue(list.getSize() == 0);
            assertThrows(() -> list.get(), NoSuchElementException.class);
        });

        it("removes element from a multi-element list", () -> {
            LinkedList<String> list = new LinkedList<>();
            list.add(str2);
            list.add(str1);
            list.remove();
            assertTrue(list.getFirst() == str2);
            assertTrue(list.getLast() == str2);
            assertTrue(list.getSize() == 1);
        });
    }

    @Test
    public void removePosition() {
        it("throws exception for negative index", () -> {
            LinkedList<String> list = new LinkedList<>();
            assertThrows(() -> list.remove(-1), NoSuchElementException.class);
        });

        it("throws exception for index not in list", () -> {
            LinkedList<String> list = new LinkedList<>();
            list.add(str1);
            assertThrows(() -> list.remove(2), NoSuchElementException.class);
        });

        it("removes element in single element list", () -> {
            LinkedList<String> list = new LinkedList<>();
            list.add(str1);
            list.remove(1);
            assertListEmpty(list);
        });

        it("removes element from multi-element list", () -> {
            LinkedList<String> list = new LinkedList<>();
            list.add(str3);
            list.add(str2);
            list.add(str1);
            list.remove(2);
            assertTrue(list.getFirst() == str1);
            assertTrue(list.getLast() == str3);
            assertTrue(list.getSize() == 2);
        });
    }

    @Test
    public void removeData() {
        it("throws exception for missing data", () -> {
            LinkedList<String> list = new LinkedList<>();
            assertThrows(() -> list.remove(str1), NoSuchElementException.class);
        });

        it("removes a element from a single element list", () -> {
            LinkedList<String> list = new LinkedList<>();
            list.add(str1);
            list.remove("str1");
            assertListEmpty(list);
        });

        it("removes an element from multi-element list", () -> {
            LinkedList<String> list = new LinkedList<>();
            list.add(str3);
            list.add(str2);
            list.add(str1);
            list.remove("str2");
            assertTrue(list.getFirst() == str1);
            assertTrue(list.getLast() == str3);
            assertTrue(list.getSize() == 2);
        });
    }

    @Test
    public void removeFirst() {
        it("throws exception for empty list", () -> {
            LinkedList<String> list = new LinkedList<>();
            assertThrows(() -> list.removeFirst(), NoSuchElementException.class);
            assertTrue(list.getSize() == 0);
        });

        it("removes an element in a single element list", () -> {
            LinkedList<String> list = new LinkedList<>();
            list.add(str1);
            list.removeFirst();
            assertListEmpty(list);
        });

        it("removes first element from multi-element list", () -> {
            LinkedList<String> list = new LinkedList<>();
            list.add(str2);
            list.add(str1);
            list.removeFirst();
            assertTrue(list.getFirst() == str2);
            assertTrue(list.getLast() == str2);
            assertTrue(list.getSize() == 1);
        });
    }

    @Test
    public void removeLast() {
        it("throws an exception for empty list", () -> {
            LinkedList<String> list = new LinkedList<>();
            assertThrows(() -> list.removeLast(), NoSuchElementException.class);
        });

        it("removes the last element in a single element list", () -> {
            LinkedList<String> list = new LinkedList<>();
            list.add(str1);
            list.removeLast();
            assertListEmpty(list);
        });

        it("removes the last element from a multi-element list", () -> {
            LinkedList<String> list = new LinkedList<>();
            list.add(str3);
            list.add(str2);
            list.add(str1);
            list.removeLast();
            assertTrue(list.getFirst() == str1);
            assertTrue(list.getLast() == str2);
            assertTrue(list.getSize() == 2);
        });
    }

    @Test
    public void setHead() {
        it("throws exception for empty list", () -> {
            LinkedList<String> list = new LinkedList<>();
            assertThrows(() -> list.set(str1), NoSuchElementException.class, "setFirst");
            assertListEmpty(list);
        });

        it("sets the data on the first element in a single element list", () -> {
            LinkedList<String> list = new LinkedList<>();
            list.add(str1);
            list.set(str2);
            assertTrue(list.getFirst() == str2);
            assertTrue(list.getLast() == str2);
        });

        it("sets first in a multi-element list", () -> {
            LinkedList<String> list = new LinkedList<>();
            list.add(str3);
            list.add(str2);
            list.add(str1);
            list.set("str4");
            assertTrue(list.getFirst().equals("str4"));
            assertTrue(list.get(2) == str2);
        });
    }

    @Test
    public void setPosition() {
        it("throws exception for index less than 1 on empty list", () -> {
            LinkedList<String> list = new LinkedList<>();
            assertThrows(() -> list.set(str1, 0), NoSuchElementException.class);
            assertThrows(() -> list.set(str1, -1), NoSuchElementException.class);
        });

        it("throws exception for bad position on list with data", () -> {
            LinkedList<String> list = new LinkedList<>();
            list.add(str3);
            list.add(str2);
            list.add(str1);
            assertThrows(() -> list.set(str1, 0), NoSuchElementException.class);
            assertThrows(() -> list.set(str1, 4), NoSuchElementException.class);
        });

        it("sets position for single element list", () -> {
            LinkedList<String> list = new LinkedList<>();
            list.add(str1);
            list.set("str4", 1);
            assertTrue(list.getSize() == 1);
            assertTrue(list.getFirst().equals("str4"));
        });
        it("sets position for multi-element list", () -> {
            LinkedList<String> list = new LinkedList<>();
            list.add(str3);
            list.add(str2);
            list.add(str1);
            list.set("str4", 3);
            assertTrue(list.getSize() == 3);
            assertTrue(list.getLast().equals("str4"));
        });
    }

    @Test
    public void setData() {
        it("throws exception when data doesn't exist", () -> {
            LinkedList<String> list = new LinkedList<>();
            assertThrows(() -> list.set(str1), NoSuchElementException.class);
        });

        it("sets data when there is a single element", () -> {
            LinkedList<String> list = new LinkedList<>();
            list.add(str1);
            list.set(str2, "str1");
            assertTrue(list.getFirst().equals("str2"));
            assertTrue(list.getLast().equals("str2"));
            assertTrue(list.getSize() == 1);
        });
        it("sets position for multi-element list", () -> {
            LinkedList<String> list = new LinkedList<>();
            list.add(str3);
            list.add(str2);
            list.add(str1);
            list.set("str4", "str2");
            assertTrue(list.getSize() == 3);
            assertTrue(list.getLast() == str3);
            assertTrue(list.get(2).equals("str4"));
        });
    }

    @Test
    public void setFirst() {
        it("throws exception for emtpy list", () -> {
            LinkedList<String> list = new LinkedList<>();
            assertThrows(() -> list.setFirst(str1), NoSuchElementException.class);
        });

        it("sets the head on a single element list", () -> {
            LinkedList<String> list = new LinkedList<>();
            list.add(str1);
            list.setFirst(str2);
            assertTrue(list.getSize() == 1);
            assertTrue(list.getFirst().equals("str2"));
            assertTrue(list.getLast().equals("str2"));
        });

        it("sets the head on a mutli-element list", () -> {
            LinkedList<String> list = new LinkedList<>();
            list.add(str2);
            list.add(str1);
            list.setFirst(str3);
            assertTrue(list.getSize() == 2);
            assertTrue(list.getFirst() == str3);
            assertTrue(list.getLast() == str2);
        });

    }
    @Test
    public void setLast() {
        it("throws exception for emtpy list", () -> {
            LinkedList<String> list = new LinkedList<>();
            assertThrows(() -> list.setLast(str1), NoSuchElementException.class);
            assertListEmpty(list);
        });

        it("sets the head on a single element list", () -> {
            LinkedList<String> list = new LinkedList<>();
            list.add(str1);
            list.setLast(str2);
            assertTrue(list.getSize() == 1);
            assertTrue(list.getFirst().equals("str2"));
            assertTrue(list.getLast().equals("str2"));
        });

        it("sets the tail on a mutli-element list", () -> {
            LinkedList<String> list = new LinkedList<>();
            list.add(str2);
            list.add(str1);
            list.setLast(str3);
            assertTrue(list.getSize() == 2);
            assertTrue(list.getLast() == str3);
            assertTrue(list.getFirst() == str1);
        });
    }

    @Test
    public void employeeLinkedLists() {
        Employee e1 = new Employee(1), e2 = new Employee(2), e3 = new Employee(3);
        LinkedList<Employee> list = new LinkedList<Employee>();
        it("adds employees", () -> {
            list.clear();
            list.add(e2);
            list.add(e1);
            assertTrue(list.getFirst() == e1);
        });

        it("sorts employees", () -> {
            list.clear();
            list.insert(e2);
            list.insert(e3);
            list.insert(e1);
            assertTrue(list.getFirst() == e1);
            assertTrue(list.get(2) == e2);
        });

        it("adds after elements", () -> {
            list.clear();
            list.add(e2);
            list.addAfter(e3, e2);
            assertTrue(list.getLast() == e3);
        });

        it("finds an element", () -> {
            list.clear();
            list.add(e3);
            list.add(e2);
            list.add(e1);
            assertTrue(list.get(new Employee(2)) == e2);
        });
    }



}
