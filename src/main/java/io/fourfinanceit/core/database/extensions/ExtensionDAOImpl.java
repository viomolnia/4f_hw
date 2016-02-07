package io.fourfinanceit.core.database.extensions;

import io.fourfinanceit.core.database.ExtensionDAO;
import io.fourfinanceit.core.domain.extension.Extension;
import org.springframework.stereotype.Component;

/**
 * Created by Anna on 07.02.2016.
 */
@Component
public class ExtensionDAOImpl extends CRUDOperationDAOImpl<Extension, Long> implements ExtensionDAO {
}
