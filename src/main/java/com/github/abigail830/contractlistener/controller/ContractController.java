package com.github.abigail830.contractlistener.controller;

import com.github.abigail830.contractlistener.domain.ContractDTO;
import com.github.abigail830.contractlistener.service.ContractService;
import io.micrometer.core.instrument.util.StringUtils;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/v1/contracts")
public class ContractController {

    private static final Logger logger = LoggerFactory.getLogger(ContractController.class);

    @Autowired
    private ContractService contractService;

    @ApiOperation(value = "Add Or Update Contract",
            notes = "添加新契约，如ID已存在则更新契约信息",
            response = ContractDTO.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "请求成功")})
    @RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public List<ContractDTO> addOrUpdateNewContract(
            @RequestBody ContractDTO contractDTO) throws ParseException {
        if(StringUtils.isNotBlank(contractDTO.getId()))
            logger.info("Going to update contract: {}", contractDTO);
        else
            logger.info("Going to insert new contract: {}", contractDTO);
        return contractService.addOrUpdateContract(contractDTO);
    }

    @ApiOperation(value = "Delete Contract",
            notes = "删除契约",
            response = ContractDTO.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "请求成功")})
    @RequestMapping(value = "", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public List<ContractDTO> removeContract(
            @RequestBody ContractDTO contractDTO) throws ParseException {
        if(StringUtils.isNotBlank(contractDTO.getId())){
            logger.info("Going to delete contract: {}", contractDTO);
            return contractService.deleteContract(contractDTO);
        }else{
            logger.info("No ID info provided thus no contract would be removed from DB.");
            return contractService.getAllContract();
        }
    }

    @ApiOperation(value = "Collect full list of contracts",
            notes = "获取契约总列表",
            response = ContractDTO.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "请求成功")})
    @RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<ContractDTO> getAllContracts() {
        logger.info("Retrieving all contracts info from DB");
        return contractService.getAllContract();
    }

    @ApiOperation(value = "Collect contracts filter by provider info",
            notes = "获取指定生产者相关的契约总列表",
            response = ContractDTO.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "请求成功")})
    @RequestMapping(value = "/provider", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public List<ContractDTO> getContractsByProviderInfo(
            @ApiParam(example = "provider-system") @RequestParam(value = "providerSystem", required = false) String providerSystem,
            @ApiParam(example = "provider") @RequestParam(value = "providerName", required = false) String providerName) {

        logger.info("Retrieving contracts info from DB filtering by provider info: {}/{}", providerSystem, providerName);
        return contractService.getContractDomainByProviderInfo(providerSystem,providerName);
    }

    @ApiOperation(value = "Collect contracts filter by consumer info",
            notes = "获取指定消费者相关的契约总列表",
            response = ContractDTO.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "请求成功")})
    @RequestMapping(value = "/consumer", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public List<ContractDTO> getContractsByConsumerInfo(
            @ApiParam(example = "consumer-system") @RequestParam(value = "consumerSystem", required = false) String consumerSystem,
            @ApiParam(example = "consumer") @RequestParam(value = "consumerName", required = false) String consumerName) {

        logger.info("Retrieving contracts info from DB filtering by consumer info: {}/{}", consumerSystem, consumerName);
        return contractService.getContractDomainByConsumerInfo(consumerSystem,consumerName);
    }

    @ApiOperation(value = "Collect contracts filter by specified url",
            notes = "获取指定URL路径的契约总列表",
            response = ContractDTO.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "请求成功")})
    @RequestMapping(value = "/url", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public List<ContractDTO> getContractsByURL(
            @ApiParam(example = "/inf0/name") @RequestParam(value = "url") String url) {

        logger.info("Retrieving contracts info from DB filtering by url:{}", url);
        return contractService.getContractDomainByUrl(url);
    }

    @ApiOperation(value = "Collect contracts filter by specified id",
            notes = "获取指定ID的契约",
            response = ContractDTO.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "请求成功")})
    @RequestMapping(value = "/id/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public ContractDTO getContractsByID(
            @ApiParam(example = "/inf0/name") @RequestParam(value = "id") String id) {

        logger.info("Retrieving contracts info from DB filtering by id:{}", id);
        return contractService.getContractDomainById(id);
    }
}
