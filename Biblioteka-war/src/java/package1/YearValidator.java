/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package package1;

/**
 *
 * @author sebastian
 */
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.faces.application.FacesMessage;
 
public class YearValidator implements Validator{
    public void validate(FacesContext context, UIComponent component, Object value)
            throws ValidatorException {
        
        if(value != null){
        String numbers = "0123456789";
        String year = (String) value;
        if(year != ""){
        System.out.println("Rok: " + year);
        if(!Character.isDigit(year.charAt(0)) || !Character.isDigit(year.charAt(1)) || !Character.isDigit(year.charAt(2)) || !Character.isDigit(year.charAt(3))) {
            FacesMessage message = new FacesMessage();
            message.setSeverity(FacesMessage.SEVERITY_ERROR);
            message.setSummary("Rok jest nieprawidłowy.");
            message.setDetail("Rok jest nieprawidłowy.");
            context.addMessage("Rok:", message);
            throw new ValidatorException(message);
        }
        }
        }
    }
}
