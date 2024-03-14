package co.edu.uptc.project11.exceptions;

import lombok.Getter;

@Getter
public class ProjectException extends Exception {
    private MessageException messageException;
    
        public ProjectException(TypeMessageEnum typeMessageEnum) {
            super(typeMessageEnum.message);
            this.messageException = new MessageException(typeMessageEnum.httpStatus, typeMessageEnum.message, typeMessageEnum.code);       
        }
        
}
