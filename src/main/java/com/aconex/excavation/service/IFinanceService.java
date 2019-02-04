package com.aconex.excavation.service;

import com.aconex.excavation.model.IExcavationJob;
import com.aconex.excavation.model.IInvoice;

public interface IFinanceService {

    IInvoice invoiceForJob(IExcavationJob job);


}
