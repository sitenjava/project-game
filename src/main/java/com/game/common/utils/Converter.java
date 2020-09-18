package com.game.common.utils;

import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

public class Converter<K,V> {
    private final Class<K> kClass;
    private final Class<V> vClass;
    private final ModelMapper modelMapper = new ModelMapper();

    public Converter(Class<K> kClass, Class<V> vClass) {
        this.kClass = kClass;
        this.vClass = vClass;
    }
    public K toDto(V value)
    {
        return modelMapper.map(value, kClass);
    }
    public V toEntity(K key)
    {
        return modelMapper.map(key,vClass);
    }
    public List<K> toDto(List<V> values)
    {
        List<K> list = new ArrayList<>();
        if (values == null || values.isEmpty())
            return null;
        for (V value : values) {
            list.add(toDto(value));
        }
        return list;
    }
    public List<V> toEntity(List<K> keys)
    {
        List<V> list = new ArrayList<>();
        if (keys == null || keys.isEmpty())
            return null;
        for (K key : keys) {
            list.add(toEntity(key));
        }
        return list;
    }

}
