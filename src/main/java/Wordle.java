import java.util.stream.Collectors;
import java.util.stream.IntStream;

public record Wordle(String hiddenAsString) {

    record Guess(String guess) {

        public GuessWithIndex hasLetterAtPosition(int index) {
            return new GuessWithIndex(this, index);
        }

        public int length() {
            return guess.length();
        }

        public int codePointAt(int i) {
            return guess.codePointAt(i);
        }

        public GuessWithIndex checkCharacterAtPosition(int index) {
            return new GuessWithIndex(this, index);
        }
    }

    record Hidden(String hidden, boolean[] used) {

        public Hidden(String hidden) {
            this(hidden, new boolean[hidden.length()]);
        }

        public int codePointAt(int index) {
            return hidden.codePointAt(index);
        }

        public int length() {
            return hidden.length();
        }

        boolean match(GuessWithIndex guessWithIndex, int indexGuess, int indexHidden) {
            if (used[indexHidden]) {
                return false;
            }
            boolean match = guessWithIndex.guess.codePointAt(indexGuess) == codePointAt(indexHidden);
            if (match) {
                used[indexHidden] = true;
            }
            return match;
        }
    }

    record GuessWithIndex(Guess guess, int index) {

        public boolean isWellPlacedIn(Hidden hidden) {
            return hidden.match(this, index, index);
        }

        public boolean isNotWellPlacedIn(Hidden hidden) {

            return IntStream.range(0, hidden.length())
                    .filter(i -> index != i)
                    .filter(i -> guess.codePointAt(i) != hidden.codePointAt(i))
                    .anyMatch(i -> hidden.match(this, index, i));
        }

        public Letter with(Hidden hidden) {
            if (isWellPlacedIn(hidden)) {
                return new WELL_PLACED(guess.codePointAt(index));
            } else if (isNotWellPlacedIn(hidden)) {
                return new NOT_WELL_PLACED(guess.codePointAt(index));
            } else {
                return new ABSENT();
            }
        }
    }

    sealed interface Letter
    permits WELL_PLACED, NOT_WELL_PLACED, ABSENT {}

    record WELL_PLACED(int codePoint) implements Letter {}
    record NOT_WELL_PLACED(int codePoint) implements Letter {}
    record ABSENT() implements Letter {}

    public String guess(String guessAsString) {
        Guess guess = new Guess(guessAsString);
        Hidden hidden = new Hidden(hiddenAsString);

        return IntStream.range(0, guess.length())
                .mapToObj(
                        index -> switch (guess.checkCharacterAtPosition(index). with(hidden)) {
                            case WELL_PLACED letter ->
                                    Character.toString(letter.codePoint()).toUpperCase();
                            case NOT_WELL_PLACED letter ->
                                    Character.toString(letter.codePoint());
                            case ABSENT letter ->
                                    ".";
                        })
                .collect(Collectors.joining());
    }
}
