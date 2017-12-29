package org.mmkulmala.dao;

import java.util.List;
import org.mmkulmala.entity.Message;

public interface IMessageDAO {

    List<Message> getAllMessages();

    Message getMessageById(int messageId);

    void addMessage(Message message);

    void updateMessage(Message message);

    void deleteMessage(int messageId);

    boolean messageExists(String content);

    List<Message> getMessageById(String messageId);
}
