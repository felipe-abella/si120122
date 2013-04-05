package si.lab2.web;

import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import si.lab2.system.Text;

@Named
@SessionScoped
public class TextBean implements Serializable {
    private String value = "";
    @Inject
    private BookBean bookBean;
    
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
    
    public String saveText() {
        if (!value.trim().equals(""))
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("New text added to the list!"));
        else
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "New text ignored because it was empty", "The new text wasn't added to the list because it was empty!"));
        
        bookBean.getBook().addText(value);
        setValue("");
        return "index";
    }
    
    public String cancel() {
        return "index?faces-redirect=true";
    }
}
