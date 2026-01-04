# 1
 cd ~/IoT/microservizi/blockchain

 # 2 (Installa il comando con: curl -L get.web3j.io | sh)
 web3j generate solidity \
   -a src/main/resources/solidity/DeliveryStorage.abi \
   -b src/main/resources/solidity/DeliveryStorage.bin \
   -o src/main/java \
   -p com.fastgo.blockchain.blockchain.wrappers

# 3 Verifica del file generato
ls src/main/java/com/fastgo/blockchain/blockchain/wrappers/

# 4 Aggiornamento dipendenze
./gradlew clean build