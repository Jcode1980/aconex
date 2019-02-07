package com.aconex.excavation.model.invoice;

import com.aconex.excavation.model.CostType;

public interface IInvoiceLineItem {

    Integer creditAmount();
    CostType costType();
    Integer units();

}
