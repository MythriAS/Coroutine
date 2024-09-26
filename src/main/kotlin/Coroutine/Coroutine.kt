package org.example.`Kotkin v`

import kotlinx.coroutines.*

/*By Lanunching application default Main thread will run.
*  Main THread Operates a smll task such as :
*  UI INTERACTION
*  MATHEMATICAL OPERATIONS
*  SMALL OPERATIONS
*  SMALL LOGICAL OPERATIONS
*  BUTTON CLICK  */

/* To opearted heavy operation need to handle in Worker or Background Thread.
*  Background Thread 1 : LONG OPERATIONS
*  Background Thread 2 : FILE DOWNLOAD
*  Background Thread 3 : DATABASE QUERIES
*  Background Thread 4 : IMAGE LOADING
*  Background Thread 5 : NETWORK OPERATION*/

/* BUT THERE WILL LIMIT CREATING BACKGROUND THREAD IN APPLICATION.CREATING MORE THREAD WILL LEAS TO OUT OF MEMORY.TO AVOID THIS THE UPDATE VERSION IS 'COROUTINES'
   In one Background thread can perform many coroutine operations

*  Coroutine 1 : LONG OPERATIONS
*  Coroutine 2 : FILE DOWNLOAD
*  Coroutine 3 : DATABASE QUERIES
*  Coroutine 4 : IMAGE LOADING
*  Coroutine 5 : NETWORK OPERATION */


/* Coroutines Properties:
*  Light-weight Threads
*  Like threads , coroutines can run in parallel,wait for each other , & communicate with each other
*  coroutine != Thread
*  Coroutines are very very cheap - almost free.Create thousands of them without any memmory issue.
*  Coroutines best for modern applications.*/

/* Main Thread and background thraed run parallel they won't block
*  In case of threads, the application waits for all the background threads to complete. */


/*fun main() {  //Execute in main thread
    println(Thread.currentThread().name) //Main thread Start
    thread {  // create a background thread (worker thread)
        println(Thread.currentThread().name)
        Thread.sleep(1000) //Pretend doing some work...may be file upload
        println(Thread.currentThread().name)
    }
    println(Thread.currentThread().name)//Main thread End
}*/


/* Unline threads , for coroutines the application by default doesn't wait for it to finish its execution*/

/*fun main() {  //Execute in main thread
    println(Thread.currentThread().name) //Main thread Start
    GlobalScope.launch {  // create a background thread (worker thread)
        println(Thread.currentThread().name)
        Thread.sleep(1000) //Pretend doing some work...may be file upload
        println(Thread.currentThread().name)
    }
    println(Thread.currentThread().name)//Main thread End
}*/

//To Make main thread to wait for background thread finsih in Coroutine
/*fun main() {  //Execute in main thread
    println(Thread.currentThread().name) //Main thread Start
    GlobalScope.launch {  // create a background thread (worker thread)
        println(Thread.currentThread().name)
        Thread.sleep(1000)
        println(Thread.currentThread().name)
    }
    //Blocks the current main thread & wait for coroutine to finish (practically not a right way to wait)
    Thread.sleep(2000)
    println(Thread.currentThread().name)//Main thread End
}*/


/*//USING DELAY
fun main() {  //Execute in main thread
    println(Thread.currentThread().name) //Main thread Start
    GlobalScope.launch {  //Thread : T1
        println(Thread.currentThread().name) //Thread : T1
        //Thread.sleep(1000) //IT WILL BLOCK THE THREAD IS NOT SAFE TI USE. HENCE, USE DELAY
        //delay ://SUSPEND THE COROUTINE FOR 1S BUT IT DOESN'T BLOCK THE THREAD
        delay(1000) //Coroutine is suspended but Thread: T1 is free(not blocked)
        println(Thread.currentThread().name) //EITHER T1 OR SOME OTHER THREAD.
    }
    //Blocks the current main thread & wait for coroutine to finish (practically not a right way to wait)
    //Thread.sleep(2000) //IT WILL BLOCK THE THREAD IS NOT SAFE TI USE HENCE USE DELAY
    runBlocking { //CREATE A COROUTINE THAT BLOCKS THE CURRENT MAIN THREAD
        delay(2000) //WAIT FOR COROUTINE TO FINISH (PRACTIALLY NOT A RIGHT WAY TO WAIT)
    }
    println(Thread.currentThread().name)//Main thread End
}*/

