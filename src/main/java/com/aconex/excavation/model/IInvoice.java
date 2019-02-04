package com.aconex.excavation.model;

import java.util.List;

public interface IInvoice {
    List<IInvoiceLineItem> invoiceItems();
    IExcavationJob job();

    void addToInvoiceItems(IInvoiceLineItem li);


}
