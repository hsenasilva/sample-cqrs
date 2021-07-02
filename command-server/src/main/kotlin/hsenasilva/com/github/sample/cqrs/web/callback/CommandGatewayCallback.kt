package hsenasilva.com.github.sample.cqrs.web.callback

import hsenasilva.com.github.sample.cqrs.domain.CreateCheckingAccountCommand
import org.axonframework.commandhandling.CommandCallback
import org.axonframework.commandhandling.CommandMessage
import org.axonframework.commandhandling.CommandResultMessage
import org.axonframework.modelling.command.AggregateNotFoundException
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

/**
 * @author hsena
 */
@Component
class CommandGatewayCallback : CommandCallback<CreateCheckingAccountCommand, String> {

    override fun onResult(commandMessage: CommandMessage<out CreateCheckingAccountCommand>, commandResultMessage: CommandResultMessage<out String>) {
        when {
            commandResultMessage.isExceptional && commandResultMessage.exceptionResult() is AggregateNotFoundException -> {
                LOGGER.error("Command resulted in exception: ${commandMessage.commandName}", commandResultMessage.exceptionResult())
            }
            else -> LOGGER.info("Command executed successfully: ${commandMessage.commandName}")
        }
    }

    companion object {
        private val LOGGER = LoggerFactory.getLogger(CommandGatewayCallback::class.java)
    }
}

