/*
 * Copyright 2020 ICONLOOP Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.iconloop.score.example;
import static java.math.BigInteger.ONE;
import static java.math.BigInteger.ZERO;
import static score.Context.require;

import com.iconloop.score.token.irc3.IRC3Basic;
import score.Address;
import score.Context;
import score.annotation.External;

import java.math.BigInteger;

import score.BranchDB;
import score.DictDB;
import score.annotation.EventLog;
import score.annotation.Optional;

public class IRC3BasicToken extends IRC3Basic {

    public IRC3BasicToken(String _name, String _symbol) {
        super(_name, _symbol);
    }
    
    @External
    public void mint(BigInteger _tokenId) {
        // simple access control - only the contract owner can mint new token
       // Context.require(Context.getCaller().equals(Context.getOwner()));
        super._mint(Context.getCaller(), _tokenId);
    }

    @External
    public void burn(BigInteger _tokenId) {
        // simple access control - only the owner of token can burn it
        Address owner = ownerOf(_tokenId);
        Context.require(Context.getCaller().equals(owner));
        super._burn(_tokenId);
    }
    @External (readonly=true)
    public String tokenURI(BigInteger tokenId) {
        if(tokenId.compareTo(new BigInteger("1")) ==0){
            return "https://634ea7934af5fdff3a634e75.mockapi.io/api/Anh1".get();
        }
        else if(tokenId.compareTo(new BigInteger("2"))==0){
            return "https://634ea7934af5fdff3a634e75.mockapi.io/api/Anh2".get();
        }
        else if(tokenId.compareTo(new BigInteger("3")) ==0){
            return "https://634ea7934af5fdff3a634e75.mockapi.io/api/Anh3".get();
        }
        else{
            return "https://634ea7934af5fdff3a634e75.mockapi.io/api/Anh4".get();
        }
    }


}
