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

        public boolean thatIsWellPlacedIn(Hidden hidden) {
            return hidden.match(this, index, index);
        }

        public boolean thatIsNotWellPlacedIn(Hidden hidden) {
            for (int i = 0; i < hidden.length(); i++) {
                if (index != i
                        && guess.codePointAt(i) != hidden.codePointAt(i)
                        && hidden.match(this, index, i)) {
                    return true;
                }
            }
            return false;
        }
    }

    public String guess(String guessAsString) {
        String result = "";
        Guess guess = new Guess(guessAsString);
        Hidden hidden = new Hidden(hiddenAsString);
        for (int i = 0; i < guess.length(); i++) {
            if (guess.hasLetterAtPosition(i)
                    .thatIsWellPlacedIn(hidden)) {
                result += Character.toString(guess.codePointAt(i)).toUpperCase();
            } else if (guess.hasLetterAtPosition(i)
                    .thatIsNotWellPlacedIn(hidden)) {
                result += Character.toString(guess.codePointAt(i));
            } else {
                result += ".";
            }
        }
        return result;
    }
}
