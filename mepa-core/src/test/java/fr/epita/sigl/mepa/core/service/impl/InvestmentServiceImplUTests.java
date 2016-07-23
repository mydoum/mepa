package fr.epita.sigl.mepa.core.service.impl;


import fr.epita.sigl.mepa.core.dao.InvestmentDao;
import fr.epita.sigl.mepa.core.dao.ModelDao;
import fr.epita.sigl.mepa.core.domain.Investment;
import fr.epita.sigl.mepa.core.domain.Model;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class InvestmentServiceImplUTests {

    @Mock
    InvestmentDao mockedInvestDao;

    @InjectMocks
    InvestmentServiceImpl investmentService;

    @Test
    public void createInvestment_ShouldCreateANewInvestment_WithDateVeryCloseToNow() {
        // Given
        Investment investToCreate = new Investment();
        Date now = new Date();
        long deltaInMilliseconds = 500;

        // When
        investmentService.createInvestment(investToCreate);

        // Then
        assertThat(investToCreate.getCreated()).isCloseTo(now, deltaInMilliseconds);
    }

    @Test
    public void createInvestment_ShouldCreateANewInvestment_UsingInvestmentDao() {
        // Given
        Investment investToCreate = new Investment();

        // When
        investmentService.createInvestment(investToCreate);

        // Then
        verify(mockedInvestDao).create(investToCreate);
    }





}



