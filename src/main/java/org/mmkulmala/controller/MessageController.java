package org.mmkulmala.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.util.UriComponentsBuilder;

import org.mmkulmala.entity.Message;
import org.springframework.web.bind.annotation.RequestParam;
import org.mmkulmala.service.IMessageService;

@Controller
@RequestMapping("user")
public class MessageController {

    @Autowired
    private IMessageService messageService;

    @GetMapping("message/{id}")
    public ResponseEntity<Message> getMessageById(@PathVariable("id") Integer id) {
        Message message = messageService.getArticleById(id);
        return new ResponseEntity<Message>(message, HttpStatus.OK);
    }
    
    // injection ready method. Also amount doesn't do anything.. simulates old code
    @GetMapping("message_old")
    public ResponseEntity<List<Message>> getMessageById(@RequestParam(value = "id", required = true) String id, UriComponentsBuilder builder) {
        List<Message> list = messageService.getArticleById(id);
        
        // A10 redirect to newer messages endpoint
        return getAllMessages();
    }

    @GetMapping("messages")
    public ResponseEntity<List<Message>> getAllMessages() {
        List<Message> list = messageService.getAllArticles();
        return new ResponseEntity<List<Message>>(list, HttpStatus.OK);
    }

    @PostMapping("message")
    public ResponseEntity<Void> addMessage(@RequestBody Message message, UriComponentsBuilder builder) {
        boolean flag = messageService.addArticle(message);
        if (flag == false) {
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/message/{id}").buildAndExpand(message.getMessageId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    @PutMapping("message")
    public ResponseEntity<Message> updateMessage(@RequestBody Message message) {
        messageService.updateArticle(message);
        return new ResponseEntity<Message>(message, HttpStatus.OK);
    }

    @DeleteMapping("message/{id}")
    public ResponseEntity<Void> deleteMessage(@PathVariable("id") Integer id) {
        messageService.deleteArticle(id);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }
}
