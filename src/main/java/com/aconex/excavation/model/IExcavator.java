package com.aconex.excavation.model;

import com.aconex.excavation.controller.ISiteController;

public interface IExcavator {
    ISiteController excavatorController();

    void recieveInstructions();
}
