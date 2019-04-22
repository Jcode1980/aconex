package com.aconex.excavation.service.job;

import com.aconex.excavation.enums.ActionType;
import com.aconex.excavation.enums.CardinalPoint;
import com.aconex.excavation.enums.RotationDirection;
import com.aconex.excavation.model.job.*;

import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ExcavationService implements IExcavationService{
    static private ExcavationService excavationService;
    private List<TerrainType> terrainTypes;

    public static ExcavationService excavationService(){
        if(excavationService == null){
            excavationService = new ExcavationService();
        }
        return excavationService;
    }

    @Override
    public IExcavationJob createExcavationJob(String filePath) throws IOException{

        Site newSite = new Site(createTerrainsMap(filePath));
        IExcavator excavator = new Excavator(CardinalPoint.EAST);

        //Setting starting point to be top left of the site
        //excavator.place(new Point(-1, (newSite.getHeight()-1)));
        excavator.place(new Point(-1, 0));
        return  new ExcavationJob(newSite, excavator);
    }

//    @Override
//    public void startExcavationJob(IExcavationJob excavationJob) {
//        if(excavationJob == null){throw new NullPointerException("job must not be null");}
//        moveAndExcavate(excavationJob, excavationJob.excavator().nextMoveCoordinates());
//    }


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
            //System.out.println("the distance is: "  + unitsStr);
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

        //System.out.println("Processing command: " + command);
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
            //System.out.println("Moving to next move coordinates: " + point);
            if(job.site().coordinatesAreValid(point)){
                //System.out.println("point is valid: yes");
                //if Valid.. do the move
                moveAndExcavate(job, point);
            }
            else{
                System.out.println("******* Error. Exavator is trying to move to an invalid spot. Simulation Terminated********");
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

    public TerrainType terrainTypeForChar(char c){
        return terrainTypes().stream().filter(terrain -> terrain.getCode() == c).findFirst().orElseThrow(() ->
                new IllegalArgumentException("terrain with code: " + c + " not found"));
    }

    private List<TerrainType> terrainTypes(){

        if(terrainTypes == null) {
            terrainTypes = new ArrayList<>();
            terrainTypes.add(new TerrainType(TerrainType.PLAIN, TerrainType.PLAIN_CODE, 0));
            terrainTypes.add(new TerrainType(TerrainType.ROCKY, TerrainType.ROCKY_CODE, 1));
            terrainTypes.add(new TerrainType(TerrainType.REMOVABLE_TREE, TerrainType.REMOVABLE_TREE_CODE, 1));
            terrainTypes.add(new TerrainType(TerrainType.PRESERVED_TREE, TerrainType.PRESERVED_TREE_CODE, 1));
        }

        return terrainTypes;
    }

    public ArrayList<ArrayList<ITerrain>> createTerrainsMap(String filePath) throws IOException{
        ArrayList<ArrayList<ITerrain>> localTerransMap =new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {

            String strCurrentLine;

            while ((strCurrentLine = br.readLine()) != null) {
                char[] chars = strCurrentLine.toCharArray();
                ArrayList<ITerrain> localRow = new ArrayList<>();

                int x = 0;
                while (x < chars.length) {
                    TerrainType terrainType = terrainTypeForChar(chars[x]);

                    ITerrain newTerrain = new Terrain(terrainType);
                    //System.out.println("Adding Terrain: " +  newTerrain.getCode() + " to  " + x);
                    localRow.add(newTerrain);
                    x++;
                }
                localTerransMap.add(localRow);
            }
        }
        return localTerransMap;
    }




}
