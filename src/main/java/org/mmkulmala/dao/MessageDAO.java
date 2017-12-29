package org.mmkulmala.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import org.mmkulmala.entity.Message;

@Transactional
@Repository
public class MessageDAO implements IMessageDAO {

    @PersistenceContext
    private EntityManager entityManager;

    /**
     * Faulty one: has inbuild sql injection. Use of String as id is also bad
     * choice..
     *
     * @param messageId
     * @return Message(s)
     */
    public List<Message> getMessageById(String messageId) {

        // injection way (also VERY bad coding ..):
        String hql = "FROM Message as mes WHERE mes.messageId='" + messageId + "'";
        return entityManager.createQuery(hql).getResultList();
    }

    /**
     * Fixed one
     * @param messageId
     * @return 
     */
    @Override
    public Message getMessageById(int messageId) {
        return entityManager.find(Message.class, messageId);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Message> getAllMessages() {
        String hql = "FROM Message as mes ORDER BY mes.messageId";
        return (List<Message>) entityManager.createQuery(hql).getResultList();
    }

    @Override
    public void addMessage(Message message) {
        System.out.println(message.getContent());
        entityManager.persist(message);
    }

    @Override
    public void updateMessage(Message message) {
        Message artcl = getMessageById(message.getMessageId());
        artcl.setContent(message.getContent());
        entityManager.flush();
    }

    @Override
    public void deleteMessage(int messageId) {
        entityManager.remove(getMessageById(messageId));
    }

    @Override
    public boolean messageExists(String content) {
        String hql = "FROM Message as mes WHERE mes.content = ?";
        int count = entityManager.createQuery(hql).setParameter(1, content).getResultList().size();
        return count > 0 ? true : false;
    }
}
