package com.github.martynagil.drugstoremanagement.model;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class StockEntryId implements Serializable {

	@ManyToOne
	@JoinColumn(name = "product_id")
	private Product product;

	@ManyToOne
	@JoinColumn(name = "shop_id")
	private Shop shop;

	@Deprecated
	protected StockEntryId() {
	}

	public StockEntryId(Product product, Shop shop) {
		this.product = product;
		this.shop = shop;
	}

	public Product getProduct() {
		return product;
	}

	public Shop getShop() {
		return shop;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		StockEntryId that = (StockEntryId) o;
		return product.equals(that.product) &&
				shop.equals(that.shop);
	}

	@Override
	public int hashCode() {
		return Objects.hash(product, shop);
	}
}
