package slng.fnord;
//
import android.accounts.Account;

import java.util.ArrayList;
import java.util.HashMap;
//
//The accounts class will keep sets of arraylists for emails, usernames and passwords for each account type (admin, homeowner, service provider)

public class Accounts {
    //admin
    private boolean adminExists = false;

    private HashMap<String, User> userData = new HashMap<>();

    public Accounts() {
    }
    public Accounts getAcc(){
        return this;
    }

    //public methods to add homeowner account
    public void makeUser(String email, String username, String password, UserTypes type) {
        if (type.equals(UserTypes.ADMIN)) {
            adminExists = true;
        }
        String id = Common.makeMD5(email);
        User user = new User(email, username, password, type);
        userData.put(id, user);
    }

    //getters
    public boolean existsAdmin() {
        return adminExists;
    }

    public boolean existsAccount(String email) {
        String id = Common.makeMD5(email);

        return userData.containsKey(id);
    }

    public User getUser(String email) {
        String id = Common.makeMD5(email);

        return userData.get(id);
    }
}