package com.aquiles.helpdesk.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Tecnico {

    List<Chamado> chamados = new ArrayList<>();
}
