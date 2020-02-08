package hsenasilva.com.github.sample.cqrs.web.parameters

import hsenasilva.com.github.sample.cqrs.core.domain.SampleAction
import hsenasilva.com.github.sample.cqrs.core.domain.SampleId
import java.io.Serializable
import javax.validation.constraints.NotNull

/**
 * @author hsena
 */

data class SampleParameter(@NotNull val id: String, @NotNull val stuff: String, val action: SampleAction) : Serializable

data class CreateSampleParameter(val id: SampleId, val stuff: String, val action: SampleAction)
