package com.aconex.excavation.model;

import java.util.List;

public interface IExcavationJob {

    IExcavator excavator();
    ISite site();
    List<ICostItem> costItems();
    List<IInstruction> instructions();


}
