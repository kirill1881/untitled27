package org.example;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Manger {
    private long id;
    private String name;
    private int amount;
    private int dept;

    public Manger(String name, int dept) {
        this.name = name;
        this.dept = dept;
    }

    public Manger() {
    }

    public void setAmount(int amount) {
        this.amount += amount;
    }
}
