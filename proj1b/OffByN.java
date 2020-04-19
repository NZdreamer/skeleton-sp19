public class OffByN implements CharacterComparator {

    private int n;

    public OffByN(int N) {
        n = N;
    }

    @Override
    public boolean equalChars(char a, char b) {
        if (a - 5 == b || a + 5 == b) {
            return true;
        }
        return false;
    }
}

