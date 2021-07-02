package hsenasilva.com.github.sample.cqrs.web

import hsenasilva.com.github.sample.cqrs.core.domain.Account
import hsenasilva.com.github.sample.cqrs.domain.BalanceEntity
import hsenasilva.com.github.sample.cqrs.repository.BalanceRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * @author hsena
 */
@RestController
@RequestMapping(value = ["/queries/accounts"])
class QueryController(@Autowired val repository: BalanceRepository) {

    @GetMapping(value = ["/balance/{id}"])
    fun getBalance(@PathVariable id: String): ResponseEntity<BalanceEntity> {

        return this.repository.findById(id)?.let {

            ResponseEntity.status(HttpStatus.OK).body(it.get())
        } ?: ResponseEntity.status(HttpStatus.NOT_FOUND).build()
    }
}
