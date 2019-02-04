package com.aconex.excavation.model;

import java.math.BigDecimal;

public class InvoiceLineItem implements IInvoiceLineItem {
    private CostType costType;
    private Integer units;


    public InvoiceLineItem(CostType costType, Integer units){
        this.costType = costType;
        this.units = units;
    }

    @Override
    public CostType getCostType() {
        return costType;
    }

    @Override
    public Integer creditAmount() {
        return costType.perUnitCost * units;
    }
}
