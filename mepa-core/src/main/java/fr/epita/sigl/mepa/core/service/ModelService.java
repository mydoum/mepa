package fr.epita.sigl.mepa.core.service;

import fr.epita.sigl.mepa.core.domain.Model;

import java.util.List;

public interface ModelService {

    void createModel(Model model);

    void updateModel(Model model);

    void deleteModel(Model model);

    Model getModelById(Long id);

    List<Model> getAllModels();

}