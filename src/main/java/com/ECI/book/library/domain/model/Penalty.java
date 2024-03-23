package com.ECI.book.library.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Penalty {


    private String id;
    private Date dueDate;
    private int amount;

    public Penalty(Date dueDate, int amount){
        this.dueDate = dueDate;
        this.amount = amount;
    }

}
