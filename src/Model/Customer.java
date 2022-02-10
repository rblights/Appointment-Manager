package Model;

public class Customer {

    private int customer_ID;
    private String customer_Name;
    private String address;
    private String postal_Code;
    private String phone;
    private Division division_ID;
    private Country country;

    public Customer(int customer_ID, String customer_Name, String address, String postal_Code, String phone, Division division_ID, Country country) {
        setCustomer_ID(customer_ID);
        setCustomer_Name(customer_Name);
        setAddress(address);
        setPostal_Code(postal_Code);
        setPhone(phone);
        setDivision_ID(division_ID);
        setCountry(country);
    }

    public int getCustomer_ID() {
        return customer_ID;
    }

    public void setCustomer_ID(int customer_ID) {
        this.customer_ID = customer_ID;
    }

    public String getCustomer_Name() {
        return customer_Name;
    }

    public void setCustomer_Name(String customer_Name) {
        this.customer_Name = customer_Name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostal_Code() {
        return postal_Code;
    }

    public void setPostal_Code(String postal_Code) {
        this.postal_Code = postal_Code;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Division getDivision_ID() {
        return division_ID;
    }

    public void setDivision_ID(Division division_ID) {
        this.division_ID = division_ID;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public String toString() {
        return String.format("%s lives at %s, in the postal area %s, with phone number %S", customer_Name, address, postal_Code, phone);
    }
}

