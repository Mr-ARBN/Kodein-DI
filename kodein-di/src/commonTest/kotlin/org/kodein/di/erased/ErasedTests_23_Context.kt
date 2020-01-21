package org.kodein.di.erased

import org.kodein.di.DI
import org.kodein.di.DIAware
import org.kodein.di.test.FixMethodOrder
import org.kodein.di.test.MethodSorters
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class ErasedTests_23_Context {

    @Test
    fun test_00_lazyOnContext() {
        val kodein = DI{
            bind<String>() with contexted<String>().provider { "Salomon $context" }
        }

        var contextRetrieved = false

        val name: String by kodein.on { contextRetrieved = true ; "BRYS" } .instance()

        assertFalse(contextRetrieved)
        assertEquals("Salomon BRYS", name)
        assertTrue(contextRetrieved)
    }

    class T01(override val di: DI) : DIAware {
        var contextRetrieved = false
        override val diContext = diContext {
            contextRetrieved = true
            "BRYS"
        }
        val name: String by instance()
    }

    @Test
    fun test_01_lazyKContext() {
        val kodein = DI{
            bind<String>() with contexted<String>().provider { "Salomon $context" }
        }

        val t = T01(kodein)

        assertFalse(t.contextRetrieved)
        assertEquals("Salomon BRYS", t.name)
        assertTrue(t.contextRetrieved)
    }

}
