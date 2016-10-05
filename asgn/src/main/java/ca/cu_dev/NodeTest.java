package ca.cu_dev;

/**
 * NodeTest - Test class for Node. Includes a main method so it can be run standalone or
 * called programmatically
 *
 * <pre>
 *
 * Assignment: #1
 * Course: ADEV-3001
 * Date Created: October 03, 2016
 *
 * Revision Log
 * Who          When    Reason
 * --------- ---------- ----------------------------------
 *
 * </pre>
 *
 * @author Chris Usick
 * @version 1.0
 *
 */
public class NodeTest extends TestSuite {
    /**
     * default constructor. sets the name and logger
     */
    public NodeTest() {
        super(new Logger());
        setName("Nodes test");
    }

    /**
     * Execute this test
     * @param args          command line arguments
     * @throws Exception    If test execution throws an exception the application exists
     */
    public static void main(String[] args) throws Exception{
        TestSuite testSuite = new NodeTest();
        testSuite.run();
    }

    /**
     * test the constructor
     */
    @Test
    public void testConstructor() {
        runTest("initialize with no data", () -> {
            new Node();
        });

        runTest("initializes with previous and next nodes", () -> {
            Node<String> node1 = new Node<>();

            Node<String> node3 = new Node<>();
            Node<String> node = new Node<String>("2", node1, node3);
            assertTrue(node.getNext() == node3);
            assertTrue(node.getPrevious() == node1);
        });
    }

    /**
     * test getting and setting properties of the employee object
     */
    @Test
    public void testProperties() {
        runTest("gets and sets an the element", () -> {
            Node<String> node = new Node<>();
            String testString = "str";
            assertTrue(node.getElement() == null);
            node.setElement(testString);
            assertTrue(node.getElement() == testString);
        });

        runTest("gets and sets an the next node", () -> {
            Node<String> n = new Node<>();
            Node<String> next = new Node<>();
            assertTrue(n.getNext() == null);
            n.setNext(next);
            assertTrue(n.getNext() == next);
        });

        runTest("gets and sets an the previous node", () -> {
            Node<String> n = new Node<>();
            Node<String> previous = new Node<>();
            assertTrue(n.getPrevious() == null);
            n.setPrevious(previous);
            assertTrue(n.getPrevious() == previous);
        });
    }
}
