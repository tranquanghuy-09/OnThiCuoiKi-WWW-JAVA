Huy đi ôn thi cuối kì WWW


![Ảnh màn hình 2023-12-12 lúc 22 17 43](https://github.com/tranquanghuy-09/OnThiCuoiKi_WWW/assets/107989088/35b16608-d855-498c-a0d1-f6744092032e)

![Ảnh màn hình 2023-12-12 lúc 22 17 55](https://github.com/tranquanghuy-09/OnThiCuoiKi_WWW/assets/107989088/159b8950-817a-4e9b-8efc-6cabd9ce661e)

```
#spring.datasource.driver-class-name=org.mariadb.jdbc.Driver
#spring.datasource.url=jdbc:mariadb://localhost:3306/thicuoikiwww?createDatabaseIfNotExist=true
#spring.datasource.username=root
#spring.datasource.password=sapassword
#
#spring.jpa.hibernate.ddl-auto=update
#spring.jpa.show-sql=true
#
#
spring.main.allow-circular-references=true
spring.security.user.name=a
spring.security.user.password=a

spring.datasource.password=a
spring.datasource.username=a
spring.datasource.url=jdbc:h2:mem:cuoiki
spring.datasource.driver-class-name=org.h2.Driver
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect

spring.jpa.hibernate.ddl-auto=create-drop
spring.jpa.show-sql=true
spring.h2.console.enabled=true



#spring.jpa.hibernate.ddl-auto=create-drop
#spring.jpa.show-sql=true
#spring.h2.console.enabled=true
#spring.datasource.url=jdbc:h2:mem:test
#spring.datasource.driverClassName=org.h2.Driver
#spring.datasource.username=root
#spring.datasource.password=root
#spring.jpa.database-platform=org.hibernate.dialect.H2Dialect


```


```
    implementation 'org.springframework.boot:spring-boot-starter-data-jpa'
    implementation 'org.springframework.boot:spring-boot-starter-validation'
    implementation 'org.springframework.boot:spring-boot-starter-security'
    implementation 'org.springframework.boot:spring-boot-starter-thymeleaf'
    implementation 'org.springframework.boot:spring-boot-starter-web'
    implementation 'org.springframework.session:spring-session-core'
    implementation 'org.thymeleaf.extras:thymeleaf-extras-springsecurity6'
    compileOnly 'org.projectlombok:lombok'
    developmentOnly 'org.springframework.boot:spring-boot-devtools'
    runtimeOnly 'com.h2database:h2'
    runtimeOnly 'org.mariadb.jdbc:mariadb-java-client'
    annotationProcessor 'org.projectlombok:lombok'
    testImplementation 'org.springframework.boot:spring-boot-starter-test'
    testImplementation 'org.springframework.security:spring-security-test'
```


```
package vn.edu.iuh.fit.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
    @Autowired
    public void globalConfig(AuthenticationManagerBuilder auth, PasswordEncoder encoder, DataSource dataSource)throws Exception{
//        auth.inMemoryAuthentication()
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .withDefaultSchema()
                .withUser(User.withUsername("admin")
                        .password(encoder.encode("admin"))
                        .roles("ADMIN")
                        .build())
                .withUser(User.withUsername("huy")
                        .password(encoder.encode("huy"))
                        .roles("USER")
                        .build())
        ;
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(auth->auth
                .requestMatchers(("/products/delete/**")).hasRole("ADMIN")//uri bat dau ban /admin thi phai dang nhap voi quyen admin
                .anyRequest().permitAll()
        );
        http.csrf(csrf -> csrf.ignoringRequestMatchers("/h2-console/**"))
                .headers(h->h.frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin));
        http.csrf(AbstractHttpConfigurer::disable);
        http.httpBasic(Customizer.withDefaults());//cac thiet lap con lai thi theo mac dinh
        return http.build();
    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}

```

```
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

                    LocalDateTime randomInputTime = LocalDateTime.now().minusDays(random.nextInt(100) + 1);

                    Product product = new Product("product " + i, randomPrice, randomStatus, null, randomInputTime);
                    product.setProductType(productTypeRepository.findById(random.nextLong(1, 11)).get());

                    productRepository.save(product);
                }


            }
        };
    }
```
