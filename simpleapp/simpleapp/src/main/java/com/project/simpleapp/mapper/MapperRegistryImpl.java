package com.project.simpleapp.mapper;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import org.springframework.stereotype.Service;

import com.project.simpleapp.mapper.exception.MapperAlreadyRegisteredException;
import com.project.simpleapp.mapper.exception.MapperNotRegisteredException;

@Service
public class MapperRegistryImpl implements MapperRegistry {
    
    private final Map<ClassPair<?, ?>, Mapper<?, ?>> mappers = new HashMap<>();

    @SuppressWarnings("unchecked")
    @Override
    public <S, T> Mapper<S, T> getMapper(Class<S> sourceClass, Class<T> targetClass) {
        Mapper<?, ?> mapper = mappers.get(new ClassPair<>(sourceClass, targetClass));
        if (mapper == null) {
            throw new MapperNotRegisteredException(sourceClass, targetClass);
        }
        
        return (Mapper<S, T>) mapper;
    }

    @Override
    public <S, T> void addMapper(Class<S> sourceClass, Class<T> targetClass, Mapper<S, T> mapper) {
        ClassPair<S, T> classPair = new ClassPair<>(sourceClass, targetClass);
        Mapper<?, ?> existingMapper = mappers.get(classPair);
        if (existingMapper != null) {
            throw new MapperAlreadyRegisteredException(sourceClass, targetClass);
        }
        mappers.put(classPair, mapper);
    }

    private static class ClassPair<S, T> {
        private final Class<S> sourceClass;
        private final Class<T> targetClass;

        public ClassPair(Class<S> sourceClass, Class<T> targetClass) {
            this.sourceClass = sourceClass;
            this.targetClass = targetClass;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            ClassPair<?, ?> classPair = (ClassPair<?, ?>) o;
            return sourceClass.equals(classPair.sourceClass) &&
                    targetClass.equals(classPair.targetClass);
        }

        @Override
        public int hashCode() {
            return Objects.hash(sourceClass, targetClass);
        }
    }

}
