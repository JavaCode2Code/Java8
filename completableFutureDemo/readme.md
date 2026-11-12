# CompletableFuture: `runAsync` vs `supplyAsync`

Both methods execute asynchronous work, but they differ in what kind of task they represent and whether they produce a value.

## `runAsync`

- Accepts a `Runnable`.
- A `Runnable` has no input and does not return a value.
- Returns `CompletableFuture<Void>`.
- `get()` or `join()` returns `null` because there is no result to retrieve.
- Best suited for side effects such as logging, saving data, or sending email.

## `supplyAsync`

- Accepts a `Supplier<U>`.
- A `Supplier<U>` takes no input but produces a value of type `U`.
- Returns `CompletableFuture<U>`.
- `get()` or `join()` returns the computed value.
- Best suited for tasks that compute or fetch data, such as database queries, API calls, or calculations.

## Core difference

`runAsync` is used for fire-and-forget work where the task is an action rather than a computation. It has no meaningful result.

`supplyAsync` is used when the task is expected to produce a value that will be consumed later by the program.

## Implementation notes

- A `Runnable` calls `run()`, which returns no value, so the future is `CompletableFuture<Void>`.
- A `Supplier<U>` calls `get()`, which returns a value, so the future carries that value and can be chained using methods such as `thenApply`, `thenAccept`, and `thenCombine`.
- By default, both run on the common `ForkJoinPool` unless you provide a custom `Executor`.
- If the task throws an exception, both futures complete exceptionally and can be handled using `exceptionally`, `handle`, or by catching `CompletionException` from `join()` / `ExecutionException` from `get()`.
