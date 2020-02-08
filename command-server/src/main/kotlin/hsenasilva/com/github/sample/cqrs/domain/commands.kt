package hsenasilva.com.github.sample.cqrs.domain

import hsenasilva.com.github.sample.cqrs.core.domain.SampleId
import hsenasilva.com.github.sample.cqrs.web.parameters.CreateSampleParameter
import org.axonframework.modelling.command.TargetAggregateIdentifier

/**
 * @author hsena
 */
abstract class SampleCommand(open val id: SampleId)
data class CreateSampleCommand(@TargetAggregateIdentifier override val id: SampleId, val createSampleParameter: CreateSampleParameter): SampleCommand(id)
data class RequestSampleCommand(@TargetAggregateIdentifier override val id: SampleId, val createSampleParameter: CreateSampleParameter): SampleCommand(id)
data class CancelCreateSampleCommand(@TargetAggregateIdentifier override val id: SampleId, val createSampleParameter: CreateSampleParameter): SampleCommand(id)


