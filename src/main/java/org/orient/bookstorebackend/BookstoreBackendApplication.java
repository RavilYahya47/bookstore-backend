package org.orient.bookstorebackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
public class BookstoreBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(BookstoreBackendApplication.class, args);

        /*

        Book
        Category
        Author

        User
        UserRole  -- ADMIN, USER

        PaymentOrder
        PaymentOrderStatus - CREATED, IN_PROGRESS, DECLINED, ERROR, SUCCESS


         */
    }

}
