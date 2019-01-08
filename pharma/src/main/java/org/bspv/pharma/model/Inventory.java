package org.bspv.pharma.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import lombok.Getter;

public final class Inventory implements Serializable {

    /**
     * Generated Serial UID.
     */
    private static final long serialVersionUID = 3218224950557063655L;


    @Getter
    private UUID id;
    private LocalDateTime creationDate;
    private LocalDateTime closingDate;

    private final Set<StockPosition> positions = new HashSet<>();
    
    private UUID responsibleUser;
    private String comment;
}
