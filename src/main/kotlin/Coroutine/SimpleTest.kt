package org.example.Kotkin

import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test

//A SUSPENDING FUNCTION CAN BE CALLED EITHER FROM A COROUTINE OR FROM ANOTHER SUSPENDING FUNCTION.

class SimpleTest {

    @Test
    fun myFirstTest() = runBlocking {
        myOwnSuspendFun()
        Assert.assertEquals(10, 5 + 5)
    }
}

/*  IMP NOTE:
*   launch{} & async {} coroutine builder  doesn't block the thread
*   run Blocking coroutine builder block the thread*/