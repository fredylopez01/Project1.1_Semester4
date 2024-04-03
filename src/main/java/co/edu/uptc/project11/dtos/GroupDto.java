package co.edu.uptc.project11.dtos;

import co.edu.uptc.SimpleUptcList.services.SimpleUptcList;
import co.edu.uptc.project11.exceptions.ProjectException;
import co.edu.uptc.project11.exceptions.TypeMessageEnum;
import co.edu.uptc.project11.models.Group;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GroupDto {
    private int number;
    private String subjectCode;
    private String identificationPlace;
    private String[] schedule;

    public static GroupDto fromGroup(Group group){
        GroupDto groupDto = new GroupDto();
        groupDto.setNumber(group.getNumber());
        groupDto.setSubjectCode(group.getSubjectCode());
        groupDto.setIdentificationPlace(group.getIdentificationPlace());
        groupDto.setSchedule(group.getSchedule());
        return groupDto;
    }

    public static Group fromGroupDto(GroupDto groupDto){
        Group group = new Group();
        group.setNumber(groupDto.getNumber());
        group.setSubjectCode(groupDto.getSubjectCode());
        group.setIdentificationPlace(groupDto.getIdentificationPlace());
        group.setSchedule(groupDto.getSchedule());
        return group;
    }

    public static SimpleUptcList<GroupDto> fromGroups(SimpleUptcList<Group> groups){
        SimpleUptcList<GroupDto> groupDtos = new SimpleUptcList<>();
        for (Group group : groups) {
            groupDtos.add(fromGroup(group));
        }
        return groupDtos;
    }

    public static SimpleUptcList<Group> fromGroupsDto(SimpleUptcList<GroupDto> groupsDto){
        SimpleUptcList<Group> group = new SimpleUptcList<>();
        for (GroupDto groupDto : groupsDto) {
            group.add(fromGroupDto(groupDto));
        }
        return group;
    }

    public static void validateGroup(GroupDto groupDto) throws ProjectException {
        if( groupDto.getNumber() == 0 ||
        groupDto.getIdentificationPlace() == null ||
        groupDto.getSubjectCode() == null ||
        groupDto.getSchedule() == null){
            throw new ProjectException(TypeMessageEnum.INCOMPLETE_INFORMATION);
        } else{
            if(groupDto.getSchedule().length > 3){
                throw new ProjectException(TypeMessageEnum.INCOMPLETE_INFORMATION);
            }
        }
    }
}
