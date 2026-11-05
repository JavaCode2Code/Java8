# Java 8 features

## Differences between Java 8 map() and flatMap()

| map() | flatMap() |
|-------|-----------|
| It processes a stream of values. | It processes a stream of streams of values. |
| It does only mapping. | It performs mapping and flattening. |
| The mapper function produces a single value for each input value. | The mapper function produces multiple values for each input value. |
| It is a one-to-one mapping. | It is a one-to-many mapping. |
| Data transformation: Stream<T> -> Stream<R> | Data transformation: Stream<T> -> Stream<R> after flattening nested streams |
| Use this method when the mapper function produces a single value for each input element. | Use this method when the mapper function produces multiple values for each input element. |

### Example

```java
List<String> names = Arrays.asList("A", "B", "C");

List<String> mapped = names.stream()
    .map(String::toLowerCase)
    .collect(Collectors.toList());
// Output: [a, b, c]

List<String> flattened = names.stream()
    .flatMap(name -> Arrays.stream(name.split("")))
    .collect(Collectors.toList());
// Output: [A, B, C]
```

### Summary

- map() transforms each element individually.
- flatMap() transforms each element into multiple elements and then flattens them into a single stream.
- map() is suitable for one-to-one transformation.
- flatMap() is suitable for one-to-many transformation.
