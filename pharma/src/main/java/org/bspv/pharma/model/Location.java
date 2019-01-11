package org.bspv.pharma.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public final class Location implements Serializable {

    /**
     * Generated Serial UID.
     */
    private static final long serialVersionUID = 4950494554646408104L;

    private Location() {
		// use builder instead
	}
    
    public static final class Builder {
    	
    	private final List<Consumer<Location>> operations = new ArrayList<>();
    	
    	public Location build() {
    		Location location = new Location();
    		this.operations.forEach(c -> c.accept(location));
    		return location;
    	}
    }
    
	public static Builder builder() {
		return new Location.Builder();
	}

}
