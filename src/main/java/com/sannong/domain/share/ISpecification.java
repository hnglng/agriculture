package com.sannong.domain.share;

import java.util.Map;

/**
 * Created by Bright Huang on 1/9/15.
 */
public interface ISpecification<T> {

    boolean isSatisfiedBy(T t);
    Map<Integer, String> getUnsatisfiedReasons();

}
