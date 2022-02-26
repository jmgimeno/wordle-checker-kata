public record Wordle(String hidden) {

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

    record GuessWithIndex(Guess guess, int index) {

        public boolean thatIsWellPlacedIn(String hidden) {
            return guess.codePointAt(index) == hidden.codePointAt(index);
        }

        public boolean thatIsNotWellPlacedIn(String hidden) {
            for (int i = 0; i < hidden.length(); i++) {
                if (index != i
                        && guess.codePointAt(index) == hidden.codePointAt(i)) {
                    return true;
                }
            }
            return false;
        }
    }

    public String guess(String guessAsString) {
        String result = "";
        Guess guess = new Guess(guessAsString);
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
