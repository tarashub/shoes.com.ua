package shoes.com.ua.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shoes.com.ua.dao.UserDAO;
import shoes.com.ua.entity.User;
import shoes.com.ua.services.*;

import java.util.List;

@Service
@Transactional
public class UserServiseImpl implements UserServise, UserDetailsService {

    @Autowired
    private UserDAO userDAO;
    @Autowired
    private PasswordEncoder passwordEncoder;


    public User findOne(int id) {

        return userDAO.findOne(id);
    }

    public List<User> findAll() {

        return userDAO.findAll();
    }

    public void delete(User user) {
        userDAO.delete(user);
    }

    public void delete(int id) {
        userDAO.delete(id);
    }

    public void save(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userDAO.save(user);
    }

    public User findByName(@Param("username") String username) {
        return userDAO.findByUserName(username);
    }

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return findByName(username);
    }

}
