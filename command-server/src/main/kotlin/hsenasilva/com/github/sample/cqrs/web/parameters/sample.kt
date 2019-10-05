package hsenasilva.com.github.sample.cqrs.web.parameters

import org.axonframework.modelling.command.TargetAggregateIdentifier
import java.io.Serializable
import java.util.*
import javax.validation.constraints.NotNull

/**
 * @author hsena
 */

data class SampleParameter(@NotNull val id: Int, @NotNull val stuff: String) : Serializable

data class ListSampleParameter(@TargetAggregateIdentifier val requestId: String, val id: Int, val stuff: String) {
    constructor(id: Int, stuff: String) : this(requestId = UUID.randomUUID().toString(), id = id, stuff = stuff)
}
