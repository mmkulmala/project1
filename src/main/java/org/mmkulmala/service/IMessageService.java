package org.mmkulmala.service;

import java.util.List;

import org.mmkulmala.entity.Message;

public interface IMessageService {

    List<Message> getAllArticles();

    Message getArticleById(int messageId);

    List<Message> getArticleById(String messageId);

    boolean addArticle(Message message);

    void updateArticle(Message message);

    void deleteArticle(int messageId);
}
