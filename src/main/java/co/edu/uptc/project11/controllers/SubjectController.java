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
import co.edu.uptc.project11.dtos.SubjectDto;
import co.edu.uptc.project11.exceptions.ProjectException;
import co.edu.uptc.project11.models.Subject;
import co.edu.uptc.project11.services.GroupService;
import co.edu.uptc.project11.services.SubjectService;
import co.edu.uptc.project11.utils.MyArrayUtils;

@RestController
@RequestMapping("/subjects")
public class SubjectController {
    SubjectService subjectService;

    public SubjectController(){
        subjectService = new SubjectService();
    }
    
    @GetMapping("/list")
    public ResponseEntity<Object> getAsignatures(){
        SimpleUptcList<SubjectDto> subjectsDto = SubjectDto.fromSubjects(subjectService.getSubjects());
        return ResponseEntity.ok().body(subjectsDto);
    }

    @PostMapping("/addSubject")
    public ResponseEntity<Object> addPerson(@RequestBody SubjectDto subjectDto){
        try{
            SubjectDto.validateSubject(subjectDto);
            SimpleUptcList<Subject> subjects = subjectService.getSubjects();
            subjectService.addSubject(subjects, SubjectDto.fromSubjectDto(subjectDto));
            return ResponseEntity.ok().body(subjectDto);
        } catch (ProjectException e) {
            return ResponseEntity.status(e.getMessageException().getHttpStatus()).body(e.getMessageException());
        }
    }

    @DeleteMapping("/deleteSubject/{code}")
    public ResponseEntity<Object> deleteSubject(@PathVariable String code){
        SimpleUptcList<Subject> subjects = subjectService.getSubjects();
        Subject deletedSubject= subjectService.deleteSubject(subjects, code);
        return ResponseEntity.ok().body(SubjectDto.fromSubject(deletedSubject));
    }

    @PutMapping("/setSubject/{code}")
    public ResponseEntity<Object> setSubject(@RequestBody SubjectDto subjectDto, @PathVariable String code){
        try{
            SubjectDto.validateSubject(subjectDto);
            SimpleUptcList<Subject> subjects = subjectService.getSubjects();
            Subject subject = subjectService.setSubject(subjects, code, SubjectDto.fromSubjectDto(subjectDto));
            return ResponseEntity.ok().body(SubjectDto.fromSubject(subject));
        } catch (ProjectException e) {
            return ResponseEntity.status(e.getMessageException().getHttpStatus()).body(e.getMessageException());
        }
    }

    @GetMapping("/samePlace/{identificationPlace}")
    public ResponseEntity<Object> getSubjectsSamePlace(@PathVariable String identificationPlace){
        try {
            SimpleUptcList<String> codes = getCodesSubjectPlace(identificationPlace);
            SimpleUptcList<Subject> subjects = new SimpleUptcList<>();
            SimpleUptcList<Subject> subjectsBase = subjectService.getSubjects();
            Subject subject = new Subject();
            for (String code : codes) {
                subject = subjectService.getSubjecByCode(subjectsBase, code);
                subjects.add(subject);
            }
            SimpleUptcList<SubjectDto> subjectsDto = SubjectDto.fromSubjects(subjects);
            return ResponseEntity.ok().body(subjectsDto);
        } catch (ProjectException e) {
            return ResponseEntity.status(e.getMessageException().getHttpStatus()).body(e.getMessageException());
        }
    }

    private SimpleUptcList<String> getCodesSubjectPlace(String identificationPlace) throws ProjectException{
        GroupService groupService = new GroupService();
        SimpleUptcList<String> codes = groupService.getCodesSubjectPlace(groupService.getGroups(), identificationPlace);
        codes = MyArrayUtils.removeElementsRepeat(codes);
        return codes;
    }

    @GetMapping("/moreOneGroup")
    public ResponseEntity<Object> getSubjectsMoreOneGroup(){
        try {
            SimpleUptcList<String> codes = getCodesSubjectMoreOneGroup();
            SimpleUptcList<Subject> subjects = new SimpleUptcList<>();
            SimpleUptcList<Subject> subjectsBase = subjectService.getSubjects();
            Subject subject = new Subject();
            for (String code : codes) {
                subject = subjectService.getSubjecByCode(subjectsBase, code);
                subjects.add(subject);
            }
            SimpleUptcList<SubjectDto> subjectsDto = SubjectDto.fromSubjects(subjects);
            return ResponseEntity.ok().body(subjectsDto);
        } catch (ProjectException e) {
            return ResponseEntity.status(e.getMessageException().getHttpStatus()).body(e.getMessageException());
        }
    }

    private SimpleUptcList<String> getCodesSubjectMoreOneGroup() throws ProjectException{
        GroupService groupService = new GroupService();
        SimpleUptcList<String> codes = groupService.getCodesSubjectMoreOneGroup(groupService.getGroups());
        codes = MyArrayUtils.removeElementsRepeat(codes);
        return codes;
    }

    @GetMapping("/sameSchedule")
    public ResponseEntity<Object> getSubjectsSameSchedule(@RequestBody String[] schedule){
        try {
            SimpleUptcList<String> codes = getCodesSubjectSameSchedule(schedule);
            SimpleUptcList<Subject> subjects = new SimpleUptcList<>();
            SimpleUptcList<Subject> subjectsBase = subjectService.getSubjects();
            Subject subject = new Subject();
            for (String code : codes) {
                subject = subjectService.getSubjecByCode(subjectsBase, code);
                subjects.add(subject);
            }
            SimpleUptcList<SubjectDto> subjectsDto = SubjectDto.fromSubjects(subjects);
            return ResponseEntity.ok().body(subjectsDto);
        } catch (ProjectException e) {
            return ResponseEntity.status(e.getMessageException().getHttpStatus()).body(e.getMessageException());
        }
    }

    private SimpleUptcList<String> getCodesSubjectSameSchedule(String[] schedule) throws ProjectException{
        GroupService groupService = new GroupService();
        SimpleUptcList<String> codes = groupService.getCodesSubjectSameSchedule(groupService.getGroups(), schedule);
        codes = MyArrayUtils.removeElementsRepeat(codes);
        return codes;
    }
}
