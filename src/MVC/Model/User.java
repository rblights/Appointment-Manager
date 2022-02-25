package MVC.Model;

import java.time.ZoneId;

/** User Class. */
public class User {
    public static boolean firstLogin = true;
    private static String currentUser;
    private static int currentUserID;
    private static ZoneId currentUserZoneID;
    private int user_ID;
    private String user_Name;
    private String password;

    /** User constructor. */
    public User(int user_ID, String user_Name, String password) {
        setUser_ID(user_ID);
        setUser_Name(user_Name);
        setPassword(password);
    }

    /** firstLogin boolean getter.
     * @return firstLogin*/
    public static boolean isFirstLogin() {
        return firstLogin;
    }

    /** firstLogin boolean setter.
     * @param firstLogin */
    public static void setFirstLogin(boolean firstLogin) {
        User.firstLogin = firstLogin;
    }

    /** currentUser getter.
     * @return currentUser*/
    public static String getCurrentUser() {
        return currentUser;
    }

    /** currentUser setter.
     * @param currentUser */
    public static void setCurrentUser(String currentUser) {
        User.currentUser = currentUser;
    }

    /** currentUserID getter.
     * @return currentUserID*/
    public static int getCurrentUserID() {
        return currentUserID;
    }

    /** currentUserID setter.
     * @param currentUserID */
    public static void setCurrentUserID(int currentUserID) {
        User.currentUserID = currentUserID;
    }

    /** currentUserZoneID getter.
     * @return currentUserZoneID*/
    public static ZoneId getCurrentUserZoneID() {
        return currentUserZoneID;
    }

    /** currentUserZoneID setter.
     * @param currentUserZoneID */
    public static void setCurrentUserZoneID(ZoneId currentUserZoneID) {
        User.currentUserZoneID = currentUserZoneID;
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

    /** user_Name getter.
     * @return user_Name*/
    public String getUser_Name() {
        return user_Name;
    }

    /** user_Name setter.
     * @param user_Name */
    public void setUser_Name(String user_Name) {
        this.user_Name = user_Name;
    }

    /** password getter.
     * @return password*/
    public String getPassword() {
        return password;
    }

    /** password setter.
     * @param password */
    public void setPassword(String password) {
        this.password = password;
    }

    public String toString(User user) {
        return getUser_Name();
    }
}
