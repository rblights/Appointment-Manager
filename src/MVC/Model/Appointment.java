package MVC.Model;

import java.time.ZonedDateTime;

public class Appointment {

    private int appointment_ID;
    private String title;
    private String description;
    private String location;
    private String type;
    private String start;
    private String end;
    private int customer_ID;
    private int contact_ID;
    private int user_ID;

    public Appointment(int appointment_ID, String title, String description, String location, String type, String start,
                       String end, int customer_ID, int contact_ID, int user_ID) {

        setAppointment_ID(appointment_ID);
        setTitle(title);
        setDescription(description);
        setLocation(location);
        setType(type);
        setStart(start);
        setEnd(end);
        setCustomer_ID(customer_ID);
        setContact_ID(contact_ID);
        setUser_ID(user_ID);
    }

    public int getAppointment_ID() {
        return appointment_ID;
    }

    public void setAppointment_ID(int appointment_ID) {
        this.appointment_ID = appointment_ID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public int getCustomer_ID() {
        return customer_ID;
    }

    public void setCustomer_ID(int customer_ID) {
        this.customer_ID = customer_ID;
    }

    public int getContact_ID() {
        return contact_ID;
    }

    public void setContact_ID(int contact_ID) {
        this.contact_ID = contact_ID;
    }

    public int getUser_ID() {
        return user_ID;
    }

    public void setUser_ID(int user_ID) {
        this.user_ID = user_ID;
    }

    public String toString(Appointment appointment) {
        return String.format("");
    }
}
