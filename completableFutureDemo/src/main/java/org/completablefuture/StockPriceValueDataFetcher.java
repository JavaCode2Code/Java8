package org.completablefuture;

import java.util.concurrent.CompletableFuture;

public class StockPriceValueDataFetcher {
    public CompletableFuture<Double> fetchStockPriceApi1(String stock) {
        return CompletableFuture.supplyAsync(() -> {
            simulateDelay(1000);
            return 180.0;
        });
    }

    private void simulateDelay(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public CompletableFuture<Double> fetchStockPriceApi2(String stock) {
        return CompletableFuture.supplyAsync(() -> {
            simulateDelay(2000);
            return 198.0;
        });
    }

    public static void main(String[] args) {
        StockPriceValueDataFetcher sPValueFetcher = new StockPriceValueDataFetcher();
        String symbolStock = "TCS";
        //fetch stock price from both the API
        CompletableFuture<Double> api1result = sPValueFetcher.fetchStockPriceApi1(symbolStock);

        CompletableFuture<Double> api2result = sPValueFetcher.fetchStockPriceApi2(symbolStock);

        //Use anyOf to wait any of the future to complete
        CompletableFuture<Object> anyOfResult = CompletableFuture.anyOf(api1result, api2result);

        anyOfResult.thenAccept(price->{
            System.out.println("Recived stock price :$ "+price);
        }).join();
    }
}
