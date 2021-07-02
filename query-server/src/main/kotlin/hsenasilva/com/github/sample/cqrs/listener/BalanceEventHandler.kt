package hsenasilva.com.github.sample.cqrs.listener

import hsenasilva.com.github.sample.cqrs.core.domain.CreditedBalance
import hsenasilva.com.github.sample.cqrs.core.domain.DebitedBalance
import hsenasilva.com.github.sample.cqrs.domain.BalanceEntity
import hsenasilva.com.github.sample.cqrs.repository.BalanceRepository
import org.axonframework.config.ProcessingGroup
import org.axonframework.eventhandling.EventHandler
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

/**
 * @author hsena
 */
@Component
@ProcessingGroup("BalanceProcessor")
class BalanceEventHandler {

    @EventHandler
    fun handle(event: CreditedBalance, @Autowired repository: BalanceRepository) {
        repository.findById(event.account.id)
            .map { entity ->
                repository.save(BalanceEntity(entity.account, event.balance))
            }.orElseGet {
                repository.save(BalanceEntity(event.account.id, event.balance))
            }
    }

    @EventHandler
    fun handle(event: DebitedBalance, @Autowired repository: BalanceRepository) {
        repository.findById(event.account.id)
            .map { entity ->
                repository.save(BalanceEntity(entity.account, event.balance))
            }.orElseGet {
                repository.save(BalanceEntity(event.account.id, event.balance))
            }
    }
}