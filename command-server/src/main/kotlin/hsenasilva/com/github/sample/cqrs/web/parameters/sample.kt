package hsenasilva.com.github.sample.cqrs.web.parameters

import java.math.BigDecimal
import javax.validation.constraints.NotNull

/**
 * @author hsena
 */

data class AmountParameter(@NotNull val id: String, @NotNull val value: BigDecimal)
