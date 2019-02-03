package com.aconex.excavation.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class Site implements ISite{
    ArrayList<ArrayList<ITerrain>> terrainsMap;

    private Integer width;
    private Integer height;

    @Override
    public String represenationalMap() {
        StringBuffer sb = new StringBuffer();
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
        return getTerrainsMap().get(point.getY()).get(point.getX());
    }

    public ArrayList<ArrayList<ITerrain>> getTerrainsMap(){return terrainsMap;}

    public Site(String filePath) throws Exception{
        terrainsMap = createTerrainsMap(filePath);
    }

    private ArrayList<ArrayList<ITerrain>> createTerrainsMap(String filePath) throws Exception{
        ArrayList<ArrayList<ITerrain>> localTerransMap =new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            int y = 0;
            String strCurrentLine;

            while((strCurrentLine = br.readLine()) != null){
                char[] chars  = strCurrentLine.toCharArray();
                ArrayList<ITerrain> localRow = new ArrayList<ITerrain>();

                int x =0;
                while(x < chars.length){
                    TerrainType terrainType = TerrainType.terrainTypeForChar(chars[x]);
                    //localTerransMap[y][x] = new Terrain(terrainType);
                    ITerrain newTerrain = new Terrain(terrainType);
                    System.out.println("Adding Terrain: " +  newTerrain.getCode() + " to  " + x);
                    localRow.add(newTerrain);
                    x++;
                }

                if(getWidth() == null){ width = (x + 1); }
                else if(getWidth() != (x +1)){
                    throw new Exception("Illegal amount of columns");
                }
                localTerransMap.add(localRow);
                y++;
            }
            height = (y+1);
        }
        return localTerransMap;
    }

    @Override
    public boolean coordinatesAreValid(Point point) {
        return  coordinatesAreWithinBoundary(point.getX(), point.getY());

    }

    @Override
    public void excavateCoordinate(Point point) {
        ITerrain terrain = terrainForCoordinate(point);

        if(!terrain.hasBeenExcavated()){
            terrain.excavateTerrain();
        }

    }

    private boolean coordinatesAreWithinBoundary(int x, int y) {
        return x >= 0 && y >= 0 && getWidth() >= x && getHeight() >= y;
    }

    private Integer getWidth(){return width;}

    private Integer getHeight(){return height;}



}
