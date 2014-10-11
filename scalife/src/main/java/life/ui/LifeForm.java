// P A C K A G E ///////////////////////////////////////////////////////////////////////////////////
package life.ui;

// I M P O R T /////////////////////////////////////////////////////////////////////////////////////
import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.logging.LogManager;

import static javax.swing.JOptionPane.*;

// C L A S S ///////////////////////////////////////////////////////////////////////////////////////
/**
 * Ventana principal, y punto de entrada a la aplicación.
 * @author jamming
 */
public class LifeForm extends JFrame {
    private static final long serialVersionUID = 1L;

    private static final String LOGGING_PROPERTIES = "/life/log/logging.properties";
    /** Columnas por omisión del tablero inicial. */
    private static final int DEFAULT_COLUMNS = 20;
    /** Filas por defecto del tablero. */
    private static final int DEFAULT_ROWS = 20;

    /**
     * Punto de entrada a la aplicación.
     * @param args Parámetros de la línea de comandos, pasados por el SO.
     */
    public static void main (String [] args) {
        try {
            // Configura las trazas
            LogManager.getLogManager ().readConfiguration (
                Class.class.getResourceAsStream (LOGGING_PROPERTIES));

            // Establece el "Look & Feel" nativo
            UIManager.setLookAndFeel (UIManager.getSystemLookAndFeelClassName ());

            // Crea la aplicación
            LifeForm lifeForm = new LifeForm ();
            lifeForm.pack ();

            // Centra la ventana principal
            Dimension screenSize = Toolkit.getDefaultToolkit ().getScreenSize ();
            lifeForm.setLocation (
                (screenSize.width - lifeForm.getWidth ()) / 2,
                (screenSize.height - lifeForm.getHeight ()) / 2);

            // Muestra la aplicación
            lifeForm.setVisible (true);
        }
        catch (Throwable t) {
            t.printStackTrace();
        }
    }

    /** Selector de ficheros. Se abre en la carpeta desde la que se lanzó la aplicación. */
    private final JFileChooser mFileChooser = new JFileChooser (".");
    /** TODO Pendiente de documentar */
    private final CellsPanel mCellsPanel = new CellsPanel (DEFAULT_COLUMNS, DEFAULT_ROWS);

    /**
     * Constructor sin parámetros.
     * @throws java.awt.HeadlessException Lanzada en caso de error al instanciar la ventana.
     */
    public LifeForm () throws HeadlessException {
        super ("Juego de la Vida");

        setIconImage (Toolkit.getDefaultToolkit ().getImage (
            Class.class.getResource ("/life/ui/img/life-ico.png")));

        // Barra de desplazamiento (entre generaciones)
        JScrollBar sbarLife = new JScrollBar (Adjustable.VERTICAL);

        // Barra de estado
        JLabel lblLifeStatus = new JLabel ("Estado");

        // Ventana
        setLayout (new BorderLayout ());
        setDefaultCloseOperation (DO_NOTHING_ON_CLOSE);
        addWindowListener (new WindowAdapter () {
            @Override
            public void windowClosing (WindowEvent aE) {
                confirmClosing ();
            }
        });
        setJMenuBar (createMenu ());
        add (mCellsPanel, BorderLayout.CENTER);
        add (createToolbar (), BorderLayout.NORTH);
        add (lblLifeStatus, BorderLayout.SOUTH);
        add (sbarLife, BorderLayout.EAST);
    }

    /**
     * TODO .
     */
    private void confirmClosing () {
        int status = showConfirmDialog ( this, "¿Quieres salir?", "Life", YES_NO_OPTION);
        if (status == YES_OPTION)
            System.exit (0);
    }

