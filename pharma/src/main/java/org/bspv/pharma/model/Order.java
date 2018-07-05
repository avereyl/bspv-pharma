package org.bspv.pharma.model;

import java.io.Serializable;

public final class Order implements Serializable {

    /**
     * Generated Serial UID.
     */
    private static final long serialVersionUID = 7511819088515918000L;

    public Order() {
        // TODO Auto-generated constructor stub
    }
    
    private final UUID id;
    private final LocalDateTime creationDate;
    private final LocalDateTime validationDate;
    private final LocalDateTime receptionDate;
    private final String internalComment;
    private final String externalComment;
    private final Set<OrderItem> items;

}
