package hsenasilva.com.github.sample.cqrs.listener

import hsenasilva.com.github.sample.cqrs.core.domain.ListedSampleEvent
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
    fun handle(event: ListedSampleEvent, @Autowired repository: SampleRepository) {
        repository.findById(event.sample.id)?.let {
            repository.save(SampleEntity(it.requestId, event.sample.id, event.sample.stuff))
        } ?: repository.save(SampleEntity(event.sample.requestId, event.sample.id, event.sample.stuff))
    }

}