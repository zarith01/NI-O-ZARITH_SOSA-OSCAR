package com.backend.repository;

import java.util.List;

public interface IDao<T> {
    void registrar(T objeto);
    List<T> listarTodos();
}
