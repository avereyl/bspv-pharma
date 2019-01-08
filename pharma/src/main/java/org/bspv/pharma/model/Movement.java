package org.bspv.pharma.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

import lombok.Getter;

/**
 * 
 * @author guillaume
 *
 */
@Getter
public final class Movement implements Serializable {

    /**
     * Generated Serial UID.
     */
    private static final long serialVersionUID = 3804255031635188617L;
    
    /**
     * Type of movement.
     * <ul>
     * <li>CONSUMPTION: Amount of goods is consumed
     * <li>DESTRUCTION: Amount of goods is destroyed (out of date, out or order...)
     * <li>CORRECTION: Movement for stock position update
     * <li>MOVE: Movement from one location to another (2 movements minimum)
     * <li>ORDER: Amount of goods received by an order
     * <li>FORECAST: Amount of goods planned to be received or consumed
     * <li>UNKNOWN: As it state: unknown
     * </ul>
     *
     */
    public enum MovementType {
    	CONSUMPTION,
    	DESTRUCTION,
    	CORRECTION,
    	MOVE,
    	ORDER,
    	FORECAST,
    	UNKNOWN;
    }

    private UUID id;
    /**
     * Amount of goods moved
     */
    private Integer value;
    /**
     * Type of movement.
     */
    private MovementType type;
    /**
     * Creation date of this movement.
     */
    private LocalDateTime createdDate;
    /**
     * Date of effect for this movement.
     */
    private LocalDateTime valueDate;
    /**
     * Goods concerned.
     */
    private Goods goods;
    /**
     * Location concerned by the movement.
     */
    private Location location;
    
    private Integer quantity;
    
    // reason
    
    private Order order; // optional
    //user id

    
    
    
}
