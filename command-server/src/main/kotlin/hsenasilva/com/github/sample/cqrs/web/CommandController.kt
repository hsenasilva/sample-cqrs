package hsenasilva.com.github.sample.cqrs.web

import hsenasilva.com.github.sample.cqrs.core.domain.SampleId
import hsenasilva.com.github.sample.cqrs.domain.CreateSampleCommand
import hsenasilva.com.github.sample.cqrs.web.callback.CommandGatewayCallback
import hsenasilva.com.github.sample.cqrs.web.parameters.CreateSampleParameter
import hsenasilva.com.github.sample.cqrs.web.parameters.SampleParameter
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
class CommandController(private val commandGateway: CommandGateway,
                        private val commandGatewayCallback: CommandGatewayCallback) {

    @PutMapping
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    fun saveSample(@RequestBody @Valid @NotNull parameter: SampleParameter) =
            this.commandGateway.send(
                    CreateSampleCommand(
                            id = SampleId(parameter.id),
                            createSampleParameter =
                                CreateSampleParameter(
                                    SampleId(parameter.id),
                                    parameter.stuff,
                                    parameter.action
                                )
                    ), this.commandGatewayCallback
            )

}