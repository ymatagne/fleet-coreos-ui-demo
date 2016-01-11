package demo.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Machine {
    public String ID;
    public String PublicIP;
    public Metadata Metadata;
    public String Version;
}
