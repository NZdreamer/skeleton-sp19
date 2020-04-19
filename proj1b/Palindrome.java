public class Palindrome {

    public Deque<Character> wordToDeque(String word) {
        Deque<Character> c = new ArrayDeque<Character>();
        for (int i = 0; i < word.length(); i++) {
            c.addLast(word.charAt(i));
        }
        return c;
    }

    /* determine whether a string is palindrome or not */
    public boolean isPalindrome(String word) {
        Deque<Character> c = wordToDeque(word);
        while (c.size() > 1) {
            if (c.removeFirst() != c.removeLast()) {
                return false;
            }
        }
        return true;
    }

    /* a private helper for isPalindromeRecur */
    private boolean isPalindromeRecur(Deque<Character> c) {
        if (c.size() <= 1) {
            return true;
        }
        if (c.removeFirst() != c.removeLast()) {
            return false;
        }
        return isPalindromeRecur(c);
    }

    /* determine whether a string is palindrome or not in a recursive way */
    public boolean isPalindromeRecur(String word) {
        return isPalindromeRecur(wordToDeque(word));
    }

    /* determine if the word is a an off-by-1 palindrome according to
    the character comparison test provided by cc
     */
    public boolean isPalindrome(String word, CharacterComparator cc) {
        int len = word.length();
        for (int i = 0; i < len / 2; i++) {
            if (!cc.equalChars(word.charAt(i), word.charAt(len - 1 - i))) {
                return false;
            }
        }
        return true;
    }
}
