package hsenasilva.com.github.sample.cqrs.configuration

import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.config.CorsRegistry
import org.springframework.web.reactive.config.WebFluxConfigurer


/**
 * @author hsena
 */
@Configuration
class CorsConfiguration : WebFluxConfigurer {

    override fun addCorsMappings(corsRegistry: CorsRegistry) {
        corsRegistry
                .addMapping("/**")
                .exposedHeaders("X-Requested-With, Authorization, Origin, Content-Type")
                .allowedHeaders("X-Requested-With, Authorization, Origin, Content-Type, Version")
                .allowedOrigins("*")
                .allowCredentials(true)
                .maxAge(3600)
    }
}