package com.aconex.excavation.service.job;

import com.aconex.excavation.model.job.IExcavationJob;

import java.io.IOException;


public interface IExcavationService {

    /**
     *
     * @param filePath <code>String</code> representation of the site map
     * @return <code>IExcavationJob</code> the job
     * @throws IOException
     */
    IExcavationJob createExcavationJob(String filePath) throws IOException;

//    void startExcavationJob(IExcavationJob excavationJob);

    /**
     * Processes the command for the given <code>IExcavationJob</code>
     * @param command The command to be applied to the job
     * @param excavationJo the job
     * @return
     */
    boolean processCommandForJob(String command, IExcavationJob excavationJo);



}
