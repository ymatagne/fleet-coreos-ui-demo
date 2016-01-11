package hello;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class EtcdController {

    @MessageMapping("/tatooine")
    @SendTo("/planets/tatooine")
    public Planet tatooine() throws Exception {
       return new Planet("tatooine","172.20.19.01",5,true);
    }

    @MessageMapping("/kamino")
    @SendTo("/planets/kamino")
    public Planet kamino() throws Exception {
        return new Planet("kamino","172.20.19.02",40,true);
    }


    @MessageMapping("/coruscant")
    @SendTo("/planets/coruscant")
    public Planet coruscant() throws Exception {
        return new Planet("coruscant","172.20.19.03",50,true);
    }


    @MessageMapping("/naboo")
    @SendTo("/planets/naboo")
    public Planet naboo() throws Exception {
        return new Planet("naboo","172.20.19.04",100,false);
    }


    @MessageMapping("/alderaan")
    @SendTo("/planets/alderaan")
    public Planet alderaan() throws Exception {
        return new Planet("alderaan","172.20.19.05",90,true);
    }


    @MessageMapping("/hoth")
    @SendTo("/planets/hoth")
    public Planet hoth() throws Exception {
        return new Planet("hoth","172.20.19.06",15,true);
    }



}