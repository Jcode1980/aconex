package com.aconex.excavation.controller;

public interface IExcavatorController {
    /**
     * Starts reading the file specified on the command line else
     * if no filepath was passed it, it will read from the default
     * example commands file.
     *
     */
    void recieveInstructions();
}
