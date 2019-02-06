package com.aconex.excavation.model.job;

import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Site implements ISite{
    private ArrayList<ArrayList<ITerrain>> terrainsMap;

    private Integer width;
    private Integer height;

    public Site(String filePath) throws IOException{
        terrainsMap = createTerrainsMap(filePath);
    }

    @Override
    public String represenationalMap() {
        StringBuilder sb = new StringBuilder();
        for(ArrayList<ITerrain> row : terrainsMap){
            for(ITerrain terrain : row){

                sb.append(terrain.getCode());
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    @Override
    public ITerrain terrainForCoordinate(Point point) {

        return getTerrainsMap().get((int)point.getY()).get((int)point.getX());
    }

    private ArrayList<ArrayList<ITerrain>> getTerrainsMap(){return terrainsMap;}



    private ArrayList<ArrayList<ITerrain>> createTerrainsMap(String filePath) throws IOException{
        ArrayList<ArrayList<ITerrain>> localTerransMap =new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            int y = 0;
            String strCurrentLine;

            while((strCurrentLine = br.readLine()) != null){
                char[] chars  = strCurrentLine.toCharArray();
                ArrayList<ITerrain> localRow = new ArrayList<>();

                int x =0;
                while(x < chars.length){
                    TerrainType terrainType = TerrainType.terrainTypeForChar(chars[x]);
                    //localTerransMap[y][x] = new Terrain(terrainType);
                    ITerrain newTerrain = new Terrain(terrainType);
                    System.out.println("Adding Terrain: " +  newTerrain.getCode() + " to  " + x);
                    localRow.add(newTerrain);
                    x++;
                }

                if(getWidth() == null){ width = x ; }
                else if(getWidth() != x){
                    throw new IllegalArgumentException("Illegal amount of columns");
                }
                localTerransMap.add(localRow);
                y++;
            }
            height = y;
        }
        return localTerransMap;
    }

    @Override
    public boolean coordinatesAreValid(Point point) {
        if(point == null){throw new NullPointerException("point must not be null");}
        return  coordinatesAreWithinBoundary((int)point.getX(), (int)point.getY());
    }

    @Override
    public List<ITerrain> clearedTerrains() {
        return allTerrains().stream().filter(ITerrain::hasBeenExcavated).collect(Collectors.toList());
    }

    @Override
    public List<ITerrain> nonClearedTerrains() {
        return allTerrains().stream().filter(terrain -> !terrain.hasBeenExcavated()).collect(Collectors.toList());
    }

    @Override
    public List<ITerrain> clearedRockyTerrains() {
        return searchClearedTerrainWithTerrainType(TerrainType.ROCKY);
    }

    private List<ITerrain> searchClearedTerrainWithTerrainType(String typeName){
        return clearedTerrains().stream().filter(terrain-> terrain.terrainType().
                getName().equalsIgnoreCase(typeName)).collect(Collectors.toList());
    }

    @Override
    public List<ITerrain> clearedProtectedTreesTerrains() {
        return searchClearedTerrainWithTerrainType(TerrainType.PRESERVED_TREE);
    }

    private List<ITerrain> allTerrains(){
        ArrayList<ITerrain> allTerrains = new ArrayList<>();
        ArrayList<ArrayList<ITerrain>> terrainsMap = getTerrainsMap();

        for(ArrayList<ITerrain> row : terrainsMap){
            //allTerrains.addAll(row.stream().collect(Collectors.toList()));
            allTerrains.addAll(new ArrayList<>(row));
        }

        return allTerrains;
    }

    private boolean coordinatesAreWithinBoundary(int x, int y) {
        return x >= 0 && y >= 0 && getWidth() >= x && getHeight() >= y;
    }

    private Integer getWidth(){return width;}

    private Integer getHeight(){return height;}





}
