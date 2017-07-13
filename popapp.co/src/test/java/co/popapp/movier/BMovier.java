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
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

// C L A S S ///////////////////////////////////////////////////////////////////////////////////////
/**
 * TODO .
 * Movier (Movie rental)
 * @author jamming
 */
public class BMovier {
    /**
     * TODO .
     * @author jamming
     */
    public static class Actor extends Person {
        /** TODO . */
        public URL website;
        /** TODO . */
        public final List<Film> films = new ArrayList<Film> ();

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
    }

    /**
     * TODO .
     * @author jamming
     */
    public static class Address {
        /** TODO . */
        public String street;
        /** TODO . */
        public City city;
        /** TODO . */
        public Integer postalCode;

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
            city = aCity;
        }
    }

    /**
     * TODO .
     * @author jamming
     */
    public static class Category {
        /** TODO . */
        public String name;

        /**
         * TODO .
         * @param aName
         */
        public Category (String aName) {
            name = aName;
        }
    }

    /**
     * TODO .
     * @author jamming
     */
    public static class City {
        /** TODO . */
        public String name;
        /** TODO . */
        public Country country;

        /**
         * TODO .
         * @param aName
         * @param aCountry
         */
        public City (String aName, Country aCountry) {
            name = aName;
            country = aCountry;
        }
    }

    /**
     * TODO .
     * @author jamming
     */
    public static class Country {
        /** TODO . */
        public String name;

        /**
         * TODO .
         * @param aName
         */
        public Country (String aName) {
            name = aName;
        }
    }

    /**
     * TODO .
     * @author jamming
     */
    public static class Customer extends Person {
        /** TODO . */
        public Address address;
        /** TODO . */
        public URL email;
        /** TODO . */
        public Store store;
        /** TODO . */
        public Boolean active;

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
    public static class Film {
        /** TODO . */
        public String title;
        /** TODO . */
        public String description;
        /** TODO . */
        public Integer year;
        /** TODO . */
        public String originalLanguage;

        /** TODO . */
        public final List<String> languages = new ArrayList<String> ();
        /** TODO . */
        public final List<Category> categories = new ArrayList<Category> ();
        /** TODO . */
        public final List<Actor> actors = new ArrayList<Actor> ();

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
            title = aTitle;
            actors.addAll (Arrays.asList (aActors));
        }
    }

    /**
     * TODO .
     * @author jamming
     */
    public static class Inventory {
        /** TODO . */
        public Film film;
        /** TODO . */
        public Store store;
    }

    /**
     * TODO .
     * @author jamming
     */
    public static class Payment {
        /** TODO . */
        public Customer customer;
        /** TODO . */
        public Staff staff;
        /** TODO . */
        public Float amount;
        /** TODO . */
        public Date date;
    }

    /**
     * TODO .
     * @author jamming
     */
    public static class Person {
        /** TODO . */
        public String name;
        /** TODO . */
        public String surname;
        /** TODO . */
        public Integer height;
        /** TODO . */
        public Integer weight;
        /** TODO . */
        public Date birthdate;

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
            name = aName;
        }
    }

    /**
     * TODO .
     * @author jamming
     */
    public static class Rental {
        /** TODO . */
        public Inventory inventory;
        /** TODO . */
        public Customer customer;
        /** TODO . */
        public Staff staff;
        /** TODO . */
        public Date date;
        /** TODO . */
        public Date returnDate;
    }

    /**
     * TODO .
     * @author jamming
     */
    public static class Staff extends Person {
        /** TODO . */
        public URL email;
        /** TODO . */
        public Store store;
        /** TODO . */
        public Boolean active;
    }

    /**
     * TODO .
     * @author jamming
     */
    public static class Store {
        /** TODO . */
        public Staff manager;
        /** TODO . */
        public Address address;
    }
}
// E O F ///////////////////////////////////////////////////////////////////////////////////////////
