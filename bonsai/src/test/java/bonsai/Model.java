package bonsai;

/**
 * Example domain classes. To work with the Tree Builder, they should define 'equals' (and
 * therefore 'hashCode').
 *
 * @author jamming
 */
public class Model {
    public static class Continent {
        private static int lId;

        public static Continent continent (int aId, String aName) {
            return new Continent (aId, aName);
        }

        public static Continent continent (int aId, String aName, Country... aCountries) {
            Continent result = continent (aId, aName);

            for (Country c : aCountries)
                c.continent = result;

            return result;
        }

        public static Continent continent (String aName, Country... aCountries) {
            return continent (++lId, aName, aCountries);
        }

        public int id;
        public String name;

        Continent (int aId, String aName) {
            id = aId;
            name = aName;
        }

        @Override public String toString () {
            return String.valueOf (id) + " " + name;
        }

        @Override public boolean equals (Object o) {
            if (this == o)
                return true;
            if (!(o instanceof Continent))
                return false;

            Continent region = (Continent)o;

            return id == region.id && name.equals (region.name);
        }

        @Override public int hashCode () {
            return 31 * id + name.hashCode ();
        }
    }

    public static class Country {
        private static int lId;

        public static Country country (int aId, String aName, Continent aContinent) {
            return new Country (aId, aName, aContinent);
        }

        public static Country country (int aId, String aName, City... aCities) {
            Country result = new Country (aId, aName, null);

            for (City c : aCities)
                c.country = result;

            return result;
        }

        public static Country country (String aName, City... aCities) {
            return country (++lId, aName, aCities);
        }

        public int id;
        public String name;
        public Continent continent;

        Country (int aId, String aName, Continent aContinent) {
            id = aId;
            name = aName;
            continent = aContinent;
        }

        @Override public String toString () {
            return String.valueOf (id) + " " + name;
        }

        @Override public boolean equals (Object o) {
            if (this == o)
                return true;
            if (!(o instanceof Country))
                return false;

            Country country = (Country)o;

            return id == country.id && name.equals (country.name)
                && continent.equals (country.continent);
        }

        @Override public int hashCode () {
            int result = 31 * id + name.hashCode ();
            return 31 * result + continent.hashCode ();
        }
    }

    public static class City {
        private static int lId;

        public static City city (int aId, String aName, Country aCountry) {
            return new City (aId, aName, aCountry);
        }

        public static City city (int aId, String aName) {
            return new City (aId, aName, null);
        }

        public static City city (String aName) {
            return city (++lId, aName);
        }

        public int id;
        public String name;
        public Country country;

        City (int aId, String aName, Country aCountry) {
            id = aId;
            name = aName;
            country = aCountry;
        }

        @Override public String toString () {
            return String.valueOf (id) + " " + name;
        }

        @Override public boolean equals (Object o) {
            if (this == o)
                return true;
            if (!(o instanceof City))
                return false;

            City city = (City)o;

            return id == city.id && name.equals (city.name) && country.equals (city.country);
        }

        @Override public int hashCode () {
            int result = 31 * id + name.hashCode ();
            return 31 * result + country.hashCode ();
        }
    }
}
