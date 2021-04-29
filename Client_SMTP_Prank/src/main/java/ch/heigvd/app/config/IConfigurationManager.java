package ch.heigvd.app.config;

import ch.heigvd.app.model.mail.Person;

import java.util.List;

public interface IConfigurationManager {
    public List<Person> getVictims();
    public List<String> getMessages();
    public int getNumberOfGroups();
    public List<Person> getWitnessesToCC();
}
