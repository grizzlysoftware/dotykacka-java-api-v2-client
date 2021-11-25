/*
 * Copyright (c) 2021 Grizzly Software, https://grizzlysoftware.pl
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated
 * documentation files (the "Software"), to deal in the Software without restriction, including without limitation
 * the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and
 * to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING
 * BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY
 * CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE,
 * ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 *
 */

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
