package co.popapp.time;

import java.util.Map;
import java.util.WeakHashMap;

/**
 * @author jamming
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class Timer extends java.util.Timer {
    /** TODO Pendiente de documentar. */
    protected Map timerTasks = null;

    /**
     * Constructor for Timer.
     */
    public Timer () {
        super ();
        timerTasks = new WeakHashMap ();
    }

    /**
     * Constructor for Timer.
     * @param arg0
     */
    public Timer (boolean arg0) {
        super (arg0);
        timerTasks = new WeakHashMap ();
    }

    /**
     * .
     * TODO Pendiente de documentar
     * @param arg0
     */
    /*
    public void schedule (TimerTask arg0) {
        // TODO Pendiente de implementar
    }
    */

    /**
     *
     * @param arg0
     * @param startPolicy empezar, noEmpezar, recalcularInicio en el caso de que
     * la hora de comienzo sea anterior a la actual
     */
    /*
    public void schedule (TimerTask arg0, int startPolicy) {
        // TODO Pendiente de implementar
    }
    */

    /**
     * .
     * TODO Pendiente de documentar
     * @param arg0
     */
    /*
    public void scheduleAtFixedRate (TimerTask arg0) {
        // TODO Pendiente de implementar
    }
    */

    /**
     *
     * @param arg0
     * @param startPolicy empezar, noEmpezar, recalcularInicio en el caso de que
     * la hora de comienzo sea anterior a la actual
     */
    /*
    public void scheduleAtFixedRate (TimerTask arg0, int startPolicy) {
        // TODO Pendiente de implementar
    }
    */
}
