/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.blueprints.services;

import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.persistence.BlueprintNotFoundException;
import edu.eci.arsw.blueprints.persistence.BlueprintPersistenceException;
import edu.eci.arsw.blueprints.persistence.BlueprintsPersistence;
import edu.eci.arsw.blueprints.persistence.impl.InMemoryBlueprintPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Qualifier;


import java.util.Set;

/**
 *
 * @author hcadavid
 */
@Service
public class BlueprintsServices {

    @Autowired
    @Qualifier("Memory")

    BlueprintsPersistence bpp = new InMemoryBlueprintPersistence();
    
    public void addNewBlueprint(Blueprint bp) throws BlueprintPersistenceException {
        bpp.saveBlueprint(bp);
    }
    
    public Set<Blueprint> getAllBlueprints() throws BlueprintNotFoundException{
        Set<Blueprint> allBluePrints = bpp.getAllBlueprint();
        if (allBluePrints.isEmpty()){

            throw new  BlueprintNotFoundException("Not found any Blueprint");
        }else {
            return allBluePrints;

        }

    }
    
    /**
     * 
     * @param author blueprint's author
     * @param name blueprint's name
     * @return the blueprint of the given name created by the given author
     * @throws BlueprintNotFoundException if there is no such blueprint
     */
    public Blueprint getBlueprint(String author,String name) throws BlueprintNotFoundException{
        Blueprint consultBluePrint = bpp.getBlueprint(author,name);
        if (consultBluePrint == null){
            throw new  BlueprintNotFoundException("Not found BluePrint from this author or this Blueprint name");
        }else {
            return consultBluePrint;
        }
    }
    
    /**
     * 
     * @param author blueprint's author
     * @return all the blueprints of the given author
     * @throws BlueprintNotFoundException if the given author doesn't exist
     */
    public Set<Blueprint> getBlueprintsByAuthor(String author) throws BlueprintNotFoundException{
        Set<Blueprint> buePrintByAuthor = bpp.getBlueprintByAuthor(author);
        if (buePrintByAuthor == null){
            throw new  BlueprintNotFoundException("Not found BluePrint from this author or this Blueprint name");
        }else {
            return buePrintByAuthor;
        }
    }
    public Set<Blueprint> getSpecificBlueprint(String author, String bpName) throws BlueprintNotFoundException {
        Set<Blueprint> buePrintByAuthor = bpp.getSpecificBlueprint(author,bpName);
        if (buePrintByAuthor == null){
            throw new  BlueprintNotFoundException("Not found BluePrint from this author or this Blueprint name");
        }else {
            return buePrintByAuthor;
        }
    }


    public void setBluePrint(String author, String bpname) throws BlueprintNotFoundException{
        bpp.setSpecificBlueprint(author,bpname);


    }

    public void updateBluePrint(String author, String bpname, List<Point> points) throws BlueprintNotFoundException {
        Blueprint bp = getBlueprint(author,bpname);
        System.out.println("Before: "+bp.toString());
        bp.setPoints(points);
        System.out.println("After: "+bp.toString());
    }
}
