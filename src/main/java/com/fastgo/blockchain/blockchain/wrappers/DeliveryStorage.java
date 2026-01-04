package com.fastgo.blockchain.blockchain.wrappers;

import io.reactivex.Flowable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.DynamicStruct;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.BaseEventResponse;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/LFDT-web3j/web3j/tree/main/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 1.7.0.
 */
@SuppressWarnings("rawtypes")
public class DeliveryStorage extends Contract {
    public static final String BINARY = "0x608060405234801561001057600080fd5b50610f3f806100206000396000f3fe608060405234801561001057600080fd5b50600436106100415760003560e01c806344e2701314610046578063712ca0f814610062578063b5b3b05114610092575b600080fd5b610060600480360381019061005b91906107ee565b6100b0565b005b61007c6004803603810190610077919061094c565b610242565b6040516100899190610b0b565b60405180910390f35b61009a610605565b6040516100a79190610b3c565b60405180910390f35b604051806101200160405280898152602001888152602001878152602001868152602001858152602001848152602001838152602001828152602001428152506000896040516101009190610b93565b908152602001604051809103902060008201518160000190816101239190610db6565b5060208201518160010190816101399190610db6565b50604082015181600201908161014f9190610db6565b5060608201518160030190816101659190610db6565b50608082015181600401908161017b9190610db6565b5060a0820151816005015560c082015181600601908161019b9190610db6565b5060e0820151816007015561010082015181600801559050506001889080600181540180825580915050600190039060005260206000200160009091909190915090816101e89190610db6565b50876040516101f79190610b93565b60405180910390207f90f822146d0104274dd6930d647d330349767091fa8911ced9b130a770609ce28884604051610230929190610ed2565b60405180910390a25050505050505050565b61024a610612565b60008260405161025a9190610b93565b90815260200160405180910390206040518061012001604052908160008201805461028490610bd9565b80601f01602080910402602001604051908101604052809291908181526020018280546102b090610bd9565b80156102fd5780601f106102d2576101008083540402835291602001916102fd565b820191906000526020600020905b8154815290600101906020018083116102e057829003601f168201915b5050505050815260200160018201805461031690610bd9565b80601f016020809104026020016040519081016040528092919081815260200182805461034290610bd9565b801561038f5780601f106103645761010080835404028352916020019161038f565b820191906000526020600020905b81548152906001019060200180831161037257829003601f168201915b505050505081526020016002820180546103a890610bd9565b80601f01602080910402602001604051908101604052809291908181526020018280546103d490610bd9565b80156104215780601f106103f657610100808354040283529160200191610421565b820191906000526020600020905b81548152906001019060200180831161040457829003601f168201915b5050505050815260200160038201805461043a90610bd9565b80601f016020809104026020016040519081016040528092919081815260200182805461046690610bd9565b80156104b35780601f10610488576101008083540402835291602001916104b3565b820191906000526020600020905b81548152906001019060200180831161049657829003601f168201915b505050505081526020016004820180546104cc90610bd9565b80601f01602080910402602001604051908101604052809291908181526020018280546104f890610bd9565b80156105455780601f1061051a57610100808354040283529160200191610545565b820191906000526020600020905b81548152906001019060200180831161052857829003601f168201915b505050505081526020016005820154815260200160068201805461056890610bd9565b80601f016020809104026020016040519081016040528092919081815260200182805461059490610bd9565b80156105e15780601f106105b6576101008083540402835291602001916105e1565b820191906000526020600020905b8154815290600101906020018083116105c457829003601f168201915b50505050508152602001600782015481526020016008820154815250509050919050565b6000600180549050905090565b6040518061012001604052806060815260200160608152602001606081526020016060815260200160608152602001600081526020016060815260200160008152602001600081525090565b6000604051905090565b600080fd5b600080fd5b600080fd5b600080fd5b6000601f19601f8301169050919050565b7f4e487b7100000000000000000000000000000000000000000000000000000000600052604160045260246000fd5b6106c58261067c565b810181811067ffffffffffffffff821117156106e4576106e361068d565b5b80604052505050565b60006106f761065e565b905061070382826106bc565b919050565b600067ffffffffffffffff8211156107235761072261068d565b5b61072c8261067c565b9050602081019050919050565b82818337600083830152505050565b600061075b61075684610708565b6106ed565b90508281526020810184848401111561077757610776610677565b5b610782848285610739565b509392505050565b600082601f83011261079f5761079e610672565b5b81356107af848260208601610748565b91505092915050565b6000819050919050565b6107cb816107b8565b81146107d657600080fd5b50565b6000813590506107e8816107c2565b92915050565b600080600080600080600080610100898b03121561080f5761080e610668565b5b600089013567ffffffffffffffff81111561082d5761082c61066d565b5b6108398b828c0161078a565b985050602089013567ffffffffffffffff81111561085a5761085961066d565b5b6108668b828c0161078a565b975050604089013567ffffffffffffffff8111156108875761088661066d565b5b6108938b828c0161078a565b965050606089013567ffffffffffffffff8111156108b4576108b361066d565b5b6108c08b828c0161078a565b955050608089013567ffffffffffffffff8111156108e1576108e061066d565b5b6108ed8b828c0161078a565b94505060a06108fe8b828c016107d9565b93505060c089013567ffffffffffffffff81111561091f5761091e61066d565b5b61092b8b828c0161078a565b92505060e061093c8b828c016107d9565b9150509295985092959890939650565b60006020828403121561096257610961610668565b5b600082013567ffffffffffffffff8111156109805761097f61066d565b5b61098c8482850161078a565b91505092915050565b600081519050919050565b600082825260208201905092915050565b60005b838110156109cf5780820151818401526020810190506109b4565b60008484015250505050565b60006109e682610995565b6109f081856109a0565b9350610a008185602086016109b1565b610a098161067c565b840191505092915050565b610a1d816107b8565b82525050565b6000610120830160008301518482036000860152610a4182826109db565b91505060208301518482036020860152610a5b82826109db565b91505060408301518482036040860152610a7582826109db565b91505060608301518482036060860152610a8f82826109db565b91505060808301518482036080860152610aa982826109db565b91505060a0830151610abe60a0860182610a14565b5060c083015184820360c0860152610ad682826109db565b91505060e0830151610aeb60e0860182610a14565b50610100830151610b00610100860182610a14565b508091505092915050565b60006020820190508181036000830152610b258184610a23565b905092915050565b610b36816107b8565b82525050565b6000602082019050610b516000830184610b2d565b92915050565b600081905092915050565b6000610b6d82610995565b610b778185610b57565b9350610b878185602086016109b1565b80840191505092915050565b6000610b9f8284610b62565b915081905092915050565b7f4e487b7100000000000000000000000000000000000000000000000000000000600052602260045260246000fd5b60006002820490506001821680610bf157607f821691505b602082108103610c0457610c03610baa565b5b50919050565b60008190508160005260206000209050919050565b60006020601f8301049050919050565b600082821b905092915050565b600060088302610c6c7fffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff82610c2f565b610c768683610c2f565b95508019841693508086168417925050509392505050565b6000819050919050565b6000610cb3610cae610ca9846107b8565b610c8e565b6107b8565b9050919050565b6000819050919050565b610ccd83610c98565b610ce1610cd982610cba565b848454610c3c565b825550505050565b600090565b610cf6610ce9565b610d01818484610cc4565b505050565b5b81811015610d2557610d1a600082610cee565b600181019050610d07565b5050565b601f821115610d6a57610d3b81610c0a565b610d4484610c1f565b81016020851015610d53578190505b610d67610d5f85610c1f565b830182610d06565b50505b505050565b600082821c905092915050565b6000610d8d60001984600802610d6f565b1980831691505092915050565b6000610da68383610d7c565b9150826002028217905092915050565b610dbf82610995565b67ffffffffffffffff811115610dd857610dd761068d565b5b610de28254610bd9565b610ded828285610d29565b600060209050601f831160018114610e205760008415610e0e578287015190505b610e188582610d9a565b865550610e80565b601f198416610e2e86610c0a565b60005b82811015610e5657848901518255600182019150602085019450602081019050610e31565b86831015610e735784890151610e6f601f891682610d7c565b8355505b6001600288020188555050505b505050505050565b600082825260208201905092915050565b6000610ea482610995565b610eae8185610e88565b9350610ebe8185602086016109b1565b610ec78161067c565b840191505092915050565b60006040820190508181036000830152610eec8185610e99565b90508181036020830152610f008184610e99565b9050939250505056fea26469706673582212204a3ccff63fad4eebba897d0ad8ca711e2832ee74b4b1845cccfb9390b6bdaf4f64736f6c63430008120033\n";

