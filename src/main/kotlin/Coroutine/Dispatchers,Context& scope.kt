package org.example.Kotkin

import kotlinx.coroutines.*

/* CoroutineScope
*  CoroutineContext
*  Dispatchers */

//CoroutineScope
/*
Each coroutine has its own coroutineScope instance attached to it.
launch{ //this:CoroutineScpe
}
async{ //this:CoroutineScpe
}
runBlocking{ //this:CoroutineScpe
}*/

/*fun main(args: Array<String>) = runBlocking {
    println(this)
    launch {
        println(this) //o:p BlockingCoroutine{Active}@277c0f21
        launch {
            println("Child launch : $this") //o:p: Child launch : StandaloneCoroutine{Active}@27082746
        }
    }
    async {
        println(this)//o:p StandaloneCoroutine{Active}@65e579dc
    }
    println("Some code")//o:p DeferredCoroutine{Active}@b065c63
}*/

//===========================================================================================================================================================================================================


/* CoroutineContext
*  It has two major component
*  Dispatcher : it decide on which thread coroutine will execute / Determines the thread of a coroutine.
*  Job*/

fun main(args: Array<String>) = runBlocking {
    /* Without parameter: CONFINED   [CONFINED DISPATCHER]
    *  Inherit CoroutineContext from immediate parent coroutine
    *  Even after delay() or suspending function,it continues to run in the same thread */

    launch {
        println("c1:${Thread.currentThread().name}")   //Thread:main
        delay(1000)
        println("c1:${Thread.currentThread().name}")    //Thread:main
    }

    /*  With parameter : Dispatcher.Default [Similar to GlobalScope.launch{} ]
    *   Gets its own context at Global leval.Execute in a separate background thread.
    *   After delay() or suspending function execution,it continue to run either in the same thread or some other thread*/
    launch(Dispatchers.Default) {
        println("c2:${Thread.currentThread().name}")   //Thread:T1
        delay(1000)
        println("c2 after delay :${Thread.currentThread().name}")   //Thread :t1 or some other Thread
    }


    /*  With parameter: Dispatchers.Unconfined      [UNCONFINED DISPATCHER]
        - Inherits CoroutineContext from the immediate parent coroutine.
        - After delay() or suspending function execution, it continues to run in some other thread.  */
    launch(Dispatchers.Unconfined) {
        println("c3:${Thread.currentThread().name}")   //Thread:main
        delay(1000)
        println("c3 after delay :${Thread.currentThread().name}")  //Thread :some other Thread

    }

    //Using coroutineContext property to flow context from parent to child.
    launch(coroutineContext) {
        println("c4:${Thread.currentThread().name}")   //Thread:main
        delay(1000)
        println("c4 after delay :${Thread.currentThread().name}")    //Thread:main
    }

    println("Main program")
}

/*  In Dispatcher has four 4 type:
*    Default
*    Unconfined
*    Main : It works on the UI Objects
*    IO : Used for IO Operations */



