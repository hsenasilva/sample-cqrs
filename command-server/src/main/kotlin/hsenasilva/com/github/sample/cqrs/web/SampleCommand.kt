package hsenasilva.com.github.sample.cqrs.web

import hsenasilva.com.github.sample.cqrs.domain.ListSampleCommand
import hsenasilva.com.github.sample.cqrs.domain.ListSampleParameter
import hsenasilva.com.github.sample.cqrs.domain.SampleParameter
import org.axonframework.commandhandling.gateway.CommandGateway
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Mono
import reactor.core.publisher.toMono

/**
 * @author hsena
 */

//fun <T> CommandGateway.accept(command: T) = this.send<T>(command)

@Component
class SampleCommand(private val commandGateway: CommandGateway) {

    fun loadPositions(request: ServerRequest): Mono<ServerResponse> {
        val parameter = request.bodyToMono(SampleParameter::class.java).toProcessor().peek()
        when {
            parameter != null -> {
                val command = this.commandGateway.send<ListSampleCommand>(
                        ListSampleCommand(listSampleParameter = ListSampleParameter(parameter.id, parameter.stuff))
                ).toMono()
                return ServerResponse.accepted().body(
                        command, ListSampleCommand::class.java
                )
            }
            else -> return ServerResponse.badRequest().build()
        }
    }
}

