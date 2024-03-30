package co.edu.uptc.project11.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.uptc.SimpleUptcList.services.SimpleUptcList;
import co.edu.uptc.project11.dtos.GroupDto;
import co.edu.uptc.project11.exceptions.ProjectException;
import co.edu.uptc.project11.exceptions.TypeMessageEnum;
import co.edu.uptc.project11.models.Group;
import co.edu.uptc.project11.services.GroupService;
import co.edu.uptc.project11.services.PlaceService;
import co.edu.uptc.project11.services.SubjectService;

@RestController
@RequestMapping("/groups")
public class GroupController {
    GroupService groupService;

    public GroupController(){
        groupService = new GroupService();
    }

    @GetMapping("/list")
    public ResponseEntity<Object> getGroups(){
        try {
            SimpleUptcList<GroupDto> groupsDto = GroupDto.fromGroups(groupService.getGroups());
            return ResponseEntity.ok().body(groupsDto);
        } catch (ProjectException e) {
            return ResponseEntity.status(e.getMessageException().getHttpStatus()).body(e.getMessageException());
        }
    }

    @PostMapping("/addGroup")
    public ResponseEntity<Object> addGroup(@RequestBody GroupDto groupDto){
        try{
            verifyNewGroup(groupDto);
            SimpleUptcList<Group> groups = groupService.getGroups();
            groupService.addGroup(groups, GroupDto.fromGroupDto(groupDto));
            return ResponseEntity.ok().body(groupDto);
        } catch (ProjectException e) {
            return ResponseEntity.status(e.getMessageException().getHttpStatus()).body(e.getMessageException());
        }
    }

    private void verifyNewGroup(GroupDto groupDto) throws ProjectException{
        GroupDto.validateGroup(groupDto);
        SubjectService subjectService = new SubjectService();
        boolean isExistSubject = subjectService.isExistSubject(subjectService.getSubjects(), groupDto.getSubjectCode());
        PlaceService placeService = new PlaceService();
        boolean isExistPlace = PlaceService.isExistPlace(placeService.getPlaces(), groupDto.getIdentificationPlace());
        if (!isExistSubject && !isExistPlace) {
            throw new ProjectException(TypeMessageEnum.NOT_FOUND);
        }
    }

    @DeleteMapping("/deleteGroup/{identificationPlace}")
    public ResponseEntity<Object> deleteGroup(@PathVariable String identificationPlace, @RequestBody String[] schedule){
        try{
            SimpleUptcList<Group> groups = groupService.getGroups();
            Group deleteGroup = groupService.deleteGroup(groups, identificationPlace, schedule);
            return ResponseEntity.ok().body(GroupDto.fromGroup(deleteGroup));
        } catch (ProjectException e) {
            return ResponseEntity.status(e.getMessageException().getHttpStatus()).body(e.getMessageException());
        }
    }

    @PutMapping("/setGroup/{numberGroup}/{subjectCode}")
    public ResponseEntity<Object> setGroup(@RequestBody GroupDto groupDto, @PathVariable int numberGroup, @PathVariable String subjectCode){
        try{
            verifyNewGroup(groupDto);
            SimpleUptcList<Group> groups = groupService.getGroups();
            Group group = groupService.setGroup(groups, numberGroup, subjectCode , GroupDto.fromGroupDto(groupDto));
            return ResponseEntity.ok().body(GroupDto.fromGroup(group));
        } catch (ProjectException e) {
            return ResponseEntity.status(e.getMessageException().getHttpStatus()).body(e.getMessageException());
        }
    }

}
