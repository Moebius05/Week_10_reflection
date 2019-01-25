/*
Which one is the most used letter in ham?
How long is the longest spam message?
Ask two additional questions yourself and test the answers.
Reminder:
Make sure you assert all the answers within a test class

Ham/spam ratio: ham 86% spam 13%
Most used ham word: to
Most used ham letter: e
Longest spam message: 224

 */

import java.util.*;
import java.util.Map.Entry;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.*;

public class Main {

    public static void main(String[] args) {
        System.out.println("Hello World!");
        AliceReader alice_reader = new AliceReader();
        List<String> ListOfWordsInText = alice_reader.Particules_élémentaires(alice_reader.get_entire_text());
        System.out.println(ListOfWordsInText);

        String entiretext = alice_reader.get_entire_text();
        String entireTextLowerCase = entiretext.toLowerCase();
        System.out.println(entireTextLowerCase);
        String entireTextLowerCaseSansKomma = entireTextLowerCase.replaceAll(",", " ");
        String entireTextLowerCaseSansKommaStrichpunkt = entireTextLowerCaseSansKomma.replaceAll(";", " ");
        String sansKommaStrichpunktS = entireTextLowerCaseSansKommaStrichpunkt.replaceAll("’s", " ");
        String sansKommaStrichpunktSHk = sansKommaStrichpunktS.replaceAll("’", " ");
        String sansKommaStrichpunktSHkHk = sansKommaStrichpunktSHk.replaceAll("‘", " ");
        String sansKommaStrichpunktSHkHkDoppelpunkt = sansKommaStrichpunktSHkHk.replaceAll(":", " ");
        String sansKommaStrichpunktSHkHkDoppelpunktRaute = sansKommaStrichpunktSHkHkDoppelpunkt.replaceAll("#", " ");
        String sansKommaStrichpunktSHkHkDoppelpunktRauteSlash = sansKommaStrichpunktSHkHkDoppelpunktRaute.replaceAll("/", " ");
        String sansFirstColumn = sansKommaStrichpunktSHkHkDoppelpunktRauteSlash.replaceAll("-", " ");

        String sansTout = sansFirstColumn
                .replaceAll("\\.", " ")
                .replaceAll("\\*", " ")
                .replaceAll("\\(", " ")
                .replaceAll("\\)", " ")
                .replaceAll("\\)", " ")
                .replaceAll("\\[", " ")
                .replaceAll("\\]", " ");


        System.out.println(sansTout);
        List<String> allWords = alice_reader.Particules_élémentaires(sansTout);
        System.out.println(allWords.size());

        Map<String, Long> countedWords =
                allWords.stream().collect(Collectors.groupingBy(e -> e, Collectors.counting()));
        System.out.println(countedWords);
        System.out.println("Number of distinct words: " + countedWords.size());

        Map<String, Integer> inversed = new HashMap<>();
        Map<Integer, String> mapInversed =
                countedWords.entrySet()
                        .stream()
                        .collect(Collectors.toMap(Map.Entry::getValue, Map.Entry::getKey));

        LinkedHashMap<String,Integer> sortedMap =
                countedWords.entrySet().stream().
                        sorted(Entry.comparingByValue()).
                        collect(Collectors.toMap(Entry::getValue, Entry::getKey,
                                (e1, e2) -> e1, LinkedHashMap::new));
        //        Map<String, Integer> sorted = countedWords.entrySet()
//                .stream()
//                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
//                .collect(Collectors.toMap(,);

        Map<String, Integer> sortedByCount = countedWords.entrySet()
                .stream()
                .sorted((Comparator<? super Entry<String, Long>>) Entry.<String, Integer>comparingByValue().reversed())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
//                .stream()
//                .sorted(Entry.comparingByValue())
//                .collect(Collectors.toMap(Entry::getKey, Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));

                System.out.println(sorted);

    }
}
