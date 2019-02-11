package com.aconex.excavation.service.invoice;

import com.aconex.excavation.model.CostType;
import com.aconex.excavation.model.invoice.IInvoice;
import com.aconex.excavation.model.invoice.IInvoiceLineItem;
import com.aconex.excavation.model.job.*;
import org.hamcrest.CoreMatchers;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static com.aconex.excavation.model.CostType.COMMUNICATION_OVERHEAD_TYPE;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class FinanceServiceTest {
    private static final String INVALID_COST_TYPE_STRING = "aoaoaoaoaoaoa";
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
    public void setUp() {
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
    public void tearDown()  {
        financeService = null;
    }


    @Test
    public void invoiceForJob() {
        IInvoice generatedInvoice = financeService.invoiceForJob(jobMock);

        List<IInvoiceLineItem> invoiceItems = generatedInvoice.invoiceItems();
        assertThat(invoiceItems.size(), is(5));

        IInvoiceLineItem overHeadInvoiceItem = invoiceItems.get(0);
        assertThat(overHeadInvoiceItem.costType(), is(financeService.costTypeForString(COMMUNICATION_OVERHEAD_TYPE)));
        assertThat(overHeadInvoiceItem.units(), is(3));

        IInvoiceLineItem fuelUsageInvoiceItem = invoiceItems.get(1);
        assertThat(fuelUsageInvoiceItem.costType(), is(financeService.costTypeForString(CostType.FUEL_USAGE_TYPE)));
        assertThat(fuelUsageInvoiceItem.units(), is(3));

        IInvoiceLineItem unclearedSquaresInvoiceItem = invoiceItems.get(2);
        assertThat(unclearedSquaresInvoiceItem.costType(), is(financeService.costTypeForString(CostType.UNCLEARED_SQUARES_TYPE)));
        assertThat(unclearedSquaresInvoiceItem.units(), is(3));

        IInvoiceLineItem destructionTreeInvoiceItem = invoiceItems.get(3);
        assertThat(destructionTreeInvoiceItem.costType(), is(financeService.costTypeForString(CostType.DESTRUCTION_OF_PROTECTED_TREE_TYPE)));
        assertThat(destructionTreeInvoiceItem.units(), is(1));

        IInvoiceLineItem paintDamageInvoiceItem = invoiceItems.get(4);
        assertThat(paintDamageInvoiceItem.costType(), is(financeService.costTypeForString(CostType.PAINT_DAMAGE_TO_BULLDOZERG_TYPE)));
        assertThat(paintDamageInvoiceItem.units(), is(2));



    }

    @Test
    public void costTypeForString() {
        CostType localCostType = financeService.costTypeForString(COMMUNICATION_OVERHEAD_TYPE);
        assertThat(localCostType.getName(), CoreMatchers.is(COMMUNICATION_OVERHEAD_TYPE));
    }

    @Test(expected = IllegalArgumentException.class)
    public void costTypeForString_shouldReturnIllegalArgumentExceptionWhenSentUnknownString() {
        financeService.costTypeForString(INVALID_COST_TYPE_STRING);
    }

}