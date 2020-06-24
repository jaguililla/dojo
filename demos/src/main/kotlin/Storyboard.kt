
@file:Suppress("FunctionName") // Exception to allow a more close experience to Gherkin

import java.time.LocalDate

class Feature(title: String = "", tags: Set<String> = emptySet(), block: Feature.() -> Unit = {}) {

    constructor(title: String = "", tags: Set<String> = emptySet(), vararg scenarios: Scenario) :
            this(title, tags) { this.scenarios = scenarios.toList() }

    var title: String = title
    var tags: Set<String> = tags
    var description: String = ""

    private var background: Background? = null
    private var scenarios: List<Scenario> = emptyList()

    init {
        block()
    }

    fun Scenario(text: String = "", block: Scenario.() -> Unit = {}) {

    }

    fun Background(text: String = "", block: Background.() -> Unit = {}) {

    }
}

open class Executable {

    private var steps: List<Step> = listOf()

    fun Given(text: String = "", handler: Step.() -> Unit = {}) {
        step(StepType.GIVEN, text, handler)
    }

    fun When(text: String = "", handler: Step.() -> Unit = {}) {
        step(StepType.WHEN, text, handler)
    }

    fun Then(text: String = "", handler: Step.() -> Unit = {}) {
        step(StepType.THEN, text, handler)
    }

    fun And(text: String = "", handler: Step.() -> Unit = {}) {
        step(steps.last().type, text, handler)
    }

    fun But(text: String = "", handler: Step.() -> Unit = {}) {
        step(steps.last().type, text, handler)
    }

    private fun step(type: StepType, text: String = "", handler: Step.() -> Unit = {}) {
        steps = steps + Step(type, text, handler)
    }
}

class Background: Executable()

class Scenario(
    title: String = "",
    tags: Set<String> = emptySet(),
    block: Scenario.() -> Unit = {}): Executable()

class ScenarioOutline<T>(
    title: String = "",
    tags: Set<String> = emptySet(),
    block: ScenarioOutline<T>.() -> Unit = {}): Executable() {

    private var examples: List<T> = emptyList()

    fun Examples(vararg examples: T) {
        this.examples = examples.toList()
    }
}

enum class StepType { GIVEN, WHEN, THEN }

class Step(var type: StepType, var text: String, val handler: Step.() -> Unit = {})

fun main() {
    data class Case(val id: String, val date: LocalDate)

    Feature("A feature") {
        Background {
            Given("something")
            When("other")
            Then("other") { }
            And { }
            But()
        }

        Scenario("Scenario 1") {
            Given()
            When()
            Then()
        }

        ScenarioOutline<Case>("Scenario Outline 1") {
            Given()
            When()
            Then()

            Examples(
                Case(id = "id", date = LocalDate.now())
            )
        }
    }
}

