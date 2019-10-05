package hsenasilva.com.github.sample.cqrs.web

import hsenasilva.com.github.sample.cqrs.domain.SampleEntity
import hsenasilva.com.github.sample.cqrs.repository.SampleRepository
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
@RequestMapping(value = ["/samples"])
class QueryController(@Autowired val repository: SampleRepository) {

    @GetMapping(value = ["/{id}"])
    fun getSample(@PathVariable id: Int) : ResponseEntity<SampleEntity> {

        return this.repository.findById(id)?.let {

            ResponseEntity.status(HttpStatus.OK).body(it)

        } ?: ResponseEntity.status(HttpStatus.NOT_FOUND).build()
    }

}