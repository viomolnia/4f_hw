package io.fourfinanceit.core.services.extensions;

import io.fourfinanceit.core.domain.extension.Extension;

/**
 * Created by Anna on 07.02.2016.
 */
public interface ExtensionFactory {

    Extension create(int weeksCount, Long loanId);


}

