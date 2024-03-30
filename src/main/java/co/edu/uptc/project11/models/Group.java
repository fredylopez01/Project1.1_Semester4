package co.edu.uptc.project11.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Group {
    private int number;
    private String subjectCode;
    private String identificationPlace;
    private String[] schedule;
}
