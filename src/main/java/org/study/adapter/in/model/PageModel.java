package org.study.adapter.in.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import org.study.port.in.PageableCoreIntegration;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class PageModel implements PageableCoreIntegration {

    private final int page;
    private final int size;

    public static PageModel of(int page, int size) {
        return new PageModel(page, size);
    }

    @Override
    public int getPageSize() {
        return this.size;
    }

    @Override
    public int getPageNumber() {
        return this.page;
    }

}