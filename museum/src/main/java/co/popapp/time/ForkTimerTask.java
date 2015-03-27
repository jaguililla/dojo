package co.popapp.time;

/**
 * @author jamming
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public abstract class ForkTimerTask extends TimerTask {
    /** TODO Pendiente de documentar. */
    protected Thread executionThread = null;
    /** TODO Pendiente de documentar. */
    protected long cancelTimeout = 0;

    /**
     * Constructor for ForkTimerTask.
     */
    public ForkTimerTask () {
        super();
    }

}
