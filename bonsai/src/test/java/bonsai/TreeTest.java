package bonsai;

import static java.lang.System.out;
import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.AfterClass;
import org.junit.Test;

public class TreeTest {

    /*
     * TESTS DATA
     */
    private Country spain = new Country (1, "España");
    private Country usa = new Country (2, "USA");

    private Region madridRegion = new Region (1, "Madrid", spain);
    private Region catalunya = new Region (2, "Cataluña", spain);
    private Region andalucia = new Region (3, "Andalucia", spain);
    private Region virginia = new Region (1, "Virginia", usa);

    private City madrid = new City (1, "Madrid", madridRegion);
    private City aranjuez = new City (2, "Aranjuez", madridRegion);
    private City barcelona = new City (3, "Barcelona", catalunya);
    private City tarragona = new City (4, "Tarragona", catalunya);
    private City sevilla = new City (5, "Sevilla", andalucia);
    private City malaga = new City (6, "Malaga", andalucia);
    private City springfield = new City (1, "Springfield", virginia);

    /**
     * <p>Utility method to build cities trees. It creates the 'City Tree Builder' inline.</p>
     *
     * <p>The builder is responsible of listing a City ascendants and sorting cities taking care of
     * the ascendants).</p>
     *
     * @param aCities List of cities.
     * @return City tree.
     */
    private static TreeNode<?> buildCityTree (List<City> aCities) {
        return TreeNode.build (aCities, new TreeBuilder<City> () {
            @Override public List<?> list (City aEntity) {
                return asList (aEntity.region.country, aEntity.region);
            }

            @Override public int compare (City o1, City o2) {
                if (o1.region.country.id == o2.region.country.id
                    && o1.region.id == o2.region.id
                    && o1.id == o2.id)
                    return 0;
                else if (o1.region.country.id < o2.region.country.id
                    || o1.region.id < o2.region.id
                    || o1.id < o2.id)
                    return -1;
                else
                    return 1;
            }
        });
    }

    @AfterClass public static void initDb () throws ClassNotFoundException, SQLException {
        Class.forName("org.h2.Driver");
        Connection conn = DriverManager.getConnection ("jdbc:h2:~/test");
        conn.close();
    }

    @Test public void nullList () {
        assertEquals (new TreeNode<> ("ROOT"), buildCityTree (null));
    }

    @Test public void emptyList () {
        assertEquals (new TreeNode<> ("ROOT"),
            buildCityTree (new ArrayList<City> ()));
    }

    @Test public void oneCityTree () {
        TreeNode<?> cityTree = buildCityTree (asList (madrid));
        out.println (cityTree);

        assertEquals (spain, cityTree.getDescendent (0).getValue ());
        assertEquals (madridRegion, cityTree.getDescendent (0, 0).getValue ());
        assertEquals (madrid, cityTree.getDescendent (0, 0, 0).getValue ());
    }

    @Test public void twoCitiesTree () {
        TreeNode<?> cityTree = buildCityTree (asList (madrid, aranjuez));
        out.println (cityTree);

        assertEquals (spain, cityTree.getDescendent (0).getValue ());
        assertEquals (madridRegion, cityTree.getDescendent (0, 0).getValue ());
        assertEquals (madrid, cityTree.getDescendent (0, 0, 0).getValue ());
        assertEquals (aranjuez, cityTree.getDescendent (0, 0, 1).getValue ());
    }

    @Test public void largeTree () {
        TreeNode<?> cityTree = buildCityTree (asList (
            madrid, aranjuez, barcelona, tarragona, sevilla, malaga, springfield));
        out.println (cityTree);

        assertEquals (spain, cityTree.getDescendent (0).getValue ());
        assertEquals (madridRegion, cityTree.getDescendent (0, 0).getValue ());
        assertEquals (madrid, cityTree.getDescendent (0, 0, 0).getValue ());
        assertEquals (aranjuez, cityTree.getDescendent (0, 0, 1).getValue ());
        assertEquals (catalunya, cityTree.getDescendent (0, 1).getValue ());
        assertEquals (barcelona, cityTree.getDescendent (0, 1, 0).getValue ());
        assertEquals (tarragona, cityTree.getDescendent (0, 1, 1).getValue ());
        assertEquals (andalucia, cityTree.getDescendent (0, 2).getValue ());
        assertEquals (sevilla, cityTree.getDescendent (0, 2, 0).getValue ());
        assertEquals (malaga, cityTree.getDescendent (0, 2, 1).getValue ());
        assertEquals (usa, cityTree.getDescendent (1).getValue ());
        assertEquals (virginia, cityTree.getDescendent (1, 0).getValue ());
        assertEquals (springfield, cityTree.getDescendent (1, 0, 0).getValue ());
    }

