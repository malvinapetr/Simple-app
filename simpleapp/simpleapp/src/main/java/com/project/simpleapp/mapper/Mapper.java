package com.project.simpleapp.mapper;

@FunctionalInterface
public interface Mapper<S, T> {
    T map(S source);
}
