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
