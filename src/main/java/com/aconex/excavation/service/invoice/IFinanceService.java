package com.aconex.excavation.service.invoice;

import com.aconex.excavation.model.job.IExcavationJob;
import com.aconex.excavation.model.invoice.IInvoice;

public interface IFinanceService {

    /**
     * Returns the IInvoice for the job.
     *
     * @param job the Job
     * @return <code>IINvoice</code> for the job.
     */

    IInvoice invoiceForJob(IExcavationJob job);


}
