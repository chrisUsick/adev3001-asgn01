package ca.cu_dev;

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
    public void addAfter() {
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
            list.addAfter(str1, 0);
            assertTrue(list.getFirst() == str0);
            assertTrue(list.getLast() == str1);
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
        });
    }


}
