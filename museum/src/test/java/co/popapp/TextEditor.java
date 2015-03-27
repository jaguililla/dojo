
// P A C K A G E ///////////////////////////////////////////////////////////////////////////////////
package co.popapp;

// I M P O R T /////////////////////////////////////////////////////////////////////////////////////
import static org.fife.ui.rsyntaxtextarea.SyntaxConstants.SYNTAX_STYLE_JAVA;
import static javax.swing.JFileChooser.*;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JTabbedPane;
import javax.swing.border.EmptyBorder;

import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rtextarea.Gutter;
import org.fife.ui.rtextarea.RTextScrollPane;

// T Y P E S ///////////////////////////////////////////////////////////////////////////////////////
class TextTabs extends JTabbedPane {
    /** TODO . */
    private static final long serialVersionUID = 1L;
}

/**
 * TODO .
 *
 * @author jamming
 */
public class TextEditor extends RTextScrollPane {
    private static final long serialVersionUID = 1L;

    private static final String EOL = System.getProperty("line.separator");
    private static final JFileChooser FILE_CHOOSER = new JFileChooser();

    public TextEditor() {
        this (25, 81, true, true);
    }

    public TextEditor (int aHeight, int aWidth) {
        this (aHeight, aWidth, true, true);
        getSyntaxTextArea().getDocument();
    }

    public TextEditor (int aHeight, int aWidth, boolean aShowLineNumbers) {
        this (aHeight, aWidth, aShowLineNumbers, true);
    }

    public TextEditor (int aHeight, int aWidth, boolean aShowLineNumbers, boolean aShowBookmarks) {
        super (new RSyntaxTextArea(aHeight, aWidth), aShowLineNumbers);

        final RSyntaxTextArea textArea = getSyntaxTextArea();
        final Gutter gutter = getGutter ();

        setBorder(new EmptyBorder (0, 0, 0, 0));

        textArea.setBorder (null);
        textArea.requestFocusInWindow ();
        textArea.setMarkOccurrences (true);
        textArea.setCodeFoldingEnabled (true);
        textArea.setClearWhitespaceLinesEnabled (false);
        textArea.setAnimateBracketMatching (true);
        textArea.setSyntaxEditingStyle (SYNTAX_STYLE_JAVA);
        textArea.setPaintTabLines (true);
        textArea.setAntiAliasingEnabled (true);
        textArea.setHighlightCurrentLine (true);
        textArea.setLineWrap (true);
        textArea.setMarginLineEnabled (true);
        textArea.setMarginLinePosition (80);
        textArea.setTabsEmulated (true);
        textArea.setTabSize (4);
        textArea.setWhitespaceVisible (true);
        textArea.setEOLMarkersVisible (true);

        gutter.setBookmarkingEnabled (aShowBookmarks);

        // FIXME Only for demo purposes
        textArea.addMouseListener(new MouseAdapter() {
            @Override public void mouseClicked(MouseEvent aE) {
                try {
                    open ();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * TODO .
     *
     * @param aFile .
     */
    public void load (String aFile) {
    }

    public void save (String aFile) {
    }

    public void close () {
        getTextArea ().setText ("");
    }

    public void open () throws IOException {
        if (FILE_CHOOSER.showOpenDialog (this) == APPROVE_OPTION) {
            open (FILE_CHOOSER.getSelectedFile ());
        }
    }

    public void open (File aFile) throws IOException {
        getTextArea ().setText (loadText (aFile));
        getTextArea ().setCaretPosition (0);
    }

    /**
     * TODO .
     *
     * @return .
     */
    public RSyntaxTextArea getSyntaxTextArea () {
        return (RSyntaxTextArea)getTextArea();
    }

    /**
     * TODO .
     * Read a text file, converts new lines to native platform
     * @param aFile .
     * @return .
     */
    private String loadText (File aFile) throws IOException {
        StringBuilder sb = new StringBuilder ();
        BufferedReader br = null;

        try {
            br = new BufferedReader (new FileReader (aFile));

            String line = br.readLine ();
            while (line != null) {
                sb.append (line).append (EOL);
                line = br.readLine ();
            }
        }
        finally {
            if (br != null) {
                br.close ();
            }
        }

        return sb.toString ();
    }
}
// E O F ///////////////////////////////////////////////////////////////////////////////////////////
