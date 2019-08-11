package hsenasilva.com.github.sample.cqrs.web

import hsenasilva.com.github.sample.cqrs.repository.SampleRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * @author hsena
 */
@RestController
@RequestMapping(value = ["/api/queries"])
class QueryController(@Autowired val repository: SampleRepository) {

    @GetMapping(value = ["/samples/{id}"])
    fun getSample(@PathVariable id: Int) = this.repository.findById(id)

}