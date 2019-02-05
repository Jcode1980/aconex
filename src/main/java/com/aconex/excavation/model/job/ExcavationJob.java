package com.aconex.excavation.model.job;

import com.aconex.excavation.enums.ActionType;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
    public List<IInstruction> instructions() { return instructions; }

    @Override
    public void addToInstructions(IInstruction instruction){instructions.add(instruction);};

    @Override
    public List<IInstruction> billableInstructions() {
       return instructions().stream().filter(i -> i.actionType() != ActionType.QUIT).collect(Collectors.toList());
    }



}