    @Test public void largeTreeFromUnsortedList () {
        TreeNode<?> cityTree = buildCityTree (asList (
            malaga, barcelona, sevilla, madrid, aranjuez, tarragona, springfield));
        out.println (cityTree);

        assertEquals (spain, cityTree.getDescendent (0).getValue ());
        assertEquals (madridRegion, cityTree.getDescendent (0, 0).getValue ());
        assertEquals (madrid, cityTree.getDescendent (0, 0, 0).getValue ());
        assertEquals (aranjuez, cityTree.getDescendent (0, 0, 1).getValue ());
        assertEquals (catalunya, cityTree.getDescendent (0, 1).getValue ());
        assertEquals (barcelona, cityTree.getDescendent (0, 1, 0).getValue ());
        assertEquals (tarragona, cityTree.getDescendent (0, 1, 1).getValue ());
        assertEquals (andalucia, cityTree.getDescendent (0, 2).getValue ());
        assertEquals (sevilla, cityTree.getDescendent (0, 2, 0).getValue ());
        assertEquals (malaga, cityTree.getDescendent (0, 2, 1).getValue ());
        assertEquals (usa, cityTree.getDescendent (1).getValue ());
        assertEquals (virginia, cityTree.getDescendent (1, 0).getValue ());
        assertEquals (springfield, cityTree.getDescendent (1, 0, 0).getValue ());
    }

    /**
     * This method shows the tree usage in a declarative way. It doesn't use domain classes (nodes
     * are strings).
     */
    @Test public void declarativeTree () {
        out.println (
            new TreeNode<> ("España",
                new TreeNode<> ("Madrid",
                    new TreeNode<> ("Madrid"),
                    new TreeNode<> ("Aranjuez")
                ),
                new TreeNode<> ("Cataluña",
                    new TreeNode<> ("Barcelona"),
                    new TreeNode<> ("Tarragona")
                ),
                new TreeNode<> ("Andalucia",
                    new TreeNode<> ("Sevilla"),
                    new TreeNode<> ("Malaga")
                )
            )
        );
    }

    /**
     * Shows a complete usage scenario without utility code.
     */
    @Test public void treeBuilderInline () {
        // List of entities
        List<City> cities = asList (
            new City (1, "Madrid", new Region (1, "Madrid", new Country (1, "España"))),
            new City (2, "Aranjuez", new Region (1, "Madrid", new Country (1, "España"))),
            new City (3, "Barcelona", new Region (2, "Cataluña", new Country (1, "España"))),
            new City (4, "Tarragona", new Region (2, "Cataluña", new Country (1, "España"))),
            new City (5, "Sevilla", new Region (3, "Andalucia", new Country (1, "España"))),
            new City (6, "Malaga", new Region (3, "Andalucia", new Country (1, "España"))),
            new City (1, "Springfield", new Region (1, "Virginia", new Country (2, "USA")))
        );

        // Convert in tree
        TreeNode<?> tree = TreeNode.build (cities, new TreeBuilder<City> () {
            @Override
            public List<?> list (City aEntity) {
                return asList (aEntity.region.country, aEntity.region);
            }

            @Override
            public int compare (City o1, City o2) {
                if (o1.region.country.id == o2.region.country.id
                    && o1.region.id == o2.region.id
                    && o1.id == o2.id)
                    return 0;
                else if (o1.region.country.id < o2.region.country.id
                    || o1.region.id < o2.region.id
                    || o1.id < o2.id)
                    return -1;
                else
                    return 1;
            }
        });
        out.println (tree);
    }
}
