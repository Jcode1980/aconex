package com.aconex.excavation.model.invoice;

import com.aconex.excavation.model.job.IExcavationJob;

import java.util.ArrayList;
import java.util.List;

public class Invoice implements IInvoice{
    private List<IInvoiceLineItem> invoiceItems = new ArrayList<>();
    private IExcavationJob job;

    public Invoice(IExcavationJob job){
        if(job == null){throw new NullPointerException("job must not be null");}
        this.job = job;
    }

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

    @Override
    public String costsDisplayString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Item\t\t\t\t Quantity\t\tCost\n");

        for(IInvoiceLineItem invoiceItems : invoiceItems()){
            sb.append(invoiceItems.displayString());
            sb.append("\n");
        }

        return sb.toString();
    }
}
