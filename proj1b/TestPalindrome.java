import org.junit.Test;
import static org.junit.Assert.*;

public class TestPalindrome {
    // You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset.
    static Palindrome palindrome = new Palindrome();

    @Test
    public void testWordToDeque() {
        Deque d = palindrome.wordToDeque("persiflage");
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("persiflage", actual);
    }

    @Test
    public void testIsPalindrome() {
        String input1 = "noon";
        String input2 = "a";
        assertTrue(palindrome.isPalindrome(input1));
        assertTrue(palindrome.isPalindrome(input2));

        assertFalse(palindrome.isPalindrome("testsomething"));
        assertFalse(palindrome.isPalindrome("Aa"));
    }

    @Test
    public void testIsPalindromeRecur() {
        assertTrue(palindrome.isPalindromeRecur("noon"));
        assertTrue(palindrome.isPalindromeRecur("a"));
        assertFalse(palindrome.isPalindromeRecur("testsomething"));
        assertFalse(palindrome.isPalindromeRecur("Aa"));
    }

    @Test
    public void testIsPalindromecc() {
        CharacterComparator ccc = new OffByOne();
        assertTrue(palindrome.isPalindrome("flake", ccc));
        assertTrue(palindrome.isPalindrome("a", ccc));
        assertFalse(palindrome.isPalindrome("testsomething", ccc));
        assertFalse(palindrome.isPalindrome("Aa", ccc));

        CharacterComparator cc5 = new OffByN(5);
        CharacterComparator cc7 = new OffByN(7);
        assertTrue(palindrome.isPalindrome("af", cc5));
        assertTrue(palindrome.isPalindrome("bibi", cc7));
        assertFalse(palindrome.isPalindrome("testsomething", cc5));
    }

}
