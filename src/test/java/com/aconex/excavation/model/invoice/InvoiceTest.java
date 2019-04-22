package com.aconex.excavation.model.invoice;

import com.aconex.excavation.model.CostType;
import com.aconex.excavation.model.job.ExcavationJob;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.MockitoRule;

import static com.aconex.excavation.model.CostType.FUEL_USAGE_TYPE;
import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.hamcrest.Matchers.is;


import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class InvoiceTest {
    private IInvoice invoice;
    @Mock
    private ExcavationJob jobMock;

    @Mock
    private InvoiceLineItem invoiceItemMock;

    @Mock
    private CostType costTypeMock;

    private static final Integer MOCK_INVOICE_ITEM_UNITS = 2;
    private static final Integer MOCK_INVOICE_ITEM_CREDIT_AMOUNT = 4;


    @Before
    public void setUp(){
        invoice = new Invoice(jobMock);
        when(invoiceItemMock.creditAmount()).thenReturn(4);
        when(invoiceItemMock.units()).thenReturn(2);
        when(invoiceItemMock.costType()).thenReturn(costTypeMock);
        when(costTypeMock.getName()).thenReturn(FUEL_USAGE_TYPE);


    }

    @After
    public void tearDown(){
        invoice = null;
    }

    @Test
    public void invoiceItems() {
        invoice.addToInvoiceItems(invoiceItemMock);
        assertEquals(1, invoice.invoiceItems().size());
        assertEquals(invoiceItemMock, invoice.invoiceItems().get(0));
    }

    @Test
    public void job() {
        assertThat(invoice.job(), is(jobMock));
    }


    @Test
    public void addToInvoiceItems() {
        invoice.addToInvoiceItems(invoiceItemMock);
        assertEquals(invoice.invoiceItems().size(), 1);
        assertEquals(invoice.invoiceItems().get(0), invoiceItemMock);
    }

    @Test
    public void costsDisplayString() {
        invoice.addToInvoiceItems(invoiceItemMock);

        String costsDisplayString = invoice.costsDisplayString();

        assertThat(costsDisplayString, containsString(FUEL_USAGE_TYPE));
        assertThat(costsDisplayString, containsString(MOCK_INVOICE_ITEM_UNITS+""));
        assertThat(costsDisplayString, containsString(MOCK_INVOICE_ITEM_CREDIT_AMOUNT+""));
        assertThat(costsDisplayString, containsString("Item"));
        assertThat(costsDisplayString, containsString("Quantity"));
        assertThat(costsDisplayString, containsString("Cost"));

    }
}