package org.study.domain.core;

import lombok.RequiredArgsConstructor;
import org.study.port.in.PageCoreIntegration;
import org.study.port.in.PageableCoreIntegration;

import java.util.List;

@RequiredArgsConstructor
public class PageCore<T> implements PageCoreIntegration<T> {
    private final List<T> content;
    private final PageableCoreIntegration pageableCoreIntegration;
    private final long total;

    @Override
    public List<T> getContent() {
        return this.content;
    }

    @Override
    public boolean isEmpty() {
        return this.content.isEmpty();
    }

    @Override
    public PageableCoreIntegration getPageable() {
        return this.pageableCoreIntegration;
    }

    @Override
    public long getTotalPages() {
        return pageableCoreIntegration.getPageSize() == 0 ? 1 : (int) Math.ceil((double) total / (double) pageableCoreIntegration.getPageSize());
    }

    @Override
    public long getTotalElements() {
        return this.total;
    }
}