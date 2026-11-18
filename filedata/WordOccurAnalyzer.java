package com.filedata;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class WordOccurAnalyzer {
    private static final String folderpath = "C:\\Users\\Sateesh\\Documents\\Spring\\Spring Boot app\\pipeline\\CodingPracticeExample\\data";

    //Get All File from the folder
    public List findNthOccurenceWords(int rank, boolean highest) throws IOException {
        Map<String, Long> wordMap = new HashMap<>();
        List<Path> files = Files.walk(Paths.get(folderpath)).filter(Files::isRegularFile).collect(Collectors.toList());
        //Read each file and directly update or store the frequency
        ExecutorService executor = Executors.newFixedThreadPool(5);
        for (Path file : files) {

            Files.lines(file).flatMap(line -> Arrays.stream(line.split("\\W+")))
                    .forEach(word -> {
                                executor.execute(() -> {
                                    try {

                                   /*    if(!wordMap.containsKey(word)){
                                           wordMap.put(word,1L);
                                       }
                                       else{
                                           wordMap.put(word,wordMap.get(word)+1);
                                       }*/
                                        wordMap.merge(word, 1L, Long::sum);
                                        System.out.println(Thread.currentThread().getName());
                                    } catch (Exception e) {
                                        throw new RuntimeException(e);
                                    }
                                });

                            }
                    );


        }
        //showdown Executor
        executor.shutdownNow();
        System.out.println(wordMap);
        // System.out.println("*********************************************");

        //Handle Ranking By grouping words by their frequency

        Map<Long, List<String>> frequencyToWord = wordMap.entrySet()
                .stream().collect(Collectors.
                        groupingBy(Map.Entry::getValue, Collectors.mapping
                                (Map.Entry::getKey, Collectors.toList())));
        System.out.println(frequencyToWord.isEmpty());

        System.out.println("*********************************************");

        //Sort the frequency based on Search dsc/asc

        List<Long> sortedFrequency = new ArrayList<>(frequencyToWord.keySet());
        sortedFrequency.sort(highest ? Comparator.reverseOrder() : Comparator.naturalOrder());
        System.out.println(sortedFrequency);
        //validation rank
        if (rank <= 0 || rank > sortedFrequency.size()) {
            throw new IllegalArgumentException("rank out of bounds" + rank + " size" + sortedFrequency.size());
        }
//get Frequency corresponding the nth rank
        long targetRank = sortedFrequency.get(rank - 1);
//return the all the words with the nth rank
        return frequencyToWord.getOrDefault(targetRank, Collections.emptyList());


    }

    public static void main(String[] args) throws IOException {
        WordOccurAnalyzer wordAnalyzer = new WordOccurAnalyzer();
        List<String> frequencyWord = wordAnalyzer.findNthOccurenceWords(1, true);
        List<String> frequencyWord2 = wordAnalyzer.findNthOccurenceWords(2, true);
        System.out.println(frequencyWord);
        System.out.println(frequencyWord2);


    }
}
