## Workspace for Middleware Management Microservice

To start the microservice, run the commands below in order

```console
cd Middlware-APD
mvn clean install
mvn exec:java
```

And for the Documentation using [JavaDocs Plugin By Maven](https://maven.apache.org/plugins/maven-javadoc-plugin/usage.html):

- JavaDocs is located in target\reports\apidocs

```console
mvn javadoc:javadoc
```

---

### Testing

The concurrency tests (MiddlewareConcurrencyTest.java) will run during ``` mvn clean install ```.

Currently ```Main.java``` is testing when 10k Tokens are passed into the middleware. The Multi-threaded scenario is set to use 50 threads. Main.java will run the Multi-threaded scenario followed by the Sequential scenario.

After running ``` Main.java ```, Multi-threaded should take less time as compared to Sequential at 10k Tokens. However, as our application is very lightweight, it will only be faster than sequential at very high volumes due also to the overhead time by ThreadExecutor.

As our service is a middleware, it is expected to have high volume and thus the inefficiency at low token volumes is taken into account.
