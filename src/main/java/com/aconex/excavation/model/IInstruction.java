package com.aconex.excavation.model;

import com.aconex.excavation.enums.ActionType;

import java.util.Optional;

public interface IInstruction {

    ActionType actionType();
    Optional<Integer> units();

}
