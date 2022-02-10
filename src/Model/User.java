package Model;

public class User {
    private int user_ID;
    private String user_Name;
    private String password;

    public User(int user_ID, String user_Name, String password) {
        setUser_ID(user_ID);
        setUser_Name(user_Name);
        setPassword(password);
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
        return user.getUser_Name();
    }
}
