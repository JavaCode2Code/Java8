# CompletableFuture: `runAsync` vs `supplyAsync`

## `runAsync`

- **Takes:** `Runnable` — no input and no return value
- **Returns:** `CompletableFuture<Void>`
- **`get()` / `join()` gives:** `null`
- **Use for:** Side effects such as logging, saving data, or sending an email

## `supplyAsync`

- **Takes:** `Supplier<U>` — no input and returns a value
- **Returns:** `CompletableFuture<U>`
- **`get()` / `join()` gives:** The computed result
- **Use for:** Computing a value, such as a database query, API call, or calculation

## Core difference

Use `runAsync` when the asynchronous task does not need to return a result. Use `supplyAsync` when it needs to produce a value.
Theory behind it
Runnable.run() is void, so a future built from it has nothing to carry. That is why it is CompletableFuture<Void>.
Supplier.get() returns a value, so the future carries that value as its result, and you can chain on it with thenApply, thenAccept, thenCombine, etc.
Both run on ForkJoinPool.commonPool() by default (daemon threads), or on a custom Executor if you pass one as the second argument.
If the task throws, the future completes exceptionally in both cases. You see it via exceptionally, handle, or as a CompletionException from join() (an ExecutionException from get()).
