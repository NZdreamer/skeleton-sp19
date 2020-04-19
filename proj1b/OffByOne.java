public class OffByOne implements CharacterComparator {

    @Override
    public boolean equalChars(char a, char b) {
        if (a - 1 == b || a + 1 == b) {
            return true;
        }
        return false;
    }

}
