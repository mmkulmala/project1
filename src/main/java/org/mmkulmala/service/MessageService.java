package org.mmkulmala.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.mmkulmala.entity.Message;
import org.mmkulmala.dao.IMessageDAO;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MessageService implements IMessageService {

    @Autowired
    private IMessageDAO messageDAO;
    
    // faulty method
    public List<Message> getArticleById(String messageId) {
        return messageDAO.getMessageById(messageId);
    }

    @Override
    public Message getArticleById(int messageId) {
        Message obj = messageDAO.getMessageById(messageId);
        return obj;
    }

    @Override
    public List<Message> getAllArticles() {
        return messageDAO.getAllMessages();
    }

    @Override
    public synchronized boolean addArticle(Message message) {
        if (messageDAO.messageExists(message.getContent())) {
            return false;
        } else {
            messageDAO.addMessage(message);
            return true;
        }
    }

    @Override
    public void updateArticle(Message message) {
        messageDAO.updateMessage(message);
    }

    @Override
    public void deleteArticle(int messageId) {
        messageDAO.deleteMessage(messageId);
    }
}
