Core difference
runAsync	supplyAsync
Takes	Runnable (no input, no return)	Supplier<U> (no input, returns a value)
Returns	CompletableFuture<Void>	CompletableFuture<U>
get() / join() gives	null	the computed result
Use for	side effects (logging, saving, sending email)	computing a value (DB query, API call, calculation)