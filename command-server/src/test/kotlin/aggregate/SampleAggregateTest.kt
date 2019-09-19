package aggregate

import hsenasilva.com.github.sample.cqrs.aggregate.SampleAggregate
import hsenasilva.com.github.sample.cqrs.domain.ListSampleCommand
import hsenasilva.com.github.sample.cqrs.domain.ListSampleParameter
import hsenasilva.com.github.sample.cqrs.domain.ListedSampleEvent
import hsenasilva.com.github.sample.cqrs.domain.Sample
import hsenasilva.com.github.sample.cqrs.repository.SampleRepository
import org.axonframework.test.aggregate.AggregateTestFixture
import org.axonframework.test.aggregate.FixtureConfiguration
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito

class SampleAggregateTest {

    private lateinit var fixture: FixtureConfiguration<SampleAggregate>
    private lateinit var listSampleParameter: ListSampleParameter
    private lateinit var listSampleCommand: ListSampleCommand
    private lateinit var sample: Sample

    @Before
    fun `setup aggregate test fixture`() {
        fixture = AggregateTestFixture(SampleAggregate::class.java)
                .registerInjectableResource(Mockito.mock(SampleRepository::class.java)) as FixtureConfiguration<SampleAggregate>

        listSampleParameter = ListSampleParameter(1, "stuff")
        listSampleCommand = ListSampleCommand(listSampleParameter)
        sample = Sample(requestId = listSampleCommand.listSampleParameter.requestId,
                id = listSampleCommand.listSampleParameter.id, stuff = listSampleCommand.listSampleParameter.stuff)
    }

    @Test
    fun `it should create ListSampleCommand and generate ListedSampleEvent` () {
        fixture
                .givenNoPriorActivity()
                .`when`(ListSampleCommand(listSampleParameter))
                .expectEvents(ListedSampleEvent(sample, listSampleParameter))
                .expectSuccessfulHandlerExecution()
    }

}