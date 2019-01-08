/**
 * 
 */
package org.bspv.pharma.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.function.Consumer;

import lombok.Getter;


/**
 * 
 * @author guillaume
 *
 */
public final class Goods implements Serializable {

    /**
     * Generated Serial UID.
     */
    private static final long serialVersionUID = -2516920878572437352L;
    
    /**
     * Identifier of this goods.
     */
    @Getter
    private UUID id;
    @Getter
    private String label;
    @Getter
    private final Set<Tag> tags = new HashSet<>();

    private Goods() {}
    
    
    public static class Builder {
        
    	private final List<Consumer<Goods>> operations = new ArrayList<>();
    	
    	private Builder() {}
        
        

        /**
         * 
         * @return new instance of {@link Goods}
         */
        public Goods build() {
            Goods goods = new Goods();
            operations.forEach(c -> c.accept(goods));
            return goods;
        }
    }


    
    
}
