package com.aconex.excavation.service;

import com.aconex.excavation.model.IExcavationJob;

import java.io.File;

public class ExcavationService implements IExcavationService{
    static private ExcavationService excavationService;

    public static ExcavationService excavationService(){
        if(excavationService == null){
            excavationService = new ExcavationService();
        }
        return excavationService;
    }

    public IExcavationJob createExcavationJob(File FilePath) {
        return null;
    }

    @Override
    public boolean startExcavationJob(IExcavationJob excavationJob) {
        return true;
    }


}
