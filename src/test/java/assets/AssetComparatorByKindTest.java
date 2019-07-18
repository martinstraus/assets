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

import java.math.BigDecimal;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertThat;
import org.junit.Test;

/**
 *
 * @author martinstraus
 */
public class AssetComparatorByKindTest {

    @Test
    public void withEqualKindReturns0() {
        assertThat(
                "result for kinds with equal symbols",
                Asset.COMPARATOR_BY_KIND.compare(
                        new Asset(new Kind(1, null, "AAA", ""), new BigDecimal(1)),
                        new Asset(new Kind(1, null, "AAA", ""), new BigDecimal(2))
                ),
                is(0)
        );
    }

    @Test
    public void withBiggerKindReturnsGreaterThat0() {
        assertThat(
                "result for kinds with equal symbols",
                Asset.COMPARATOR_BY_KIND.compare(
                        new Asset(new Kind(1, null, "BBB", ""), new BigDecimal(1)),
                        new Asset(new Kind(1, null, "AAA", ""), new BigDecimal(2))
                ),
                is(greaterThan(0))
        );
    }

    @Test
    public void withLesserKindReturnsLessThan0() {
        assertThat(
                "result for kinds with equal symbols",
                Asset.COMPARATOR_BY_KIND.compare(
                        new Asset(new Kind(1, null, "AAA", ""), new BigDecimal(1)),
                        new Asset(new Kind(1, null, "BBB", ""), new BigDecimal(2))
                ),
                is(lessThan(0))
        );
    }
}
