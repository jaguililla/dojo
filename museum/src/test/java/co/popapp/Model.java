// P A C K A G E ///////////////////////////////////////////////////////////////////////////////////
package co.popapp;

// I M P O R T /////////////////////////////////////////////////////////////////////////////////////


import java.net.URL;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import co.popapp.model.Field;
import co.popapp.model.KeyedEntity;
import co.popapp.model.KeyedEntity.AutoKeyEntity;
import co.popapp.model.ListField;
import co.popapp.model.ReadField;

// T Y P E S ///////////////////////////////////////////////////////////////////////////////////////
enum Style { ACESSOR, NAME, BOTH }

@interface Model {}
@interface Observable {}
@interface Default {}
@interface Get {
    Style value () default Style.ACESSOR;
}
@interface Set {}
/**
 * TODO .
 *
 * methods to Build (declarative idiom)
 *
 * ie:
 * Actor a = actor ()
 *      .name ("J")
 *      .surname ("A")
 *
 * @author jam
 */
@interface Builder {}
/**
 * TODO .
 * Generate metadata. ie: Actor.meta.fields.NAME
 *
 * @author jam
 */
@interface Metagen {}
@interface Copiable {}
@interface Comparable {}
/**
 * TODO .
 *
 * Generate set (NAME, val)
 * Where name is a member of the fields enum
 *
 * @author jam
 */
@interface Mapping {}

interface IField {
    <T> void set (String n, T val);
}
/**
 * TODO .
 * Default Java visibility is 'public'
 * Default method visibility public?
 *
 * @author jam
 */
@Model interface TestModel {
    /**
     * TODO .
     * @author jamming
     */
    @Observable public class Actor extends Person {
        /** TODO . */
        @Builder @Get private URL website;
        /** TODO . */
        @Builder @Get (Style.NAME) private ListField<Film> films = listField ();

        /**
         * TODO .
         * @param aName .
         */
        public Actor (@Default () String aName) {
            super (aName);
        }
    }

    /**
     * TODO .
     * @author jamming
     */
    public static class Address extends AutoKeyEntity {
        /** TODO . */
        public final Field<String> street = field ();
        /** TODO . */
        public final Field<City> city = field ();
        /** TODO . */
        public final Field<Integer> postalCode = field ();
        public Actor a;

        /**
         * TODO .
         */
        public Address () {
            super ();
        }

        /**
         * TODO .
         * @param aCity
         */
        public Address (City aCity) {
            city.set (aCity);
        }
    }

    /**
     * TODO .
     * @author jamming
     */
    public static class Category extends AutoKeyEntity {
        /** TODO . */
        public final ReadField<String> name = readField ();

        /**
         * TODO .
         * @param aName
         */
        // TODO Remove, this shouldn't exist, is here because of a bug serializing
        public Category () {
            super ();
        }

        /**
         * TODO .
         * @param aName
         */
        public Category (String aName) {
            set (name, aName);
        }
    }

    /**
     * TODO .
     * @author jamming
     */
    public static class City extends AutoKeyEntity {
        /** TODO . */
        public final ReadField<String> name = readField ();
        /** TODO . */
        public final ReadField<Country> country = readField ();

        /**
         * TODO .
         * @param aName
         * @param aCountry
         */
        public City (String aName, Country aCountry) {
            set (name, aName);
            set (country, aCountry);
        }
    }

    /**
     * TODO .
     * @author jamming
     */
    public static class Country extends KeyedEntity<String> {
        /** TODO . */
        public final ReadField<String> name = key;

        /**
         * TODO .
         * @param aName
         */
        public Country (String aName) {
            set (name, aName);
        }
    }

    /**
     * TODO .
     * @author jamming
     */
    public static class Customer extends Person {
        /** TODO . */
        public final Field<Address> address = field ();
        /** TODO . */
        public final Field<URL> email = field ();
        /** TODO . */
        public final Field<Store> store = field ();
        /** TODO . */
        public final Field<Boolean> active = field ();

        /**
         * TODO .
         */
        public Customer () {
            super ();
        }

        /**
         * TODO .
         * @param aName
         */
        public Customer (String aName) {
            super (aName);
        }
    }

    /**
     * TODO .
     * @author jamming
     */
    public static class Film extends AutoKeyEntity {
        /** TODO . */
        public final Field<String> title = field ();
        /** TODO . */
        public final Field<String> description = field ();
        /** TODO . */
        public final Field<Integer> year = field ();
        /** TODO . */
        public final Field<String> originalLanguage = field ();
        /** TODO . */
        public final ListField<String> languages = listField ();
        /** TODO . */
        public final ListField<Category> categories = listField ();
        /** TODO . */
        public final ListField<Actor> actors = listField ();

        /**
         * TODO .
         */
        public Film () {
            super ();
        }

        /**
         * TODO .
         * @param aTitle
         * @param aActors
         */
        public Film (String aTitle, Actor... aActors) {
            title.set (aTitle);
            actors.addAll (Arrays.asList (aActors));
        }

