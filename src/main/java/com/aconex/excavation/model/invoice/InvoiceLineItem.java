package com.aconex.excavation.model.invoice;

import com.aconex.excavation.model.CostType;

import java.awt.*;

public class InvoiceLineItem implements IInvoiceLineItem {
    private CostType costType;
    private Integer units;


    public InvoiceLineItem(CostType costType, Integer units){
        if(costType == null){throw new NullPointerException("costType must not be null");}
        if(units == null){throw new NullPointerException("units must not be null");}

        this.costType = costType;
        this.units = units;
    }

    @Override
    public Integer creditAmount() {
        return costType.getPerUnitCost() * units;
    }

    @Override
    public String displayString(){
        return costType.getName() + "\t\t\t\t" + units + "\t\t" + creditAmount();
    }

    public CostType costType(){ return costType; }

    public Integer units(){ return units;}
}
