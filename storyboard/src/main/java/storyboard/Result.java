package storyboard;

import static storyboard.util.Strings.replace;

import java.util.Map;

/**
 * TODO .
 *
 * @author jam
 */
public final class Result {
    enum Type { OK, FAIL, ERROR, IGNORED }

    final Action action;
    final Map<String, Object> example;
    final Type type;

    public Result (Action aAction, Map<String, Object> aExample, Type aType) {
        action = aAction;
        example = aExample;
        type = aType;
    }

    @Override public String toString () {
        return replace (example, action.toString ());
    }
}
