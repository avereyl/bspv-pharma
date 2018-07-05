package org.bspv.pharma.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public final class Inventory implements Serializable {

    /**
     * Generated Serial UID.
     */
    private static final long serialVersionUID = 3218224950557063655L;

    private final UUID id;
    private final LocalDateTime creationDate;
    private final LocalDateTime closingDate;
    private final Set<StockPosition> positions = new HashSet<>();
    
    private final UUID responsibleUser;
    private final String comment;
    private final Set<StockPosition> positions;
}
