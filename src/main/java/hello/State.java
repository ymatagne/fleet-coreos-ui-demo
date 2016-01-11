package hello;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Getter
@Setter
@Slf4j
public class State {
    public String loadState;
    public String activeState;
    public String subState;
    public Machine machineState;
    public String unitHash;

}
