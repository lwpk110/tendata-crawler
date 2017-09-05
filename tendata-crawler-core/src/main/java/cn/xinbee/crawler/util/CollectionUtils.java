package cn.xinbee.crawler.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public abstract class CollectionUtils {

    public static <E> Collection<E> toCollection(Iterable<E> iterable) {
        if(iterable instanceof Collection) {
            return (Collection<E>) iterable;
        }
        List<E> list = new ArrayList<E>();
        if(iterable != null) {
            for(E e: iterable) {
                list.add(e);
            }
        }
        return list;
    }
    
    public static <E> List<E> toList(Iterable<E> iterable) {
        if(iterable instanceof List) {
            return (List<E>) iterable;
        }
        List<E> list = new ArrayList<E>();
        if(iterable != null) {
            for(E e: iterable) {
                list.add(e);
            }
        }
        return list;
    }
    
    public static <E> Set<E> toSet(Iterable<E> iterable) {
        if(iterable instanceof Set) {
            return (Set<E>) iterable;
        }
        Set<E> set = new HashSet<>();
        if(iterable != null) {
            for(E e: iterable) {
                set.add(e);
            }
        }
        return set;
    }
    
    private CollectionUtils(){
        
    }
}
