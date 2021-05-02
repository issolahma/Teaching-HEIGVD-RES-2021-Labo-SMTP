/**
 * Author : Maude Issolah, Matthieu Godi
 * Date : 01.05.2021
 */
package ch.heigvd.app.model.mail;

import java.util.ArrayList;
import java.util.List;

/**
 * Group of person to prank.
 */
public class Group {
    private final List<Person> members = new ArrayList<>();

    public void addMember(Person person){
        members.add(person);
    }

    public List<Person> getMembers(){
        return new ArrayList<>(members);
    }
}
