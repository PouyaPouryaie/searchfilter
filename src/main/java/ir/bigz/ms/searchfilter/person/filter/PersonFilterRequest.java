package ir.bigz.ms.searchfilter.person.filter;

public class PersonFilterRequest {

    private String firstName;
    private String lastName;

    public PersonFilterRequest() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
