package com.aconex.excavation.model;

import java.util.ArrayList;
import java.util.List;

public class CostType {
    protected String name;
    protected Integer perUnitCost;

    public static final String COMMUNICATION_OVERHEAD_TYPE = "communication overhead";
    public static final String FUEL_USAGE_TYPE = "fuel usage";
    public static final String UNCLEARED_SQUARES_TYPE = "uncleared squares";
    public static final String DESTRUCTION_OF_PROTECTED_TREE_TYPE = "destruction of protected tree";
    public static final String PAINT_DAMAGE_TO_BULLDOZERG_TYPE = "paint damage to bulldozer";


    static private List<CostType> costTypes = new ArrayList<CostType>();

    public String getName(){return name;}
    public Integer getPerUnitCost(){return perUnitCost;}



    //FIXME move this out somewhere
    static{
        costTypes.add(new CostType(COMMUNICATION_OVERHEAD_TYPE, 1));
        costTypes.add(new CostType(FUEL_USAGE_TYPE, 1));
        costTypes.add(new CostType(UNCLEARED_SQUARES_TYPE, 3));
        costTypes.add(new CostType(DESTRUCTION_OF_PROTECTED_TREE_TYPE, 10));
        costTypes.add(new CostType(PAINT_DAMAGE_TO_BULLDOZERG_TYPE, 2));
    }

    public CostType(String name, Integer perUnitCost){
        this.name = name;
        this.perUnitCost = perUnitCost;
    }

    static public CostType costTypeForString(String label){
        return costTypes.stream().filter(costype -> costype.getName() == label).findFirst()
                .orElseThrow(() ->new IllegalArgumentException());
    }


}
