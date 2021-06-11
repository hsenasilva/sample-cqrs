package hsenasilva.com.github.sample.cqrs.web

import hsenasilva.com.github.sample.cqrs.core.domain.Account
import hsenasilva.com.github.sample.cqrs.domain.CreditBalanceCommand
import hsenasilva.com.github.sample.cqrs.domain.DebitBalanceCommand
import hsenasilva.com.github.sample.cqrs.web.callback.CommandGatewayCallback
import hsenasilva.com.github.sample.cqrs.web.parameters.AmountParameter
import org.axonframework.commandhandling.gateway.CommandGateway
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RequestBody
import javax.validation.Valid
import javax.validation.constraints.NotNull

/**
 * @author hsena
 */
@RestController
@RequestMapping(value = ["/commands"])
class CommandController(
    private val commandGateway: CommandGateway,
    private val commandGatewayCallback: CommandGatewayCallback
) {

    @PostMapping("credit-balance")
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    fun activeAccount(@RequestBody @Valid @NotNull amountParameter: AmountParameter) =
        this.commandGateway.send(
            CreditBalanceCommand(
                id = Account(amountParameter.id),
                value = amountParameter.value
            ),
            this.commandGatewayCallback
        )

    @PostMapping("debit-account")
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    fun creditAccoutn(@RequestBody @Valid @NotNull amountParameter: AmountParameter) =
        this.commandGateway.sendAndWait<Any>(
            DebitBalanceCommand(
                id = Account(amountParameter.id),
                value = amountParameter.value
            )
        )
}
