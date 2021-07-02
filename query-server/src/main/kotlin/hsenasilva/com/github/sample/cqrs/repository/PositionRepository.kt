package hsenasilva.com.github.sample.cqrs.repository

import hsenasilva.com.github.sample.cqrs.domain.BalanceEntity
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

/**
 * @author hsena
 */
@Repository
interface BalanceRepository : MongoRepository<BalanceEntity, String> {

    fun findById(id: String?): BalanceEntity?
}
