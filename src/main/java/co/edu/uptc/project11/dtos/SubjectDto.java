package co.edu.uptc.project11.dtos;

import co.edu.uptc.list.services.SimpleUptcList;
import co.edu.uptc.project11.exceptions.ProjectException;
import co.edu.uptc.project11.exceptions.TypeMessageEnum;
import co.edu.uptc.project11.models.Subject;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SubjectDto {
    private String name;
    private String code;

    public static SubjectDto fromSubject(Subject subject){
        SubjectDto subjectDto = new SubjectDto();
        subjectDto.setName(subject.getName());
        subjectDto.setCode(subject.getCode());
        return subjectDto;
    }

    public static void validateSubject(SubjectDto subjectDto) throws ProjectException {
        if (subjectDto.getName() == null || 
        subjectDto.getCode() == null) {
            throw new ProjectException(TypeMessageEnum.INCOMPLETE_INFORMATION);
        }
    }

    public static Subject fromSubjectDto(SubjectDto subjectDto){
        Subject subject = new Subject();
        subject.setName(subjectDto.getName());
        subject.setCode(subjectDto.getCode());
        return subject;
    }

    public static SimpleUptcList<SubjectDto> fromSubjects(SimpleUptcList<Subject> subjects){
        SimpleUptcList<SubjectDto> subjectsDto = new SimpleUptcList<>();
        for (Subject subject : subjects) {
            subjectsDto.add(fromSubject(subject));
        }
        return subjectsDto;
    }

    public static SimpleUptcList<Subject> fromSubjectsDto(SimpleUptcList<SubjectDto> subjectsDto){
        SimpleUptcList<Subject> subjects = new SimpleUptcList<>();
        for (SubjectDto subjectDto : subjectsDto) {
            subjects.add(fromSubjectDto(subjectDto));
        }
        return subjects;
    }
}
