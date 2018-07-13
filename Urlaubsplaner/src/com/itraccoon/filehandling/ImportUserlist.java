package com.itraccoon.filehandling;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import com.itraccoon.constants.Constants;
import com.itraccoon.main.Runtime;
import com.itraccoon.object.Users;

public class ImportUserlist {
    
    public ImportUserlist() {
        try {
            load();
        }
        catch (JAXBException e) {
            e.printStackTrace();
        }
    }
    
    private void load() throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(Users.class);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        
        Users users = (Users) jaxbUnmarshaller.unmarshal(new File(Constants.EXPORT_LOCATION));
        
        Runtime.getInstance().setUserlist(users.getUserList());
    }
    
}
