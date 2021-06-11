package hsenasilva.com.github.sample.cqrs.core.domain

import java.math.BigDecimal

/**
 * @author hsena
 */

abstract class AccountEvent(val id: Account)
data class CreatedCheckingAccount(val Account: Account) : AccountEvent(id = Account)
data class DebitedBalance(val Account: Account, val value: BigDecimal) : AccountEvent(id = Account)
data class CreditedBalance(val Account: Account, val value: BigDecimal) : AccountEvent(id = Account)
