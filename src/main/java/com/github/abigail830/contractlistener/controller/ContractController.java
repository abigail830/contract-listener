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
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/v1/contracts")
public class ContractController {

    private static final Logger logger = LoggerFactory.getLogger(ContractController.class);

    @Autowired
    private ContractService contractService;

    @ApiOperation(value = "Add Contract",
            notes = "添加新契约",
            response = ContractDTO.class)
    @ApiResponses(value = {@ApiResponse(code = 201, message = "创建成功")})
    @RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public ResponseEntity addNewContract(
            @RequestBody ContractDTO contractDTO) throws ParseException {

        if(StringUtils.isNotBlank(contractDTO.getId())){
            logger.warn("ID would be ignore when add new contract: {}", contractDTO.getId());
            contractDTO.setId(null);
        }
        logger.info("Going to insert new request: {}", contractDTO);
        return new ResponseEntity<ContractDTO>(contractService.addContract(contractDTO),
                HttpStatus.CREATED);
    }

    @ApiOperation(value = "Update Contract",
            notes = "更新契约信息",
            response = ContractDTO.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "请求成功"), @ApiResponse(code = 422, message = "缺少ID无法更新契约")})
    @RequestMapping(value = "", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public ResponseEntity<ContractDTO> updateContract(
            @RequestBody ContractDTO contractDTO) throws ParseException {

        if(StringUtils.isNotBlank(contractDTO.getId())){
            logger.info("Going to update request: {}", contractDTO);
            return new ResponseEntity<ContractDTO>(contractService.updateContract(contractDTO),
                    HttpStatus.OK);
        } else{
            logger.warn("ID should not be blank when trying to update request: {}", contractDTO);
            return new ResponseEntity<ContractDTO>(contractDTO,
                    HttpStatus.UNPROCESSABLE_ENTITY);
        }

    }

    @ApiOperation(value = "Delete Contract",
            notes = "删除契约",
            response = ContractDTO.class)
    @ApiResponses({@ApiResponse(code = 204, message = "删除成功")})
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity removeContract(
            @ApiParam(example = "5c32e7c29a3f126201de5f8e") @RequestParam(value = "id") String id) throws ParseException {

            logger.info("Going to delete contract with id: {}", id);
            contractService.deleteContract(id);
            return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);

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
            @ApiParam(example = "provider") @RequestParam(value = "providerID", required = false) String providerName) {

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
            @ApiParam(example = "consumer") @RequestParam(value = "consumerID", required = false) String consumerName) {

        logger.info("Retrieving contracts info from DB filtering by consumer info: {}/{}", consumerSystem, consumerName);
        return contractService.getContractDomainByConsumerInfo(consumerSystem,consumerName);
    }

    @ApiOperation(value = "Collect contracts filter by specified api",
            notes = "获取指定URL路径的契约总列表",
            response = ContractDTO.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "请求成功")})
    @RequestMapping(value = "/api", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public List<ContractDTO> getContractsByURL(
            @ApiParam(example = "/inf0/name") @RequestParam(value = "api") String url) {

        logger.info("Retrieving contracts info from DB filtering by api:{}", url);
        return contractService.getContractDomainByUrl(url);
    }

    @ApiOperation(value = "Collect contracts filter by specified id",
            notes = "获取指定ID的契约",
            response = ContractDTO.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "请求成功")})
    @RequestMapping(value = "/id/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public ContractDTO getContractsByID(
            @ApiParam(example = "5c32e7c29a3f126201de5f8e") @RequestParam(value = "id") String id) {

        logger.info("Retrieving contracts info from DB filtering by id:{}", id);
        return contractService.getContractDomainById(id);
    }
}