/* IMP NOTE:
*  WHAT ARE SUSPENDING FUNCTIONS?
*  A FUNCTION WITH A 'suspend' MODIFIER IS KNOWN AS SUSPENDING FUNCTION
*  THE SUSPENDING FUNCTIONS ARE ONLY ALLOWED TO BE CALLED FROM A COROUTINE OR FROM ANOTHER SUSPENDING FUNCTION
*  THEY CANNOT BE CALLED FROM OUTSIDE A COROUTINE*/

/* GlobalScope.launch & runBlocking THESE BOTH CREATE A NEW COROUTINE RUNS PARALLEL BUT THESE FUNCTION ARE COMPLETLY DIFFERNT
*  GlobalScope.launch() IS NON-BLOCKING IN NATURE
*  runBlocking() IS BLOCKS THE THREAD IN WHICH IT OPERATES.*/


//BLOCKING WITH runBlocking
/*fun main() = runBlocking {  //Execute in main thread //CREATE A COROUTINE THAT BLOCKS THE CURRENT MAIN THREAD
    println(Thread.currentThread().name) //Main thread Start
    GlobalScope.launch {  //Thread : T1
        println(Thread.currentThread().name) //Thread : T1
        //Thread.sleep(1000) //IT WILL BLOCK THE THREAD IS NOT SAFE TI USE. HENCE, USE DELAY
        //delay ://SUSPEND THE COROUTINE FOR 1S BUT IT DOESN'T BLOCK THE THREAD
        delay(1000) //Coroutine is suspended but Thread: T1 is free(not blocked)
        println(Thread.currentThread().name) //EITHER T1 OR SOME OTHER THREAD.
    }
    //Blocks the current main thread & wait for coroutine to finish (practically not a right way to wait)
    //Thread.sleep(2000) //IT WILL BLOCK THE THREAD IS NOT SAFE TI USE HENCE USE DELAY

    delay(2000) //Main thread : WAIT FOR COROUTINE TO FINISH (PRACTIALLY NOT A RIGHT WAY TO WAIT)

    println(Thread.currentThread().name)//Main thread End
}*/




//USING SUSPEND FUNCTION
fun main() = runBlocking {  //Execute in main thread //CREATE A COROUTINE THAT BLOCKS THE CURRENT MAIN THREAD
    println(Thread.currentThread().name) //Main thread Start
    GlobalScope.launch {  //Thread : T1
        println(Thread.currentThread().name) //Thread : T1
        //Thread.sleep(1000) //IT WILL BLOCK THE THREAD IS NOT SAFE TI USE. HENCE, USE DELAY
        //delay ://SUSPEND THE COROUTINE FOR 1S BUT IT DOESN'T BLOCK THE THREAD
        my3SuspendFunc(1000) //Coroutine is suspended but Thread: T1 is free(not blocked)
        println(Thread.currentThread().name) //EITHER T1 OR SOME OTHER THREAD.
    }
    //Blocks the current main thread & wait for coroutine to finish (practically not a right way to wait)
    //Thread.sleep(2000) //IT WILL BLOCK THE THREAD IS NOT SAFE TI USE HENCE USE DELAY
    my3SuspendFunc(2000) //Main thread : WAIT FOR COROUTINE TO FINISH (PRACTIALLY NOT A RIGHT WAY TO WAIT)
    println(Thread.currentThread().name)//Main thread End
}

suspend fun my3SuspendFunc(time:Long){
    //CODE
    delay(time)
}



