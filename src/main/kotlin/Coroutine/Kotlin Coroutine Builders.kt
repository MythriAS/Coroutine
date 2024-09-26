package org.example.Kotkin

import kotlinx.coroutines.*

/* Coroutine Builders are used for creating coroutines
   Types of Coroutine Builders
*  launch -> GlobalScope.launch{} ->Companion Object
*  async  -> GlobalScope.async{}
*  runBlocking */


/* Using launch{} Function if create login screenlike Local C1.
*  If the lifetime of Login screen is over, then coroutine c1 is also over*/

/* GlobalScope.launch{ }  //Create coroutines at global(app) level
*  Global coroutines are top-level coroutines & can survive the entire life of the application.*/


/*  When to use GlobalScope.launch{ } &  launch{} Functions
*   CREATES COROUTINE AT GOBAL (APP) LEVEL
GlobalScope.launch{
    // File download             GlobalScope.launch{} is Discouraged.Use only when needen.
    // Play Music
}

*   CREATES COROUTINE AT LOCAL SCOPE
launch{
   //SOME DATA COMPUTATION         launch{} is By default, use launch{ }
   //LOGIN OPEARTION
}
*/


/*  launch{} Function is run in main thread
*   Reason : Because launch coroutine builder is actuallt present within the scope of the parent run blocking coroutine builder & since
    these run blocking runs on main thread so these child coroutine inherit the scope of the parent coroutine & hence, run on the Main thread
    * If runBlocking {} run in Thread T1 then launch{} also run in Thread T1.*/


/*fun main() = runBlocking {
    println(Thread.currentThread().name)
    launch {
        println(Thread.currentThread().name)
        delay(1000)
        println(Thread.currentThread().name)
    }
    delay(2000)
    println(Thread.currentThread().name)
}*/

//USING JOB

/*fun main() = runBlocking {
    println(Thread.currentThread().name)
    val job:Job=launch { //USING JOB OBJECT CAN CONTROL THE COROUTINE
        println(Thread.currentThread().name)
        delay(1000)
        println(Thread.currentThread().name)
    }
    //delay(2000) // WAIT FOR COROUTINE TO FINISH (PRACTIALLY NOT A RIGHT WAY TO WAIT)
    job.join() //Join() will wait coroutine to finish it execution only after which the next statement will be executed.[In one word no need to hardcode the time using delay].
    //job.cancel()//Using job object can cancel the coroutine.
    println(Thread.currentThread().name)
}*/

/*  IMP NOTE :
*   Launches a new coroutine without blocking the current thread
    Inherits the thread & coroutine scope of the immediate parent coroutine
*   Returns a reference to the job object
*   Using job object you can cancel the coroutine or wait for coroutine to finsh.
*   launch launch is essentially a Kotlin coroutine builder that is “fire and forget”.
    This means that launch creates a new coroutine that won’t return any result to the caller.*/


//asynch Coroutine builder : IN CODE JUST REPLACE LAUNCH {} TO async{}



/*fun main() = runBlocking {
    println(Thread.currentThread().name)
    val jobDeffered: Deferred<Int> = async { //USING JOB OBJECT CAN CONTROL THE COROUTINE
        println(Thread.currentThread().name)
        delay(1000)
        println(Thread.currentThread().name)
        15
    }
    //delay(2000) // WAIT FOR COROUTINE TO FINISH (PRACTIALLY NOT A RIGHT WAY TO WAIT)
    //jobDeffered.join()
    val num: Int =
        jobDeffered.await() //Join() will wait coroutine to finish it execution only after which the next statement will be executed.[In one word no need to hardcode the time using delay].
    //job.cancel()//Using job object can cancel the coroutine.
    println(Thread.currentThread().name)
}*/



/*  Diff between launch{} & async{}
*   async{} : it doesn't return job Object it basically return Deferred Object. Defeered is the sub class of job object
*   delay() , join() , awit () all are suspect function.[wait for coroutine to finish the thread]*/

/*'async' Coroutine Builder*/

/* val def: Deferred<String> = async { //c1
   delay(1000) //do some work
   "Mythri A S"
    }
    val name= def.await()
    //def.cancel or def.join...etc

 Imp Note:
*  Launches a new coroutine without blocking the current thread
   Inherit the thread & coroutine scope of the immediate parent coroutine.
*  Returns a ref to the Deferred<T> object
*  Using Deferred object you can cancel the coroutine, wait for coroutine to finish , or retrive the returned result.*/



//Run Blocking Co routine Builder

/*  Use of Run Blocking Function
*   Run Blocking Function used to write the test cases to test suspending function.*/


fun main() = runBlocking {
    println(Thread.currentThread().name)
    val jobDeffered: Deferred<Int> = async { //USING JOB OBJECT CAN CONTROL THE COROUTINE
        println(Thread.currentThread().name)
        delay(1000)
        println(Thread.currentThread().name)
        15
    }
    //delay(2000) // WAIT FOR COROUTINE TO FINISH (PRACTIALLY NOT A RIGHT WAY TO WAIT)
    //jobDeffered.join()
    val num: Int =
        jobDeffered.await() //Join() will wait coroutine to finish it execution only after which the next statement will be executed.[In one word no need to hardcode the time using delay].
    //job.cancel()//Using job object can cancel the coroutine.
    println(Thread.currentThread().name)
}

suspend fun myOwnSuspendFun(){
    delay(1000)
}


