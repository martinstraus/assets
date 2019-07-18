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
package assets;

import assets.lang.Sets;
import java.util.Comparator;
import java.util.Set;
import java.util.SortedSet;

/**
 *
 * @author martinstraus
 */
public class AssetsSetDefault implements AssetsSet {

    private final Set<Asset> set;

    public AssetsSetDefault(Set<Asset> set) {
        this.set = set;
    }

    @Override
    public SortedSet<Asset> sortedBy(Comparator<Asset> comparator) {
        return Sets.sortedBy(set, comparator);
    }

    @Override
    public SortedSet<Asset> sortedByKind() {
        return sortedBy(Asset.COMPARATOR_BY_KIND);
    }

}
