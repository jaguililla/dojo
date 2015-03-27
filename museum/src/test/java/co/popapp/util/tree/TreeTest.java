package co.popapp.util.tree;

import static co.popapp.util.tree.Model.City.city;
import static co.popapp.util.tree.Model.Continent.continent;
import static co.popapp.util.tree.Model.Country.country;
import static java.lang.System.out;
import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;

import static co.popapp.util.tree.TreeNode.*;

import java.util.ArrayList;
import java.util.List;

import co.popapp.util.tree.Model.City;
import co.popapp.util.tree.Model.Continent;
import co.popapp.util.tree.Model.Country;
import org.junit.Test;

/**
 * Tree building tests.
 *
 * @author jamming
 */
public class TreeTest {
    /*
     * TESTS DATA
     */
    private final Continent europe = new Continent (1, "Europe");
    private final Continent america = new Continent (2, "America");
    private final Continent asia = new Continent (3, "Asia");

    private final Country spain = new Country (1, "Spain", europe);
    private final Country ireland = new Country (2, "Ireland", europe);
    private final Country usa = new Country (3, "USA", america);
    private final Country canada = new Country (4, "Canada", america);
    private final Country japan = new Country (5, "Japan", asia);
    private final Country china = new Country (6, "China", asia);

    private final City madrid = new City (1, "Madrid", spain);
    private final City barcelona = new City (2, "Barcelona", spain);
    private final City dublin = new City (3, "Dublin", ireland);
    private final City galway = new City (4, "Galway", ireland);
    private final City washington = new City (5, "Washington", usa);
    private final City newyork = new City (6, "New York", usa);
    private final City toronto = new City (7, "Toronto", canada);
    private final City vancouver = new City (8, "Vancouver", canada);
    private final City tokio = new City (9, "Tokio", japan);
    private final City osaka = new City (10, "Osaka", japan);
    private final City beijing = new City (11, "Beijing", china);
    private final City shanghai = new City (12, "Shanghai", china);

    /**
     * <p>Utility method to buildTree cities trees. It creates the 'City Tree Builder'
     * inline.</p>
     *
     * <p>The builder is responsible of listing a City ascendants and sorting cities taking
     * care of the ascendants).</p>
     *
     * @param aCities List of cities.
     * @return City tree.
     */
    private static TreeNode<?> buildCityTree (List<City> aCities) {
        final TreeNode<?> cityTree = buildTree (aCities, new TreeBuilder<City> () {
            @Override public List<?> list (City aEntity) {
                return asList (aEntity.country.continent, aEntity.country);
            }

            @Override public int compare (City o1, City o2) {
                if (o1.country.continent.id == o2.country.continent.id
                    && o1.country.id == o2.country.id
                    && o1.id == o2.id)
                    return 0;
                else if (o1.country.continent.id < o2.country.continent.id
                    || o1.country.id < o2.country.id
                    || o1.id < o2.id)
                    return -1;
                else
                    return 1;
            }
        });
        out.println (cityTree);
        return cityTree;
    }

    private static TreeNode<?> buildCityTree (City... aCities) {
        return buildCityTree (asList (aCities));
    }

    @Test public void nullList () {
        assertEquals (new TreeNode<String> ("ROOT"), buildCityTree ((List<City>)null));
    }

    @Test public void emptyList () {
        assertEquals (new TreeNode<String> ("ROOT"), buildCityTree (new ArrayList<City> ()));
    }

    @Test public void oneCityTree () {
        TreeNode<?> cityTree = buildCityTree (madrid);

        assertEquals (europe, cityTree.getDescendentValue (0));
        assertEquals (spain, cityTree.getDescendentValue (0, 0));
        assertEquals (madrid, cityTree.getDescendentValue (0, 0, 0));
    }

    @Test public void twoCitiesTree () {
        TreeNode<?> cityTree = buildCityTree (madrid, barcelona);

        assertEquals (europe, cityTree.getDescendentValue (0));
        assertEquals (spain, cityTree.getDescendentValue (0, 0));
        assertEquals (madrid, cityTree.getDescendentValue (0, 0, 0));
        assertEquals (barcelona, cityTree.getDescendentValue (0, 0, 1));
    }

