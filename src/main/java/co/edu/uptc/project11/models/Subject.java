package co.edu.uptc.project11.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Subject {
    private String name;
    private String code;
    @Override
    public String toString() {
        return name + code;
    }
}
