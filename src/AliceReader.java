import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.Map.Entry.comparingByValue;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

public class AliceReader {

    public String get_entire_text() {
        String text = new FileReader().asStream("alice.txt")
                .skip(1)
                .collect(Collectors.joining(" "));
        return text;
    }

    public List<String> Particules_élémentaires(String eini) {
        List<String> aussi = new ArrayList<>(Arrays.asList(eini.split(" ")));
        return aussi;
    }

    public Optional<Map.Entry<String, Long>> wordCount(String autor) {
        if (autor == "ham") {
            String mitte = new FileReader().asStream("sms.csv")
                    .skip(1)
                    .filter(e -> e.startsWith("ham;"))
                    .map(n -> n.toString())
                    .collect(Collectors.joining(" "));
            Optional<Map.Entry<String, Long>> aussi =
                    Particules_élémentaires(mitte).stream()
                            .collect(groupingBy(Function.identity(), counting()))
                            .entrySet().stream()
                            .sorted(Map.Entry.<String, Long>comparingByValue(Comparator.reverseOrder())
                                    .thenComparing(Map.Entry.comparingByKey()))
                            .findFirst();
            return aussi;
        }
        if (autor == "spam") {
            String mitte = new FileReader().asStream("sms.csv")
                    .skip(1)
                    .filter(e -> e.startsWith("spam;"))
                    .map(n -> n.toString())
                    .collect(Collectors.joining(" "));
            Optional<Map.Entry<String, Long>> aussi =
                    Particules_élémentaires(mitte).stream()
                            .collect(groupingBy(Function.identity(), counting()))
                            .entrySet().stream()
                            .sorted(Map.Entry.<String, Long>comparingByValue(Comparator.reverseOrder())
                                    .thenComparing(Map.Entry.comparingByKey()))
                            .findFirst();
            return aussi;
        } else {
            throw new RuntimeException(autor + " hat nicht mitgemacht.");
        }
    }
}