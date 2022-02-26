public record Wordle(String hidden) {
 
    public String guess(String guess) {
        String result = "";
        for (int i = 0; i < guess.length(); i++) {
            if (guess.codePointAt(i) == hidden.codePointAt(i)) {
                result += Character.toString(guess.codePointAt(i)).toUpperCase();
            } else {
                result += ".";
            }
        }
        return result;
    }
}
