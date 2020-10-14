<i>Retrofit Parameters</i></p>

<b>@Path</b> – variable substitution for the API endpoint. For example movie id will be swapped for{id} in the URL endpoint.

<b>@Query</b> – specifies the query key name with the value of the annotated parameter.

<b>@Body</b> – payload for the POST call

<b>@Header</b> – specifies the header with the value of the annotated parameter


<img src="https://github.com/arpit999/Images/blob/master/RetrofitRxJavaDemo.png" alt="Retrofit RxJava Demo">

<i>Below are the list of schedulers available and their brief introduction.<i>

<b>Schedulers.io()</b> – This is used to perform non CPU-intensive operations like making network calls, reading disc / files, database operations etc., This maintains pool of threads.

<b>AndroidSchedulers.mainThread()</b> – This provides access to android Main Thread / UI Thread. Usually operations like updating UI, user interactions happens on this thread. We shouldn’t perform any intensive operations on this thread as it makes the app glitchy or ANR dialog can be thrown.

<b>Schedulers.newThread()</b> – Using this, a new thread will be created each time a task is scheduled. It’s usually suggested not to use schedular unless there is a very long running operation. The threads created via newThread() won’t be reused.

<b>Schedulers.computation()</b> – This schedular can be used to perform CPU-intensive operations like processing huge data, bitmap processing etc., The number of threads created using this scheduler completely depends on number CPU cores available.

<b>Schedulers.single()</b> – This scheduler will execute all the tasks in sequential order they are added. This can be used when there is necessity of sequential execution is required.

<b>Schedulers.immediate()</b> – This scheduler executes the the task immediately in synchronous way by blocking the main thread.

<b>Schedulers.trampoline()</b> – It executes the tasks in First In – First Out manner. All the scheduled tasks will be executed one by one by limiting the number of background threads to one.

<b>Schedulers.from()</b> – This allows us to create a scheduler from an executor by limiting number of threads to be created. When thread pool is occupied, tasks will be queued.


API TO GET MOVIE : http://api.themoviedb.org/3/movie/top_rated?api_key=2b054de6d61b4c4e590162f41c45bd80

API LINK FOR LANGUAGE : https://api.themoviedb.org/3/configuration/languages?api_key=2b054de6d61b4c4e590162f41c45bd80
