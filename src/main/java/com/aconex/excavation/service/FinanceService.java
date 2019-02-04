package com.aconex.excavation.service;

import com.aconex.excavation.model.*;

public class FinanceService implements IFinanceService {
    static private FinanceService financeService;

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

        CostType overHeadCostType = CostType.costTypeForString(CostType.COMMUNICATION_OVERHEAD_TYPE);
        CostType fuelUsageCostType = CostType.costTypeForString(CostType.FUEL_USAGE_TYPE);
        CostType unclearedSquaresCostType = CostType.costTypeForString(CostType.UNCLEARED_SQUARES_TYPE);
        CostType protectedTreesCostType = CostType.costTypeForString(CostType.DESTRUCTION_OF_PROTECTED_TREE_TYPE);
        CostType paintDamageCostType = CostType.costTypeForString(CostType.PAINT_DAMAGE_TO_BULLDOZERG_TYPE);

        invoice.addToInvoiceItems(new InvoiceLineItem(overHeadCostType, job.billableInstructions().size()));
        invoice.addToInvoiceItems(new InvoiceLineItem(fuelUsageCostType, job.excavator().fuelUsed()));
        invoice.addToInvoiceItems(new InvoiceLineItem(unclearedSquaresCostType, job.site().nonClearedTerrains().size()));
        invoice.addToInvoiceItems(new InvoiceLineItem(protectedTreesCostType,
                job.site().clearedProtectedTreesTerrains().size()));
        invoice.addToInvoiceItems(new InvoiceLineItem(paintDamageCostType, job.site().clearedRockyTerrains().size()));

        return invoice;

    }
}
