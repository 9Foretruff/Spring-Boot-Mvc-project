package org.foretruff.collegemvcproject.mapper;

public interface Mapper<F, T> {

    T map(F entity);

    default T map(F fromObject, T toObject) {
        return toObject;
    }

}
