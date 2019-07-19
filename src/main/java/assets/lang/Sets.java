/*
 * Copyright (C) 2019 martinstraus
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package assets.lang;

import java.util.Comparator;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 *
 * @author martinstraus
 */
public class Sets {

    public static <T> SortedSet<T> sortedBy(Set<T> set, Comparator<T> comparator) {
        SortedSet<T> sorted = new TreeSet<>(comparator);
        sorted.addAll(set);
        return sorted;
    }
    
    public static <T, U> Set<U> transformed(Set<T> set, Function<T,U> transform) {
        return set.stream().map(transform).collect(Collectors.toSet());
    }
}
