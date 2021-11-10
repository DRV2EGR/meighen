package ru.pominki.presenter.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RepoShortDto {
    protected Long ID;
    protected String name;
}
