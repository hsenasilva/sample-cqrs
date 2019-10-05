package hsenasilva.com.github.sample.cqrs.domain

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.io.Serializable

/**
 * @author hsena
 */
@Document(collection = "sample")
data class SampleEntity(@Id val requestId: String?, val id: Int?, val stuff: String) : Serializable