    /**
     * Crea el menú de la ventana principal.
     * @return .
     */
    private JMenuBar createMenu () {
        JMenu mnuPanel = new JMenu ("Tablero");

        mnuPanel.add (createMenuItem (this, "Abrir...", 'A', new ActionListener () {
            public void actionPerformed (ActionEvent aE) {
                openPanel ((Component)aE.getSource ());
            }
        }));

        mnuPanel.add (createMenuItem (this, "Guardar", 'G', new ActionListener () {
            public void actionPerformed (ActionEvent aE) {
                // TODO Bloque generado automáticamente
            }
        }));

        mnuPanel.add (createMenuItem (this, "Guardar como...", 'U', new ActionListener () {
            public void actionPerformed (ActionEvent aE) {
                // TODO Bloque generado automáticamente
            }
        }));

        mnuPanel.addSeparator ();

        mnuPanel.add (createMenuItem (this, "Nuevo...", 'N', new ActionListener () {
            public void actionPerformed (ActionEvent aE) {
                // TODO Bloque generado automáticamente
            }
        }));

        mnuPanel.add (createMenuItem (this, "Borrar todo...", 'B', new ActionListener () {
            public void actionPerformed (ActionEvent aE) {
                mCellsPanel.clear ();
            }
        }));

        mnuPanel.addSeparator ();

        mnuPanel.add (createMenuItem (this, "Salir", 'S', new ActionListener () {
            public void actionPerformed (ActionEvent aE) {
                confirmClosing ();
            }
        }));

        JMenuBar mbarLife = new JMenuBar ();
        mbarLife.add (mnuPanel);

        return mbarLife;
    }

    /**
     * TODO .
     * @param aParent
     * @param aText
     * @param aKey
     * @param aListener
     * @return .
     */
    private JMenuItem createMenuItem (final Component aParent, String aText, char aKey, ActionListener aListener) {
        JMenuItem mitOpen = new JMenuItem (aText, aKey);
        mitOpen.setAccelerator (KeyStroke.getKeyStroke (aKey, InputEvent.CTRL_DOWN_MASK));
        mitOpen.addActionListener (aListener);
        return mitOpen;
    }

    /**
     * TODO .
     * @return .
     */
    private JToolBar createToolbar () {
        JButton btnAction = new JButton ("Siguiente");
        btnAction.setFocusable (false);
        btnAction.addActionListener (new ActionListener () {
            public void actionPerformed (ActionEvent aE) {
                mCellsPanel.generateNext ();
            }
        });

        JRadioButton radRecursive = new JRadioButton ("Recursivo", true);
        radRecursive.setFocusable (false);
        radRecursive.addItemListener (new ItemListener () {
            public void itemStateChanged (ItemEvent aE) {
                CellsPanel.setRecursive (aE.getStateChange () == ItemEvent.SELECTED);
            }
        });

        JRadioButton radIterative = new JRadioButton ("Iterativo");
        radIterative.setFocusable (false);

        ButtonGroup grpAlgorithm = new ButtonGroup ();
        grpAlgorithm.add (radRecursive);
        grpAlgorithm.add (radIterative);

        JToolBar tbarLife = new JToolBar ();
        tbarLife.setFloatable (false);
        tbarLife.setRollover (true);
        tbarLife.add (btnAction);
        tbarLife.addSeparator ();
        tbarLife.add (radRecursive);
        tbarLife.add (radIterative);

        return tbarLife;
    }

    /**
     * TODO .
     * @param aParent
     */
    private void openPanel (Component aParent) {
        mFileChooser.setDialogTitle ("Carga un tablero");
        mFileChooser.setFileFilter (new FileFilter () {
            @Override
            public boolean accept (File aF) {
                return aF.getName ().endsWith (".com.warizmi.life") || aF.isDirectory ();
            }
            @Override
            public String getDescription () {
                return "Tableros del juego de la vida (*.com.warizmi.life)";
            }
        });
        mFileChooser.setFileHidingEnabled (true);
        int result = mFileChooser.showOpenDialog (aParent);
        if (result == JFileChooser.APPROVE_OPTION) {
            showMessageDialog (aParent, "Fichero '"
                + mFileChooser.getSelectedFile ().getName () + "' abierto");
        }
    }
}
// E O F ///////////////////////////////////////////////////////////////////////////////////////////
