package in.karthik.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.karthik.entity.Product;
@Repository
public interface ProductRepo extends JpaRepository<Product, Integer>{

}
