public record Wordle(String hidden) {

    public String guess(String guess) {
        String result = "";
        for (int i = 0; i < guess.length(); i++) {
            if (guess.codePointAt(i) == hidden.codePointAt(i)) {
                result += Character.toString(guess.codePointAt(i)).toUpperCase();
            } else {
                boolean found = false;
                for (int j = 0; j < hidden.length(); j++) {
                    if (i != j
                            && guess.codePointAt(j) != hidden.codePointAt(j)
                            && guess.codePointAt(i) == hidden.codePointAt(j)) {
                        result += Character.toString(guess.codePointAt(i));
                        found = true;
                    }
                }
                if (!found) {
                    result += ".";
                }
            }
        }
        return result;
    }
}
