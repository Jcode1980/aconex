package com.aconex.excavation.model.job;

import java.util.List;


public interface IExcavationJob {


    /**
     * Retrieve the IExcavator object for the <code>IExcavationJob</code>
     * @return the <code>IExcavator</code>
     */
    IExcavator excavator();

    /**
     * Retrieve the ISite object for the <code>IExcavationJob</code>
     * @return the <code>ISite</code>
     */
    ISite site();

    /**
     * Retrieve the List of instructions for the <code>IExcavationJob</code>
     * @return the <code>List<IInstruction></code>
     */
    List<IInstruction> instructions();


    /**
     * Add an Instruction for the <code>IExcavationJob</code>
     * @param instruction the instruction to be added.
     */
    void addToInstructions(IInstruction instruction);


    /**
     * Retrieve the list of billable instructions for the <code>IExcavationJob</code>
     * @return the <code>List<IInstruction></code>
     */
    List<IInstruction> billableInstructions();


}
