package MVC.Model;

import javafx.beans.value.ObservableValue;

/** Customer Class. */
public class Customer {

    private int customer_ID;
    private String customer_Name;
    private String address;
    private String postal_Code;
    private String phone;
    private int division_ID;

    /** Customer constructor. */
    public Customer(int customer_ID, String customer_Name, String address, String postal_Code, String phone, int division_ID) {
        setCustomer_ID(customer_ID);
        setCustomer_Name(customer_Name);
        setAddress(address);
        setPostal_Code(postal_Code);
        setPhone(phone);
        setDivision_ID(division_ID);
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

    /** customer_Name getter.
     * @return customer_Name*/
    public String getCustomer_Name() {
        return customer_Name;
    }

    /** customer_Name setter.
     * @param customer_Name */
    public void setCustomer_Name(String customer_Name) {
        this.customer_Name = customer_Name;
    }

    /** address getter.
     * @return address*/
    public String getAddress() {
        return address;
    }

    /** address setter.
     * @param address */
    public void setAddress(String address) {
        this.address = address;
    }

    /** postal_Code getter.
     * @return postal_Code*/
    public String getPostal_Code() {
        return postal_Code;
    }

    /** postal_Code setter.
     * @param postal_Code */
    public void setPostal_Code(String postal_Code) {
        this.postal_Code = postal_Code;
    }

    /** phone getter.
     * @return phone*/
    public String getPhone() {
        return phone;
    }

    /** phone setter.
     * @param phone */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /** division_ID getter.
     * @return division_ID*/
    public int getDivision_ID() {
        return division_ID;
    }

    /** division_ID setter.
     * @param division_ID */
    public void setDivision_ID(int division_ID) {
        this.division_ID = division_ID;
    }

    public String toString() {
        return String.format("%d", customer_ID);
    }
}

