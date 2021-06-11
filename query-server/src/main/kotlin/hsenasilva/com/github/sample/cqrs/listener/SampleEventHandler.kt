package hsenasilva.com.github.sample.cqrs.listener

import hsenasilva.com.github.sample.cqrs.core.domain.CreditedBalance
import hsenasilva.com.github.sample.cqrs.core.domain.DebitedBalance
import hsenasilva.com.github.sample.cqrs.core.domain.CreatedCheckingAccount
import hsenasilva.com.github.sample.cqrs.domain.SampleEntity
import hsenasilva.com.github.sample.cqrs.repository.SampleRepository
import org.axonframework.config.ProcessingGroup
import org.axonframework.eventhandling.EventHandler
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

/**
 * @author hsena
 */
@Component
@ProcessingGroup("SampleProcessor")
class SampleEventHandler {

    @EventHandler
    fun handle(event: CreatedCheckingAccount, @Autowired repository: SampleRepository) {
        repository.findById(event.sample.id).map { entity ->
            repository.save(SampleEntity(entity.id, event.sample.stuff, event.sample.status))
        }.orElseGet {
            repository.save(SampleEntity(event.Account, event.sample.stuff, event.sample.status))
        }
    }

    @EventHandler
    fun handle(event: DebitedBalance, @Autowired repository: SampleRepository) {
        repository.findById(event.value.id).map { entity ->
            repository.save(SampleEntity(entity.id, event.value.stuff, event.value.status))
        }.orElseGet {
            repository.save(SampleEntity(event.Account, event.value.stuff, event.value.status))
        }
    }

    @EventHandler
    fun handle(event: CreditedBalance, @Autowired repository: SampleRepository) {
        repository.findById(event.value.id).map { entity ->
            repository.save(SampleEntity(entity.id, event.value.stuff, event.value.status))
        }.orElseGet {
            repository.save(SampleEntity(event.Account, event.value.stuff, event.value.status))
        }
    }

}