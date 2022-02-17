package MVC.Model;

public class Division {

    private int division_ID;
    private String divisionName;
    private int country_ID;

    public Division(int division_ID, String divisionName, int country_ID) {
        setDivision_ID(division_ID);
        setDivisionName(divisionName);
        setCountry_ID(country_ID);
    }

    public int getDivision_ID() {
        return division_ID;
    }

    public void setDivision_ID(int division_ID) {
        this.division_ID = division_ID;
    }

    public String getDivisionName() {
        return divisionName;
    }

    public void setDivisionName(String divisionName) {
        this.divisionName = divisionName;
    }

    public int getCountry_ID() {
        return country_ID;
    }

    public void setCountry_ID(int country_ID) {
        this.country_ID = country_ID;
    }

    public String toString() {
        return getDivisionName();
    }
}
