package hsenasilva.com.github.sample.cqrs.configuration

import org.springframework.context.annotation.Configuration
import java.io.IOException
import javax.servlet.*
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


/**
 * @author hsena
 */
@Configuration
class CorsConfiguration : Filter {


    @Throws(IOException::class, ServletException::class)
    override fun doFilter(req: ServletRequest, res: ServletResponse, chain: FilterChain) {
        val response = res as HttpServletResponse
        response.setHeader("Access-Control-Allow-Origin", "*")

        response.setHeader("Access-Control-Allow-Credentials", "true")

        response.setHeader("Access-Control-Allow-Methods", "POST, PUT, GET, OPTIONS, DELETE")
        response.setHeader("Access-Control-Max-Age", "3600")
        response.setHeader("Access-Control-Allow-Headers", "X-Requested-With, Authorization, Origin, Content-Type, Version")
        response.setHeader("Access-Control-Expose-Headers", "X-Requested-With, Authorization, Origin, Content-Type")

        val request = req as HttpServletRequest
        if (request.method != "OPTIONS") {
            chain.doFilter(req, res)
        }
    }

    override fun destroy() {

    }

    @Throws(ServletException::class)
    override fun init(filterConfig: FilterConfig) {
    }
}