package MVC.Model;

/** Country Class. */
public class Country {

    private int country_ID;
    private String countryName;

    /** Country constructor. */
    public Country(int country_ID, String countryName) {
        setCountry_ID(country_ID);
        setCountryName(countryName);
    }

    /** country_ID getter.
     * @return country_ID*/
    public int getCountry_ID() {
        return country_ID;
    }

    /** country_ID setter.
     * @param country_ID */
    public void setCountry_ID(int country_ID) {
        this.country_ID = country_ID;
    }

    /** countryName getter.
     * @return countryName*/
    public String getCountryName() {
        return countryName;
    }

    /** countryName setter.
     * @param countryName */
    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String toString() {
        return getCountryName();
    }
}
