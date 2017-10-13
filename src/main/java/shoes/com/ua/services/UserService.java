package shoes.com.ua.services;




import shoes.com.ua.entity.User;


import java.util.List;

public interface UserService {
    void save(User user);

    User findOne(int id);

    List<User> findAll();

    void delete(User user);

    void delete(int id);

    User findByName(String username);

    long count();


}
