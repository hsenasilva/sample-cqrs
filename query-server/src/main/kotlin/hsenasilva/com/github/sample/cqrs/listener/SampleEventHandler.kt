package hsenasilva.com.github.sample.cqrs.listener

import hsenasilva.com.github.sample.cqrs.core.domain.CanceledCreateSampleEvent
import hsenasilva.com.github.sample.cqrs.core.domain.CreatedSampleEvent
import hsenasilva.com.github.sample.cqrs.core.domain.RequestedSampleEvent
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
    fun handle(event: RequestedSampleEvent, @Autowired repository: SampleRepository) {
        repository.findById(event.sample.id).map { entity ->
            repository.save(SampleEntity(entity.id, event.sample.stuff, event.sample.status))
        }.orElseGet {
            repository.save(SampleEntity(event.sampleId, event.sample.stuff, event.sample.status))
        }
    }

    @EventHandler
    fun handle(event: CreatedSampleEvent, @Autowired repository: SampleRepository) {
        repository.findById(event.sample.id).map { entity ->
            repository.save(SampleEntity(entity.id, event.sample.stuff, event.sample.status))
        }.orElseGet {
            repository.save(SampleEntity(event.sampleId, event.sample.stuff, event.sample.status))
        }
    }

    @EventHandler
    fun handle(event: CanceledCreateSampleEvent, @Autowired repository: SampleRepository) {
        repository.findById(event.sample.id).map { entity ->
            repository.save(SampleEntity(entity.id, event.sample.stuff, event.sample.status))
        }.orElseGet {
            repository.save(SampleEntity(event.sampleId, event.sample.stuff, event.sample.status))
        }
    }

}