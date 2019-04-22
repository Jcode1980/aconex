package com.aconex.excavation.service.job;

import com.aconex.excavation.model.job.IExcavationJob;
import com.aconex.excavation.model.job.ITerrain;

import java.io.IOException;
import java.util.ArrayList;


public interface IExcavationService {

    /**
     * @param filePath <code>String</code> representation of the site map
     * @return <code>IExcavationJob</code> the job
     * @throws IOException
     */
    IExcavationJob createExcavationJob(String filePath) throws IOException;


    /**
     * Processes the command for the given <code>IExcavationJob</code>
     *
     * @param command      The command to be applied to the job
     * @param excavationJo the job
     * @return <code>boolean</code>
     */
    boolean processCommandForJob(String command, IExcavationJob excavationJo);


    /**
     * Processes the command for the given <code>IExcavationJob</code>
     *
     * @param filePath      The filePath of the file that represents a map
     * @return ArrayList represenation of a map
     */
    ArrayList<ArrayList<ITerrain>> createTerrainsMap(String filePath) throws IOException;
}
