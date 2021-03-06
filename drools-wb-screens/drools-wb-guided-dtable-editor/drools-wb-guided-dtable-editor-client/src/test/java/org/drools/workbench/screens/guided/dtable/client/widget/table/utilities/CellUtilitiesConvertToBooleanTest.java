/*
 * Copyright 2016 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.drools.workbench.screens.guided.dtable.client.widget.table.utilities;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;

import org.drools.workbench.models.guided.dtable.shared.model.DTCellValue52;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.kie.soup.project.datamodel.oracle.DataType;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class CellUtilitiesConvertToBooleanTest {

    private Object expected;
    private Object value;
    private boolean isOtherwise;

    private CellUtilities cellUtilities;

    public CellUtilitiesConvertToBooleanTest(final Object expected,
                                             final Object value,
                                             final boolean isOtherwise) {
        this.expected = expected;
        this.value = value;
        this.isOtherwise = isOtherwise;
    }

    @Before
    public void setup() {
        cellUtilities = new CellUtilities();
    }

    @Parameterized.Parameters
    public static Collection testParameters() {
        return Arrays.asList(new Object[][]{
                {null, new BigDecimal("1"), false},
                {null, new BigInteger("2"), false},
                {null, new Byte("3"), false},
                {null, new Double("4.0"), false},
                {null, new Float("5.0"), false},
                {null, new Integer("6"), false},
                {null, new Long("7"), false},
                {null, new Short("8"), false},
                {null, "9", false},
                {true, true, false},
                {null, new Date(), false},
                {null, "banana", false},
                {true, "true", false},
                {false, "false", false},
                {null, null, true}
        });
    }

    @Test
    public void conversion() {
        final DTCellValue52 dcv = new DTCellValue52(value);
        dcv.setOtherwise(isOtherwise);
        assertEquals(expected,
                     cellUtilities.convertToBoolean(dcv));
    }

    @Test
    public void conversionToDataType() {
        final DTCellValue52 dcv = new DTCellValue52(value);
        dcv.setOtherwise(isOtherwise);
        cellUtilities.convertDTCellValueType(DataType.DataTypes.BOOLEAN,
                                             dcv);
        assertEquals(expected,
                     dcv.getBooleanValue());
    }
}
