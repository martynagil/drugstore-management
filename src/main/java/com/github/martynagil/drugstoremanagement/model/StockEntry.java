package com.github.martynagil.drugstoremanagement.model;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "stock_entry")
public class StockEntry {

    @EmbeddedId
    private StockEntryId stockEntryId;

    @Column(nullable = false)
    private int count;

    @Deprecated
    protected StockEntry() {
    }

    public StockEntry(StockEntryId stockEntryId, int count) {
        this.stockEntryId = stockEntryId;
        this.count = count;
    }

    public StockEntryId getStockEntryId() {
        return stockEntryId;
    }

    public int getCount() {
        return count;
    }
}
