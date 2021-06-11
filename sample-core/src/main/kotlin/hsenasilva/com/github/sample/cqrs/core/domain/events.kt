package hsenasilva.com.github.sample.cqrs.core.domain

/**
 * @author hsena
 */

abstract class SampleEvent(val id: SampleId)
data class CreatedSampleEvent(val sampleId: SampleId, val sample: Sample): SampleEvent(id = sampleId)
data class CanceledCreateSampleEvent(val sampleId: SampleId, val sample: Sample): SampleEvent(id = sampleId)