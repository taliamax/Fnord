package com.seg2105.fnord;

public class Homeowner extends User {
    private String address;

    public Homeowner () {
        // Empty constructor for database
    }

    public Homeowner(String username, String passwordHash, String email,
                     String firstName, String lastName, String address) {
        super(username, email, firstName, lastName);
    }

    public Homeowner(String username, String email) {
        super(username, email, AccountType.HOMEOWNER);
    }
}
