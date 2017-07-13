/**
 * Contains all Storyboard's classes.
 *
 * <p>Classes belongs to two main categories:
 * <ul>
 *     <li>
 *         Domain classes (DSL): hold the features' data. They are independent of the testing
 *         library.
 *         <ul>
 *             <li>{@link storyboard.Specification}</li>
 *             <li>{@link storyboard.Action}</li>
 *             <li>{@link storyboard.Background}</li>
 *             <li>{@link storyboard.Feature}</li>
 *             <li>{@link storyboard.Scenario}</li>
 *             <li>{@link storyboard.Step}</li>
 *             <li>{@link storyboard.State}</li>
 *         </ul>
 *     </li>
 *     <li>
 *         Runner classes (executors): Used to run the specifications. They depends on the testing
 *         framework.
 *         <ul>
 *             <li>{@link storyboard.FeatureRunner}</li>
 *             <li>{@link storyboard.Result}</li>
 *         </ul>
 *     </li>
 * </ul>
 *
 * <p>TODO Add 'equals', 'hascode' and 'toString' to domain classes
 * <p>TODO Clean and refactor tests code
 *
 * @author jam
 */
package storyboard;
