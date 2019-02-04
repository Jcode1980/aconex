package com.aconex.excavation.model;

import java.util.ArrayList;
import java.util.List;

public class ExcavationJob implements  IExcavationJob{
    private ISite site;
    private IExcavator excavator;
    private List<IInstruction> instructions = new ArrayList<>();

    public ExcavationJob(ISite site, IExcavator excavator){
            this.site = site;
            this.excavator = excavator;
    }

    @Override
    public IExcavator excavator() {
        return excavator;
    }

    @Override
    public ISite site() {
        return site;
    }

    @Override
    public List<IInvoiceLineItem> costItems() {
        return null;
    }

    @Override
    public List<IInstruction> instructions() { return instructions; }


}
