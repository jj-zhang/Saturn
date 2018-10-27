package utoronto.saturn;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import utoronto.saturn.Event;

public class User {

    private String email;
    private String lastName;
    private String firstName;
    private List<User> followedCreatorList;
    private List<Event> events;
    private Set<Event> localEvents;
    private Set<Event> globalEvents;
    private Set<Event> interestedEvents;
    private Set<Event> attendingEvents;

    User(String firstName, String lastName, String email) {

        // Zero Lengths
        if (firstName.length() == 0 || lastName.length() == 0 || email.length() < 3) {
            throw new IllegalArgumentException("You passed an Argument of insufficient length!");
        }
        // No @ in email
        if (!email.contains("@")){
            throw new IllegalArgumentException("Emails must contain @!");
        }

        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.followedCreatorList = new ArrayList<User>();
        this.events = new ArrayList<Event>();
    }

    void addToFollowedCreators(User creator) {

        // Already Following Creator
        if (followedCreatorList.contains(creator)) {
            throw new IllegalStateException("Already following that creator!");
        }
        this.followedCreatorList.add(creator);
    }

    public List<User> getFollowedCreatorList() {
        return this.followedCreatorList;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(email, user.email) &&
                Objects.equals(lastName, user.lastName) &&
                Objects.equals(firstName, user.firstName) &&
                Objects.equals(followedCreatorList, user.followedCreatorList) &&
                Objects.equals(events, user.events) &&
                Objects.equals(localEvents, user.localEvents) &&
                Objects.equals(globalEvents, user.globalEvents) &&
                Objects.equals(interestedEvents, user.interestedEvents) &&
                Objects.equals(attendingEvents, user.attendingEvents);
    }

    @Override
    public int hashCode() {

        return Objects.hash(email, lastName, firstName, followedCreatorList, events, localEvents, globalEvents, interestedEvents, attendingEvents);
    }
}
