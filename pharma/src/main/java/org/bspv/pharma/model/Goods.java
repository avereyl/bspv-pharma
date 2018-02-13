/**
 * 
 */
package org.bspv.pharma.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;


/**
 * @author guillaume
 *
 */
public final class Goods implements Serializable {

    /**
     * Generated Serial UID.
     */
    private static final long serialVersionUID = -2516920878572437352L;
    
    /**
     * Builder class for {@link EVoucher}.
     * 
     */
    public static class Builder {
        /**
         * Constructor of the builder.
         * 
         */
        private Builder() {
            super();
        }

        /**
         * build the goods calling the constructor or the Goods class.
         * 
         * @return new instance of {@link Goods}
         */
        public Goods build() {
            return new Goods(this);
        }
    }

    /**
     * 
     */
    private Goods(Builder builder) {
    }

    private final Set<Tag> tags = new HashSet<>();
    
}
