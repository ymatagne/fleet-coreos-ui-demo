package hello;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import mousio.etcd4j.EtcdClient;
import mousio.etcd4j.responses.EtcdAuthenticationException;
import mousio.etcd4j.responses.EtcdException;
import mousio.etcd4j.responses.EtcdKeysResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeoutException;
import java.util.stream.Collectors;

@Controller
@Slf4j
public class EtcdController {

    private ObjectMapper mapper;
    private EtcdClient etcd;

    @Autowired
    private SimpMessagingTemplate template;

    public EtcdController() throws IOException, TimeoutException, EtcdException {
        mapper = new ObjectMapper();
        etcd = new EtcdClient(URI.create("http://172.17.8.101:4001"), URI.create("http://172.17.8.102:4001"), URI.create("http://172.17.8.103:4001"), URI.create("http://172.17.8.104:4001"), URI.create("http://172.17.8.105:4001"), URI.create("http://172.17.8.106:4001"));
    }

    private Planet getInfoPlanet(final String planetName) {
        if(etcd==null){
            etcd = new EtcdClient(URI.create("http://172.17.8.101:4001"), URI.create("http://172.17.8.102:4001"), URI.create("http://172.17.8.103:4001"), URI.create("http://172.17.8.104:4001"), URI.create("http://172.17.8.105:4001"), URI.create("http://172.17.8.106:4001"));
        }
        Optional<Machine> machine = Optional.empty();

        Planet planet = new Planet();

        planet.setName(planetName);

        try {
            EtcdKeysResponse response = null;

            response = etcd.getDir("/_coreos.com/fleet/machines/").recursive().send().get();
            machine = response.node.nodes.stream().filter(p -> p != null && p.nodes.size()>0).map(m -> decodeMachine(m)).filter(m -> m.getMetadata().getPlanet().equals(planetName)).findFirst();

        } catch (IOException | EtcdAuthenticationException | TimeoutException | EtcdException e) {
            log.debug("erreur", e);
        }

        if (machine.isPresent()) {
            planet.setUp(true);
            planet.setIp(machine.get().getPublicIP());
            EtcdKeysResponse response = null;
            try {
                response = etcd.get("/services/troopers/" + machine.get().getID()).recursive().send().get();
                List<Trooper> troopers = response.node.nodes.stream().map(n -> decodeTrooper(n.value)).collect(Collectors.toList());
                troopers.stream().filter(p -> p != null).forEach(trooper -> planet.getTroopers().add(planet.getIp() + ":" + trooper.getPort()));
            } catch (IOException | EtcdException | EtcdAuthenticationException | TimeoutException e) {
                log.debug("erreur", e);
            }
        } else {
            planet.setUp(false);
        }
        return planet;
    }

    private Trooper decodeTrooper(String value) {
        try {
            return mapper.readValue(value, Trooper.class);
        } catch (IOException e) {
            log.debug("erreur dans le mapping", e);
            return null;
        }
    }


    private Machine decodeMachine(EtcdKeysResponse.EtcdNode node) {
        try {
            if(node.nodes.size()>0) {
                return mapper.readValue(node.nodes.get(0).value, Machine.class);
            }
        } catch (IOException e) {
            log.debug("erreur dans le mapping", e);
        }
        return null;
    }

    private State decodeState(String value) {
        try {
            return mapper.readValue(value, State.class);
        } catch (IOException e) {
            log.debug("erreur dans le mapping", e);
            return null;
        }
    }

    @MessageMapping("/tatooine")
    @SendTo("/planets/tatooine")
    public Planet tatooine() throws Exception {
        return getInfoPlanet("tatooine");
    }

    @MessageMapping("/kamino")
    @SendTo("/planets/kamino")
    public Planet kamino() throws Exception {
        return getInfoPlanet("kamino");
    }


    @MessageMapping("/coruscant")
    @SendTo("/planets/coruscant")
    public Planet coruscant() throws Exception {
        return getInfoPlanet("coruscant");
    }


    @MessageMapping("/naboo")
    @SendTo("/planets/naboo")
    public Planet naboo() throws Exception {
        return getInfoPlanet("naboo");
    }


    @MessageMapping("/alderaan")
    @SendTo("/planets/alderaan")
    public Planet alderaan() throws Exception {
        return getInfoPlanet("alderaan");
    }


    @MessageMapping("/hoth")
    @SendTo("/planets/hoth")
    public Planet hoth() throws Exception {
        return getInfoPlanet("hoth");
    }
}