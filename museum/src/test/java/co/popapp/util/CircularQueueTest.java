/*
 * Created on 15-may-2003
 */

package co.popapp.util;

import junit.framework.TestCase;

/**
 * @author jamming
 */
public class CircularQueueTest extends TestCase {
    /** TODO Pendiente de documentar. */
    protected CircularQueue emptyCircularQueue = null;
    /** TODO Pendiente de documentar. */
    protected CircularQueue emptyCircularQueue16 = null;

    /**
     * Constructor for CircularQueueTest.
     * @param name
     */
    public CircularQueueTest (String name) {
        super(name);
    }

    /**
     * @see TestCase#setUp()
     */
    protected void setUp () throws Exception {
        super.setUp();
        emptyCircularQueue = new CircularQueue();
        emptyCircularQueue16 = new CircularQueue(16);
    }

    /**
     * @see TestCase#tearDown()
     */
    protected void tearDown () throws Exception {
        super.tearDown();
    }

    /**
     * Test for void CircularQueue()
     */
    public void testCircularQueue () {
        emptyCircularQueue = new CircularQueue();
        System.out.println(emptyCircularQueue.toString());
    }

    /**
     * Test for void CircularQueue(int)
     */
    public void testCircularQueueint () {
        emptyCircularQueue16 = new CircularQueue(16);
        System.out.println(emptyCircularQueue16.toString());
    }

    /**
     * .
     * TODO Pendiente de documentar
     */
    public void testResize () {
        emptyCircularQueue.resize(8);
        System.out.println(emptyCircularQueue.toString());
        emptyCircularQueue.resize(16);
        System.out.println(emptyCircularQueue.toString());
        emptyCircularQueue.add("E1");
        System.out.println(emptyCircularQueue.toString());
        emptyCircularQueue.add("E2");
        System.out.println(emptyCircularQueue.toString());
    }

    /**
     * .
     * TODO Pendiente de documentar
     */
    public void testAdd () {
        // TODO Pendiente de implementar
    }

    /**
     * .
     * TODO Pendiente de documentar
     */
    public void testRemove () {
        // TODO Pendiente de implementar
    }

    /**
     * .
     * TODO Pendiente de documentar
     */
    public void testHead () {
        // TODO Pendiente de implementar
    }

    /**
     * .
     * TODO Pendiente de documentar
     */
    public void testTail () {
        // TODO Pendiente de implementar
    }

    /**
     * .
     * TODO Pendiente de documentar
     */
    public void testElements () {
        // TODO Pendiente de implementar
    }
}
