package code.adapt;

import java.util.List;
import java.util.Map;

interface Processor {
    void analyse(String text);

    List<Map.Entry<String, Integer>> getResults();

    int getProcessedWords();
}