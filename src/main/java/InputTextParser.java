import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

class InputTextParser {
    Map<Character, TreeSet<String>> mapWordsToLetters(String textToBeParsed) {
        if (textToBeParsed == null || textToBeParsed.isEmpty()) {
            throw new IllegalArgumentException("Input text cannot be empty");
        }

        Set<String> uniqueWords = splitTextIntoUniqueWords(textToBeParsed);

        return assignWordsToLetters(uniqueWords);
    }

    private Set<String> splitTextIntoUniqueWords(String textToBeParsed) {
        String[] allWordsFromText = textToBeParsed.split(" ");
        Set<String> uniqueWords = new HashSet<>();

        for (String word : allWordsFromText) {
            int wordStartIndex = 0;
            for (int i = 0; i < word.length(); i++) {
                if (!Character.isLetterOrDigit(word.charAt(i))) {
                    uniqueWords.add(word.substring(wordStartIndex, i).toLowerCase());
                    wordStartIndex = i + 1;
                } else if (i == word.length() - 1) {
                    uniqueWords.add(word.substring(wordStartIndex, i + 1).toLowerCase());
                }
            }
        }

        return uniqueWords;
    }

    private Map<Character, TreeSet<String>> assignWordsToLetters(Set<String> uniqueWords) {
        Map<Character, TreeSet<String>> wordsAssignedToLetters = new HashMap<>();

        for (String word : uniqueWords) {
            for (int i = 0; i < word.length(); i++) {
                char sign = word.charAt(i);
                if (Character.isLetter(sign)) {
                    if (!wordsAssignedToLetters.containsKey(sign)) {
                        wordsAssignedToLetters.put(sign, new TreeSet<>());
                    }

                    wordsAssignedToLetters.get(sign).add(word);
                }
            }
        }

        return wordsAssignedToLetters;
    }
}