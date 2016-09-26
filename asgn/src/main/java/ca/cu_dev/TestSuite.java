package ca.cu_dev;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;

/**
 * Created by chris on 26-Sep-16.
 */
public class TestSuite {
    private Logger logger;
    public TestSuite(Logger l) {
        logger = l;
    }
    public void run() throws Exception {
        logger = new Logger();

        ArrayList<Method> tests = this.getTestMethods();
        try {
            for (Method test : tests) {
                test.invoke(this);
            }
        } catch (Exception e) {
            // test invocation failed
            logger.e("Test invocation failed");
            throw e;
        }
    }

    public ArrayList<Method> getTestMethods() {
        Method[] methods = getClass().getDeclaredMethods();

        ArrayList<Method> testMethods = new ArrayList<>();
        for (Method method :
                methods) {
            if ( hasTestAnnotation(method)) {
                testMethods.add(method);
            }
        }

        return testMethods;
    }

    private boolean hasTestAnnotation(Method method) {
        Annotation[] annotations = method.getDeclaredAnnotations();
        boolean res = false;
        for (Annotation a : annotations) {
            if (a instanceof Test) {
                res = true;
            }
        }
        return res;
    }

    protected void assertTrue(boolean b) throws AssertionError {
        String messageText = "assertion failure";
        assertTrue(b, messageText);
    }

    protected void assertTrue(boolean b, String message) throws AssertionError {
        if (!b) {
            throw new AssertionError(message);
        }
    }

    public void it(String message, Runnable test) {
        try {
            test.run();
        } catch (Exception e) {
            // test failed
            throw new AssertionError("test failed " + message, e);

        }
    }


}