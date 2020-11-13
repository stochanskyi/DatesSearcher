package data.searcher;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ConcreteDateMatcher {

    private char divider;

    private List<Integer> maxSlotsNumbers;

    public ConcreteDateMatcher(char divider, List<Integer> maxSlotsNumbers) {
        this.divider = divider;
        this.maxSlotsNumbers = maxSlotsNumbers;
    }

    public char getDivider() {
        return divider;
    }

    public List<Integer> getMaxSlotsNumbers() {
        return maxSlotsNumbers;
    }

    public boolean matches(String value) {
        ArrayList<String> slots = new ArrayList<>();

        var builder = new StringBuilder();

        for (char s : value.toCharArray()) {
            if (s == divider) {
                slots.add(builder.toString());
                builder = new StringBuilder();
                continue;
            } else {
                builder.append(s);
            }
        }
        slots.add(builder.toString());

        if (slots.size() != maxSlotsNumbers.size()) return false;

        for (int i = 0; i < slots.size(); ++i) {
            var currentSlot = slots.get(i);
            var maxSlotSize = maxSlotsNumbers.get(i);
            if (!isNumber(currentSlot) || currentSlot.length() > maxSlotSize) return false;
        }

        return true;
    }

    private boolean isNumber(String text) {
        for (char s : text.toCharArray()) {
            if (!Character.isDigit(s)) return false;
        }

        return true;
    }
}
