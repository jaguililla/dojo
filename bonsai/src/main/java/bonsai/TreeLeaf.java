package bonsai;

import java.util.List;

public interface TreeLeaf <E> {
    /**
     * Should provide a list with all entity's ascendants.
     *
     * @param aEntity
     * @return
     */
    List<?> list (E aEntity);
}
