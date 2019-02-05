package com.aconex.excavation.model.invoice;

import com.aconex.excavation.model.job.ExcavationJob;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import static org.mockito.Mockito.when;
import static org.hamcrest.Matchers.is;


import static org.junit.Assert.*;

public class InvoiceTest {
    @Mock
    private ExcavationJob jobMock;

    @Mock
    private InvoiceLineItem invoiceItemMock;

    private static final String LINE_ITEM_TEST_STRING = "Example Cost Type " + "\t\t\t\t" + "2" + "\t\t" + "20";
    private static final String INVOICE_DISPLAY_STRING_TEST = "Item\t\t\t\t Quantity\t\tCost\n";

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();



    @Before
    public void setUp(){
        when(invoiceItemMock.displayString()).thenReturn(LINE_ITEM_TEST_STRING);
    }

    //this would be an integration test.
    //how would u unit test invoiceItems()?
    @Test
    public void invoiceItems() {
        Invoice invoice = new Invoice(jobMock);
        invoice.addToInvoiceItems(invoiceItemMock);

        assertEquals(1, invoice.invoiceItems().size());
        assertEquals(invoiceItemMock, invoice.invoiceItems().get(0));
    }

    @Test
    public void job() {
        IInvoice invoice = new Invoice(jobMock);
        assertThat(invoice.job(), is(jobMock));
    }

    //this would be an integration test.
    //how would u unit test addToInvoiceItems()?
    @Test
    public void addToInvoiceItems() {
        Invoice invoice = new Invoice(jobMock);
        invoice.addToInvoiceItems(invoiceItemMock);
        assertEquals(invoice.invoiceItems().size(), 1);
        assertEquals(invoice.invoiceItems().get(0), invoiceItemMock);
    }

    @Test
    public void costsDisplayString() {
        Invoice invoice = new Invoice(jobMock);
        invoice.addToInvoiceItems(invoiceItemMock);

        StringBuilder expectedStringB = new StringBuilder("Item\t\t\t\t Quantity\t\tCost\n");
        expectedStringB.append(INVOICE_DISPLAY_STRING_TEST);

        assertEquals(expectedStringB.toString(), invoice.costsDisplayString());


    }
}