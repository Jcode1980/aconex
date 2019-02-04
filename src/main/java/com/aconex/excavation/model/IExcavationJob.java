package com.aconex.excavation.model;

import java.util.List;

public interface IExcavationJob {

    IExcavator excavator();
    ISite site();
    List<IInvoiceLineItem> costItems();
    List<IInstruction> instructions();


}
