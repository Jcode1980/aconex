package com.aconex.excavation.model;

import java.util.List;

public interface IExcavationJob {

    IExcavator excavator();
    ISite site();
    List<IInstruction> instructions();
    List<IInstruction> billableInstructions();


}
