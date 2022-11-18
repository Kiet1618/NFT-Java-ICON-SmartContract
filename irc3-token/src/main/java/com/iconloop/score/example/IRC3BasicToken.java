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

import com.iconloop.score.token.irc3.IRC3Basic;
import score.Address;
import score.Context;
import score.annotation.External;
import com.iconloop.score.util.EnumerableMap;
import com.iconloop.score.util.IntSet;
import score.annotation.Payable;


import java.math.BigInteger;

public class IRC3BasicToken extends IRC3Basic {
    private final EnumerableMap<BigInteger, String> tokenURI = new EnumerableMap<>("URI", BigInteger.class, String.class);
    
    public static BigInteger ID = new BigInteger("1");

    public IRC3BasicToken(String _name, String _symbol) {
        super(_name, _symbol);
    }

    @External
    public void mint(BigInteger _tokenId) {
        // simple access control - only the contract owner can mint new token
        Context.require(Context.getCaller().equals(Context.getOwner()));
        super._mint(Context.getCaller(),_tokenId);
        ID = ID.add(new BigInteger("1"));
    }
    @External
    public void safeMint() {
        // simple access control - only the contract owner can mint new token
        // Context.require(Context.getCaller().equals(Context.getOwner()));
        super._mint(Context.getCaller(),ID);
        ID = ID.add(new BigInteger("1"));
    }


    @External
    public void _setTokenURI(BigInteger _tokenId, String _data){
        tokenURI.set(_tokenId, _data);
    }
    @External
    public void mintURI(BigInteger _tokenId, String _data){
        Context.require(Context.getCaller().equals(Context.getOwner()));
        super._mint(Context.getCaller(), _tokenId);
        _setTokenURI(_tokenId, _data);
        ID = ID.add(new BigInteger("1"));

    }
    @External
    public void safeMintURI(String _data){
        super._mint(Context.getCaller(), ID);
        _setTokenURI(ID, _data);
        ID = ID.add(new BigInteger("1"));

    }
    @Payable
    @External
    public void share(BigInteger _tokenId){
        super._mint(Context.getCaller(), ID);
        _setTokenURI(ID, _tokenURI(_tokenId));
        ID = ID.add(new BigInteger("1"));
    }

    @External
    public void burn(BigInteger _tokenId) {
        // simple access control - only the owner of token can burn it
        Address owner = ownerOf(_tokenId);
        Context.require(Context.getCaller().equals(owner));
        super._burn(_tokenId);
    }
    @External (readonly=true)
    public String _tokenURI(BigInteger _tokenId) {
        return tokenURI.getOrThrow(_tokenId, "Non-existent token");

    }
    
    @External(readonly=true)
    public BigInteger _tokenOfOwnerByIndex(Address _owner, int _index) {
        return super.tokenOfOwnerByIndex(_owner,_index);
    }
    @External(readonly=true)
    public Address _ownerOf(BigInteger _tokenId) {
        return super.ownerOf(_tokenId);
    }
    @External(readonly=true)
    public int _balanceOf(Address _owner) {
        
        return super.balanceOf(_owner);
    }
    @External(readonly=true)
    public int _totalSupply() {
        return super.totalSupply();
    }
}
