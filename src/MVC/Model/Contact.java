package MVC.Model;

/** Contact Class. */
public class Contact {

    private int contactID;
    private String contactName;
    private String contactEmail;

    /** Contact constructor. */
    public Contact(int contactID, String contactName, String contactEmail) {
        setContactID(contactID);
        setContactName(contactName);
        setContactEmail(contactEmail);
    }

    /** contactID getter.
     * @return contactID*/
    public int getContactID() {
        return contactID;
    }

    /** contactID setter.
     * @param contactID */
    public void setContactID(int contactID) {
        this.contactID = contactID;
    }

    /** contactName getter.
     * @return contactName*/
    public String getContactName() {
        return contactName;
    }

    /** contactName setter.
     * @param contactName */
    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    /** contactEmail getter.
     * @return contactEmail*/
    public String getContactEmail() {
        return contactEmail;
    }

    /** contactEmail setter.
     * @param contactEmail */
    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    /** Overide toString. */
    public String toString() {
        return String.format("%d: %s", contactID, contactName);
    }
}
