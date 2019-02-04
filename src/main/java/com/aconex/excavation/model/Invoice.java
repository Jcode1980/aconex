package com.aconex.excavation.model;

import java.util.ArrayList;
import java.util.List;

public class Invoice implements IInvoice{
    private List<IInvoiceLineItem> invoiceItems = new ArrayList<IInvoiceLineItem>();
    private IExcavationJob job;

    public Invoice(IExcavationJob job){this.job = job;}

    @Override
    public List<IInvoiceLineItem> invoiceItems() {
        return invoiceItems;
    }

    @Override
    public IExcavationJob job() {
        return job;
    }
    @Override
    public void addToInvoiceItems(IInvoiceLineItem li){invoiceItems.add(li);}
}
