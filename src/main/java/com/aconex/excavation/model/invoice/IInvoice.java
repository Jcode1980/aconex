package com.aconex.excavation.model.invoice;

import com.aconex.excavation.model.job.IExcavationJob;

import java.util.List;

public interface IInvoice {
    List<IInvoiceLineItem> invoiceItems();
    IExcavationJob job();

    void addToInvoiceItems(IInvoiceLineItem li);

    String costsDisplayString();

}
