// !LANGUAGE: +InlineClasses
// WITH_RUNTIME
// IGNORE_BACKEND: JVM_IR

import kotlin.test.*

inline class TestUIntArrayW(val x: UIntArray)

inline class InlineCharArray(val x: CharArray) {
    override fun toString(): String = x.contentToString()
}

inline class TestInlineCharArrayW(val x: InlineCharArray)

fun box(): String {
    assertEquals("TestUIntArrayW(x=[0])", TestUIntArrayW(UIntArray(1)).toString())

    assertEquals("TestInlineCharArrayW(x=[a])", TestInlineCharArrayW(InlineCharArray(charArrayOf('a'))).toString())

    return "OK"
}
