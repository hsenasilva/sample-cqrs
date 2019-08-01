package hsenasilva.com.github.sample.cqrs.web

import hsenasilva.com.github.sample.cqrs.domain.ListSampleCommand
import hsenasilva.com.github.sample.cqrs.domain.ListSampleParameter
import org.axonframework.commandhandling.gateway.CommandGateway
import org.springframework.http.HttpStatus
import org.springframework.scheduling.annotation.Async
import org.springframework.web.bind.annotation.*
import java.util.concurrent.CompletableFuture
import javax.validation.constraints.NotNull

/**
 * @author hsena
 */
@RestController
@RequestMapping(value = ["/api/commands"])
class CommandController(private val commandGateway: CommandGateway) {

    @Async
    @PostMapping(value = ["/samples/id/{id}"])
    @ResponseStatus(value = HttpStatus.CREATED)
    fun getPositions(@NotNull @PathVariable id: Int): CompletableFuture<Unit> =
            this.commandGateway.send(ListSampleCommand(listSampleParameter = ListSampleParameter(id)))

}