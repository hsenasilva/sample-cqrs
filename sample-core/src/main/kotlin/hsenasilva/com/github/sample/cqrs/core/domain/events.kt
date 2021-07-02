package hsenasilva.com.github.sample.cqrs.core.domain

import org.axonframework.serialization.Revision
import java.math.BigDecimal

/**
 * @author hsena
 */
abstract class AccountEvent(val id: Account)
@Revision("1.0")
data class CreatedCheckingAccount(val account: Account) : AccountEvent(id = account)
@Revision("1.0")
data class DebitedBalance(val account: Account, val value: BigDecimal) : AccountEvent(id = account)
@Revision("1.0")
data class CreditedBalance(val account: Account, val value: BigDecimal) : AccountEvent(id = account)