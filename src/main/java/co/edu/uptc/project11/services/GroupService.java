package co.edu.uptc.project11.services;

import co.edu.uptc.SimpleUptcList.services.SimpleUptcList;
import co.edu.uptc.project11.models.Group;
import co.edu.uptc.project11.utils.DataListsUtils;
import co.edu.uptc.project11.utils.MyArrayUtils;

public class GroupService {

    public SimpleUptcList<Group> getGroups() {
        SimpleUptcList<Group> groups = loadDates();
        SimpleUptcList<Group> groupsAux = new SimpleUptcList<>();
        for (Group group : groups) {
            groupsAux.add(group);
        }
        return groupsAux;
    }

    public void addGroup(SimpleUptcList<Group> groups, Group group) {
        boolean isAdd = false;
        for (Group groupi : groups) {
            if(groupi.getIdentificationPlace().equalsIgnoreCase(group.getIdentificationPlace())
                && MyArrayUtils.isEqualsSchedule(groupi.getSchedule(), group.getSchedule()) 
                && groupi.getNumber() == group.getNumber() 
                && groupi.getSubjectCode().equalsIgnoreCase(group.getSubjectCode())){
                    isAdd = true;
            }
        }
        if(!isAdd){
            groups.add(group);
            saveDates(groups);
        }
    }

    public Group deleteGroup(SimpleUptcList<Group> groups, String identificationPlace, String[] schedule)  {
        Group deleteGroup = new Group();
        for (int i = 0; i < groups.size(); i++) {
            if(groups.get(i).getIdentificationPlace().equalsIgnoreCase(identificationPlace)
                    && MyArrayUtils.isEqualsSchedule(groups.get(i).getSchedule(), schedule)){
                deleteGroup = groups.remove(i);
                saveDates(groups);
            }
        }
        return deleteGroup;
    }

    public Group setGroup(SimpleUptcList<Group> groups, int number, String subjectCode, Group newGroup) {
        Group group = new Group();
        for (int i = 0; i < groups.size(); i++) {
            if(groups.get(i).getNumber() == number && groups.get(i).getSubjectCode().equalsIgnoreCase(subjectCode)){
                group = groups.set(i, newGroup);
                saveDates(groups);
            }
        }
        return group;
    }

    public SimpleUptcList<String> getCodesSubjectPlace(SimpleUptcList<Group> groups,String identificationPlace) {
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

    public SimpleUptcList<String> getCodesSubjectSameSchedule(SimpleUptcList<Group> groups, String[] schedule) {
        SimpleUptcList<String> codesSubjectSameSchedule = new SimpleUptcList<>();
        for (Group group : groups) {
            if(MyArrayUtils.isEqualsSchedule(group.getSchedule(), schedule)){
                codesSubjectSameSchedule.add(group.getSubjectCode());
            }
        }
        return codesSubjectSameSchedule;
    }

    public SimpleUptcList<Group> loadDates() {
        return DataListsUtils.groups;
    }

    public void saveDates(SimpleUptcList<Group> groups) {
        DataListsUtils.groups = groups;
    }

}
