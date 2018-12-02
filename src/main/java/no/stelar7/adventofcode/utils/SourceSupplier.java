package no.stelar7.adventofcode.utils;

import java.util.List;

public interface SourceSupplier<T>
{
    List<T> getDataSource();
}
