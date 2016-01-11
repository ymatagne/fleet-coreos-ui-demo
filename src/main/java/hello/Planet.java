package hello;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor()
public class Planet {
    private String name;
    private String ip;
    private int nbrTrooper;
    private boolean up;
}

