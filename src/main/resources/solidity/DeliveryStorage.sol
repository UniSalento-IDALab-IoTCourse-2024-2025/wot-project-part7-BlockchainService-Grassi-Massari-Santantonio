// SPDX-License-Identifier: MIT
pragma solidity ^0.8.18;

contract DeliveryStorage {
    
    // Struttura che rispecchia il tuo OrderBlockchain Java
    struct Order {
        string orderId;
        string riderId;
        string shopId;
        string clientId;
        string orderDate;
        uint256 totalPrice; 
        string result;
        uint256 points;
        uint256 timestamp;  // Quando è stato salvato on-chain
    }

    // Mappa orderId -> Order
    mapping(string => Order) private orders;
    string[] private orderIds;

    // Evento per facilitare il tracking 
    event OrderRecorded(string indexed orderId, string riderId, string result);

    function recordOrder(
        string memory _orderId,
        string memory _riderId,
        string memory _shopId,
        string memory _clientId,
        string memory _orderDate,
        uint256 _totalPrice,
        string memory _result,
        uint256 _points
    ) public {
        orders[_orderId] = Order(
            _orderId,
            _riderId,
            _shopId,
            _clientId,
            _orderDate,
            _totalPrice,
            _result,
            _points,
            block.timestamp
        );
        orderIds.push(_orderId);
        
        emit OrderRecorded(_orderId, _riderId, _result);
    }

    function getOrder(string memory _orderId) public view returns (Order memory) {
        return orders[_orderId];
    }

    function getOrdersCount() public view returns (uint256) {
        return orderIds.length;
    }
}