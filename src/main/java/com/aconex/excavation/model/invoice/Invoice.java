package com.aconex.excavation.model.invoice;

import com.aconex.excavation.model.job.IExcavationJob;
import com.sun.deploy.util.StringUtils;

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
        String format = "%-45s%20s%20s%n";
        StringBuilder sb = new StringBuilder();
        sb.append(String.format(format, "Item", "Quantity", "Cost"));

        for(IInvoiceLineItem lineItem : invoiceItems()){
            //sb.append(invoiceItems.displayString());
            sb.append(String.format(format, lineItem.costType().getName(), lineItem.units(), lineItem.creditAmount()));
        }

        return sb.toString();
    }

}
