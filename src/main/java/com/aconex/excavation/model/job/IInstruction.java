package com.aconex.excavation.model.job;

import com.aconex.excavation.enums.ActionType;

import java.util.Optional;

public interface IInstruction {

    /**
     * Retrieve the <code>ActionType</> of the <code>IInstruction</code>
     * @return <code>ActionType</code>
     */
    ActionType actionType();

    /**
     *Retrieve the units of the <code>IInstruction</code>
     *@return <code>Integer</code>
     */
    Optional<Integer> units();

}
