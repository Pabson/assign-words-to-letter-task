import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static org.assertj.core.api.BDDAssertions.then;


@RunWith(JUnit4.class)
public class InputTextParserTest {
    private InputTextParser inputTextParser = new InputTextParser();

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void whenMapWordsToLetters_givenNullableInput_thenThrowException() {
        //given
        String inputText = null;

        //then
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Input text cannot be empty");

        //when
        inputTextParser.mapWordsToLetters(inputText);
    }

    @Test
    public void whenMapWordsToLetters_givenEmptyInput_thenThrowException() {
        //given
        String inputText = "";

        //then
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Input text cannot be empty");

        //when
        inputTextParser.mapWordsToLetters(inputText);
    }

    @Test
    public void whenMapWordsToLetters_givenOnlySpecialSings_thenReturnEmptyStructure() {
        //given
        String text = " ,,,,.$%^&* ^&#&%^><<? *.*.*.*";

        //when
        Map<Character, TreeSet<String>> resultStructure = inputTextParser.mapWordsToLetters(text);

        //then
        then(resultStructure).hasSize(0);
    }

    @Test
    public void whenMapWordsToLetters_givenOnlyWhiteSpace_thenReturnEmptyStructure() {
        //given
        String text = "                         ";

        //when
        Map<Character, TreeSet<String>> resultStructure = inputTextParser.mapWordsToLetters(text);

        //then
        then(resultStructure).hasSize(0);
    }

    @Test
    public void whenMapWordsToLetters_givenDifferentCaseOfLettersInWords_thenReturnCorrectStructure() {
        //given
        String inputText = "ala, ala, Ala, ALa, ALA, aLa, aLA, alA";

        //when
        Map<Character, TreeSet<String>> resultStructure = inputTextParser.mapWordsToLetters(inputText);

        //then
        then(resultStructure).hasSize(2);
        List<String> aWords = new ArrayList<>(resultStructure.get('a'));
        List<String> lWords = new ArrayList<>(resultStructure.get('l'));
        then(resultStructure.keySet()).containsSequence('a', 'l');
        then(aWords).isEqualTo(singletonList("ala"));
        then(lWords).isEqualTo(singletonList("ala"));
    }

    @Test
    public void whenMapWordsToLetters_givenInputWithSpecialSigns_thenReturnCorrectStructure() {
        //given
        String text = "john.doe123@gmail.com";

        //when
        Map<Character, TreeSet<String>> resultStructure = inputTextParser.mapWordsToLetters(text);

        //then
        then(resultStructure).hasSize(12);
        List<String> jWords = new ArrayList<>(resultStructure.get('j'));
        List<String> dWords = new ArrayList<>(resultStructure.get('d'));
        List<String> gWords = new ArrayList<>(resultStructure.get('g'));
        List<String> cWords = new ArrayList<>(resultStructure.get('c'));

        then(resultStructure.keySet()).containsSequence('a', 'c', 'd', 'e', 'g', 'h', 'i', 'j', 'l', 'm', 'n', 'o');
        then(jWords).isEqualTo(singletonList("john"));
        then(dWords).isEqualTo(singletonList("doe123"));
        then(gWords).isEqualTo(singletonList("gmail"));
        then(cWords).isEqualTo(singletonList("com"));
    }

    @Test
    public void whenMapWordsToLetters_givenInputStartedBySpecialSings_thenReturnEmptyStructure() {
        //given
        String text = ",,,,.john 123john";

        //when
        Map<Character, TreeSet<String>> resultStructure = inputTextParser.mapWordsToLetters(text);

        //then
        then(resultStructure).hasSize(4);
        List<String> hKeyWords = new ArrayList<>(resultStructure.get('h'));
        List<String> cKeyWords = new ArrayList<>(resultStructure.get('j'));
        List<String> dKeyWords = new ArrayList<>(resultStructure.get('n'));
        List<String> oKeyWords = new ArrayList<>(resultStructure.get('o'));

        then(resultStructure.keySet()).containsSequence('h', 'j', 'n', 'o');
        then(hKeyWords).isEqualTo(asList("123john", "john"));
        then(cKeyWords).isEqualTo(asList("123john", "john"));
        then(dKeyWords).isEqualTo(asList("123john", "john"));
        then(oKeyWords).isEqualTo(asList("123john", "john"));
    }

    @Test
    public void whenMapWordsToLetters_givenInputWithMultipleSpacesAndSpecialSigns_thenReturnCorrectStructure() {
        //given
        String text = "john      /john doe@ j.o.h.n";

        //when
        Map<Character, TreeSet<String>> resultStructure = inputTextParser.mapWordsToLetters(text);

        //then
        List<String> jWords = new ArrayList<>(resultStructure.get('j'));

        then(resultStructure).hasSize(6);
        then(resultStructure.keySet()).containsSequence('d', 'e', 'h', 'j', 'n', 'o');
        then(jWords).isEqualTo(asList("j", "john"));
    }

    @Test
    public void whenMapWordsToLetters_givenInputWithSpecialCharactersAndDoubledWords_thenReturnCorrectStructure() {
        //given
        String text = "ala ma kota, kot koduje w Javie Kota";

        //when
        Map<Character, TreeSet<String>> resultStructure = inputTextParser.mapWordsToLetters(text);

        //then
        then(resultStructure).hasSize(13);
        List<String> aWords = new ArrayList<>(resultStructure.get('a'));
        List<String> dWords = new ArrayList<>(resultStructure.get('d'));
        List<String> eWords = new ArrayList<>(resultStructure.get('e'));
        List<String> iWords = new ArrayList<>(resultStructure.get('i'));
        List<String> jWords = new ArrayList<>(resultStructure.get('j'));
        List<String> kWords = new ArrayList<>(resultStructure.get('k'));
        List<String> lWords = new ArrayList<>(resultStructure.get('l'));
        List<String> mWords = new ArrayList<>(resultStructure.get('m'));
        List<String> oWords = new ArrayList<>(resultStructure.get('o'));
        List<String> tWords = new ArrayList<>(resultStructure.get('t'));
        List<String> uWords = new ArrayList<>(resultStructure.get('u'));
        List<String> vWords = new ArrayList<>(resultStructure.get('v'));
        List<String> wWords = new ArrayList<>(resultStructure.get('w'));

        then(resultStructure.keySet()).containsSequence('a', 'd', 'e', 'i', 'j', 'k', 'l', 'm', 'o', 't', 'u', 'v', 'w');
        then(aWords).isEqualTo(asList("ala", "javie", "kota", "ma"));
        then(dWords).isEqualTo(singletonList("koduje"));
        then(eWords).isEqualTo(asList("javie", "koduje"));
        then(iWords).isEqualTo(singletonList("javie"));
        then(jWords).isEqualTo(asList("javie", "koduje"));
        then(kWords).isEqualTo(asList("koduje", "kot", "kota"));
        then(lWords).isEqualTo(singletonList("ala"));
        then(mWords).isEqualTo(singletonList("ma"));
        then(oWords).isEqualTo(asList("koduje", "kot", "kota"));
        then(tWords).isEqualTo(asList("kot", "kota"));
        then(uWords).isEqualTo(singletonList("koduje"));
        then(vWords).isEqualTo(singletonList("javie"));
        then(wWords).isEqualTo(singletonList("w"));
    }
}
