/*
 * Copyright © 2014 Juan José Aguililla. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file
 * except in compliance with the License. You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the
 * License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language governing permissions
 * and limitations under the License.
 */

package demos;

import static demos.Address.address;
import static demos.Car.car;
import static demos.ObjectUtils.equal;
import static demos.ObjectUtils.stringOf;
import static demos.ObjectUtils.print;
import static demos.Person.namedPerson;
import static demos.Person.person;
import static java.lang.String.format;
import static java.util.Arrays.asList;
import static java.util.Objects.hash;
import static java.lang.System.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;

class ObjectUtils {
    public static <T> boolean equal (T a, T b) { return Objects.equals (a, b); }

    public static String stringOf (Object a) { return Objects.toString (a); }

    public static String print (Object o) {
        return format ("%s [%s]\n", o.getClass ().getSimpleName (), o.hashCode ());
    }

    public static String print (String name, Object value) {
        return value == null? "" : format ("%s : %s\n", name, value);
    }

    private ObjectUtils () {
        throw new IllegalArgumentException ();
    }
}

class Address {
    public static Address address () {
        return new Address (null, null, null);
    }

    public final String streetAddress, city, state;

    private Address (final String streetAddress, final String city, final String state) {
        this.streetAddress = streetAddress;
        this.city = city;
        this.state = state;
    }

    public String streetAddress () {
        return streetAddress;
    }

    public Address withStreetAddress (String streetAddress) {
        return new Address (streetAddress, city, state);
    }

    public Address streetAddress (String streetAddress) {
        return new Address (streetAddress, city, state);
    }

    public String city () {
        return city;
    }

    public Address withCity (String city) {
        return new Address (streetAddress, city, state);
    }

    public Address city (String city) {
        return new Address (streetAddress, city, state);
    }

    public String state () {
        return state;
    }

    public Address withState (String state) {
        return new Address (streetAddress, city, state);
    }

    public Address state (String state) {
        return new Address (streetAddress, city, state);
    }

    @Override public boolean equals (Object o) {
        if (this == o)
            return true;
        if (o == null || getClass () != o.getClass ())
            return false;

        Address that = (Address)o;
        return
            equal (this.streetAddress, that.streetAddress) &&
            equal (this.city, that.city) &&
            equal (this.state, that.state);
    }

    @Override public int hashCode () {
        return hash (streetAddress, city, state);
    }

    @Override public String toString () {
        return
            print (this) +
            print ("streetAddress", streetAddress) +
            print ("city", city) +
            print ("state", state);
    }
}

class Car {
    public static Car car (String brand, String model) {
        return new Car (brand, model);
    }

    public final String brand, model;

    private Car (String brand, String model) {
        this.brand = brand;
        this.model = model;
    }

    public Car brand (String brand) { return new Car (brand, model); }
    public Car model (String model) { return new Car (brand, model); }
}
class Person {
    public static Person person () {
        return new Person (
            null, null, null, null, null, false, false, false, null, new ArrayList<>());
    }

    public static Person person (Function<Person, Person> builder) {
        Person result = person ();
        return builder.apply (result);
    }

    public static Person namedPerson (String firstName, String middleName, String lastName) {
        return new Person (
            lastName, firstName, middleName,
            null, null, false, false, false, null, new ArrayList<>());
    }

    public final String lastName, firstName, middleName;
    public final String salutation;
    public final String suffix;
    public final boolean isFemale;
    public final boolean isEmployed;
    public final boolean isHomeOwner;

    public final Address address;

    public final List<Car> cars;

    /**
     * All fields constructor (REQUIRED).
     */
    private Person (
        final String lastName, final String firstName, final String middleName,
        final String salutation,
        final String suffix,
        final boolean isFemale,
        final boolean isEmployed,
        final boolean isHomeOwner,
        final Address address,
        final List<Car> cars) {

        this.lastName = lastName;
        this.firstName = firstName;
        this.middleName = middleName;
        this.salutation = salutation;
        this.suffix = suffix;
        this.isFemale = isFemale;
        this.isEmployed = isEmployed;
        this.isHomeOwner = isHomeOwner;
        this.address = address;
        this.cars = cars;
    }

    public String lastName () {
        return lastName;
    }

    public Person withLastName (String lastName) {
        return new Person (
            lastName, firstName, middleName, salutation, suffix, isFemale, isEmployed,
            isHomeOwner, address, cars);
    }

    public Person lastName (String lastName) {
        return new Person (
            lastName, firstName, middleName, salutation, suffix, isFemale, isEmployed,
            isHomeOwner, address, cars);
    }

    public String firstName () {
        return firstName;
    }

    public Person withFirstName (String firstName) {
        return new Person (
            lastName, firstName, middleName, salutation, suffix, isFemale, isEmployed,
            isHomeOwner, address, cars);
    }

