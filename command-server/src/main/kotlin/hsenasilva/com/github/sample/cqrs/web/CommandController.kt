package hsenasilva.com.github.sample.cqrs.web

import hsenasilva.com.github.sample.cqrs.domain.ListSampleCommand
import hsenasilva.com.github.sample.cqrs.web.parameters.ListSampleParameter
import hsenasilva.com.github.sample.cqrs.web.parameters.SampleParameter
import org.axonframework.commandhandling.callbacks.LoggingCallback
import org.axonframework.commandhandling.gateway.CommandGateway
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import javax.validation.Valid
import javax.validation.constraints.NotNull

/**
 * @author hsena
 */
@RestController
@RequestMapping(value = ["/samples"])
class CommandController(private val commandGateway: CommandGateway) {

    @PostMapping
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    fun getSamples(@RequestBody @Valid @NotNull parameter: SampleParameter) =
            this.commandGateway.send(ListSampleCommand(listSampleParameter = ListSampleParameter(parameter.id, parameter.stuff)), LoggingCallback.INSTANCE)

}