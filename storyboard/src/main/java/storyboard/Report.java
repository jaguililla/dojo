package storyboard;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO .
 *
 * @author jam
 */
abstract class Report {
    final Feature feature;
    final List<Result> results = new ArrayList<> ();

    protected Report (final Feature feature) {
        this.feature = feature;
    }

    abstract void generate ();
}
