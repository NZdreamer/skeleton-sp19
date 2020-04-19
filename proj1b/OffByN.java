public class OffByN implements CharacterComparator {

    private int n;

    public OffByN(int N) {
        n = N;
    }

    @Override
    public boolean equalChars(char a, char b) {
        if (a - n == b || a + n == b) {
            return true;
        }
        return false;
    }
}

