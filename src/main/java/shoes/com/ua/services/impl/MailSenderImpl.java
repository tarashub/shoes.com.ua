package shoes.com.ua.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import shoes.com.ua.entity.User;
import shoes.com.ua.services.MailSenderService;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class MailSenderImpl implements MailSenderService{
    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public void send(User user) {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        try {
            helper.setTo(user.getEmail());
            helper.setText("Welcome " + user.getUsername() + "to the site",true);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        javaMailSender.send(message);


    }
}
