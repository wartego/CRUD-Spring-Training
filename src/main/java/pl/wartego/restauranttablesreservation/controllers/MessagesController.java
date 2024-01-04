package pl.wartego.restauranttablesreservation.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.json.JsonParser;
import org.springframework.boot.json.JsonParserFactory;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import pl.wartego.restauranttablesreservation.models.Messages;
import pl.wartego.restauranttablesreservation.repository.MessagesRepository;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@Slf4j
public class MessagesController {
    private MessagesRepository messagesRepository;

    private List<Messages> messages;


    public MessagesController(List<Messages> messages, MessagesRepository messagesRepository) {
        this.messagesRepository = messagesRepository;
        this.messages = messages;
    }

    //http://localhost:8080/hello?sender=patryk&receiver=adam&body=ToJestBody
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @GetMapping(path = "/hello")
    public String helloWorld(@RequestParam(value = "sender", required = false) String sender, @RequestParam(value = "receiver") String receveier, @RequestParam(value = "body") String body) {
        Messages message = new Messages();

        message.setNameSender(sender);
        message.setNameReceiver(receveier);
        message.setBodyMessage(body);

        messages.add(message);
        messagesRepository.save(message);
        return String.format("Message added to database: sender: %S, receiver: %S, bodyMessage: %S",sender,receveier,body);
        // return "hello " + (name == null ? name = "World" : name ) + "!";
    }

    @GetMapping(path = "getAllMessages")
    public List<Messages> getAllMessages(){
        List<Messages> all = (List<Messages>) messagesRepository.findAll();
        return all;
    }

    @GetMapping(path = "/parse")
    @ResponseStatus(HttpStatus.OK)
    public void pareJsonAction() throws IOException {
        jsonParser();
    }

    @GetMapping(path = "/delete/{id}")
    @ResponseStatus(HttpStatus.GONE)
    public String deleteMessageById(@PathVariable Long id){
        messagesRepository.deleteById(id);
        return "Deleted message with ID = " + id;
    }


    public static void jsonParser() throws IOException {
        String url = "http://localhost:8080/getAllMessages";
        RestTemplate restTemplate = new RestTemplate();
        String resp = restTemplate.getForObject(url, String.class);

        JsonParser springParser = JsonParserFactory.getJsonParser();
        List<Object> list = springParser.parseList(resp);

        for (Object o : list) {
            if (o instanceof Map) {
                Map<String, Object> map = (Map<String, Object>) o;
                System.out.println("Items found: " + map.size());
                int i = 0;
                for (Map.Entry<String, Object> entry : map.entrySet()) {
                    System.out.println(entry.getKey() + " = " + entry.getValue());
                    i++;
                }
            }
        }

    }

}
