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

	public static class Builder {

		private final List<Consumer<Goods>> operations = new ArrayList<>();

		private Builder() {
		}

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

	private Goods() {
	}

	/**
	 * Identifier of this piece of goods.
	 */
	private UUID id;
	private String name;
	private String label;

	private Location defaultLocation;

	private Integer minimumOrderQuantity;
	private Integer maximumOrderQuantity;
	private Integer optimumOrderQuantity;

	private Set<Tag> tags = new HashSet<>();

}
