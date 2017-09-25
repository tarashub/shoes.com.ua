package shoes.com.ua.services;


import shoes.com.ua.entity.User;


public interface MailSenderService {
    void send(User user);
}
