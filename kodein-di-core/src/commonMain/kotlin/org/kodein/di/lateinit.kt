package org.kodein.di

import kotlin.reflect.KProperty

/**
 * Kodein object that defers all method to a base kodein object that can be set later.
 *
 * You can use all lazy methods on this even if [baseKodein] is not set, but you cannot **retrieve** a value while it is not set.
 */
@Deprecated(DEPRECATE_7X)
class LateInitKodein : Kodein {

    /**
     * The user is responsible to set this property **before** actually retrieveing a value.
     */
    @Deprecated(DEPRECATE_7X)
    lateinit var baseKodein: Kodein

    override val container: KodeinContainer get() = baseKodein.container
}

/**
 * Kodein object that defers all method to a kodein object that will be created only upon first retrieval.
 */
@Deprecated(DEPRECATE_7X)
class LazyKodein(f: () -> Kodein) : Kodein {

    /**
     * The real kodein object that will be created upon first retrieval (or manual access).
     */
    @Suppress("MemberVisibilityCanBePrivate")
    @Deprecated(DEPRECATE_7X)
    val baseKodein by lazy(f)

    override val container: KodeinContainer get() = baseKodein.container

    operator fun getValue(thisRef: Any?, property: KProperty<*>) = this
}
