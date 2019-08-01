package hsenasilva.com.github.sample.cqrs.web

import hsenasilva.com.github.sample.cqrs.domain.ListSampleCommand
import hsenasilva.com.github.sample.cqrs.domain.ListSampleParameter
import hsenasilva.com.github.sample.cqrs.domain.SampleParameter
import org.axonframework.commandhandling.gateway.CommandGateway
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import java.util.concurrent.CompletableFuture
import javax.validation.Valid
import javax.validation.constraints.NotNull

/**
 * @author hsena
 */
@RestController
@RequestMapping(value = ["/api/commands"])
class CommandController(private val commandGateway: CommandGateway) {

    @PostMapping(value = ["/samples"])
    @ResponseStatus(value = HttpStatus.CREATED)
    fun getPositions(@Valid @NotNull parameter: SampleParameter): CompletableFuture<Unit> =
            this.commandGateway.send(ListSampleCommand(listSampleParameter = ListSampleParameter(parameter.id, parameter.stuff)))

}