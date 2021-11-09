package ru.pominki.presenter.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateRepoPayload {
    protected String name;
    protected Long owner;
}
