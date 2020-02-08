package hsenasilva.com.github.sample.cqrs.core.domain

import java.io.Serializable

/**
 * @author hsena
 */

data class SampleId(val id: String)

data class Sample(val id: String, val stuff: String, val action: SampleAction, val status: SampleStatus) : Serializable

enum class SampleAction {
    CREATE, CREATE_AND_CANCEL
}
enum class SampleStatus {
    REQUESTED, CREATED, CANCELED
}