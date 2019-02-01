package com.aconex.excavation.service;

import com.aconex.excavation.model.IExcavationJob;

import java.io.File;

public interface IExcavationService {

    IExcavationJob createExcavationJob(File filePath);

    boolean startExcavationJob(IExcavationJob excavationJob);

    //moves
    boolean processCommandForJob(String command, IExcavationJob excavationJo);
}
