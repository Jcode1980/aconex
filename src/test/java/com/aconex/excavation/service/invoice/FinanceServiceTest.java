package com.aconex.excavation.service.invoice;

import com.aconex.excavation.model.CostType;
import com.aconex.excavation.model.invoice.IInvoice;
import com.aconex.excavation.model.invoice.IInvoiceLineItem;
import com.aconex.excavation.model.job.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class FinanceServiceTest {
    private FinanceService financeService;
    @Mock
    private IExcavationJob jobMock;
    @Mock
    private ISite siteMock;
    @Mock
    private IExcavator excavatorMock;
    @Mock
    private ITerrain terrainMock;
    @Mock
    private IInstruction instructionMock;



    @Before
    public void setUp() throws Exception {
        financeService = new FinanceService();

        when(jobMock.excavator()).thenReturn(excavatorMock);
        when(jobMock.site()).thenReturn(siteMock);


        when(jobMock.billableInstructions()).thenReturn(Arrays.asList(instructionMock, instructionMock, instructionMock));
        when(excavatorMock.fuelUsed()).thenReturn(3);
        when(siteMock.nonClearedTerrains()).thenReturn(Arrays.asList(terrainMock,terrainMock,terrainMock));
        when(siteMock.clearedProtectedTreesTerrains()).thenReturn(Arrays.asList(terrainMock));
        when(siteMock.clearedRockyTerrains()).thenReturn(Arrays.asList(terrainMock, terrainMock));

    }

    @After
    public void tearDown() throws Exception {
        financeService = null;
    }


    @Test
    public void invoiceForJob() {
        IInvoice generatedInvoice = financeService.invoiceForJob(jobMock);

        List<IInvoiceLineItem> invoiceItems = generatedInvoice.invoiceItems();
        assertThat(invoiceItems.size(), is(5));

        IInvoiceLineItem overHeadInvoiceItem = invoiceItems.get(0);
        assertThat(overHeadInvoiceItem.costType(), is(CostType.costTypeForString(CostType.COMMUNICATION_OVERHEAD_TYPE)));
        assertThat(overHeadInvoiceItem.units(), is(3));

        IInvoiceLineItem fuelUsageInvoiceItem = invoiceItems.get(1);
        assertThat(fuelUsageInvoiceItem.costType(), is(CostType.costTypeForString(CostType.FUEL_USAGE_TYPE)));
        assertThat(fuelUsageInvoiceItem.units(), is(3));

        IInvoiceLineItem unclearedSquaresInvoiceItem = invoiceItems.get(2);
        assertThat(unclearedSquaresInvoiceItem.costType(), is(CostType.costTypeForString(CostType.UNCLEARED_SQUARES_TYPE)));
        assertThat(unclearedSquaresInvoiceItem.units(), is(3));

        IInvoiceLineItem destructionTreeInvoiceItem = invoiceItems.get(3);
        assertThat(destructionTreeInvoiceItem.costType(), is(CostType.costTypeForString(CostType.DESTRUCTION_OF_PROTECTED_TREE_TYPE)));
        assertThat(destructionTreeInvoiceItem.units(), is(1));

        IInvoiceLineItem paintDamageInvoiceItem = invoiceItems.get(4);
        assertThat(paintDamageInvoiceItem.costType(), is(CostType.costTypeForString(CostType.PAINT_DAMAGE_TO_BULLDOZERG_TYPE)));
        assertThat(paintDamageInvoiceItem.units(), is(2));



    }

}