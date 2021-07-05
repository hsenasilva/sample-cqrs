package hsenasilva.com.github.sample.cqrs.web

import hsenasilva.com.github.sample.cqrs.core.domain.Account
import hsenasilva.com.github.sample.cqrs.domain.CreditBalanceCommand
import hsenasilva.com.github.sample.cqrs.domain.DebitBalanceCommand
import hsenasilva.com.github.sample.cqrs.web.callback.CommandGatewayCallback
import hsenasilva.com.github.sample.cqrs.web.parameters.AmountParameter
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

    @PutMapping("/{id}")
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    fun credit(@PathVariable id: String, @RequestBody @Valid @NotNull amountParameter: AmountParameter) {
        // ASYNC commandGateway.send
        this.commandGateway.send(
            CreditBalanceCommand(
                id = Account(id),
                value = amountParameter.value
            ),
            this.commandGatewayCallback
        )
    }

    @PutMapping("/debit/{id}")
    fun debit(@PathVariable id: String, @RequestBody @Valid @NotNull amountParameter: AmountParameter): ResponseEntity<*> {
        // SYNC commandGateway.sendAndWait
        return try {
            this.commandGateway.sendAndWait<DebitBalanceCommand>(
                DebitBalanceCommand(
                    id = Account(id),
                    value = amountParameter.value
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
