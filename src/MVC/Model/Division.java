package MVC.Model;

/** Division Class. */
public class Division {

    private int division_ID;
    private String divisionName;
    private int country_ID;

    /** Division constructor. */
    public Division(int division_ID, String divisionName, int country_ID) {
        setDivision_ID(division_ID);
        setDivisionName(divisionName);
        setCountry_ID(country_ID);
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

    /** divisionName getter.
     * @return divisionName*/
    public String getDivisionName() {
        return divisionName;
    }

    /** divisionName setter.
     * @param divisionName */
    public void setDivisionName(String divisionName) {
        this.divisionName = divisionName;
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

    public String toString() {
        return getDivisionName();
    }
}
