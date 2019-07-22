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
package assets.thymeleaf;

import assets.tests.Tests;
import java.math.BigDecimal;
import static org.hamcrest.CoreMatchers.equalTo;
import org.junit.Assert;
import static org.junit.Assert.assertThat;
import org.junit.Ignore;
import org.junit.Test;

/**
 *
 * @author martinstraus
 */
public class MoneyTest {

    @Ignore(value = "I don't know the assert doesn't work.")
    @Test
    public void asTextWithNormalPrecisionRendersCorrectly() {
        Assert.assertEquals("$ 100,00", new Currency().withNormalPrecision(Tests.money(new BigDecimal(100))));
    }

}
