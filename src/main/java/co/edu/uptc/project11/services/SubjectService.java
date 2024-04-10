package co.edu.uptc.project11.services;

import co.edu.uptc.SimpleUptcList.services.SimpleUptcList;
import co.edu.uptc.project11.models.Subject;
import co.edu.uptc.project11.utils.DataListsUtils;

public class SubjectService {

    public SubjectService(){
        Subject s1 = new Subject("matemáticas", "M1");
        Subject s2 = new Subject("socio humanistisca", "SH1");
        Subject s3 = new Subject("quimica", "Q1");
        Subject s4 = new Subject("sociales", "S1");
        Subject s5 = new Subject("programación", "P1");
        Subject s6 = new Subject("naturales", "NA1");
        Subject s7 = new Subject("musica", "MUS1");
        Subject s8 = new Subject("humanidades", "HUM1");
        Subject s9 = new Subject("algmoritmos", "A1");
        Subject s10 = new Subject("sofware", "SOF1");

        addSubject(getSubjects(), s1);
        addSubject(getSubjects(), s2);
        addSubject(getSubjects(), s3);
        addSubject(getSubjects(), s4);
        addSubject(getSubjects(), s5);
        addSubject(getSubjects(), s6);
        addSubject(getSubjects(), s7);
        addSubject(getSubjects(), s8);
        addSubject(getSubjects(), s9);
        addSubject(getSubjects(), s10);
    }

    public SimpleUptcList<Subject> getSubjects() {
        SimpleUptcList<Subject> subjects = loadDates();
        SimpleUptcList<Subject> subjectsAux = new SimpleUptcList<>();
        for (Subject subject : subjects) {
            subjectsAux.add(subject);
        }
        return subjectsAux;
    }

    public Subject getSubjecByCode(SimpleUptcList<Subject> subjects, String codeSubject){
        Subject subjectReturned = new Subject();
        for (Subject subject : subjects) {
            if(subject.getCode().equalsIgnoreCase(codeSubject)){
                subjectReturned = subject;
            }
        }
        return subjectReturned;
    }

    public void addSubject(SimpleUptcList<Subject> subjects, Subject subject) {
        boolean isAdd = isExistSubject(subjects, subject.getCode());
        if(!isAdd){
            subjects.add(subject);
            saveDates(subjects);
        }
    }

    public Subject deleteSubject(SimpleUptcList<Subject> subjects, String code) {
        Subject subject = new Subject();
        for (int i = 0; i < subjects.size(); i++) {
            if(subjects.get(i).getCode().equalsIgnoreCase(code)){
                subject = subjects.remove(i);
                saveDates(subjects);
            }
        }
        return subject;
    }

    public Subject setSubject(SimpleUptcList<Subject> subjects, String code, Subject newSubject) {
        Subject subject = new Subject();
        for (int i = 0; i < subjects.size(); i++) {
            if(subjects.get(i).getCode().equalsIgnoreCase(code)){
                subject = subjects.set(i, newSubject);
                saveDates(subjects);
            }
        }
        return subject;
    }

    public boolean isExistSubject(SimpleUptcList<Subject> subjects, String code){
        boolean isExist = false;
        for (Subject subject : subjects) {
            if(subject.getCode().equalsIgnoreCase(code)){
                isExist = true;
            }
        }
        return isExist;
    }
    
    public SimpleUptcList<Subject> loadDates() {
        return DataListsUtils.subjects;
    }

    public void saveDates(SimpleUptcList<Subject> subjects) {
        DataListsUtils.subjects = subjects;
    }

}
