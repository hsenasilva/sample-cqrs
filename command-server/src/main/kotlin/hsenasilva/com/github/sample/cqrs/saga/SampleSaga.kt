package hsenasilva.com.github.sample.cqrs.saga

import hsenasilva.com.github.sample.cqrs.core.domain.CanceledCreateSampleEvent
import hsenasilva.com.github.sample.cqrs.core.domain.CreatedSampleEvent
import hsenasilva.com.github.sample.cqrs.core.domain.SampleAction
import hsenasilva.com.github.sample.cqrs.domain.CancelCreateSampleCommand
import hsenasilva.com.github.sample.cqrs.web.parameters.CreateSampleParameter
import org.axonframework.commandhandling.gateway.CommandGateway
import org.axonframework.modelling.saga.EndSaga
import org.axonframework.modelling.saga.SagaEventHandler
import org.axonframework.modelling.saga.SagaLifecycle
import org.axonframework.modelling.saga.StartSaga
import org.axonframework.spring.stereotype.Saga
import org.springframework.beans.factory.annotation.Autowired

/**
 * @author hsena
 */
@Saga(sagaStore = "sampleSagaStore")
class SampleSaga {

    @Autowired
    @Transient
    private val commandGateway: CommandGateway? = null

    @StartSaga
    @SagaEventHandler(associationProperty = "id")
    fun handle(event: CreatedSampleEvent) {
        println("Start: CreatedSampleEvent")
        println("sample id: $event.sample.action")
        println("sample action $event.sample.action")
        println("sample status $event.sample.status")

        when(event.sample.action){
            SampleAction.CREATE -> {
                println("Saga ending for sample CreatedSampleEvent: $event.id")
                SagaLifecycle.end()
            }
            else -> {
                println("Saga ending for sample CreatedSampleEvent: $event.id")
                this.commandGateway!!.send<CancelCreateSampleCommand>(
                        CancelCreateSampleCommand(
                                id = event.id,
                                createSampleParameter = CreateSampleParameter(event.id, event.sample.stuff, event.sample.action)
                        )
                )
            }
        }
    }

    @EndSaga
    @SagaEventHandler(associationProperty = "id")
    fun handle(event: CanceledCreateSampleEvent) {
        println("Start: CanceledCreateSampleEvent")
        println("sample id: $event.sampleId")
        println("Saga ending for sample CanceledCreateSampleEvent: $event.id")

    }
}