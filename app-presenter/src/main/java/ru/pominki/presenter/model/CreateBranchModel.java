package ru.pominki.presenter.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateBranchModel {
    protected String name;
    protected Long owner;
    protected Long repoId;
}
