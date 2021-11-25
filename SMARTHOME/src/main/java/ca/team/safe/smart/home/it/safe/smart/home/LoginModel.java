package ca.team.safe.smart.home.it.safe.smart.home;

public class LoginModel {
    String secureID;

    public LoginModel(String secureID) {
        this.secureID = secureID;
    }

    public String getSecureID() {
        return secureID;
    }

    public void setSecureID(String secureID) {
        this.secureID = secureID;
    }
}
