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
    public ResponseEntity<ContractDTO> addNewContract(
            @RequestBody ContractDTO contractDTO) throws ParseException {

        if (contractDTO.getId() != null)
            throw new IllegalArgumentException("ID should not be assigned when HTTP.POST to add new contract. ");

        logger.info("Going to insert new request: {}", contractDTO);
        return new ResponseEntity<>(contractService.addContract(contractDTO), HttpStatus.CREATED);

    }

    @ApiOperation(value = "Update Contract",
            notes = "更新契约信息",
            response = ContractDTO.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "请求成功"), @ApiResponse(code = 422, message = "缺少ID无法更新契约")})
    @RequestMapping(value = "", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public ResponseEntity<ContractDTO> updateContract(
            @RequestBody ContractDTO contractDTO) throws ParseException {

        if (StringUtils.isBlank(contractDTO.getId()))
            throw new IllegalArgumentException("ID must be assign when HTTP.PUT to update existing contract. ");

        logger.info("Going to update request: {}", contractDTO);
        return new ResponseEntity<>(contractService.updateContract(contractDTO), HttpStatus.OK);
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

    @ApiOperation(value = "Collect list of contracts by criteria (full list would be replied if no parameter assigned)",
            notes = "获取契约列表",
            response = ContractDTO.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "请求成功")})
    @RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<ContractDTO>> getContractsByExample(
            @ApiParam("消费者系统") @RequestParam(value = "consumerSystem", required = false) String consumerSystem,
            @ApiParam("消费者ID") @RequestParam(value = "consumerID", required = false) String consumerID,
            @ApiParam("生产者系统") @RequestParam(value = "providerSystem", required = false) String providerSystem,
            @ApiParam("生产者ID") @RequestParam(value = "providerID", required = false) String providerID,
            @ApiParam("HTTP URL") @RequestParam(value = "api", required = false) String api,
            @ApiParam("HTTP方法") @RequestParam(value = "method", required = false) String method,
            @ApiParam("契约类型") @RequestParam(value = "contractType", required = false) String contractType) {

        logger.info("Retrieving contracts info from DB.");
        return new ResponseEntity<>(contractService.getContractsByExample(consumerSystem, consumerID,
                providerSystem, providerID, api, method, contractType), HttpStatus.OK);
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
