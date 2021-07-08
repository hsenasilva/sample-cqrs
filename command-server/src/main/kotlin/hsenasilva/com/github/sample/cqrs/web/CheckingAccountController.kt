package hsenasilva.com.github.sample.cqrs.web

import hsenasilva.com.github.sample.cqrs.core.domain.Account
import hsenasilva.com.github.sample.cqrs.domain.CreditBalanceCommand
import hsenasilva.com.github.sample.cqrs.domain.DebitBalanceCommand
import hsenasilva.com.github.sample.cqrs.web.callback.CommandGatewayCallback
import hsenasilva.com.github.sample.cqrs.web.parameters.BalanceEntryParameter
import org.axonframework.commandhandling.gateway.CommandGateway
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException
import javax.validation.Valid
import javax.validation.constraints.NotNull

/**
 * @author hsena
 */
@RestController
@RequestMapping(value = ["/checking-accounts"])
class CheckingAccountController(
    private val commandGateway: CommandGateway,
    private val commandGatewayCallback: CommandGatewayCallback
) {

    @PutMapping("/async/{id}")
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    fun asyncBalanceEntry(@PathVariable id: String, @RequestBody @Valid @NotNull balanceEntryParameter: BalanceEntryParameter) {
        this.commandGateway.send(
            CreditBalanceCommand(
                id = Account(id),
                value = balanceEntryParameter.value
            ),
            this.commandGatewayCallback
        )
    }

    @PutMapping("/sync/{id}")
    fun syncBalanceEntry(@PathVariable id: String, @RequestBody @Valid @NotNull balanceEntryParameter: BalanceEntryParameter): ResponseEntity<*> {
        return try {
            this.commandGateway.sendAndWait<DebitBalanceCommand>(
                DebitBalanceCommand(
                    id = Account(id),
                    value = balanceEntryParameter.value
                )
            )
            ResponseEntity.noContent().build<Any>()
        }
        catch (e: IllegalArgumentException) {
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, e.message, e)
        }
        catch (e: Exception) {
            throw ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.message, e)
        }
    }
}
