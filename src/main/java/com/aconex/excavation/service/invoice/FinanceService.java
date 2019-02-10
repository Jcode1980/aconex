package com.aconex.excavation.service.invoice;

import com.aconex.excavation.model.*;
import com.aconex.excavation.model.invoice.IInvoice;
import com.aconex.excavation.model.invoice.Invoice;
import com.aconex.excavation.model.invoice.InvoiceLineItem;
import com.aconex.excavation.model.job.IExcavationJob;
import com.aconex.excavation.service.job.ExcavationService;

import java.util.ArrayList;
import java.util.List;

public class FinanceService implements IFinanceService {
    static private FinanceService financeService;

    private List<CostType> costTypes;

    public static FinanceService financeService(){
        if(financeService == null){
            financeService = new FinanceService();
        }
        return financeService;
    }

    @Override
    public IInvoice invoiceForJob(IExcavationJob job) {
        if(job == null){throw new NullPointerException("job must not be null");}

        IInvoice invoice = new Invoice(job);

        CostType overHeadCostType = costTypeForString(CostType.COMMUNICATION_OVERHEAD_TYPE);
        CostType fuelUsageCostType = costTypeForString(CostType.FUEL_USAGE_TYPE);
        CostType unclearedSquaresCostType = costTypeForString(CostType.UNCLEARED_SQUARES_TYPE);
        CostType protectedTreesCostType = costTypeForString(CostType.DESTRUCTION_OF_PROTECTED_TREE_TYPE);
        CostType paintDamageCostType = costTypeForString(CostType.PAINT_DAMAGE_TO_BULLDOZERG_TYPE);

        invoice.addToInvoiceItems(new InvoiceLineItem(overHeadCostType, job.billableInstructions().size()));
        invoice.addToInvoiceItems(new InvoiceLineItem(fuelUsageCostType, job.excavator().fuelUsed()));
        invoice.addToInvoiceItems(new InvoiceLineItem(unclearedSquaresCostType, job.site().nonClearedTerrains().size()));
        invoice.addToInvoiceItems(new InvoiceLineItem(protectedTreesCostType,
                job.site().clearedProtectedTreesTerrains().size()));
        invoice.addToInvoiceItems(new InvoiceLineItem(paintDamageCostType, job.site().clearedRockyTerrains().size()));

        return invoice;
    }

    public CostType costTypeForString(String label){
        if(label == null){ throw new RuntimeException("label must not be null"); }

        return costTypes().stream().filter(costype -> costype.getName().equals(label) ).findFirst()
                .orElseThrow(() ->new IllegalArgumentException("Could not find cost type with label: " + label));
    }


    private List<CostType> costTypes(){
        if(costTypes == null){
            costTypes = new ArrayList<>();
            costTypes.add(new CostType(CostType.COMMUNICATION_OVERHEAD_TYPE, 1));
            costTypes.add(new CostType(CostType.FUEL_USAGE_TYPE, 1));
            costTypes.add(new CostType(CostType.UNCLEARED_SQUARES_TYPE, 3));
            costTypes.add(new CostType(CostType.DESTRUCTION_OF_PROTECTED_TREE_TYPE, 10));
            costTypes.add(new CostType(CostType.PAINT_DAMAGE_TO_BULLDOZERG_TYPE, 2));
        }

        return costTypes;
    }



}
