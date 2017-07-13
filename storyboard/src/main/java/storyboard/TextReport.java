package storyboard;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * TODO .
 *
 * @author jam
 */
public class TextReport extends Report {
    final File file;

    protected TextReport (Feature feature) {
        this (feature, null);
    }

    protected TextReport (Feature feature, File file) {
        super (feature);
        this.file = file;

        if (this.file != null && !this.file.exists ())
            this.file.mkdir ();
    }

    @Override void generate () {
        if (file == null)
            System.out.println (feature);
        else
            try (FileWriter fos = new FileWriter (file + "/" + feature.text + ".feature")) {
                fos.write (feature.toString ());
                fos.write ("\n#INLINE\n\n");
                fos.write (feature.composeBackground ().toString ());
            }
            catch (IOException e) {
                e.printStackTrace ();
            }
    }
}
