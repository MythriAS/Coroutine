package org.example.Kotkin

import kotlinx.coroutines.*
import java.util.concurrent.CancellationException

/*  Why would cancel a Coroutine?
*   Result no longer needed
*   If Coroutine is taking too long to respond*/

/*  To Cancel a Coroutine , it should be cooperative
    val job=launch{
       // the codehas to be cooperative in order to get cancelled
    }
    job.cancel () // IF THE COROUTINE IS COOPERATIVE THEN CANCEL IT
    job.join() //WAITS FOR THE COROUTINE TO FINISH

Insted of using cancel () & join() both differ can use together like  job.cancelAndJoin()
job.cancelAndJoin() : IF THE COROUTINE IS COOPERATIVE THEN CANCEL IT ELSE, IF IT IS NOT COOPERATIVE THEN WAIT FOR THE COROUTINE TO FINISH

IMP NOTE : join() function will be effective only when cancel function face to cancel the coroutine */


/*  TWO WAYS TO MAKE COROUTINE COOPERATIVE
    Perodically invoke a suspending function that checks for cancellation
*   Only those suspending function that belongs to Kotlinx.coroutines package will make coroutine cooperative
*   delay(),yield(),withContext(),withTimeOut() etc.. are the suspending functions that belongs to Kotlinx.coroutines package

    use these above function in coroutine then coroutine will become cooperative & therefore it can be cancelled by calling the cancel function*/


/*fun main(args:Array<String>)= runBlocking{

    println("Main method started ${Thread.currentThread().name}")
    val job:Job=launch {
        for (i in 1..100){
            println(i)
        }
    }
    job.join()
    println("Main method ended ${Thread.currentThread().name}")
}*/


//Uinsg sleep function instead of delay function
/*fun main(args:Array<String>)= runBlocking{

    println("Main method started ${Thread.currentThread().name}")
    val job:Job=launch {
        for (i in 1..100){
            println(i)
            Thread.sleep(500)
        }
    }
    delay(200) //Let's print the value before we cancel  But in o/p it doen't print only few it print all the value means coroutin not cancelled,
    // why?b,coz as mention above defnition Coroutine coperate only above mention funtion Kotlinx.coroutine package .[Now , coroutine is incoperative]
    job.cancel()//CAN'T WAIT UNTILL 100 NUM DISPLAY
    job.join()
    println("Main method ended ${Thread.currentThread().name}")
}*/

/*o/p:Print all 1 to 100 Number no use of cancel()*/


//USING KOTLINX.COROUINT PACKAGE TO MAKE WORK OF CANCEL COROUTINR OPERATIVE[Delay]
/*fun main(args:Array<String>)= runBlocking{

    println("Main method started ${Thread.currentThread().name}")
    val job:Job=launch {
        for (i in 1..100){
            println(i)
            //Thread.sleep(500)
            delay(50)//Coroutine cooprate to delay () coz it is kotlinx.coroutine package
        }
    }
    delay(200) //Let's print the value before we cancel  But in o/p it doen't print only few it print all the value means coroutin not cancelled,
    // why?b,coz as mention above defnition Coroutine coperate only above mention funtion Kotlinx.coroutine package .[Now , coroutine is incoperative]
    //job.cancel()//CAN'T WAIT UNTILL 100 NUM DISPLAY
    //job.join()
    job.cancelAndJoin()
    println("Main method ended ${Thread.currentThread().name}")
}*/

/*Output:
Main method started main
1
2
3
4
Main method ended main*/


//USING YIELD FUNCTION : IN CASE DON'T WANT COROUTINE TO DELAY THEN YIELD() IS BEST CHOICE.
/*fun main(args:Array<String>)= runBlocking{

    println("Main method started ${Thread.currentThread().name}")
    val job:Job=launch { //Thraed T1 : Creates a non-blocking coroutine
        for (i in 1..100){
            println("$i.")
            //Thread.sleep(500)
            //delay(50)//Coroutine cooprate to delay () coz it is kotlinx.coroutine package
            yield() // or us delay() or any other suspending function as per your need
        }
    }
    delay(10) //Let's print the value before we cancel  But in o/p it doen't print only few it print all the value means coroutin not cancelled,
    // why?b,coz as mention above defnition Coroutine coperate only above mention funtion Kotlinx.coroutine package .[Now , coroutine is incoperative]
    //job.cancel()//CAN'T WAIT UNTILL 100 NUM DISPLAY
    //job.join()
    job.cancelAndJoin()
    println("Main method ended ${Thread.currentThread().name}")
}*/

/*SECOND WAY TO MAKE COROUTINE COOPERATIVE*/
/*  Explicitly check for the cancellation status within the the coroutine
    CoroutineScope.isActive boolean flag
    *  When the coroutine is active: isActive=true
    *  When the coroutine is cancelled: isActive=false */

/* Threads don't have such in-bulit mechanism to cancel itself internally.*/

