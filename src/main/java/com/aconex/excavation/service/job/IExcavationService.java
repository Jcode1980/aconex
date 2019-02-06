package com.aconex.excavation.service.job;

import com.aconex.excavation.model.job.IExcavationJob;

import java.io.IOException;


public interface IExcavationService {

    IExcavationJob createExcavationJob(String filePath) throws IOException;

    void startExcavationJob(IExcavationJob excavationJob);

    //moves
    boolean processCommandForJob(String command, IExcavationJob excavationJo);



}
