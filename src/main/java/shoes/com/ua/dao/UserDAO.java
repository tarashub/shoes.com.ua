package shoes.com.ua.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import shoes.com.ua.entity.User;



public interface UserDAO extends JpaRepository<User,Integer> {
    @Query("from User u where u.username=:username")
    public User findByUserName(@Param("username") String username);

}