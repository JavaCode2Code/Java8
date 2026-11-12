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
