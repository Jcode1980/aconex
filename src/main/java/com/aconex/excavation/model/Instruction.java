package com.aconex.excavation.model;

import com.aconex.excavation.enums.ActionType;

import java.util.Optional;

public class Instruction implements IInstruction {
    private ActionType actionType;
    private Optional<Integer> units;

    public Instruction(ActionType actionType, Optional<Integer> units){
        if(actionType == null){throw new NullPointerException("action type must not be null");}

        this.actionType = actionType;
        this.units = units;
    }

    @Override
    public ActionType actionType() {
        return actionType;
    }

    @Override
    public Optional<Integer> units() {
        return units;
    }

    public String toString(){
        return actionType.toString() + " " +(units.orElse(null));


    }
}
