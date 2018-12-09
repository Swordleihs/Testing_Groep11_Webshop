package domain.model.sorters;

import domain.model.Person;

import java.util.Comparator;
import java.util.List;

public class PersonSorter {

    public PersonSorter(){}

    public List<Person> sortByEmail(List<Person> p){
        List<Person> persons = p;
        persons.sort(Comparator.comparing(Person::getEmail));
        return persons;
    }

    public List<Person> sortByFirstName(List<Person> p){
        List<Person> persons = p;
        persons.sort(Comparator.comparing(Person::getFirstName));
        return persons;
    }

    public List<Person> sortByLastName(List<Person> p){
        List<Person> persons = p;
        persons.sort(Comparator.comparing(Person::getLastName));
        return persons;
    }
}
