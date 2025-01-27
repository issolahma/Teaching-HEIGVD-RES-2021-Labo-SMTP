/**
 * Author : Maude Issolah, Matthieu Godi
 * Date : 01.05.2021
 */
package ch.heigvd.app.config;

import ch.heigvd.app.model.mail.Person;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Class that manage configuration files, and extract data.
 */
public class ConfigurationManager implements IConfigurationManager {
    private String smtpServerAddress;
    private int smtpServerPort;
    private final List<Person> victims;
    private final List<String> messages;
    private int numberOfGroups;
    private List<Person> witnessesToCC;

    /**
     * Import config files
     * @throws IOException
     */
    public ConfigurationManager() throws IOException {
        victims = loadAddressesFromFile("./config/victims.utf8");
        messages = loadMessagesFromFile("./config/messages.utf8");
        loadProperties("./config/config.properties");
    }

    /**
     * Load info from config.properties
     * @param filename
     * @throws IOException
     */
    private void loadProperties(String filename) throws IOException {
        BufferedInputStream fis = new BufferedInputStream(new FileInputStream(filename));
        Properties properties = new Properties();
        properties.load(fis);

        if(properties.getProperty("smtpServerAddress") == null || properties.getProperty("smtpServerPort") == null
                || properties.getProperty("numberOfGroups") == null || properties.getProperty("witnessesToCC") == null)
            throw new IOException("Error : Config.properties File wrong format");

        this.smtpServerAddress = properties.getProperty("smtpServerAddress");
        this.smtpServerPort = Integer.parseInt(properties.getProperty("smtpServerPort"));
        this.numberOfGroups = Integer.parseInt(properties.getProperty("numberOfGroups"));

        this.witnessesToCC = new ArrayList<>();
        String witnesses = properties.getProperty("witnessesToCC");
        if(!witnesses.contains("@"))
            throw new IOException("Error : Config.properties witnessesToCC Wrong Format");
        String []witnessesAddresses = witnesses.split(",");
        for (String address : witnessesAddresses){
            this.witnessesToCC.add(new Person(address));
        }
    }

    /**
     * Load list of victims email from victims.utf8
     * @param filename
     * @return List of Person
     * @throws IOException
     */
    private List<Person> loadAddressesFromFile(String filename) throws IOException {
        List<Person> result;
        try (BufferedInputStream fis = new BufferedInputStream(new FileInputStream(filename));){
            InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
            try (BufferedReader reader = new BufferedReader(isr)){
                result = new ArrayList<>();
                String address = reader.readLine();
                while (address != null){
                    if(!address.contains("@"))
                        throw new IOException("Error : victim config file wrong format");
                    result.add(new Person(address));
                    address = reader.readLine();
                }
            }
        }
        return result;
    }

    /**
     * Load prank messages from messages.utf8
     * @param filename
     * @return List of messages
     * @throws IOException
     */
    private List<String> loadMessagesFromFile(String filename) throws IOException {
        List<String> result;
        try (BufferedInputStream fis = new BufferedInputStream(new FileInputStream(filename));){
            InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
            try (BufferedReader reader = new BufferedReader(isr)){
                result = new ArrayList<>();
                String line = reader.readLine();
                while (line != null){
                    StringBuilder body = new StringBuilder();
                    while ((line != null) && (!line.equals("=="))){
                        body.append(line);
                        body.append("\r\n");
                        line = reader.readLine();
                    }
                    result.add(body.toString());
                    line = reader.readLine();
                }
            }
        }
        return result;
    }

    @Override
    public List<Person> getVictims(){
        return victims;
    }

    @Override
    public List<String> getMessages(){
        return messages;
    }

    @Override
    public int getNumberOfGroups(){
        return numberOfGroups;
    }

    @Override
    public List<Person> getWitnessesToCC(){
        return witnessesToCC;
    }

    public String getSmtpServerAddress() {
        return smtpServerAddress;
    }

    public int getSmtpServerPort() {
        return smtpServerPort;
    }
}
