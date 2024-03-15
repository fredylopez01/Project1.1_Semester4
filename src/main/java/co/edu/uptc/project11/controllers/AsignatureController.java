package co.edu.uptc.project11.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.uptc.list.services.SimpleUptcList;
import co.edu.uptc.project11.dtos.SubjectDto;
import co.edu.uptc.project11.exceptions.ProjectException;
import co.edu.uptc.project11.models.Subject;
import co.edu.uptc.project11.services.SubjectService;

@RestController
@RequestMapping("/subjects")
public class AsignatureController {
    SubjectService subjectService = new SubjectService();
    
    @GetMapping("/list")
    public ResponseEntity<Object> getAsignatures(){
        try {
            SimpleUptcList<SubjectDto> subjectsDto = SubjectDto.fromSubjects(subjectService.getSubjects());
            return ResponseEntity.ok().body(subjectsDto);
        } catch (ProjectException e) {
            return ResponseEntity.status(e.getMessageException().getHttpStatus()).body(e.getMessageException());
        }
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
}
