package com.aconex.excavation.service;

import com.aconex.excavation.model.*;

public class FinanceService implements IFinanceService {
    static private ExcavationService excavationService;

    public static ExcavationService excavationService(){
        if(excavationService == null){
            excavationService = new ExcavationService();
        }
        return excavationService;
    }


    @Override
    public IInvoice invoiceForJob(IExcavationJob job) {
        IInvoice invoice = new Invoice(job);

        CostType overHeadCosteType = CostType.costTypeForString(CostType.COMMUNICATION_OVERHEAD_TYPE);
        CostType fuelUsageCostType = CostType.costTypeForString(CostType.FUEL_USAGE_TYPE);
        CostType unclearedSquaresCostType = CostType.costTypeForString(CostType.UNCLEARED_SQUARES_TYPE);
        CostType protectedTreesCostType = CostType.costTypeForString(CostType.DESTRUCTION_OF_PROTECTED_TREE_TYPE);
        CostType paintDamageCostType = CostType.costTypeForString(CostType.PAINT_DAMAGE_TO_BULLDOZERG_TYPE);

        invoice.addToInvoiceItems(new InvoiceLineItem(overHeadCosteType, job.instructions().size()));
        invoice.addToInvoiceItems(new InvoiceLineItem(fuelUsageCostType, job.excavator().fuelUsed()));
        invoice.addToInvoiceItems(new InvoiceLineItem(unclearedSquaresCostType, job.site().nonClearedTerrains().size()));
        invoice.addToInvoiceItems(new InvoiceLineItem(protectedTreesCostType, job.numberOfInstructions()));
        invoice.addToInvoiceItems(new InvoiceLineItem(paintDamageCostType, job.numberOfInstructions()));

    }
}
