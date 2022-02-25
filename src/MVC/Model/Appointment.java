package MVC.Model;

/** Appointment Class*/
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
    private String contact_Name;
    private int user_ID;

    /** Appointment Constructor.
     * @param title
     * @param appointment_ID
     * @param contact_ID
     * @param contact_Name
     * @param customer_ID
     * @param description
     * @param end
     * @param location
     * @param start
     * @param type
     * @param user_ID */
    public Appointment(int appointment_ID, String title, String description, String location, String type, String start,
                       String end, int customer_ID, int contact_ID, String contact_Name, int user_ID) {

        setAppointment_ID(appointment_ID);
        setTitle(title);
        setDescription(description);
        setLocation(location);
        setType(type);
        setStart(start);
        setEnd(end);
        setCustomer_ID(customer_ID);
        setContact_ID(contact_ID);
        setContact_Name(contact_Name);
        setUser_ID(user_ID);
    }

    /** appointment_ID getter.
     * @return appointment_ID*/
    public int getAppointment_ID() {
        return appointment_ID;
    }

    /** appointment_ID setter.
     * @param appointment_ID */
    public void setAppointment_ID(int appointment_ID) {
        this.appointment_ID = appointment_ID;
    }

    /** title getter.
     * @return title*/
    public String getTitle() {
        return title;
    }

    /** title setter.
     * @param title */
    public void setTitle(String title) {
        this.title = title;
    }

    /** description getter.
     * @return description*/
    public String getDescription() {
        return description;
    }

    /** description setter.
     * @param description */
    public void setDescription(String description) {
        this.description = description;
    }

    /** location getter.
     * @return location*/
    public String getLocation() {
        return location;
    }

    /** location setter.
     * @param location */
    public void setLocation(String location) {
        this.location = location;
    }

    /** type getter.
     * @return type*/
    public String getType() {
        return type;
    }

    /** type setter.
     * @param type */
    public void setType(String type) {
        this.type = type;
    }

    /** start getter.
     * @return start*/
    public String getStart() {
        return start;
    }

    /** start setter.
     * @param start */
    public void setStart(String start) {
        this.start = start;
    }

    /** end getter.
     * @return end*/
    public String getEnd() {
        return end;
    }

    /** end setter.
     * @param end */
    public void setEnd(String end) {
        this.end = end;
    }

    /** customer_ID getter.
     * @return customer_ID*/
    public int getCustomer_ID() {
        return customer_ID;
    }

    /** customer_ID setter.
     * @param customer_ID */
    public void setCustomer_ID(int customer_ID) {
        this.customer_ID = customer_ID;
    }

    /** contact_ID getter.
     * @return contact_ID*/
    public int getContact_ID() {
        return contact_ID;
    }

    /** contact_ID setter.
     * @param contact_ID */
    public void setContact_ID(int contact_ID) {
        this.contact_ID = contact_ID;
    }

    /** contact_Name getter.
     * @return contact_Name*/
    public String getContact_Name() {
        return contact_Name;
    }

    /** contact_Name setter.
     * @param contact_Name */
    public void setContact_Name(String contact_Name) {
        this.contact_Name = contact_Name;
    }

    /** user_ID getter.
     * @return user_ID*/
    public int getUser_ID() {
        return user_ID;
    }

    /** user_ID setter.
     * @param user_ID */
    public void setUser_ID(int user_ID) {
        this.user_ID = user_ID;
    }


    public String toString(Appointment appointment) {
        return String.format("");
    }
}