    public Person firstName (String firstName) {
        return new Person (
            lastName, firstName, middleName, salutation, suffix, isFemale, isEmployed,
            isHomeOwner, address, cars);
    }

    public String middleName () {
        return middleName;
    }

    public Person withMiddleName (String middleName) {
        return new Person (
            lastName, firstName, middleName, salutation, suffix, isFemale, isEmployed,
            isHomeOwner, address, cars);
    }

    public Person middleName (String middleName) {
        return new Person (
            lastName, firstName, middleName, salutation, suffix, isFemale, isEmployed,
            isHomeOwner, address, cars);
    }

    public String salutation () {
        return salutation;
    }

    public Person withSalutation (String salutation) {
        return new Person (
            lastName, firstName, middleName, salutation, suffix, isFemale, isEmployed,
            isHomeOwner, address, cars);
    }

    public Person salutation (String salutation) {
        return new Person (
            lastName, firstName, middleName, salutation, suffix, isFemale, isEmployed,
            isHomeOwner, address, cars);
    }

    public Address address () {
        return address;
    }

    public Person withAddress (Address address) {
        return new Person (
            lastName, firstName, middleName, salutation, suffix, isFemale, isEmployed,
            isHomeOwner, address, cars);
    }

    public Person address (Address address) {
        return new Person (
            lastName, firstName, middleName, salutation, suffix, isFemale, isEmployed,
            isHomeOwner, address, cars);
    }

    public Person cars (List<Car> cars) {
        return new Person (
            lastName, firstName, middleName, salutation, suffix, isFemale, isEmployed,
            isHomeOwner, address, cars);
    }

    public Person cars (Car... cars) {
        return new Person (
            lastName, firstName, middleName, salutation, suffix, isFemale, isEmployed,
            isHomeOwner, address, asList (cars));
    }

    @Override public boolean equals (Object o) {
        if (this == o)
            return true;
        if (o == null || getClass () != o.getClass ())
            return false;

        Person that = (Person)o;
        return
            equal (this.lastName, that.lastName) &&
            equal (this.firstName, that.firstName) &&
            equal (this.middleName, that.middleName) &&
            equal (this.salutation, that.salutation) &&
            equal (this.suffix, that.suffix) &&
            equal (this.isFemale, that.isFemale) &&
            equal (this.isEmployed, that.isEmployed) &&
            equal (this.isHomeOwner, that.isHomeOwner) &&
            equal (this.address, that.address) &&
            equal (this.cars, that.cars);
    }

    @Override public int hashCode () {
        return hash (
            lastName, firstName, middleName, salutation, suffix, isFemale, isEmployed,
            isHomeOwner, address, cars);
    }

    @Override public String toString () {
        return
            print (this) +
            print ("lastName", lastName) +
            print ("firstName", firstName) +
            print ("middleName", middleName) +
            print ("salutation", salutation) +
            print ("suffix", suffix) +
            print ("isFemale", isFemale) +
            print ("isEmployed", isEmployed) +
            print ("isHomeOwner", isHomeOwner) +
            print ("address", address) +
            print ("cars", cars);
    }
}

public class InmutableExample {
    public static void main (String[] args) {
        out.println (stringOf (null));
        out.println (person ().withFirstName ("Pepe").withLastName ("Sanchez"));
        out.println (namedPerson ("Juan José", "Aguililla", "Martinez"));
        out.println (person ()
            .withFirstName ("Juanjo")
            .withAddress (address ()
                    .withStreetAddress ("C/Dolores Folgueras")
                    .withCity ("Madrid")
            )
        );
        out.println (person (p -> {
            p = p.withFirstName ("Juanjo");

            return p.withAddress (address ()
                    .withStreetAddress ("C/Dolores Folgueras")
                    .withCity ("Madrid")
            );
        }));
        out.println (person ()
            .firstName ("Juanjo")
            .address (address ()
                    .streetAddress ("C/Dolores Folgueras")
                    .city ("Madrid")
            )
        );
        out.println (person ().
            firstName ("Juanjo").
            address (address ().
                    streetAddress ("C/Dolores Folgueras").
                    city ("Madrid")
            )
        );

        out.println (person ().
                firstName ("Juanjo").
                address (address ().
                        streetAddress ("C/Dolores Folgueras").
                        city ("Madrid")
                ).
                cars (
                    car ("Seat", "Ibiza"),
                    car ("Porsche", "911")
                )
        );

        Person p = person ().
            firstName ("Juanjo").
            address (address ().
                streetAddress ("C/Dolores Folgueras").
                city ("Madrid").
                state ("Madrid")
            );

        out.println (p.withFirstName (p.firstName () + " Jr."));
        out.println (p.firstName (p.firstName + " Jr."));
    }
}