    private static String librariesLinkedBinary;

    public static final String FUNC_GETORDER = "getOrder";

    public static final String FUNC_GETORDERSCOUNT = "getOrdersCount";

    public static final String FUNC_RECORDORDER = "recordOrder";

    public static final Event ORDERRECORDED_EVENT = new Event("OrderRecorded", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>(true) {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}));
    ;

    @Deprecated
    protected DeliveryStorage(String contractAddress, Web3j web3j, Credentials credentials,
            BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected DeliveryStorage(String contractAddress, Web3j web3j, Credentials credentials,
            ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected DeliveryStorage(String contractAddress, Web3j web3j,
            TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected DeliveryStorage(String contractAddress, Web3j web3j,
            TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static List<OrderRecordedEventResponse> getOrderRecordedEvents(
            TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = staticExtractEventParametersWithLog(ORDERRECORDED_EVENT, transactionReceipt);
        ArrayList<OrderRecordedEventResponse> responses = new ArrayList<OrderRecordedEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            OrderRecordedEventResponse typedResponse = new OrderRecordedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.orderId = (byte[]) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.riderId = (String) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.result = (String) eventValues.getNonIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public static OrderRecordedEventResponse getOrderRecordedEventFromLog(Log log) {
        Contract.EventValuesWithLog eventValues = staticExtractEventParametersWithLog(ORDERRECORDED_EVENT, log);
        OrderRecordedEventResponse typedResponse = new OrderRecordedEventResponse();
        typedResponse.log = log;
        typedResponse.orderId = (byte[]) eventValues.getIndexedValues().get(0).getValue();
        typedResponse.riderId = (String) eventValues.getNonIndexedValues().get(0).getValue();
        typedResponse.result = (String) eventValues.getNonIndexedValues().get(1).getValue();
        return typedResponse;
    }

    public Flowable<OrderRecordedEventResponse> orderRecordedEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(log -> getOrderRecordedEventFromLog(log));
    }

    public Flowable<OrderRecordedEventResponse> orderRecordedEventFlowable(
            DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(ORDERRECORDED_EVENT));
        return orderRecordedEventFlowable(filter);
    }

    public RemoteFunctionCall<Order> getOrder(String _orderId) {
        final Function function = new Function(FUNC_GETORDER, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(_orderId)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Order>() {}));
        return executeRemoteCallSingleValueReturn(function, Order.class);
    }

    public RemoteFunctionCall<BigInteger> getOrdersCount() {
        final Function function = new Function(FUNC_GETORDERSCOUNT, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<TransactionReceipt> recordOrder(String _orderId, String _riderId,
            String _shopId, String _clientId, String _orderDate, BigInteger _totalPrice,
            String _result, BigInteger _points) {
        final Function function = new Function(
                FUNC_RECORDORDER, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(_orderId), 
                new org.web3j.abi.datatypes.Utf8String(_riderId), 
                new org.web3j.abi.datatypes.Utf8String(_shopId), 
                new org.web3j.abi.datatypes.Utf8String(_clientId), 
                new org.web3j.abi.datatypes.Utf8String(_orderDate), 
                new org.web3j.abi.datatypes.generated.Uint256(_totalPrice), 
                new org.web3j.abi.datatypes.Utf8String(_result), 
                new org.web3j.abi.datatypes.generated.Uint256(_points)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    @Deprecated
    public static DeliveryStorage load(String contractAddress, Web3j web3j, Credentials credentials,
            BigInteger gasPrice, BigInteger gasLimit) {
        return new DeliveryStorage(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static DeliveryStorage load(String contractAddress, Web3j web3j,
            TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new DeliveryStorage(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static DeliveryStorage load(String contractAddress, Web3j web3j, Credentials credentials,
            ContractGasProvider contractGasProvider) {
        return new DeliveryStorage(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static DeliveryStorage load(String contractAddress, Web3j web3j,
            TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new DeliveryStorage(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<DeliveryStorage> deploy(Web3j web3j, Credentials credentials,
            ContractGasProvider contractGasProvider) {
        return deployRemoteCall(DeliveryStorage.class, web3j, credentials, contractGasProvider, getDeploymentBinary(), "");
    }

    @Deprecated
    public static RemoteCall<DeliveryStorage> deploy(Web3j web3j, Credentials credentials,
            BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(DeliveryStorage.class, web3j, credentials, gasPrice, gasLimit, getDeploymentBinary(), "");
    }

    public static RemoteCall<DeliveryStorage> deploy(Web3j web3j,
            TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(DeliveryStorage.class, web3j, transactionManager, contractGasProvider, getDeploymentBinary(), "");
    }

    @Deprecated
    public static RemoteCall<DeliveryStorage> deploy(Web3j web3j,
            TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(DeliveryStorage.class, web3j, transactionManager, gasPrice, gasLimit, getDeploymentBinary(), "");
    }

    public static void linkLibraries(List<Contract.LinkReference> references) {
        librariesLinkedBinary = linkBinaryWithReferences(BINARY, references);
    }

    private static String getDeploymentBinary() {
        if (librariesLinkedBinary != null) {
            return librariesLinkedBinary;
        } else {
            return BINARY;
        }
    }

    public static class Order extends DynamicStruct {
        public String orderId;

        public String riderId;

        public String shopId;

        public String clientId;

        public String orderDate;

        public BigInteger totalPrice;

        public String result;

        public BigInteger points;

        public BigInteger timestamp;

        public Order(String orderId, String riderId, String shopId, String clientId,
                String orderDate, BigInteger totalPrice, String result, BigInteger points,
                BigInteger timestamp) {
            super(new org.web3j.abi.datatypes.Utf8String(orderId), 
                    new org.web3j.abi.datatypes.Utf8String(riderId), 
                    new org.web3j.abi.datatypes.Utf8String(shopId), 
                    new org.web3j.abi.datatypes.Utf8String(clientId), 
                    new org.web3j.abi.datatypes.Utf8String(orderDate), 
                    new org.web3j.abi.datatypes.generated.Uint256(totalPrice), 
                    new org.web3j.abi.datatypes.Utf8String(result), 
                    new org.web3j.abi.datatypes.generated.Uint256(points), 
                    new org.web3j.abi.datatypes.generated.Uint256(timestamp));
            this.orderId = orderId;
            this.riderId = riderId;
            this.shopId = shopId;
            this.clientId = clientId;
            this.orderDate = orderDate;
            this.totalPrice = totalPrice;
            this.result = result;
            this.points = points;
            this.timestamp = timestamp;
        }

        public Order(Utf8String orderId, Utf8String riderId, Utf8String shopId, Utf8String clientId,
                Utf8String orderDate, Uint256 totalPrice, Utf8String result, Uint256 points,
                Uint256 timestamp) {
            super(orderId, riderId, shopId, clientId, orderDate, totalPrice, result, points, timestamp);
            this.orderId = orderId.getValue();
            this.riderId = riderId.getValue();
            this.shopId = shopId.getValue();
            this.clientId = clientId.getValue();
            this.orderDate = orderDate.getValue();
            this.totalPrice = totalPrice.getValue();
            this.result = result.getValue();
            this.points = points.getValue();
            this.timestamp = timestamp.getValue();
        }
    }

    public static class OrderRecordedEventResponse extends BaseEventResponse {
        public byte[] orderId;

        public String riderId;

        public String result;
    }
}
