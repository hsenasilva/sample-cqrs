package hsenasilva.com.github.sample.cqrs.core.domain

import java.math.BigDecimal

/**
 * @author hsena
 */

abstract class AccountEvent(val id: Account)
data class CreatedCheckingAccount(val account: Account) : AccountEvent(id = account)
data class DebitedBalance(val account: Account, val value: BigDecimal) : AccountEvent(id = account)
data class CreditedBalance(val account: Account, val value: BigDecimal) : AccountEvent(id = account)
