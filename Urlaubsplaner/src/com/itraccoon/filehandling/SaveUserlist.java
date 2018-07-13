package com.itraccoon.filehandling;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import com.itraccoon.constants.Constants;
import com.itraccoon.gui.dialogue.MessageDialogue;
import com.itraccoon.main.Runtime;
import com.itraccoon.object.Users;

public class SaveUserlist {
    
    public SaveUserlist() {
        try {
            save();
        }
        catch (JAXBException e) {
            e.printStackTrace();
        }
    }
    
    public void save() throws JAXBException {
        Users users = new Users();
        users.setUserList(Runtime.getInstance().getUserlist());
        
        JAXBContext jaxbContext = JAXBContext.newInstance(Users.class);
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
        
        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        jaxbMarshaller.marshal(users, new File(Constants.USERVERZEICHNIS));
        
        new MessageDialogue("Daten erfolgreich gesichert", "Saved");
    }
    
}
