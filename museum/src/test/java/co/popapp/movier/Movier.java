/*
 * POPAPP Copyright © 2011 Juan José Aguililla. All rights reserved.
 *
 * This program comes WITHOUT ANY WARRANTY; It is free software, you can redistribute it and/or
 * modify it under the terms of the GNU General Public License, Version 3.0.
 * You may obtain a copy of the License at: http://www.gnu.org/licenses/gpl.html
 */

// P A C K A G E ///////////////////////////////////////////////////////////////////////////////////
package co.popapp.movier;

// I M P O R T /////////////////////////////////////////////////////////////////////////////////////
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.Arrays;
import java.util.Date;

import co.popapp.model.Entity.Observable;
import co.popapp.model.Field;
import co.popapp.model.KeyedEntity;
import co.popapp.model.KeyedEntity.AutoKeyEntity;
import co.popapp.model.ListField;
import co.popapp.model.Model.Module;
import co.popapp.model.ReadField;

// C L A S S ///////////////////////////////////////////////////////////////////////////////////////
/**
 * TODO .
 * @author jam
 */
class MovierWebapp {
    // TODO Implement webapp
}

/**
 * TODO .
 * Movier (Movie rental). Based on the sample DB schema 'Sakila' from MySQL.
 * @author jamming
 */
public class Movier extends Module {
    /**
     * TODO .
     * @author jamming
     */
    public static class Actor extends Person implements Observable {
        /** TODO . */
        public final Field<URL> website = field ();
        /** TODO . */
        public final ListField<Film> films = listField ();

        /**
         * TODO .
         */
        public Actor () {
            super ();
        }

        /**
         * TODO .
         * @param aName
         */
        public Actor (String aName) {
            super (aName);
        }

        @Override public Actor birthdate (Date aV) {
            return birthdate.set (Actor.class, aV);
        }

        public Actor films (Film... aV) {
            return films.addAll (Actor.class, aV);
        }

        @Override public Actor height (Integer aV) {
            return height.set (Actor.class, aV);
        }

        @Override public Actor name (String aV) {
            return name.set (Actor.class, aV);
        }

        @Override public Actor surname (String aV) {
            return surname.set (Actor.class, aV);
        }

        public Actor website (URL aV) {
            return website.set (Actor.class, aV);
        }

        @Override
        public Actor weight (Integer aV) {
            return weight.set (Actor.class, aV);
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

    /*
     * Builder methods for this module entities
     */

    public static Actor actor () { return new Actor (); }

    public static Category category (String aName) { return new Category (aName); }

    public static Film film () { return new Film (); }

    public static void main (String [] aArgs) throws FileNotFoundException, IOException, ClassNotFoundException {
        Film firstBlood = new Film ()
            .title.set (Film.class, "First Blood")
            .description.set (Film.class, "A small town's police force war against a war vet.")
            .year.set (Film.class, 1982)

            .actors.addAll (Film.class,
                new Actor ()
                    .name.set (Actor.class, "Sylvester")
                    .surname.set (Actor.class, "Stallone"),
                new Actor ()
                    .name.set (Actor.class, "Bryan")
                    .surname.set (Actor.class, "Dennehy"),
                new Actor ()
                    .name.set (Actor.class, "Richard")
                    .surname.set (Actor.class, "Crenna")
            )

            .categories.addAll (Film.class,
                new Category ("Action"),
                new Category ("Adventure"),
                new Category ("Drama")
            );

        Film rambo = film ()
            .title ("Rambo")
            .description ("John Rambo is released from prison for a covert mission.")
            .year (1985)

            .actors (
                actor ()
                    .name ("Sylvester")
                    .surname ("Stallone"),
                actor ()
                    .name ("Charles")
                    .surname ("Napier"),
                actor ()
                    .name ("Richard")
                    .surname ("Crenna")
            )

            .categories (
                category ("Action"),
                category ("Adventure"),
                category ("Thriller")
            );

        System.out.println (firstBlood);
        System.out.println (rambo);

        ObjectOutputStream writer = null;
        ObjectInputStream reader = null;

        try {
            OutputStream output = new FileOutputStream ("target/test");
            writer = new ObjectOutputStream (output);
            writer.writeObject (firstBlood);
            writer.writeObject (rambo);

            InputStream input = null;
            input = new FileInputStream ("target/test");
            reader = new ObjectInputStream (input);
            Film lFirstBlood = (Film)reader.readObject ();
            Film lRambo = (Film)reader.readObject ();

            System.out.println (
                "EQUALS? " + (firstBlood.equals (lFirstBlood) && rambo.equals (lRambo)) );
            System.out.println (
                "SAME? " + (firstBlood == lFirstBlood && rambo == lRambo) );
        }
        finally {
            if (writer != null)
                writer.close ();
        }
    }
}
// E O F ///////////////////////////////////////////////////////////////////////////////////////////
