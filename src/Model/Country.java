package Model;

public class Country {

    private int country_ID;
    private String countryName;

    public Country(int country_ID, String countryName) {
        setCountry_ID(country_ID);
        setCountryName(countryName);
    }

    public int getCountry_ID() {
        return country_ID;
    }

    public void setCountry_ID(int country_ID) {
        this.country_ID = country_ID;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String toString(Country country) {
        return country.getCountryName();
    }
}
