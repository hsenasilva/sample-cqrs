package hsenasilva.com.github.sample.cqrs.core.domain

import org.axonframework.serialization.Revision
import java.math.BigDecimal

/**
 * @author hsena
 */

abstract class AccountEvent(val id: Account, open val balance: BigDecimal)

@Revision("1.0")
data class DebitedBalance(
    val account: Account,
    override val balance: BigDecimal,
    val value: BigDecimal
) : AccountEvent(
    id = account,
    balance = balance
)

@Revision("1.0")
data class CreditedBalance(
    val account: Account,
    override val balance: BigDecimal,
    val value: BigDecimal
) : AccountEvent(
    id = account,
    balance = balance
)
