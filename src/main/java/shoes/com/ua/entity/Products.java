package shoes.com.ua.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
public class Products {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String brand;
    private double price;
    private String type;
    private String sex;
    private double size;
    private String width;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "products_id",
            joinColumns = @JoinColumn(name = "products_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"))
    private Set<User> users;

    public Products() {
    }


    public Products(String brand, double price, String type, String sex, double size, String width, Set<User> users) {
        this.brand = brand;
        this.price  = price;
        this.type = type;
        this.sex = sex;
        this.size = size;
        this.width = width;
        this.users = users;
    }
    @Override
    public String toString() {
        String result = String.format(
                "Products [id=%d, brand='%s']%n",
                id, brand);
        if (users != null) {
            for(User user : users) {
                result += String.format(
                        "User[id=%d, userName='%s']%n",
                        user.getId(), user.getUsername());
            }
        }

        return result;
    }
}