    @Test public void duplicatedLeaves () {
        TreeNode<?> cityTree = buildCityTree (madrid, madrid);

        assertEquals (europe, cityTree.getDescendentValue (0));
        assertEquals (spain, cityTree.getDescendentValue (0, 0));
        assertEquals (madrid, cityTree.getDescendentValue (0, 0, 0));
        assertEquals (1, cityTree.getDescendent (0, 0).getChildren ().size ());
    }

    @Test public void largeTree () {
        checkAllCitiesTree (buildCityTree (
            madrid, barcelona, dublin, galway, washington, newyork,
            toronto, vancouver, tokio, osaka, beijing, shanghai));
    }

    @Test public void largeTreeFromUnsortedList () {
        checkAllCitiesTree (buildCityTree (
            shanghai, barcelona, dublin, galway, washington, newyork,
            toronto, vancouver, tokio, osaka, beijing, madrid));
    }

    private void checkAllCitiesTree (TreeNode<?> aCityTree) {
        assertEquals (europe,     aCityTree.getDescendentValue (0));
        assertEquals (spain,      aCityTree.getDescendentValue (0, 0));
        assertEquals (madrid,     aCityTree.getDescendentValue (0, 0, 0));
        assertEquals (barcelona,  aCityTree.getDescendentValue (0, 0, 1));
        assertEquals (ireland,    aCityTree.getDescendentValue (0, 1));
        assertEquals (dublin,     aCityTree.getDescendentValue (0, 1, 0));
        assertEquals (galway,     aCityTree.getDescendentValue (0, 1, 1));

        assertEquals (america,    aCityTree.getDescendentValue (1));
        assertEquals (usa,        aCityTree.getDescendentValue (1, 0));
        assertEquals (washington, aCityTree.getDescendentValue (1, 0, 0));
        assertEquals (newyork,    aCityTree.getDescendentValue (1, 0, 1));
        assertEquals (canada,     aCityTree.getDescendentValue (1, 1));
        assertEquals (toronto,    aCityTree.getDescendentValue (1, 1, 0));
        assertEquals (vancouver,  aCityTree.getDescendentValue (1, 1, 1));

        assertEquals (asia,       aCityTree.getDescendentValue (2));
        assertEquals (japan,      aCityTree.getDescendentValue (2, 0));
        assertEquals (tokio,      aCityTree.getDescendentValue (2, 0, 0));
        assertEquals (osaka,      aCityTree.getDescendentValue (2, 0, 1));
        assertEquals (china,      aCityTree.getDescendentValue (2, 1));
        assertEquals (beijing,    aCityTree.getDescendentValue (2, 1, 0));
        assertEquals (shanghai,   aCityTree.getDescendentValue (2, 1, 1));
    }

    /**
     * This method shows the tree usage in a declarative way. It doesn't use domain classes
     * (nodes are strings).
     */
    @Test public void declarativeTree () {
        final TreeNode<String> cityTree =
            new TreeNode<> ("Europe",
                new TreeNode<> ("Spain",
                    new TreeNode<> ("Madrid"),
                    new TreeNode<> ("Barcelona")
                ),
                new TreeNode<> ("Ireland",
                    new TreeNode<> ("Dublin"),
                    new TreeNode<> ("Galway")
                ),
                new TreeNode<> ("Germany",
                    new TreeNode<> ("Berlin"),
                    new TreeNode<> ("Munich")
                )
            );
        out.println (cityTree);

        assertEquals ("Europe",    cityTree.getValue ());
        assertEquals ("Spain",     cityTree.getDescendentValue (0));
        assertEquals ("Madrid",    cityTree.getDescendentValue (0, 0));
        assertEquals ("Barcelona", cityTree.getDescendentValue (0, 1));
        assertEquals ("Ireland",   cityTree.getDescendentValue (1));
        assertEquals ("Dublin",    cityTree.getDescendentValue (1, 0));
        assertEquals ("Galway",    cityTree.getDescendentValue (1, 1));
        assertEquals ("Germany",   cityTree.getDescendentValue (2));
        assertEquals ("Berlin",    cityTree.getDescendentValue (2, 0));
        assertEquals ("Munich",    cityTree.getDescendentValue (2, 1));
    }

