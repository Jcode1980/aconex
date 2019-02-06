package com.aconex.excavation.service.job;

import com.aconex.excavation.enums.ActionType;
import com.aconex.excavation.enums.CardinalPoint;
import com.aconex.excavation.enums.RotationDirection;
import com.aconex.excavation.model.job.*;

import java.awt.*;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class ExcavationService implements IExcavationService{
    static private ExcavationService excavationService;

    public static ExcavationService excavationService(){
        if(excavationService == null){
            excavationService = new ExcavationService();
        }
        return excavationService;
    }

    @Override
    public IExcavationJob createExcavationJob(String filePath) throws IOException{
        Site newSite = new Site(filePath);
        IExcavator excavator = new Excavator(CardinalPoint.EAST);
        return  new ExcavationJob(newSite, excavator);
    }

    @Override
    public void startExcavationJob(IExcavationJob excavationJob) {
        if(excavationJob == null){throw new NullPointerException("job must not be null");}
        moveAndExcavate(excavationJob, excavationJob.excavator().nextMoveCoordinates());
    }


    //FIXME .. deal with no action types found??
    private IInstruction instructionForCommand(String commmand) throws IllegalArgumentException{
        if(commmand == null){throw new NullPointerException("command parameter not be null");}

        IInstruction instruction;
        List<ActionType> actionTypes = ActionType.actionTypes();
        ActionType actionType =  actionTypes.stream().filter(
                currentAction -> commmand.matches(currentAction.pattern())).findFirst().orElse(null);

        //create instruction
        if(actionType != null){
            instruction = new Instruction(actionType, getUnitsFromCommand(commmand).orElse(null));
        }
        else{
            //FIXME: throw a more meaningfull exception
            throw new IllegalArgumentException("Incorrect command given");
        }

        return instruction;
    }

    private Optional<Integer> getUnitsFromCommand(String command){
        int index = command.lastIndexOf(" ");

        if(index != -1) {
            String unitsStr = command.substring(index + 1);
            System.out.println("the distance is: "  + unitsStr);
            return  Optional.of(Integer.valueOf(unitsStr));
        }
        else{
            return Optional.empty();
        }


    }

    //binds valid commands to actions
    @Override
    public boolean processCommandForJob(String command, IExcavationJob job) {
        if(job == null){throw new NullPointerException("job must not be null");}
        if(command == null){throw new NullPointerException("command parameter must not be null");}

        System.out.println("Processing command: " + command);
        boolean continueJob = true;
        try {
            IInstruction instruction = instructionForCommand(command);

            //adding instruction to instructions list of job.
            job.addToInstructions(instruction);

            ActionType actionType = instruction.actionType();
            switch (actionType) {
                case LEFT:
                    job.excavator().rotate(RotationDirection.LEFT);
                    break;
                case RIGHT:
                    job.excavator().rotate(RotationDirection.RIGHT);
                    break;
                case ADVANCE:
                    continueJob = advanceAction(job, instruction.units().orElse(0));
                    break;
                case QUIT:
                    continueJob = false;

            }
        } catch (IllegalArgumentException e) {
            System.out.println("Unknown command given. Please enter valid command");
            //e.printStackTrace();
        }

        return continueJob;
    }



    private boolean advanceAction(IExcavationJob job, Integer units){


        int i=0;
        while(i < units){
            //check excavators next coordinate
            Point point = job.excavator().nextMoveCoordinates();
            System.out.println("Moving to next move coordinates: " + point);
            if(job.site().coordinatesAreValid(point)){
                System.out.println("point is valid: yes");
                //if Valid.. do the move
                moveAndExcavate(job, point);
            }
            else{
                System.out.println("point is valid: false");
                return false;
            }
            i++;
        }
        return true;
    }


//    private void moveAndExcavate(IExcavationJob job){
//        int totalFuelUsed = job.excavator().move();
//        Point newPoint = job.excavator().nextMoveCoordinates();
//
//        ITerrain terrain = job.site().terrainForCoordinate(newPoint);
//        if(!terrain.hasBeenExcavated()){
//            totalFuelUsed = totalFuelUsed + job.excavator().excavateTerrain(terrain);
//        }
//
//        job.excavator().addToFuelUsed(totalFuelUsed);
//    }

    private void moveAndExcavate(IExcavationJob job, Point point ){
        ITerrain terrain = job.site().terrainForCoordinate(point);
        job.excavator().moveAndExcavate(terrain);

    }

}
