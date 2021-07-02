package hsenasilva.com.github.sample.cqrs.domain

import hsenasilva.com.github.sample.cqrs.core.domain.Account
import org.axonframework.modelling.command.TargetAggregateIdentifier
import java.math.BigDecimal

/**
 * @author hsena
 */
abstract class AccountCommand(open val id: Account)
data class CreditBalanceCommand(@TargetAggregateIdentifier override val id: Account, val value: BigDecimal) : AccountCommand(id)
data class DebitBalanceCommand(@TargetAggregateIdentifier override val id: Account, val value: BigDecimal) : AccountCommand(id)