        public Film actors (Actor... aV) {
            return actors.addAll (Film.class, aV);
        }

        public Film categories (Category... aV) {
            return categories.addAll (Film.class, aV);
        }

        public Film description (String aV) {
            return description.set (Film.class, aV);
        }

        public Film languages (String... aV) {
            return languages.addAll (Film.class, aV);
        }

        public Film originalLanguage (String aV) {
            return originalLanguage.set (Film.class, aV);
        }

        public Film title (String aV) {
            return title.set (Film.class, aV);
        }

        public Film year (Integer aV) {
            return year.set (Film.class, aV);
        }
    }

    /**
     * TODO .
     * @author jamming
     */
    public static class Inventory extends AutoKeyEntity {
        /** TODO . */
        public final Field<Film> film = field ();
        /** TODO . */
        public final Field<Store> store = field ();
    }

    /**
     * TODO .
     * @author jamming
     */
    public static class Payment extends AutoKeyEntity {
        /** TODO . */
        public final Field<Customer> customer = field ();
        /** TODO . */
        public final Field<Staff> staff = field ();
        /** TODO . */
        public final Field<Float> amount = field ();
        /** TODO . */
        public final Field<Date> date = field ();
    }

    /**
     * TODO .
     * @author jamming
     */
    public static class Person extends AutoKeyEntity {
        /** TODO . */
        public final Field<String> name = field ();
        /** TODO . */
        public final Field<String> surname = field ();
        /** TODO . */
        public final Field<Integer> height = field ();
        /** TODO . */
        public final Field<Integer> weight = field ();
        /** TODO . */
        public final Field<Date> birthdate = field ();

        /**
         * TODO .
         */
        public Person () {
            super ();
        }

        /**
         * TODO .
         * @param aName
         */
        public Person (String aName) {
            name.set (aName);
        }

        // BUILDERS
        public Person birthdate (Date aV) {
            return birthdate.set (Person.class, aV);
        }

        public Person height (Integer aV) {
            return height.set (Person.class, aV);
        }

        public Person name (String aV) {
            return name.set (Person.class, aV);
        }

        public Person surname (String aV) {
            return surname.set (Person.class, aV);
        }

        public Person weight (Integer aV) {
            return weight.set (Person.class, aV);
        }
    }

    /**
     * TODO .
     * @author jamming
     */
    public static class Rental extends AutoKeyEntity {
        /** TODO . */
        public final Field<Inventory> inventory = field ();
        /** TODO . */
        public final Field<Customer> customer = field ();
        /** TODO . */
        public final Field<Staff> staff = field ();
        /** TODO . */
        public final Field<Date> date = field ();
        /** TODO . */
        public final Field<Date> returnDate = field ();
    }

    /**
     * TODO .
     * @author jamming
     */
    public static class Staff extends Person {
        /** TODO . */
        public final Field<URL> email = field ();
        /** TODO . */
        public final Field<Store> store = field ();
        /** TODO . */
        public final Field<Boolean> active = field ();
    }

    /**
     * TODO .
     * @author jamming
     */
    public static class Store extends AutoKeyEntity {
        /** TODO . */
        public final Field<Staff> manager = field ();
        /** TODO . */
        public final Field<Address> address = field ();
    }
}

@Model class TestModel2 {
    /**
     * TODO .
     * @author jamming
     */
    @Observable public class Actor extends Person {
        /** TODO . */
        private URL website;
        /** TODO . */
        private List<Film> films;

        /**
         * TODO .
         * @param aName .
         */
        public Actor (@Default () String aName) {
            super (aName);
        }
    }

    /**
     * TODO .
     * @author jamming
     */
    public static class Address extends AutoKeyEntity {
        /** TODO . */
        public final Field<String> street = field ();
        /** TODO . */
        public final Field<City> city = field ();
        /** TODO . */
        public final Field<Integer> postalCode = field ();
        public Actor a;

        /**
         * TODO .
         */
        public Address () {
            super ();
        }

        /**
         * TODO .
         * @param aCity
         */
        public Address (City aCity) {
            city.set (aCity);
        }
    }

    /**
     * TODO .
     * @author jamming
     */
    public static class Category extends AutoKeyEntity {
        /** TODO . */
        public final ReadField<String> name = readField ();

        /**
         * TODO .
         * @param aName
         */
        // TODO Remove, this shouldn't exist, is here because of a bug serializing
        public Category () {
            super ();
        }

        /**
         * TODO .
         * @param aName
         */
        public Category (String aName) {
            set (name, aName);
        }
    }

    /**
     * TODO .
     * @author jamming
     */
    public static class City extends AutoKeyEntity {
        /** TODO . */
        public final ReadField<String> name = readField ();
        /** TODO . */
        public final ReadField<Country> country = readField ();

        /**
         * TODO .
         * @param aName
         * @param aCountry
         */
        public City (String aName, Country aCountry) {
            set (name, aName);
            set (country, aCountry);
        }
    }

