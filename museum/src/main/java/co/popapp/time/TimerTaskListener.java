/*
 * Created on 16-abr-2003
 *
 * To change the template for this generated file go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package co.popapp.time;

/**
 * @author jamming
 *
 * To change the template for this generated type comment go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public interface TimerTaskListener {
    /**
     * .
     * TODO Pendiente de documentar
     * @param task
     */
    public void taskStarted (TimerTask task);

    /**
     * .
     * TODO Pendiente de documentar
     * @param task
     */
    public void taskExecuted (TimerTask task);

    /**
     * .
     * TODO Pendiente de documentar
     * @param task
     */
    public void taskFinalized (TimerTask task);

    /**
     * .
     * TODO Pendiente de documentar
     * @param task
     */
    public void taskCanceled (TimerTask task);

    /**
     * .
     * TODO Pendiente de documentar
     * @param task
     */
    public void taskTimeout (TimerTask task);
}
