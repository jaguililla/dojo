// P A C K A G E ///////////////////////////////////////////////////////////////////////////////////
package life.log;

// I M P O R T /////////////////////////////////////////////////////////////////////////////////////
import java.util.logging.Formatter;
import java.util.logging.LogRecord;

// C L A S S ///////////////////////////////////////////////////////////////////////////////////////
/**
 * .
 * TODO Pendiente de documentar
 * @author jamming
 */
public class ShortFormat extends Formatter {

    /**
     * Constructor.
     * TODO Pendiente de documentar
     */
    public ShortFormat () {
        super ();
    }

    /**
     * @see java.util.logging.Formatter#format(java.util.logging.LogRecord)
     */
    @Override
    public String format (LogRecord aRecord) {
        StringBuffer buffer = new StringBuffer ();
        buffer.append (aRecord.getMessage ());
        return buffer.toString ();
    }
}
// E O F ///////////////////////////////////////////////////////////////////////////////////////////
