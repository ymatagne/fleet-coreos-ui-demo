package demo.models;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Getter
@Setter
@Slf4j
public class Trooper {
    public String loadState;
    public String loaded;
    public String activeState;
    public String subState;
    public Machine machineState;
    public String unitHash;
    public String port;
}
