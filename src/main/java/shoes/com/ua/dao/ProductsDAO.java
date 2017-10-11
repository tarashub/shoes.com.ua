package shoes.com.ua.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import shoes.com.ua.entity.Products;

public interface ProductsDAO extends JpaRepository<Products, Long> {
}
