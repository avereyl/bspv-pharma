package org.bspv.pharma.model;

import java.io.Serializable;

public final class Movement implements Serializable {

    /**
     * Generated Serial UID.
     */
    private static final long serialVersionUID = 3804255031635188617L;

    /**
     * Identifier of this movement.
     */
    private final UUID id;
    private final LocalDateTime createdDate;
    private final Goods goods;
    private final Location location;
    private final Integer quantity;
    
    // reason
    
    private final Order order; // optional
    //user id

}
