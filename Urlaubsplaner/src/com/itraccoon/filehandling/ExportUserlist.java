package com.itraccoon.filehandling;

import javax.xml.bind.JAXBException;

public class ExportUserlist {

	public ExportUserlist() {
		try {
			save();
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}

	public void save() throws JAXBException {
		// Users users = new Users();
		// users.setUserList(Runtime.getInstance().getUserlist());
		//
		// JAXBContext jaxbContext = JAXBContext.newInstance(Users.class);
		// Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
		//
		// jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		// jaxbMarshaller.marshal(users, new File(Constants.EXPORT_LOCATION));
		//
		// new MessageDialogue("Daten erfolgreich gesichert", "Saved");
	}

}
