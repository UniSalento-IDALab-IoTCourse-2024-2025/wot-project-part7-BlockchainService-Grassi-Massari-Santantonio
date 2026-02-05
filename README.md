# FastGo Blockchain Service

Questo repository contiene il microservizio "Blockchain", sviluppato in Java Spring Boot. Il servizio funge da Gateway per l'interazione con la rete Ethereum (Sepolia Testnet). Si occupa di registrare hash degli ordini su Smart Contract per garantire immutabilità e di gestire la gamification dei rider tramite l'emissione di NFT (ERC-721).

## Struttura del Progetto

.
├── src/main/java/com/fastgo/blockchain/blockchain/
│   ├── restcontroller/     # Endpoint API (Blockchain e NFT)
│   ├── service/            # Logica Web3j, Pinata e conversione dati
│   ├── wrappers/           # Classi Java generate dagli Smart Contract (Web3j)
│   ├── domain/             # Entità interne (es. BadgeLevel)
│   ├── dto/                # Data Transfer Objects (Request/Response)
│   └── repositories/       # Accesso a MongoDB
├── src/main/resources/
│   ├── application.properties # Configurazione nodi RPC e chiavi
│   └── solidity/              # ABI e BIN degli Smart Contract originali
├── build.gradle            # Gestione dipendenze (Web3j, OkHttp, etc.)
└── docker-compose.yml      # Orchestrazione locale

## Tecnologie Utilizzate

* Java 17 / Spring Boot
* Web3j: Libreria per l'interazione con nodi Ethereum/EVM.
* MongoDB: Database per caching dati e log transazioni off-chain.
* Pinata (IPFS): Servizio per l'hosting decentralizzato dei metadati NFT.
* Solidity: Smart Contracts (DeliveryStorage e RiderBadge).

## Prerequisiti

* Java JDK 17+
* MongoDB (in esecuzione locale o container)
* Wallet Ethereum.
* Account Pinata Cloud (per API Key e Secret).

## Configurazione

Il servizio richiede una configurazione sensibile per firmare le transazioni. Modificare `src/main/resources/application.properties` o passare le variabili d'ambiente:

1. Database (MongoDB):
   spring.data.mongodb.uri=mongodb://localhost:27017/blockchaindb

2. RabbitMQ:
   spring.rabbitmq.host=localhost
   spring.rabbitmq.port=5672

3. Blockchain (Ethereum/Sepolia):
   blockchain.rpc-url=https://sepolia.infura.io/v3/TUO_PROJECT_ID
   blockchain.private-key=TUA_CHIAVE_PRIVATA_WALLET (Senza 0x)
   blockchain.contract-address=INDIRIZZO_CONTRACT_DELIVERY_STORAGE
   blockchain.nft-contract-address=INDIRIZZO_CONTRACT_RIDER_BADGE
   blockchain.public-key=INDIRIZZO_PUBBLICO_WALLET

4. Integrazione Pinata (IPFS):
   fastgo.api.qr-endpoint=tuo-gateway.mypinata.cloud

## Compilazione e Avvio

1. Clean e Build:
   ./gradlew clean build

2. Avvio Applicazione:
   ./gradlew bootRun

Il servizio risponderà sulla porta configurata (default 8080 o dinamica se gestita da gateway).

## Smart Contracts Gestiti

Il servizio interagisce con due contratti (i wrapper Java si trovano in `src/main/java/.../wrappers`):

1. DeliveryStorage:
   Utilizzato per salvare in modo immutabile i dati essenziali degli ordini (ID, Hash, Timestamp).
   
2. RiderBadge (ERC-721):
   Utilizzato per creare (mintare) NFT che rappresentano i livelli raggiunti dai rider (Bronze, Silver, Gold).

## API Endpoints

### Gestione Notarizzazione (/api/blockchain)
* POST /sync
  Registra un ordine completato sulla Blockchain. Richiede i dati dell'ordine nel corpo della richiesta.
  
* GET /verify/{orderId}
  Recupera i dati di un ordine direttamente dallo Smart Contract per verificarne l'integrità.

* POST /rider/points
  Calcola il totale dei punti accumulati da un rider (utilizzato per determinare l'eleggibilità ai badge).

### Gestione NFT (/api/nft)
* POST /assign
  Assegna manualmente un badge a un rider. Carica i metadati su IPFS (Pinata) e esegue il minting del token on-chain.
  Payload: { "riderId": "...", "level": "GOLD" }

* POST /rider-badges
  Restituisce la lista di tutti i badge (NFT) posseduti da un rider specifico, leggendo dalla Blockchain.

## Generazione Wrapper Web3j

Se vengono modificati i file .sol in `resources/solidity`, è necessario rigenerare i wrapper Java. Utilizzare la CLI di Web3j:

web3j generate solidity -a src/main/resources/solidity/RiderBadge.abi -b src/main/resources/solidity/RiderBadge.bin -o src/main/java -p com.fastgo.blockchain.blockchain.wrappers