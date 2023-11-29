package org.study.port.in;

import java.util.List;

public interface PageCoreIntegration<T> {

    List<T> getContent();

    boolean isEmpty();

    PageableCoreIntegration getPageable();

    long getTotalPages();

    long getTotalElements();
}