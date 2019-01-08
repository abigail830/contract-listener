package com.github.abigail830.contractlistener.integration;

import com.github.abigail830.contractlistener.domain.ContractDTO;
import com.github.abigail830.contractlistener.service.ContractAuditTrailService;
import com.github.abigail830.contractlistener.service.ContractService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
//@DataMongoTest
public class ContractIntegrationTest {

	@Autowired
	ContractService contractService;

	@Autowired
	ContractAuditTrailService contractAuditTrailService;

	@Before
	public void setUp() throws Exception {
		ContractDTO contract1 = new ContractDTO("id1",
				"providerSystem", "providerID", "providerName",
				"consumerSystem", "consumerName1", "consumerName",
				"Url1","GET",
				"This is the request content","This is the response content", "This is desc","yml");
		contractService.addContract(contract1);

		ContractDTO contract2 = new ContractDTO("id2",
				"providerSystem", "providerID", "providerName",
				"consumerSystem", "consumerName2", "consumerName",
				"Url2","GET",
				"This is the request content","This is the request content", "This is desc","yml");
		contractService.addContract(contract2);
	}

	@Test
	public void getAllContract(){
		Assert.assertEquals(2, contractService.getAllContract().size());
	}

	@Test
	public void getContractDomainByApi() {
        List<ContractDTO> contractDTOByUrl1 = contractService.getContractsByExample(null, null,
                null, null, null, null, "Url1",
                null, null);
		Assert.assertEquals(1, contractDTOByUrl1.size());

        List<ContractDTO> contractDTOByUrl2 = contractService.getContractsByExample(null, null,
                null, null, null, null, "Url2",
                null, null);
		Assert.assertEquals(1, contractDTOByUrl2.size());

        List<ContractDTO> contractDTOByUrl3 = contractService.getContractsByExample(null, null,
                null, null, null, null, "Url3",
                null, null);
		Assert.assertEquals(0, contractDTOByUrl3.size());
	}

}

