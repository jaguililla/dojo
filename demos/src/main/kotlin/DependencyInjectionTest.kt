
import kotlin.reflect.KClass
import org.junit.Test

/*
 * Example interfaces and implementations
 */
interface Foo

class SubFoo1 : Foo
class SubFoo2 : Foo
object SubFoo3 : Foo

interface Bar { val foo: Foo }

class SubBar1(override val foo: Foo) : Bar
class SubBar2(override val foo: Foo = inject<Foo>()) : Bar
class SubBar3(override val foo: Foo = inject()) : Bar
class SubBar3a(override val foo: Foo) : Bar {
    constructor() : this(inject())
}

interface Service {
    fun a(p: Int)
    fun b(p: Boolean): Int
}

/*
 * Generators registry and utilities
 */
private var registry : Map<KClass<*>, () -> Any> = mapOf()

fun <T : Any, R : T> register(type: KClass<T>, provider: () -> R) {
    registry += type to provider
}

infix fun <T : Any, R : T> KClass<T>.bind (provider: () -> R) {
    register(this, provider)
}

infix fun <T : Any, R : T> KClass<T>.bind (instance: R) {
    register(this) { instance }
}

inline fun <reified T : Any> register(noinline provider: () -> T) =
    register(T::class, provider)

inline fun <reified T : Any> inject(): T = (T::class)()

@Suppress("UNCHECKED_CAST")
operator fun <T : Any> KClass<T>.invoke (): T =
    registry[this]?.invoke() as? T ?: error("${this.java.name} generator not found")

/*
 * Tests
 */
class DiPoC {
    @Test fun di_just_works() {
        register(Foo::class, ::SubFoo1)
        register<Foo>(::SubFoo1)

        val foo1 = (Foo::class)()
        assert(foo1.javaClass == SubFoo1::class.java)

        val foo1a = inject<Foo>()
        assert(foo1a.javaClass == SubFoo1::class.java)

        val foo1b: Foo = inject()
        assert(foo1b.javaClass == SubFoo1::class.java)

        register(Foo::class, ::SubFoo2)
        register<Foo>(::SubFoo2)

        val foo2 = (Foo::class)()
        assert(foo2.javaClass == SubFoo2::class.java)

        register (Foo::class) { SubFoo3 }
        register<Foo> { SubFoo3 }

        val foo3 = (Foo::class)()
        assert(foo3.javaClass == SubFoo3::class.java)

        register (Bar::class) { SubBar1((Foo::class)()) }
        register<Bar> { SubBar1(inject<Foo>()) }

        val bar1 = (Bar::class)()
        assert(bar1.javaClass == SubBar1::class.java)
        assert(bar1.foo.javaClass == SubFoo3::class.java)

        register (Bar::class) { SubBar2() }

        val bar2 = (Bar::class)()
        assert(bar2.javaClass == SubBar2::class.java)
        assert(bar2.foo.javaClass == SubFoo3::class.java)

        register (Bar::class) { SubBar3() }
        register (Bar::class, ::SubBar3a)

        val bar3 = inject<Bar>()
        assert(bar3.javaClass == SubBar3a::class.java)
        assert(bar3.foo.javaClass == SubFoo3::class.java)
    }

    @Test fun mocks_are_easy_to_build() {
        var aCalled = false

        Service::class bind object : Service {
            override fun a(p: Int) { aCalled = true }
            override fun b(p: Boolean) = 100
        }

        val srv = inject<Service>()

        assert(srv.b(true) == 100)
        srv.a(0)
        assert(aCalled)
    }
}
