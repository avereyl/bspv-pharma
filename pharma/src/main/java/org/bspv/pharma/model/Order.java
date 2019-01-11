package org.bspv.pharma.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

public final class Order implements Serializable {

    /**
     * Generated Serial UID.
     */
    private static final long serialVersionUID = 7511819088515918000L;

//    private Order() {
//        // TODO Auto-generated constructor stub
//    }
    
    private UUID id;
    private LocalDateTime creationDate;
    private LocalDateTime validationDate;
    private LocalDateTime receptionDate;
    private String internalComment;
    private String externalComment;
//    private final Set<OrderItem> items = new LinkedHashSet<>();

}
