package hello;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class EtcdController {

    @MessageMapping("/etcd")
    @SendTo("/traffic/planets")
    public Planet greeting() throws Exception {
       return new Planet("TataOuine");
    }

}