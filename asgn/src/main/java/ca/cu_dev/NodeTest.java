package ca.cu_dev;

/**
 * Created by chris on 26-Sep-16.
 */
public class NodeTest extends TestSuite {
    public NodeTest() {
        super(new Logger());
    }
    public static void main(String[] args) throws Exception{
        TestSuite testSuite = new NodeTest();
        testSuite.run();
    }
    @Test
    public void constructor() {
        it("initialize with no data", () -> {
            new Node();
        });

        it("initializes with previous and next nodes", () -> {
            Node<String> node1 = new Node<>();

            Node<String> node3 = new Node<>();
            Node<String> n = new Node<String>("2", node1, node3);
            assertTrue(n.getNext() == node3);
            assertTrue(n.getPrevious() == node1);
        });
    }
}
