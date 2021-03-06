package com.santosh.preeti.messenger.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import com.santosh.preeti.messenger.database.DatabaseClass;
import com.santosh.preeti.messenger.model.Message;

public class MessageService {

	private Map<Long, Message> messages = DatabaseClass.getMessage();

	public MessageService() {
		messages.put(1L, new Message(1, "java", "santosh"));
		messages.put(2L, new Message(2, "dotNet", "Preeti"));
	}

	public List<Message> getAllMessage() {
		return new ArrayList<Message>(messages.values());
	}

	public List<Message> getAllMessagesForYear(int year) {
		List<Message> messageForYear = new ArrayList<>();
		Calendar cal = Calendar.getInstance();
		for (Message message : messages.values()) {
			cal.setTime(message.getCreated());
			if (cal.get(Calendar.YEAR) == year) {
				messageForYear.add(message);
			}
		}
		return messageForYear;
	}

	public List<Message> getAllMessagesPaginated(int start,int size) {
	 ArrayList<Message> list=new ArrayList<>(messages.values());
	 if(start + size>list.size()) return new ArrayList<Message>();
	 return list.subList(start, start+size);

	}

	public Message getMessage(long id) {
		return messages.get(id);
	}

	public Message addMessage(Message message) {
		message.setId(messages.size() + 1);
		messages.put(message.getId(), message);
		return message;
	}

	public Message updateMessage(Message message) {
		if (message.getId() <= 0) {
			return null;
		}
		messages.put(message.getId(), message);
		return message;
	}

	public Message deleteMessage(long id) {
		return messages.remove(id);
	}

}
