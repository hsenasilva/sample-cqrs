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

    @PostMapping("credit")
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    fun credit(@RequestBody @Valid @NotNull amountParameter: AmountParameter) =
        this.commandGateway.send(
            CreditBalanceCommand(
                id = Account(amountParameter.id),
                value = amountParameter.value
            ),
            this.commandGatewayCallback
        )

    @PostMapping("debit")
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    fun debit(@RequestBody @Valid @NotNull amountParameter: AmountParameter) {
        try {
            this.commandGateway.sendAndWait<Any>(
                DebitBalanceCommand(
                    id = Account(amountParameter.id),
                    value = amountParameter.value
                )
            )
        } catch (e: Exception) {
            throw ResponseStatusException(HttpStatus.BAD_REQUEST)
        }
    }
}
