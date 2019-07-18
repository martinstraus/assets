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

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertThat;
import org.junit.Test;

/**
 *
 * @author martinstraus
 */
public class KindComparatorBySymbolTest {

    @Test
    public void withEqualSymbolsReturns0() {
        assertThat(
                "result for kinds with equal symbols",
                Kind.COMPARATOR_BY_SYMBOL.compare(new Kind(1, null, "AAA", ""), new Kind(1, null, "AAA", "")),
                is(0)
        );
    }

    @Test
    public void withBiggerSymbolReturnsGreaterThat0() {
        assertThat(
                "result for kinds with equal symbols",
                Kind.COMPARATOR_BY_SYMBOL.compare(new Kind(1, null, "BBB", ""), new Kind(1, null, "AAA", "")),
                is(greaterThan(0))
        );
    }

    @Test
    public void withLesserSymbolReturnsLessThan0() {
        assertThat(
                "result for kinds with equal symbols",
                Kind.COMPARATOR_BY_SYMBOL.compare(new Kind(1, null, "AAA", ""), new Kind(1, null, "BBB", "")),
                is(lessThan(0))
        );
    }

}