    /**
     * Uses a custom builder to create a tree with two levels instead of three.
     */
    @Test public void treeWithLessLevelsRoot () {
        // List of entities
        List<City> cities = asList (
            city (1, "Madrid", country (1, "Spain", continent (1, "Europe"))),
            city (2, "Barcelona", country (1, "Spain", continent (1, "Europe"))),
            city (5, "Washington", country (3, "USA", continent (2, "America"))),
            city (6, "New York", country (3, "USA", continent (2, "America"))),
            city (9, "Tokio", country (5, "Japan", continent (3, "Asia"))),
            city (10, "Osaka", country (5, "Japan", continent (3, "Asia")))
        );

        // Convert in tree
        TreeNode<?> tree = buildTree (cities, new TreeBuilder<City> () {
            @Override public List<?> list (City aEntity) {
                return asList (aEntity.country);
            }

            @Override public int compare (City o1, City o2) {
                if (o1.country.id == o2.country.id && o1.id == o2.id)
                    return 0;
                else if (o1.country.id < o2.country.id || o1.id < o2.id)
                    return -1;
                else
                    return 1;
            }
        });
        out.println (tree);

        assertEquals (spain,      tree.getDescendentValue (0));
        assertEquals (madrid,     tree.getDescendentValue (0, 0));
        assertEquals (barcelona,  tree.getDescendentValue (0, 1));
        assertEquals (usa,        tree.getDescendentValue (1));
        assertEquals (washington, tree.getDescendentValue (1, 0));
        assertEquals (newyork,    tree.getDescendentValue (1, 1));
        assertEquals (japan,      tree.getDescendentValue (2));
        assertEquals (tokio,      tree.getDescendentValue (2, 0));
        assertEquals (osaka,      tree.getDescendentValue (2, 1));
    }

    /**
     * Uses a custom builder to create a tree with two levels instead of three.
     */
    @Test public void treeLessLevelsMiddle () {
        // List of entities
        List<City> cities = asList (
            city (1, "Madrid", country (1, "Spain", continent (1, "Europe"))),
            city (2, "Barcelona", country (1, "Spain", continent (1, "Europe"))),
            city (5, "Washington", country (3, "USA", continent (2, "America"))),
            city (6, "New York", country (3, "USA", continent (2, "America"))),
            city (9, "Tokio", country (5, "Japan", continent (3, "Asia"))),
            city (10, "Osaka", country (5, "Japan", continent (3, "Asia")))
        );

        // Convert in tree
        TreeNode<?> tree = buildTree (cities, new TreeBuilder<City> () {
            @Override public List<?> list (City aEntity) {
                return asList (aEntity.country.continent);
            }

            @Override public int compare (City o1, City o2) {
                if (o1.country.continent.id == o2.country.continent.id && o1.id == o2.id)
                    return 0;
                else if (o1.country.continent.id < o2.country.continent.id || o1.id < o2.id)
                    return -1;
                else
                    return 1;
            }
        });
        out.println (tree);

        assertEquals (europe,     tree.getDescendentValue (0));
        assertEquals (madrid,     tree.getDescendentValue (0, 0));
        assertEquals (barcelona,  tree.getDescendentValue (0, 1));
        assertEquals (america,    tree.getDescendentValue (1));
        assertEquals (washington, tree.getDescendentValue (1, 0));
        assertEquals (newyork,    tree.getDescendentValue (1, 1));
        assertEquals (asia,       tree.getDescendentValue (2));
        assertEquals (tokio,      tree.getDescendentValue (2, 0));
        assertEquals (osaka,      tree.getDescendentValue (2, 1));
    }

    @Test public void modelBuilder () {
        List<Continent> continents = asList (
            continent ("Europe",
                country ("Spain",
                    city ("Madrid"),
                    city ("Barcelona")
                ),
                country ("Ireland",
                    city ("Dublin"),
                    city ("Galway")
                )
            ),
            continent ("America",
                country ("USA",
                    city ("Washington"),
                    city ("New York")
                ),
                country ("Canada",
                    city ("Toronto"),
                    city ("Vancouver")
                )
            ),
            continent ("Asia",
                country ("Japan",
                    city ("Tokio"),
                    city ("Osaka")
                ),
                country ("China",
                    city ("Beijing"),
                    city ("Shanghai")
                )
            )
        );

        for (Continent c : continents)
            out.println (c);
    }
}
