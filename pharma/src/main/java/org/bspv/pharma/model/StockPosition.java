package org.bspv.pharma.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public final class StockPosition implements Serializable {

    /**
     * Generated Serial UID.
     */
    private static final long serialVersionUID = 7935404557509004867L;
    /**
     * Type of stock position.
     * <ul>
     * <li>LIVE: current stock position
     * <li>CHECKED: checked stocked position
     * <li>COMPUTED: computed stocked position (default) might be used for forecast
     * </ul>
     *
     */
    public enum Type {
        LIVE,
        CHECKED,
        COMPUTED
    }
    /**
     * Identifier of this position.
     */
    private final UUID id;
    
    private final Integer minimum;
    private final Integer maximum;
    private final Integer optimum;
    private final Integer current;
    
    private final Type type;
    
    private final LocalDateTime createdDate;
    private final LocalDateTime lastModifiedDate;
    
    private final Goods goods;
    private final Location location;
    
    private final Set<ExtraInformation> information = new HashSet<>();


}
