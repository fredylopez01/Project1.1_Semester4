package co.edu.uptc.project11.services;

import java.io.FileNotFoundException;
import java.io.IOException;

import co.edu.uptc.list.services.SimpleUptcList;
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
            boolean isAdd = false;
            for (Subject subjecti : subjects) {
                if(subjecti.getCode().equals(subject.getCode())){
                    isAdd = true;
                }
            }
            if(!isAdd){
                subjects.add(subject);
                saveDates(subjects);
            }
        } catch (FileNotFoundException e) {
            throw new ProjectException(TypeMessageEnum.FILE_NOT_FOUND);
        }
    }

    public SimpleUptcList<Subject> loadDates() throws IOException{
        return persistenceJSON.readDates("s");
    }

    public void saveDates(SimpleUptcList<Subject> subjects) throws FileNotFoundException{
        persistenceJSON.writeDates(subjects);
    }

}
