/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package package1;

/**
 *
 * @author sebastian
 */
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
 
public class EmailValidator implements Validator{
    public void validate(FacesContext context, UIComponent component, Object value)
            throws ValidatorException {
         
        String email = (String) value;
         
        if(!email.contains("@")) {
            FacesMessage message = new FacesMessage();
            message.setSeverity(FacesMessage.SEVERITY_ERROR);
            message.setSummary("Email jest nieprawidłowy.");
            message.setDetail("Email jest nieprawidłowy.");
            context.addMessage("Email:", message);
            throw new ValidatorException(message);
        }
    }
}