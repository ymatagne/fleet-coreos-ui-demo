package demo.models;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Planet {
    private String name;
    private String ip;
    private String id;
    private List<String> troopers;
    private boolean up;
    public Planet(){
        troopers= new ArrayList<String>();
    }
}

