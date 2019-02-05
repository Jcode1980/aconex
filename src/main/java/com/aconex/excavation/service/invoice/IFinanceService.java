package com.aconex.excavation.service.invoice;

import com.aconex.excavation.model.job.IExcavationJob;
import com.aconex.excavation.model.invoice.IInvoice;

public interface IFinanceService {

    IInvoice invoiceForJob(IExcavationJob job);


}
