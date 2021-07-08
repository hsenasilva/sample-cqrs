package hsenasilva.com.github.sample.cqrs.web.parameters

import java.math.BigDecimal
import javax.validation.constraints.NotNull

/**
 * @author hsena
 */

data class BalanceEntryParameter(@NotNull val value: BigDecimal)
