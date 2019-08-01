package hsenasilva.com.github.sample.cqrs.domain

import org.axonframework.modelling.command.TargetAggregateIdentifier
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.io.Serializable
import java.util.*
import javax.validation.constraints.NotNull

/**
 * @author hsena
 */
@Document(collection = "Sample")
data class Sample(@Id val requestId: String?, val id: Int?, val stuff: String) : Serializable

data class SampleParameter(@NotNull val id: Int, @NotNull val stuff: String) : Serializable

data class ListSampleParameter(@TargetAggregateIdentifier val requestId: String, val id: Int, val stuff: String) {
    constructor(id: Int, stuff: String) : this(requestId = UUID.randomUUID().toString(), id = id, stuff = stuff)
}
