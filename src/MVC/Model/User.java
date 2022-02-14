package MVC.Model;

public class User {
    private static User user;
    private int user_ID;
    private String user_Name;
    private String password;

    public User(int user_ID, String user_Name, String password) {
        setUser_ID(user_ID);
        setUser_Name(user_Name);
        setPassword(password);
    }

    public static User getUser() {
        return user;
    }

    public static void setUser(User user) {
        User.user = user;
    }

    public int getUser_ID() {
        return user_ID;
    }

    public void setUser_ID(int user_ID) {
        this.user_ID = user_ID;
    }

    public String getUser_Name() {
        return user_Name;
    }

    public void setUser_Name(String user_Name) {
        this.user_Name = user_Name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public String toString(User user) {
        return getUser_Name();
    }
}
