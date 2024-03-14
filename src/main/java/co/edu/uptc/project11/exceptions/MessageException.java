package co.edu.uptc.project11.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class MessageException {
    private int httpStatus;
    private String Message;
    private int code;

}
