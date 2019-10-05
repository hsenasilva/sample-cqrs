package hsenasilva.com.github.sample.cqrs.domain

import hsenasilva.com.github.sample.cqrs.web.parameters.ListSampleParameter
import org.axonframework.modelling.command.TargetAggregateIdentifier
import java.io.Serializable

/**
 * @author hsena
 */
abstract class SampleEvent(open val listSampleParameter: ListSampleParameter) : Serializable

data class ListSampleCommand(@TargetAggregateIdentifier override val listSampleParameter: ListSampleParameter) : SampleEvent(listSampleParameter)

