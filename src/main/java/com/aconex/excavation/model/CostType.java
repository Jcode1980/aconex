package com.aconex.excavation.model;

import java.util.ArrayList;
import java.util.List;

public class CostType {
    private String name;
    private Integer perUnitCost;

    public static final String COMMUNICATION_OVERHEAD_TYPE = "communication overhead";
    public static final String FUEL_USAGE_TYPE = "fuel usage";
    public static final String UNCLEARED_SQUARES_TYPE = "uncleared squares";
    public static final String DESTRUCTION_OF_PROTECTED_TREE_TYPE = "destruction of protected tree";
    public static final String PAINT_DAMAGE_TO_BULLDOZERG_TYPE = "paint damage to bulldozer";

    public CostType(String name, Integer perUnitCost){
        this.name = name;
        this.perUnitCost = perUnitCost;
    }

    public String getName(){return name;}
    public Integer getPerUnitCost(){return perUnitCost;}








}

