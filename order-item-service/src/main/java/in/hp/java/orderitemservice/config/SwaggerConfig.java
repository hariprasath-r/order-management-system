package in.hp.java.orderitemservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Swagger Configuration for Order Item Service
 */
@Configuration
public class SwaggerConfig {

    private static final Contact DEFAULT_CONTACT = new Contact(
            "Hariprasath",
            "https://www.github.com/hariprasath-r",
            "");

    private static final ApiInfo DEFAULT_API_INFO = new ApiInfo(
            "Order Item Service",
            "Manages Creation and Retrieval of Order Items based on Order ID.",
            "1.0",
            "urn:tos",
            DEFAULT_CONTACT,
            "GNU General Public License v3.0",
            "https://www.gnu.org/licenses/gpl-3.0.en.html",
            new ArrayList<>());

    private static final Set<String> DEFAULT_PRODUCES_CONSUMES =
            new HashSet<>(Arrays.asList("application/json", "application.xml"));

    @Bean
    public Docket configureDocket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("order-item-service")
                .select()
                .apis(RequestHandlerSelectors.basePackage("in.hp.java"))
                .build()
                .consumes(DEFAULT_PRODUCES_CONSUMES)
                .produces(DEFAULT_PRODUCES_CONSUMES)
                .apiInfo(DEFAULT_API_INFO);
    }
}
