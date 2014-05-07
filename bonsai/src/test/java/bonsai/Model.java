package bonsai;/*
 * Example domain classes. To work with the Tree Builder, they should define 'equals' (and
 * therefore 'hasCode')
 */

class Country {
    public final int id;
    public final String name;

    Country (int aId, String aName) {
        id = aId;
        name = aName;
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

        return id == country.id && name.equals (country.name);
    }

    @Override public int hashCode () {
        return 31 * id + name.hashCode ();
    }
}

class Region {
    public final int id;
    public final String name;
    public final Country country;

    Region (int aId, String aName, Country aCountry) {
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
        if (!(o instanceof Region))
            return false;

        Region region = (Region)o;

        return id == region.id && country.equals (region.country) && name.equals (region.name);
    }

    @Override public int hashCode () {
        int result = 31 * id + name.hashCode ();
        return 31 * result + country.hashCode ();
    }
}

class City {
    public final int id;
    private final String name;
    public final Region region;

    City (int aId, String aName, Region aRegion) {
        id = aId;
        name = aName;
        region = aRegion;
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

        return id == city.id && name.equals (city.name) && region.equals (city.region);
    }

    @Override public int hashCode () {
        int result = 31 * id + name.hashCode ();
        return 31 * result + region.hashCode ();
    }
}
