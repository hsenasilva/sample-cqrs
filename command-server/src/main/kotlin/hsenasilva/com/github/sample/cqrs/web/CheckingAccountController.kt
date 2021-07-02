package hsenasilva.com.github.sample.cqrs.web

import hsenasilva.com.github.sample.cqrs.core.domain.Account
import hsenasilva.com.github.sample.cqrs.domain.CreditBalanceCommand
import hsenasilva.com.github.sample.cqrs.domain.DebitBalanceCommand
import hsenasilva.com.github.sample.cqrs.web.callback.CommandGatewayCallback
import hsenasilva.com.github.sample.cqrs.web.parameters.AmountParameter
import org.axonframework.commandhandling.gateway.CommandGateway
import org.springframework.http.HttpStatus
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
    fun credit(@PathVariable id: String, @RequestBody @Valid @NotNull amountParameter: AmountParameter) =
        this.commandGateway.send(
            CreditBalanceCommand(
                id = Account(id),
                value = amountParameter.value
            ),
            this.commandGatewayCallback
        )

    @PutMapping("/debit/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    fun debit(@PathVariable id: String, @RequestBody @Valid @NotNull amountParameter: AmountParameter) {
        try {
            this.commandGateway.sendAndWait<Any>(
                DebitBalanceCommand(
                    id = Account(id),
                    value = amountParameter.value
                )
            )
        } catch (e: Exception) {
            throw ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }
}
