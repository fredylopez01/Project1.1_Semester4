package co.edu.uptc.project11.services;

import java.io.FileNotFoundException;
import java.io.IOException;

import com.google.gson.reflect.TypeToken;

import co.edu.uptc.SimpleUptcList.services.SimpleUptcList;
import co.edu.uptc.project11.exceptions.ProjectException;
import co.edu.uptc.project11.exceptions.TypeMessageEnum;
import co.edu.uptc.project11.models.Subject;
import co.edu.uptc.project11.persistence.PersistenceJSON;
import co.edu.uptc.project11.persistence.PersistenceProperties;

public class SubjectService {
    private PersistenceJSON<Subject> persistenceJSON;

    public SubjectService(){
        String route = PersistenceProperties.read("routeSubjects");
        persistenceJSON = new PersistenceJSON<>(route);
    }

    public SimpleUptcList<Subject> getSubjects() throws ProjectException {
        try {
            SimpleUptcList<Subject> subjects = loadDates();
            SimpleUptcList<Subject> subjectsAux = new SimpleUptcList<>();
            for (Subject subject : subjects) {
                subjectsAux.add(subject);
            }
            return subjectsAux;
         } catch (IOException e) {
             throw new ProjectException(TypeMessageEnum.FILE_NOT_FOUND);
         }
 
     }

    public void addSubject(SimpleUptcList<Subject> subjects, Subject subject) throws ProjectException{
        try {
            boolean isAdd = isExistSubject(subjects, subject.getCode());
            if(!isAdd){
                subjects.add(subject);
                saveDates(subjects);
            }
        } catch (FileNotFoundException e) {
            throw new ProjectException(TypeMessageEnum.FILE_NOT_FOUND);
        }
    }

    public Subject deleteSubject(SimpleUptcList<Subject> subjects, String code) throws ProjectException {
        Subject subject = new Subject();
        for (int i = 0; i < subjects.size(); i++) {
            if(subjects.get(i).getCode().equalsIgnoreCase(code)){
                subject = subjects.remove(i);
                try {
                    saveDates(subjects);
                } catch (FileNotFoundException e) {
                    throw new ProjectException(TypeMessageEnum.FILE_NOT_FOUND);
                }
            }
        }
        return subject;
    }

    public Subject setSubject(SimpleUptcList<Subject> subjects, String code, Subject newSubject) throws ProjectException {
        Subject subject = new Subject();
        for (int i = 0; i < subjects.size(); i++) {
            if(subjects.get(i).getCode().equalsIgnoreCase(code)){
                subject = subjects.set(i, newSubject);
                try {
                    saveDates(subjects);
                } catch (FileNotFoundException e) {
                    throw new ProjectException(TypeMessageEnum.FILE_NOT_FOUND);
                }
            }
        }
        return subject;
    }

    public static boolean isExistSubject(SimpleUptcList<Subject> subjects, String code){
        boolean isExist = false;
        for (Subject subject : subjects) {
            if(subject.getCode().equalsIgnoreCase(code)){
                isExist = true;
            }
        }
        return isExist;
    }

    public SimpleUptcList<Subject> loadDates() throws IOException{
        TypeToken<SimpleUptcList<Subject>> listTypeToken = new TypeToken<SimpleUptcList<Subject>>() {};
        return persistenceJSON.readDates(listTypeToken);
    }

    public void saveDates(SimpleUptcList<Subject> subjects) throws FileNotFoundException{
        persistenceJSON.writeDates(subjects);
    }

}
