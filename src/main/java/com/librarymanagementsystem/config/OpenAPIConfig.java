package com.librarymanagementsystem.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;

import java.util.List;

/**
 * Configuration class for setting up OpenAPI (Swagger) documentation.
 * <p>
 * This class configures the OpenAPI specification for the Library Management System API.
 * It defines the API metadata and the server URLs for different environments (development and production).
 * </p>
 * <p>
 * The URLs for the development and production environments are injected via the
 * {@code @Value} annotation from the application's configuration properties.
 * </p>
 * <p>
 * The OpenAPI bean configured here provides a description of the API, including its
 * title, version, and description, which will be used by Swagger UI to generate interactive API documentation.
 * </p>
 * <p>
 * Example property configuration in {@code application.properties}:
 * <pre>
 * library-system.openapi.dev-url=http://localhost:8080
 * library-system.openapi.prod-url=https://api.example.com
 * </pre>
 * </p>
 *
 * <pre>
 * Usage:
 * {@code
 * // Accessing the OpenAPI documentation in different environments:
 * http://localhost:8080/swagger-ui.html (Development)
 * https://api.example.com/swagger-ui.html (Production)
 * }
 * </pre>
 * </p>
 *
 * Author: Gajendrasinh Zala
 * Email: gajendrasinh.zala93@gmail.com
 * Github: https://github.com/Gajendrasinh
 */
@Configuration
public class OpenAPIConfig {

    /**
     * The URL for the development environment.
     * <p>
     * Injected from the application properties using the key {@code library-system.openapi.dev-url}.
     * </p>
     */
    @Value("${library-system.openapi.dev-url}")
    private String devUrl;

    /**
     * The URL for the production environment.
     * <p>
     * Injected from the application properties using the key {@code library-system.openapi.prod-url}.
     * </p>
     */
    @Value("${library-system.openapi.prod-url}")
    private String prodUrl;

    /**
     * Configures the OpenAPI bean with API metadata and server URLs.
     * <p>
     * This method creates and returns an {@link OpenAPI} object that contains
     * the API's information (title, version, description) and the URLs for
     * the development and production servers.
     * </p>
     *
     * @return an {@link OpenAPI} object with the configured API information and server URLs.
     */
    @Bean
    public OpenAPI myOpenAPI() {
        // Configuring the development server URL
        Server devServer = new Server();
        devServer.setUrl(devUrl);
        devServer.setDescription("Server URL in Development environment");

        // Configuring the production server URL
        Server prodServer = new Server();
        prodServer.setUrl(prodUrl);
        prodServer.setDescription("Server URL in Production environment");

        // Configuring API metadata
        Info info = new Info()
                .title("Library Management System API")
                .version("1.0")
                .description("This API exposes endpoints to manage the Library Management System.");

        // Returning the OpenAPI configuration with the servers and API information
        return new OpenAPI()
                .info(info)
                .servers(List.of(devServer, prodServer));
    }
}
