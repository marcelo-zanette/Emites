# Emites Java Job Application Challenge

## Target

The customer has requested a way to query for movie titles in [IMDB](https://www.imdb.com/).

## Required

* It must respect the Uncle Bob’s Clean Code principles (we expect to understand your code easily).
* It must use Java.
* It must read on a TCP port for requests.
* It must support multiple concurrent accesses.
* The protocol is text, according to: <query length>:<query> (the query length must not consider the :
separator).
* It must not use Spring.
* Unit tests are strictly required.
* The response must respect the request same protocol: <payload length>:<payload>.
* The response payload must be a movie title list separated by LF (\n).
* You must supply the installation and use documentation.
* The code must be shared on [GitHub](http://github.com).

## Wanted (but not required)

* It should use Gradle.
* It should use Java 8.
* It should avoid null-use approaches.
* The response payload should be terminated by the separator (\n) in the last item.

## Suggestions

* For Clean Code, you may start reading [SOLID](http://www.wikiwand.com/en/SOLID).
* For unit tests, you may use [JUnit](https://junit.org/junit5/docs/current/user-guide/).
* For D.I., you may use [Guice](https://github.com/google/guice).
* For dealing with null, you may use [optional](https://docs.oracle.com/javase/8/docs/api/java/util/Optional.html).
* For HTML/XML parsing, you may use SAX or [XPath](https://docs.oracle.com/javase/8/docs/api/javax/xml/xpath/package-summary.html).
