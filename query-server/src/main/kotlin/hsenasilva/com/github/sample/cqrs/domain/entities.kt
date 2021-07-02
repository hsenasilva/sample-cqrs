package hsenasilva.com.github.sample.cqrs.domain

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.io.Serializable
import java.math.BigDecimal

/**
 * @author hsena
 */
@Document(collection = "balance")
data class BalanceEntity(@Id val id: String?, val balance: BigDecimal) : Serializable
