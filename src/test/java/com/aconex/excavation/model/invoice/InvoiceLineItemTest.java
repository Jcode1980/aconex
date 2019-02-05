package com.aconex.excavation.model.invoice;

import com.aconex.excavation.model.CostType;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;

import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.junit.Assert.*;

public class InvoiceLineItemTest {
    private InvoiceLineItem invoiceLineItem;

    @Mock
    private CostType costTypeMock;

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Before
    public void setUp() {
        invoiceLineItem = new InvoiceLineItem(costTypeMock, 2);

        when(costTypeMock.getPerUnitCost()).thenReturn(10);
    }

    @Test
    public void creditAmount() {
        assertEquals(new Integer(20), invoiceLineItem.creditAmount());
    }

    @Test
    public void displayString() {
        String expectedString = costTypeMock.getName() + "\t\t\t\t" + 2 + "\t\t" + 20;
        assertEquals(expectedString, invoiceLineItem.displayString());
    }
}