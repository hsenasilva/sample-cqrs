package hsenasilva.com.github.sample.cqrs.domain

import hsenasilva.com.github.sample.cqrs.core.domain.Account
import hsenasilva.com.github.sample.cqrs.core.domain.SampleStatus
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.io.Serializable

/**
 * @author hsena
 */
@Document(collection = "sample")
data class SampleEntity(@Id val id: Account?, val stuff: String, val status: SampleStatus) : Serializable