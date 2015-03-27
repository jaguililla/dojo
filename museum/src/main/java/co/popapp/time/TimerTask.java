package co.popapp.time;

/**
 * .
 * @author jamming
 */
public abstract class TimerTask extends java.util.TimerTask {
    /** . */
    public static final byte WAITING = 0;
    /** . */
    public static final byte EXECUTING = 1;
    /** . */
    public static final byte FINALIZED = 2;
    /** . */
    public static final byte CANCELED = 3;

    /** . */
    protected Timer taskTimer = null; // Temporizador en el que se encuentra esta tarea
    /** . */
    protected long startTime = 0;
    /** . */
    protected long times = 0; // 0 indefinida
    /** . */
    protected long count = 0;
    /** . */
    protected long delay = 0; // 0 no es periodica, otro valor, retraso del inicio cuando la tarea acabe
    /** . */
    protected long timeout = 0; // Tiempo pasado el cual de una ejecucion se la salta, aun asi incrementa count, para que la coincida con la fecha de finalización
    /** . */
    protected long interval = 0;
    /** . */
    protected byte state = WAITING;

    /**
     * Tarea que se ejecuta una vez en el momento de ser planificada.
     */
    public TimerTask () {
        this(System.currentTimeMillis());
    }

    /**
     * Tarea que se ejecuta N veces con un intervalo fijo y que comienza su ejecución
     * al ser planificada.
     * @param timesNumber .
     * @param intervalMillis
     */
    public TimerTask (int timesNumber, long intervalMillis) {
        this(System.currentTimeMillis(), timesNumber, intervalMillis);
    }

    /**
     * Tarea que se ejecuta una vez en la hora indicada.
     * @param start
     */
    public TimerTask (long start) {
        super();
        startTime = start;
        times = 1;
    }

    /**
     * Tarea que se ejecuta N veces con un intervalo fijo y que comienza su ejecución
     * en una fecha concreta.
     * @param start .
     * @param timesNumber
     * @param intervalMillis
     */
    public TimerTask (long start, int timesNumber, long intervalMillis) {
        super();
        startTime = start;
        setTimes(timesNumber, intervalMillis);
    }

    /**
     * .
     * @param end .
     * @param intervalMillis
     */
    public TimerTask (long end, long intervalMillis) {
        this(System.currentTimeMillis(), end, intervalMillis);
    }

    /**
     * .
     * @param start .
     * @param end .
     * @param intervalMillis
     */
    public TimerTask (long start, long end, long intervalMillis) {
        super();
        startTime = start;
        setEndTime(end, intervalMillis);
    }

    /**
     * @see java.util.TimerTask#cancel()
     */
    @Override
    public boolean cancel () {
        return super.cancel();
    }

    /**
     * .
     */
    public abstract void execute ();

    /**
     * .
     * @return .
     */
    public long getCount () {
        return count;
    }

    /**
     * .
     * @return .
     */
    public long getDelay () {
        return delay;
    }

    /**
     * .
     * @return .
     */
    public long getInterval () {
        return interval;
    }

    /**
     * .
     * @return .
     */
    public long getStartTime () {
        return startTime;
    }

    /**
     * .
     * @return .
     */
    public byte getState () {
        return state;
    }

    /**
     * .
     * @return .
     */
    public Timer getTaskTimer () {
        return taskTimer;
    }

    /**
     * .
     * @return .
     */
    public long getTimeout () {
        return timeout;
    }

    /**
     * .
     * @return .
     */
    public long getTimes () {
        return times;
    }

    /**
     * .
     * @return .
     */
    public boolean isCanceled () {
        return false;
    }

    /**
     * .
     * @return .
     */
    public boolean isExecuting () {
        return false;
    }

    /**
     * .
     * @return .
     */
    public boolean isFinalized () {
        return false;
    }

    /**
     * .
     * @return .
     */
    public boolean isScheduled () {
        return taskTimer != null;
    }

    /**
     * .
     * @return .
     */
    public boolean isWaiting () {
        return false;
    }

    /**
     * @see java.util.TimerTask#run()
     */
    @Override
    public void run () {
        if (count < times) {
            execute();
            count++;
        }
        else {
            cancel();
        }
    }

    /**
     * .
     * @param aDelay .
     */
    public void setDelay (long aDelay) {
        if (aDelay < 0) {
            throw new IllegalArgumentException("");
        }
        this.delay = aDelay;
    }

    /**
     * .
     * @param aTimes .
     * @param intervalMillis
     */
    public void setEndTime (long aTimes, long intervalMillis) {
        if (aTimes < 0) {
            throw new IllegalArgumentException("");
        }
        this.times = aTimes;
        this.interval = intervalMillis;
    }

    /**
     * .
     * @param aInterval .
     */
    public void setInterval (long aInterval) {
        if (aInterval <= 0) {
            throw new IllegalArgumentException("");
        }
        this.interval = aInterval;
    }

    /**
     * .
     * @param aStartTime .
     */
    public void setStartTime (long aStartTime) {
        if (aStartTime < 0) {
            throw new IllegalArgumentException("");
        }
        this.startTime = aStartTime;
    }

    /**
     * .
     * @param aTimeout .
     */
    public void setTimeout (long aTimeout) {
        if (aTimeout < 0) {
            throw new IllegalArgumentException("");
        }
        this.timeout = aTimeout;
    }

    /**
     * .
     * @param aTimes .
     * @param aIntervalMillis
     */
    public void setTimes (long aTimes, long aIntervalMillis) {
        if (aTimes < 0) {
            throw new IllegalArgumentException("");
        }
        this.times = aTimes;
        this.interval = aIntervalMillis;
        //times = (int) ((end - start) / interval);
    }

    /**
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString () {
        return super.toString();
    }
}
