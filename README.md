#Deloy 
goloop rpc sendtx deploy ./irc3-token/build/libs/irc3-token-0.9.0-optimized.jar \
    --uri https://sejong.net.solidwallet.io/api/v3 \
    --key_store /$HOME/gochain-local/data/godWallet.json --key_password gochain \
    --nid 0x53 --step_limit 10000000000 \
    --content_type application/java \
    --param _name=DeveraNFT --param _symbol=DEV
