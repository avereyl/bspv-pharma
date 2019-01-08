package org.bspv.pharma.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.function.Consumer;

import lombok.Getter;
/**
 * This class indicates for a {@link Location} and {@link Goods} the number of elements at a given time.
 * This position could have been checked by a user or is computed.
 * 
 * @author guillaume
 *
 */
@Getter
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
    public enum StockPostitionType {
        LIVE,
        CHECKED,
        COMPUTED
    }
    /**
     * Identifier of this position.
     */
    private UUID id;
    
    private Integer minimum;
    private Integer maximum;
    private Integer optimum;
    private Integer current;
    
    private StockPostitionType type;
    
    private LocalDateTime createdDate;
    private LocalDateTime valueDate;
    
    private Goods goods;
    private Location location;
    
    private final Set<ExtraInformation> information = new HashSet<>();
    
    private final static class Builder {
    	
        private Builder() {}
    	private List<Consumer<StockPosition>> operations = new ArrayList<>();
    	
    	public StockPosition build() {
    		StockPosition stockPosition = new StockPosition();
    		this.operations.forEach(c -> c.accept(stockPosition));
    		return stockPosition;
    	}
    	
    	public Builder movement(Movement movement) {
    		//TODO take care of the valueDate ?
    		return this;
    	}
    	
    	public Builder from(StockPosition stockPosition) {
    		//TODO
    		return this;
    	}
    }
    
    public static final StockPosition.Builder builder() {
    	return new StockPosition.Builder();
    }
    
    /**
     * Should use the builder instead.
     */
    private StockPosition() {}

    public StockPosition computeNewPosition(final List<Movement> movements) {
    	StockPosition.Builder builder = StockPosition.builder().from(this);
    	movements.forEach(builder::movement);
    	return builder.build();
    }
    
}
