package com.aconex.excavation.model.job;

import java.util.List;


public interface IExcavationJob {


    IExcavator excavator();
    ISite site();
    List<IInstruction> instructions();
    void addToInstructions(IInstruction instruction);
    List<IInstruction> billableInstructions();


}
