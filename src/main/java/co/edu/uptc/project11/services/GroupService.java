package co.edu.uptc.project11.services;

import java.io.FileNotFoundException;
import java.io.IOException;

import com.google.gson.reflect.TypeToken;

import co.edu.uptc.SimpleUptcList.services.SimpleUptcList;
import co.edu.uptc.project11.exceptions.ProjectException;
import co.edu.uptc.project11.exceptions.TypeMessageEnum;
import co.edu.uptc.project11.models.Group;
import co.edu.uptc.project11.persistence.PersistenceJSON;
import co.edu.uptc.project11.persistence.PersistenceProperties;
import co.edu.uptc.project11.utils.MyArrayUtils;

public class GroupService {
    private PersistenceJSON<Group> persistenceJSON;

    public GroupService(){
        String route = PersistenceProperties.read("routeGroups");
        persistenceJSON = new PersistenceJSON<>(route);
    }

    public SimpleUptcList<Group> getGroups() throws ProjectException {
        try {
            SimpleUptcList<Group> groups = loadDates();
            SimpleUptcList<Group> groupsAux = new SimpleUptcList<>();
            for (Group group : groups) {
                groupsAux.add(group);
            }
            return groupsAux;
        } catch (IOException e) {
            throw new ProjectException(TypeMessageEnum.FILE_NOT_FOUND);
        }
    }

    public void addGroup(SimpleUptcList<Group> groups, Group group) throws ProjectException {
        try {
            boolean isAdd = false;
            for (Group groupi : groups) {
                if(groupi.getIdentificationPlace().equalsIgnoreCase(group.getIdentificationPlace())
                    && MyArrayUtils.isEqualsSchedule(groupi.getSchedule(), group.getSchedule())){
                    isAdd = true;
                }
            }
            if(!isAdd){
                groups.add(group);
                saveDates(groups);
            }
        } catch (FileNotFoundException e) {
            throw new ProjectException(TypeMessageEnum.FILE_NOT_FOUND);
        }
    }

    public Group deleteGroup(SimpleUptcList<Group> groups, String identificationPlace, String[] schedule) throws ProjectException {
        Group deleteGroup = new Group();
        for (int i = 0; i < groups.size(); i++) {
            if(groups.get(i).getIdentificationPlace().equalsIgnoreCase(identificationPlace)
                    && MyArrayUtils.isEqualsSchedule(groups.get(i).getSchedule(), schedule)){
                deleteGroup = groups.remove(i);
                try {
                    saveDates(groups);
                } catch (FileNotFoundException e) {
                    throw new ProjectException(TypeMessageEnum.FILE_NOT_FOUND);
                }
            }
        }
        return deleteGroup;
    }

    public SimpleUptcList<String> getCodesSubjectPlace(SimpleUptcList<Group> groups,String identificationPlace) throws ProjectException{
        SimpleUptcList<String> codesSubjectPlace = new SimpleUptcList<>();
        for (Group group : groups) {
            if(group.getIdentificationPlace().equalsIgnoreCase(identificationPlace)){
                codesSubjectPlace.add(group.getSubjectCode());
            }
        }
        return codesSubjectPlace;
    }

    public SimpleUptcList<String> getCodesSubjectMoreOneGroup(SimpleUptcList<Group> groups){
        SimpleUptcList<String> codesSubjectMoreOneGroup = new SimpleUptcList<>();
        SimpleUptcList<String> codes = codesSubjectGroup(groups);
        for (Group group : groups) {
            if(MyArrayUtils.countTimesRepeat(codes, group.getSubjectCode())>1){
                codesSubjectMoreOneGroup.add(group.getSubjectCode());
            }
        }
        return codesSubjectMoreOneGroup;
    }

    public SimpleUptcList<String> codesSubjectGroup(SimpleUptcList<Group> groups){
        SimpleUptcList<String> codes = new SimpleUptcList<>();
        for (Group group : groups) {
            codes.add(group.getSubjectCode());
        }
        return codes;
    }

    public SimpleUptcList<Group> loadDates() throws IOException{
        TypeToken<SimpleUptcList<Group>> listTypeToken = new TypeToken<SimpleUptcList<Group>>() {};
        return persistenceJSON.readDates(listTypeToken);
    }

    public void saveDates(SimpleUptcList<Group> groups) throws FileNotFoundException{
        persistenceJSON.writeDates(groups);
    }

}
