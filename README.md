# CodeChallenge

<b>Project Desription:</b> <br>
This app aims to retrieve a list of simple CurrencyInfo object and display them on screen.

<b>Assumption</b> <br>
* Future cloud database integration is out of scope as of now

<b>Feature Description</b> <br>
* Display information
    * When the app is started, the data will be loaded and be displayed on screen in an unsorted order
* Sort currency information
    * The sort button and data order will switch between below state on every click: grey(unsorted), blue A-Z (ASC), and blue Z-A (DESC)
* Refresh currency information
    * For pure demoing purpose, delay() is added to allow users to see how the UI react to Data Loading in case of a large amount of data / slow data-retrieval operation.

<b>Additional Notes</b> <br>
* Considering that it is local-database, it is more efficient to do sorting on SQL level than in Kotlin runtime. If cloud database is to be integrated in the future, it may be better to sort the data programmatically than initiating another call to the cloud.
