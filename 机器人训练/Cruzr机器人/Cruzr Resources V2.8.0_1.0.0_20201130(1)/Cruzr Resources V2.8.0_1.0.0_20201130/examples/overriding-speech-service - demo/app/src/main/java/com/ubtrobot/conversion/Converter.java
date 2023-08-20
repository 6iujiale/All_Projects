package com.ubtrobot.conversion;

public interface Converter<F, T> {
    T convert(F var1);
}