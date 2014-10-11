// P A C K A G E ///////////////////////////////////////////////////////////////////////////////////
package life;

// I M P O R T /////////////////////////////////////////////////////////////////////////////////////
import java.util.logging.Logger;

// C L A S S ///////////////////////////////////////////////////////////////////////////////////////
/**
 * @author jamming
 */
public class LifeGame {
    private static final Logger log = Logger.getLogger (LifeGame.class.getName ());

    private int mColumns = 0;
    private int mRows = 0;
    private int mRange = 1;

    public LifeGame (int aColumns, int aRows) {
        setColumns (aColumns);
        setRows (aRows);
    }

    private boolean [] c (boolean [] aV, int aI, boolean aB) {
        aV[aI] = aB;
        return aV;
    }

    private int casilla (int aC, int aR) { return aR * getColumns () + aC; }

    private int columna (int aI) { return aI % getColumns (); }

    private boolean condiciones (boolean [] aV, int aI) {
        int vecinos = vecinos(aV, aI);
        return vecinos == 3 || (aV[aI] && vecinos == 2);
    }

    private int fila (int aI) { return aI / getColumns (); }

    private boolean [] igen (boolean [] aV, int aI) {
        if (aI >= 0) c (igen (aV, aI - 1), aI, condiciones (aV, aI));
        return aV;
    }

    private int vecinos (boolean [] aV, int aI) {
        int count = 0;
        log.fine ("Calculando posición (" + fila (aI) + "," + columna (aI) + ") [" + aI + "]");
        for (int ii = -mRange; ii <= mRange; ii++) {
            for (int jj = -mRange; jj <= mRange; jj++) {
                if ( !(ii == 0 && jj == 0) ) {
                    int row = fila (aI) + ii;
                    int column = columna (aI) + jj;
                    log.fine ("\t(" + row + "," + column + ")");

                    if (row >= 0 && row < mRows && column >= 0 && column < mColumns
                        && aV [casilla (column, row)])
                        count++;
                }
            }
        }
        return count;
    }

    public boolean [] gen (boolean [] aV) {
        log.info ("Recursivo");
        return igen (aV, getColumns () * getRows () - 1);
    }

    public boolean [] genit (boolean [] aV) {
        log.info ("Iterativo");
        boolean [] result = new boolean [aV.length];
        for (int ii = 0; ii < aV.length; ii++) result[ii] = condiciones (aV, ii);
        return result;
    }

    public int getColumns () { return mColumns; }

    public int getRows () { return mRows; }

    public void setColumns (int aColumns) { mColumns = aColumns; }

    public void setRows (int aRows) { mRows = aRows; }
}
// E O F ///////////////////////////////////////////////////////////////////////////////////////////
