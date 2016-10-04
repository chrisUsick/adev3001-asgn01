package ca.cu_dev;

/**
 * Created by chris on 26-Sep-16.
 */
public class NodeTest extends TestSuite {
    public NodeTest() {
        super(new Logger());
        setName("Nodes test");
    }
    public static void main(String[] args) throws Exception{
        TestSuite testSuite = new NodeTest();
        testSuite.run();
    }
    @Test
    public void constructor() {
        runTest("initialize with no data", () -> {
            new Node();
        });

        runTest("initializes with previous and next nodes", () -> {
            Node<String> node1 = new Node<>();

            Node<String> node3 = new Node<>();
            Node<String> n = new Node<String>("2", node1, node3);
            assertTrue(n.getNext() == node3);
            assertTrue(n.getPrevious() == node1);
        });
    }

    @Test
    public void properties() {
        runTest("gets and sets an the element", () -> {
            Node<String> n = new Node<>();
            String str = "str";
            assertTrue(n.getElement() == null);
            n.setElement(str);
            assertTrue(n.getElement() == str);
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
