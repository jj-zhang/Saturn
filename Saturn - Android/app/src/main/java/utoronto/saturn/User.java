package utoronto.saturn;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import utoronto.saturn.Event;

public class User {

    private static int currentID = 0;
    private String email;
    private String lastName;
    private String firstName;
    private List<User> followedCreatorList;
    private List<Event> events;
    private Set<Event> localEvents;
    private Set<Event> globalEvents;
    private Set<Event> interestedEvents;
    private Set<Event> attendingEvents;

    public User(String firstName, String lastName, String email) {

        // Zero Lengths
        if (firstName.length() == 0 || lastName.length() == 0 || email.length() == 0) {
            throw new IllegalArgumentException("You passed an Argument of length 0!");
        }
        // No @ in email
        if (!email.contains("@")){
            throw new IllegalArgumentException("Emails must contain @!");
        }

        this.firstName = firstName;
        this.lastName = lastName;
        // ID
        this.email = email;
        this.followedCreatorList = new ArrayList<User>();
        this.events = new ArrayList<Event>();
    }

    private void addToFollowedCreators(User creator) {
        this.followedCreatorList.add(creator);
    }

    public List<User> getFollowedCreatorList() {
        return this.followedCreatorList;
    }

    public void followCreator(User creator) {
        this.addToFollowedCreators(creator);
    }

    public List<Event> getAllEvents() {
        return this.events;
    }

    // Returns name in format first:last
    public String getName() {
        return this.firstName + ":" + this.lastName;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }
}
