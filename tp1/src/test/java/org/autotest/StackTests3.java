package org.autotest;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class StackTests3 extends MutationAnalysisRunner {
    @Override
    protected boolean useVerboseMode() {
        return false;
    }

    // Tests de StackTests2.java
    public void testSizeIncreasesByOne() throws Exception {
        Stack stack = createStack();
        assertEquals(0, stack.size());
        stack.push(42);
        assertEquals(1, stack.size());
    }

    public void testDefaultConstructor() throws Exception {
        Stack stack = createStack();
        assertTrue(stack.isEmpty());
    }

    public void testConstructorWithSpecifiedCapacity() throws Exception {
        Stack stack = createStack(5);
    }

    public void testConstructorWithNegativeCapacity() {
        assertThrows(IllegalArgumentException.class, () -> {
            Stack stack = createStack(-1);
        });
    }

    public void testIsEmptyMethod() throws Exception {
        Stack stack = createStack();
        assertTrue(stack.isEmpty());
        stack.push(42);
        assertFalse(stack.isEmpty());
        stack.pop();
        assertTrue(stack.isEmpty());
    }

    public void testIsFullMethod() throws Exception {
        Stack stack = createStack(1);
        assertFalse(stack.isFull());
        stack.push(42);
        assertTrue(stack.isFull());
        stack.pop();
        assertFalse(stack.isFull());
    }

    public void testToStringMethod() throws Exception {
        Stack stack = createStack(2);
        assertEquals("[]", stack.toString());
        stack.push(42);
        assertEquals("[42]", stack.toString());
        stack.push(43);
        assertEquals("[42,43]", stack.toString());
    }

    // Tests nuestros
    public void testConstructorWithNoCapacity() {
        assertDoesNotThrow(() -> {
            Stack stack = createStack(0);
        });
    }

    public void testSizeDecreasesByOne() throws Exception {
        Stack stack = createStack();
        assertEquals(0, stack.size());
        stack.push(42);
        stack.push(43);
        stack.push(44);
        assertEquals(3, stack.size());
        stack.pop();
        assertEquals(2, stack.size());
    }

    public void testPushOnFull() throws Exception {
        Stack stack = createStack(1);
        stack.push(42);
        assertThrows(IllegalStateException.class, () -> stack.push(43));
    }

    public void testPopOnEmpty() throws Exception {
        Stack stack = createStack();
        assertThrows(IllegalStateException.class, stack::pop);
    }

    public void testPopReturnValue() throws Exception {
        Stack stack = createStack();
        stack.push(42);
        stack.push(43);
        assertEquals(43, stack.pop());
    }

    public void testTopOnEmpty() throws Exception {
        Stack stack = createStack();
        assertThrows(IllegalStateException.class, stack::top);
    }

    public void testTopReturnValue() throws Exception {
        Stack stack = createStack();
        stack.push(42);
        stack.push(43);
        assertEquals(43, stack.top());
    }

    int calcHashCode(Object[] arr, int elems) {
        return 31 * (31 + Arrays.hashCode(arr)) + elems-1;
    }

    public void testHashCodeOnEmpty() throws Exception {
        Stack stack = createStack(10);
        assertEquals(calcHashCode(new Object[10], 0), stack.hashCode());
    }

    public void testHashCodeWithItems() throws Exception {
        Stack stack = createStack(10);
        stack.push(42);
        stack.push(43);

        Object[] elems = new Object[10];
        elems[0] = 42;
        elems[1] = 43;

        assertEquals(calcHashCode(elems, 2), stack.hashCode());
    }

    public void testEqualsSelf() throws Exception {
        Stack stack = createStack();
        assertEquals(stack, stack);
    }

    public void testEqualsNull() throws Exception {
        Stack stack = createStack();
        assertNotEquals(null, stack);
        assertNotEquals(stack, null);
    }

    public void testEqualsClass() throws Exception {
        Stack stack = createStack();
        assertNotEquals(1, stack);
        assertNotEquals(stack, 1);
    }

    public void testEquals() throws Exception {
        Stack s1 = createStack(),
                s2 = createStack();

        s1.push(42);
        s2.push(42);

        assertEquals(s2, s1);
    }

    public void testNotEqualsSameTop() throws Exception {
        Stack s1 = createStack(),
                s2 = createStack();

        s1.push(42);
        s2.push(42);
        s2.push(42);

        assertNotEquals(s2, s1);
    }

    public void testNotEqualsSameLength() throws Exception {
        Stack s1 = createStack(),
                s2 = createStack();

        s1.push(42);
        s2.push(43);

        assertNotEquals(s2, s1);
    }
}
