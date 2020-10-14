<i>Retrofit Parameters</i></p>

<b>@Path</b> – variable substitution for the API endpoint. For example movie id will be swapped for{id} in the URL endpoint.

<b>@Query</b> – specifies the query key name with the value of the annotated parameter.

<b>@Body</b> – payload for the POST call

<b>@Header</b> – specifies the header with the value of the annotated parameter


<img src="https://github.com/arpit999/Images/blob/master/RetrofitRxJavaDemo.png" alt="Retrofit RxJava Demo">


<p>RxJava is all about two key components: <strong>Observable</strong> and <strong>Observer</strong>. In addition to these, there are other things like <strong>Schedulers</strong>, <strong>Operators</strong> and <strong>Subscription</strong>.</p>

<p><b><em>Observable:</em></b> Observable is a data stream that do some work and emits data.</p>
<p><b><em>Observer:</em></b> Observer is the counter part of Observable. It receives the data emitted by Observable.</p>
<p><b><em>Subscription:</em></b> The bonding between Observable and Observer is called as Subscription. There can be multiple Observers subscribed to a single Observable.</p>
<p><b><em>Operator / Transformation:</em></b> Operators modifies the data emitted by Observable before an observer receives them.</p>
<p><b><em>Schedulers:</em></b> Schedulers decides the thread on which Observable should emit the data and on which Observer should receives the data i.e background thread, main thread etc.,</p>

<p> Observer provides the below interface methods to know the the state of Observable.</p>
<ul>
<li><strong>onSubscribe():</strong> Method will be called when an Observer subscribes to Observable.</li>
<li><strong>onNext():</strong> This method will be called when Observable starts emitting the data.</li>
<li><strong>onError():</strong> In case of any error, onError() method will be called.</li>
<li><strong>onComplete():</strong> When an Observable completes the emission of all the items, onComplete() will be called.</li>
</ul>
<i>Below are the list of schedulers available and their brief introduction.<i>

<b>Schedulers.io()</b> – This is used to perform non CPU-intensive operations like making network calls, reading disc / files, database operations etc., This maintains pool of threads.

<b>AndroidSchedulers.mainThread()</b> – This provides access to android Main Thread / UI Thread. Usually operations like updating UI, user interactions happens on this thread. We shouldn’t perform any intensive operations on this thread as it makes the app glitchy or ANR dialog can be thrown.

<b>Schedulers.newThread()</b> – Using this, a new thread will be created each time a task is scheduled. It’s usually suggested not to use schedular unless there is a very long running operation. The threads created via newThread() won’t be reused.

<b>Schedulers.computation()</b> – This schedular can be used to perform CPU-intensive operations like processing huge data, bitmap processing etc., The number of threads created using this scheduler completely depends on number CPU cores available.

<b>Schedulers.single()</b> – This scheduler will execute all the tasks in sequential order they are added. This can be used when there is necessity of sequential execution is required.

<b>Schedulers.immediate()</b> – This scheduler executes the the task immediately in synchronous way by blocking the main thread.

<b>Schedulers.trampoline()</b> – It executes the tasks in First In – First Out manner. All the scheduled tasks will be executed one by one by limiting the number of background threads to one.

<b>Schedulers.from()</b> – This allows us to create a scheduler from an executor by limiting number of threads to be created. When thread pool is occupied, tasks will be queued.

All Observables varies from one another in the number of emission it makes. The below table describes each Observable, its Observer and number of emissions.

<table>
  <tr>
    <th>Observable</th>
    <th>Observer</th>
    <th># of emissions</th>
  </tr>
  <tr>
    <td>Observable</td>
    <td>Observer</td>
    <td>Multiple or None</td>
  </tr>
  <tr>
    <td>Single</td>
    <td>SingleObserver</td>
    <td>One</td>
  </tr>
  <tr>
    <td>Maybe</td>
    <td>MaybeObserver</td>
    <td>One or None</td>
  </tr>
  <tr>
    <td>Flowable</td>
    <td>Observer</td>
    <td>Multiple or None</td>
  </tr>
  <tr>
    <td>Completable</td>
    <td>CompletableObserver</td>
    <td>None</td>
  </tr>
  
</table>

API TO GET MOVIE : http://api.themoviedb.org/3/movie/top_rated?api_key=2b054de6d61b4c4e590162f41c45bd80

API LINK FOR LANGUAGE : https://api.themoviedb.org/3/configuration/languages?api_key=2b054de6d61b4c4e590162f41c45bd80
