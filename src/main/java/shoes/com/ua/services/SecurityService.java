package shoes.com.ua.services;

public interface SecurityService {

    String findLoggedInUsername();

    void autologin(String username, String password);

}
