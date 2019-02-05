package com.aconex.excavation.service.job;

import com.aconex.excavation.model.job.IExcavationJob;


public interface IExcavationService {

    IExcavationJob createExcavationJob(String filePath) throws Exception;

    void startExcavationJob(IExcavationJob excavationJob);

    //moves
    boolean processCommandForJob(String command, IExcavationJob excavationJo);



}
