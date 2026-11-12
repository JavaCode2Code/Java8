package org.completablefuture;

import java.util.concurrent.CompletableFuture;

public class MultiApiDataFetchDemo {
    public CompletableFuture<String> fetchWheatherData() {
        return CompletableFuture.supplyAsync(() -> {
            simulateDelay(2000); // Simulate network delay
            return "Weather: Sunny, 25°C";
        });
    }

    private void simulateDelay(int milis) {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public CompletableFuture<String> fetchNewsHeadLines() {
        return CompletableFuture.supplyAsync(() -> {
            simulateDelay(3000);
            return "News Java 28 Released!";
        });
    }

    public CompletableFuture<String> fetchStockPrice() {
        return CompletableFuture.supplyAsync(() -> {
            simulateDelay(1500);
            return "Stocks: HCS: 300$,TCS: 350$ ";
        });
    }

    public static void main(String[] args) {
        MultiApiDataFetchDemo multiApi = new MultiApiDataFetchDemo();
        //combine multiple independent future (more than 2) -> allOf(n task)
        //-> weatherDetailsAPI
        CompletableFuture<String> wheatherData = multiApi.fetchWheatherData();
        //-> news apis
        CompletableFuture<String> newsData = multiApi.fetchNewsHeadLines();
        //->Stocks api
        CompletableFuture<String> stocksData = multiApi.fetchStockPrice();
        //wait for all future to complete
        CompletableFuture<Void> allFuture = CompletableFuture.allOf(wheatherData, newsData, stocksData);
        //process results after all future are completed
        allFuture.thenRun(() -> {
            String wheather = wheatherData.join();
            String news = newsData.join();
            String stocks = stocksData.join();
            System.out.println("Aggregate data");
            System.out.println(wheather);
            System.out.println(news);
            System.out.println(stocks);
        }).join();
    }

}