/*fun main(args:Array<String>)= runBlocking{

    println("Main method started ${Thread.currentThread().name}")
    val job:Job=launch() { //Thraed T1 : Creates a non-blocking coroutine
        for (i in 1..100){
            if (!isActive) {
                return@launch //IT WILL RETURN @COROUTINE LEVEL.SO,AS SOON AS RETURN@LUNCH EXECUTED NO MORE CODE WITHIN THE COROUTINE WILL BE EXECUTED FURTHER
                //break //Can use break or return@launch
            }
            println("$i.")
            //Thread.sleep(500)
            //delay(50)//Coroutine cooprate to delay () coz it is kotlinx.coroutine package
            yield() // or us delay() or any other suspending function as per your need
        }
    }
    delay(10) //Let's print the value before we cancel  But in o/p it doen't print only few it print all the value means coroutin not cancelled,
    // why?b,coz as mention above defnition Coroutine coperate only above mention funtion Kotlinx.coroutine package .[Now , coroutine is incoperative]
    //job.cancel()//CAN'T WAIT UNTILL 100 NUM DISPLAY
    //job.join()
    job.cancelAndJoin()
    println("Main method ended ${Thread.currentThread().name}")
}*/


/*  HANDLING EXCEPTIONS :  1
*   Cancellable suspending functions such as yield(),delay() etc.. throw CancellationException on the coroutine cancellation.Hnece use TRY & CATCH BLOCK*/


/*fun main(args: Array<String>) = runBlocking {

    println("Main method started ${Thread.currentThread().name}")
    val job: Job = launch {
        try {
            for (i in 1..100) {
                println("$i.")
                yield()//Coroutine cooprate to delay () coz it is kotlinx.coroutine package
            }

        } catch (ex: CancellationException) {
            print("\nException caught safety")
        } finally {
            print("\nclose resources in finally")
        }
    }
    delay(200)
    job.cancelAndJoin()
    println("Main method ended ${Thread.currentThread().name}")
}*/

/*HANDLING EXCEPTIONS :  2
*   You cannot execute a suspending function from the finally block because the coroutine running this code is already cancelled.
*   If you want to execute a suspending function from a finally block then wrap the code within withContext(NonCancellable) function*/

/*fun main(args: Array<String>) = runBlocking {

    println("Main method started ${Thread.currentThread().name}")
    val job: Job = launch {
        try {
            for (i in 1..100) {
                println("$i.")
                yield()//Coroutine cooprate to delay () coz it is kotlinx.coroutine package
            }

        } catch (ex: CancellationException) {
            print("\nException caught safety")
        } finally {
            withContext(NonCancellable){
                delay(2000) //Generally we don't use suspending function in finally
                print("\nclose resources in finally")
            }
        }
    }
    delay(200)
    job.cancelAndJoin()
    println("Main method ended ${Thread.currentThread().name}")
}*/


/*  HANDLING EXCEPTIONS :  3

*   You can print your own cancellation message using job.cancel(CancellationException("My Crash message"))*/

/*fun main(args: Array<String>) = runBlocking {

    println("Main method started ${Thread.currentThread().name}")
    val job: Job = launch {
        try {
            for (i in 1..100) {
                println("$i.")
                yield()//Coroutine cooprate to delay () coz it is kotlinx.coroutine package
            }

        } catch (ex: CancellationException) {
            println("\nException caught safety :${ex.message}")
        } finally {
            withContext(NonCancellable){
                delay(2000) //Generally we don't use suspending function in finally
                println("\nclose resources in finally")
            }
        }
    }
    delay(10)
    job.cancel(CancellationException("My won crash message"))
    job.join()
    //job.cancelAndJoin()
    println("Main method ended ${Thread.currentThread().name}")
}*/


/* Timeouts
*  withTimeOut
*  withTimeOutOrNull
*  Similar to launch and async functions,withTimeOut and withTimeOutOrNull functions are coroutine builders.*/
//withTimeOut
/*fun main(args: Array<String>) = runBlocking {
    println("Main method started${Thread.currentThread().name}")

    withTimeout(2000) { //ERROR TimeoutCancellationException: Timed out waiting for 2000 ms
        try {
            for (i in 1..500) {
                println("$i.")
                delay(500)
            }
        } catch (ex: TimeoutCancellationException) {

        } finally {
        }
    }
    println("Main method ended ${Thread.currentThread().name}")
}*/




//withTimeOutOrNull : Doesn't throw any exception
/*fun main(args: Array<String>) = runBlocking {
    println("Main method started${Thread.currentThread().name}")

    withTimeoutOrNull(2000) { //ERROR TimeoutCancellationException: Timed out waiting for 2000 ms
        for (i in 1..500) {
            println("$i.")
            delay(500)
        }
    }
    println("Main method ended ${Thread.currentThread().name}")
}*/

//withTimeOutOrNull : and alos return lamda
fun main(args: Array<String>) = runBlocking {
    println("Main method started${Thread.currentThread().name}")

    val result:Int?=withTimeoutOrNull(2000) { //ERROR TimeoutCancellationException: Timed out waiting for 2000 ms
        for (i in 1..500) {
            println("$i.")
            delay(500)
        }
        15
    }
    println(result)//IN CASE THERE IS A TIMEOUT THAT IS COROUTINE IS UNABLE TO FINISH THE TASK WITHIN 2S THAT CASE RETURN NULL
    println("Main method ended ${Thread.currentThread().name}")
}


