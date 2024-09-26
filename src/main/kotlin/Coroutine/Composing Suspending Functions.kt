package org.example.Kotkin

import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlin.time.measureTime

/* Sequental Execution : Functions execution are sequential by default
*  Concurrent Execution : Achieve concurrent execution by async{}
*  Lazy coroutine Execution : Lazily execute code in coroutine. */

//=======================================================================================================================================================================================================================================================


//Sequental Execution :By default methods are executed Sequence

/* Code execution within coroutine is by default sequential.*/
/*fun main(args: Array<String>) = runBlocking {
    println("Main method started ${Thread.currentThread().name}")
    val time = measureTime {
        val msgOne = getMessageOne()
        val msgTwo = getMessageTwo()
        println("The entire message is : ${msgOne + msgTwo}")
    }
    println("Time taken : $time ms")
    println("Main method ended ${Thread.currentThread().name}")
}

suspend fun getMessageOne(): String {
    delay(1000L)
    return "Hello"
}

suspend fun getMessageTwo(): String {
    delay(1000L)
    return "World"
}*/


//================================================================================================================================================================================================


//Concurrent Execution or Parallel Exceution hence it run parallely time takin is less than sequential.

/*fun main(args: Array<String>) = runBlocking {
    println("Main method started ${Thread.currentThread().name}")
    val time = measureTime {
        val msgOne = async { getMessageOne() } //Instead of async cannot use launach{ } why?coz it doesn't return the value
        val msgTwo =  async { getMessageTwo() }
        println("The entire message is : ${msgOne.await() + msgTwo.await()}")
    }
    println("Time taken : $time ms")
    println("Main method ended ${Thread.currentThread().name}")
}

suspend fun getMessageOne(): String {
    delay(1000L)
    return "Hello"
}

suspend fun getMessageTwo(): String {
    delay(1000L)
    return "World"
}*/

//================================================================================================================================================================================================

/*Lazy coroutine Execution */


fun main(args: Array<String>) = runBlocking {
    println("Main method started ${Thread.currentThread().name}")
    val msgOne =
        async(start = CoroutineStart.LAZY) { getMessageOne() } //Instead of async cannot use launach{ } why?coz it doesn't return the value
    val msgTwo = async { getMessageTwo() }
    //println("The entire message is : ${msgOne.await() + msgTwo.await()}")

    println("Main method ended ${Thread.currentThread().name}")
}

suspend fun getMessageOne(): String {
    delay(1000L)
    println("After working in getMessageOne()")
    return "Hello"
}

suspend fun getMessageTwo(): String {
    delay(1000L)
    println("After working in getMessageTwo()")
    return "World"
}


/*
output:
Main method started main
Main method ended main
After working in getMessageTwo() //Here this variable is not used but still wasting memory hence in getMessageone() used Lazy. */
