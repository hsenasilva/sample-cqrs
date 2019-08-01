package hsenasilva.com.github.sample.cqrs.domain

import org.axonframework.modelling.command.TargetAggregateIdentifier
import java.io.Serializable

/**
 * @author hsena
 */
abstract class SampleEvent(open val listSampleParameter: ListSampleParameter) : Serializable

data class ListSampleCommand(@TargetAggregateIdentifier override val listSampleParameter: ListSampleParameter) : SampleEvent(listSampleParameter)

data class ListedSampleEvent(val sample: Sample?, override val listSampleParameter: ListSampleParameter) : SampleEvent(listSampleParameter)
