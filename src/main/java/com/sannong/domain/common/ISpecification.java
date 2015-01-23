package com.sannong.domain.common;


import java.util.List;

/**
 * Created by Bright Huang on 1/9/15.
 */
public interface ISpecification<T> {

    boolean isSatisfiedBy(T t);

    List<Error> getErrors();

}
