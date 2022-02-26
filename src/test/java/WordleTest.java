
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class WordleTest {

    @Test
    void wordle() {

        Assertions.assertThat(new Wordle("aaaaa")
                .guess("bbbbb")).isEqualTo(".....");
        Assertions.assertThat(new Wordle("aaaaa")
                .guess("abbbb")).isEqualTo("A....");
        Assertions.assertThat(new Wordle("aaaaa")
                .guess("babbb")).isEqualTo(".A...");
        Assertions.assertThat(new Wordle("aaaaa")
                .guess("bbabb")).isEqualTo("..A..");
        Assertions.assertThat(new Wordle("aaaaa")
                .guess("bbbab")).isEqualTo("...A.");
        Assertions.assertThat(new Wordle("aaaaa")
                .guess("bbbba")).isEqualTo("....A");

        Assertions.assertThat(new Wordle("abbbb")
                .guess("caccc")).isEqualTo(".a...");
        Assertions.assertThat(new Wordle("abbbb")
                .guess("ccacc")).isEqualTo("..a..");
        Assertions.assertThat(new Wordle("abbbb")
                .guess("cccac")).isEqualTo("...a.");
        Assertions.assertThat(new Wordle("abbbb")
                .guess("cccca")).isEqualTo("....a");

        Assertions.assertThat(new Wordle("bbabb")
                .guess("aaaaa")).isEqualTo("..A..");

        Assertions.assertThat(new Wordle("abbbb")
                .guess("accca")).isEqualTo("A....");
        Assertions.assertThat(new Wordle("abbbb")
                .guess("accaa")).isEqualTo("A....");
        Assertions.assertThat(new Wordle("aabbb")
                .guess("accaa")).isEqualTo("A..a.");
        Assertions.assertThat(new Wordle("aabbb")
                .guess("aacaa")).isEqualTo("AA...");
        Assertions.assertThat(new Wordle("aabbb")
                .guess("cccaa")).isEqualTo("...aa");
    }
}