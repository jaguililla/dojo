package co.popapp.util.tree;

import java.util.Comparator;
import java.util.List;

/**
 * Supplies the needed behaviour to buildTree a table from a list of entities. Essentially this is
 * providing a list with an Entity's parents and sorting them taking care of the parents. Ie:
 *
 * <pre>
 * Asuming:
 *
 * ENTITIES: E1, E2, E3, E4, E5
 *
 * ENTITIES PARENTS:
 *   - E1 -> P11, P22, P33
 *   - E2 -> P12, P21, P34
 *   - E3 -> P11, P22, P35
 *   - E4 -> P13, P23, P33
 *
 * ORDERED TABLE:
 *   - E1 -> P11, P22, P33
 *   - E3 -> P11, P22, P35
 *   - E2 -> P12, P21, P34
 *   - E4 -> P13, P23, P33
 *
 * TREE:
 *
 * - ROOT
 *   - P11
 *     - P22
 *       - P33
 *         - E1
 *       - P35
 *         - E3
 *   - P12
 *     - P21
 *       - P34
 *         - E2
 *   - P13
 *     - P23
 *       - P33
 *         - E4
 * </pre>
 *
 * @param <E> Type of the entities from which the builder will arrange a tree (tree leaves).
 * @author jamming
 */
public interface TreeBuilder<E> extends Comparator<E>, TreeLeaf<E> {}
