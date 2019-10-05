package hsenasilva.com.github.sample.cqrs.core.domain

import java.io.Serializable

/**
 * @author hsena
 */

data class Sample(val requestId: String, val id: Int?, val stuff: String) : Serializable