    /**
     * TODO .
     * @author jamming
     */
    public static class Country extends KeyedEntity<String> {
        /** TODO . */
        public final ReadField<String> name = key;

        /**
         * TODO .
         * @param aName
         */
        public Country (String aName) {
            set (name, aName);
        }
    }

    /**
     * TODO .
     * @author jamming
     */
    public static class Customer extends Person {
        /** TODO . */
        public final Field<Address> address = field ();
        /** TODO . */
        public final Field<URL> email = field ();
        /** TODO . */
        public final Field<Store> store = field ();
        /** TODO . */
        public final Field<Boolean> active = field ();

        /**
         * TODO .
         */
        public Customer () {
            super ();
        }

        /**
         * TODO .
         * @param aName
         */
        public Customer (String aName) {
            super (aName);
        }
    }

    /**
     * TODO .
     * @author jamming
     */
    public static class Film extends AutoKeyEntity {
        /** TODO . */
        public final Field<String> title = field ();
        /** TODO . */
        public final Field<String> description = field ();
        /** TODO . */
        public final Field<Integer> year = field ();
        /** TODO . */
        public final Field<String> originalLanguage = field ();
        /** TODO . */
        public final ListField<String> languages = listField ();
        /** TODO . */
        public final ListField<Category> categories = listField ();
        /** TODO . */
        public final ListField<Actor> actors = listField ();

        /**
         * TODO .
         */
        public Film () {
            super ();
        }

        /**
         * TODO .
         * @param aTitle
         * @param aActors
         */
        public Film (String aTitle, Actor... aActors) {
            title.set (aTitle);
            actors.addAll (Arrays.asList (aActors));
        }

        public Film actors (Actor... aV) {
            return actors.addAll (Film.class, aV);
        }

        public Film categories (Category... aV) {
            return categories.addAll (Film.class, aV);
        }

        public Film description (String aV) {
            return description.set (Film.class, aV);
        }

        public Film languages (String... aV) {
            return languages.addAll (Film.class, aV);
        }

        public Film originalLanguage (String aV) {
            return originalLanguage.set (Film.class, aV);
        }

        public Film title (String aV) {
            return title.set (Film.class, aV);
        }

        public Film year (Integer aV) {
            return year.set (Film.class, aV);
        }
    }

    /**
     * TODO .
     * @author jamming
     */
    public static class Inventory extends AutoKeyEntity {
        /** TODO . */
        public final Field<Film> film = field ();
        /** TODO . */
        public final Field<Store> store = field ();
    }

    /**
     * TODO .
     * @author jamming
     */
    public static class Payment extends AutoKeyEntity {
        /** TODO . */
        public final Field<Customer> customer = field ();
        /** TODO . */
        public final Field<Staff> staff = field ();
        /** TODO . */
        public final Field<Float> amount = field ();
        /** TODO . */
        public final Field<Date> date = field ();
    }

    /**
     * TODO .
     * @author jamming
     */
    public static class Person extends AutoKeyEntity {
        /** TODO . */
        public final Field<String> name = field ();
        /** TODO . */
        public final Field<String> surname = field ();
        /** TODO . */
        public final Field<Integer> height = field ();
        /** TODO . */
        public final Field<Integer> weight = field ();
        /** TODO . */
        public final Field<Date> birthdate = field ();

        /**
         * TODO .
         */
        public Person () {
            super ();
        }

        /**
         * TODO .
         * @param aName
         */
        public Person (String aName) {
            name.set (aName);
        }

        // BUILDERS
        public Person birthdate (Date aV) {
            return birthdate.set (Person.class, aV);
        }

        public Person height (Integer aV) {
            return height.set (Person.class, aV);
        }

        public Person name (String aV) {
            return name.set (Person.class, aV);
        }

        public Person surname (String aV) {
            return surname.set (Person.class, aV);
        }

        public Person weight (Integer aV) {
            return weight.set (Person.class, aV);
        }
    }

    /**
     * TODO .
     * @author jamming
     */
    public static class Rental extends AutoKeyEntity {
        /** TODO . */
        public final Field<Inventory> inventory = field ();
        /** TODO . */
        public final Field<Customer> customer = field ();
        /** TODO . */
        public final Field<Staff> staff = field ();
        /** TODO . */
        public final Field<Date> date = field ();
        /** TODO . */
        public final Field<Date> returnDate = field ();
    }

    /**
     * TODO .
     * @author jamming
     */
    public static class Staff extends Person {
        /** TODO . */
        public final Field<URL> email = field ();
        /** TODO . */
        public final Field<Store> store = field ();
        /** TODO . */
        public final Field<Boolean> active = field ();
    }

    /**
     * TODO .
     * @author jamming
     */
    public static class Store extends AutoKeyEntity {
        /** TODO . */
        public final Field<Staff> manager = field ();
        /** TODO . */
        public final Field<Address> address = field ();
    }
}
// E O F ///////////////////////////////////////////////////////////////////////////////////////////
