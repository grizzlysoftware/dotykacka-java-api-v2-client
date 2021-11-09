package pl.grizzlysoftware.dotykacka.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.function.Function;

public class BatchLoader {
    private int limit;

    public BatchLoader(int limit) {
        this.limit = limit;
    }

    public <T extends Collection<?>> T load(Function<Page, T> f) {
        int size = 0, lastSize = 0, limit = 100, offset = 0;

        var page = new Page();

        var out = new ArrayList<>(10_000);
        while (true) {
            page.limit = limit;
            page.offset = offset;
            var p = f.apply(page);
            out.addAll(p);
            size += p.size();
            if (size == lastSize) {
                break;
            }

            lastSize = size;
            offset += limit;
        }
        return (T) out;
    }
}
