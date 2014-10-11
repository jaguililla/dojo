// P A C K A G E ///////////////////////////////////////////////////////////////////////////////////
package life.log;

// I M P O R T /////////////////////////////////////////////////////////////////////////////////////
import java.util.logging.Handler;
import java.util.logging.LogRecord;

// C L A S S ///////////////////////////////////////////////////////////////////////////////////////
/**
 * .
 * TODO Pendiente de documentar
 * @author jamming
 */
public class OutHandler extends Handler {
    /**
     * Constructor.
     * TODO Pendiente de documentar
     */
    public OutHandler () {
        super ();
        setFormatter (new ShortFormat ());
    }

    /**
     * @see java.util.logging.Handler#close()
     */
    @Override
    public void close () throws SecurityException {
        // TODO No implementado
    }

    /**
     * @see java.util.logging.Handler#flush()
     */
    @Override
    public void flush () {
        // TODO Bloque generado autom�ticamente
    }

    /**
     * @see java.util.logging.Handler#publish(java.util.logging.LogRecord)
     */
    @Override
    public void publish (LogRecord aRecord) {
        System.out.println (getFormatter ().format (aRecord));
    }
}
// E O F ///////////////////////////////////////////////////////////////////////////////////////////
