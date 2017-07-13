package com.pine.lib.func.kv;

/**
 * Created by ben on 13/07/2017.
 */

public class KV<E> {
    public E key;
    public E value;


    public KV(E key, E value)
    {
        this.key = key;
        this.value = value;
    }

    public KV()
    {

    }
}
