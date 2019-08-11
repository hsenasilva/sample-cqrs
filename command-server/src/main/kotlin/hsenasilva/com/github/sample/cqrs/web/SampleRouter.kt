package hsenasilva.com.github.sample.cqrs.web

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.config.EnableWebFlux
import org.springframework.web.reactive.config.WebFluxConfigurer
import org.springframework.web.reactive.function.server.HandlerFunction
import org.springframework.web.reactive.function.server.RequestPredicates.POST
import org.springframework.web.reactive.function.server.RouterFunction
import org.springframework.web.reactive.function.server.RouterFunctions.route
import org.springframework.web.reactive.function.server.ServerResponse


/**
 * @author hsena
 */
@EnableWebFlux
@Configuration
class SampleRouter : WebFluxConfigurer {

    @Bean
    fun routes(handler: SampleCommand): RouterFunction<ServerResponse> {
        return route(POST("/api/commands/samples"), HandlerFunction(handler::loadPositions))
    }

}
