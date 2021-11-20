package pl.grizzlysoftware.dotykacka.util;

import pl.grizzlysoftware.dotykacka.client.v2.model.ResultPage;

import java.util.ArrayList;
import java.util.Collection;
import java.util.function.Function;

public class BatchLoader {
    private final int pageSize;

    public BatchLoader(int pageSize) {
        this.pageSize = pageSize;
    }

    public <T, F extends ResultPage<T>> Collection<T> load(Function<Page, F> f) {
        final var page = new Page();
        page.page = 1;
        page.pageSize = pageSize;
        final var out = new ArrayList<T>(10_000);
        while (true) {
            final var p = f.apply(page);
            out.addAll(p.data);
            if (p.page == p.lastPage) {
                break;
            }
            page.page++;
        }
        return out;
    }
}
