package hsenasilva.com.github.sample.cqrs.repository

import hsenasilva.com.github.sample.cqrs.domain.Sample
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

/**
 * @author hsena
 */
@Repository
interface SampleRepository : MongoRepository<Sample, String> {

    fun findById(id: Int?): Sample?

}