package vn.edu.iuh.fit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import vn.edu.iuh.fit.backend.enums.ProductStatus;
import vn.edu.iuh.fit.backend.models.Product;
import vn.edu.iuh.fit.backend.models.ProductType;
import vn.edu.iuh.fit.backend.repositories.ProductRepository;
import vn.edu.iuh.fit.backend.repositories.ProductTypeRepository;

import java.time.LocalDateTime;
import java.util.Random;

@SpringBootApplication
public class Stt20092731TranQuangHuyApplication {
    @Autowired
    private ProductTypeRepository productTypeRepository;
    @Autowired
    private ProductRepository productRepository;

    public static void main(String[] args) {
        SpringApplication.run(Stt20092731TranQuangHuyApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(){
        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
                for (int i=1; i<=10; i++){
                    productTypeRepository.save(new ProductType("product tpye "+i));
                }

                Random random = new Random();
                for (int i = 1; i <= 100; i++) {
                    float randomPrice = 100 + random.nextFloat() * (1000 - 100);

                    int randomStatusIndex = random.nextInt(ProductStatus.values().length);
                    ProductStatus randomStatus = ProductStatus.values()[randomStatusIndex];

                    LocalDateTime randomInputTime = LocalDateTime.now().minusDays(random.nextInt(30) + 1);

                    Product product = new Product("product " + i, randomPrice, randomStatus, null, randomInputTime);
                    product.setProductType(productTypeRepository.findById(random.nextLong(1, 11)).get());

                    productRepository.save(product);
                }


            }
        };
    }
}
